package com.carlos.sistema_ventas.service.serviceImpl;

import com.carlos.sistema_ventas.dto.sale.SaleRequest;
import com.carlos.sistema_ventas.dto.sale.SaleResponse;
import com.carlos.sistema_ventas.exception.BusinessException;
import com.carlos.sistema_ventas.exception.ResourceNotFoundException;
import com.carlos.sistema_ventas.mapper.SaleMapper;
import com.carlos.sistema_ventas.model.ItemQuotation;
import com.carlos.sistema_ventas.model.Product;
import com.carlos.sistema_ventas.model.Quotation;
import com.carlos.sistema_ventas.model.Sale;
import com.carlos.sistema_ventas.repository.ProductRepository;
import com.carlos.sistema_ventas.repository.QuotationRepository;
import com.carlos.sistema_ventas.repository.SaleRepository;
import com.carlos.sistema_ventas.service.EmailService;
import com.carlos.sistema_ventas.service.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SaleServiceImpl implements SaleService {

    private final SaleRepository saleRepository;
    private final QuotationRepository quotationRepository;
    private final ProductRepository productRepository;
    private final SaleMapper mapper;
    private final EmailService emailService;

    @Override
    @Transactional(readOnly = true)
    public List<SaleResponse> findAll() {
        return saleRepository.findAll().stream().map(mapper::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public SaleResponse findById(Long id) {
        return saleRepository.findById(id)
                .map(mapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Venta no encontrada con id: " + id));
    }

    @Override
    @Transactional
    public SaleResponse create(SaleRequest request) {
        Quotation quotation = quotationRepository.findById(request.quotationId())
                .orElseThrow(() -> new ResourceNotFoundException("Cotizacion no encontrada con id: " + request.quotationId()));

        if (quotation.getSale() != null) {
            throw new BusinessException("Esta cotizacion ya tiene una venta registrada");
        }

        for (ItemQuotation item : quotation.getItems()) {
            Product product = item.getProduct();
            int quantity = item.getQuantity();

            if (product.getStock() < quantity) {
                throw new BusinessException(
                        "Stock insuficiente para el producto: " + product.getName() +
                                ". Disponible: " + product.getStock() + ", Requerido: " + quantity
                );
            }

            product.setStock(product.getStock() - quantity);
            productRepository.save(product);
        }

        Sale sale = Sale.builder()
                .quotation(quotation)
                .build();

        Sale saved = saleRepository.save(sale);

        String customerEmail = quotation.getCustomer().getAddress();
        if (customerEmail != null && !customerEmail.isBlank()) {
            emailService.sendInvoiceEmail(
                    customerEmail,
                    quotation.getCustomer().getName(),
                    quotation
            );
        }

        return mapper.toResponse(saved);
    }
}