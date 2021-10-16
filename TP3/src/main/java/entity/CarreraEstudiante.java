package entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table ( name = "carrera_estudiante")
public class CarreraEstudiante {

	@EmbeddedId
	private CarreraEstudiantePk carreraEstudiantePk;

	@ManyToOne( fetch = FetchType.LAZY )
	@JoinColumn ( name = "idCarrera", insertable=false, updatable=false)
	private Carrera carrera;

	@ManyToOne( fetch = FetchType.LAZY )
	@JoinColumn ( name = "idEstudiante", insertable=false, updatable=false)
	private Estudiante estudiante;

	@Column
	//@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private LocalDate fechaIngreso;

	@Column
	//@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private LocalDate fechaEgreso;

	public CarreraEstudiante() {}

	public CarreraEstudiante(Carrera carrera, Estudiante estudiante, LocalDate fechaIngreso, LocalDate fechaEgreso) {
		this.fechaIngreso = fechaIngreso;
		this.fechaEgreso = fechaEgreso;
		this.carrera = carrera;
		this.estudiante = estudiante;
		this.carreraEstudiantePk = new CarreraEstudiantePk(carrera.getId_carrera(), estudiante.getDocumento());
	}

	public int getIdEstudiante () {
		return this.carreraEstudiantePk.getIdEstudiante();
	}

	public int getIdCarrera () {
		return this.carreraEstudiantePk.getIdCarrera();
	}

	@JsonIgnore
	public CarreraEstudiantePk getPk ( ) {
		return this.carreraEstudiantePk;
	}

	public String getFechaIngreso() {
		return fechaIngreso.toString();
	}

	public void setFechaIngreso(LocalDate fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public String getFechaEgreso() {
		return fechaEgreso.toString();
	}

	public void setFechaEgreso(LocalDate fechaEgreso) {
		this.fechaEgreso = fechaEgreso;
	}

	@Override
	public boolean equals(Object o) {
		try{
			CarreraEstudiante ce = (CarreraEstudiante) o;
			return ce.getPk().equals( this.carreraEstudiantePk);
		}catch (Exception e ) {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(carreraEstudiantePk);
	}

	@Override
	public String toString() {
		return "CarreraEstudiante{" +
				"carreraEstudiantePk=" + carreraEstudiantePk +
				", carrera=" + carrera.getNombre() +
				", estudiante=" + estudiante.getNombre() +
				", fechaIngreso=" + fechaIngreso +
				", fechaEgreso=" + fechaEgreso +
				'}';
	}
}
