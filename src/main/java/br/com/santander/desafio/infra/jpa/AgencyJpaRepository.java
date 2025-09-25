package br.com.santander.desafio.infra.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AgencyJpaRepository extends JpaRepository<AgencyEntity, String> {
}
