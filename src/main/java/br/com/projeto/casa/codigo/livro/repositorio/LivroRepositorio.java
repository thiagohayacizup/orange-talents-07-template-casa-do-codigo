package br.com.projeto.casa.codigo.livro.repositorio;

import br.com.projeto.casa.codigo.livro.modelo.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepositorio extends JpaRepository<Livro, Long> {
}
