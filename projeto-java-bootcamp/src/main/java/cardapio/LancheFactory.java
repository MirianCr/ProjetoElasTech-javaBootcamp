package cardapio;

public class LancheFactory {

    // pão, hambúrguer, queijo, alface e tomate
    public static Lanche xBurger() {
        return new Lanche("X-Burger", 16.0);
    }

    // pão, hambúrguer, queijo, alface, tomate e cebola
    public static Lanche xSalada() {
        return new Lanche("X-Salada", 18.0);
    }

    // pão, hambúrguer, queijo, bacon, alface, tomate e cebola
    public static Lanche xBacon() {
        return new Lanche("X-Bacon", 22.0);
    }
}
