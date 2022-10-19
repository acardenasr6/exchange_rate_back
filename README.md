# Exchange rate / Tipo de cambio
## ¿De que trata este proyecto?
Exchange rate es una proyecto en java con spring boot para la conversion de moneda, aplicando en tipo de cambio según en `monto`, la `moneda de origen` y la `moneda de destino`.

## Base de datos
```sql
    CREATE TABLE IF NOT EXISTS TB_TIPO_CAMBIO  (
        id INT AUTO_INCREMENT  PRIMARY KEY,
        moneda_origen VARCHAR(250) NOT NULL,
        moneda_destino VARCHAR(250) NOT NULL,
        tipo_cambio DECIMAL(9,2)
    );