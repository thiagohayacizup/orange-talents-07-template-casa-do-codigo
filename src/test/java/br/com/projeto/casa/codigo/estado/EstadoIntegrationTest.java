package br.com.projeto.casa.codigo.estado;

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
@DirtiesContext( classMode = DirtiesContext.ClassMode.BEFORE_CLASS )
class EstadoIntegrationTest {

    private static final String ESTADO_ENDPOINT = "/estado";

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Estado cadastrado sucesso")
    @Sql(statements = "INSERT INTO Pais(nome) VALUES ('Brasil')")
    void estadoCadastradoSucesso() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders
                                .post(ESTADO_ENDPOINT)
                                .contentType( MediaType.APPLICATION_JSON )
                                .content(
                                        Files.readString(
                                                ResourceUtils
                                                        .getFile("classpath:br/com/projeto/casa/codigo/estado/estado-cadastrado-sucesso.json")
                                                        .toPath()
                                        )
                                )
                ).andDo( MockMvcResultHandlers.print() )
                .andExpect( MockMvcResultMatchers.status().isCreated() )
                .andExpect( MockMvcResultMatchers.content().contentType( MediaType.APPLICATION_JSON ) )
                .andExpect( MockMvcResultMatchers.jsonPath("$.nome").value("Sao Paulo") )
                .andExpect( MockMvcResultMatchers.jsonPath("$.pais").value("Brasil") );
    }

    @Test
    @DisplayName("Pais nao encontrado")
    void paisNaoEncontrado() throws Exception {
        final String request = Files.readString(
                ResourceUtils
                        .getFile("classpath:br/com/projeto/casa/codigo/estado/estado-sem-pais-cadastrado.json")
                        .toPath()
        );
        MockErro.mockBadRequest( mockMvc, request, ESTADO_ENDPOINT );
    }

    @Test
    @DisplayName("Estado invalido")
    void estadoInvalido() throws Exception {
        final String request = Files.readString(
                ResourceUtils
                        .getFile("classpath:br/com/projeto/casa/codigo/estado/estado-invalido.json")
                        .toPath()
        );
        MockErro.mockBadRequest( mockMvc, request, ESTADO_ENDPOINT );
    }

    @Test
    @DisplayName("Estado ja cadastrado para um pais")
    @Sql(statements = {
            "INSERT INTO Pais(nome) VALUES ('Japao')",
            "INSERT INTO Estado(nome, pais_id) VALUES('Tokyo', 1)"
    })
    void estadoJaCadastradoParaPais() throws Exception {
        final String request = Files.readString(
                ResourceUtils
                        .getFile("classpath:br/com/projeto/casa/codigo/estado/estado-ja-cadastrado.json")
                        .toPath()
        );
        MockErro.mockBadRequest( mockMvc, request, ESTADO_ENDPOINT );
    }

}
