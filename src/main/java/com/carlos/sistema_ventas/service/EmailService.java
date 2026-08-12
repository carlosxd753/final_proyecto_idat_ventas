package com.carlos.sistema_ventas.service;

import com.carlos.sistema_ventas.model.ItemQuotation;
import com.carlos.sistema_ventas.model.Quotation;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter
            .ofPattern("dd 'de' MMMM 'de' yyyy, HH:mm")
            .withLocale(new Locale("es", "PE"));

    public void sendInvoiceEmail(String to, String customerName, Quotation quotation) {
        try {
            var message = mailSender.createMimeMessage();
            var helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom("caminatas.ecorutas@gmail.com");
            helper.setTo(to);
            helper.setSubject("Factura de tu compra - Cotizacion #" + quotation.getId());
            helper.setText(buildInvoiceHtml(customerName, quotation), true);

            mailSender.send(message);
            System.out.println("Email enviado exitosamente a: " + to);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("No se pudo enviar el email: " + e.getMessage(), e);
        }
    }

    private String buildInvoiceHtml(String customerName, Quotation q) {
        String fechaFormateada = q.getCreatedAt().format(DATE_FORMATTER);

        StringBuilder itemsHtml = new StringBuilder();
        for (ItemQuotation item : q.getItems()) {
            itemsHtml.append(String.format("""
                <tr>
                    <td style="padding:10px;border-bottom:1px solid #eee;">%s</td>
                    <td style="padding:10px;border-bottom:1px solid #eee;text-align:center;">%d</td>
                    <td style="padding:10px;border-bottom:1px solid #eee;text-align:right;">S/ %s</td>
                    <td style="padding:10px;border-bottom:1px solid #eee;text-align:right;">S/ %s</td>
                </tr>
                """,
                    item.getProduct().getName(),
                    item.getQuantity(),
                    item.getUnitPrice(),
                    item.getSubtotal()
            ));
        }

        return """
            <!DOCTYPE html>
            <html>
            <head><meta charset="UTF-8"></head>
            <body style="font-family:Arial,sans-serif;background:#f5f5f5;padding:20px;">
                <div style="max-width:600px;margin:0 auto;background:white;border-radius:8px;overflow:hidden;box-shadow:0 2px 8px rgba(0,0,0,0.1);">
                    <div style="background:#1a1a2e;color:white;padding:20px;text-align:center;">
                        <h1 style="margin:0;">FACTURA</h1>
                        <p style="margin:5px 0 0;">Cotizacion #%d</p>
                    </div>
                    <div style="padding:20px;">
                        <p><strong>Cliente:</strong> %s</p>
                        <p><strong>Vendedor:</strong> %s</p>
                        <p><strong>Fecha:</strong> %s</p>
                        <hr style="border:none;border-top:1px solid #eee;margin:20px 0;">
                        <table style="width:100%%;border-collapse:collapse;">
                            <thead>
                                <tr style="background:#f8f9fa;">
                                    <th style="padding:10px;text-align:left;">Producto</th>
                                    <th style="padding:10px;text-align:center;">Cant</th>
                                    <th style="padding:10px;text-align:right;">P. Unit</th>
                                    <th style="padding:10px;text-align:right;">Subtotal</th>
                                </tr>
                            </thead>
                            <tbody>
                                %s
                            </tbody>
                        </table>
                        <div style="text-align:right;margin-top:20px;padding-top:20px;border-top:2px solid #1a1a2e;">
                            <h2 style="margin:0;color:#1a1a2e;">TOTAL: S/ %s</h2>
                        </div>
                        <p style="margin-top:30px;font-size:12px;color:#666;text-align:center;">
                            Este es un comprobante de simulacion generado automaticamente.
                        </p>
                    </div>
                </div>
            </body>
            </html>
            """.formatted(
                q.getId(),
                customerName,
                q.getSeller().getName(),
                fechaFormateada,
                itemsHtml.toString(),
                q.getTotal()
        );
    }
}