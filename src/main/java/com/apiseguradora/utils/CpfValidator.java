package com.apiseguradora.utils;

import org.springframework.stereotype.Component;
@Component
public class CpfValidator {

        public boolean isValid(String cpf) {
            return Utils.isValidCPF(cpf);

    }

}
