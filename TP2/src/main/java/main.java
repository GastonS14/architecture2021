import entity.Carrera;
import entity.Estudiante;
import service.CarreraService;
import service.EstudianteService;

import java.time.LocalDate;

public class main {


	public static void main(String[] args) {

		// Services
		EstudianteService estudianteService = new EstudianteService();
		CarreraService carreraService = new CarreraService();

		// Entities
		Estudiante e = new Estudiante(0, "juanse", "cr", 4, 0, "masculino", "tandil");
		Estudiante e1 = new Estudiante(1, "gaston", "ss", 4, 1, "femenino", "montevideo");
		Estudiante e2 = new Estudiante(2, "pablo", "c", 4, 2, "masculino", "buenos aires");
		Carrera c = new Carrera("web1");
		Carrera c1 = new Carrera("arqui");
		Carrera c2 = new Carrera("web2");
		Carrera c3 = new Carrera("prog");
		Carrera c4 = new Carrera("prog2");
		Carrera c5 = new Carrera("prog3");
		Carrera c6 = new Carrera("interfaces");
		Carrera c7 = new Carrera("tDoc&val");
		Carrera c8 = new Carrera("go");
		Carrera c9 = new Carrera("docker");
		Carrera c10 = new Carrera("angular");
		Carrera c11 = new Carrera("metodologias");

		// Save entities
		estudianteService.save(e);
		estudianteService.save(e1);
		estudianteService.save(e2);

		carreraService.save(c);
		carreraService.save(c1);
		carreraService.save(c2);
		carreraService.save(c3);
		carreraService.save(c4);
		carreraService.save(c5);
		carreraService.save(c6);
		carreraService.save(c7);
		carreraService.save(c8);
		carreraService.save(c9);
		carreraService.save(c10);
		carreraService.save(c11);

		// Add some careers
		estudianteService.addCarrera(c, e, LocalDate.of(2021, 3, 15), LocalDate.of(2022, 1, 10));
		estudianteService.addCarrera(c1, e, LocalDate.of(2019, 3, 15), LocalDate.of(2022, 6, 16));
		estudianteService.addCarrera(c2, e, LocalDate.of(2018, 3, 15), LocalDate.of(2022, 3, 17));

		// Add more careers
		estudianteService.addCarrera(c3, e, LocalDate.of(2019, 7, 15), LocalDate.of(2022, 8, 10));
		estudianteService.addCarrera(c4, e, LocalDate.of(2014, 3, 15), LocalDate.of(2023, 8, 10));
		estudianteService.addCarrera(c5, e, LocalDate.of(2016, 3, 15), LocalDate.of(2024, 8, 10));
		estudianteService.addCarrera(c6, e, LocalDate.of(2015, 3, 15), LocalDate.of(2025, 8, 10));
		estudianteService.addCarrera(c, e1, LocalDate.of(2020, 3, 23), LocalDate.of(2022, 8, 10));
		estudianteService.addCarrera(c, e2, LocalDate.now(), LocalDate.of(2027, 8, 10));

		// Solutions
		System.out.println( estudianteService.findAllOrderByDocumento() );
		System.out.println( estudianteService.findByLibreta( 2 ) );
		System.out.println( estudianteService.findAllByGenero( "masculino" ) );
		System.out.println( carreraService.findAllByInscriptosOrderByCount() );
		Carrera career = carreraService.findByName("arqui");
		System.out.println( estudianteService.findAllByCarreraAndCiudad( career.getId(), "tandil") );
		System.out.println( carreraService.report() );
	}
}
	/*
		TESTING MAIN


	public static void main(String[]args) {
		CarreraService cs = new CarreraService();
		EstudianteService es = new EstudianteService();

		Estudiante e = new Estudiante(0,"p","p",2,159,"m","asd");
		//es.save( e );
		//cs.removeStudent(515,0); // it's working
		//cs.addStudent( cs.findByName("docker"),e,LocalDate.now(),null);

		// System.out.println( cs.findAllByInscriptosOrderByCount() ); // working
		//System.out.println( cs.findAll() ); // working
		//System.out.println( cs.findById(507) ); // working
		//System.out.println( cs.findByName("web1") ); // working
		//System.out.println( cs.findById(2)); // working -> doesn't exist-> return null
		//System.out.println( cs.report() ); // working

		//System.out.println( es.findByLibreta(3) ); // working

		Carrera c = new Carrera("redes");
		//cs.save( c ); // working
		// es.addCarrera( c, e, LocalDate.now(), null ); // working
		// es.removeCarrera(cs.findByName("redes").getId(), e.getId() ); // working
		//System.out.println( es.findByDocumento(233) ); // working
		//System.out.println( es.findAllByGenero("a casa") ); // working
		//System.out.println( es.findAllOrderByDocumento() ); // working
		//System.out.println( es.findAllByCarreraAndCiudad(506,"con el trabajo") ); // working
		es.removeCarrera(516,0);
	}

}

	 */
