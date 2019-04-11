package com.hbt.semillero.servicios.ejb;

import java.util.Calendar;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.hbt.semillero.dto.PersonaDTO;
import com.hbt.semillero.entidades.Comprador;
import com.hbt.semillero.entidades.Linea;
import com.hbt.semillero.entidades.Marca;
import com.hbt.semillero.entidades.Persona;
import com.hbt.semillero.entidades.Vendedor;
import com.hbt.semillero.servicios.interfaces.IConsultasBeanLocal;

@Stateless
/**
 * Clase de tipo Bean creada para realizar las consultas a la base de datos y
 * que implementa la interface IConsultasBeanLocal
 * 
 * @author Duvis Alejandro Gómez Neira
 *
 */
public class ConsultasBean implements IConsultasBeanLocal {

	/**
	 * EntityManager se utiliza para crear y eliminar instancias persistentes de una
	 * entidad.
	 */
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Método que permite obtener la lista de todas las marcas.
	 */
	public List<Marca> consultarMarcas() {
		return entityManager.createQuery("select ma FROM Marca ma").getResultList();
	}

	/**
	 * Método que permite obtener la linea de un vehíuclo a partir de la marca
	 * 
	 * @param idMarca
	 */
	public List<Linea> consultarLineas(Long idMarca) {
		return entityManager.createQuery("Select ln from Linea ln JOIN FETCH ln.marca where ln.marca.idMarca=:idMarca")
				.setParameter("idMarca", idMarca).getResultList();
	}

	/**
	 * Método que permite editar la información almacenada de una persona.
	 */
	public void editarPersona(PersonaDTO personaDTO) {
		Persona persona = entityManager.find(Persona.class, personaDTO.getIdPersona());
		persona.setNombres(personaDTO.getNombres());
		persona.setApellidos(personaDTO.getApellidos());
		persona.setNumeroIdentificacion(personaDTO.getNumeroIdentificacion());
		persona.setTipoIdentificacion(personaDTO.getTipoIdentificacion());
		persona.setNumeroTelefonico(personaDTO.getNumeroTelefonico());
		persona.setEdad(personaDTO.getEdad());
		entityManager.merge(persona);

		// si persona es comprador lo busca si existe, y si no existe lo crea, si
		// persona no es comprador lo busca y si lo encuentra lo elimina.
		Comprador comprador = new Comprador();
		if (personaDTO.isComprador()) {
			System.out.println("Es comprador");
			try {
				comprador = entityManager.find(Comprador.class, personaDTO.getIdPersona());
				System.out.println("Lo encontre en la tabla compradores y lo dejé así" + comprador.toString());
			} catch (Exception e) {
				System.out.println("No lo encontré en la tabla compradores");
				comprador = new Comprador();
				comprador.setFechaAfiliacion(Calendar.getInstance());
				comprador.setPersona(persona);
				entityManager.persist(comprador);
				System.out.println("Lo cree y lo guarde");
			}
		} else {
			System.out.println("No es un comprador");
			try {
				System.out.println("Reviso que no exista en la tabla compradores");
				List<Comprador> compradores = entityManager
						.createQuery(
								"select co FROM Comprador co where co.persona.idPersona = " + personaDTO.getIdPersona())
						.getResultList();
				for (Comprador comprador2 : compradores) {

					entityManager.remove(comprador2);
					System.out.println("Lo encontre en la tabla compradores y lo eliminé");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		Vendedor vendedor = new Vendedor();
		if (personaDTO.isVendedor()) {
			System.out.println("Es un vendedor");
			try {
				vendedor = entityManager.find(Vendedor.class, personaDTO.getIdPersona());
				System.out.println("Lo encontre en la tabla vendedores y lo dejé así" + vendedor.getIdVendedor());
			} catch (Exception e) {
				System.out.println("No lo encontré en la tabla vendedores");
				vendedor = new Vendedor();
				vendedor.setFechaIngreso(Calendar.getInstance());
				vendedor.setPersona(persona);
				entityManager.persist(vendedor);
				System.out.println("Lo cree y lo guarde el vendedor");
			}
		} else {
			System.out.println("No es un vendedor");
			try {
				System.out.println("Reviso que no exista en la tabla vendedores");
				List<Vendedor> vendedores = entityManager
						.createQuery(
								"select ve FROM Vendedor ve where ve.persona.idPersona = " + personaDTO.getIdPersona())
						.getResultList();
				for (Vendedor vendedor2 : vendedores) {
					entityManager.remove(vendedor2);
					System.out.println("Lo encontre en la tabla vendedores y lo eliminé");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * Método que permite almacenar una persona en la base de datos.
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void crearPersona(PersonaDTO personaDTO) {
		Persona persona = new Persona();
		persona.setNombres(personaDTO.getNombres());
		persona.setApellidos(personaDTO.getApellidos());
		persona.setNumeroIdentificacion(personaDTO.getNumeroIdentificacion());
		persona.setTipoIdentificacion(personaDTO.getTipoIdentificacion());
		persona.setNumeroTelefonico(personaDTO.getNumeroTelefonico());
		persona.setEdad(personaDTO.getEdad());

		entityManager.persist(persona);

		if (personaDTO.isComprador()) {
			Comprador comprador = new Comprador();
			comprador.setFechaAfiliacion(Calendar.getInstance());
			comprador.setPersona(persona);
			entityManager.persist(comprador);
		}

		if (personaDTO.isVendedor()) {
			Vendedor vendedor = new Vendedor();
			vendedor.setFechaIngreso(Calendar.getInstance());
			vendedor.setPersona(persona);
			entityManager.persist(vendedor);
		}
	}

	/**
	 * Método que permite consultar el listado de personas almacenadas teniendo en
	 * cuenta el tipo de identificación y numero de identificación.
	 * 
	 * @param tipoIdentificacion   Ejemplo: CC, TI, RC
	 * @param numeroIdentificacion numero de identificación de la persona
	 */
	@Override
	public List<Persona> consultarPersonas(String tipoIdentificacion, String numeroIdentificacion) {
		return entityManager.createQuery(
				"Select per from Persona per where per.tipoIdentificacion=:tipoIdentificacion AND per.numeroIdentificacion =: numeroIdentificacion")
				.setParameter("tipoIdentificacion", tipoIdentificacion)
				.setParameter("numeroIdentificacion", numeroIdentificacion).getResultList();
	}
}
