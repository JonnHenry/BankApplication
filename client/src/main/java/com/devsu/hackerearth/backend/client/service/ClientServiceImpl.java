package com.devsu.hackerearth.backend.client.service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import java.util.Optional;
import com.devsu.hackerearth.backend.client.exception.NotFoundException;
import com.devsu.hackerearth.backend.client.constants.ClientConstants;
import com.devsu.hackerearth.backend.client.mapper.ClientMapper;
import com.devsu.hackerearth.backend.client.model.Client;
import com.devsu.hackerearth.backend.client.model.dto.ClientDto;
import com.devsu.hackerearth.backend.client.model.dto.PartialClientDto;
import com.devsu.hackerearth.backend.client.repository.ClientRepository;

@Service
public class ClientServiceImpl implements ClientService {

	private final ClientRepository clientRepository;

	public ClientServiceImpl(ClientRepository clientRepository) {
		this.clientRepository = clientRepository;
	}

	@Override
	public List<ClientDto> getAll() {
		// Get all clients
		return clientRepository.findAll()
				.stream()
				.map(ClientMapper.INSTANCE::toDTO)
				.collect(Collectors.toList());
	}

	@Override
	public ClientDto getById(Long id) {
		// Get clients by id
		return clientRepository.findById(id).map(ClientMapper.INSTANCE::toDTO).orElse(null);
	}

	@Override
	public ClientDto create(ClientDto clientDto) {
		// Create client
		clientDto.setIsActive(true);
		return ClientMapper.INSTANCE.toDTO(
				clientRepository.save(ClientMapper.INSTANCE.toEntity(clientDto)));
	}

	@Override
	public ClientDto update(ClientDto clientDto) {
		// Update client
		Client client = clientRepository.findById(clientDto.getId()).orElseThrow(
				()->new NotFoundException(String.format(ClientConstants.CLIENT_NOT_EXIST, clientDto.getId()))
			);

		ClientMapper.INSTANCE.updateEntityFromDTO(clientDto, client);
		return ClientMapper.INSTANCE.toDTO(clientRepository.save(client));
	}

	@Override
	public ClientDto partialUpdate(Long id, PartialClientDto partialClientDto) {
		// Partial update account
		Optional<Client> client = clientRepository.findById(id);
		if (client.isEmpty()) {
			throw new NotFoundException(
					String.format(ClientConstants.CLIENT_NOT_EXIST, id));
		}

		client.get().setIsActive(partialClientDto.getIsActive());
		return ClientMapper.INSTANCE.toDTO(
				clientRepository.save(client.get()));
	}

	@Override
	public void deleteById(Long id) {
		// Delete client
		clientRepository.deleteById(id);
	}
}
