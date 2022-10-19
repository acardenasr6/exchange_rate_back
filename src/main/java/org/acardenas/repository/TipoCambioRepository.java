package org.acardenas.repository;

import org.acardenas.model.TipoCambioModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Repository
public class TipoCambioRepository {

    private Logger logger = Logger.getLogger(TipoCambioRepository.class.getName());

    private JdbcTemplate jdbcTemplate;

    @Autowired
    private void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Optional<Integer> crear(TipoCambioModel tipoCambioModel) {
        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(
                    "INSERT INTO TB_TIPO_CAMBIO (moneda_origen, moneda_destino, tipo_cambio) VALUES(?,?,?) ",
                    Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, tipoCambioModel.getMonedaOrigen());
            stmt.setString(2, tipoCambioModel.getMonedaDestino());
            stmt.setDouble(3, tipoCambioModel.getTipoCambio());
            int count = stmt.executeUpdate();
            if (count == 1) {
                try (ResultSet key = stmt.getGeneratedKeys()) {
                    if (key.next()) {
                        logger.info("Nuevo tio cambio: " + tipoCambioModel.getMonedaOrigen() + " ==> " + tipoCambioModel.getMonedaDestino());
                        return Optional.of(key.getInt(1));
                    }
                }
            }
        } catch (SQLException error) {
            logger.log(Level.SEVERE, "Unexpected error", error);
        }
        return Optional.empty();
    }

    public Optional<Integer> modificar(TipoCambioModel tipoCambioModel) {
        try (Connection connection = jdbcTemplate.getDataSource().getConnection()){
            PreparedStatement stmt = connection.prepareStatement("UPDATE TB_TIPO_CAMBIO SET tipo_cambio = ? WHERE moneda_origen=? AND moneda_destino=?");
            stmt.setDouble(1, tipoCambioModel.getTipoCambio());
            stmt.setString(2, tipoCambioModel.getMonedaOrigen());
            stmt.setString(3, tipoCambioModel.getMonedaDestino());
            return Optional.of(stmt.executeUpdate());
        } catch (SQLException error) {
            logger.log(Level.SEVERE, "Unexpected error", error);
        }
        return Optional.empty();
    }

    public Optional<Double> obtenerPorMoneda(String monedaOrigen, String monedaDestino) {
        try {
            return Optional.of(jdbcTemplate.queryForObject("SELECT tipo_cambio FROM TB_TIPO_CAMBIO " +
                            "WHERE moneda_origen=? AND moneda_destino=?",
                    new Object[] { monedaOrigen, monedaDestino }, (rs, rowNum) -> rs.getDouble(1)));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }


}
