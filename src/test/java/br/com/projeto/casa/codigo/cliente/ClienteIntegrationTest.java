package br.com.projeto.casa.codigo.cliente;

import br.com.projeto.casa.codigo.MockErro;
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
@DirtiesContext( classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD )
class ClienteIntegrationTest {

    private static final String CLIENTE_ENDPOINT = "/cliente";

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Cliente cadastrado sucesso")
    @Sql(scripts = {"cliente-insert.sql"})
    void clienteCadastradoSucesso() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders
                                .post(CLIENTE_ENDPOINT)
                                .contentType( MediaType.APPLICATION_JSON )
                                .content(
                                        Files.readString(
                                                ResourceUtils
                                                        .getFile("classpath:br/com/projeto/casa/codigo/cliente/cliente-cadastrado-sucesso.json")
                                                        .toPath()
                                        )
                                )
                ).andDo( MockMvcResultHandlers.print() )
                .andExpect( MockMvcResultMatchers.status().isOk() )
                .andExpect( MockMvcResultMatchers.content().contentType( MediaType.APPLICATION_JSON ) )
                .andExpect( MockMvcResultMatchers.jsonPath("$.id").value(2L) );
    }

    @Test
    @DisplayName("Email deve ser unico")
    @Sql(scripts = {"cliente-insert.sql"})
    void emailUnico() throws Exception {
        final String request = Files.readString(
                ResourceUtils
                        .getFile("classpath:br/com/projeto/casa/codigo/cliente/cliente-ja-cadastrado-email.json")
                        .toPath()
        );
        MockErro.mockBadRequest( mockMvc, request, CLIENTE_ENDPOINT );
    }

    @Test
    @DisplayName("Documento deve ser unico")
    @Sql(scripts = {"cliente-insert.sql"})
    void documentoUnico() throws Exception {
        final String request = Files.readString(
                ResourceUtils
                        .getFile("classpath:br/com/projeto/casa/codigo/cliente/cliente-ja-cadastrado-documento.json")
                        .toPath()
        );
        MockErro.mockBadRequest( mockMvc, request, CLIENTE_ENDPOINT );
    }

    @Test
    @DisplayName("Pais não foi encontrado")
    void paisNaoEncontrado() throws Exception {
        final String request = Files.readString(
                ResourceUtils
                        .getFile("classpath:br/com/projeto/casa/codigo/cliente/cliente-ja-cadastrado-documento.json")
                        .toPath()
        );
        MockErro.mockBadRequest( mockMvc, request, CLIENTE_ENDPOINT );
    }

    @Test
    @DisplayName("Estado não foi encontrado")
    @Sql(statements = "INSERT INTO Pais(nome) VALUES ('Brasil')")
    void estadoNaoEncontrado() throws Exception {
        final String request = Files.readString(
                ResourceUtils
                        .getFile("classpath:br/com/projeto/casa/codigo/cliente/cliente-ja-cadastrado-documento.json")
                        .toPath()
        );
        MockErro.mockBadRequest( mockMvc, request, CLIENTE_ENDPOINT );
    }

    @Test
    @DisplayName("Cpf invalido")
    @Sql(scripts = {"cliente-insert.sql"})
    void cpfInvalido() throws Exception {
        final String request = Files.readString(
                ResourceUtils
                        .getFile("classpath:br/com/projeto/casa/codigo/cliente/cpf-invalido.json")
                        .toPath()
        );
        MockErro.mockBadRequest( mockMvc, request, CLIENTE_ENDPOINT );
    }

    @Test
    @DisplayName("Cnpj valido")
    @Sql(scripts = {"cliente-insert.sql"})
    void cnpjValido() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders
                                .post(CLIENTE_ENDPOINT)
                                .contentType( MediaType.APPLICATION_JSON )
                                .content(
                                        Files.readString(
                                                ResourceUtils
                                                        .getFile("classpath:br/com/projeto/casa/codigo/cliente/cnpj-valido.json")
                                                        .toPath()
                                        )
                                )
                ).andDo( MockMvcResultHandlers.print() )
                .andExpect( MockMvcResultMatchers.status().isOk() )
                .andExpect( MockMvcResultMatchers.content().contentType( MediaType.APPLICATION_JSON ) )
                .andExpect( MockMvcResultMatchers.jsonPath("$.id").value(2L) );
    }

    @Test
    @DisplayName("Cnpj invalido")
    @Sql(scripts = {"cliente-insert.sql"})
    void cnpjInvalido() throws Exception {
        final String request = Files.readString(
                ResourceUtils
                        .getFile("classpath:br/com/projeto/casa/codigo/cliente/cnpj-invalido.json")
                        .toPath()
        );
        MockErro.mockBadRequest( mockMvc, request, CLIENTE_ENDPOINT );
    }

}
