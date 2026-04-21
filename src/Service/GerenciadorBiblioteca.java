package Service;

import Model.Livro;
import Model.Usuario;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class GerenciadorBiblioteca {

    // Método para carregar livros
    public static LinkedList<Livro> loadlivros() {
        return new LinkedList<>();
    }

    // Método para salvar livros
    public static void salvarLivros(LinkedList<Livro> livros, Biblioteca biblioteca) {

    }

    // Método para carregar usuários
    public static Map<String, Usuario> loadUsers() {
        return new HashMap<>();
    }

    // Método para salvar usuários
    public static void salvarUsers(Map<String, Usuario> usuarios) {
    }

}