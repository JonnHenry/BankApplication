package com.devsu.hackerearth.backend.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Date;
import com.devsu.hackerearth.backend.account.model.Transaction;
import com.devsu.hackerearth.backend.account.model.dto.BankStatementDto;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query(value = "SELECT t.* FROM Transaction t where t.account_id=:accountId ORDER BY t.date DESC LIMIT 1", nativeQuery = true)
    public Transaction getLastByAccountId(@Param("accountId") Long accountId);

    @Query("SELECT new com.devsu.hackerearth.backend.account.model.dto.BankStatementDto("+
            "t.date,"+
            "str(a.clientId),"+
            "a.number,"+
            "a.type,"+
            "a.initialAmount,"+
            "a.isActive,"+
            "t.type,"+
            "t.amount,"+
            "t.balance)"+
            "FROM Transaction t JOIN t.account a "+
            "WHERE a.clientId=:clientId and t.date BETWEEN :dateTransactionStart AND :dateTransactionEnd")
    public List<BankStatementDto> getAllByAccountClientIdAndDateBetween(@Param("clientId") Long clientId,
            @Param("dateTransactionStart") Date dateTransactionStart,
            @Param("dateTransactionEnd") Date dateTransactionEnd);

}
