package Grafos;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.HashSet;
import java.util.Set;
import java.util.HashMap;
import java.util.Map;
import java.util.Collections;

public class Grafo<T> {
    private ArrayList<Vertice<T>> vertices;

    public Grafo() {
        this.vertices = new ArrayList<>();
    }

    public Vertice<T> addVertice(T valor) {
        Vertice<T> test = getVertice(valor);
        if (test != null) {
            System.out.println("Vertice "+valor+" já existe");
            return test;
        }

        Vertice<T> novo = new Vertice<T>(valor);
        this.vertices.add(novo);
        return novo;
    }

    private Vertice<T> getVertice(T valor) {
        // Para cada Vertice v na lista de vertices checa o valor
        for (Vertice<T> v : this.vertices) {
            if (v.getValor().equals(valor))
                return v;
        }
        return null;
    }

    public void addAresta(T origem, T destino, float peso) {
        Vertice<T> vertOrigem, vertDestino;

        vertOrigem = getVertice(origem);
        if (vertOrigem == null) {
            vertOrigem = addVertice(origem);
        }

        vertDestino = getVertice(destino);
        if (vertDestino == null) {
            vertDestino = addVertice(destino);
        }

        vertOrigem.addDestino(new Aresta(vertDestino, peso));

    }

    public void buscaEmLargura() {
        ArrayList<Vertice<T>> marcados = new ArrayList<Vertice<T>>();
        ArrayList<Vertice<T>> fila = new ArrayList<Vertice<T>>();

        Vertice<T> atual = this.vertices.get(0);

        fila.add(atual);
        System.out.println("Busca em Largura a partir do vertice: " + atual.getValor());

        while (!fila.isEmpty()) {
            atual = fila.get(0);
            fila.remove(0);
            marcados.add(atual);
            System.out.println(atual.getValor());

            ArrayList<Aresta> destinos = atual.getDestinos();
            Vertice<T> proximo;

            // Para cada Aresta dAtual em destinos pega o proximo no 'caminho' de destinos
            for (Aresta dAtual : destinos) {
                proximo = dAtual.getDestino();
                if (!marcados.contains(proximo) && !fila.contains(proximo)) {
                    fila.add(proximo);
                }
            }
        }
    }

    // ================== PARTE 2 ==================


    public void caminhoMaisCurto(T origem, T destino) {
        Vertice<T> vertOrigem = getVertice(origem);
        Vertice<T> vertDestino = getVertice(destino);
        if (vertOrigem == null || vertDestino == null) {
            System.out.println("Uma das cidades fornecidas não está no mapa");
            return;
        }

        Map<Vertice<T>, Float> distancias = new HashMap<>();
        Map<Vertice<T>, Vertice<T>> pais = new HashMap<>();
        PriorityQueue<NoPrioritario<T>> fila = new PriorityQueue<>();

        // Inicia todas as distâncias como infinito (ou um valor alto)
        for (Vertice<T> v : this.vertices) {
            distancias.put(v, Float.MAX_VALUE);
            pais.put(v, null);
        }

        distancias.put(vertOrigem, 0f);
        fila.add(new NoPrioritario<>(vertOrigem,null,0));

        while (!fila.isEmpty()) {
            NoPrioritario<T> atualInfo = fila.poll();
            Vertice<T> atual = atualInfo.getVertice();
            float distanciaAtual = atualInfo.getCusto();

            // Se a distância na fila for maior do que a distância já registrada (encontramos um caminho melhor antes), ignore.
            if (distanciaAtual > distancias.get(atual)) {
                continue;
            }

            // Chegou ao destino
            if (atual.equals(vertDestino)) {
                break;
            }

            for (Aresta aresta : atual.getDestinos()) {
                Vertice<T> vizinho = aresta.getDestino();
                float pesoAresta = aresta.getPeso();

                float novaDistancia = distancias.get(atual)+pesoAresta;

                if (novaDistancia < distancias.get(vizinho)) {
                    distancias.put(vizinho, novaDistancia);
                    pais.put(vizinho, atual);
                    fila.add(new NoPrioritario<>(vizinho,atual,novaDistancia));
                }
            }

        }
        System.out.println("===== Caminho mais curto =====");

        if (distancias.get(vertDestino) == Float.MAX_VALUE) {
            System.out.printf("Não foi possível encontrar um caminho entre %s e %s.\n", origem, destino);
        } else {
            ArrayList<T> caminho = new ArrayList<>();
            Vertice<T> passo = vertDestino;

            while (passo != null) {
                caminho.add(passo.getValor());
                passo = pais.get(passo);
            }

            Collections.reverse(caminho);

            System.out.printf("A rota mais curta de "+origem+ " para "+destino+" é:\n");
            for (int i=0; i<caminho.size(); i++) {
                System.out.print(caminho.get(i));
                if (i<caminho.size()-1) {
                    System.out.print(" -> ");
                }
            }
            System.out.println();
            System.out.printf("Custo Total: %.2f\n", distancias.get(vertDestino));
        }

    }

    // Algoritmo de Prim
    public void arvoreMinima() {
        if (this.vertices.size() < 2) {
            System.out.println("O mapa não tem cidades o suficiente prara fazer a infraestrutura mínima");
            return;
        }
        Set<Vertice<T>> visitados = new HashSet<>();

        PriorityQueue<NoPrioritario<T>> fila = new PriorityQueue<>();

        Vertice<T> inicial = this.vertices.get(0);
        fila.add(new NoPrioritario<>(inicial,null, 0));

        float cTotal = 0;
        System.out.println("== Planejamento de infraestrutura mínima ==");
        System.out.println("Conexões a serem construidas:");

        while (!fila.isEmpty()) {
            NoPrioritario<T> atualInfo = fila.poll();
            Vertice<T> atual = atualInfo.getVertice();

            if (visitados.contains(atual)) continue;

            visitados.add(atual);
            cTotal += atualInfo.getCusto();

            // Se o custo for > 0, significa que usamos uma aresta para chegar aqui
            if (atualInfo.getCusto() >0) {
                System.out.println("- Conectar: " + atualInfo.getOrigem().getValor() +"<->"+atual.getValor()+ " | Custo do cabo: " + atualInfo.getCusto());
            }

            for (Aresta aresta : atual.getDestinos()) {
                Vertice<T> vizinho = aresta.getDestino();

                if (!visitados.contains(vizinho)) {
                    fila.add(new NoPrioritario<>(vizinho,atual, aresta.getPeso()));
                }
            }
        }
        System.out.println("\nCusto total de conexão da obra: " + cTotal);
        System.out.println("--------------------------------------------------");

    }



}
