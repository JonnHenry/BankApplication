package com.devsu.hackerearth.backend.account.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.devsu.hackerearth.backend.account.model.Transaction;
import com.devsu.hackerearth.backend.account.model.dto.TransactionDto;

@Mapper
public interface TransactionMapper {

    TransactionMapper INSTANCE = Mappers.getMapper(TransactionMapper.class);

    TransactionDto toDTO(Transaction transaction);
    Transaction toEntity(TransactionDto transactionDto);
    
}
