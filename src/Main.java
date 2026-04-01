
public class Main {
    public static void main(String[] args) {
        // Criando uma instância da biblioteca
        Biblioteca biblioteca = new Biblioteca();

        // Adicionando livros à biblioteca
        biblioteca.adicionarLivro(new Livros("Dom Casmurro" , "Machado de Assis" , 1899));
        biblioteca.adicionarLivro(new Livros("1984" , "George Orwell" , 1949));

        // Listando os livros da biblioteca
        biblioteca.listarLivros();

        // Removendo um livro da biblioteca
        biblioteca.removerLivro("1984");

        // Listando os livros da biblioteca após a remoção
        System.out.println("-------------------------------");
        biblioteca.listarLivros();

    }
}