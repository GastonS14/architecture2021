package restfull;

import dto.CarreraDto;
import dto.CarreraReportDto;
import entity.Carrera;
import service.CarreraService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/career")
public class CareerController {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Carrera> getCareers() {
        return CarreraService.findAll();
    }

    @GET
    @Path("/{name}")
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
}
