package patrones.comportamentales;

import models.Orden;

public class EstadoListo implements StateOrden {
    public static final String ESTADO_ID = "LISTO";
    private static final String DESCRIPCION = "La orden está lista para ser entregada";

    @Override
    public void siguiente(Orden orden) {
        orden.setEstado(new EstadoEntregado());
    }

    @Override
    public void anterior(Orden orden) {
        orden.setEstado(new EstadoEnPreparacion());
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
        return EstadoEnPreparacion.ESTADO_ID.equals(estadoId) ||
                EstadoEntregado.ESTADO_ID.equals(estadoId);
    }
}
