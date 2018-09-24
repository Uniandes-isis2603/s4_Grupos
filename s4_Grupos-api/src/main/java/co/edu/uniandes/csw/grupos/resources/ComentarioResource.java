/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.grupos.resources;

import co.edu.uniandes.csw.grupos.dtos.CiudadanoDTO;
import co.edu.uniandes.csw.grupos.dtos.ComentarioDTO;
import co.edu.uniandes.csw.grupos.ejb.CiudadanoLogic;
import co.edu.uniandes.csw.grupos.ejb.ComentarioLogic;
import co.edu.uniandes.csw.grupos.entities.ComentarioEntity;
import co.edu.uniandes.csw.grupos.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
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
import javax.ws.rs.WebApplicationException;


/**
 * Clase que representa un ComentarioResource
 * @author Daniel Augusto Ramirez Dueñas
 */
@Path("comentarios")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class ComentarioResource 
{
    private static final String NOEXISTE1= "/comentarios/";
    private static final String NOEXISTE2= " no existe";
    private static final Logger LOGGER = Logger.getLogger(ComentarioResource.class.getName());
    
    @Inject
    ComentarioLogic comentarioLogic;
    
    @Inject
    CiudadanoResource ciudadanoResource;
    
    /**
     * Crea un comentario
     * @param comentario
     * @return un comentario
     * @throws co.edu.uniandes.csw.grupos.exceptions.BusinessLogicException
     */
    @POST
    public ComentarioDTO  createComentario (ComentarioDTO comentario) throws BusinessLogicException 
    {
        LOGGER.log(Level.INFO, "ComentarioResource createComentario: input: {0}");
        ComentarioDTO nuevoComentarioDTO = new ComentarioDTO(comentarioLogic.createComentario(comentario.toEntity()));
        LOGGER.log(Level.INFO, "ComentarioResource createComentario: output: {0}", nuevoComentarioDTO.toString());
        return nuevoComentarioDTO;
    }
    
    /**
     * Consulta todos los comentario de un usuario
     * @return los comentarios de un usuario
     */
    @GET
    public List<ComentarioDTO> consultarComentarios()
    {
        LOGGER.log(Level.INFO, "ComentarioResource getComentarios: input: {0}");
        List<ComentarioDTO> listaDTOs = listEntityToDTO(comentarioLogic.getComentarios());
        LOGGER.log(Level.INFO, "EditorialGruposResource getGrupos: output: {0}");
        return listaDTOs;
    }
    
    /**
     * Consulta un comentario de un usario dando el identificador del comentario
     * @param id
     * @return comentario de un usuario 
     */
    @GET
    @Path("{id: \\d+}")
    public ComentarioDTO consultarUnComentario(@PathParam("id") Long id)
    {
        LOGGER.log(Level.INFO, "ComentarioResource getComentario: input: {0}", id);
        ComentarioEntity entity = comentarioLogic.getComentario(id);
        if (entity == null) {
            throw new WebApplicationException( NOEXISTE1 + id + NOEXISTE2, 404);
        }
        ComentarioDTO comentarioDTO = new ComentarioDTO(entity);
        LOGGER.log(Level.INFO, "ComentarioResource getComentario: output: {0}");
        return comentarioDTO;
    }
    
    /**
     * Modifica un comentario con el texto que le entra por paramtro
     * @param id
     * @param comentario
     * @return el comentario modificado
     * @throws BusinessLogicException
     */
    @PUT
    @Path("{id:\\d+}")
    public ComentarioDTO modificarComentario(@PathParam("id") Long id, ComentarioDTO comentario) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "ComentarioResource updateComentario: input: id: {0}, comentario:{1}", new Object[]{id});
        List<CiudadanoDTO> listaDTOs = ciudadanoResource.getCiudadanos();
        boolean termino = false;
        if (id.equals(comentario.getId())) {
            throw new BusinessLogicException("Los ids del Comentario no coinciden.");
        }
        ComentarioEntity entity = comentarioLogic.getComentario(id);
        if (entity == null) 
        {
            throw new WebApplicationException(NOEXISTE1 + id + NOEXISTE2, 404);

        }
        
        for (int i = 0; i < listaDTOs.size() && !termino; i++)
        {
            if (comentario.getNombre().compareTo(listaDTOs.get(i).getNombre()) == 0)
            {
                 ComentarioDTO comentarioDTO = new ComentarioDTO(comentarioLogic.updateComentario(id,comentario.toEntity()));
                 comentarioLogic.deleteComentario(id);
                 LOGGER.log(Level.INFO, "ComentarioResource updateComentario: output:{0}");
                 termino = true;
                 return comentarioDTO;
            }
            else
            {
                throw new WebApplicationException("El ciudadano con nombre: "  +  comentario.getNombre() + " no existe. Por favor verifique el nombre.");
            }
        }
        throw new WebApplicationException("El comentario con identificador: " + id + " no se actualizo con éxito.");
    }
    
    /**
     * Elimina un comentario
     * @param id
     * @throws co.edu.uniandes.csw.grupos.exceptions.BusinessLogicException
     */
    @DELETE
    @Path("{id: \\d+}")
    public void eliminarComentario(@PathParam("id")Long id) throws BusinessLogicException
    {
        ComentarioEntity entity = comentarioLogic.getComentario(id);
        if (entity == null) {
            throw new WebApplicationException(NOEXISTE1 + id + NOEXISTE2 , 404);
        }
        comentarioLogic.deleteComentario(id);
    }
    
    @DELETE
    public void eliminarComentarios() throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "ComentarioResource getComentarios: input: {0}");
        List<ComentarioDTO> listaDTOs = listEntityToDTO(comentarioLogic.getComentarios());
        for (int i = 0; i < listaDTOs.size(); i++)
        {
            comentarioLogic.deleteComentario(listaDTOs.get(i).getId());
        }
        LOGGER.log(Level.INFO, "ComentariosResource getComentarios: output: {0}");
    }

    private List<ComentarioDTO> listEntityToDTO(List<ComentarioEntity> entityList) {
        List<ComentarioDTO> list = new ArrayList<>();
        for (ComentarioEntity entity : entityList) {
            list.add(new ComentarioDTO(entity));
        }
        return list;       
    }
}
