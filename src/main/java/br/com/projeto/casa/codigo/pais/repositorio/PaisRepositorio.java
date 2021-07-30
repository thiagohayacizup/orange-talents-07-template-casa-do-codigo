package br.com.projeto.casa.codigo.pais.repositorio;

import br.com.projeto.casa.codigo.pais.modelo.Pais;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaisRepositorio extends JpaRepository<Pais, Long> {

    Optional<Pais> findByNome( final String nome );

}
