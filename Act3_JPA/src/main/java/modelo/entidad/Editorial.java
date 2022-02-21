package modelo.entidad;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity //metemos la clase Editorial en un tabla
@Table(name="editoriales") //damos nombre a la tabla
public class Editorial {
	
	@Id // proporcionamos el id y lo ponemos autoicrement con @GeneratedValue
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nombre;
	private String direccion;
	
	// Con libro va a tener una relación de uno a muchos. Editorial es la parte del uno
	// y por tanto no es la que lleva la FK. Se le menciona a JPA con la siguiente anotación 
	// y su comprensión es igual que la de OneToOne
	@OneToMany(mappedBy="editorial", cascade=CascadeType.PERSIST) 
	private List<Libro> libro;
	
	public Editorial() {
		super();
	}


	public Editorial(Integer id, String nombre, String direccion) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.direccion = direccion;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public List<Libro> getLibro() {
		return libro;
	}

	public void setLibro(List<Libro> libro) {
		this.libro = libro;
	}


}
