package com.hbt.semillero.dto;

public class VehiculoDTO {

	private Long idVehiculo;

	private int modelo;

	private String placa;

	private LineaDTO linea;

	/**
	 * Método que permite obtener el atributo idVehiculo
	 * @return idVehiculo
	 */
	public Long getIdVehiculo() {
		return idVehiculo;
	}

	/**
	 * Método que permite modificar el atributo idVehiculo.
	 * @param idVehiculo
	 */
	public void setIdVehiculo(Long idVehiculo) {
		this.idVehiculo = idVehiculo;
	}

	/**
	 * Método que permite obtener el atributo modelo
	 * @return modelo
	 */
	public int getModelo() {
		return modelo;
	}

	/**
	 * Método que permite modificar el atributo modelo.
	 * @param modelo
	 */
	public void setModelo(int modelo) {
		this.modelo = modelo;
	}

	/**
	 * Método que permite obtener el atributo placa
	 * @return placa
	 */
	public String getPlaca() {
		return placa;
	}

	/**
	 * Método que permite modificar el atributo placa.
	 * @param placa
	 */
	public void setPlaca(String placa) {
		this.placa = placa;
	}

	/**
	 * Método que permite obtener el atributo linea
	 * @return linea
	 */
	public LineaDTO getLinea() {
		return linea;
	}

	/**
	 * Método que permite modificar el atributo linea.
	 * @param linea
	 */
	public void setLinea(LineaDTO linea) {
		this.linea = linea;
	}
	
	


}
