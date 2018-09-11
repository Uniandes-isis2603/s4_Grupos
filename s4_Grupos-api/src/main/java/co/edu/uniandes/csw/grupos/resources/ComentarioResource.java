/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.grupos.resources;

import co.edu.uniandes.csw.grupos.dtos.ComentarioDTO;
import co.edu.uniandes.csw.grupos.ejb.ComentarioLogic;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;


/**
 *
 * @author Daniel Augusto Ramirez Dueñas
 */
@Path("comentarios")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class ComentarioResource 
{
    private static final Logger LOGGER = Logger.getLogger(ComentarioResource.class.getName());
    
    @Inject
    ComentarioLogic comentarioLogic;
    
    /**
     * Crea un comentario
     * @param comentario
     * @return un comentario
     */
    @POST
    public ComentarioDTO  createComentario (ComentarioDTO comentario) 
    {
        //throws Exception
        
        return comentario;
    }
    
    /**
     * Consulta los comentario de un usuario
     * @param comentario
     * @return los comentarios de un usuario
     */
    @GET
    public ComentarioDTO consultarComentario()
    {
        return new ComentarioDTO();
    }
    
    @GET
    @Path("{comentariosId: \\d+}")
    public ComentarioDTO consultarUnCiudadano(@PathParam("comentariosId") Long comentariosId)
    {
        return new ComentarioDTO();
    }
    
    /**
     * Modifica un comentario con el texto que le entra por paramtro
     * @param texto
     * @return el comentario modificado
     */
    @PUT
    @Path("{id:\\d+}")
    public ComentarioDTO modificarComentario(@PathParam("id") Long id, ComentarioDTO comentario)
    {
        return new ComentarioDTO();
    }
    
    /**
     * Elimina un comentario
     */
    @DELETE
    @Path("{comentariosId: \\d+}")
    public void eliminarComentario(@PathParam("comentariosId")Long pId)
    {
        
    }
}
