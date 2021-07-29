package br.com.projeto.casa.codigo.livro;

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
import org.hamcrest.Matchers;

import java.nio.file.Files;


@ActiveProfiles( value = "test" )
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext( classMode = DirtiesContext.ClassMode.BEFORE_CLASS )
class LivroTodosIntegrationTest {

    private static final String LIVRO_ENDPOINT = "/livros";

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Não ha livros cadastrados")
    public void livrosNaoCadastrados() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders
                                .get(LIVRO_ENDPOINT)
                                .contentType( MediaType.APPLICATION_JSON )
                ).andDo( MockMvcResultHandlers.print() )
                .andExpect( MockMvcResultMatchers.status().isOk() )
                .andExpect( MockMvcResultMatchers.content().contentType( MediaType.APPLICATION_JSON ) )
                .andExpect( MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(0) ) );
    }

    @Test
    @DisplayName("Livros cadastrados retornados")
    @Sql(scripts = {"sql/inserir-livro-titulo-unico.sql"})
    public void possuiLivrosCadastrados() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders
                                .get(LIVRO_ENDPOINT)
                                .contentType( MediaType.APPLICATION_JSON )
                ).andDo( MockMvcResultHandlers.print() )
                .andExpect( MockMvcResultMatchers.status().isOk() )
                .andExpect( MockMvcResultMatchers.content().contentType( MediaType.APPLICATION_JSON ) )
                .andExpect( MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1) ) )
                .andExpect( MockMvcResultMatchers.jsonPath("$[0].id").value(1) )
                .andExpect( MockMvcResultMatchers.jsonPath("$[0].titulo").value("Contos") );
    }

}
