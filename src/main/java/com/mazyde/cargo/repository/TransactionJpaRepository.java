package com.mazyde.cargo.repository;

import com.mazyde.cargo.model.transaction.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TransactionJpaRepository extends PagingAndSortingRepository<Transaction, Long> {

    Page<Transaction> findAllByReceiptNumber(String receiptNumber, Pageable pageable);

}
