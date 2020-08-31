package com.protosstechnology.train.bootexamine.datamodel;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.sun.istack.NotNull;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(of = {"id"})
public class Document {

	private @Id
	@GeneratedValue
	Long id;

	@NotNull
	private String documentNumber;
	@NotNull
	private String description;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDocumentNumber() {
		return documentNumber;
	}

	public void setDocumentNumber(String documentNumber) {
		this.documentNumber = documentNumber;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}