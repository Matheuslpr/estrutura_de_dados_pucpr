package Service;

import Controller.HistoricoUsuario;
import Model.Livro;
import Model.Usuario;

import java.util.*;

public class Biblioteca {

    // Atributos
    private List<Livro> livros = new LinkedList<>();
    private final Map<String, Usuario> usuario = new HashMap<>();
    private final FilaEspera   filaEspera   = new FilaEspera();
    private final HistoricoUsuario historicoUsuario = new HistoricoUsuario();
    private final Map<Livro, Set<Livro>> recomendacaoGrafo = new HashMap<>();

    // Metodo para adicionar livro
    public void adicionarLivro(Livro livro) {
        livros.add(livro); }

    // Metodo para exibir livros
    public void displayLivros() { livros.forEach(System.out::println); }

    // Metodo para contar livros
    public int totalLivros() {
        return livros.size(); }

    // Metodo para remover livro
    public boolean removerLivro(Livro livro) {
        return livros.remove(livro); }

    // Metodo para buscar livro por titulo
    public List<Livro> buscarPorTitulo(String titulo) {
        List<Livro> resultado = new ArrayList<>();
        for (Livro l : livros)
            if (l.getTitulo() != null && l.getTitulo().toLowerCase().contains(titulo.toLowerCase()))
                resultado.add(l);
        return resultado;
    }

    // metodo para adicionar usuario
    public void adicionarUser(String nome, String email, String id) {
        if (nome != null && email != null && id != null) usuario.put(id, new Usuario(nome, email, id));
    }

    // metodo para remover usuario
    public void removerUser(String id) { usuario.remove(id); }

    // metodo para buscar usuario por id
    public Optional<Usuario> getUsuario(String id) { return Optional.ofNullable(usuario.get(id)); }

   // metodo para exibir usuarios
    public void displayUsuarios() { usuario.values().forEach(System.out::println); }

    // Grafo de recomendações
    public void addRecommendation(Livro base, Livro rec) {
        recomendacaoGrafo.putIfAbsent(base, new HashSet<>());
        recomendacaoGrafo.putIfAbsent(rec, new HashSet<>());
        recomendacaoGrafo.get(base).add(rec);
    }
    public Set<Livro> getRecommendations(Livro b) { return recomendacaoGrafo.getOrDefault(b, Collections.emptySet()); }

    // Exibe o grafo de recomendações
    public void displayRecommendationGraph() {
        System.out.println("\n--- Grafo de Recomendações ---");
        recomendacaoGrafo.forEach((k, v) -> System.out.println(k + " -> " + v));
    }

    // Getters para fila de espera e histórico de usuário
    public FilaEspera getFilaEspera() { return filaEspera; }
    public HistoricoUsuario getHistoricoUsuario() { return historicoUsuario; }
}