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
		Estudiante e = new Estudiante(128, "juan", "ja", 4, 55, "masculino", "tandil");
		Estudiante e1 = new Estudiante(45536, "maria", "je", 4, 12, "femenino", "mar del plata");
		Estudiante e2 = new Estudiante(1256, "pablo", "jo", 4, 3, "masculino", "buenos aires");
		Carrera c = new Carrera( "web1");
		Carrera c1 = new Carrera( "arqui");
		Carrera c2 = new Carrera( "web2");
		Carrera c3 = new Carrera( "prog");
		Carrera c4 = new Carrera( "prog2");
		Carrera c5 = new Carrera( "prog3");
		Carrera c6 = new Carrera( "interfaces");
		Carrera c7 = new Carrera( "tDoc&val");
		Carrera c8 = new Carrera( "go");
		Carrera c9 = new Carrera( "docker");
		Carrera c10 = new Carrera( "angular");
		Carrera c11 = new Carrera( "metodologias");

		// Save entities
		estudianteService.save( e );
		estudianteService.save( e1 );
		estudianteService.save( e2 );

		carreraService.save( c);
		carreraService.save( c1 );
		carreraService.save( c2 );
		carreraService.save( c3 );
		carreraService.save( c4 );
		carreraService.save( c5 );
		carreraService.save( c6 );
		carreraService.save( c7 );
		carreraService.save( c8 );
		carreraService.save( c9 );
		carreraService.save( c10 );
		carreraService.save( c11 );

		// Add some careers
		estudianteService.addCarrera( c, e, LocalDate.of(2021,3,15), LocalDate.of(2022,1,10) );
		estudianteService.addCarrera( c1, e, LocalDate.of(2019,3,15), LocalDate.of(2022,6,16) );
		estudianteService.addCarrera( c2, e, LocalDate.of(2018,3,15), LocalDate.of(2022,3,17) );

		// Let see the partial result
		System.out.println( " carreras por cant de inscriptos ");
		System.out.println( carreraService.findAllByInscriptosOrderByCount() );

		// Add more careers
		estudianteService.addCarrera( c3, e, LocalDate.of(2019,7,15), LocalDate.of(2022,8,10) );
		estudianteService.addCarrera( c4, e, LocalDate.of(2014,3,15), LocalDate.of(2023,8,10) );
		estudianteService.addCarrera( c5, e, LocalDate.of(2016,3,15), LocalDate.of(2024,8,10) );
		estudianteService.addCarrera( c6, e, LocalDate.of(2015,3,15), LocalDate.of(2025,8,10) );
		estudianteService.addCarrera( c, e1, LocalDate.of(2020,3,23), LocalDate.of(2022,8,10) );
		estudianteService.addCarrera( c, e2, LocalDate.now(), LocalDate.of(2027,8,10));

		System.out.println( carreraService.report() );

		// Let see the final result


		System.out.println( " estudiante juan : ");
		System.out.println( estudianteService.findByLibreta( 55 ) );
		System.out.println( " carreras por cant de inscriptos ");
		System.out.println( carreraService.findAllByInscriptosOrderByCount() );


		// Testing
		// --- Estudiante ---

		System.out.println( estudianteService.findByLibreta( 12 ) );
		System.out.println( estudianteService.findByLibreta( 55454 ) ); // Not found

		System.out.println( estudianteService.findByDocumento( 128 ) );
		System.out.println( estudianteService.findByDocumento( 123234554 ) ); // Not found

		System.out.println( estudianteService.findAllByGenero( "masculino" ) );
		System.out.println( estudianteService.findAllByGenero( "m" ) ); // Empty list

		System.out.println( estudianteService.findAllByCarreraAndCiudad( 4, "tandil" ) );
		System.out.println( estudianteService.findAllByCarreraAndCiudad( 128, "buenos aires" ) );  // Empty list
		System.out.println( estudianteService.findAllByCarreraAndCiudad( 455, "cordoba" ) ); // Empty list

		System.out.println( estudianteService.findAllOrderByDocumento() );



		// Update


		estudianteService.removeCarrera( 128, 1256); // Not found Carrera
		estudianteService.removeCarrera( 1, 6785); // Not found Esudiante
		estudianteService.removeCarrera( 3, 128);


		// --- Carrera ---
		System.out.println( carreraService.findById( 1 ) );
		System.out.println( carreraService.findById( 123432 ) ); // Not found

		System.out.println( carreraService.findByName( "angular" ) );
		System.out.println( carreraService.findByName( "not found name" ) ); // Not found

		System.out.println( carreraService.findAll() );

		System.out.println( carreraService.findAllByInscriptosOrderByCount() );

		carreraService.removeStudent( 128, 1256); // Not found Carrera
		carreraService.removeStudent( 1, 6785); // Not found Esudiante
		carreraService.removeStudent( 3, 128);

		// Update

		// --- CarreraEstudiante ---
		// carreraEstudianteService.findByIdCarreraAndIdEstudiante(6, 128);
		// carreraEstudianteService.findByIdCarreraAndIdEstudiante(3, 128); // Not found



		// Let see what happen if I remove a student from career c
		// It doesn't work when you run this in the same execution
		// If you run first the above code and then, in other execution, run this; it works :)
		/*
			Carrera toUpdate = carreraService.findByName( "web1" );
			Estudiante toDelete = estudianteService.findByLibreta( 55 );
			carreraService.removeStudent( toUpdate, toDelete);
			System.out.println( " estudiante juan : ");
			System.out.println( toDelete );
			System.out.println( " carreras por cant de inscriptos ");
			System.out.println( carreraService.findAllByInscriptosOrderByCount() );

		// Delete career &/or Delete student
			// Carrera c = carreraService.findByName("web1");
			// Estudiante aux = estudianteService.findByLibreta( 12 );
			// carreraService.addStudent( c,aux,LocalDate.now(),null ); it´s work
			// carreraService.removeStudent( c, aux ); it´s work
			// estudianteService.removeCarrera( c, aux ); it´s work too



		// System.out.println(estudianteService.findAllOrderByDocumento());
		// System.out.println(carreraService.findAll());
		// System.out.println(estudianteService.findByLibreta(53455));
		// System.out.println(estudianteService.findAllByGenero("a casa"));

		// System.out.println(carreraService.findAllByInscriptosOrderByCount());
		//System.out.println(estudianteService.findAllByCarreraAndCiudad(12, "tandil"));
		//System.out.println(estudianteService.findAllByCarreraAndCiudad(12, "urug"));

		 */

	}
}
