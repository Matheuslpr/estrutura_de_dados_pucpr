package Controller;

import Model.Livro;

import java.util.*;

public class HistoricoUsuario {

    private final Map<String, Stack<Livro>> historico = new HashMap<>();


    public void visitLivro(String userId, Livro livro) {
        historico.computeIfAbsent(userId, k -> new Stack<>()).push(livro);
    }


    public List<Livro> verHistoricoUs(String userId) {
        return new ArrayList<>(historico.getOrDefault(userId, new Stack<>()));
    }

    public Livro visitadoUltimaVez(String userId) {
        Stack<Livro> stack = historico.get(userId);
        return (stack != null && !stack.isEmpty()) ? stack.pop() : null;
    }
}