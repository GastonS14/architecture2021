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
    public List<Estudiante> getStudents() {
        return EstudianteService.findAll();
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
    @Path("/genero/{value}")
    public List<Estudiante> getStudentsByGenero(@PathParam("value") String genero ) {
        return EstudianteService.findAllByGenero( genero );
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
    @Path("/orderByDoc")
    public List<Estudiante> getStudentsInOrderByDoc ( ) {
        return EstudianteService.findAllOrderByDocumento();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addStudent ( Estudiante s ) {
        EstudianteService.save( s );
        return Response.status(201).entity(s).build();

    }

    @POST
    @Path("/{id}/addCareer")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addCareer ( @PathParam("id") int doc, CarreraEstudianteDto c )  {
        Estudiante student = EstudianteService.findByDocumento( doc );
        Carrera career = CarreraService.findById( c.getIdCarrera() );
        student.addCareer( career, LocalDate.parse(c.getfIngreso()), LocalDate.parse( c.getfEgreso() ) );
        return Response.status(201).entity(student).build();
    }
}
