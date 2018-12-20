package dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import dev.domain.Achat;

public interface AchatRepo extends JpaRepository<Achat, Long>,  JpaSpecificationExecutor<Achat> {

}
