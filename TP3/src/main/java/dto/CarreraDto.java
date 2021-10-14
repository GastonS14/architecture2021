package dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table ( name = "carrera_dto")
public class CarreraDto {
	@Id
	private int idCarrera;
	@Column
	private String nombre;
	@Column
	private long cantidad;

	public CarreraDto(int idCarrera, String nombre, long cantidad) {
		this.idCarrera = idCarrera;
		this.nombre= nombre;
		this.cantidad = cantidad;
	}

	public CarreraDto() {

	}

	public int getIdCarrera() {
		return idCarrera;
	}

	public String getNombre() {
		return nombre;
	}

	public long getCantidad() {
		return cantidad;
	}

	@Override
	public String toString() {
		return "CarreraDto{" +
				"idCarrera=" + idCarrera +
				", nombre='" + nombre + '\'' +
				", cantidad=" + cantidad +
				'}';
	}
}
