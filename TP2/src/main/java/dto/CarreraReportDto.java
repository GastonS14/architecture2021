package dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class CarreraReportDto {

	@Id
	private int idCarrera;
	@Column
	private String nombre;
	@Column
	private String apellido;
	@Column
	private int libretaEstudiante;
	@Column
	private LocalDate fechaIngreso;
	@Column
	private LocalDate fechaEgreso;
	@Column
	private String nombreCarrera;
	@Column
	private long egresadosPorAnio;

	public CarreraReportDto() {}

	public CarreraReportDto(int idCarrera, String nombre, String apellido, int libretaEstudiante, LocalDate fechaIngreso, LocalDate fechaEgreso, String nombreCarrera, long egresadosPorAnio) {
		this.idCarrera = idCarrera;
		this.nombre = nombre;
		this.apellido = apellido;
		this.libretaEstudiante = libretaEstudiante;
		this.fechaIngreso = fechaIngreso;
		this.fechaEgreso = fechaEgreso;
		this.nombreCarrera = nombreCarrera;
		this.egresadosPorAnio = egresadosPorAnio;
	}

	@Override
	public String toString() {
		return "CarreraReportDto{" +
				"idCarrera=" + idCarrera +
				", nombre='" + nombre + '\'' +
				", apellido='" + apellido + '\'' +
				", libretaEstudiante=" + libretaEstudiante +
				", fechaIngreso=" + fechaIngreso +
				", fechaEgreso=" + fechaEgreso +
				", nombreCarrera='" + nombreCarrera + '\'' +
				", egresadosPorAnio=" + egresadosPorAnio +
				'}';
	}
}
