package src;

public class Conta {
    private double saldo;

    public Conta(double saldoInicial) throws Exception {
        if (saldoInicial < 0) throw new Exception("Saldo inicial negativo!");
        saldo = saldoInicial;
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
