package br.com.projeto.casa.codigo.autor;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.ResourceUtils;

import java.nio.file.Files;

@ActiveProfiles( value = "test" )
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext( classMode = DirtiesContext.ClassMode.BEFORE_CLASS )
public class AutorItegrationTest {

    public static final String AUTOR_ENDPOINT = "/autor";

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Autor cadastrado com sucesso")
    void cadastroAutorSucesso() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders
                        .post(AUTOR_ENDPOINT)
                        .contentType( MediaType.APPLICATION_JSON )
                        .content(
                                Files.readString(
                                        ResourceUtils
                                                .getFile("classpath:br/com/projeto/casa/codigo/autor/autora-cadastrado-sucesso.json")
                                                .toPath()
                                )
                        )
        ).andDo( MockMvcResultHandlers.print() )
                .andExpect( MockMvcResultMatchers.status().isOk() )
                .andExpect( MockMvcResultMatchers.content().contentType( MediaType.APPLICATION_JSON ) )
                .andExpect( MockMvcResultMatchers.jsonPath("$.nome").value("Maria Silva") )
                .andExpect( MockMvcResultMatchers.jsonPath("$.email").value("maria.silva@email.com") );
    }

    @Test
    @DisplayName("Autor já cadastrado")
    @Sql(scripts = {"inserir-autor.sql"})
    void autorJaCadastrado() throws Exception {
        final String request = Files.readString(
                ResourceUtils
                        .getFile("classpath:br/com/projeto/casa/codigo/autor/autor-ja-cadastrado.json")
                        .toPath()
        );
        mockBadRequest( request );
    }

    @Test
    @DisplayName("Autor com email invalido")
    void autorComEmailInvalido() throws Exception {
        final String request = Files.readString(
                ResourceUtils
                        .getFile("classpath:br/com/projeto/casa/codigo/autor/autor-email-invalido.json")
                        .toPath()
        );
        mockBadRequest( request );
    }

    @Test
    @DisplayName("Autor com nome invalido")
    void autorComNomeInvalido() throws Exception {
        final String request = Files.readString(
                ResourceUtils
                        .getFile("classpath:br/com/projeto/casa/codigo/autor/autor-nome-invalido.json")
                        .toPath()
        );
        mockBadRequest( request );
    }

    private void mockBadRequest( final String request ) throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders
                        .post(AUTOR_ENDPOINT)
                        .contentType( MediaType.APPLICATION_JSON )
                        .content( request )
        ).andDo( MockMvcResultHandlers.print() )
                .andExpect( MockMvcResultMatchers.status().isBadRequest() )
                .andExpect( MockMvcResultMatchers.content().contentType( MediaType.APPLICATION_JSON ) )
                .andExpect( MockMvcResultMatchers.jsonPath("$.codigo").value(400));
    }

}
