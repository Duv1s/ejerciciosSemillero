package com.hbt.semillero.servicios;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.hbt.semillero.dto.LineaDTO;
import com.hbt.semillero.dto.MarcaDTO;
import com.hbt.semillero.dto.PersonaDTO;
import com.hbt.semillero.dto.ResultadoDTO;
import com.hbt.semillero.entidades.Linea;
import com.hbt.semillero.entidades.Marca;
import com.hbt.semillero.entidades.Persona;
import com.hbt.semillero.servicios.interfaces.IConsultasBeanLocal;

/**
 * Clase que permite exponer los servicios de tipo rest al contenedor de aplicaciones.
 * @author Duvis Alejandro Gómez Neira
 *
 */
@Path("/ServiciosRest")
public class ServiciosRest {
	
	/**
	 * Interface utilizada para el encapsulamiento de datos.
	 */
	@EJB
	private IConsultasBeanLocal consultasBean;
	
	
	/**
	 * Método que permite obtener todas las marcas almacenadas
	 * Expone servicio rest en el path /consultarMarcas
	 * @return Listado de las marcas almacendas.
	 */
	@GET
	@Path("/consultarMarcas")
	@Produces(MediaType.APPLICATION_JSON)
	public List<MarcaDTO> consultarMarcas(){
		List<Marca> marcas = consultasBean.consultarMarcas();
		List<MarcaDTO> retorno = new ArrayList<MarcaDTO>();
		for (Marca marca : marcas) {
			MarcaDTO marcaDTO = construirMarcaDTO(marca);
			retorno.add(marcaDTO);
		}
		return retorno;
		
	}
	
	/**
	 * Método que permite obtener todas las lineas almacenadas teniendo en cuenta una marca
	 * 
	 * @param idMarca Identificador de la marca de la cual se quiere consultar las lineas.
	 * @return Listado de todas las lineas de una determinada marca.
	 */
	@GET
	@Path("/consultarLineas")
	@Produces(MediaType.APPLICATION_JSON)
	public List<LineaDTO> consultarLineas(@QueryParam(value = "idMarca")Long idMarca) {
		List<Linea> lineas = consultasBean.consultarLineas(idMarca);
		List<LineaDTO> retorno = new ArrayList<LineaDTO>();
		for (Linea linea : lineas) {
			LineaDTO lineaDTO = new LineaDTO();
			lineaDTO.setIdLinea(linea.getIdLinea());
			lineaDTO.setNombre(linea.getNombre());
			lineaDTO.setCilindraje(linea.getCilindraje());
			MarcaDTO marcaDTO = construirMarcaDTO(linea.getMarca());
			lineaDTO.setMarca(marcaDTO);
			retorno.add(lineaDTO);
		}
		return retorno;
	}
	
	/**
	 * Método que permite obtener las personas almacenadas filtrándolas por tipo de identificación y número de identificación.
	 * @param tipoIdentificacion Tipo de identificación de la persona
	 * @param numeroIdentificacion Número de indetificación de la persona
	 * @return Lista de personas que coinciden con el tipo y número de identificación
	 */
	@GET
	@Path("/consultarPersonas")
	@Produces(MediaType.APPLICATION_JSON)
	public List<PersonaDTO> consultarPersonas(@QueryParam(value = "tipoIdentificacion")String tipoIdentificacion, @QueryParam(value="numeroIdentificacion") String numeroIdentificacion) {
		List<Persona> personas = consultasBean.consultarPersonas(tipoIdentificacion, numeroIdentificacion);
		List<PersonaDTO> retorno = new ArrayList<PersonaDTO>();
		for (Persona persona : personas) {
			PersonaDTO personaDTO = new PersonaDTO();
			personaDTO.setNumeroIdentificacion(persona.getNumeroIdentificacion());
			personaDTO.setTipoIdentificacion(persona.getTipoIdentificacion());
			personaDTO.setNumeroTelefonico(persona.getNumeroTelefonico());
			personaDTO.setNombres(persona.getNombres());
			personaDTO.setApellidos(persona.getApellidos());
			personaDTO.setEdad(persona.getEdad());
			retorno.add(personaDTO);
		}
		return retorno;
	}
	
	
	/**
	 * Método que permite exponer un servicio para almacenar personas
	 * Expone servicio rest en el path /crearPersona
	 * @param personaDTO Objeto de tipo Persona que se quiere almacenar
	 * @return Objeto de tipo resultado con el resultado de la transaccion satisfactorio o un mensaje de error.
	 */
	@POST
	@Path("/crearPersona")
	@Produces(MediaType.APPLICATION_JSON)
	public ResultadoDTO crearPersona(PersonaDTO personaDTO){
		ResultadoDTO retorno = new ResultadoDTO();
		retorno.setExitoso(true);
		try {
			consultasBean.crearPersona(personaDTO);
		} catch (Exception e) {
			retorno.setExitoso(false);
			retorno.setMensajeError("No se pudo Ingresar la persona");
		}
		return retorno;
	}
	
	/**
	 * Método que permite exponer un servicio para modificar personas almacenadas.
	 * Expone servicio rest en el path /crearPersona
	 * @param personaDTO Objeto de tipo Persona que se quiere actualizar
	 * @return Objeto de tipo resultado con el resultado de la transaccion satisfactorio o un mensaje de error.
	 */
	@POST
	@Path("/editarPersona")
	@Produces(MediaType.APPLICATION_JSON)
	public ResultadoDTO editarPersona(PersonaDTO personaDTO){
		ResultadoDTO retorno = new ResultadoDTO();
		retorno.setExitoso(true);
		try {
			consultasBean.editarPersona(personaDTO);
		} catch (Exception e) {
			retorno.setExitoso(false);
			retorno.setMensajeError("No se pudo Editar la persona");
		}
		return retorno;
	}
	
	/**
	 * Metodo que permite convertir un objeto de tipo Marca en un objeto para la transferencia de datos.
	 * @param marca Marca de la cual se requiere realizar copia para la transferencia de datos.
	 * @return Objeto para la transferencia de datos de una Marca.
	 */
	private MarcaDTO construirMarcaDTO(Marca marca) {
		MarcaDTO marcaDTO = new MarcaDTO();
		marcaDTO.setIdMarca(marca.getIdMarca());
		marcaDTO.setNombre(marca.getNombre());
		return marcaDTO;
	}
}
