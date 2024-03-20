import java.io.*;
import java.util.Scanner;

public class cxeletronico {
    static double saldo = 1000.00;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        exibirBoasVindas();
        carregarHistorico(); // Carrega o histórico antes de exibir o saldo
        exibirSaldoAtual();
        exibirOpcoes();

        int opcao = scanner.nextInt();

        switch (opcao) {
            case 1:
                realizarSaque(scanner);
                break;
            case 2:
                realizarDeposito(scanner);
                break;
            case 3:
                consultarSaldo();
                break;
            case 4:
                exibirHistorico();
                break;
            default:
                exibirOpcaoInvalida();
        }

        scanner.close();
    }

    public static void exibirBoasVindas() {
        System.out.println("Bem-vindo ao Caixa Eletrônico");
    }

    public static void exibirSaldoAtual() {
        System.out.println("Seu saldo atual é: R$" + saldo);
    }

    public static void exibirOpcoes() {
        System.out.println("Escolha uma opção:");
        System.out.println("1 - Saque");
        System.out.println("2 - Depósito");
        System.out.println("3 - Consultar Saldo");
        System.out.println("4 - Exibir Histórico de Depósitos");
    }

    public static void realizarSaque(Scanner scanner) {
        System.out.println("Digite o valor do saque:");
        double valor = scanner.nextDouble();
        if (valor > saldo) {
            System.out.println("Saldo insuficiente.");
        } else {
            saldo -= valor;
            System.out.println("Saque de R$" + valor + " realizado.");
        }
    }

    public static void realizarDeposito(Scanner scanner) {
        System.out.println("Digite o valor do depósito:");
        double valor = scanner.nextDouble();
        saldo += valor;
        salvarDeposito(valor); // Salva o depósito
        System.out.println("Depósito de R$" + valor + " realizado.");
        exibirSaldoAtual(); // Exibe o saldo atualizado
    }

    public static void consultarSaldo() {
        System.out.println("Seu saldo atual é: R$" + saldo);
    }

    public static void exibirHistorico() {
        try (BufferedReader reader = new BufferedReader(new FileReader("historico.txt"))) {
            String linha;
            int numDeposito = 1;
            while ((linha = reader.readLine()) != null) {
                System.out.println("Depósito " + numDeposito++ + ": R$" + linha);
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o histórico.");
        }
    }

    public static void exibirOpcaoInvalida() {
        System.out.println("Opção inválida.");
    }

    public static void salvarDeposito(double valor) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("historico.txt", true))) {
            writer.write(String.valueOf(valor));
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Erro ao salvar o depósito.");
        }
    }

    public static void carregarHistorico() {
        try (BufferedReader reader = new BufferedReader(new FileReader("historico.txt"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                saldo += Double.parseDouble(linha); // Soma todos os depósitos ao saldo
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o histórico.");
        }
    }
}
