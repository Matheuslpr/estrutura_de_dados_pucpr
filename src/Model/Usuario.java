package Model;

public class Usuario {

    // Atributos
    private String nome;
    private String email;
    private String userId;

    // Construtores
    public Usuario() {
    }

    public Usuario(String nome, String email, String userId) {
        this.nome = nome;
        this.email = email;
        this.userId = userId;
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    // Overrides

    @Override
    public String toString() {
        return "Usuario{" +
                "nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}