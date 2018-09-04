/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.grupos.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author estudiante
 */
@Entity
class PatrocinioEntity extends BaseEntity implements Serializable 
{
    /**
     * el identificador unico
     */
    @Id
    private Long id;
    
    /**
     * nombre del patrocinador
     */
    private String nombre;
    
    /**
     * valor del patrocinio
     */
    private Integer valor;
    
    /**
     * eventos patrocinados
     */
    @PodamExclude
    @ManyToMany(mappedBy = "eventos", fetch = javax.persistence.FetchType.LAZY)
    private List<EventoEntity> eventos = new ArrayList<EventoEntity>();

    /**
     * retorna el id del patrocinio 
     * @return Long id
     */
    public Long getId() {
        return id;
    }

    /**
     * modifica el id del patrocinio
     * @param id el nuevo id
     */
    public void setId(Long id) {
        this.id = id;
    }
    
    /**
     * retorna el nombre del patrocinador
     * @return el nombre del patrocinador
     */
    public String getNombre() {
        return nombre;
    }
    
    /**
     * modifica el nombre del patrocinador
     * @param nombre el nuevo nombre del patrocinador
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * retorna el valor del patrocinio      
     * @return valor patrocinio
     */
    public Integer getValor() {
        return valor;
    }
    
    /**
     * modifica el valor del patrocinio
     * @param valor el nuevo valor
     */
    public void setValor(Integer valor) {
        this.valor = valor;
    }

    /**
     * retorna la lista de eventos asociados al patrocinio
     * @return lista de eventos
     */
    public List<EventoEntity> getEventos() {
        return eventos;
    }

    /**
     * modifica la lista de eventos del patrocinio
     * @param eventos lista de EventosEntity nueva
     */
    
    public void setEventos(List<EventoEntity> eventos) {
        this.eventos = eventos;
    }
    
    
}