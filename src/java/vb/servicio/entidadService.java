package vb.servicio;
import java.util.List;

public interface entidadService<T> {
    
    public int crearEntidad(T t);
    public int eliminarEntidad(int codigo);
    public int actualizarEntidad(T t);
    public List<T> obtenerEntidades();
    public T buscarEntidad(int codigo);
    
}
