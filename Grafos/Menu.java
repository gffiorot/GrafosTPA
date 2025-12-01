package Grafos;
import java.util.Comparator;
import java.util.Scanner;
public class Menu {
    Grafo<String> mapa = new Grafo<>();
    Scanner input = new Scanner(System.in);
    void menu() {

        int opcao;
        boolean roda = true;
        while (roda) {
            String msg = "*********************\n" +
                    "Escolha uma opção\n" +
                    "1) adicionar Vertice\n" +
                    "2) Adicionar Aresta\n" +
                    "3) caminho mínimo (Dijkstra)\n"+
                    "4) árvore geradora mínima (Prim)\n"+
                    "0) Sair";
            System.out.println(msg);
            opcao = lerInt();
            switch (opcao){
                case 1:{
                    System.out.println("Insira o Vertice que voce quer adicionar");
                    String valor = this.lerLinha();
                    mapa.addVertice(valor);
                    break;
                }
                case 2:{
                    String origem, destino ;
                    float peso;
                    System.out.println("Insira o Vertice de origem(caso ele nao exista, ele sera adicionado)");
                    origem = this.lerLinha();
                    System.out.println("Insira o Vertice de destino (caso ele nao exista, ele sera adicionado)");
                    destino = this.lerLinha();
                    System.out.println("Insira o Peso da aresta");
                    peso = Float.parseFloat(lerLinha());
                    mapa.addAresta(origem,destino,peso);
                    break;
                }
                case 3:{
                    String origem, destino ;
                    System.out.println("Insira o Vertice de origem");
                    origem = this.lerLinha();
                    System.out.println("Insira o Vertice de destino");
                    destino = this.lerLinha();
                    mapa.caminhoMaisCurto(origem, destino);
                    break;
                }
                case 4:{
                    mapa.arvoreMinima();
                    break;
                }
                case 0:{
                    roda = false;
                }
            }
        }
    }
    private  int lerInt() {
        String linha = lerLinha();

        return Integer.parseInt(linha);
    }

    private  String lerLinha() {
        return input.nextLine();
    }
}
