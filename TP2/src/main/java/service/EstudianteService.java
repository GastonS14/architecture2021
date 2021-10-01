package service;

import entity.Estudiante;
import repository.*;
import java.util.List;

public class EstudianteService {

    private static final EstudianteRepository estudianteRepository = EstudianteRepositoryImpl.getInstance();

    private EstudianteService(){}

    public static void save(Estudiante e) {
        estudianteRepository.save( e );
    }

    public static List<Estudiante> findAllOrderByDocumento() {
        return estudianteRepository.findAllOrderByDocumento();
    }

    public static Estudiante findByLibreta(int libreta) {
        return estudianteRepository.findByLibreta(libreta);
    }

    public static Estudiante findByDocumento(int documento) {
        return estudianteRepository.findByDocumento(documento);
    }

    public static List<Estudiante> findAllByGenero(String genero) {
        return estudianteRepository.findAllByGenero(genero);
    }

    public static List<Estudiante> findAllByCarreraAndCiudad(int idCarrera, String ciudad) {
        return estudianteRepository.findAllByCarreraAndCiudad(idCarrera, ciudad);
    }

}
