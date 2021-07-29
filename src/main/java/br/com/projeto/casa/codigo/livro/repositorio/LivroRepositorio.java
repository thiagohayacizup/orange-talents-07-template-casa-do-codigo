package br.com.projeto.casa.codigo.livro.repositorio;

import br.com.projeto.casa.codigo.livro.modelo.Livro;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LivroRepositorio extends JpaRepository<Livro, Long> {

    @Query("SELECT l.id AS id, l.titulo AS titulo FROM Livro l")
    List<LivroProjecao> buscarLivros();

}
