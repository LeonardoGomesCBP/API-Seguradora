package com.apiseguradora.utils;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Random;

public class Utils {

    static int[] pesoCPF = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};

    // metodo para gerar o numero da apolice
    public static String randomNovo(ArrayList<String> setVerificar) {

        Random rnd = new Random();
        int number = rnd.nextInt(1000000);
        return String.valueOf(number);
    }


    private static int calcularDigito(String str, int[] peso) {
        int soma = 0;

        for (int indice = str.length() - 1, digito; indice >= 0; indice--) {
            digito = Integer.parseInt(str.substring(indice, indice + 1));
            soma += digito * peso[peso.length - str.length() + indice];
        }
        soma = 11 - soma % 11;
        return soma > 9 ? 0 : soma;
    }

    //metodo para verificar se o cpf é valido
    public static boolean isValidCPF(String cpf) {
        if ((cpf == null) || (cpf.length() != 11)) return false;

        Integer digito1 = calcularDigito(cpf.substring(0, 9), pesoCPF);
        Integer digito2 = calcularDigito(cpf.substring(0, 9) + digito1, pesoCPF);
        return cpf.equals(cpf.substring(0, 9) + digito1.toString() + digito2.toString());
    }

    @Component
    public class CpfValidator {
        public boolean isValid(String cpf) {
            return Utils.isValidCPF(cpf);
        }
    }

}
