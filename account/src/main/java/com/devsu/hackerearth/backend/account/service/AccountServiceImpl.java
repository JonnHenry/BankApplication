package com.devsu.hackerearth.backend.account.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.devsu.hackerearth.backend.account.client.ClientResponse;
import com.devsu.hackerearth.backend.account.constants.AccountConstants;
import com.devsu.hackerearth.backend.account.exception.AlreadyExistException;
import com.devsu.hackerearth.backend.account.exception.NonExecutableTransactionExeption;
import com.devsu.hackerearth.backend.account.exception.NotFoundException;
import com.devsu.hackerearth.backend.account.mapper.AccountMapper;
import com.devsu.hackerearth.backend.account.model.Account;
import com.devsu.hackerearth.backend.account.model.dto.AccountDto;
import com.devsu.hackerearth.backend.account.model.dto.PartialAccountDto;
import com.devsu.hackerearth.backend.account.repository.AccountRepository;
import com.devsu.hackerearth.backend.account.utils.ClientRest;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    private final ClientRest clientRest;

    public AccountServiceImpl(AccountRepository accountRepository, ClientRest clientRest) {
        this.accountRepository = accountRepository;
        this.clientRest = clientRest;
    }

    @Override
    public List<AccountDto> getAll() {
        return accountRepository.findAll()
                .stream()
                .map(AccountMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AccountDto getById(Long id) {
        // Get accounts by id
        return accountRepository.findById(id).map(AccountMapper.INSTANCE::toDTO).orElse(null);
    }

    @Override
    public AccountDto create(AccountDto accountDto) {
        // Create account 
        Optional<ClientResponse> clientResponse = clientRest.findClientById(accountDto.getClientId());
        if (clientResponse.isEmpty() || Objects.isNull(clientResponse.get().getIsActive()) || clientResponse.get().getIsActive()==false){
            throw new NotFoundException(AccountConstants.USER_ACCOUNT_NOT_EXIST);
        }

        if (Objects.nonNull(accountDto.getId()) && accountRepository.findById(accountDto.getId()).isPresent()) {
            throw new AlreadyExistException(
                    String.format(AccountConstants.ACCOUNT_ALREADY_EXIST,accountDto.getId()));
        }

		accountDto.setIsActive(true);
        return AccountMapper.INSTANCE.toDTO(
                accountRepository.save(AccountMapper.INSTANCE.toEntity(accountDto)));
    }

    @Override
    public AccountDto update(AccountDto accountDto) {
        // Update account
        if (accountDto == null) {
            throw new NonExecutableTransactionExeption(AccountConstants.UNPROCESABLE_ENTITY);
        }

        Optional<ClientResponse> clientResponse = clientRest.findClientById(accountDto.getClientId());
        if (clientResponse.isEmpty() || Objects.isNull(clientResponse.get().getIsActive()) || clientResponse.get().getIsActive()==false){
            throw new NotFoundException(AccountConstants.USER_ACCOUNT_NOT_EXIST);
        }

        return AccountMapper.INSTANCE.toDTO(accountRepository.save(AccountMapper.INSTANCE.toEntity(accountDto)));
    }

    @Override
    public AccountDto partialUpdate(Long id, PartialAccountDto partialAccountDto) {
        // Partial update account
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(
                        String.format(AccountConstants.ACCOUNT_NOT_EXIST,id)));
        account.setIsActive(partialAccountDto.getIsActive());
        return AccountMapper.INSTANCE.toDTO(accountRepository.save(account));
    }

    @Override
    public void deleteById(Long id) {
        // Delete account
        accountRepository.deleteById(id);
    }

}
