package app.model;

import jakarta.persistence.Entity;

@Entity
@jakarta.persistence.Table(name = "CuentasCliente", schema = "BancoVigo", catalog = "")
@jakarta.persistence.IdClass(app.model.CuentasClienteModelPK.class)
public class CuentasClienteModel {
    @jakarta.persistence.GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @jakarta.persistence.Id
    @jakarta.persistence.Column(name = "ccDNI")
    private String ccDni;

    public String getCcDni() {
        return ccDni;
    }

    public void setCcDni(String ccDni) {
        this.ccDni = ccDni;
    }

    @jakarta.persistence.GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @jakarta.persistence.Id
    @jakarta.persistence.Column(name = "ccCodCuenta")
    private int ccCodCuenta;

    public int getCcCodCuenta() {
        return ccCodCuenta;
    }

    public void setCcCodCuenta(int ccCodCuenta) {
        this.ccCodCuenta = ccCodCuenta;
    }
}
