import entity.Carrera;
import entity.Estudiante;
import service.CarreraService;
import service.EstudianteService;

import java.time.LocalDate;

public class main {


	public static void main(String[] args) {

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
		EstudianteService.save(e);
		EstudianteService.save(e1);
		EstudianteService.save(e2);

		CarreraService.save(c);
		CarreraService.save(c1);
		CarreraService.save(c2);
		CarreraService.save(c3);
		CarreraService.save(c4);
		CarreraService.save(c5);
		CarreraService.save(c6);
		CarreraService.save(c7);
		CarreraService.save(c8);
		CarreraService.save(c9);
		CarreraService.save(c10);
		CarreraService.save(c11);

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

		//System.out.println( EstudianteService.findAllOrderByDocumento() );
		//System.out.println( EstudianteService.findByLibreta( 2 ) );
		//System.out.println( EstudianteService.findAllByGenero( "masculino" ) );
		//System.out.println( CarreraService.findAllByInscriptosOrderByCount() );
		//Carrera career = CarreraService.findByName("arqui");
		//System.out.println( EstudianteService.findAllByCarreraAndCiudad( career.getId(), "tandil") );
		//System.out.println( CarreraService.report() );

	}

}

