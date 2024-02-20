package org.example.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Cliente", schema = "BancoVigo")
public class ClienteModel {
    @Id
    @Column(name = "clDni")
    private String clDni;

    @Column(name = "clNombre")
    private String clNombre;

    @Column(name = "clApellido")
    private String clApellido;

    @Column(name = "clTelefono")
    private Integer clTelefono;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CuentaModel> cuentas = new HashSet<>();

    // Constructor, getters y setters

    public ClienteModel() {
    }

    public String getClDni() {
        return clDni;
    }

    public void setClDni(String clDni) {
        this.clDni = clDni;
    }

    public String getClNombre() {
        return clNombre;
    }

    public void setClNombre(String clNombre) {
        this.clNombre = clNombre;
    }

    public String getClApellido() {
        return clApellido;
    }

    public void setClApellido(String clApellido) {
        this.clApellido = clApellido;
    }

    public Integer getClTelefono() {
        return clTelefono;
    }

    public void setClTelefono(Integer clTelefono) {
        this.clTelefono = clTelefono;
    }

    public Set<CuentaModel> getCuentas() {
        return cuentas;
    }

    public void setCuentas(Set<CuentaModel> cuentas) {
        this.cuentas = cuentas;
    }

    public void addCuenta(CuentaModel cuenta) {
        cuentas.add(cuenta);
        cuenta.setCliente(this);
    }

    public void removeCuenta(CuentaModel cuenta) {
        cuentas.remove(cuenta);
        cuenta.setCliente(null);
    }
}
