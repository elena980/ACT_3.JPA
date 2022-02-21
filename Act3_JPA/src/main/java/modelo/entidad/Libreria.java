package modelo.entidad;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity //metemos la clase Libreria en un tabla
@Table(name="librerias") //damos nombre a la tabla
public class Libreria {
	
	@Id // proporcionamos el id y lo ponemos autoicrement con @GeneratedValue
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nombre;
	private String propietario;
	private String direccion;

	// Con la clase libro va a tener una relación de muchos a muchos. En este caso hemos optado
	// por declarar un tipo de cascada Persist ya que es muy peligroso en relaciones de muchos a muchos
	// asiginar una cascada ALL. No queremos quedarnos sin base de datos :)
	// si borramos un Libro se borrarían todos las Librerias y a la inversa también.
	@ManyToMany(cascade=CascadeType.PERSIST)
	@JoinTable(name="Libreria_Libro",
			   joinColumns= { @JoinColumn(name="fk_id_libreria", referencedColumnName="id") }, 
			   inverseJoinColumns= { @JoinColumn(name="fk_id_libro", referencedColumnName="id")}) 
	private List<Libro> libro;

	public Libreria() {
		super();
	}


	public Libreria(Integer id, String nombre, String propietario, String direccion) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.propietario = propietario;
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


	public String getPropietario() {
		return propietario;
	}


	public void setPropietario(String propietario) {
		this.propietario = propietario;
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
