package src;

import org.junit.jupiter.api.Test;
import org.mockito.*;
import static org.junit.jupiter.api.Assertions.*;


class ContaTest {

    private IApiBancoCentral getApiBc() {
        return Mockito.mock(ApiBancoCentral.class);
    }

    @Test
    void depositar() throws Exception {
        var obj = new Conta (100,getApiBc());
        obj.depositar(50);
        assertEquals(150,obj.getSaldo());
    }

    @Test
    void depositarValorNegativo() throws Exception {
        var obj = new Conta (100,getApiBc());
        assertThrows(Exception.class, () -> obj.depositar(-50));
    }

    @Test
    void sacarAbaixo5K() throws Exception {
        var api = getApiBc();
        var obj = new Conta (100,api);
        try {
            obj.sacar(50);
        } catch (Exception e) {
            throw new Exception("Erro no saque");
        }
        assertEquals(50,obj.getSaldo());
        Mockito.verify(api,Mockito.times(0)).notificarSaque(obj,50);
    }

    @Test
    void sacarAcima5K() throws Exception {
        var api = getApiBc();
        var obj = new Conta (5100,api);
        try {
            obj.sacar(5001);
        } catch (Exception e) {
            throw new Exception("Erro no saque");
        }
        assertEquals(99,obj.getSaldo());
        Mockito.verify(api,Mockito.times(1)).notificarSaque(obj,5001);
    }
    @Test
    void getSaldo() throws Exception {
        var obj = new Conta(1,getApiBc());
        assertEquals(1,obj.getSaldo());
    }

    @Test
    void sacarAcimaSaldo() throws Exception {
        var obj = new Conta(100,getApiBc());
        assertThrows(Exception.class, () -> obj.sacar(100.01));
    }
    @Test
    void transferirComSaldo() throws Exception {
        var origem = new Conta(100.0,getApiBc());
        var destino = new Conta(25,getApiBc());

        try {
            origem.transferir(destino,100);
            assertEquals(125,destino.getSaldo());
            assertEquals(0,origem.getSaldo());
        } catch (Exception e) {
            fail();
        }
    }
}