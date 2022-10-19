package org.acardenas.service;


import org.acardenas.dto.CambioRequestDTO;

public interface TipoCambioService extends MonedaService{

    Object cambio(CambioRequestDTO cambioRequestDTO);

}
