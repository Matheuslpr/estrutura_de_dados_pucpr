package Service;

import Model.Livro;

import java.util.HashMap;
import java.util.Map;

public class GerenciadorBiblioteca {

    // Map do título do livro
    private Map<String, FilaEspera> filasDeEspera;

    // Map do nome do usuário
    private Map<String, HistoricoNavegacao> historicos;


    public GerenciadorBiblioteca() {
        this.filasDeEspera = new HashMap<>();
        this.historicos = new HashMap<>();
    }


    //Usuário entra na fila de espera de um livro
    public void entrarFilaEspera(String nomeUsuario, String tituloLivro) {
        filasDeEspera.putIfAbsent(tituloLivro, new FilaEspera(tituloLivro));
        filasDeEspera.get(tituloLivro).entrarNaFila(nomeUsuario);
    }


    // Simula a devolução de um livro e notifica o próximo usuário na fila de espera.
    public void livroDevolvido(String tituloLivro) {
        System.out.println("Livro devolvido: " + tituloLivro);
        FilaEspera fila = filasDeEspera.get(tituloLivro);
        if (fila == null || fila.filaVazia()) {
            System.out.println("Nenhum usuário aguardando este livro.");
        } else {
            fila.notificarProximo();
            System.out.println("Ainda aguardando: " + fila.tamanhoFila() + " usuário");
        }
    }

    //Exibe a fila de espera de um livro.
    public void exibirFilaEspera(String tituloLivro) {
        FilaEspera fila = filasDeEspera.get(tituloLivro);
        if (fila == null) {
            System.out.println("Nenhuma fila cadastrada para: " + tituloLivro );
        } else {
            fila.exibirFila();
        }
    }

  // Simula o usuário visualizando um livro, registrando essa ação no histórico de navegação.
    public void visualizarLivro(String nomeUsuario, String tituloLivro) {
        historicos.putIfAbsent(nomeUsuario, new HistoricoNavegacao(nomeUsuario));
        historicos.get(nomeUsuario).registrarVisualizacao(tituloLivro);
    }

    // Exibe o histórico de navegação de um usuário, listando os livros visualizados
    public void exibirHistorico(String nomeUsuario) {
        HistoricoNavegacao hist = historicos.get(nomeUsuario);
        if (hist == null) {
            System.out.println("Nenhum histórico encontrado para: " + nomeUsuario);
        } else {
            hist.exibirHistorico();
        }
    }

    // Simula o usuário voltando para o livro anterior (como um botão "voltar").

    public String voltarNavegacao(String nomeUsuario) {
        HistoricoNavegacao hist = historicos.get(nomeUsuario);
        if (hist == null) return null;
        return hist.voltarNavegacao();
    }

    //Limpa o histórico de um usuário.

    public void limparHistorico(String nomeUsuario) {
        HistoricoNavegacao hist = historicos.get(nomeUsuario);
        if (hist != null) hist.limparHistorico();
    }
}
