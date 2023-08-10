import java.util.ArrayList;
import java.util.Scanner;

public class AgenciaBancaria {
    private ArrayList<ContaBancaria> contas;
    private ArrayList<String> agencias;
    private int numeroProximaConta;
    private Scanner scanner;

    public AgenciaBancaria() {
        contas = new ArrayList<>();
        agencias = new ArrayList<>();
        numeroProximaConta = 1;
        scanner = new Scanner(System.in);
    }

    public void criarConta() {
        System.out.print("Digite o nome do titular: ");
        String nomeTitular = scanner.nextLine();

        System.out.print("Digite o número da agência: ");
        String numeroAgencia = scanner.nextLine();

        // Verifica se a agência já existe na lista de agências
        if (!agencias.contains(numeroAgencia)) {
            agencias.add(numeroAgencia);
        }

        String numeroConta = gerarNumeroConta();

        System.out.print("Digite o valor inicial para o depósito: ");
        double saldoInicial = scanner.nextDouble();

        ContaBancaria novaConta = new ContaBancaria(numeroAgencia, numeroConta, nomeTitular, saldoInicial);
        contas.add(novaConta);

        System.out.println("Conta criada com sucesso!");
        System.out.println("Número da conta: " + numeroConta);
    }

    public void removerConta() {
        System.out.print("Digite o número da conta que deseja remover: ");
        String numeroConta = scanner.nextLine();

        ContaBancaria conta = buscarConta(numeroConta);

        if (conta == null) {
            System.out.println("Conta não encontrada.");
            return;
        }

        contas.remove(conta);
        System.out.println("Conta removida com sucesso!");
    }

    private String gerarNumeroConta() {
        String numeroConta = "0000" + numeroProximaConta;
        numeroProximaConta++;
        return numeroConta;
    }

    public void listarContas() {
        if (contas.isEmpty()) {
            System.out.println("Nenhuma conta encontrada.");
            return;
        }

        System.out.println("Lista de contas:");
        for (ContaBancaria conta : contas) {
            System.out.println(conta);
            System.out.println("------------------------");
        }
    }

    public void listarAgencias() {
        if (agencias.isEmpty()) {
            System.out.println("Nenhuma agência encontrada.");
            return;
        }

        System.out.println("Lista de agências:");
        for (String agencia : agencias) {
            System.out.println(agencia);
        }
    }

    public void interagirComUsuario() {
        while (true) {
            System.out.println("==================================================");
            System.out.println("===== Bem vindo(a) a agência bancária =====");
            System.out.println("1. Criar nova conta");
            System.out.println("2. Realizar depósito");
            System.out.println("3. Realizar saque");
            System.out.println("4. Listar contas");
            System.out.println("5. Listar agências");
            System.out.println("6. Remover conta");
            System.out.println("7. Sair");
            System.out.print("Escolha a opção desejada: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer do teclado

            switch (opcao) {
                case 1:
                    criarConta();
                    break;
                case 2:
                    realizarDeposito();
                    break;
                case 3:
                    realizarSaque();
                    break;
                case 4:
                    listarContas();
                    break;
                case 5:
                    listarAgencias();
                    break;
                case 6:
                    removerConta();
                    break;
                case 7:
                    System.out.println("Encerrando programa. Obrigado!");
                    fecharScanner(); // Fechar o Scanner antes de sair
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    public void realizarDeposito() {
        System.out.print("Digite o número da conta para o depósito: ");
        String numeroConta = scanner.nextLine();

        ContaBancaria conta = buscarConta(numeroConta);

        if (conta == null) {
            System.out.println("Conta não encontrada.");
            return;
        }

        System.out.print("Digite o valor do depósito: ");
        double valorDeposito = scanner.nextDouble();

        conta.depositar(valorDeposito);
    }

    public void realizarSaque() {
        System.out.print("Digite o número da conta para o saque: ");
        String numeroConta = scanner.nextLine();

        ContaBancaria conta = buscarConta(numeroConta);

        if (conta == null) {
            System.out.println("Conta não encontrada.");
            return;
        }

        System.out.print("Digite o valor do saque: ");
        double valorSaque = scanner.nextDouble();

        conta.sacar(valorSaque);
    }

    private ContaBancaria buscarConta(String numeroConta) {
        for (ContaBancaria conta : contas) {
            if (conta.getNumeroConta().equals(numeroConta)) {
                return conta;
            }
        }
        return null;
    }

    private void fecharScanner() {
        scanner.close();
    }
}




