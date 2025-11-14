package patrones.comportamentales;

import models.Orden;

public class EstadoPendiente implements StateOrden {
    public static final String ESTADO_ID = "PENDIENTE";
    private static final String DESCRIPCION = "La orden está pendiente de ser preparada";

    @Override
    public void siguiente(Orden orden) {
        orden.setEstado(new EstadoEnPreparacion());
    }

    @Override
    public void anterior(Orden orden) {
        throw new IllegalStateException("No se puede retroceder desde el estado PENDIENTE");
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
        return EstadoEnPreparacion.ESTADO_ID.equals(estadoId);
    }
}