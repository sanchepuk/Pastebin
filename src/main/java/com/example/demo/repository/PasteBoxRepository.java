package com.example.demo.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface PasteBoxRepository extends JpaRepository<PasteBoxEntity, Long> {
    Optional<PasteBoxEntity> findByHash(String hash);
    List<PasteBoxEntity> findByIsPublicTrueAndExpirationTimeAfterOrderByIdDesc(LocalDateTime now, Pageable pageable);
}
