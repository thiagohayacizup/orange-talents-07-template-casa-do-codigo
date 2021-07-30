package br.com.projeto.casa.codigo.cliente.documento.dominio;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class DocumentoTest {

    @DisplayName("Cpf's validos")
    @ParameterizedTest
    @ValueSource(strings = {
            "297.036.590-12", "176.443.910-45", "696.412.140-47", "238.669.030-06", "145.505.220-54"
    })
    void cpfsValidos( final String cpf ){
        Assertions.assertTrue(
                Cpf.de(cpf.replaceAll("\\.", "").replaceAll("-",""))
                        .validarPrimeiroDigito()
                        .validarSegundoDigito()
                        .eValido()
        );
    }

    @DisplayName("Cpf's invalidos")
    @ParameterizedTest
    @ValueSource(strings = {
            "297.036.590-13", "176.443.910-44", "696.412.140-45", "238.669.030-07", "145.505.220-58"
    })
    void cpfsInvalidos( final String cpf ){
        Assertions.assertFalse(
                Cpf.de(cpf.replaceAll("\\.", "").replaceAll("-",""))
                        .validarPrimeiroDigito()
                        .validarSegundoDigito()
                        .eValido()
        );
    }

    @DisplayName("Cnpj's validos")
    @ParameterizedTest
    @ValueSource(strings = {
            "36.534.314/0001-11",
            "11.262.670/0001-00",
            "16.245.862/0001-14",
            "49.200.426/0001-88",
            "70.206.192/0001-72"
    })
    void cnpjsValidos( final String cpf ){
        Assertions.assertTrue(
                Cnpj
                        .de(cpf
                                .replaceAll("\\.", "")
                                .replaceAll("-","")
                                .replaceAll("/","")
                        )
                        .validarPrimeiroDigito()
                        .validarSegundoDigito()
                        .eValido()
        );
    }

    @DisplayName("Cnpj's invalidos")
    @ParameterizedTest
    @ValueSource(strings = {
            "36.534.314/0001-12",
            "11.262.670/0001-03",
            "16.245.862/0001-15",
            "49.200.426/0001-86",
            "70.206.192/0001-77"
    })
    void cnpjsInvalidos( final String cpf ){
        Assertions.assertFalse(
                Cnpj
                        .de(cpf
                                .replaceAll("\\.", "")
                                .replaceAll("-","")
                                .replaceAll("/","")
                        )
                        .validarPrimeiroDigito()
                        .validarSegundoDigito()
                        .eValido()
        );
    }

}
