package src;

public class Conta {
    private double saldo;

    private double limite = 0;

    private IApiBancoCentral apiBc;

    public Conta(double saldoInicial, IApiBancoCentral apiBc) throws Exception {
        //if (saldoInicial < 0) throw new Exception("Saldo inicial negativo!");
        saldo = saldoInicial;
        this.apiBc = apiBc;
    }

    public void depositar(double quantia) throws Exception {
        if (quantia < 0) throw new Exception("Quantia negativa");
        saldo += quantia;
    }

    public void sacar(double quantia) throws Exception {
        if (saldo < quantia) {
            throw new Exception("Saldo insuficiente");
        }
        saldo -= quantia;

        if (quantia > 5000) {
            apiBc.notificarSaque(this,quantia);
        }
    }

    public double getSaldo() {
        return saldo;
    }

    public void transferir(Conta destino, double quantia) throws Exception {
        if (destino == null) {
            throw new Exception("Conta de destino inválida");
        }
        if (saldo < quantia) {
            throw new Exception("Saldo insuficiente na conta");
        }
        sacar(quantia);
        destino.depositar(quantia);
    }
}
