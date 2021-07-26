package br.com.projeto.casa.codigo.autor.repositorio;

import br.com.projeto.casa.codigo.autor.modelo.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AutorRepositorio extends JpaRepository<Autor, Long> {

    Optional<Autor> findByEmail( final String email );

}
