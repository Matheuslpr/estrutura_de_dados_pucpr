package Controller;

import Model.Livro;
import Service.Biblioteca;
import Service.GerenciadorBiblioteca;

public class Main {
    public static void main(String[] args) {
        // Criando uma instância da biblioteca
        Biblioteca biblioteca = new Biblioteca();

        // Criando uma instância do gerenciador de biblioteca
        GerenciadorBiblioteca gerenciador = new GerenciadorBiblioteca();

        // Adicionando livros à biblioteca
        biblioteca.adicionarLivro(new Livro("Dom Casmurro" , "Machado de Assis" , 1899));
        biblioteca.adicionarLivro(new Livro("1984" , "George Orwell" , 1949));

        // Listando os livros da biblioteca
        biblioteca.listarLivros();

        // Removendo um livro da biblioteca
        biblioteca.removerLivro("1984");

        // Listando os livros da biblioteca após a remoção
        System.out.println("-------------------------------");
        biblioteca.listarLivros();

        // ── Teste: Histórico de Navegação ──
        System.out.println("── HISTÓRICO DE NAVEGAÇÃO ──");
        gerenciador.visualizarLivro("Ana", "Estruturas de Dados em Java");
        gerenciador.visualizarLivro("Ana", "Clean Code");
        gerenciador.visualizarLivro("Ana", "Design Patterns");
        gerenciador.visualizarLivro("Ana", "Clean Code"); // não duplica consecutivo
        gerenciador.visualizarLivro("Ana", "Algoritmos - Sedgewick");

        System.out.println();
        gerenciador.exibirHistorico("Ana");

        System.out.println();
        gerenciador.voltarNavegacao("Ana"); // volta para Design Patterns
        gerenciador.exibirHistorico("Ana");

        // ── Teste: Fila de Espera ──
        System.out.println("\n── FILA DE ESPERA ──");
        gerenciador.entrarFilaEspera("Carlos",  "Clean Code");
        gerenciador.entrarFilaEspera("Beatriz", "Clean Code");
        gerenciador.entrarFilaEspera("Diego",   "Clean Code");

        System.out.println();
        gerenciador.exibirFilaEspera("Clean Code");

        System.out.println();
        gerenciador.livroDevolvido("Clean Code"); // notifica Carlos
        gerenciador.exibirFilaEspera("Clean Code");

        System.out.println();
        gerenciador.livroDevolvido("Clean Code"); // notifica Beatriz
        gerenciador.livroDevolvido("Clean Code"); // notifica Diego
        gerenciador.livroDevolvido("Clean Code"); // fila vazia



    }
}
