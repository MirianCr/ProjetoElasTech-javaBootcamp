package app;

import cardapio.ItemCardapio;
import pedido.Pedido;

import java.util.Collection;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class MenuConsole {
    private Scanner sc = new Scanner(System.in);

    public void exibirMenu() {
        System.out.println();
        System.out.println("=== RESTAURANTE ===");
        System.out.println("1 - Registrar novo pedido");
        System.out.println("2 - Listar pedidos do dia");
        System.out.println("3 - Fechar restaurante");
        System.out.println("4 - Modificar pedido");
        System.out.println("0 - Sair");
    }

    public int lerOpcao() {
        return lerInteiro("Escolha uma opção: ");
    }

    public String lerNomeCliente() {
        System.out.print("Nome do cliente: ");
        return sc.nextLine();
    }

    public int lerOpcaoLanche() {
        System.out.println("--- CARDÁPIO ---");
        System.out.println("1 - X-Burger  R$ 16,00");
        System.out.println("2 - X-Salada  R$ 18,00");
        System.out.println("3 - X-Bacon   R$ 22,00");
        return lerInteiroEntre("Escolha o lanche: ", 1, 3);
    }

    public boolean querIngrediente(String ingrediente) {
        return lerSimNao("Manter " + ingrediente + "? (s/n): ");
    }

    public boolean querAdicional(String adicional) {
        return lerSimNao("Adicionar " + adicional + "? (s/n): ");
    }

    public boolean querMaisUmItem() {
        return lerSimNao("Adicionar mais um item? (s/n): ");
    }

    public int lerNumeroPedido() {
        return lerInteiro("Número do pedido: ");
    }

    public int lerOpcaoModificar() {
        System.out.println("1 - Acrescentar item");
        System.out.println("2 - Remover item");
        System.out.println("3 - Cancelar pedido");
        System.out.println("0 - Voltar");
        return lerInteiroEntre("Escolha uma opção: ", 0, 3);
    }

    // Lista os itens numerados e devolve o índice (começando em 0) do item escolhido
    public int lerItemParaRemover(Pedido pedido) {
        List<ItemCardapio> itens = pedido.getItens();
        for (int i = 0; i < itens.size(); i++) {
            System.out.println((i + 1) + " - " + itens.get(i).getDescricao());
        }
        return lerInteiroEntre("Número do item a remover: ", 1, itens.size()) - 1;
    }

    public void exibirPedido(Pedido pedido) {
        System.out.println("Pedido #" + pedido.getNumero() + " - " + pedido.getNomeCliente());
        for (ItemCardapio item : pedido.getItens()) {
            System.out.println("  " + item.getDescricao() + "  " + formatarPreco(item.getPreco()));
        }
        System.out.println("  Total: " + formatarPreco(pedido.calcularTotal()));
    }

    public void exibirPedidos(Collection<Pedido> pedidos) {
        if (pedidos.isEmpty()) {
            System.out.println("Nenhum pedido registrado hoje.");
            return;
        }
        for (Pedido pedido : pedidos) {
            exibirPedido(pedido);
        }
    }

    public void exibirFechamentoDoDia(double total) {
        System.out.println("=== FECHAMENTO DO DIA ===");
        System.out.println("Total vendido: " + formatarPreco(total));
    }

    public void exibirCombinacoes(HashSet<String> combinacoes) {
        System.out.println("Combinações diferentes: " + combinacoes.size());
        for (String combinacao : combinacoes) {
            System.out.println("  " + combinacao);
        }
    }

    public void exibirPedidoNaoEncontrado() {
        System.out.println("Pedido não encontrado.");
    }

    public void exibirPedidoSemItens() {
        System.out.println("Esse pedido não tem itens.");
    }

    public void exibirPedidoCancelado() {
        System.out.println("Pedido cancelado.");
    }

    public void exibirOpcaoInvalida() {
        System.out.println("Opção inválida.");
    }

    public void exibirErro(String mensagem) {
        System.out.println("Erro: " + mensagem);
    }

    public void exibirDespedida() {
        System.out.println("Até logo!");
    }

    private int lerInteiro(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            try {
                int valor = sc.nextInt();
                sc.nextLine();
                return valor;
            } catch (InputMismatchException e) {
                sc.nextLine();
                System.out.println("Digite um número.");
            }
        }
    }

    private int lerInteiroEntre(String mensagem, int min, int max) {
        while (true) {
            int valor = lerInteiro(mensagem);
            if (valor >= min && valor <= max) {
                return valor;
            }
            exibirOpcaoInvalida();
        }
    }

    private boolean lerSimNao(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String resposta = sc.nextLine().trim().toLowerCase();
            if (resposta.equals("s")) {
                return true;
            }
            if (resposta.equals("n")) {
                return false;
            }
            System.out.println("Responda com s ou n.");
        }
    }

    private String formatarPreco(double valor) {
        return String.format("R$ %.2f", valor);
    }
}
