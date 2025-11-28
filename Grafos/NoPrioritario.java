package Grafos;
public class NoPrioritario<T> implements Comparable<NoPrioritario<T>>{
    private Vertice<T> vertice;
    private Vertice<T> origem;
    private float custo;

    public NoPrioritario(Vertice<T> vertice, Vertice<T> origem, float custo) {
        this.setVertice(vertice);
        this.setCusto(custo);
        this.setOrigem(origem);
    }

    @Override
    public int compareTo(NoPrioritario<T> outro) {
        return Float.compare(this.getCusto(), outro.getCusto());
    }

    public Vertice<T> getVertice() {
        return vertice;
    }

    public void setVertice(Vertice<T> vertice) {
        this.vertice = vertice;
    }

    public Vertice<T> getOrigem() {
        return origem;
    }

    public void setOrigem(Vertice<T> origem) {
        this.origem = origem;
    }

    public float getCusto() {
        return custo;
    }

    public void setCusto(float custo) {
        this.custo = custo;
    }
}
