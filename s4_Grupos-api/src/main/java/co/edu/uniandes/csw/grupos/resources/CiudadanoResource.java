/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.grupos.resources;

import co.edu.uniandes.csw.grupos.dtos.CiudadanoDTO;
import co.edu.uniandes.csw.grupos.ejb.CiudadanoLogic;
import co.edu.uniandes.csw.grupos.entities.CiudadanoEntity;
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
 * Clase que representa un CiudadanoResource
 * @author Daniel Augusto Ramirez Dueñas
 */
@Path("ciudadanos")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class CiudadanoResource 
{    
    private final static String NOEXISTE1= "/ciudadanos/";
    private final static String NOEXISTE2= " no existe";
    private static final Logger LOGGER = Logger.getLogger(CiudadanoResource.class.getName());
    
    @Inject
    CiudadanoLogic ciudadanoLogic;
    /**
     * Crea un nuevo ciudadano
     * @param ciudadano
     * @return ciudadno creado
     * @throws co.edu.uniandes.csw.grupos.exceptions.BusinessLogicException
     */
    @POST
    public CiudadanoDTO createCiudadano(CiudadanoDTO ciudadano) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "CiudadanoResource createCiudadano: input: {0}", ciudadano.toString());
        CiudadanoDTO nuevoCiudadanoDTO = new CiudadanoDTO(ciudadanoLogic.createCiudadano(ciudadano.toEntity()));
        LOGGER.log(Level.INFO, "CiudadanoResource createCiudadano: output: {0}", nuevoCiudadanoDTO.toString());
        return nuevoCiudadanoDTO;
    }
    
    /**
     * Consulta la lista de todos los ciudadanos
     * @return lista de todos los ciudadanos
     */
    @GET
    public List<CiudadanoDTO> getCiudadanos()
    {
        LOGGER.log(Level.INFO, "CiudadanoResource getCiudadanos: input: {0}");
        List<CiudadanoDTO> listaDTOs = listEntityToDTO(ciudadanoLogic.getCiudadanos());
        LOGGER.log(Level.INFO, "EditorialGruposResource getGrupos: output: {0}", listaDTOs.toString());
        return listaDTOs;
    }
    
    /**
     * Consulta un ciudadano con el numero de usuario dado parametro
     * @param id
     * @param user numero de usuario que se desea consultar
     * @return ciudadano con el numero de usuario dado por parametro
     */
    @GET
    @Path("{id :\\d+}")
    public CiudadanoDTO getCiudadano(@PathParam("id") Long id)
    {
        LOGGER.log(Level.INFO, "CiudadanoResource getCiudadano: input: {0}", id);
        CiudadanoEntity entity = ciudadanoLogic.getCiudadano(id);
        if (entity == null) {
            throw new WebApplicationException( NOEXISTE1 + id + NOEXISTE2, 404);
        }
        CiudadanoDTO ciudadanoDTO = new CiudadanoDTO(entity);
        LOGGER.log(Level.INFO, "CiudadanoResource getCiudadano: output: {0}", ciudadanoDTO.toString());
        return ciudadanoDTO;
    }
  
    
    /**
     * Actualiza un ciudadano con sus nuevas caacteristicas
     * @param id
     * @param ciudadano
     * @return un ciudadano actualizado
     * @throws co.edu.uniandes.csw.grupos.exceptions.BusinessLogicException
     */
    @PUT
    @Path("{id :\\d+}")
    public CiudadanoDTO modificarCiudadano(@PathParam("id") Long id, CiudadanoDTO ciudadano ) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "CiudadanoResource updateCiudadano: input: gruposID: {0} , id: {1} , ciudadano:{2}", new Object[]{id, ciudadano.toString()});
        if (id.equals(ciudadano.getId())) {
            throw new BusinessLogicException("Los ids del Ciudadano no coinciden.");
        }
        CiudadanoEntity entity = ciudadanoLogic.getCiudadano(id);
        if (entity == null) 
        {
            throw new WebApplicationException(NOEXISTE1 + id + NOEXISTE2, 404);

        }
        CiudadanoDTO ciudadanoDTO = new CiudadanoDTO(ciudadanoLogic.updateCiudadano(id,ciudadano.toEntity()));
        LOGGER.log(Level.INFO, "CiudadanoResource updateCiudadano: output:{0}", ciudadanoDTO.toString());
        return ciudadanoDTO;
    }
    
    /**
     * Elimina un ciudadano
     * @param id
     */
    @DELETE
    @Path("{id: \\d+}")
    public void eliminarCiudadano(@PathParam("id") Long id) throws BusinessLogicException
    {
        CiudadanoEntity entity = ciudadanoLogic.getCiudadano(id);
        if (entity == null) {
            throw new WebApplicationException(NOEXISTE1 + id + NOEXISTE2 , 404);
        }
        ciudadanoLogic.deleteCiudadano(id);
    }

    private List<CiudadanoDTO> listEntityToDTO(List<CiudadanoEntity> entityList) 
    {
        List<CiudadanoDTO> list = new ArrayList<>();
        for (CiudadanoEntity entity : entityList) {
            list.add(new CiudadanoDTO(entity));
        }
        return list;    
    }
}
