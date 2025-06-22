package dto;

public class VentaDto {

    private FuncionDTO funcion;

    public VentaDto(FuncionDTO funcion) {
        this.funcion = funcion;
    }

    public FuncionDTO getFuncion() {
        return funcion;
    }

    public void setFuncion(FuncionDTO funcion) {
        this.funcion = funcion;
    }
}
