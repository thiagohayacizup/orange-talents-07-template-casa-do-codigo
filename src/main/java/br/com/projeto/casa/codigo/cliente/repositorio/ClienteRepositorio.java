package br.com.projeto.casa.codigo.cliente.repositorio;

import br.com.projeto.casa.codigo.cliente.modelo.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepositorio extends JpaRepository<Cliente, Long> {
}
