package patrones.comportamentales;

import models.Orden;

public class EstadoPendiente implements StateOrden {
    @Override
    public void siguiente(Orden orden) {
        orden.setEstado(new EstadoEnPreparacion());
        System.out.println("   ⏩ Orden en preparación");
    }

    @Override
    public void anterior(Orden orden) {
        System.out.println("   ⚠️ La orden ya está en estado inicial");
    }

    @Override
    public String getEstado() { return "PENDIENTE"; }
}
