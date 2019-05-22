import java.util.ArrayList;
import java.util.LinkedList;
import java.awt.Color;
import java.util.Collections;
import java.util.Comparator;
import java.util.Objects;
import java.util.Random;

public class Hierholzer {
    static private Graph graph;
    private LinkedList<Integer> eulerianCycle;
    
    static void slow(){
        try {
            Thread.sleep(1000);
        }catch(InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    public static Color getRadomColor(){
        Random rand = new Random();
        float r = rand.nextFloat();
        float g = rand.nextFloat();
        float b = rand.nextFloat();
        return new Color(r, g, b);
    }
    
    private void InsertCycle(LinkedList<Integer> newCycle, int index){
        this.eulerianCycle.addAll(index, newCycle);
    }
    
    private LinkedList<Integer> SearchSimpleCycle(int startVertice){
        LinkedList<Integer> ciclo = new LinkedList<>();      
        LinkedList<Edge> vizinhos;     
        ciclo.addLast(startVertice);
        int ivizinho=0;
        Edge e;
        //repete até que o vertice de destino seja igual ao startVertice
        do{ 
            //Obtem vizinhos da ultimo vertice da lista. Como a cada iteração, eh adicionado
            //um vértice em ciclos, esse codigo passa por todos os valores de ciclo
            vizinhos=graph.getNeighbours(ciclo.getLast());
            if(vizinhos.isEmpty())
            {
                return null;
            }
            //Se quisermos rota de menor peso
           /* Collections.sort(vizinhos, new Comparator<Edge>() {
                @Override
                public int compare(Edge o1, Edge o2) {
                    int e = (int) (o1.getWeight()-o2.getWeight());
                    return e;
                }
            });*/
           //Pegamos preferencialmente o primeiro vizinho dentre os vizinhos
            e = vizinhos.get(ivizinho);
            
            //Se o vertice de destino tem apenas um vizinho, entraremos em um caminho sem volta, pois o seu unico vizinho
            //será o vertice que estamos agora. A menos que ele seja o vertice de parada, não vamos até ele. Dessa forma,
            //vamos para outro vizinho
            
            if(graph.getNeighbours(e.getV()).size()==1 && e.getV() != startVertice){
               ivizinho++;                
            }
            else
            {
                ciclo.addLast(e.getV());
                graph.removeEdge(e);
                ivizinho=0;
            }
        }while(ciclo.getLast()!=startVertice);
        
        slow();
        Color cor = Hierholzer.getRadomColor();
        for(int i = 0; i< ciclo.size();i++){
            graph.markNode(ciclo.get(i), cor);
        }
        
        Hierholzer.CycleToString(ciclo);
        
        return ciclo;
    }
    
    
    //Este código acha o primeiro ciclo partindo do startVertice. Depois, percorre o eulerianCycle, procurando
    //ciclos e adicionando-os em eulerianCycle, por exemplo:
    //eulerianCycle (acha ciclos começando do startVertice [indice j=0]) = 0 -> 1 -> 2 -> 3 -> 0
    //l (achar ciclos partindo de 1 [indice j=1]) = 1 -> 4 -> 7 -> 1
    //eulerianCycle = l+r (conforme explicado abaixo) =  0 -> 1 ->| 4 -> 7 -> 1 ->| 2 -> 3 -> 0 (parte adicionada entre | |)
    //l (achar ciclos partindo do vertice 4 [indice j=2]) : null //j++
    //l (achar ciclos partindo do vertice 7 [indice j=3])  ....
    
    public LinkedList<Integer> SearchEulerianCycle(int startVertice){
        if(!graph.isEulerian()){
            graph.turnEulerian();
        }
        
        //Para mostrar arestas do grafo
        //System.out.println(graph.toString());
        
        //Adiciona o primeiro ciclo dentro de eulerianCycle
        LinkedList<Integer> l = SearchSimpleCycle(startVertice);
        eulerianCycle=l;
        
        //Repete até que não haja mais arestas no grafo
        int j=1;
        while(graph.nEdges!=0){
            l = SearchSimpleCycle(eulerianCycle.get(j));
            if(l==null)
            {
                //Se o daquele vertice não for achado ciclo, vá para próximo vertice
                j++;
            }
            else
            {
                //Se a primeira posição do ciclo achado for igual a do ciclo que já tem,
                //apagamos esse valor, de forma que:
                //0 -> 1 -> 4 -> 0  +  0 -> 2 -> 0  =  0 -> 1 -> 4 -> 0 -> 2 -> 0
                if(Objects.equals(l.getFirst(), eulerianCycle.get(j)))
                {
                    l.pop();
                }
                //insere o ciclo na posição conforme dito acima
                InsertCycle(l, j+1);
            }
        }
        return eulerianCycle;
    }
    
    /*******************************************************************************************/
    
    public static String CycleToString(LinkedList<Integer> cycle){
        String str = "";
        for (Integer integer : cycle) {
            str += integer +" -> ";
        }
        return str.substring(0, str.length()-4);
    }
    
    public void test1(){
        Graph graph = new Graph(5);
        graph.addEdge(0,1,5);
        graph.addEdge(0,2,5);
        graph.addEdge(0,3,5);
        graph.addEdge(0,4,5);
        graph.addEdge(4,1,5);
        graph.addEdge(4,2,5);
        graph.addEdge(4,3,5);
        
        System.out.println("Lista de adjacencia do grafo:");
        System.out.println(graph.toString());
    }
    
    public void test2(){
        Graph graph = new Graph(5);
        graph.addEdge(0,1,5);
        graph.addEdge(0,2,5);
        graph.addEdge(0,3,5);
        graph.addEdge(0,4,5);
        graph.addEdge(4,1,5);
        graph.addEdge(4,2,5);
        graph.addEdge(4,3,5);
        
        
        System.out.println("Lista de adjacencia do grafo ANTES da remocao:");
        System.out.println(graph.toString());
        System.out.println();
        
        graph.removeEdge(new Edge(1,4,5));
        graph.removeEdge(new Edge(0,4,5));
        graph.removeEdge(new Edge(0,2,5));
        
        System.out.println("Lista de adjacencia do grafo DEPOIS da remocao:");
        System.out.println(graph.toString());
    }
    
    public void test3(){
        Graph graph = new Graph(5);
        graph.addEdge(0,1,5);
        graph.addEdge(0,2,5);
        graph.addEdge(0,3,5);
        graph.addEdge(0,4,5);
        graph.addEdge(4,1,5);
        graph.addEdge(4,2,5);
        graph.addEdge(4,3,5);
        
        System.out.println("Vertices vizinhos do vertice 0:");
        for(Edge e : graph.getNeighbours(0)){
            System.out.print(e.getV()+" ");
        }
        System.out.println();
        
        System.out.println("Vertices vizinhos do vertice 2:");
        for(Edge e : graph.getNeighbours(2)){
            System.out.print(e.getV()+" ");
        }
        System.out.println();;
    }
    
    public void test4(){
        Graph graph = new Graph(5);
        graph.addEdge(0,1,5);
        graph.addEdge(0,2,5);
        graph.addEdge(0,3,5);
        graph.addEdge(0,4,5);
        graph.addEdge(4,1,5);
        graph.addEdge(4,2,5);
        graph.addEdge(4,3,5);
        
        System.out.println("Lista de adjacencia do grafo 1:");
        System.out.print(graph.toString());
        System.out.println("Euleriano:" + graph.isEulerian() + "\n");
        
        graph = new Graph(6);
        graph.addEdge(0,1,5);
        graph.addEdge(0,2,5);
        graph.addEdge(0,3,5);
        graph.addEdge(1,4,5);
        graph.addEdge(1,2,5);
        graph.addEdge(2,3,5);
        graph.addEdge(2,5,5);
        graph.addEdge(3,5,5);
        graph.addEdge(4,5,5);
        
        System.out.println("Lista de adjacencia do grafo 2:");
        System.out.print(graph.toString());
        System.out.println("Euleriano: " + graph.isEulerian());
    }
    
    public void test5(int startVertice){
        graph = new Graph(5);
        graph.addEdge(0,1,5);
        graph.addEdge(0,2,5);
        graph.addEdge(0,3,5);
        graph.addEdge(0,4,5);
        graph.addEdge(4,1,5);
        graph.addEdge(4,2,5);
        graph.addEdge(4,3,5);        
        
        LinkedList<Integer> cycle =  SearchSimpleCycle(startVertice);
        System.out.println("Ciclo Simples Encontrado: comecando de " + startVertice);
        System.out.println(CycleToString(cycle));
    }
    
    public void test6(){
        Hierholzer hierholzer = new Hierholzer ();
        //graph = new Graph("grafo1.csv", "white.png", "Roteamento"); // Grafo nao Euleriano
        graph = new Graph("grafo2.csv", "white.png", "Roteamento"); // Grafo Euleriano
        System.out. println ();
        LinkedList<Integer> eulerianCycle = hierholzer.SearchEulerianCycle(0);
        System.out.println("Ciclo␣Final: ");
        System.out.println(Hierholzer.CycleToString(eulerianCycle));
        Graph graph2 = new Graph(graph.getGraphics(), "white.png", "Rota␣Final");
        for(Integer i : eulerianCycle){ slow ();
            slow ();
            Color color = Hierholzer.getRadomColor();
            graph2.markNode(i, color);
        }
    }
    
    public void test7(){
        Hierholzer hierholzer = new Hierholzer();
        // hierholzer.test5(0);
        
        // graph = new Graph("grafo.csv", "white.png", "Roteamento");
        // graph = new Graph("grafo1.csv", "white.png", "Roteamento");
        // graph = new Graph("grafoMapa1.csv", "mapa1.png", "Roteamento");
        graph = new Graph("grafoMapa2.csv", "mapa2.png", "Roteamento");
        
        
        System.out.println();
        LinkedList<Integer> eulerianCycle = hierholzer.SearchEulerianCycle(0);
        System.out.println("Ciclo Final:");
        System.out.println(Hierholzer.CycleToString(eulerianCycle));
        
        // Graph graph2 = new Graph(graph.getGraphics(), "white.png", "Rota Final");
        // Graph graph2 = new Graph(graph.getGraphics(), "mapa1.png", "Rota Final");
        Graph graph2 = new Graph(graph.getGraphics(), "mapa2.png", "Rota Final");
        for(Integer i: eulerianCycle){
            slow();
            Color color = Hierholzer.getRadomColor();
            graph2.markNode(i, color);
        }
    }
    
    public static void main(String[] args) {
        Hierholzer instance = new Hierholzer();
        instance.test7();
    }
}
