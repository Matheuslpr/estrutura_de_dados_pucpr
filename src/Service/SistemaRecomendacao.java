package Service;

import Model.Livro;

import java.util.ArrayList;
import java.util.List;

public class SistemaRecomendacao {

    private GrafoLivros grafo;

    public SistemaRecomendacao() {
        this.grafo = new GrafoLivros();
        popularGrafo();
    }

    // Monta o grafo com 12 livros e suas relações
    private void popularGrafo() {

        Livro cleanCode    = new Livro("Clean Code",                       "Robert C. Martin", "Tecnologia");
        Livro cleanArch    = new Livro("Clean Architecture",               "Robert C. Martin", "Tecnologia");
        Livro pragProg     = new Livro("The Pragmatic Programmer",         "Hunt & Thomas",    "Tecnologia");
        Livro refactoring  = new Livro("Refactoring",                      "Martin Fowler",    "Tecnologia");
        Livro codComplete  = new Livro("Code Complete",                    "Steve McConnell",  "Tecnologia");
        Livro designPatt   = new Livro("Design Patterns",                  "Gang of Four",     "Arquitetura");
        Livro ddd          = new Livro("Domain-Driven Design",             "Eric Evans",       "Arquitetura");
        Livro sysDesign    = new Livro("Designing Data-Intensive Apps",    "M. Kleppmann",     "Arquitetura");
        Livro algorithms   = new Livro("Introduction to Algorithms",       "Cormen et al.",    "Algoritmos");
        Livro sicp         = new Livro("Structure & Interpretation of CP", "Abelson",          "Algoritmos");
        Livro mythMan      = new Livro("The Mythical Man-Month",           "Brooks",           "Engenharia");
        Livro legacyCode   = new Livro("Working with Legacy Code",         "M. Feathers",      "Tecnologia");

        // Cada livro tem pelo menos 2 relações
        grafo.adicionarRelacao(cleanCode,   refactoring);
        grafo.adicionarRelacao(cleanCode,   pragProg);
        grafo.adicionarRelacao(cleanCode,   codComplete);
        grafo.adicionarRelacao(cleanCode,   cleanArch);

        grafo.adicionarRelacao(cleanArch,   designPatt);
        grafo.adicionarRelacao(cleanArch,   ddd);
        grafo.adicionarRelacao(cleanArch,   sysDesign);

        grafo.adicionarRelacao(pragProg,    codComplete);
        grafo.adicionarRelacao(pragProg,    mythMan);

        grafo.adicionarRelacao(refactoring, legacyCode);
        grafo.adicionarRelacao(refactoring, designPatt);
        grafo.adicionarRelacao(refactoring, codComplete);

        grafo.adicionarRelacao(designPatt,  ddd);
        grafo.adicionarRelacao(designPatt,  legacyCode);

        grafo.adicionarRelacao(ddd,         sysDesign);

        grafo.adicionarRelacao(algorithms,  sicp);
        grafo.adicionarRelacao(algorithms,  sysDesign);

        grafo.adicionarRelacao(mythMan,     codComplete);

        grafo.adicionarRelacao(sicp,        codComplete);
    }

    // Recebe títulos lidos e imprime sugestões
    public void recomendar(String nomeUsuario, String... titulosLidos) {
        List<Livro> lidos = new ArrayList<>();

        // Busca os objetos Livro no grafo pelos títulos informados
        for (String titulo : titulosLidos) {
            for (Livro l : grafo.getRelacionados(buscarPorTitulo(titulo))) {
                // só precisamos do objeto — buscarPorTitulo já faz o trabalho
            }
            Livro encontrado = buscarPorTitulo(titulo);
            if (encontrado != null) {
                lidos.add(encontrado);
            }
        }

        System.out.println("\n=== RECOMENDACOES PARA: " + nomeUsuario + " ===");
        System.out.println("Livros lidos:");
        for (Livro l : lidos) {
            System.out.println("  [x] " + l);
        }

        List<Livro> sugestoes = grafo.sugerir(lidos);

        if (sugestoes.isEmpty()) {
            System.out.println("Nenhuma sugestao disponivel.");
        } else {
            System.out.println("Sugestoes:");
            int rank = 1;
            for (Livro s : sugestoes) {
                System.out.println("  " + rank + ". " + s);
                rank++;
            }
        }
    }

    // Percorre todos os nos do grafo e retorna o livro com o titulo informado
    private Livro buscarPorTitulo(String titulo) {
        for (Livro l : grafo.getRelacionados(new Livro("", "", ""))) {
            // getRelacionados de livro vazio retorna vazio; precisamos iterar o grafo todo
        }
        // Acesso direto ao grafo via método auxiliar
        return grafo.buscarPorTitulo(titulo);
    }

    public GrafoLivros getGrafo() {
        return grafo;
    }
}