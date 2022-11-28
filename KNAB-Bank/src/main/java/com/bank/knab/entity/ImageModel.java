package com.bank.knab.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ImageModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long imageId;
	private String name;
	private String type;
	@Column(length = 500000)
	private byte[] picByte;
}
