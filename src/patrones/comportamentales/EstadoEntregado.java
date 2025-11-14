package patrones.comportamentales;

import models.Orden;

public class EstadoEntregado implements StateOrden {
    public static final String ESTADO_ID = "ENTREGADO";
    private static final String DESCRIPCION = "La orden ha sido entregada al cliente";

    @Override
    public void siguiente(Orden orden) {
        throw new IllegalStateException("No se puede avanzar desde el estado ENTREGADO");
    }

    @Override
    public void anterior(Orden orden) {
        orden.setEstado(new EstadoListo());
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
        return EstadoListo.ESTADO_ID.equals(estadoId);
    }
}
