package models;

import patrones.comportamentales.Observador;

import java.util.ArrayList;
import java.util.List;

public class SistemaNotificaciones {
    private List<Observador> observadores = new ArrayList<>();

    public void agregarObservador(Observador observador) {
        observadores.add(observador);
    }

    public void notificar(Orden orden, String evento) {
        for (Observador obs : observadores) {
            obs.actualizar(orden, evento);
        }
    }
}