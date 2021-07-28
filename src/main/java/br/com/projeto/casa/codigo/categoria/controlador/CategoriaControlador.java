package br.com.projeto.casa.codigo.categoria.controlador;

import br.com.projeto.casa.codigo.categoria.repositorio.CategoriaRepositorio;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
class CategoriaControlador {

    private final CategoriaRepositorio categoriaRepositorio;

    CategoriaControlador( final CategoriaRepositorio categoriaRepositorio ) {
        this.categoriaRepositorio = categoriaRepositorio;
    }

    @PostMapping("/categoria")
    @ResponseStatus( HttpStatus.OK )
    public @ResponseBody CategoriaResposta cadastrar( @RequestBody @Valid final CategoriaRequisicao categoriaRequisicao ){
        return categoriaRequisicao.cadastrar( categoriaRepositorio );
    }

}
