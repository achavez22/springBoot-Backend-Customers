package com.achavez.springBootbackendapirest.model.entity;
import java.io.Serializable;

@Entity
@Table(name = "clientes")
public class Cliente implements Serializable{
	
	  	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	  	
	    @Column(nullable = false)
	    @NotEmpty
	    @Size(min = 4, max = 12)
	    private String nombre;
	    
	    @NotEmpty
	    private String primerApellido;
	    
	    @NotEmpty
	    private String segundoApellido;
	    
	    @Column(nullable = false, unique = true)
	    @Email
	    @NotEmpty
	    private String email;
	    
	    @Column(name = "create_at")
	    @Temporal(TemporalType.DATE)
	    private Date createAt;
	    
	    @PrePersist
	    public void prePersist(){
	        createAt = new Date();
	    }
	    public Long getId() {
	        return id;
	    }

	    public void setId(Long id) {
	        this.id = id;
	    }

	    public String getNombre() {
	        return nombre;
	    }

	    public void setNombre(String nombre) {
	        this.nombre = nombre;
	    }

	    public String getPrimerApellido() {
	        return primerApellido;
	    }

	    public void setPrimerApellido(String primerApellido) {
	        this.primerApellido = primerApellido;
	    }

	    public String getSegundoApellido() {
	        return segundoApellido;
	    }

	    public void setSegundoApellido(String segundoApellido) {
	        this.segundoApellido = segundoApellido;
	    }

	    public String getEmail() {
	        return email;
	    }

	    public void setEmail(String email) {
	        this.email = email;
	    }

	    public Date getCreateAt() {
	        return createAt;
	    }

	    public void setCreateAt(Date createAt) {
	        this.createAt = createAt;
	    }

}
