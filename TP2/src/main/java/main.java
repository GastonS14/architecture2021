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
		e.addCareer( c, LocalDate.of(2021, 3, 15), LocalDate.of(2022, 1, 10));
		e.addCareer( c1, LocalDate.of(2019, 3, 15), LocalDate.of(2022, 6, 16));
		e.addCareer( c2, LocalDate.of(2018, 3, 15), LocalDate.of(2022, 3, 17));

		// Add more careers
		e.addCareer( c3, LocalDate.of(2019, 7, 15), LocalDate.of(2022, 8, 10));
		e.addCareer( c4, LocalDate.of(2014, 3, 15), LocalDate.of(2023, 8, 10));
		e.addCareer( c5, LocalDate.of(2016, 3, 15), LocalDate.of(2024, 8, 10));
		e.addCareer( c6, LocalDate.of(2015, 3, 15), LocalDate.of(2025, 8, 10));
		e1.addCareer( c, LocalDate.of(2020, 3, 23), LocalDate.of(2022, 8, 10));
		e2.addCareer( c, LocalDate.now(), LocalDate.of(2027, 8, 10));

		// Solutions

		//System.out.println( estudianteService.findAllOrderByDocumento() );
		//System.out.println( estudianteService.findByLibreta( 2 ) );
		//System.out.println( estudianteService.findAllByGenero( "masculino" ) );
		//System.out.println( carreraService.findAllByInscriptosOrderByCount() );
		//Carrera career = carreraService.findByName("arqui");
		//System.out.println( estudianteService.findAllByCarreraAndCiudad( career.getId(), "tandil") );
		//System.out.println( carreraService.report() );
		//estudianteService.removeCarrera( career.getId(), 0);
	}

}

