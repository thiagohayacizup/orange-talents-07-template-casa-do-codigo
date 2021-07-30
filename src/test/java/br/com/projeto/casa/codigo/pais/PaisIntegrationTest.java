package br.com.projeto.casa.codigo.pais;

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
class PaisIntegrationTest {

     private static final String PAIS_ENDPOINT = "/pais";

     @Autowired
     private MockMvc mockMvc;

     @Test
     @DisplayName("Pais cadastrado sucesso")
     void paisCadastradoSucesso() throws Exception {
          mockMvc.perform(
                          MockMvcRequestBuilders
                                  .post(PAIS_ENDPOINT)
                                  .contentType( MediaType.APPLICATION_JSON )
                                  .content(
                                          Files.readString(
                                                  ResourceUtils
                                                          .getFile("classpath:br/com/projeto/casa/codigo/pais/pais-cadastrado-sucesso.json")
                                                          .toPath()
                                          )
                                  )
                  ).andDo( MockMvcResultHandlers.print() )
                  .andExpect( MockMvcResultMatchers.status().isCreated() )
                  .andExpect( MockMvcResultMatchers.content().contentType( MediaType.APPLICATION_JSON ) )
                  .andExpect( MockMvcResultMatchers.jsonPath("$.nome").value("Brasil") );
     }

     @Test
     @DisplayName("Pais ja cadastrado")
     @Sql( statements = "INSERT INTO Pais (nome) VALUES ('Japao')" )
     void paisJaCadastrado() throws Exception {
          final String request = Files.readString(
                  ResourceUtils
                          .getFile("classpath:br/com/projeto/casa/codigo/pais/pais-ja-cadastrado.json")
                          .toPath()
          );
          MockErro.mockBadRequest( mockMvc, request, PAIS_ENDPOINT );
     }

     @Test
     @DisplayName("Nome pais invalido")
     void nomePaisInvalido() throws Exception{
          final String request = Files.readString(
                  ResourceUtils
                          .getFile("classpath:br/com/projeto/casa/codigo/pais/pais-invalido.json")
                          .toPath()
          );
          MockErro.mockBadRequest( mockMvc, request, PAIS_ENDPOINT );
     }

}
