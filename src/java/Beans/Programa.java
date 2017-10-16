/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import java.util.Date;

/**
 *
 * @author portafolio
 */
public class Programa {
    public int _idPrograma;
    public String _nombrePrograma;
    public String _lugar;
    public Date _fechaInicio;
    public Date _fechaTermino;
    public String _descripcion;

    public Programa() {
    }

    public int getIdPrograma() {
        return _idPrograma;
    }

    public void setIdPrograma(int _idPrograma) {
        this._idPrograma = _idPrograma;
    }

    public String getNombrePrograma() {
        return _nombrePrograma;
    }

    public void setNombrePrograma(String _nombrePrograma) {
        this._nombrePrograma = _nombrePrograma;
    }

    public String getLugar() {
        return _lugar;
    }

    public void setLugar(String _lugar) {
        this._lugar = _lugar;
    }

    public Date getFechaInicio() {
        return _fechaInicio;
    }

    public void setFechaInicio(Date _fechaInicio) {
        this._fechaInicio = _fechaInicio;
    }

    public Date getFechaTermino() {
        return _fechaTermino;
    }

    public void setFechaTermino(Date _fechaTermino) {
        this._fechaTermino = _fechaTermino;
    }

    public String getDescripcion() {
        return _descripcion;
    }

    public void setDescripcion(String _descripcion) {
        this._descripcion = _descripcion;
    }
    
    
    
}
