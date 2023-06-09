package com.achavez.springBootbackend.apirest.model.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "facturas_items")
public class ItemFactura implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id; 
	
	private Integer cantidad; 
	
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"} )
	@ManyToOne(fetch= FetchType.LAZY)
	private Producto producto; 
	
	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Double getImporte() { 
		return cantidad.doubleValue()*producto.getPrecio(); 
	}

	public Producto getProducto() {
		return producto;
	}


	public void setProducto(Producto producto) {
		this.producto = producto;
	}



	private static final long serialVersionUID = 1L;

}
