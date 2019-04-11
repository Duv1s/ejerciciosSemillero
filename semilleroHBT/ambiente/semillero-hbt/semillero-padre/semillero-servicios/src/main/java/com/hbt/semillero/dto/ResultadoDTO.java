package com.hbt.semillero.dto;

import java.io.Serializable;


/**
 * Clase creada para la transferencia de datos que representa el resultado de una transacción.
 * @author Duvis Alejandro Gómez Neira
 *
 */
public class ResultadoDTO implements Serializable {

	/**
	 * Variable para almacenar el estado de una transacción Si es exitosa tomará el
	 * valor true, de lo contrario tomará el valor false.
	 */
	private boolean exitoso;

	/**
	 * Variable para almacenar un posible mensaje de error
	 */
	private String mensajeError;

	/**
	 * Método que permite obtener el atributo exitoso
	 * 
	 * @return exitoso
	 */
	public boolean isExitoso() {
		return exitoso;
	}

	/**
	 * Método que permite modificar el atributo exitoso.
	 * 
	 * @param exitoso
	 */
	public void setExitoso(boolean exitoso) {
		this.exitoso = exitoso;
	}

	/**
	 * Método que permite obtener el atributo mensajeError
	 * 
	 * @return mensajeError
	 */
	public String getMensajeError() {
		return mensajeError;
	}

	/**
	 * Método que permite modificar el atributo mensajeError.
	 * 
	 * @param mensajeError
	 */
	public void setMensajeError(String mensajeError) {
		this.mensajeError = mensajeError;
	}

}
