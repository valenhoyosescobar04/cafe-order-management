package patrones.comportamentales;

import models.Orden;

public interface StateOrden {
    void siguiente(Orden orden);
    void anterior(Orden orden);
    String getEstadoId();
    String getDescripcion();
    boolean esTransicionValida(String estadoId);
}