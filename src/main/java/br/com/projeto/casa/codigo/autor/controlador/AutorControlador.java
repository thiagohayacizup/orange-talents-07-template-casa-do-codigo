package br.com.projeto.casa.codigo.autor.controlador;

import br.com.projeto.casa.codigo.autor.repositorio.AutorRepositorio;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
class AutorControlador {

    private final AutorRepositorio autorRepositorio;

    AutorControlador(AutorRepositorio autorRepositorio) {
        this.autorRepositorio = autorRepositorio;
    }

    @PostMapping("/autor")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody AutorResposta cadastrarAutor(@RequestBody @Valid final AutorRequisicao autorRequisicao ){
        return autorRequisicao.cadastrar( autorRepositorio );
    }

}
