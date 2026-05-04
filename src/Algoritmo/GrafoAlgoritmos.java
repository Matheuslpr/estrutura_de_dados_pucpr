package Algoritmo;

import Model.Livro;

import java.util.*;

public final class GrafoAlgoritmos {

    private GrafoAlgoritmos() {
    }

    public static Map<Livro, Integer> dijkstraSimple(HashMap<Livro, Set<Livro>> grafo, Livro origem) {

        Map<Livro, Integer> dist = new HashMap<>();
        Queue<Livro> fila = new LinkedList<>();

        dist.put(origem, 0);
        fila.add(origem);

        while (!fila.isEmpty()) {
            Livro cur = fila.poll();
            int d = dist.get(cur);

            for (Livro n : grafo.getOrDefault(cur, Collections.emptySet())) {
                if (!dist.containsKey(n)) {
                    dist.put(n, d + 1);
                    fila.add(n);
                }
            }
        }
        return dist;
    }

    public static List<Livro> verLivrosProximos(HashMap<Livro, Set<Livro>> grafo, Livro origem, int limit) {

        Map<Livro, Integer> dist = dijkstraSimple(grafo, origem);

        List<Livro> ordered = new ArrayList<>(dist.keySet());
        ordered.sort(
                Comparator.<Livro>comparingInt(dist::get)
                        .thenComparing(Livro::getTitulo, String.CASE_INSENSITIVE_ORDER)
        );

        return (limit > 0 && ordered.size() > limit)
                ? ordered.subList(0, limit)
                : ordered;
    }

}
