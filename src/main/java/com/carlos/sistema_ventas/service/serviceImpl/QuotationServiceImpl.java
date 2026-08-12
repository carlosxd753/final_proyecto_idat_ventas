package com.carlos.sistema_ventas.service.serviceImpl;

import com.carlos.sistema_ventas.dto.item_quotation.ItemQuotationRequest;
import com.carlos.sistema_ventas.dto.quotation.QuotationRequest;
import com.carlos.sistema_ventas.dto.quotation.QuotationResponse;
import com.carlos.sistema_ventas.exception.BusinessException;
import com.carlos.sistema_ventas.exception.ResourceNotFoundException;
import com.carlos.sistema_ventas.mapper.QuotationMapper;
import com.carlos.sistema_ventas.model.*;
import com.carlos.sistema_ventas.repository.CustomerRepository;
import com.carlos.sistema_ventas.repository.ProductRepository;
import com.carlos.sistema_ventas.repository.QuotationRepository;
import com.carlos.sistema_ventas.repository.SellerRepository;
import com.carlos.sistema_ventas.service.QuotationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuotationServiceImpl implements QuotationService {

    private final QuotationRepository quotationRepository;
    private final CustomerRepository customerRepository;
    private final SellerRepository sellerRepository;
    private final ProductRepository productRepository;
    private final QuotationMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public List<QuotationResponse> findAll() {
        return quotationRepository.findAll().stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public QuotationResponse findById(Long id) {
        return quotationRepository.findById(id)
                .map(mapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Cotización no encontrada con id: " + id));
    }

    @Override
    @Transactional
    public QuotationResponse create(QuotationRequest request) {
        Customer customer = customerRepository.findById(request.customerId())
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con id: " + request.customerId()));
        Seller seller = sellerRepository.findById(request.sellerId())
                .orElseThrow(() -> new ResourceNotFoundException("Vendedor no encontrado con id: " + request.sellerId()));

        Quotation quotation = Quotation.builder()
                .customer(customer)
                .seller(seller)
                .items(new ArrayList<>())
                .build();

        BigDecimal total = BigDecimal.ZERO;

        for (ItemQuotationRequest itemReq : request.items()) {
            Product product = productRepository.findById(itemReq.productId())
                    .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con id: " + itemReq.productId()));

            if (product.getStock() < itemReq.quantity()) {
                throw new BusinessException("Stock insuficiente para el producto: " + product.getName());
            }

            BigDecimal unitPrice = product.getSellPrice();
            BigDecimal subtotal = unitPrice.multiply(BigDecimal.valueOf(itemReq.quantity()));

            ItemQuotation item = ItemQuotation.builder()
                    .quotation(quotation)
                    .product(product)
                    .quantity(itemReq.quantity())
                    .unitPrice(unitPrice)
                    .subtotal(subtotal)
                    .build();

            quotation.getItems().add(item);
            total = total.add(subtotal);
        }

        quotation.setTotal(total);
        return mapper.toResponse(quotationRepository.save(quotation));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Quotation quotation = quotationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cotización no encontrada con id: " + id));
        if (quotation.getSale() != null) {
            throw new BusinessException("No se puede eliminar una cotización que ya tiene una venta asociada");
        }
        quotationRepository.deleteById(id);
    }
}