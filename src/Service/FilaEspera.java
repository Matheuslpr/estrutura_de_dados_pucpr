package Service;

import java.util.LinkedList;
import java.util.Queue;

public class FilaEspera {

    // Atributos
    private String tituloLivro;
    private Queue<String> fila;

    // Construtores
    public FilaEspera() {
    }

    public FilaEspera(String tituloLivro) {
        this.tituloLivro = tituloLivro;
        this.fila = new LinkedList<>();
    }

    // Adiciona um usuário à fila de espera para o livro.
    public void entrarNaFila(String nomeUsuario) {
        fila.offer(nomeUsuario);
        System.out.println("Usuario: " + nomeUsuario + " entrou na fila de espera para: " + tituloLivro);
        System.out.println("Posição na fila: " + fila.size());
    }

    // Remove o próximo usuário da fila e o notifica que o livro está disponível.
    public String notificarProximo() {
        if (fila.isEmpty()) {
            System.out.println("Nenhum usuário na fila de espera para: " + tituloLivro);
            return null;
        }
        String proximo = fila.poll();
        System.out.println(" Notificando " + proximo + ": o livro " + tituloLivro + " está disponível");
        return proximo;
    }

    //Consulta quem está na frente da fila sem removê-lo.

    public String verProximo() {
        return fila.peek();
    }

    // Verifica se a fila de espera está vazia.
    public boolean filaVazia() {
        return fila.isEmpty();
    }

    // Retorna o número de usuários atualmente na fila de espera.
    public int tamanhoFila() {
        return fila.size();
    }

    // Exibe a lista de usuários na fila de espera para o livro.
    public void exibirFila() {
        if (fila.isEmpty()) {
            System.out.println("A fila de espera para " + tituloLivro + " está vazia.");
            return;
        }
        System.out.println("Fila de espera para " + tituloLivro + ":");
        int posicao = 1;
        for (String usuario : fila) {
            System.out.println("   " + posicao + " - " + usuario);
            posicao++;
        }
    }

    // Getters e Setters
    public String getTituloLivro() {
        return tituloLivro;
    }

    // toString
    @Override
    public String toString() {
        return "FilaEspera{" +
                "tituloLivro='" + tituloLivro + '\'' +
                ", fila=" + fila +
                '}';
    }
}
