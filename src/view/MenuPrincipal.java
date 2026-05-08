package view;

import domain.*;
import infrastructure.persistence.EntregaRepository;
import application.repositories.EntregaRepositoryImplement;

import java.util.List;
import java.util.Scanner;

public class MenuPrincipal {

    private EntregaRepository repository = new EntregaRepositoryImplement();
    private Scanner scanner = new Scanner(System.in);
    private int contadorEntrega = 1;
    private int contadorEntregador = 1;

    public void iniciar() {
        int opcao;
        do {
            System.out.println("\n===== SISTEMA DE LOGÍSTICA =====");
            System.out.println("1. Cadastrar Entregador");
            System.out.println("2. Listar Entregadores");
            System.out.println("3. Criar Entrega");
            System.out.println("4. Listar Entregas");
            System.out.println("5. Atribuir Entregador à Entrega");
            System.out.println("6. Atualizar Status da Entrega");
            System.out.println("0. Sair");
            System.out.print("Opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> cadastrarEntregador();
                case 2 -> listarEntregadores();
                case 3 -> criarEntrega();
                case 4 -> listarEntregas();
                case 5 -> atribuirEntregador();
                case 6 -> atualizarStatus();
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private void cadastrarEntregador() {
        System.out.println("\n-- Tipo de Entregador --");
        System.out.println("1. Moto");
        System.out.println("2. Bike");
        System.out.println("3. Carro");
        System.out.print("Tipo: ");
        int tipo = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        Entregador entregador = null;

        switch (tipo) {
            case 1 -> {
                System.out.print("Placa: ");
                String placa = scanner.nextLine();
                System.out.print("Capacidade (kg): ");
                double cap = scanner.nextDouble();
                scanner.nextLine();
                entregador = new EntregadorMoto(contadorEntregador++, nome, placa, cap);
            }
            case 2 -> {
                System.out.print("Raio máximo (km): ");
                double raio = scanner.nextDouble();
                System.out.print("Ecológico (true/false): ");
                boolean eco = scanner.nextBoolean();
                scanner.nextLine();
                entregador = new EntregadorBike(contadorEntregador++, nome, raio, eco);
            }
            case 3 -> {
                System.out.print("Placa: ");
                String placa = scanner.nextLine();
                System.out.print("Capacidade (kg): ");
                double cap = scanner.nextDouble();
                scanner.nextLine();
                entregador = new EntregadorCarro(contadorEntregador++, nome, placa, cap);
            }
            default -> System.out.println("Tipo inválido!");
        }

        if (entregador != null) {
            repository.salvarEntregador(entregador);
            System.out.println("Entregador cadastrado com sucesso!");
        }
    }

    private void listarEntregadores() {
        List<Entregador> lista = repository.buscarTodosEntregadores();
        if (lista.isEmpty()) { System.out.println("Nenhum entregador cadastrado."); return; }
        System.out.println("\n-- Entregadores --");
        lista.forEach(System.out::println);
    }

    private void criarEntrega() {
        System.out.print("Destino: ");
        String destino = scanner.nextLine();

        System.out.print("Nome do Remetente: ");
        String nomeRem = scanner.nextLine();
        System.out.print("Endereço do Remetente: ");
        String endRem = scanner.nextLine();
        System.out.print("Telefone do Remetente: ");
        String telRem = scanner.nextLine();

        System.out.print("Nome do Destinatário: ");
        String nomeDest = scanner.nextLine();
        System.out.print("Endereço do Destinatário: ");
        String endDest = scanner.nextLine();
        System.out.print("Telefone do Destinatário: ");
        String telDest = scanner.nextLine();

        Remetente remetente = new Remetente(nomeRem, endRem, telRem);
        Destinatario destinatario = new Destinatario(nomeDest, endDest, telDest);

        Entrega entrega = new Entrega(contadorEntrega++, destino, remetente, destinatario);
        repository.salvar(entrega);
        System.out.println("Entrega criada com sucesso!");
    }

    private void listarEntregas() {
        List<Entrega> lista = repository.buscarTodos();
        if (lista.isEmpty()) { System.out.println("Nenhuma entrega cadastrada."); return; }
        System.out.println("\n-- Entregas --");
        lista.forEach(System.out::println);
    }

    private void atribuirEntregador() {
        listarEntregas();
        System.out.print("ID da Entrega: ");
        int idEntrega = scanner.nextInt();
        scanner.nextLine();

        listarEntregadores();
        System.out.print("ID do Entregador: ");
        int idEntregador = scanner.nextInt();
        scanner.nextLine();

        Entrega entrega = repository.buscarTodos().stream()
                .filter(e -> e.getId() == idEntrega).findFirst().orElse(null);
        Entregador entregador = repository.buscarTodosEntregadores().stream()
                .filter(e -> e.getId() == idEntregador).findFirst().orElse(null);

        if (entrega == null || entregador == null) {
            System.out.println("Entrega ou Entregador não encontrado!");
            return;
        }

        entrega.atribuirEntregador(entregador);
        entregador.realizarEntrega();
        System.out.println("Entregador atribuído com sucesso!");
    }

    private void atualizarStatus() {
        listarEntregas();
        System.out.print("ID da Entrega: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.println("1. PENDENTE  2. EM_ROTA  3. ENTREGUE  4. CANCELADO");
        System.out.print("Status: ");
        int s = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Observação (Enter para pular): ");
        String obs = scanner.nextLine();

        StatusEntrega status = switch (s) {
            case 1 -> StatusEntrega.PENDENTE;
            case 2 -> StatusEntrega.EM_ROTA;
            case 3 -> StatusEntrega.ENTREGUE;
            case 4 -> StatusEntrega.CANCELADO;
            default -> null;
        };

        if (status == null) { System.out.println("Status inválido!"); return; }

        Entrega entrega = repository.buscarTodos().stream()
                .filter(e -> e.getId() == id).findFirst().orElse(null);

        if (entrega == null) { System.out.println("Entrega não encontrada!"); return; }

        if (obs.isBlank()) {
            entrega.atualizarStatus(status);
        } else {
            entrega.atualizarStatus(status, obs);
        }

        System.out.println("Status atualizado!");
    }
}