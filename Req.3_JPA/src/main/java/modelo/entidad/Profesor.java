package modelo.entidad;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity //metemos la clase alumno en un tabla
@Table (name="profesores") //damos nombre a la tabla
public class Profesor {
	
	@Id // proporcionamos el id y lo ponemos autoicrement con @GeneratedValue
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nombre;
	private int telefono;

	// esta tabla es la tabla que mayores relaciones tiene y la que se relaciona con todas
	// las tablas. Con la tabla ContratoLaboral va a tener una relación uno a uno.
	// para ello usamos la etiqueta @OneToOne y le decimos a JPA con mappedBy qué atributo 
	// de la clase ContratoLaboral tiene que relacionarlo.
	// en este caso sí nos interesa que cuando se borre un profesor también se borre su 
	// número de contrato y a la inversa también. Ese es el motivo por el que se le asigna 
	// el tipo de cascada ALL
	@OneToOne(mappedBy = "profesor", cascade=CascadeType.ALL)
	private ContratoLaboral contrato;
	
	// Con asignatura va a tener una relación de uno a muchos. Profesor es la parte del uno
	// y por tanto no es la que lleva la FK. Se le menciona a JPA con la siguiente anotación 
	// y su comprensión es igual que la de OneToOne
	@OneToMany(mappedBy="profesor", cascade=CascadeType.ALL) 
	private List<Asignatura> asignaturas;
	
	// Con la clase alumno va a tener una relación de muchos a muchos. En este caso hemos optado
	// por declarar un tipo de cascada Persist ya que es muy peligroso en relaciones de muchos a muchos
	// asiginar una cascada ALL. No queremos quedarnos sin base de datos :)
	// si borramos un profesor se borrarían todos los alumnos y a la inversa también.
	@ManyToMany(mappedBy="profesores", cascade=CascadeType.PERSIST) 
	private List<Alumno> alumnos;

	

	public Profesor(Integer id, String nombre, int telefono, ContratoLaboral contrato) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.telefono = telefono;
		this.contrato = contrato;
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


	public int getTelefono() {
		return telefono;
	}


	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public ContratoLaboral getContrato() {
		return contrato;
	}


	public void setContrato(ContratoLaboral contrato) {
		this.contrato = contrato;
	}


	public List<Asignatura> getAsignaturas() {
		return asignaturas;
	}


	public void setAsignaturas(List<Asignatura> asignaturas) {
		this.asignaturas = asignaturas;
	}


	public List<Alumno> getAlumnos() {
		return alumnos;
	}


	public void setAlumnos(List<Alumno> alumnos) {
		this.alumnos = alumnos;
	}	



}
