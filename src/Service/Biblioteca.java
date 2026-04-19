package Service;

import Model.Livro;

import java.util.LinkedList;

public class Biblioteca {

    private LinkedList<Livro> livros = new LinkedList<>();

    // Metodo para adicionar um livro à biblioteca
    public void adicionarLivro(Livro livro) {
        livros.add(livro); }

    // Metodo para listar os livros da biblioteca
    public void listarLivros() {
        for (Livro livro : livros) {
            System.out.println(livro); }
    }

    // Metodo para remover um livro da biblioteca pelo título
    public boolean removerLivro(String titulo) {
        for (Livro livro : livros) {
            if (livro.getTitulo().equalsIgnoreCase(titulo)) {
                livros.remove(livro);
                return true;
            }
        }
        return false;
    }


}
