package Service;

import Algoritmo.GrafoAlgoritmos;
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

    // Dijkstra
    public Map<Livro,Integer> getDistances(Livro origem){
        Map<Livro,Integer> d = GrafoAlgoritmos.dijkstraSimple(
                (HashMap<Livro,Set<Livro>>) recomendacaoGrafo, origem);
        d.remove(origem);
        return d;
    }
    public List<Map.Entry<Livro,Integer>> getDistancesOrdered(Livro origem){
        Map<Livro,Integer> d = getDistances(origem);
        return d.entrySet().stream()
                .sorted(
                        Comparator.comparingInt((Map.Entry<Livro,Integer> e)->e.getValue())
                                .thenComparing((Map.Entry<Livro,Integer> e)->e.getKey().getTitulo(),
                                        String.CASE_INSENSITIVE_ORDER))
                .toList();
    }

    // Exibe distâncias simples
    public void displayDistances(Livro origem){
        var ordered = getDistancesOrdered(origem);
        if(ordered.isEmpty()){
            System.out.println("Nenhum outro livro alcançável a partir de "+origem.getTitulo()+".");
            return;
        }
        System.out.println("Distâncias (arestas) a partir de "+origem.getTitulo()+":");
        ordered.forEach(e->
                System.out.printf("  %d → %s%n", e.getValue(), e.getKey().getTitulo()));
    }

    // Passo‑a‑passo , caminho
    public void displayDistancesVerbose(Livro origem){

        HashMap<Livro,Set<Livro>> g = (HashMap<Livro,Set<Livro>>)recomendacaoGrafo;
        Map<Livro,Integer> dist = new HashMap<>();
        Map<Livro,Livro> pred   = new HashMap<>();
        Queue<Livro> q        = new LinkedList<>();

        dist.put(origem,0); q.add(origem);

        System.out.println("== Início da BFS (Dijkstra não‑ponderado) ==");
        while(!q.isEmpty()){
            Livro cur = q.poll(); int d = dist.get(cur);
            System.out.printf("Visitando %-30s | distância = %d%n",cur.getTitulo(),d);
            for(Livro n:g.getOrDefault(cur,Collections.emptySet())){
                if(!dist.containsKey(n)){
                    dist.put(n,d+1); pred.put(n,cur); q.add(n);
                    System.out.printf("  ↳ Descoberto %-27s | distância = %d%n",n.getTitulo(),d+1);
                }
            }
        }
        System.out.println("== Fim da BFS ==");

        if(dist.size()==1){ System.out.println("Nenhum outro livro alcançável."); return; }

        var ordered = dist.entrySet().stream()
                .filter(e->!e.getKey().equals(origem))
                .sorted(
                        Comparator.comparingInt((Map.Entry<Livro,Integer> e)->e.getValue())
                                .thenComparing((Map.Entry<Livro,Integer> e)->e.getKey().getTitulo(),
                                        String.CASE_INSENSITIVE_ORDER))
                .toList();

        System.out.println("Distância e caminho (origem = "+origem.getTitulo()+"):");
        for(var e:ordered){
            Livro dest=e.getKey(); int dVal=e.getValue();
            List<Livro> path=new ArrayList<>();
            for(Livro at=dest; at!=null; at=pred.get(at)){ path.add(at); if(at.equals(origem)) break; }
            Collections.reverse(path);
            String pStr=path.stream().map(Livro::getTitulo).reduce((a,b)->a+" -> "+b).orElse(dest.getTitulo());
            System.out.printf("  %d  | %s%n",dVal,pStr);
        }
    }
}