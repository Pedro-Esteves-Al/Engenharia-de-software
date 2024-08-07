package src;

import java.util.ArrayList;
public class Numero {
    public Integer encontrarSegundoMaior(ArrayList<Integer> numeros) {
        Integer segundoMaior = numeros.get(0);
        Integer maior = numeros.get(1);
        Integer numero;
        for (int i = 2; i < numeros.size(); i++) {
            numero = numeros.get(i);
            if (numero > maior) {
                segundoMaior = maior;
                maior = numero;
            } else if (numero > segundoMaior && numero < maior) {
                segundoMaior = numero;
            }
        }
        return segundoMaior;
    }
}
