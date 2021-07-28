package br.com.projeto.casa.codigo.livro;

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
public class LivroIntegrationTest {

    public static final String LIVRO_ENDPOINT = "/livro";

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Livro cadastrado com sucesso")
    @Sql(scripts = {"sql/inserir-livro-sucesso.sql"})
    public void livroCadastradoSucesso() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders
                                .post(LIVRO_ENDPOINT)
                                .contentType( MediaType.APPLICATION_JSON )
                                .content(
                                        Files.readString(
                                                ResourceUtils
                                                        .getFile("classpath:br/com/projeto/casa/codigo/livro/livro-cadastrado-sucesso.json")
                                                        .toPath()
                                        )
                                )
                ).andDo( MockMvcResultHandlers.print() )
                .andExpect( MockMvcResultMatchers.status().isOk() )
                .andExpect( MockMvcResultMatchers.content().contentType( MediaType.APPLICATION_JSON ) )
                .andExpect( MockMvcResultMatchers.jsonPath("$.titulo").value("Livro de Aventuras") )
                .andExpect( MockMvcResultMatchers.jsonPath("$.isbn").value("ISBN 123-543-65467-5433456-5544") );
    }

    @Test
    @DisplayName("Livro deve ter titulo unico")
    @Sql(scripts = {"sql/inserir-livro-titulo-unico.sql"})
    public void livroTituloUnico() throws Exception {
        final String request = Files.readString(
                ResourceUtils
                        .getFile("classpath:br/com/projeto/casa/codigo/livro/livro-titulo-unico.json")
                        .toPath()
        );
        MockErro.mockBadRequest( mockMvc, request, LIVRO_ENDPOINT );
    }

    @Test
    @DisplayName("Livro deve ter isbn unico")
    @Sql(scripts = {"sql/inserir-livro-isbn-unico.sql"})
    public void livroIsbnUnico() throws Exception {
        final String request = Files.readString(
                ResourceUtils
                        .getFile("classpath:br/com/projeto/casa/codigo/livro/livro-isbn-unico.json")
                        .toPath()
        );
        MockErro.mockBadRequest( mockMvc, request, LIVRO_ENDPOINT );
    }

    @Test
    @DisplayName("Livro deve ter o preco no minimo 20.0")
    @Sql(scripts = {"sql/preco-menor-minimo.sql"})
    public void livroPrecoMenorMinimo() throws Exception {
        final String request = Files.readString(
                ResourceUtils
                        .getFile("classpath:br/com/projeto/casa/codigo/livro/livro-preco-menor-minimo.json")
                        .toPath()
        );
        MockErro.mockBadRequest( mockMvc, request, LIVRO_ENDPOINT );
    }

    @Test
    @DisplayName("Livro deve ter numero de paginas no minimo 100")
    @Sql(scripts = {"sql/numero-paginas.sql"})
    public void livroNumeroPaginasMenorMinimo() throws Exception {
        final String request = Files.readString(
                ResourceUtils
                        .getFile("classpath:br/com/projeto/casa/codigo/livro/livro-numero-paginas-menor-minimo.json")
                        .toPath()
        );
        MockErro.mockBadRequest( mockMvc, request, LIVRO_ENDPOINT );
    }

    @Test
    @DisplayName("Livro data de lançamento nao pode ser no passado")
    @Sql(scripts = {"sql/data-passada.sql"})
    public void livroDataPassada() throws Exception {
        final String request = Files.readString(
                ResourceUtils
                        .getFile("classpath:br/com/projeto/casa/codigo/livro/livro-data-passada.json")
                        .toPath()
        );
        MockErro.mockBadRequest( mockMvc, request, LIVRO_ENDPOINT );
    }

    @Test
    @DisplayName("Categoria não encontrado")
    @Sql(statements = "INSERT INTO Autor (nome, email, descricao, data_cadastro) " +
                    "VALUES ('Caio Souza', 'caio.souza@email.com', 'descricao', NOW());")
    public void categoriaNaoEncontrada() throws Exception {
        final String request = Files.readString(
                ResourceUtils
                        .getFile("classpath:br/com/projeto/casa/codigo/livro/categoria-nao-encontrada.json")
                        .toPath()
        );
        MockErro.mockBadRequest( mockMvc, request, LIVRO_ENDPOINT );
    }

    @Test
    @DisplayName("Autor não Encontrado")
    @Sql(statements = "INSERT INTO Categoria(nome) VALUES ('Sifi')")
    public void autorNaoEncontrado() throws Exception {
        final String request = Files.readString(
                ResourceUtils
                        .getFile("classpath:br/com/projeto/casa/codigo/livro/autor-nao-encontrado.json")
                        .toPath()
        );
        MockErro.mockBadRequest( mockMvc, request, LIVRO_ENDPOINT );
    }

}
