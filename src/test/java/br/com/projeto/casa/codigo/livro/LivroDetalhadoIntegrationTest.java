package br.com.projeto.casa.codigo.livro;

import org.hamcrest.Matchers;
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

import java.math.BigDecimal;

@ActiveProfiles( value = "test" )
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext( classMode = DirtiesContext.ClassMode.BEFORE_CLASS )
class LivroDetalhadoIntegrationTest {

    private static final String LIVRO_ENDPOINT = "/livro";

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Livro nao encontrado")
    public void livroNaoEncontrado() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders
                                .get(LIVRO_ENDPOINT + "/1")
                                .contentType( MediaType.APPLICATION_JSON )
                ).andDo( MockMvcResultHandlers.print() )
                .andExpect( MockMvcResultMatchers.status().isBadRequest() )
                .andExpect( MockMvcResultMatchers.content().contentType( MediaType.APPLICATION_JSON ) )
                .andExpect( MockMvcResultMatchers.jsonPath("$.codigo" ).value( 400 ) );
    }

    @Test
    @DisplayName("Livro encontrado")
    @Sql(scripts = {"sql/inserir-livro-titulo-unico.sql"})
    public void livroEncontrado() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders
                                .get(LIVRO_ENDPOINT + "/1")
                                .contentType( MediaType.APPLICATION_JSON )
                ).andDo( MockMvcResultHandlers.print() )
                .andExpect( MockMvcResultMatchers.status().isOk() )
                .andExpect( MockMvcResultMatchers.content().contentType( MediaType.APPLICATION_JSON ) )
                .andExpect( MockMvcResultMatchers.jsonPath("$.titulo").value("Contos") )
                .andExpect( MockMvcResultMatchers.jsonPath("$.resumo").value("") )
                .andExpect( MockMvcResultMatchers.jsonPath("$.sumario").value("") )
                .andExpect( MockMvcResultMatchers.jsonPath("$.preco").value(new BigDecimal("50.0") ) )
                .andExpect( MockMvcResultMatchers.jsonPath("$.numeroPaginas").value(150) )
                .andExpect( MockMvcResultMatchers.jsonPath("$.isbn").value("ISBN 467-03776") )
                .andExpect( MockMvcResultMatchers.jsonPath("$.dataLancamento").value("2022-01-01") )
                .andExpect( MockMvcResultMatchers.jsonPath("$.categoria").value("Aventura") )
                .andExpect( MockMvcResultMatchers.jsonPath("$.autor").value("Joao Silva") );
    }

}
