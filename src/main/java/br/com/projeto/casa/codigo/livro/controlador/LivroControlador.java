package br.com.projeto.casa.codigo.livro.controlador;

import br.com.projeto.casa.codigo.autor.repositorio.AutorRepositorio;
import br.com.projeto.casa.codigo.categoria.repositorio.CategoriaRepositorio;
import br.com.projeto.casa.codigo.livro.repositorio.LivroRepositorio;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
class LivroControlador {

    public final LivroRepositorio livroRepositorio;
    public final CategoriaRepositorio categoriaRepositorio;
    public final AutorRepositorio autorRepositorio;

    LivroControlador(final LivroRepositorio livroRepositorio, final CategoriaRepositorio categoriaRepositorio, final AutorRepositorio autorRepositorio) {
        this.livroRepositorio = livroRepositorio;
        this.categoriaRepositorio = categoriaRepositorio;
        this.autorRepositorio = autorRepositorio;
    }

    @PostMapping("/livro")
    @ResponseStatus( HttpStatus.OK )
    public @ResponseBody LivroResposta cadastrar( @RequestBody @Valid final LivroRequisicao livroRequisicao ){
        return livroRequisicao.cadastrar( livroRepositorio, categoriaRepositorio, autorRepositorio );
    }

}
