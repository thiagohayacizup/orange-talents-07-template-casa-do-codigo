package br.com.projeto.casa.codigo.cliente.controlador;

import br.com.projeto.casa.codigo.cliente.repositorio.ClienteRepositorio;
import br.com.projeto.casa.codigo.estado.repositorio.EstadoRepositorio;
import br.com.projeto.casa.codigo.pais.repositorio.PaisRepositorio;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
class ClienteControlador {

    private final ClienteRepositorio clienteRepositorio;
    private final PaisRepositorio paisRepositorio;
    private final EstadoRepositorio estadoRepositorio;

    ClienteControlador(final ClienteRepositorio clienteRepositorio, final PaisRepositorio paisRepositorio, final EstadoRepositorio estadoRepositorio) {
        this.clienteRepositorio = clienteRepositorio;
        this.paisRepositorio = paisRepositorio;
        this.estadoRepositorio = estadoRepositorio;
    }

    @PostMapping("/cliente")
    @ResponseStatus( HttpStatus.OK )
    public @ResponseBody ClienteResposta cadastrar( @RequestBody @Valid final ClienteRequisicao clienteRequisicao ){
        return clienteRequisicao.cadastrar( clienteRepositorio, paisRepositorio, estadoRepositorio );
    }

}
