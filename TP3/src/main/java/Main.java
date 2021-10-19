import entity.Carrera;
import entity.Estudiante;
import service.CarreraService;
import service.EstudianteService;
import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        Estudiante e = new Estudiante(0, "juanse", "rossi", 24, 0, "masculino", "tandil");
        Estudiante e1 = new Estudiante(1, "gaston", "sanchez", 25, 1, "femenino", "montevideo");
        Estudiante e2 = new Estudiante(2, "pablo", "cutropia", 32, 2, "masculino", "buenos aires");
        Estudiante e3 = new Estudiante(3, "rafa", "camet", 23, 3, "masculino", "tandil");
        Estudiante e4 = new Estudiante(4, "manuel", "terpin", 45, 4, "femenino", "tandil");
        Estudiante e5 = new Estudiante(5, "franco", "gayo", 19, 5, "masculino", "buenos aires");
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
        EstudianteService.save(e3);
        EstudianteService.save(e4);
        EstudianteService.save(e5);

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
        e3.addCareer( c3, LocalDate.of(2019, 7, 15), LocalDate.of(2022, 8, 10));
        e.addCareer( c4, LocalDate.of(2014, 3, 15), LocalDate.of(2023, 8, 10));
        e5.addCareer( c5, LocalDate.of(2016, 3, 15), LocalDate.of(2024, 8, 10));
        e.addCareer( c6, LocalDate.of(2015, 3, 15), LocalDate.of(2025, 8, 10));
        e1.addCareer( c, LocalDate.of(2020, 3, 23), LocalDate.of(2022, 8, 10));
        e2.addCareer( c, LocalDate.now(), LocalDate.of(2027, 8, 10));
        e4.addCareer( c10, LocalDate.now(), LocalDate.of(2025, 8, 10));
    }
}
