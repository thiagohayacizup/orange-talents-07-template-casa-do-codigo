package br.com.projeto.casa.codigo.advice;

import br.com.projeto.casa.codigo.autor.modelo.excessao.AutorJaCadastradoException;
import br.com.projeto.casa.codigo.autor.modelo.excessao.EmailFormatoInvalidoException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

@RestControllerAdvice
class Notificacao {

    @ExceptionHandler({EmailFormatoInvalidoException.class})
    @ResponseStatus( HttpStatus.BAD_REQUEST )
    RespostaErro emailFormatoInvalido( final EmailFormatoInvalidoException exception ){
        return new RespostaErro( 400, exception.getMessage() );
    }

    @ExceptionHandler({AutorJaCadastradoException.class})
    @ResponseStatus( HttpStatus.BAD_REQUEST )
    RespostaErro autorJaCadastrado( final AutorJaCadastradoException exception ){
        return new RespostaErro( 400, exception.getMessage() );
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus( HttpStatus.BAD_REQUEST )
    RespostaErro dadosInvalidos( final MethodArgumentNotValidException exception ){
        return new RespostaErro(400, exception.getFieldErrors().get(0).getDefaultMessage() );
    }

    @ExceptionHandler({ConstraintViolationException.class})
    @ResponseStatus( HttpStatus.BAD_REQUEST )
    RespostaErro dadosInvalidos( final ConstraintViolationException exception ){
        return new RespostaErro(400, exception.getMessage() );
    }


}
