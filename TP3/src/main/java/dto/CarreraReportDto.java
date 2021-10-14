package dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigInteger;


@Entity
@Table (name = "carrera_report")
public class CarreraReportDto {
	@Id
	private int idCarrera;
	@Column
	private String nombreCarrera;
	@Column
	private int añoIngreso;
	@Column
	private int añoEgreso;
	@Column ( name = "cantidad_de_egresados")
	private BigInteger cantEgresados;
	@Column ( name = "cantidad_de_inscriptos")
	private BigInteger cantInscriptos;

	public CarreraReportDto() {}

	public CarreraReportDto(int idCarrera, String nombreCarrera, int añoIngreso, int añoEgreso,
							BigInteger cantInscriptos, BigInteger cantEgresados ) {
		this.idCarrera = idCarrera;
		this.añoIngreso = añoIngreso;
		this.añoEgreso = añoEgreso;
		this.nombreCarrera = nombreCarrera;
		this.cantEgresados = cantEgresados;
		this.cantInscriptos = cantInscriptos;
	}

	public int getIdCarrera() {
		return idCarrera;
	}

	public String getNombreCarrera() {
		return nombreCarrera;
	}

	public int getAñoIngreso() {
		return añoIngreso;
	}

	public int getAñoEgreso() {
		return añoEgreso;
	}

	public BigInteger getCantEgresados() {
		return cantEgresados;
	}

	public BigInteger getCantInscriptos() {
		return cantInscriptos;
	}

	@Override
	public String toString() {
		return "CarreraReportDto{" +
				"idCarrera=" + idCarrera +
				", nombreCarrera='" + nombreCarrera + '\'' +
				", fechaIngreso=" + añoIngreso +
				", fechaEgreso=" + añoEgreso +
				", cantEgresados=" + cantEgresados +
				", cantInscriptos=" + cantInscriptos +
				'}';
	}
}
