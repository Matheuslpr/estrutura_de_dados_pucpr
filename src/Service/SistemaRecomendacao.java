package Service;

import Model.Livro;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SistemaRecomendacao {

    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();

        Livro b1 = new Livro ("1984", "George Orwell", 1949);
        Livro b2 = new Livro("Admirável Mundo Novo", "Aldous Huxley", 1932);
        Livro b3 = new Livro("Fahrenheit 451", "Ray Bradbury", 1953);
        Livro b4 = new Livro("O Conto da Aia", "Margaret Atwood", 1985);
        Livro b5 = new Livro("Laranja Mecânica", "Anthony Burgess", 1962);
        Livro b6 = new Livro("A Revolução dos Bichos", "George Orwell", 1945);
        Livro b7 = new Livro("Neuromancer", "William Gibson", 1984);
        Livro b8 = new Livro("Duna", "Frank Herbert", 1965);
        Livro b9 = new Livro("Fundação", "Isaac Asimov", 1951);
        Livro b10 = new Livro("2001: Uma Odisseia no Espaço", "Arthur C. Clarke", 1968);

        List<Livro> todosLivros = List.of(b1, b2, b3, b4, b5, b6, b7, b8, b9, b10);
        todosLivros.forEach(b -> {
            b.updateLivroHash();
            biblioteca.adicionarLivro(b);
        });

        biblioteca.addRecommendation(b1, b2);
        biblioteca.addRecommendation(b1, b3);
        biblioteca.addRecommendation(b2, b1);
        biblioteca.addRecommendation(b2, b4);
        biblioteca.addRecommendation(b3, b5);
        biblioteca.addRecommendation(b3, b6);
        biblioteca.addRecommendation(b4, b1);
        biblioteca.addRecommendation(b4, b7);
        biblioteca.addRecommendation(b5, b3);
        biblioteca.addRecommendation(b5, b8);
        biblioteca.addRecommendation(b6, b2);
        biblioteca.addRecommendation(b6, b9);
        biblioteca.addRecommendation(b7, b10);
        biblioteca.addRecommendation(b7, b6);
        biblioteca.addRecommendation(b8, b9);
        biblioteca.addRecommendation(b8, b10);
        biblioteca.addRecommendation(b9, b4);
        biblioteca.addRecommendation(b9, b5);
        biblioteca.addRecommendation(b10, b1);
        biblioteca.addRecommendation(b10, b2);

        Livro leituraAnterior = b1;
        Set<Livro> recomendacoes = biblioteca.getRecommendations(leituraAnterior);

        System.out.println("\nUsuário leu: " + leituraAnterior.getTitulo());
        System.out.println("Recomendações com base nessa leitura:");
        recomendacoes.forEach(b ->
                System.out.println(" - " + b.getTitulo() + " (" + b.getAutor() + ")")
        );
    }
}