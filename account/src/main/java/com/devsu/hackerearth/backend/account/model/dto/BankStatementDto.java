package com.devsu.hackerearth.backend.account.model.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AccessLevel;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankStatementDto {

	private Date date;
	private String client;
	private String accountNumber;
	private String accountType;
	private double initialAmount;

	@Getter(AccessLevel.NONE)
	@Setter(AccessLevel.NONE)
	@JsonProperty(value = "isActive")
	private boolean isActive;

	private String transactionType;
	private double amount;
	private double balance;

	public boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}
}
