package br.com.projeto.casa.codigo.categoria;

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
class CategoriaIntegrationTest {

    private static final String CATEGORIA_ENDPOINT = "/categoria";

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Categoria cadastrada sucesso")
    void categoriaCadastradaSucesso() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders
                                .post(CATEGORIA_ENDPOINT)
                                .contentType( MediaType.APPLICATION_JSON )
                                .content(
                                        Files.readString(
                                                ResourceUtils
                                                        .getFile("classpath:br/com/projeto/casa/codigo/categoria/categoria-cadastrada-sucesso.json")
                                                        .toPath()
                                        )
                                )
                ).andDo( MockMvcResultHandlers.print() )
                .andExpect( MockMvcResultMatchers.status().isOk() )
                .andExpect( MockMvcResultMatchers.content().contentType( MediaType.APPLICATION_JSON ) )
                .andExpect( MockMvcResultMatchers.jsonPath("$.nome").value("Software") );
    }

    @Test
    @DisplayName("Categoria ja cadastrada")
    @Sql(scripts = {"inserir-categoria.sql"})
    void categoriaJaCadastrada() throws Exception {
        final String request = Files.readString(
                ResourceUtils
                        .getFile("classpath:br/com/projeto/casa/codigo/categoria/categoria-ja-cadastrada.json")
                        .toPath()
        );
        MockErro.mockBadRequest( mockMvc, request, CATEGORIA_ENDPOINT );
    }

    @Test
    @DisplayName("Categoria nome invalido")
    void categoriaNomeInvalido() throws Exception {
        final String request = Files.readString(
                ResourceUtils
                        .getFile("classpath:br/com/projeto/casa/codigo/categoria/categoria-invalida.json")
                        .toPath()
        );
        MockErro.mockBadRequest( mockMvc, request, CATEGORIA_ENDPOINT );
    }

}
