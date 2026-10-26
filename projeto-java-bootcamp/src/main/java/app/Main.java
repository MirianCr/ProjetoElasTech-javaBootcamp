package app;

import cardapio.ComBaconExtra;
import cardapio.ComCheddar;
import cardapio.ItemCardapio;
import cardapio.Lanche;
import pedido.Pedido;

public class Main {

    public static void main(String[] args) {
        MenuConsole menu = new MenuConsole();
        RestauranteFacade sistema = new RestauranteFacade();

        boolean rodando = true;
        while (rodando) {
            menu.exibirMenu();
            int opcao = menu.lerOpcao();

            switch (opcao) {
                case 1:
                    registrarPedido(menu, sistema);
                    break;
                case 2:
                    menu.exibirPedidos(sistema.getPedidos());
                    break;
                case 3:
                    double total = sistema.calcularTotalDoDia();
                    menu.exibirFechamentoDoDia(total);
                    menu.exibirCombinacoes(sistema.getCombinacoes());
                    rodando = false;
                    break;
                case 4:
                    modificarPedido(menu, sistema);
                    break;
                case 0:
                    menu.exibirDespedida();
                    rodando = false;
                    break;
                default:
                    menu.exibirOpcaoInvalida();
            }
        }
    }

    private static void registrarPedido(MenuConsole menu, RestauranteFacade sistema) {
        String nome = menu.lerNomeCliente();
        Pedido pedido;
        try {
            pedido = sistema.criarPedido(nome);
        } catch (IllegalArgumentException e) {
            menu.exibirErro(e.getMessage());
            return;
        }

        do {
            pedido.adicionarItem(montarItem(menu, sistema));
        } while (menu.querMaisUmItem());

        menu.exibirPedido(pedido);
    }

    private static ItemCardapio montarItem(MenuConsole menu, RestauranteFacade sistema) {
        Lanche lanche = sistema.criarLanche(menu.lerOpcaoLanche());

        if (!menu.querIngrediente("queijo")) lanche.setQueijo(false);
        if (!menu.querIngrediente("alface")) lanche.setAlface(false);
        if (!menu.querIngrediente("tomate")) lanche.setTomate(false);
        if (!menu.querIngrediente("cebola")) lanche.setCebola(false);

        ItemCardapio item = lanche;
        if (menu.querAdicional("cheddar (+R$ 4,00)")) item = new ComCheddar(item);
        if (menu.querAdicional("bacon extra (+R$ 6,00)")) item = new ComBaconExtra(item);
        return item;
    }

    private static void modificarPedido(MenuConsole menu, RestauranteFacade sistema) {
        int numero = menu.lerNumeroPedido();
        Pedido pedido = sistema.buscarPedido(numero);
        if (pedido == null) {
            menu.exibirPedidoNaoEncontrado();
            return;
        }

        menu.exibirPedido(pedido);
        switch (menu.lerOpcaoModificar()) {
            case 1:
                pedido.adicionarItem(montarItem(menu, sistema));
                menu.exibirPedido(pedido);
                break;
            case 2:
                if (pedido.getItens().isEmpty()) {
                    menu.exibirPedidoSemItens();
                    break;
                }
                pedido.removerItem(menu.lerItemParaRemover(pedido));
                menu.exibirPedido(pedido);
                break;
            case 3:
                sistema.cancelarPedido(numero);
                menu.exibirPedidoCancelado();
                break;
        }
    }
}
