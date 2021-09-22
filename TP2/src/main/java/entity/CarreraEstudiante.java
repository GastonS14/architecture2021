package entity;

import com.sun.istack.NotNull;
import com.sun.istack.Nullable;

import javax.persistence.*;
import java.time.LocalDate;

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

	public int getIdEstudiante () {
		return this.carreraEstudiantePk.getIdEstudiante();
	}

	public int getIdCarrera () {
		return this.carreraEstudiantePk.getIdCarrera();
	}

	@Override
	public boolean equals ( Object ce ) {
		try {
			CarreraEstudiante auxCE = (CarreraEstudiante) ce;
			return auxCE.getIdEstudiante() == this.getIdEstudiante() &&
						 auxCE.getIdCarrera() == this.getIdCarrera();
		}catch ( Exception e ) {
			return false;
		}
	}

	@Override
	public String toString() {
		return "CarreraEstudiante{" +
				"carreraEstudiantePk=" + carreraEstudiantePk +
				", carrera=" + carrera.getNombre() +
				", estudiante=" + estudiante.getName() +
				", fechaIngreso=" + fechaIngreso +
				", fechaEgreso=" + fechaEgreso +
				'}';
	}
}
