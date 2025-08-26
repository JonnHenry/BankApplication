package com.devsu.hackerearth.backend.account.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import com.devsu.hackerearth.backend.account.model.Account;
import com.devsu.hackerearth.backend.account.model.dto.AccountDto;

@Mapper
public interface AccountMapper {

    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    AccountDto toDTO(Account account);
    Account toEntity(AccountDto accountDto);
    void updateEntityFromDTO(AccountDto accountDto,@MappingTarget Account account);
    
}
