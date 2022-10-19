package org.acardenas.service;

import org.acardenas.model.TipoCambioModel;

public interface MonedaService {
    int crear(TipoCambioModel moneda);

    int modificar(TipoCambioModel moneda);
}
