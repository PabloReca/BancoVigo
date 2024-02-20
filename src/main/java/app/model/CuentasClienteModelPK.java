package app.model;

import java.io.Serializable;
import java.util.Objects;

public class CuentasClienteModelPK implements Serializable {
    private String ccDni;
    private int ccCodCuenta;

    // Constructores, getters y setters

    public CuentasClienteModelPK() {
    }

    public CuentasClienteModelPK(String ccDni, int ccCodCuenta) {
        this.ccDni = ccDni;
        this.ccCodCuenta = ccCodCuenta;
    }

    public String getCcDni() {
        return ccDni;
    }

    public void setCcDni(String ccDni) {
        this.ccDni = ccDni;
    }

    public int getCcCodCuenta() {
        return ccCodCuenta;
    }

    public void setCcCodCuenta(int ccCodCuenta) {
        this.ccCodCuenta = ccCodCuenta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CuentasClienteModelPK that = (CuentasClienteModelPK) o;
        return ccCodCuenta == that.ccCodCuenta && Objects.equals(ccDni, that.ccDni);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ccDni, ccCodCuenta);
    }
}
