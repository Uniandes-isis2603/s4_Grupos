/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.grupos.entities;

import java.io.Serializable;

/**
 *
 * @author estudiante
 */
public class CategoriaEntity extends BaseEntity implements Serializable {
    
     private String nombre;
    
    private String descripcion;
    
    
    
    public String getNombre() {
        
        return nombre;
    }

    public void setNombre(String pNombre) {
        nombre = pNombre;
    }

    public String getDescripcion() {
        return descripcion;
    }
    
     public void setDescripcion(String pDescripcion) {
        descripcion = pDescripcion;
    }

    
}
