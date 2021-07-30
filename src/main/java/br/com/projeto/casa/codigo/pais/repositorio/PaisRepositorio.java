package br.com.projeto.casa.codigo.pais.repositorio;

import br.com.projeto.casa.codigo.pais.modelo.Pais;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaisRepositorio extends JpaRepository<Pais, Long> {
}
