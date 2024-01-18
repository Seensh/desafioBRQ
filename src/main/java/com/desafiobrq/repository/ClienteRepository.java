package com.desafiobrq.repository;

import com.desafiobrq.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Cliente findByDocumento(String documentoCliente);
}
