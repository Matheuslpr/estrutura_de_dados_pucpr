package Service;

import java.util.EmptyStackException;
import java.util.Stack;

public class HistoricoNavegacao {


    // Atributos
    private String nomeUsuario;
    private Stack<String> historico;
    private static final int LIMITE_HISTORICO = 50;

    // Construtores

    public HistoricoNavegacao() {
    }
    public HistoricoNavegacao(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
        this.historico = new Stack<>();
    }

    // Registra a visualização de um livro

    public void registrarVisualizacao(String tituloLivro) {
        if (!historico.isEmpty() && historico.peek().equals(tituloLivro)) {
            System.out.println(tituloLivro + "já está no topo do histórico");
            return;
        }
        // Limita o tamanho do histórico
        if (historico.size() >= LIMITE_HISTORICO) {
            historico.remove(0);
        }
        historico.push(tituloLivro);
        System.out.println("Visualização registrada: " + tituloLivro + " por " + nomeUsuario);
    }

    //Retorna o livro mais recentemente visualizado sem remover.

    public String ultimoLivroVisualizado() {
        if (historico.isEmpty()) {
            System.out.println("Histórico de " + nomeUsuario + " está vazio");
            return null;
        }
        return historico.peek();
    }

    // Remove e retorna o livro do topo
    public String voltarNavegacao() {
        if (historico.isEmpty()) {
            System.out.println("Não há navegação anterior para " + nomeUsuario );
            return null;
        }
        try {
            String livro = historico.pop();
            System.out.println("Voltando de: " + livro );
            return livro;
        } catch (EmptyStackException e) {
            return null;
        }
    }

    // Exibe o histórico de navegação do usuário, do mais recente ao mais antigo.
    public void exibirHistorico() {
        if (historico.isEmpty()) {
            System.out.println("O histórico de " + nomeUsuario + " está vazio.");
            return;
        }
        System.out.println("O Histórico de navegação de " + nomeUsuario );
        for (int i = historico.size() - 1; i >= 0; i--) {
            String prefixo = (i == historico.size() - 1) ? "   ▶ " : "     ";
            System.out.println(prefixo + (historico.size() - i) + "º - " + historico.get(i));
        }
    }

    // Limpa todo o histórico de navegação

    public void limparHistorico() {
        historico.clear();
        System.out.println("Histórico de " + nomeUsuario + " limpo.");
    }

    public boolean historicoVazio() {
        return historico.isEmpty();
    }

    public int tamanhoHistorico() {
        return historico.size();
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

}
