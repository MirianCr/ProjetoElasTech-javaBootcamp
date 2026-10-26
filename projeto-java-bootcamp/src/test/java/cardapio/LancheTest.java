package cardapio;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LancheTest {

    @Test
    void precoNegativoERecusado() {
        assertThrows(IllegalArgumentException.class, () -> new Lanche("X-Teste", -1.0));
    }

    @Test
    void xBaconComCheddarCusta26() {
        ItemCardapio lanche = new ComCheddar(LancheFactory.xBacon());
        assertEquals(26.0, lanche.getPreco(), 0.001);
    }

    @Test
    void xBaconComCheddarEBaconExtra() {
        ItemCardapio lanche = LancheFactory.xBacon();
        lanche = new ComCheddar(lanche);
        lanche = new ComBaconExtra(lanche);
        assertEquals("X-Bacon + cheddar + bacon extra", lanche.getDescricao());
        assertEquals(32.0, lanche.getPreco(), 0.001);
    }
}
