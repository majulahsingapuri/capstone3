package com.example.capstone3;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByAccount(Account account);

    @Query(value = "SELECT CASE WHEN balance > 0 THEN balance ELSE 0 END as balance\n" +
        "FROM (\n" +
        "SELECT SUM(CASE WHEN type = 0 THEN amount ELSE 0 END) - SUM(CASE WHEN type = 1 THEN amount ELSE 0 END) as balance\n"+
        "FROM transactions\n" +
        "WHERE account_id = :accountId)",
            nativeQuery = true)
    double getBalance(@Param("accountId") Long accountId);
}
