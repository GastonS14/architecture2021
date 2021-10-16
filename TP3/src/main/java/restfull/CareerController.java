package restfull;

import dto.CarreraDto;
import dto.CarreraReportDto;
import entity.Carrera;
import service.CarreraService;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/career")
public class CareerController {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Carrera> getCareers() {
        return CarreraService.findAll();
    }

    @GET
    @Path("/{value}")
    @Produces( MediaType.APPLICATION_JSON)
    public Carrera getById ( @PathParam("value") int id ) {
        return CarreraService.findById( id );
    }

    @GET
    @Path("/name/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Carrera getCareerByName (@PathParam("name") String name ) {
        return CarreraService.findByName( name );
    }

    @GET
    @Path("/report")
    @Produces(MediaType.APPLICATION_JSON)
    public List<CarreraReportDto> getReport () {
        return CarreraService.report();
    }

    @GET
    @Path("/inscriptos")
    @Produces(MediaType.APPLICATION_JSON)
    public List<CarreraDto> getCareersInscriptosOrderByCount () {
        return CarreraService.findAllByInscriptosOrderByCount();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    /**
     * JSON FORMAT:
     *       {
     *         "id_carrera": 11,
     *         "nombre": "angular",
     *         "estudianteCarreras": []
     *     }
     *     If you want, you can add a career without estudianteCarreras attribute
     */
    public Response addCareer ( Carrera c ) {
        CarreraService.save( c );
        return Response.status(201).entity(c).build();
    }
}
