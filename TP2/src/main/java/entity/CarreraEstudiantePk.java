package entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class CarreraEstudiantePk implements Serializable {

	@Column(name = "idEstudiante")
	private int idEstudiante;

	@Column(name = "idCarrera")
	private int idCarrera;

	public CarreraEstudiantePk() {}

	public CarreraEstudiantePk(int idCarrera, int idEstudiante) {
		this.idCarrera = idCarrera;
		this.idEstudiante = idEstudiante;
	}

	public int getIdEstudiante ( ) {
		return this.idEstudiante;
	}

	public int getIdCarrera ( ) {
		return this.idCarrera;
	}

	public String toString ( ) {
		return " id_estudiante: " + this.idEstudiante + ", id_carrera: " + this.idCarrera;
	}
}
