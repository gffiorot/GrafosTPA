package Grafos;
import java.util.ArrayList;

public class Vertice<T> {
    private T valor;
    private ArrayList<Aresta> destinos;

    public Vertice(T valor) {
        this.setValor(valor);
        this.destinos = new ArrayList<Aresta>();
    }

    public void addDestino(Aresta a) {
        this.destinos.add(a);
        a.setOrigem(this);
    }

    public T getValor() {
        return valor;
    }

    public void setValor(T valor) {
        this.valor = valor;
    }

    public ArrayList<Aresta> getDestinos() {
        return destinos;
    }
}
