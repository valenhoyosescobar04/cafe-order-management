package patrones.comportamentales;

import models.Orden;

public class EstadoEnPreparacion implements StateOrden {
    public static final String ESTADO_ID = "EN_PREPARACION";
    private static final String DESCRIPCION = "La orden está siendo preparada";

    @Override
    public void siguiente(Orden orden) {
        orden.setEstado(new EstadoListo());
    }

    @Override
    public void anterior(Orden orden) {
        orden.setEstado(new EstadoPendiente());
    }

    @Override
    public String getEstadoId() {
        return ESTADO_ID;
    }

    @Override
    public String getDescripcion() {
        return DESCRIPCION;
    }

    @Override
    public boolean esTransicionValida(String estadoId) {
        return EstadoPendiente.ESTADO_ID.equals(estadoId) ||
                EstadoListo.ESTADO_ID.equals(estadoId);
    }
}
