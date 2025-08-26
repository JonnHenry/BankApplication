package com.devsu.hackerearth.backend.account.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class Transaction extends Base {

	private Date date;
	private String type;
	private double amount;
	private double balance;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "account_id",insertable=false,updatable = false)
	@JsonIgnore
	private Account account;

	@Column(name = "account_id")
	private Long accountId;
}
