package com.sulab.sulab.repositories;

import com.sulab.sulab.models.Lote;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface LoteRepository extends JpaRepository<Lote, Integer> {
}
