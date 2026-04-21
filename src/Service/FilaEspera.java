package Service;

import Model.Usuario;

import java.util.*;

public class FilaEspera {

    //atributo
    private final Map<Integer, Queue<Usuario>> listaEspera = new HashMap<>();

    //metodo para adicionar usuario a fila de espera
    public void adicionarFila(int bookHash, Usuario user) {
        listaEspera.computeIfAbsent(bookHash, k -> new LinkedList<>()).add(user);
    }

    //metodo para remover usuario da fila de espera
    public Usuario proximoFila(int bookHash) {
        Queue<Usuario> queue = listaEspera.get(bookHash);
        return (queue != null && !queue.isEmpty()) ? queue.poll() : null;
    }

    //metodo para ver a fila de espera
    public List<Usuario> verFila(int bookHash) {
        return new ArrayList<>(listaEspera.getOrDefault(bookHash, new LinkedList<>()));
    }
}
