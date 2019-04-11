package com.hbt.semillero.dto;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Clase creada para la transferencia de datos que representa una Persona
 * @author Duvis Alejandro Gómez Neira
 *
 */
public class PersonaDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Identificador de la persona
	 */
	private Long idPersona;

	/**
	 * Numero de identificacion de una persona
	 */
	private String numeroIdentificacion;
	
	/**
	 * Tipo de identificación de una persona
	 * Ejemplo: CC, TI, RC
	 */
	private String tipoIdentificacion;

	/**
	 * Número telefónico de una persona
	 */
	private String numeroTelefonico;

	/**
	 * Atributo donde se almacenan los nombre de una persona
	 */
	private String nombres;

	/**
	 * Atributo donde se almacenan los Apellidos de una persona
	 */
	private String apellidos;

	/**
	 * Edad en años de una persona
	 */
	private Long edad;
	
	/**
	 * Variable booleana para identificar si una persona es vendedor
	 * En caso de que una persona sea vendedor esta variable será true, de lo contrario será false.
	 */
	private boolean vendedor;
	
	/**
	 * Variable booleana para identificar si una persona es comprador
	 * En caso de que una persona sea comprador esta variable será true, de lo contrario será false.
	 */
	private boolean comprador;

	/**
	 * Método que permite obtener el atributo idPersona
	 * @return idPersona
	 */
	public Long getIdPersona() {
		return idPersona;
	}

	/**
	 * Método que permite modificar el atributo idPersona.
	 * @param idPersona
	 */
	public void setIdPersona(Long idPersona) {
		this.idPersona = idPersona;
	}

	/**
	 * Método que permite obtener el atributo numeroIdentificacion
	 * @return numeroIdentificacion
	 */
	public String getNumeroIdentificacion() {
		return numeroIdentificacion;
	}

	/**
	 * Método que permite modificar el atributo numeroIdentificacion.
	 * @param numeroIdentificacion
	 */
	public void setNumeroIdentificacion(String numeroIdentificacion) {
		this.numeroIdentificacion = numeroIdentificacion;
	}

	/**
	 * Método que permite obtener el atributo tipoIdentificacion
	 * @return tipoIdentificacion
	 */
	public String getTipoIdentificacion() {
		return tipoIdentificacion;
	}

	/**
	 * Método que permite modificar el atributo tipoIdentificacion.
	 * @param tipoIdentificacion
	 */
	public void setTipoIdentificacion(String tipoIdentificacion) {
		this.tipoIdentificacion = tipoIdentificacion;
	}

	/**
	 * Método que permite obtener el atributo numeroTelefonico
	 * @return numeroTelefonico
	 */
	public String getNumeroTelefonico() {
		return numeroTelefonico;
	}

	/**
	 * Método que permite modificar el atributo numeroTelefonico.
	 * @param numeroTelefonico
	 */
	public void setNumeroTelefonico(String numeroTelefonico) {
		this.numeroTelefonico = numeroTelefonico;
	}

	/**
	 * Método que permite obtener el atributo nombres
	 * @return nombres
	 */
	public String getNombres() {
		return nombres;
	}

	/**
	 * Método que permite modificar el atributo nombres.
	 * @param nombres
	 */
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	/**
	 * Método que permite obtener el atributo apellidos
	 * @return apellidos
	 */
	public String getApellidos() {
		return apellidos;
	}

	/**
	 * Método que permite modificar el atributo apellidos.
	 * @param apellidos
	 */
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	/**
	 * Método que permite obtener el atributo edad
	 * @return edad
	 */
	public Long getEdad() {
		return edad;
	}

	/**
	 * Método que permite modificar el atributo edad.
	 * @param edad
	 */
	public void setEdad(Long edad) {
		this.edad = edad;
	}

	/**
	 * Método que permite obtener el atributo vendedor
	 * @return vendedor
	 */
	public boolean isVendedor() {
		return vendedor;
	}

	/**
	 * Método que permite modificar el atributo vendedor.
	 * @param vendedor
	 */
	public void setVendedor(boolean vendedor) {
		this.vendedor = vendedor;
	}

	/**
	 * Método que permite obtener el atributo comprador
	 * @return comprador
	 */
	public boolean isComprador() {
		return comprador;
	}

	/**
	 * Método que permite modificar el atributo comprador.
	 * @param comprador
	 */
	public void setComprador(boolean comprador) {
		this.comprador = comprador;
	}

	/**
	 * Método que permite obtener el atributo serialversionuid
	 * @return serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
