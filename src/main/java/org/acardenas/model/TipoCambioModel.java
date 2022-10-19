package org.acardenas.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class TipoCambioModel {
    private Integer id;
    private String monedaOrigen;
    private String monedaDestino;
    private Double tipoCambio;
}
