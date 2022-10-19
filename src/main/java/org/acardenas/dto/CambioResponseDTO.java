package org.acardenas.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CambioResponseDTO{
    private Double montoConTipoCambio;
    private Double tipoCambio;
    private Double monto;
    private String monedaOrigen;
    private String monedaDestino;
}
