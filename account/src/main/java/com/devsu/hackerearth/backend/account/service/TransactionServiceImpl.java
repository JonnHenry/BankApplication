package com.devsu.hackerearth.backend.account.service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import java.util.Optional;
import com.devsu.hackerearth.backend.account.constants.AccountConstants;
import com.devsu.hackerearth.backend.account.exception.AlreadyExistException;
import com.devsu.hackerearth.backend.account.exception.NonExecutableTransactionExeption;
import com.devsu.hackerearth.backend.account.exception.NotFoundException;
import com.devsu.hackerearth.backend.account.mapper.TransactionMapper;
import com.devsu.hackerearth.backend.account.model.Account;
import com.devsu.hackerearth.backend.account.model.dto.BankStatementDto;
import com.devsu.hackerearth.backend.account.model.dto.TransactionDto;
import com.devsu.hackerearth.backend.account.repository.AccountRepository;
import com.devsu.hackerearth.backend.account.repository.TransactionRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository, AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public List<TransactionDto> getAll() {
        // Get all transactions
        return transactionRepository.findAll()
                .stream()
                .map(TransactionMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TransactionDto getById(Long id) {
        // Get transactions by id
        return transactionRepository.findById(id).map(TransactionMapper.INSTANCE::toDTO).orElse(null);
    }

    @Transactional
    @Override
    public TransactionDto create(TransactionDto transactionDto) {
        // Create transaction
        if (Objects.nonNull(transactionDto.getId()) && transactionRepository.findById(transactionDto.getId()).isPresent()) {
            throw new AlreadyExistException(
                    String.format(AccountConstants.ACCOUNT_ALREADY_EXIST, transactionDto.getId()));
        }

        Optional<Account> account = accountRepository.findById(transactionDto.getAccountId());
        if (account.isEmpty()){
            throw new NotFoundException(
                    String.format(AccountConstants.ACCOUNT_NOT_EXIST, transactionDto.getAccountId())
            );
        }
        double actualBalance;
        TransactionDto lastTransaction = this.getLastByAccountId(transactionDto.getAccountId());
        if (lastTransaction==null){
            actualBalance = account.get().getInitialAmount()+transactionDto.getAmount();
        }else{
            actualBalance = lastTransaction.getBalance()+transactionDto.getAmount();
        }
        if (actualBalance<0){
            throw new NonExecutableTransactionExeption(AccountConstants.TRANSACTION_NOT_EXECUTABLE);
        }
        transactionDto.setBalance(actualBalance);
        transactionDto.setDate(new Date());
        return TransactionMapper.INSTANCE.toDTO(
            transactionRepository.save(TransactionMapper.INSTANCE.toEntity(transactionDto)));
    }

    @Override
    public List<BankStatementDto> getAllByAccountClientIdAndDateBetween(Long clientId, Date dateTransactionStart,
            Date dateTransactionEnd) {
        // Report
        return transactionRepository.getAllByAccountClientIdAndDateBetween(clientId, dateTransactionStart,
                dateTransactionEnd);
    }

    @Override
    public TransactionDto getLastByAccountId(Long accountId) {
        // If you need it
        return TransactionMapper.INSTANCE.toDTO(transactionRepository.getLastByAccountId(accountId));
    }

}
