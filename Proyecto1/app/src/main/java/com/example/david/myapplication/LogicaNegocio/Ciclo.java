package com.example.david.myapplication.LogicaNegocio;

import java.io.Serializable;

public class Ciclo implements Serializable {
    private String codigo;
    private String ano;
    private char estado;
    private char numero;
    private String fecha_inicio;
    private String fecha_finalizacion;

    public Ciclo() {
        this.codigo = "";
        this.ano = "";
        this.estado = ' ';
        this.numero = ' ';
        this.fecha_inicio = null;
        this.fecha_finalizacion = null;
    }

    public Ciclo(String codigo, String ano, char estado, char numero, String fecha_inicio, String fecha_finalizacion) {
        this.codigo = codigo;
        this.ano = ano;
        this.estado = estado;
        this.numero = numero;
        this.fecha_inicio = fecha_inicio;
        this.fecha_finalizacion = fecha_finalizacion;
    }



    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public char getEstado() {
        return estado;
    }

    public void setEstado(char estado) {
        this.estado = estado;
    }

    public char getNumero() {
        return numero;
    }

    public void setNumero(char numero) {
        this.numero = numero;
    }

    public String getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(String fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public String getFecha_finalizacion() {
        return fecha_finalizacion;
    }

    public void setFecha_finalizacion(String fecha_finalizacion) {
        this.fecha_finalizacion = fecha_finalizacion;
    }



    //@Override
    public String toString() {
        return codigo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ciclo ciclo = (Ciclo) o;

        if (getCodigo() != ciclo.getCodigo()) return false;
        return getAno() != null ? getAno().equals(ciclo.getAno()) : ciclo.getAno() == null;
    }

//    @Override
//    public int hashCode() {
//        int result = getAno();
//        result = 31 * result + (getNumero() != null ? getNumero().hashCode() : 0);
//        result = 31 * result + (getFinicio() != null ? getFinicio().hashCode() : 0);
//        result = 31 * result + (getFfinal() != null ? getFfinal().hashCode() : 0);
//        return result;
//    }

}
