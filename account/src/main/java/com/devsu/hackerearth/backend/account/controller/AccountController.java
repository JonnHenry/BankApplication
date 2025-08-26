package com.devsu.hackerearth.backend.account.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.devsu.hackerearth.backend.account.model.dto.AccountDto;
import com.devsu.hackerearth.backend.account.model.dto.PartialAccountDto;
import com.devsu.hackerearth.backend.account.service.AccountService;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

	private final AccountService accountService;

	public AccountController(AccountService accountService) {
		this.accountService = accountService;
	}

	@GetMapping
	public ResponseEntity<List<AccountDto>> getAll() {
		// api/accounts
		// Get all accounts
		List<AccountDto> accountDtos = accountService.getAll();
		if (accountDtos.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(accountDtos);
	}

	@GetMapping("/{id}")
	public ResponseEntity<AccountDto> get(@PathVariable Long id) {
		// api/accounts/{id}
		// Get accounts by id
		AccountDto accountDto = accountService.getById(id);
		if (accountDto==null){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.ok(accountService.getById(id));
	}

	@PostMapping
	public ResponseEntity<AccountDto> create(@RequestBody @Valid AccountDto accountDto) {
		// api/accounts
		// Create accounts
		return ResponseEntity.status(HttpStatus.CREATED).body(accountService.create(accountDto));
	}

	@PutMapping("/{id}")
	public ResponseEntity<AccountDto> update(@PathVariable Long id, @RequestBody AccountDto accountDto) {
		// api/accounts/{id}
		// Update accounts
		accountDto.setId(id);
		if (accountService.getById(id)==null) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
		return ResponseEntity.ok(accountService.update(accountDto));
	}

	@PatchMapping("/{id}")
	public ResponseEntity<AccountDto> partialUpdate(@PathVariable Long id,
			@RequestBody PartialAccountDto partialAccountDto) {
		// api/accounts/{id}
		// Partial update accounts
		AccountDto accountDto = accountService.getById(id);
		if (accountDto==null){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		AccountDto account = accountService.partialUpdate(id,partialAccountDto);

		return ResponseEntity.ok(account);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		// api/accounts/{id}
		// Delete accounts
		if (accountService.getById(id)==null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
		accountService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
