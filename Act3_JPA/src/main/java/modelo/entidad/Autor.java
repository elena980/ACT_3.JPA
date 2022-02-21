package modelo.entidad;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity //metemos la clase Autor en un tabla
@Table(name="autores") //damos nombre a la tabla
public class Autor {
	
	@Id // proporcionamos el id y lo ponemos autoicrement con @GeneratedValue
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nombre;
	private String apellido;
	private String fechaNacimiento;

	// Con libro va a tener una relación de uno a muchos. Autor es la parte del uno
	// y por tanto no es la que lleva la FK. Se le menciona a JPA con la siguiente anotación 
	// y su comprensión es igual que la de OneToOne
	@OneToMany(mappedBy="autor", cascade=CascadeType.ALL) 
	private List<Libro> libro;
	

	public Autor() {
		super();
	}


	public Autor(Integer id, String nombre, String apellido, String fechaNacimiento) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNacimiento = fechaNacimiento;
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



	public String getApellido() {
		return apellido;
	}


	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public List<Libro> getLibro() {
		return libro;
	}

	public void setLibro(List<Libro> libro) {
		this.libro = libro;
	}
}
