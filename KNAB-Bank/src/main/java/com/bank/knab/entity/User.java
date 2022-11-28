package com.bank.knab.entity;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long employeeId;
	private String firstName;
	private String lastName;
	private String fatherName;
	private String bloodGroup;
	private String email;
	private String password;
	private String mobileNo;
	private String role;
	private String branch;
	@CreationTimestamp
	private LocalDateTime createdOn;
	private String createdBy;
	@UpdateTimestamp
	private LocalDateTime updatedOn;
	private String updatedBy;
	
//	@JoinTable(name = "profile_picture", joinColumns = { @JoinColumn(name = "profile_pic_id") }, inverseJoinColumns = {
//			@JoinColumn(name = "image_id") })
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name="image_id_fk")
	private ImageModel profilePicture;

}
