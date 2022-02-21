package modelo.entidad;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity //metemos la clase Libro en un tabla
@Table(name= "libros") //damos nombre a la tabla
public class Libro {
	
	@Id // proporcionamos el id y lo ponemos autoicrement con @GeneratedValue
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String titulo;
	private double precio;
	
	// Relación de muchos a muchos
	// En este caso he optado por no declarar un tipo de cascada
	@ManyToOne
	@JoinColumn(name="fk_id_editorial", referencedColumnName="id")
	private Editorial editorial;
	
	// Relación de muchos a muchos
	// En este caso he optado por no declarar un tipo de cascada
	@ManyToOne
	@JoinColumn(name="fk_id_autor", referencedColumnName="id")
	private Autor autor;

	// Con la clase Libreria va a tener una relación de muchos a muchos. En este caso he optado
	// por declarar un tipo de cascada Persist ya que es muy peligroso en relaciones de muchos a muchos
	// asiginar una cascada ALL. No queremos quedarnos sin base de datos :)
	// si borramos un libro se borrarían todos las librerias y a la inversa también.
	@ManyToMany(mappedBy="libro", cascade=CascadeType.PERSIST) 
	private List<Libreria> librerias;	

	
	public Libro() {
		super();
	}

	public Libro(Integer id, String titulo, double precio, Editorial editorial, Autor autor) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.precio = precio;
		this.editorial = editorial;
		this.autor = autor;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public Editorial getEditorial() {
		return editorial;
	}

	public void setEditorial(Editorial editorial) {
		this.editorial = editorial;
	}

	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}

	public List<Libreria> getLibrerias() {
		return librerias;
	}

	public void setLibrerias(List<Libreria> librerias) {
		this.librerias = librerias;
	}
}
