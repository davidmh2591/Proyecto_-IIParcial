/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hn.uth.pa1.p1.objetos;

/**
 *
 * @author uth
 */
public class Humano {
    private int id;
    private String nombre;
    private String apellido;
    private double peso;
    private String sexo;
    private String departamento;
    private String municipio;

    public Humano(int id, String nombre, String apellido, double peso, String sexo, String departamento, String municipio) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.peso = peso;
        this.sexo = sexo;
        this.departamento = departamento;
        this.municipio = municipio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    @Override
    public String toString() {
        return "Humano{" + "id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", peso=" + peso + ", sexo=" + sexo + ", departamento=" + departamento + ", municipio=" + municipio + '}';
    }
    
    
    
}


