package com.innoventes.test.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class CompanyDTO {

	private Long id;
	@NotBlank(message = "companyName is Mandatory.")
	@NotEmpty(message = "companyName can not be empty.")
	@Size(min = 5, message = "companyName Should be at least 5 characters of length.")
	private String companyName;

	@NotBlank(message = "Email is mandatory.")
	@Email(message = "Please enter a valid email.")
	private String email;

	@PositiveOrZero(message = "Strength should be a positive number or zero")
	private Integer strength;
	
	private String webSiteURL;
	@Pattern(regexp = "[A-Za-z]{2}[0-9]{2}[ENen]", message = "Please a valid companyCode.")
	private String companyCode;
}
