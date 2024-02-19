package org.example.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Cliente", schema = "BancoVigo", catalog = "")
public class ClienteModel {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "clDni")
    private String clDni;
    @Basic
    @Column(name = "clNombre")
    private String clNombre;
    @Basic
    @Column(name = "clApellido")
    private String clApellido;
    @Basic
    @Column(name = "clTelefono")
    private Integer clTelefono;

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
}
