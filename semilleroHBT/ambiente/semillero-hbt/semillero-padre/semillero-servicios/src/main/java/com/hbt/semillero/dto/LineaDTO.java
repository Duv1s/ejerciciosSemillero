package com.hbt.semillero.dto;


import java.io.Serializable;

/**
 *
 * Clase creada para la transferencia de datos que representa Linea de un vehículo
 *
 */
public class LineaDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	/**
	 * Identificador de una linea de un vehículo
	 */
	private Long idLinea;

	/**
	 * Nombre de la linea de un vehículo
	 */
	private String nombre;

	/**
	 * Cilindraje de un vehiculo en cc
	 */
	private int cilindraje;

	/**
	 * Marca a la que peretenece una linea de un vehiculo
	 * Ejemplo una linea Clio es de la marca Renault
	 */
	private MarcaDTO marca;

	/**
	 * Método que permite obtener el atributo idLinea
	 * @return idLinea
	 */
	public Long getIdLinea() {
		return idLinea;
	}

	/**
	 * Método que permite modificar el atributo idLinea.
	 * @param idLinea
	 */
	public void setIdLinea(Long idLinea) {
		this.idLinea = idLinea;
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
	 * Método que permite obtener el atributo cilindraje
	 * @return cilindraje
	 */
	public int getCilindraje() {
		return cilindraje;
	}

	/**
	 * Método que permite modificar el atributo cilindraje.
	 * @param cilindraje
	 */
	public void setCilindraje(int cilindraje) {
		this.cilindraje = cilindraje;
	}

	/**
	 * Método que permite obtener el atributo marca
	 * @return marca
	 */
	public MarcaDTO getMarca() {
		return marca;
	}

	/**
	 * Método que permite modificar el atributo marca.
	 * @param marca
	 */
	public void setMarca(MarcaDTO marca) {
		this.marca = marca;
	}

	/**
	 * Método que permite obtener el atributo serialversionuid
	 * @return serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
