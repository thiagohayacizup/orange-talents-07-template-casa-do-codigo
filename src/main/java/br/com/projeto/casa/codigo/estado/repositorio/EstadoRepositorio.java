package br.com.projeto.casa.codigo.estado.repositorio;

import br.com.projeto.casa.codigo.estado.modelo.Estado;
import br.com.projeto.casa.codigo.pais.modelo.Pais;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EstadoRepositorio extends JpaRepository<Estado, Long> {

    Optional<Estado> findByNomeAndPais( final String nome, final Pais pais );

}
