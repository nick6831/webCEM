/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import java.io.StringWriter;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonWriter;

/**
 *
 * @author portafolio
 */
public class Usuario {
        public int IdUsuario;
        public String NomUsuario;
        public String Password;
        public int IdAlumno;
        public int IdAdministrativo;
        public int IdRol;
        public int IdFamilia;
        public int IdEncargadoCel;

    public Usuario(JsonObject usuarioObject){
        this.IdUsuario = usuarioObject.getInt("IdUsuario");
        this.NomUsuario = usuarioObject.getString("NomUsuario");
        this.Password = usuarioObject.getString("Password");
        this.IdAlumno = usuarioObject.getInt("IdAlumno");
        this.IdAdministrativo = 0;
        this.IdRol = 0;
        this.IdFamilia = 0;
        this.IdEncargadoCel = 0;
    }
        
    public Usuario() {
        this.Init();
    }
    
    private void Init() {
        this.IdUsuario = 0;
        this.NomUsuario = "";
        this.Password = "";
        this.IdAlumno = 0;
        this.IdAdministrativo = 0;
        this.IdRol = 0;
        this.IdFamilia = 0;
        this.IdEncargadoCel = 0;
    }    
    
    public StringWriter Json(){
        JsonObject us = Json.createObjectBuilder().
                    add("IdUsuario", this.IdUsuario).
                    add("NomUsuario", this.NomUsuario).
                    add("Password", this.Password).
                    add("IdAlumno", this.IdAlumno).
                    add("IdAdministrativo", this.IdAdministrativo).
                    add("IdRol", this.IdRol).
                    add("IdFamilia", this.IdFamilia).
                    add("IdEncargadoCel", this.IdEncargadoCel).build();
            
        StringWriter string = new StringWriter();
        JsonWriter writer = Json.createWriter(string);
        writer.writeObject(us);
        writer.close();
        
        return string;
    }


}
