package Controller;


import Model.Livro;
import Model.Usuario;
import Service.Biblioteca;
import Service.GerenciadorBiblioteca;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();

        LinkedList<Livro> livros = GerenciadorBiblioteca.loadlivros();
        Map<String, Usuario> usuarios = GerenciadorBiblioteca.loadUsers();

        livros.forEach(livro -> { livro.updateLivroHash(); biblioteca.adicionarLivro(livro); });
        usuarios.values().forEach(u -> biblioteca.adicionarUser(u.getNome(), u.getEmail(), u.getUserId()));

        if (biblioteca.totalLivros() == 0) {
            Livro[] todos = {
                    new Livro("1984", "George Orwell", 1949),
                    new Livro("O Senhor dos Anéis", "Tolkien"),
                    new Livro("Dom Casmurro", "Machado de Assis", 1899),
                    new Livro("Cem Anos de Solidão", "Gabriel García Márquez"),
                    new Livro("O Pequeno Príncipe", "Antoine de Saint-Exupéry", 1943),
                    new Livro("A Revolução dos Bichos", "George Orwell", 1945),
                    new Livro("Crime e Castigo", "Fiódor Dostoiévski"),
                    new Livro("O Hobbit", "J.R.R. Tolkien", 1937),
                    new Livro("O Apanhador no Campo de Centeio", "J.D. Salinger"),
                    new Livro("Moby Dick", "Herman Melville", 1851)
            };
            for (Livro b : todos) { b.updateLivroHash(); biblioteca.adicionarLivro(b); livros.add(b); }
            GerenciadorBiblioteca.salvarLivros(livros, biblioteca);
        }

        if (biblioteca.getUsuario("teste1").isEmpty() && biblioteca.getUsuario("teste2").isEmpty()) {
            Usuario u1 = new Usuario("Usuário Teste 1", "teste1@email.com", "teste1");
            Usuario u2 = new Usuario("Usuário Teste 2", "teste2@email.com", "teste2");
            biblioteca.adicionarUser(u1.getNome(), u1.getEmail(), u1.getUserId());
            biblioteca.adicionarUser(u2.getNome(), u2.getEmail(), u2.getUserId());
            usuarios.put(u1.getUserId(), u1);
            usuarios.put(u2.getUserId(), u2);
            GerenciadorBiblioteca.salvarUsers(usuarios);
        }

        Scanner scanner = new Scanner(System.in);
        int option;

        do {
            System.out.println("\n--- Biblioteca Virtual ---");
            System.out.println("1. Adicionar livro");
            System.out.println("2. Listar livros");
            System.out.println("3. Buscar livro por título");
            System.out.println("4. Adicionar usuário");
            System.out.println("5. Listar usuários");
            System.out.println("6. Buscar usuário por ID");
            System.out.println("7. Atualizar dados de livro");
            System.out.println("8. Atualizar dados de usuário");
            System.out.println("9. Remover livro");
            System.out.println("10. Remover usuário");
            System.out.println("11. Ver hashCode de um livro");
            System.out.println("12. Salvar tudo");
            System.out.println("13. Adicionar usuário à fila de espera");
            System.out.println("14. Visualizar fila de espera de um livro");
            System.out.println("15. Exibir histórico de um usuário");
            System.out.println("16. Exibir recomendações para um livro");
            System.out.println("17. Mostrar grafo completo");
            System.out.println("0. Fechar");
            System.out.print("Opção: ");

            String input = scanner.nextLine().trim();
            try { option = input.isBlank() ? -1 : Integer.parseInt(input); }
            catch (NumberFormatException e) { option = -1; }

            switch (option) {
                case 1 -> {
                    System.out.print("Título: "); String titulo = scanner.nextLine();
                    System.out.print("Autor: "); String autor = scanner.nextLine();
                    System.out.print("Ano: "); int ano = Integer.parseInt(scanner.nextLine());
                    Livro livro = new Livro(titulo, autor, ano);
                    livro.updateLivroHash();
                    biblioteca.adicionarLivro(livro);
                    livros.add(livro);
                    GerenciadorBiblioteca.salvarLivros(livros, biblioteca);
                    System.out.println("Livro adicionado.");
                }
                case 2 -> biblioteca.displayLivros();
                case 3 -> {
                    System.out.print("Título (ou parte): "); String s = scanner.nextLine();
                    biblioteca.buscarPorTitulo(s).forEach(System.out::println);
                }
                case 4 -> {
                    System.out.print("Nome: "); String nome = scanner.nextLine();
                    System.out.print("Email: "); String email = scanner.nextLine();
                    System.out.print("ID: "); String id = scanner.nextLine();
                    Usuario u = new Usuario(nome, email, id);
                    biblioteca.adicionarUser(nome, email, id);
                    usuarios.put(id, u);
                    GerenciadorBiblioteca.salvarUsers(usuarios);
                    System.out.println("Usuário adicionado.");
                }
                case 5 -> biblioteca.displayUsuarios();
                case 6-> {
                    System.out.print("ID: "); String id = scanner.nextLine();
                    biblioteca.getUsuario(id).ifPresentOrElse(System.out::println,
                            () -> System.out.println("Usuário não encontrado."));
                }
                case 7 -> {
                    System.out.print("Título do livro: "); String title = scanner.nextLine();
                    for (Livro livro : biblioteca.buscarPorTitulo(title)) {
                        System.out.print("Novo título (ou Enter): "); String t = scanner.nextLine();
                        if (!t.isBlank()) livro.setTitulo(t);
                        System.out.print("Novo autor (ou Enter): "); String a = scanner.nextLine();
                        if (!a.isBlank()) livro.setAutor(a);
                        System.out.print("Novo ano (ou Enter): "); String y = scanner.nextLine();
                        if (!y.isBlank()) livro.setAnoPublicacao(Integer.parseInt(y));
                        livro.updateLivroHash();
                    }
                    GerenciadorBiblioteca.salvarLivros(livros, biblioteca);
                }
                case 8 -> {
                    System.out.print("ID do usuário: "); String id = scanner.nextLine();
                    biblioteca.getUsuario(id).ifPresent(user -> {
                        System.out.print("Novo nome (ou Enter): "); String n = scanner.nextLine();
                        if (!n.isBlank()) user.setNome(n);
                        System.out.print("Novo email (ou Enter): "); String e = scanner.nextLine();
                        if (!e.isBlank()) user.setEmail(e);
                    });
                    GerenciadorBiblioteca.salvarUsers(usuarios);
                }
                case 9 -> {
                    System.out.print("Título: "); String title = scanner.nextLine();
                    List<Livro> toRemove = biblioteca.buscarPorTitulo(title);
                    toRemove.forEach(biblioteca::removerLivro);
                    livros.removeAll(toRemove);
                    GerenciadorBiblioteca.salvarLivros(livros, biblioteca);
                }
                case 10 -> {
                    System.out.print("ID: "); String id = scanner.nextLine();
                    biblioteca.removerUser(id);
                    usuarios.remove(id);
                    GerenciadorBiblioteca.salvarUsers(usuarios);
                }
                case 11 -> {
                    System.out.print("Título: "); String title = scanner.nextLine();
                    biblioteca.buscarPorTitulo(title)
                            .forEach(b -> System.out.println("Hash: " + b.getLivroHash()));
                }
                case 12 -> {
                    GerenciadorBiblioteca.salvarLivros(livros, biblioteca);
                    GerenciadorBiblioteca.salvarUsers(usuarios);
                    System.out.println("Tudo salvo.");
                }
                case 13 -> {
                    System.out.print("ID do usuário: "); String userId = scanner.nextLine();
                    var maybeUser = biblioteca.getUsuario(userId);
                    if (maybeUser.isEmpty()) { System.out.println("Usuário não encontrado."); break; }
                    System.out.print("Título do livro: "); String title = scanner.nextLine();
                    List<Livro> encontrados = biblioteca.buscarPorTitulo(title);
                    if (encontrados.isEmpty()) { System.out.println("Livro não encontrado."); }
                    else {
                        Livro livro = encontrados.get(0);
                        biblioteca.getFilaEspera().adicionarFila(livro.getLivroHash(), maybeUser.get());
                        System.out.println("Usuário adicionado à fila de '" + livro.getTitulo() + "'.");
                    }
                }
                case 14 -> {
                    System.out.print("Título do livro: "); String title = scanner.nextLine();
                    List<Livro> encontrados = biblioteca.buscarPorTitulo(title);
                    if (encontrados.isEmpty()) { System.out.println("Livro não encontrado."); }
                    else {
                        Livro livro = encontrados.get(0);
                        var fila = biblioteca.getFilaEspera().verFila(livro.getLivroHash());
                        if (fila.isEmpty()) System.out.println("Fila vazia para este livro.");
                        else { System.out.println("Fila de '" + livro.getTitulo() + "':"); fila.forEach(u -> System.out.println(" - " + u.getNome())); }
                    }
                }
                case 15 -> {
                    System.out.print("ID do usuário: "); String userId = scanner.nextLine();
                    var maybeUser = biblioteca.getUsuario(userId);
                    if (maybeUser.isEmpty()) { System.out.println("Usuário não encontrado."); }
                    else {
                        var hist = biblioteca.getHistoricoUsuario().verHistoricoUs(userId);
                        if (hist.isEmpty()) System.out.println("Sem histórico.");
                        else { System.out.println("Histórico de " + maybeUser.get().getNome() + ":"); hist.forEach(b -> System.out.println(" - " + b.getTitulo())); }
                    }
                }
                case 16 -> {
                    System.out.print("Título: "); String title = scanner.nextLine();
                    List<Livro> encontrados = biblioteca.buscarPorTitulo(title);
                    if (encontrados.isEmpty()) { System.out.println("Livro não encontrado."); }
                    else {
                        Set<Livro> recs = biblioteca.getRecommendations(encontrados.get(0));
                        if (recs.isEmpty()) System.out.println("Sem recomendações.");
                        else { System.out.println("Recomendações:"); recs.forEach(r -> System.out.println(" - " + r.getTitulo())); }
                    }
                }
                case 17 -> biblioteca.displayRecommendationGraph();
                case 0 -> { GerenciadorBiblioteca.salvarLivros(livros, biblioteca); GerenciadorBiblioteca.salvarUsers(usuarios); System.out.println("Fechando biblioteca..."); }
                default -> System.out.println("Opção inválida.");
            }

            if (option != 0) { System.out.print("[ENTER] para continuar... "); scanner.nextLine(); }

        } while (option != 0);

        scanner.close();
    }
}
