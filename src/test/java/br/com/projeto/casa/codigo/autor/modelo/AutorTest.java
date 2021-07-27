package br.com.projeto.casa.codigo.autor.modelo;

import br.com.projeto.casa.codigo.autor.modelo.excessao.EmailFormatoInvalidoException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AutorTest {

    @Test
    @DisplayName("Autor com email valido criado")
    public void autorEmailValido(){
        final String email = "nome.sobrenome@email.com";
        final Autor autor = Autor.mock(email);
        Assertions.assertEquals("", autor.getNome() );
        Assertions.assertEquals( email, autor.getEmail() );
    }

    @Test
    @DisplayName("Autor com email invalido")
    public void autorEmailInvalido(){
        Assertions.assertThrows(
                EmailFormatoInvalidoException.class,
                () -> Autor.mock("@")
        );
    }

}
