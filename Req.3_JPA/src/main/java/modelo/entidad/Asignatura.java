

package modelo.entidad;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity //metemos la clase alumno en un tabla
@Table( name="asignaturas") //damos nombre a la tabla
public class Asignatura {

	@Id // proporcionamos el id y lo ponemos autoicrement con @GeneratedValue
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;
	private String nombre;
	private String rama;
	
	// Relación de muchos a muchos
	// En este caso hemos optado por declarar un tipo de cascada Persist
	// No borrar una asignatura y que se borre todos los profesores y a la inversa también.
	@ManyToOne(cascade= CascadeType.PERSIST)
	@JoinColumn(name="fk_id_profesores", referencedColumnName="id")
	private Profesor profesor;

	public Asignatura(Integer id, String nombre, String rama, Profesor profesor) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.rama = rama;
		this.profesor = profesor;
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

	public String getRama() {
		return rama;
	}

	public void setRama(String rama) {
		this.rama = rama;
	}

	public Profesor getProfesor() {
		return profesor;
	}

	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}

	

	
}
