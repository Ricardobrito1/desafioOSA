package br.com.santander.desafio.infra.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AgencyJpaRepository extends JpaRepository<AgencyEntity, UUID> {
}
