package dto;

public class CarreraEstudianteDto {

    private int idCarrera;
    private String fIngreso;
    private String fEgreso;

    public CarreraEstudianteDto () {}

    public int getIdCarrera() {
        return idCarrera;
    }

    public void setIdCarrera(int idCarrera) {
        this.idCarrera = idCarrera;
    }

    public String getfIngreso() {
        return fIngreso;
    }

    public void setfIngreso(String fIngreso) {
        this.fIngreso = fIngreso;
    }

    public String getfEgreso() {
        return fEgreso;
    }

    public void setfEgreso(String fEgreso) {
        this.fEgreso = fEgreso;
    }
}
