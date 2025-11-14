package patrones.comportamentales.estados;

public class EstadoLista implements StateOrden {
    @Override
    public void siguiente(Orden orden) {
        orden.setEstado(new EstadoEntregada());
        System.out.println("   📦 Orden entregada al cliente");
    }

    @Override
    public void anterior(Orden orden) {
        orden.setEstado(new EstadoEnPreparacion());
    }

    @Override
    public String getEstado() { return "LISTA"; }
}
