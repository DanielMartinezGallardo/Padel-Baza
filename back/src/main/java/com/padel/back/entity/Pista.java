package com.padel.back.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "pistas")
public class Pista 
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numPista", nullable = false)
    private Integer numPista;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private Boolean activa = true;

    public Pista() {}

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumPista() {
        return numPista;
    }

    public void setNumPista(Integer numPista) {
        this.numPista = numPista;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean getActiva() {
        return activa;
    }

    public void setActiva(Boolean activa) {
        this.activa = activa;
    }

    
}
