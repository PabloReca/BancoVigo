package org.example.model;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "Cuenta", schema = "BancoVigo", catalog = "")
public class CuentaModel {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "cuCodCuenta")
    private int cuCodCuenta;
    @Basic
    @Column(name = "cuCodSucursal")
    private Integer cuCodSucursal;
    @Basic
    @Column(name = "cuFechaCreacion")
    private Date cuFechaCreacion;
    @Basic
    @Column(name = "CuSaldo")
    private Integer cuSaldo;

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
}
