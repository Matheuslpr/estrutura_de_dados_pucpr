package Service;
import Model.Livro;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GrafoLivros {

    private HashMap<Livro, Set<Livro>> grafo;

    //construtor
    public GrafoLivros() {
        this.grafo = new HashMap<>();
    }

    // Adiciona um livro sem nenhuma relação ainda
    public void adicionarLivro(Livro livro) {
        if (!grafo.containsKey(livro)) {
            grafo.put(livro, new HashSet<>());
        }
    }

    // Cria relação entre dois livros
    public void adicionarRelacao(Livro a, Livro b) {
        adicionarLivro(a);
        adicionarLivro(b);
        grafo.get(a).add(b);
        grafo.get(b).add(a);
    }

    // Retorna os livros diretamente relacionados
    public Set<Livro> getRelacionados(Livro livro) {
        if (grafo.containsKey(livro)) {
            return grafo.get(livro);
        }
        return new HashSet<>();
    }

    // Sugere livros com base nos livros lidos
    public List<Livro> sugerir(List<Livro> lidos) {
        Map<Livro, Integer> contagem = new HashMap<>();

        for (Livro lido : lidos) {
            Set<Livro> vizinhos = getRelacionados(lido);
            for (Livro vizinho : vizinhos) {
                if (!lidos.contains(vizinho)) {
                    if (contagem.containsKey(vizinho)) {
                        contagem.put(vizinho, contagem.get(vizinho) + 1);
                    } else {
                        contagem.put(vizinho, 1);
                    }
                }
            }
        }

        // Ordena por contagem decrescente (sem streams: insertion sort simples)
        List<Livro> sugestoes = new ArrayList<>(contagem.keySet());
        for (int i = 1; i < sugestoes.size(); i++) {
            Livro chave = sugestoes.get(i);
            int j = i - 1;
            while (j >= 0 && contagem.get(sugestoes.get(j)) < contagem.get(chave)) {
                sugestoes.set(j + 1, sugestoes.get(j));
                j--;
            }
            sugestoes.set(j + 1, chave);
        }

        return sugestoes;
    }
    public Livro buscarPorTitulo(String titulo) {
        for (Livro l : grafo.keySet()) {
            if (l.getTitulo().equals(titulo)) {
                return l;
            }
        }
        return null;
    }
    // Exibe o grafo completo no console
    public void exibirGrafo() {
        System.out.println("=== GRAFO DE LIVROS ===");
        for (Map.Entry<Livro, Set<Livro>> entrada : grafo.entrySet()) {
            System.out.println("\n" + entrada.getKey());
            System.out.println("  Relacionados:");
            for (Livro rel : entrada.getValue()) {
                System.out.println("    -> " + rel);
            }
        }
    }
}