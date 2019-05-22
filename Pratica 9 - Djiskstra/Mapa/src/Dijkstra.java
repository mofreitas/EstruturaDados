import java.util.ArrayList;
import java.util.PriorityQueue;

// Algoritmo de Dijkstra
public class Dijkstra {

    final Graph g;
    final int n;
    final int source;
    final int dest;
    Fenetre f;
    int dist[];
    int pred[];
    PriorityQueue<Node> naoacomodados;
    boolean settled[];

    // construtor
    Dijkstra(Graph g, int source, int dest) {
        this.g = g;
        n = g.n;
        this.source = source;
        this.dest = dest;
        dist = new int[g.n];
        pred = new int[g.n];
        settled = new boolean[g.n];
        naoacomodados = new PriorityQueue<>();
        naoacomodados.add(new Node(source, 0));
        

        for (int i = 0; i < g.n; i++) {
            settled[i] = false;
            if (i == source) {
                dist[i] = 0;
                pred[i] = source;
            } else {
                dist[i] = Integer.MAX_VALUE;
                pred[i] = -1;
            }
        }
    }

	// atualizacao da distancia, da prioridade, e do predecessor de um no
    void update(int v, int x) {
        if (dist[v] > (g.value(x, v) + dist[x])) {
            dist[v] = g.value(x, v) + dist[x];
            pred[v] = x;
            naoacomodados.add(new Node(v, g.value(x, v) + dist[x]));
        }
    }

    // retorna o próximo nó a ser acomodado
    int nextNode() {
        while (!naoacomodados.isEmpty()) {
            Node d = naoacomodados.poll();
            if (!settled[d.id]) {
                return d.id;
            }
        }
        return (-1);
    }

    // uma etapa do algoritmo de Dijkstra

    int oneStep() {
        int next_n = nextNode();
        if (next_n != -1) {
            settled[next_n] = true;
            ArrayList<Integer> suc_next_n = g.successors(next_n);
            for (Integer i : suc_next_n) {
                update(i, next_n);
            }
            return next_n;
        }
        return (-1);
    }

    // algoritmo de Dijsktra completo
    int compute() {
        while (!settled[dest] && !naoacomodados.isEmpty()) {
            if (oneStep() == dest) {
                return dist[dest];
            }
        }

        return -1;
    }

    // desacelera o visualizador
    void slow() {
        if (f == null) {
            return;
        }
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
        }
    }

    public static void main(String[] args) {

    }
}

