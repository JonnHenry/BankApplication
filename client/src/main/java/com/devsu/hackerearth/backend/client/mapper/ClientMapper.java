package com.devsu.hackerearth.backend.client.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import com.devsu.hackerearth.backend.client.model.Client;
import com.devsu.hackerearth.backend.client.model.dto.ClientDto;

@Mapper
public interface ClientMapper {

    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);

    ClientDto toDTO(Client client);
    Client toEntity(ClientDto clientDto);
    void updateEntityFromDTO(ClientDto clientDto,@MappingTarget Client client);
}
