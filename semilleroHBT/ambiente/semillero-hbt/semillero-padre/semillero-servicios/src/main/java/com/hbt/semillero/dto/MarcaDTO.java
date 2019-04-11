package com.hbt.semillero.dto;

import java.io.Serializable;
/**
 * Clase creada para la transferencia de datos que representa una marca de un vehículo
 * @author Duvis Alejandro Gómez Neira
 *
 */
public class MarcaDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	 * Id de la marca del vehículo
	 */
	private Long idMarca;
	
	/**
	 * Nombre de la marca del vehículo
	 */
	private String nombre;

	/**
	 * Método que permite obtener el atributo idMarca
	 * @return idMarca
	 */
	
	
	public Long getIdMarca() {
		return idMarca;
	}

	/**
	 * Método que permite modificar el atributo idMarca.
	 * @param idMarca
	 */
	public void setIdMarca(Long idMarca) {
		this.idMarca = idMarca;
	}

	/**
	 * Método que permite obtener el atributo nombre
	 * @return nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Método que permite modificar el atributo nombre.
	 * @param nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Método que permite obtener el atributo serialversionuid
	 * @return serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
