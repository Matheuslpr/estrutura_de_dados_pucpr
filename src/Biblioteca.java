import java.util.LinkedList;

public class Biblioteca {

    private LinkedList<Livros> livros = new LinkedList<>();

    // Metodo para adicionar um livro à biblioteca
    public void adicionarLivro(Livros livro) {
        livros.add(livro); }

    // Metodo para listar os livros da biblioteca
    public void listarLivros() {
        for (Livros livro : livros) {
            System.out.println(livro); }
    }

    // Metodo para remover um livro da biblioteca pelo título
    public boolean removerLivro(String titulo) {
        for (Livros livro : livros) {
            if (livro.getTitulo().equalsIgnoreCase(titulo)) {
                livros.remove(livro);
                return true;
            }
        }
        return false;
    }


}
