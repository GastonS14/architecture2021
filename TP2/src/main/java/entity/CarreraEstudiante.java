package entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class CarreraEstudiante {

	@EmbeddedId
	private CarreraEstudiantePk carreraEstudiantePk;

	@ManyToOne
	@MapsId("idCarrera")
	private Carrera carrera;

	@ManyToOne(cascade = {CascadeType.REFRESH})
	@MapsId("idEstudiante")
	private Estudiante estudiante;

	@Column
	private LocalDate fechaIngreso;

	@Column
	private LocalDate fechaEgreso;

	public CarreraEstudiante() {}

	public CarreraEstudiante(Carrera carrera, Estudiante estudiante, LocalDate fechaIngreso, LocalDate fechaEgreso) {
		this.fechaIngreso = fechaIngreso;
		this.fechaEgreso = fechaEgreso;
		this.carrera = carrera;
		this.estudiante = estudiante;
		this.carreraEstudiantePk = new CarreraEstudiantePk(carrera.getId(), estudiante.getId());
	}

	@Override
	public String toString() {
		return "CarreraEstudiante{" +
				"carreraEstudiantePk=" + carreraEstudiantePk +
				", carrera=" + carrera +
				", estudiante=" + estudiante +
				", fechaIngreso=" + fechaIngreso +
				", fechaEgreso=" + fechaEgreso +
				'}';
	}
}
