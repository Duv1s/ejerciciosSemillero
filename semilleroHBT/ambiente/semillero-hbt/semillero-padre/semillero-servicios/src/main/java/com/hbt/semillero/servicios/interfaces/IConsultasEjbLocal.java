package com.hbt.semillero.servicios.interfaces;

import java.util.List;

import javax.ejb.Local;

import com.hbt.semillero.dto.LineaDTO;
import com.hbt.semillero.dto.MarcaDTO;
import com.hbt.semillero.dto.PersonaDTO;
import com.hbt.semillero.dto.ResultadoDTO;
import com.hbt.semillero.dto.VehiculoDTO;

/**
 * Expone los métodos del EJB ConsultasEJB
 * 
 * @author Johnny Soto
 *
 */
@Local
public interface IConsultasEjbLocal {

	/**
	 * Consulta las marcas existentes en el sistema.
	 * 
	 * @return
	 */
	public List<MarcaDTO> consultarMarcasExistentes();

	/**
	 * Consulta una lista de todas líneas asociadas a una marca registrada en el
	 * sistema.
	 */
	public List<LineaDTO> consultarLineasPorMarca(Long idMarca);

	/**
	 * Consulta las personas que cumplan con los criterios ingresados.
	 * 
	 * @param tipoID
	 * @param numID
	 * @return
	 */
	public List<PersonaDTO> consultarPersonas(String tipoID, String numID);

	/**
	 * Crea las personas en sus diferentes roles dentro del sistema.
	 * 
	 * @param personaDTO
	 * @return
	 */
	public ResultadoDTO crearPersona(PersonaDTO personaDTO);

	/**
	 * Crea los vehiculos dentro del sistema.
	 * @param vehiculo
	 * @return
	 */
	public ResultadoDTO crearVehiculo(VehiculoDTO vehiculo);

	/**
	 * Consulta los vehiculos que cumplan con los criterios ingresados.
	 * @param idMarca 
	 * @param idLinea 
	 * @return
	 */
	public List<VehiculoDTO> consultarVehiculos(long idLinea, long idMarca);

	/**
	 * Edita los vehiculos dentro del sistema
	 * @param vehiculoDTO
	 * @return 
	 */
	public ResultadoDTO editarVehiculo(VehiculoDTO vehiculoDTO);

	/**
	 * Elimina los vehiculos dentro del sistema por Id.
	 * @param idVehiculo
	 */
	public ResultadoDTO eliminarVehiculo(Long idVehiculo);

}
