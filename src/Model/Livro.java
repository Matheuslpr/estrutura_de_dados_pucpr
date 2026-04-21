package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Livro {

    // Atributos
    private String titulo;
    private String autor;
    private int anoPublicacao;
    private int  LivroHash;

    private List<Integer> recomendacoes = new ArrayList<>();

    // Construtores

    public Livro() { updateLivroHash(); }

    public Livro(String titulo) {
        this.titulo = titulo;
        updateLivroHash(); }

    public Livro(String titulo, String autor)   {
        this(titulo);
        this.autor = autor; }

    public Livro(String titulo, String autor, int anoPublicacao) {
        this(titulo, autor);
        this.anoPublicacao = anoPublicacao;
    }



    public void updateLivroHash() { this.LivroHash = hashCode(); }
    private int generateCopyHash() {
        return Objects.hash(titulo, autor, anoPublicacao, System.nanoTime());
    }
    // Getters e Setters
    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getAnoPublicacao() {
        return anoPublicacao;
    }

    public void setAnoPublicacao(int anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }


    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getLivroHash() {
        return LivroHash;
    }

    public void setLivroHash(int livroHash) {
        LivroHash = livroHash;
    }

    public List<Integer> getRecomendacoes() {
        return recomendacoes;
    }

    public void setRecomendacoes(List<Integer> recomendacoes) {
        this.recomendacoes = recomendacoes;
    }

    // overrides
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Livro b)) return false;
        return anoPublicacao == b.anoPublicacao &&
                Objects.equals(titulo, b.titulo) &&
                Objects.equals(autor, b.autor);
    }
    @Override
    public int hashCode() { return Objects.hash(titulo, autor, anoPublicacao); }

    @Override
    public String toString() {
        return "Book{" + titulo + " (" + autor + ", " + anoPublicacao + ")}";
    }
}
