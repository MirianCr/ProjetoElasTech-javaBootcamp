# Sistema de Restaurante

Projeto 1 do bootcamp: sistema de console de um restaurante com cardápio, pedidos, personalização dos lanches e fechamento do dia.

**Vídeo:** _(coloque o link aqui)_

## O que foi feito

- **Nível 1:** classes `Lanche`, `Pedido` e `GerenciadorPedidos`; menu de console com registro de pedidos, listagem e fechamento do dia.
- **Nível 2:** interface `ItemCardapio`, `LancheFactory` (padrão Factory) e adicionais `ComCheddar` e `ComBaconExtra` (padrão Decorator); testes com JUnit.
- **Nível 3:** retirar ingredientes do lanche, modificar pedido (acrescentar item, remover item, cancelar pedido), contagem de combinações diferentes com `HashSet` e tratamento de entrada inválida.

## Pacotes

- `app` — `Main` (fluxo principal), `MenuConsole` (leitura e exibição) e `RestauranteFacade` (coordena as operações).
- `cardapio` — `ItemCardapio`, `Lanche`, `LancheFactory` e os adicionais.
- `pedido` — `Pedido` (uma comanda) e `GerenciadorPedidos` (todos os pedidos do dia).

## Como rodar

Abra o projeto no IntelliJ e execute a classe `app.Main`. Para rodar os testes, execute `mvn test` ou rode a pasta `src/test/java` pelo IntelliJ.
