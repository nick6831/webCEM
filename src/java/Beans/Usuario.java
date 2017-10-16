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
    public String _correo;
    public String _contrasenia;
    public String _id;
    
    public Usuario(String user, String pass, String id){
        _correo=user;
        _contrasenia=pass;
        _id=id;
    }

    public Usuario() {
    }
    
    

    public String getCorreo() {
        return _correo;
    }

    public void setCorreo(String _correo) {
        this._correo = _correo;
    }

    public String getContrasenia() {
        return _contrasenia;
    }

    public void setContrasenia(String _contrasenia) {
        this._contrasenia = _contrasenia;
    }

    public String getId() {
        return _id;
    }

    public void setId(String _id) {
        this._id = _id;
    }
    
    public String CrearUsuario(String nombre, String apellido)
    {
        String user= nombre.substring(2).concat(".").concat(apellido);
        return user;
    }
      
}
