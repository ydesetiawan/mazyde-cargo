package com.mazyde.cargo.repository;

import com.mazyde.cargo.model.transaction.TransactionPhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransactionPhotoJpaRepository extends JpaRepository<TransactionPhoto, Long> {

    Optional<TransactionPhoto> findFirstByTransactionIdOrderByCreatedDateDesc(Long transactionId);
}
