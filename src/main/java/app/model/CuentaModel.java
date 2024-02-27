package app.model;

import jakarta.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "Cuenta", schema = "BancoVigo")
public class CuentaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cuCodCuenta")
    private int cuCodCuenta;

    @Column(name = "cuCodSucursal")
    private Integer cuCodSucursal;

    @Column(name = "cuFechaCreacion")
    private Date cuFechaCreacion;

    @Column(name = "CuSaldo")
    private Integer cuSaldo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clDni", nullable = false)
    private String cliente;

    public CuentaModel() {
    }

    // Getters y setters

    public int getCuCodCuenta() {
        return cuCodCuenta;
    }

    public void setCuCodCuenta(int cuCodCuenta) {
        this.cuCodCuenta = cuCodCuenta;
    }

    public Integer getCuCodSucursal() {
        return cuCodSucursal;
    }

    public void setCuCodSucursal(Integer cuCodSucursal) {
        this.cuCodSucursal = cuCodSucursal;
    }

    public Date getCuFechaCreacion() {
        return cuFechaCreacion;
    }

    public void setCuFechaCreacion(Date cuFechaCreacion) {
        this.cuFechaCreacion = cuFechaCreacion;
    }

    public Integer getCuSaldo() {
        return cuSaldo;
    }

    public void setCuSaldo(Integer cuSaldo) {
        this.cuSaldo = cuSaldo;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }
}
