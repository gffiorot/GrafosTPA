package Grafos;

public class Main {
    public static void main(String[] args) {
        Grafo<String> mapa = new Grafo<>();
        System.out.println("### Teste de Estrutura de Grafo e Adição ###");

        System.out.println("Adicionando arestas. Os vértices A, B, C, D e E serão criados automaticamente:");

        mapa.addVertice("A");

        mapa.addAresta("A", "B", 1.0f);
        mapa.addAresta("A", "C", 2.0f);
        mapa.addAresta("B", "D", 3.0f);
        mapa.addAresta("C", "E", 4.0f);
        mapa.addAresta("D", "E", 5.0f);
        mapa.addAresta("E", "B", 6.0f);

        mapa.addVertice("B");

        System.out.println("Adicionando vértices 'F' e 'G' (desconectados do restante).");
        mapa.addVertice("F");
        mapa.addVertice("G");

        System.out.println("O grafo agora tem 7 vértices e 6 arestas.");

        System.out.println("\n--------------------------------------------------");

        System.out.println(" Executando Busca em Largura a partir do primeiro vértice 'A'");

        // A ordem de visitação esperada  é: A, B, C, D, E.
        // (F e G não são alcançados)
        mapa.buscaEmLargura();

        System.out.println("\n--------------------------------------------------");
        mapa.caminhoMaisCurto("A","E");
        System.out.println("\n--------------------------------------------------");
        mapa.arvoreMinima();

        System.out.println("Teste concluído com sucesso!");

        Menu menu = new Menu();
        menu.menu(mapa);

    }
}
