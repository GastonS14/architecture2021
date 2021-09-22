import entity.Carrera;
import entity.Estudiante;
import service.CarreraService;
import service.EstudianteService;
import java.time.LocalDate;

/**
 * All comments are in english. In your face GASTON !
 */
public class main {

	/*
		PREGUNTAS:
			1- MANEJO DE EXCEPCIONES // rta: mostrar en el log
			2- COMO HACER ADD EN LAS LISTAS ? DESDE LA ENTIDAD O SERVICE ? rta: service
	 */

	public static void main(String[] args) {

		// Services
		EstudianteService se = new EstudianteService();
		CarreraService cs = new CarreraService();
/*
		// Entities
		Estudiante e = new Estudiante(128, "juan", "ja", 4, 55, "a casa", "con el trabajo");
		Estudiante e1 = new Estudiante(45536, "gaston", "je", 4, 12, "a casa", "con el trabajo");
		Estudiante e2 = new Estudiante(1256, "pablo", "jo", 4, 3, "a casa", "con el trabajo");
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
		se.save( e );
		se.save( e1 );
		se.save( e2 );

		cs.save( c);
		cs.save( c1 );
		cs.save( c2 );
		cs.save( c3 );
		cs.save( c4 );
		cs.save( c5 );
		cs.save( c6 );
		cs.save( c7 );
		cs.save( c8 );
		cs.save( c9 );
		cs.save( c10 );
		cs.save( c11 );

	// Add some careers
		se.addCarrera( c, e, LocalDate.of(2021,3,15), LocalDate.of(2022,1,10) );
		se.addCarrera( c1, e, LocalDate.of(2019,3,15), LocalDate.of(2022,6,16) );
		se.addCarrera( c2, e, LocalDate.of(2018,3,15), LocalDate.of(2022,3,17) );

	// Let see the partial result
		System.out.println( " carreras por cant de inscriptos ");
		System.out.println( cs.findAllByInscriptosOrderByCount() );

	// Add more careers
		se.addCarrera( c3, e, LocalDate.of(2019,7,15), LocalDate.of(2022,8,10) );
		se.addCarrera( c4, e, LocalDate.of(2014,3,15), LocalDate.of(2023,8,10) );
		se.addCarrera( c5, e, LocalDate.of(2016,3,15), LocalDate.of(2024,8,10) );
		se.addCarrera( c6, e, LocalDate.of(2015,3,15), LocalDate.of(2025,8,10) );
		se.addCarrera( c, e1, LocalDate.of(2020,3,23), LocalDate.of(2022,8,10) );
		se.addCarrera( c, e2, LocalDate.now(), LocalDate.of(2027,8,10));
*/
		System.out.println( cs.report() );

	// Let see the final result
		/*
		System.out.println( " estudiante juan : ");
		System.out.println( se.findByLibreta( 55 ) );
		System.out.println( " carreras por cant de inscriptos ");
		System.out.println( cs.findAllByInscriptosOrderByCount() );

	// Let see what happen if I remove a student from career c
		//It doesn't work when you run this in the same execution
		// If you run first the above code and then, in other execution, run this; it works :)
		/*
			Carrera toUpdate = cs.findByName( "web1" );
			Estudiante toDelete = se.findByLibreta( 55 );
			cs.removeStudent( toUpdate, toDelete);
			System.out.println( " estudiante juan : ");
			System.out.println( toDelete );
			System.out.println( " carreras por cant de inscriptos ");
			System.out.println( cs.findAllByInscriptosOrderByCount() );
		*/

	// Delete career &/or Delete student
		/*
			//Carrera c = cs.findByName("web1");
			//Estudiante aux = se.findByLibreta( 12 );
			// cs.addStudent( c,aux,LocalDate.now(),null ); it´s work
			// cs.removeStudent( c, aux ); it´s work
			// se.removeCarrera( c, aux ); it´s work too
		*/


	// System.out.println(se.findAllOrderByDocumento());
	// System.out.println(cs.findAll());
	// System.out.println(se.findByLibreta(53455));
	// System.out.println(se.findAllByGenero("a casa"));

	// System.out.println(cs.findAllByInscriptosOrderByCount());
	//System.out.println(se.findAllByCarreraAndCiudad(12, "tandil"));
	//System.out.println(se.findAllByCarreraAndCiudad(12, "urug"));

	}
}
