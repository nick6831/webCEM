/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

/**
 *
 * @author portafolio
 */
public class Usuario {
        private String _idUsuario;
        private String _nombreUsuario;
        private String _password;
        private String _idAlumno;
        private String _idAdministrativo;
        private String _idRol;
        private String _idFamilia;
        private String _idEncargadoCel;

    public Usuario() {
    }

    public String getIdUsuario() {
        return _idUsuario;
    }

    public void setIdUsuario(String _idUsuario) {
        this._idUsuario = _idUsuario;
    }

    public String getNombreUsuario() {
        return _nombreUsuario;
    }

    public void setNombreUsuario(String _nombreUsuario) {
        this._nombreUsuario = _nombreUsuario;
    }

    public String getPassword() {
        return _password;
    }

    public void setPassword(String _password) {
        this._password = _password;
    }

    public String getIdAlumno() {
        return _idAlumno;
    }

    public void setIdAlumno(String _idAlumno) {
        this._idAlumno = _idAlumno;
    }

    public String getIdAdministrativo() {
        return _idAdministrativo;
    }

    public void setIdAdministrativo(String _idAdministrativo) {
        this._idAdministrativo = _idAdministrativo;
    }

    public String getIdRol() {
        return _idRol;
    }

    public void setIdRol(String _idRol) {
        this._idRol = _idRol;
    }

    public String getIdFamilia() {
        return _idFamilia;
    }

    public void setIdFamilia(String _idFamilia) {
        this._idFamilia = _idFamilia;
    }

    public String getIdEncargadoCel() {
        return _idEncargadoCel;
    }

    public void setIdEncargadoCel(String _idEncargadoCel) {
        this._idEncargadoCel = _idEncargadoCel;
    }
    
    
}
