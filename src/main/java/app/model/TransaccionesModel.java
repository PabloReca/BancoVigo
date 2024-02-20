package app.model;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "Transacciones", schema = "BancoVigo", catalog = "")
public class TransaccionesModel {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private int id;
    @Basic
    @Column(name = "trCodCuenta")
    private Integer trCodCuenta;
    @Basic
    @Column(name = "trFechaTransaccion")
    private Date trFechaTransaccion;
    @Basic
    @Column(name = "trTipo")
    private Object trTipo;
    @Basic
    @Column(name = "trCantidad")
    private Integer trCantidad;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getTrCodCuenta() {
        return trCodCuenta;
    }

    public void setTrCodCuenta(Integer trCodCuenta) {
        this.trCodCuenta = trCodCuenta;
    }

    public Date getTrFechaTransaccion() {
        return trFechaTransaccion;
    }

    public void setTrFechaTransaccion(Date trFechaTransaccion) {
        this.trFechaTransaccion = trFechaTransaccion;
    }

    public Object getTrTipo() {
        return trTipo;
    }

    public void setTrTipo(Object trTipo) {
        this.trTipo = trTipo;
    }

    public Integer getTrCantidad() {
        return trCantidad;
    }

    public void setTrCantidad(Integer trCantidad) {
        this.trCantidad = trCantidad;
    }
}
