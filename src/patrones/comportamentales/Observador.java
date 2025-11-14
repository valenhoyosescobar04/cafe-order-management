package patrones.comportamentales;
import models.Orden;
public interface Observador {
    void actualizar(Orden orden, String evento);
}