package com.hbt.semillero.servicios.ejb;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.hbt.semillero.dto.LineaDTO;
import com.hbt.semillero.dto.MarcaDTO;
import com.hbt.semillero.dto.PersonaDTO;
import com.hbt.semillero.dto.ResultadoDTO;
import com.hbt.semillero.dto.VehiculoDTO;
import com.hbt.semillero.entidades.Comprador;
import com.hbt.semillero.entidades.Linea;
import com.hbt.semillero.entidades.Marca;
import com.hbt.semillero.entidades.Persona;
import com.hbt.semillero.entidades.Vehiculo;
import com.hbt.semillero.entidades.Vendedor;
import com.hbt.semillero.servicios.interfaces.IConsultasEjbLocal;

/**
 * @author Duvis Alejandro Gómez Neira
 * EJB de consultas
 */
@Stateless
public class ConsultasEJB implements IConsultasEjbLocal {

	@PersistenceContext
	private EntityManager em;

	/**
	 * {@link com.hbt.semillero.servicios.interfaces.IConsultasEjbLocal#consultarMarcasExistentes()}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<MarcaDTO> consultarMarcasExistentes() {
		List<Marca> marcas = em.createQuery("Select ma from Marca ma").getResultList();
		List<MarcaDTO> marcasRetorno = new ArrayList<>();
		for (Marca marca : marcas) {
			MarcaDTO marcaDto = construirMarcaDTO(marca);
			marcasRetorno.add(marcaDto);
		}
		return marcasRetorno;
	}

	/**
	 * {@link com.hbt.semillero.servicios.interfaces.IConsultasEjbLocal#consultarLineasPorMarca(Long)}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<LineaDTO> consultarLineasPorMarca(Long idMarca) {
		List<Linea> lineas = em
				.createQuery("Select ln from Linea ln JOIN FETCH ln.marca where ln.marca.idMarca=:idMarca ")
				.setParameter("idMarca", idMarca).getResultList();
		List<LineaDTO> lineasRetorno = new ArrayList<>();
		for (Linea linea : lineas) {
			lineasRetorno.add(construirLineaDTO(linea));
		}
		return lineasRetorno;
	}

	/**
	 * {@link com.hbt.semillero.servicios.interfaces.IConsultasEjbLocal#consultarPersonas(String, String)}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<PersonaDTO> consultarPersonas(String tipoID, String numID) {
		StringBuilder consulta = new StringBuilder("Select per FROM Persona per WHERE 1=1 ");
		Map<String, Object> parametros = new HashMap<>();
		if (tipoID != null) {
			consulta.append(" and per.tipoIdentificacion =:tipoID");
			parametros.put("tipoID", tipoID);
		}
		if (numID != null) {
			consulta.append(" and per.numeroIdentificacion =:numID");
			parametros.put("numID", numID);
		}
		Query query = em.createQuery(consulta.toString());

		for (Entry<String, Object> entry : parametros.entrySet()) {
			query.setParameter(entry.getKey(), entry.getValue());
		}
		List<Persona> personas = query.getResultList();
		List<PersonaDTO> personasRetorno = new ArrayList<>();
		for (Persona persona : personas) {
			PersonaDTO personaDTO = new PersonaDTO();
			personaDTO.setNombres(persona.getNombres());
			personaDTO.setApellidos(persona.getApellidos());
			personaDTO.setNumeroIdentificacion(persona.getNumeroIdentificacion());
			personaDTO.setTipoIdentificacion(persona.getTipoIdentificacion());
			personaDTO.setNumeroTelefonico(persona.getNumeroTelefonico());
			personaDTO.setEdad(persona.getEdad());

			personasRetorno.add(personaDTO);
		}

		return personasRetorno;
	}

	/**
	 * {@link com.hbt.semillero.servicios.interfaces.IConsultasEjbLocal#crearPersona(PersonaDTO)}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public ResultadoDTO crearPersona(PersonaDTO personaDTO) {
		try {
			Persona persona = asignarAtributosBasicosPersona(personaDTO);
			em.persist(persona);
			if (personaDTO.isComprador()) {
				Comprador comprador = new Comprador();
				comprador.setPersona(persona);
				comprador.setFechaAfiliacion(Calendar.getInstance());
				em.persist(comprador);
			}
			if (personaDTO.isVendedor()) {
				Vendedor vendedor = new Vendedor();
				vendedor.setFechaIngreso(Calendar.getInstance());
				vendedor.setPersona(persona);
				em.persist(vendedor);
			}
		} catch (Exception e) {
			return new ResultadoDTO(false, e.getMessage());
		}

		return new ResultadoDTO(true, "Creado de forma exitosa");
	}

	/**
	 * Asigna los atributos básicos de la persona
	 * 
	 * @param persona
	 * @param personaDTO
	 */
	private Persona asignarAtributosBasicosPersona(PersonaDTO personaDTO) {
		Persona persona = new Persona();
		persona.setNombres(personaDTO.getNombres());
		persona.setApellidos(personaDTO.getApellidos());
		persona.setNumeroIdentificacion(personaDTO.getNumeroIdentificacion());
		persona.setTipoIdentificacion(personaDTO.getTipoIdentificacion());
		persona.setNumeroTelefonico(personaDTO.getNumeroTelefonico());
		persona.setEdad(personaDTO.getEdad());
		return persona;
	}

	/**
	 * Asigna los atributos básicos del vehículo
	 * @param vehiculo
	 * @return
	 */
	private Vehiculo asignarAtributosBasicosVehiculo(VehiculoDTO vehiculoDTO) {
		Vehiculo vehiculo = new Vehiculo();
		vehiculo.setModelo(vehiculoDTO.getModelo());
		vehiculo.setPlaca(vehiculoDTO.getPlaca());
		List<Linea> lineas = em.createQuery("Select ln from Linea ln where ln.idLinea=:idLinea ")
				.setParameter("idLinea", vehiculoDTO.getLinea().getIdLinea()).getResultList();
		if (lineas.size() == 1) {
			vehiculo.setLinea(lineas.get(0));
		}

		return vehiculo;
	}

	/**
	 * Construye un DTO de Marca
	 * 
	 * @param marca
	 * @return
	 */
	private MarcaDTO construirMarcaDTO(Marca marca) {
		MarcaDTO marcaDto = new MarcaDTO();
		marcaDto.setIdMarca(marca.getIdMarca());
		marcaDto.setNombre(marca.getNombre());
		return marcaDto;
	}

	/**
	 * Construye un DTO de Linea
	 * @param linea
	 * @return
	 */
	private LineaDTO construirLineaDTO(Linea linea) {
		LineaDTO lineaDTO = new LineaDTO();
		lineaDTO.setIdLinea(linea.getIdLinea());
		lineaDTO.setNombre(linea.getNombre());
		lineaDTO.setCilindraje(linea.getCilindraje());
		lineaDTO.setMarca(construirMarcaDTO(linea.getMarca()));
		return lineaDTO;
	}

	/**
	 * Construye un DTO de vehiculo
	 * @param vehiculo
	 * @return
	 */
	private VehiculoDTO construirvehiculoDTO(Vehiculo vehiculo) {
		VehiculoDTO vehiculoDTO = new VehiculoDTO();
		vehiculoDTO.setIdVehiculo(vehiculo.getIdVehiculo());
		vehiculoDTO.setModelo(vehiculo.getModelo());
		vehiculoDTO.setPlaca(vehiculo.getPlaca());
		vehiculoDTO.setLinea(construirLineaDTO(vehiculo.getLinea()));
		return vehiculoDTO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hbt.semillero.servicios.interfaces.IConsultasEjbLocal#crearVehiculo(com.
	 * hbt.semillero.dto.VehiculoDTO)
	 */
	@Override
	public ResultadoDTO crearVehiculo(VehiculoDTO vehiculoDTO) {
		try {

			Vehiculo vehiculo = asignarAtributosBasicosVehiculo(vehiculoDTO);
			em.persist(vehiculo);
		} catch (Exception e) {
			return new ResultadoDTO(false, e.getMessage());
		}

		return new ResultadoDTO(true, "Vehiculo Creado de forma exitosa");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hbt.semillero.servicios.interfaces.IConsultasEjbLocal#consultarVehiculos()
	 */
	@Override
	public List<VehiculoDTO> consultarVehiculos(long idLinea, long idMarca) {
		List<Vehiculo> vehiculos = new ArrayList<Vehiculo>();
		vehiculos = em.createQuery("Select ve from Vehiculo ve").getResultList();
		if (idMarca > 0) {
			vehiculos = em.createQuery(
					"Select ve from Vehiculo ve JOIN FETCH ve.linea li "
							+ "JOIN FETCH li.marca where li.marca.idMarca=:idMarca ").
					setParameter("idMarca", idMarca).getResultList();
		}
		if (idLinea > 0) {
			vehiculos = em.createQuery("Select ve from Vehiculo ve JOIN FETCH ve.linea where ve.linea.idLinea=:idLinea")
					.setParameter("idLinea", idLinea).getResultList();
		}

		List<VehiculoDTO> vehiculosRetorno = new ArrayList<>();
		for (Vehiculo vehiculo : vehiculos) {
			vehiculosRetorno.add(construirvehiculoDTO(vehiculo));
		}
		return vehiculosRetorno;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hbt.semillero.servicios.interfaces.IConsultasEjbLocal#editarVehiculo(com.
	 * hbt.semillero.dto.VehiculoDTO)
	 */
	@Override
	public ResultadoDTO editarVehiculo(VehiculoDTO vehiculoDTO) {
		try {

			Vehiculo vehiculo = em.find(Vehiculo.class, vehiculoDTO.getIdVehiculo());
			vehiculo.setModelo(vehiculoDTO.getModelo());
			vehiculo.setPlaca(vehiculoDTO.getPlaca());
			List<Linea> lineas = em.createQuery("Select ln from Linea ln where ln.idLinea=:idLinea ")
					.setParameter("idLinea", vehiculoDTO.getLinea().getIdLinea()).getResultList();
			if (lineas.size() == 1) {
				vehiculo.setLinea(lineas.get(0));
			} else {
				vehiculo.setLinea(null);
			}
			em.merge(vehiculo);
		} catch (Exception e) {
			return new ResultadoDTO(false, e.getMessage());
		}
		return new ResultadoDTO(true, "Vehiculo Editado de forma exitosa");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hbt.semillero.servicios.interfaces.IConsultasEjbLocal#eliminarVehiculo(
	 * java.lang.Long)
	 */
	@Override
	public ResultadoDTO eliminarVehiculo(Long idVehiculo) {
		try {
			em.remove(em.find(Vehiculo.class, idVehiculo));
			return new ResultadoDTO(true, "Vehiculo Eliminado de froma exitosa");
		} catch (Exception e) {
			return new ResultadoDTO(false, e.getMessage());
		}
	}
}