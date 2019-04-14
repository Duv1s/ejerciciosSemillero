package com.hbt.semillero.servicios;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.hbt.semillero.dto.LineaDTO;
import com.hbt.semillero.dto.MarcaDTO;
import com.hbt.semillero.dto.PersonaDTO;
import com.hbt.semillero.dto.ResultadoDTO;
import com.hbt.semillero.dto.VehiculoDTO;
import com.hbt.semillero.servicios.interfaces.IConsultasEjbLocal;

/**
 * Componente que expone servicios rest.
 * 
 * @author Johnny Soto
 *
 */
@Path("/ConsultasRest")
public class ConsultasRest {

	@EJB
	private IConsultasEjbLocal consultaEJB;

	/**
	 * Consulta las marcas existentes en el sistema.
	 * 
	 * @return
	 */
	@GET
	@Path("/consultarMarcas")
	@Produces(MediaType.APPLICATION_JSON)
	public List<MarcaDTO> consultarMarcas() {
		return consultaEJB.consultarMarcasExistentes();
	}

	/**
	 * Consulta las marcas existentes en el sistema.
	 * 
	 * @return
	 */
	@GET
	@Path("/consultarLineasPorMarca")
	@Produces(MediaType.APPLICATION_JSON)
	public List<LineaDTO> consultarLineasPorMarca(@QueryParam("idMarca") Long idMarca) {
		return consultaEJB.consultarLineasPorMarca(idMarca);
	}

	/**
	 * Consulta las personas que cumplan con los criterios ingresados
	 * 
	 * @param tipoID
	 * @param numID
	 * @return
	 */
	@GET
	@Path("/consultarPersonas")
	@Produces(MediaType.APPLICATION_JSON)
	public List<PersonaDTO> consultarPersonas(@QueryParam("tipoID") String tipoID, @QueryParam("numID") String numID) {
		return consultaEJB.consultarPersonas(tipoID, numID);
	}

	/**
	 * Consulta los vehiculos que cumplan con los criterios ingresados
	 * 
	 * @param idLinea
	 * @param idMarca
	 * @return
	 */
	@GET
	@Path("/consultarVehiculos")
	@Produces(MediaType.APPLICATION_JSON)
	public List<VehiculoDTO> consultarVehiculos(@QueryParam("idLinea") long idLinea,
			@QueryParam("idMarca") long idMarca) {
		return consultaEJB.consultarVehiculos(idLinea, idMarca);
	}

	/**
	 * Permite editar un vehiculo
	 * 
	 * @param vehiculoDTO
	 * @return
	 */
	@POST
	@Path("/editarVehiculo")
	@Produces(MediaType.APPLICATION_JSON)
	public ResultadoDTO editarVehiculo(VehiculoDTO vehiculoDTO) {
		return consultaEJB.editarVehiculo(vehiculoDTO);
	}

	/**
	 * Permite eliminar un vehiculo por Id.
	 * @param idVehiculo
	 * @return
	 */
	@GET
	@Path("/eliminarVehiculo")
	@Produces(MediaType.APPLICATION_JSON)
	public ResultadoDTO eliminarVehiculo(@QueryParam("idVehiculo") Long idVehiculo) {
		return consultaEJB.eliminarVehiculo(idVehiculo);
	}

	/**
	 * Crea las personas en sus diferentes roles dentro del sistema.
	 * 
	 * @param persona
	 * @return
	 */
	@POST
	@Path("/crearPersona")
	@Produces(MediaType.APPLICATION_JSON)
	public ResultadoDTO crearPersona(PersonaDTO persona) {
		return consultaEJB.crearPersona(persona);
	}

	/**
	 * Crea los vehiculos  dentro del sistema.
	 * @param vehiculo
	 * @return
	 */
	@POST
	@Path("/crearVehiculo")
	@Produces(MediaType.APPLICATION_JSON)
	public ResultadoDTO crearVehiculo(VehiculoDTO vehiculo) {
		return consultaEJB.crearVehiculo(vehiculo);
	}
}
