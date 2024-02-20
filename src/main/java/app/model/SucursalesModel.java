package app.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "Sucursales", schema = "BancoVigo", catalog = "")
public class SucursalesModel {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "suCodSucursal")
    private int suCodSucursal;
    @Basic
    @Column(name = "suCiudad")
    private String suCiudad;
    @Basic
    @Column(name = "suActivo")
    private BigDecimal suActivo;

    public int getSuCodSucursal() {
        return suCodSucursal;
    }

    public void setSuCodSucursal(int suCodSucursal) {
        this.suCodSucursal = suCodSucursal;
    }

    public String getSuCiudad() {
        return suCiudad;
    }

    public void setSuCiudad(String suCiudad) {
        this.suCiudad = suCiudad;
    }

    public BigDecimal getSuActivo() {
        return suActivo;
    }

    public void setSuActivo(BigDecimal suActivo) {
        this.suActivo = suActivo;
    }
}
