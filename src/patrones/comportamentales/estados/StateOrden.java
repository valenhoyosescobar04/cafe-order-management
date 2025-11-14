package patrones.comportamentales.estados;

public interface StateOrden {
    void siguiente(Orden orden);
    void anterior(Orden orden);
    String getEstado();
}

class EstadoEnPreparacion implements StateOrden {
    @Override
    public void siguiente(Orden orden) {
        orden.setEstado(new EstadoLista());
        System.out.println("   ✅ Orden lista para entregar");
    }

    @Override
    public void anterior(Orden orden) {
        orden.setEstado(new EstadoPendiente());
    }

    @Override
    public String getEstado() { return "EN_PREPARACION"; }
}

class EstadoEntregada implements StateOrden {
    @Override
    public void siguiente(Orden orden) {
        System.out.println("   ⚠️ La orden ya fue entregada");
    }

    @Override
    public void anterior(Orden orden) {
        orden.setEstado(new EstadoLista());
    }

    @Override
    public String getEstado() { return "ENTREGADA"; }
}
