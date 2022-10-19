package org.acardenas.service.impl;

import org.acardenas.dto.CambioRequestDTO;
import org.acardenas.dto.CambioResponseDTO;
import org.acardenas.model.TipoCambioModel;
import org.acardenas.pojo.ObjectResponse;
import org.acardenas.repository.TipoCambioRepository;
import org.acardenas.service.TipoCambioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TipoCambioServiceImpl implements TipoCambioService {

    private final TipoCambioRepository tipoCambioRepository;

    @Autowired
    public TipoCambioServiceImpl(TipoCambioRepository tipoCambioRepository){
        this.tipoCambioRepository = tipoCambioRepository;
    }

    @Override
    public int crear(TipoCambioModel tipoCambioModel) {
       return tipoCambioRepository.crear(tipoCambioModel).isPresent() ? 1 : 0;
    }

    @Override
    public int modificar(TipoCambioModel tipoCambioModel) {
        Optional<Double> tipoCambio = tipoCambioRepository.obtenerPorMoneda(tipoCambioModel.getMonedaOrigen(),tipoCambioModel.getMonedaDestino());
        return  tipoCambio.isPresent() && tipoCambioRepository.modificar(tipoCambioModel).isPresent() ? 1 : 0;

    }

    @Override
    public Object cambio(CambioRequestDTO cambioRequestDTO) {
        Optional<Double> tipoCambio = tipoCambioRepository.obtenerPorMoneda(cambioRequestDTO.getMonedaOrigen(),cambioRequestDTO.getMonedaDestino());
        return tipoCambio.isPresent() ? aplicarCambio(tipoCambio.get(), cambioRequestDTO)
                : "Aun no tenemos el tipo de cambio para la moneda ingresada";
    }

    private CambioResponseDTO aplicarCambio(Double tipoCambio, CambioRequestDTO cambioRequestDTO){
        return CambioResponseDTO.builder()
                .montoConTipoCambio(tipoCambio * cambioRequestDTO.getMonto())
                .tipoCambio(tipoCambio)
                .monto(cambioRequestDTO.getMonto())
                .monedaOrigen(cambioRequestDTO.getMonedaOrigen())
                .monedaDestino(cambioRequestDTO.getMonedaDestino())
                .build();
    }

}
