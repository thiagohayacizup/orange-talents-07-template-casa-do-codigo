package br.com.projeto.casa.codigo.cliente.controlador;

import br.com.projeto.casa.codigo.cliente.modelo.Cliente;
import br.com.projeto.casa.codigo.cliente.repositorio.ClienteRepositorio;
import br.com.projeto.casa.codigo.estado.modelo.Estado;
import br.com.projeto.casa.codigo.estado.repositorio.EstadoRepositorio;
import br.com.projeto.casa.codigo.pais.modelo.Pais;
import br.com.projeto.casa.codigo.pais.repositorio.PaisRepositorio;
import br.com.projeto.casa.codigo.validador.Documento;
import br.com.projeto.casa.codigo.validador.ValorUnico;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

class ClienteRequisicao {

    private final Cliente.Builder builder;

    ClienteRequisicao(){
        builder = Cliente.construtor();
    }

    @NotBlank
    @ValorUnico( campo = "email", dominio = Cliente.class )
    private String email;

    @NotBlank
    @Size( min = 11, max = 18 )
    @Documento
    @ValorUnico( campo = "documento", dominio = Cliente.class )
    private String documento;

    void setEmail(final String email) {
        this.email = email;
    }

    void setNome(final String nome) {
        builder.comNome( nome );
    }

    void setSobrenome(final String sobrenome) {
        builder.comSobrenome( sobrenome );
    }

    void setDocumento(final String documento) {
        this.documento = documento;
    }

    void setEndereco(final String endereco) {
        builder.comEndereco( endereco );
    }

    void setComplemento(final String complemento) {
        builder.comComplemento( complemento );
    }

    void setCidade(final String cidade) {
        builder.comCidade( cidade );
    }

    @NotNull
    private Long paisId;

    void setPaisId(final Long paisId) {
        this.paisId = paisId;
    }

    @NotNull
    private Long estadoId;

    void setEstadoId(final Long estadoId) {
        this.estadoId = estadoId;
    }

    void setTelefone(final String telefone) {
        builder.comTelefone( telefone );
    }

    void setCep(final String cep) {
        builder.comCep( cep );
    }

    ClienteResposta cadastrar(final ClienteRepositorio clienteRepositorio, final PaisRepositorio paisRepositorio, final EstadoRepositorio estadoRepositorio ){
        return new ClienteResposta(
                builder
                        .comEmail( email )
                        .comDocumento( documento )
                        .comPais( Pais.buscarPorId( paisId, paisRepositorio ) )
                        .comEstado( Estado.buscarPorId( estadoId, estadoRepositorio ) )
                        .construir()
                        .cadastrar( clienteRepositorio )
        );
    }

}
