package src;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContaTest {

    @Test
    void depositar() throws Exception {
        var obj = new Conta (100);
        obj.depositar(50);
        assertEquals(150,obj.getSaldo());
    }

    @Test
    void depositarValorNegativo() throws Exception {
        var obj = new Conta (100);
        assertThrows(Exception.class, () -> obj.depositar(-50));
    }

    @Test
    void sacar() throws Exception {
        var obj = new Conta (100);
        try {
            obj.sacar(50);
        } catch (Exception e) {
            throw new Exception("Erro no saque");
        }
        assertEquals(50,obj.getSaldo());
    }

    @Test
    void getSaldo() throws Exception {
        var obj = new Conta(1);
        assertEquals(1,obj.getSaldo());
    }

    @Test
    void sacarAcimaSaldo() throws Exception {
        var obj = new Conta(100);
        assertThrows(Exception.class, () -> obj.sacar(100.01));
    }
}