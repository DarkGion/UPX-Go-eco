package Goeco.view;

import java.util.List;
import java.util.Scanner;

import GoEco.dao.*;
import GoEco.entity.*;

public class main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ConnectionFactory factory = new ConnectionFactory();
        ContatoDAO contatoDAO = new ContatoDAO();
        EnderecoDAO enderecoDAO = new EnderecoDAO();
        ProdutoDAO produtoDAO = new ProdutoDAO();
        TipoDAO tipoDAO = new TipoDAO();
        UsuariosDAO usuarioDAO = new UsuariosDAO();

        System.out.println("========================================");
        System.out.println("🌱 Bem-vindo ao EcoGo Console Marketplace 🌱");
        System.out.println("========================================");
        System.out.println("\nAntes de começar, cadastre sua empresa!\n");

        // --- CADASTRO DE CONTATO ---
        System.out.print("Nome do responsável: ");
        String nomeContato = sc.nextLine();
        System.out.print("E-mail: ");
        String email = sc.nextLine();
        System.out.print("Telefone: ");
        String telefone = sc.nextLine();

        Contato contato = new Contato(nomeContato, email, telefone);
        contatoDAO.inserir(contato);
        int contatoId = contatoDAO.buscarUltimoId(); // método auxiliar no DAO

        // --- CADASTRO DE ENDEREÇO ---
        System.out.println("\nInforme o endereço da empresa:");
        System.out.print("Rua: ");
        String logradouro = sc.nextLine();
        System.out.print("Número: ");
        String numero = sc.nextLine();
        System.out.print("Complemento: ");
        String complemento = sc.nextLine();
        System.out.print("Bairro: ");
        String bairro = sc.nextLine();
        System.out.print("Cidade: ");
        String cidade = sc.nextLine();
        System.out.print("Estado (UF): ");
        String estado = sc.nextLine();
        System.out.print("CEP: ");
        String cep = sc.nextLine();

        Endereco endereco = new Endereco(0, logradouro, numero, complemento, bairro, cidade, estado, cep);
        enderecoDAO.inserir(endereco);
        int enderecoId = enderecoDAO.buscarUltimoId();

        // --- CADASTRO DO TIPO ---
        System.out.println("\nTipo de empresa:");
        System.out.println("1 - Fornecedor");
        System.out.println("2 - Reciclador");
        System.out.println("3 - Ambos");
        System.out.print("Escolha: ");
        int tipoEscolha = sc.nextInt();
        sc.nextLine();

        String tipoTexto = switch (tipoEscolha) {
            case 1 -> "Fornecedor";
            case 2 -> "Reciclador";
            default -> "Ambos";
        };

        Tipo tipo = new Tipo(0, tipoTexto);
        tipoDAO.inserir(tipo);
        int tipoId = tipoDAO.buscarUltimoId();

        // --- CADASTRO DE PRODUTO INICIAL ---
        System.out.println("\nAdicione um produto inicial da empresa:");
        System.out.print("Nome do produto: ");
        String nomeProd = sc.nextLine();
        System.out.print("Descrição: ");
        String descricao = sc.nextLine();
        System.out.print("Quantidade: ");
        int qtd = sc.nextInt();
        System.out.print("Valor por unidade: ");
        double valor = sc.nextDouble();
        sc.nextLine();

        Produtos produto = new Produtos(0, nomeProd, qtd, descricao, valor);
        produtoDAO.inserir(produto);
        int produtoId = produtoDAO.buscarUltimoId();

        // --- CADASTRO DE USUÁRIO / EMPRESA ---
        System.out.print("\nNome da Empresa: ");
        String nomeEmpresa = sc.nextLine();

        Usuarios usuario = new Usuarios(nomeEmpresa, enderecoId, tipoId, produtoId, contatoId);
        usuarioDAO.inserir(usuario);

        System.out.println("\n✅ Empresa cadastrada com sucesso!");
        System.out.println("========================================");

        // --- MENU PRINCIPAL ---
        int opcao;
        do {
            System.out.println("\n====== MENU PRINCIPAL ======");
            System.out.println("1 - Marketplace (ver produtos)");
            System.out.println("2 - Adicionar Material");
            System.out.println("3 - Minha Empresa");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1 -> {
                    System.out.println("\n--- LISTA DE PRODUTOS DISPONÍVEIS ---");
                    List<Produtos> produtos = produtoDAO.listarTodos();
                    for (Produtos p : produtos) {
                        System.out.println("ID: " + p.getProd_id() + " | " + p.getNome_prod() + 
                                           " | Quantidade: " + p.getQtd_estoque() +
                                           " | Valor: R$" + p.getValor());
                        System.out.println("Descrição: " + p.getDescricao());
                        System.out.println("-----------------------------------");
                    }
                }
                case 2 -> {
                    System.out.println("\n--- ADICIONAR MATERIAL ---");
                    System.out.print("Nome do produto: ");
                    String nomeP = sc.nextLine();
                    System.out.print("Descrição: ");
                    String desc = sc.nextLine();
                    System.out.print("Quantidade: ");
                    int qtdP = sc.nextInt();
                    System.out.print("Valor por unidade: ");
                    double valorP = sc.nextDouble();
                    sc.nextLine();

                    Produtos novo = new Produtos(0, nomeP, qtdP, desc, valorP);
                    produtoDAO.inserir(novo);
                    System.out.println("✅ Produto adicionado com sucesso!");
                }
                case 3 -> {
                    System.out.println("\n--- PERFIL DA SUA EMPRESA ---");
                    Usuarios u = usuarioDAO.buscarUltimoUsuario();
                    Tipo t = new Tipo();
                    if (u != null) {
                        System.out.println("Empresa: " + u.getNome());
                        System.out.println("Tipo: " + tipoDAO.buscarPorId(u.getTipo_id()).getTipo());
                        System.out.println("Endereço: " + enderecoDAO.buscarPorId(u.getEndereco_id()).toString());
                        System.out.println("Contato: " + contatoDAO.buscarPorId(u.getContato_id()).toString());
                    } else {
                        System.out.println("Nenhuma empresa cadastrada ainda.");
                    }
                }
                case 0 -> System.out.println("Encerrando o sistema... 🌎");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);

        sc.close();
    }
}