package br.com.projeto.casa.codigo.advice;

import br.com.projeto.casa.codigo.autor.modelo.excessao.AutorNaoEncontrado;
import br.com.projeto.casa.codigo.autor.modelo.excessao.EmailFormatoInvalidoException;
import br.com.projeto.casa.codigo.categoria.modelo.excessao.CategoriaNaoEncontrada;
import br.com.projeto.casa.codigo.estado.modelo.excessao.EstadoComPaisJaCadastradoException;
import br.com.projeto.casa.codigo.estado.modelo.excessao.EstadoNaoEncontradoException;
import br.com.projeto.casa.codigo.livro.modelo.excessao.LivroNaoEncontradoException;
import br.com.projeto.casa.codigo.pais.modelo.excessao.PaisNaoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Optional;
import java.util.Set;

@RestControllerAdvice
class Notificacao {

    @ExceptionHandler({EmailFormatoInvalidoException.class})
    @ResponseStatus( HttpStatus.BAD_REQUEST )
    RespostaErro emailFormatoInvalido( final EmailFormatoInvalidoException exception ){
        return new RespostaErro( 400, exception.getMessage() );
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus( HttpStatus.BAD_REQUEST )
    RespostaErro dadosInvalidos( final MethodArgumentNotValidException exception ){
        return new RespostaErro(400, exception.getFieldErrors().get(0).getDefaultMessage() );
    }

    @ExceptionHandler({CategoriaNaoEncontrada.class})
    @ResponseStatus( HttpStatus.BAD_REQUEST )
    RespostaErro categoriaNaoEncontrada( final CategoriaNaoEncontrada exception ){
        return new RespostaErro( 400, exception.getMessage() );
    }

    @ExceptionHandler({AutorNaoEncontrado.class})
    @ResponseStatus( HttpStatus.BAD_REQUEST )
    RespostaErro autorNaoEncontrado( final AutorNaoEncontrado exception ){
        return new RespostaErro( 400, exception.getMessage() );
    }

    @ExceptionHandler({LivroNaoEncontradoException.class})
    @ResponseStatus( HttpStatus.BAD_REQUEST )
    RespostaErro livroNaoEncontrado( final LivroNaoEncontradoException exception ){
        return new RespostaErro( 400, exception.getMessage() );
    }

    @ExceptionHandler({PaisNaoEncontradoException.class})
    @ResponseStatus( HttpStatus.BAD_REQUEST )
    RespostaErro paisNaoEncontrado( final PaisNaoEncontradoException exception ){
        return new RespostaErro( 400, exception.getMessage() );
    }

    @ExceptionHandler({EstadoComPaisJaCadastradoException.class})
    @ResponseStatus( HttpStatus.BAD_REQUEST )
    RespostaErro estadoJaCadastrado( final EstadoComPaisJaCadastradoException exception ){
        return new RespostaErro( 400, exception.getMessage() );
    }

    @ExceptionHandler({EstadoNaoEncontradoException.class})
    @ResponseStatus( HttpStatus.BAD_REQUEST )
    RespostaErro estadoNaoEncontrado( final EstadoNaoEncontradoException exception ){
        return new RespostaErro( 400, exception.getMessage() );
    }

    @ExceptionHandler({ConstraintViolationException.class})
    @ResponseStatus( HttpStatus.BAD_REQUEST )
    RespostaErro dadosInvalidos( final ConstraintViolationException exception ){
        final Set<ConstraintViolation<?>> violations = exception.getConstraintViolations();
        final Optional<ConstraintViolation<?>> first = violations.stream().findFirst();
        if( first.isEmpty() )
            return new RespostaErro( 400, exception.getMessage());
        return new RespostaErro( 400, first.get().getMessage() );
    }

}
