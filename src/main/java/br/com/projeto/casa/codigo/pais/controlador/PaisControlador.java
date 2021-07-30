package br.com.projeto.casa.codigo.pais.controlador;

import br.com.projeto.casa.codigo.pais.repositorio.PaisRepositorio;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
class PaisControlador {

    private final PaisRepositorio paisRepositorio;

    PaisControlador(final PaisRepositorio paisRepositorio) {
        this.paisRepositorio = paisRepositorio;
    }

    @PostMapping("/pais")
    @ResponseStatus( HttpStatus.CREATED )
    public @ResponseBody PaisResposta cadastrar( @RequestBody @Valid final PaisRequisicao paisRequisicao ){
        return paisRequisicao.cadastrar( paisRepositorio );
    }

}
