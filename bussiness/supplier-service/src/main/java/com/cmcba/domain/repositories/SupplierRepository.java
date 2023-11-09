package com.cmcba.domain.repositories;

import com.cmcba.domain.entities.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author claudio.vilas
 * date 11/2023
 */

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
}
