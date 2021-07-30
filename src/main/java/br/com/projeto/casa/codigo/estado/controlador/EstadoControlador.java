package br.com.projeto.casa.codigo.estado.controlador;

import br.com.projeto.casa.codigo.estado.repositorio.EstadoRepositorio;
import br.com.projeto.casa.codigo.pais.repositorio.PaisRepositorio;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
class EstadoControlador {

    private final EstadoRepositorio estadoRepositorio;
    private final PaisRepositorio paisRepositorio;

    EstadoControlador(final EstadoRepositorio estadoRepositorio, final PaisRepositorio paisRepositorio) {
        this.estadoRepositorio = estadoRepositorio;
        this.paisRepositorio = paisRepositorio;
    }

    @PostMapping("/estado")
    @ResponseStatus( HttpStatus.CREATED )
    public @ResponseBody EstadoResposta cadastrar(@RequestBody @Valid final EstadoRequisicao estadoRequisicao ){
        return estadoRequisicao.cadastrar( estadoRepositorio, paisRepositorio );
    }

}
