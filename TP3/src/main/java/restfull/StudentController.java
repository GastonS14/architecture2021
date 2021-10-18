package restfull;

import dto.CarreraEstudianteDto;
import entity.Carrera;
import entity.Estudiante;
import service.CarreraService;
import service.EstudianteService;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.util.List;

@Path("/student")
public class StudentController {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Estudiante> getStudents( ) {
        return EstudianteService.findAll( );
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Estudiante getStudentByDoc(@PathParam("id") int doc ) {
        return EstudianteService.findByDocumento( doc );
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/libreta/{value}")
    public Estudiante getStudentByLibreta(@PathParam("value") int libreta ) {
        return EstudianteService.findByLibreta( libreta );
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/filter/")
    public List<Estudiante> getStudentsByGenero(@DefaultValue("masculino")@QueryParam("genre") String genero,
                                        @DefaultValue("tandil")@QueryParam("city") String ciudad ) {
        return EstudianteService.filterBy( genero, ciudad );
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/cities")
    public List<String> getAllCities() {
        return EstudianteService.getAllCities( );
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/career/{id_carrera}/city/{ciudad}")
    public List<Estudiante> getStudentsByCarreraCiudad( @PathParam("id_carrera") int idCarrera,
                                                @PathParam("ciudad") String ciudad ) {
        return EstudianteService.findAllByCarreraAndCiudad( idCarrera, ciudad);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/sort/")
    public List<Estudiante> getStudentsInOrderBy ( @DefaultValue("documento") @QueryParam("attribute") String attribute,
                                                   @DefaultValue("ASC")@QueryParam("sortOrder") String sortOrder) {
        return EstudianteService.findAllOrderBy( attribute, sortOrder);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    /**
     * JSON FORMAT:
     *      {
     *          "documento": 5,
     *          "nombre": "postman",
     *          "apellido": "desde",
     *          "libretaUniversitaria": 34,
     *          "edad": 24,
     *          "genero": "masculino",
     *          "ciudadResidencia": "tandil",
     *          "carreraEstudiante": [
     *                  {
     *                  "fechaIngreso": "2020-07-17",
     *                  "fechaEgreso": "2025-12-01",
     *                  "idCarrera": 11,
     *                  "idEstudiante": 5
     *                  }
     *                  ]
     *     }
     *     If you want, you can add a student without carreraEstudiante attribute
     */
    public Response addStudent ( Estudiante s ) {
        EstudianteService.save( s );
        return Response.status(201).entity(s).build();

    }

    @POST
    @Path("/{id}/addCareer")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    /**
     * JSON FORMAT:
     *      {
     *          "idCarrera":11,
     *          "fIngreso":"2020-07-17",
     *          "fEgreso":"2025-12-01"
     *      }
     */
    public Response addCareer ( @PathParam("id") int doc, CarreraEstudianteDto c )  {
        Estudiante student = EstudianteService.findByDocumento( doc );
        Carrera career = CarreraService.findById( c.getIdCarrera() );
        try {
            student.addCareer( career, LocalDate.parse(c.getfIngreso()), LocalDate.parse( c.getfEgreso() ) );
            return Response.status(201).entity(student).build();
        } catch ( Exception e ) {
           return Response.status(400).entity(null).build();
        }
    }

}
