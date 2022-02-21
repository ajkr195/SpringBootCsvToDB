package com.spring.boot.rocks.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "customer")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Inventory {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "itemname")
	private String itemname;

	@Column(name = "itemtype")
	private String itemtype;

	@Column(name = "itemquantity")
	private String itemquantity;

	public Inventory(String itemname, String itemtype, String itemquantity) {
		super();
		this.itemname = itemname;
		this.itemtype = itemtype;
		this.itemquantity = itemquantity;
	}

}
