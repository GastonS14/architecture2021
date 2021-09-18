import entity.Carrera;
import entity.CarreraEstudiante;
import entity.Estudiante;
import service.CarreraEstudianteService;
import service.CarreraService;
import service.EstudianteService;

public class main {

	public static void main(String[] args) {

		// Services
		EstudianteService se = new EstudianteService();
		CarreraService cs = new CarreraService();
		CarreraEstudianteService ces = new CarreraEstudianteService();

		// Entities
		Estudiante e = new Estudiante(128, "sdf", "apellido", 4, 55, "a casa", "con el trabajo");
		Estudiante e1 = new Estudiante(45536, "nombre", "apellido", 4, 55, "a casa", "con el trabajo");
		Estudiante e2 = new Estudiante(1256, "nombre", "apellido", 4, 55, "a casa", "con el trabajo");
		Carrera c = new Carrera(12, "jaja");
		Carrera c1 = new Carrera(2, "carrera2");
		CarreraEstudiante ce = new CarreraEstudiante(c, e, null, null);

		// Save entities
		// se.save(e);
		// se.save(e1);
		// se.save(e2);
		// cs.save(c);
		// cs.save(c1);
		// ces.save(ce);

		// System.out.println(se.findAllOrderByDocumento());
		// System.out.println(cs.findAll());
		// System.out.println(se.findByLibreta(53455));
		// System.out.println(se.findAllByGenero("a casa"));

		// System.out.println(cs.findAllByInscriptosOrderByCount());
		System.out.println(se.findAllByCarreraAndCiudad(12, "tandil"));
		System.out.println(se.findAllByCarreraAndCiudad(12, "urug"));

	}
}
