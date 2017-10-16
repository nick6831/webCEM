/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

/**
 *
 * @author nickm
 */
public class Alumno {
    
    private int _idAlumno;
        private String _dv;
        private String _nombres;
        private String _apePaterno; 
        private String _apeMaterno; 
        private String _correo;
        private int _reserva;
        private int _telefono;
        private String _estadoMora;

    public Alumno() {
    }

    public Alumno(int _idAlumno, String _dv, String _nombres, String _apePaterno, String _apeMaterno, String _correo, int _reserva, int _telefono, String _estadoMora) {
        this._idAlumno = _idAlumno;
        this._dv = _dv;
        this._nombres = _nombres;
        this._apePaterno = _apePaterno;
        this._apeMaterno = _apeMaterno;
        this._correo = _correo;
        this._reserva = _reserva;
        this._telefono = _telefono;
        this._estadoMora = _estadoMora;
    }

        
        
        
    public int getIdAlumno() {
        return _idAlumno;
    }

    public void setIdAlumno(int _idAlumno) {
        this._idAlumno = _idAlumno;
    }

    public String getDv() {
        return _dv;
    }

    public void setDv(String _dv) {
        this._dv = _dv;
    }

    public String getNombres() {
        return _nombres;
    }

    public void setNombres(String _nombres) {
        this._nombres = _nombres;
    }

    public String getApePaterno() {
        return _apePaterno;
    }

    public void setApePaterno(String _apePaterno) {
        this._apePaterno = _apePaterno;
    }

    public String getApeMaterno() {
        return _apeMaterno;
    }

    public void setApeMaterno(String _apeMaterno) {
        this._apeMaterno = _apeMaterno;
    }

    public String getCorreo() {
        return _correo;
    }

    public void setCorreo(String _correo) {
        this._correo = _correo;
    }

    public int getReserva() {
        return _reserva;
    }

    public void setReserva(int _reserva) {
        this._reserva = _reserva;
    }

    public int getTelefono() {
        return _telefono;
    }

    public void setTelefono(int _telefono) {
        this._telefono = _telefono;
    }

    public String getEstadoMora() {
        return _estadoMora;
    }

    public void setEstadoMora(String _estadoMora) {
        this._estadoMora = _estadoMora;
    }
        
    public String ObtenerDv(String rut)
    {  
        
       String dv =rut.substring(rut.length()-1);
        return dv;
    }
      public int ObtenerRut(String rut)
    {  
        
       int ruut =Integer.parseInt(rut.substring(rut.length()-2));
        return ruut;
    }
    
        
}
