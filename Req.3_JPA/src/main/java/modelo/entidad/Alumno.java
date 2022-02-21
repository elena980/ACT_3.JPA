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


@Entity //metemos la clase alumno en un tabla
@Table( name= "alumnos") //damos nombre a la tabla
public class Alumno {
	
	@Id // proporcionamos el id y lo ponemos autoicrement con @GeneratedValue
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nombre;
	private String apellido;
	private String fechaNacimiento;

	// la relación que tiene con la tabla profesores va a ser una relación de muchos a muchos
	// elegimos el tipo de cascade.Persist ya que no queremos borrar a un profesor cuando demos
	// de baja un alumno, pero sí nos interesa que cuando se de de alta un alumno se le asigne un profesor
	@ManyToMany(cascade=CascadeType.PERSIST)
	@JoinTable(name="alumnos_profesores",
	// con las siguientes referencias nombramos a las FK y le decimos a que referencia hace
	// joinColumns se trata de la clase (tabla) en la que estamos y con inverseJoinColumns hacemos 
	// referencia a la clase (tabla)con la que se relaciona
			   joinColumns= { @JoinColumn(name="fk_id_alumnos", referencedColumnName="id") }, 
			   inverseJoinColumns= { @JoinColumn(name="fk_id_profesores", referencedColumnName="id")}) 
	private List<Profesor> profesores;



	public Alumno(Integer id, String nombre, String apellido, String fechaNacimiento, List<Profesor> profesores) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNacimiento = fechaNacimiento;
		this.profesores = profesores;
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



	public List<Profesor> getProfesores() {
		return profesores;
	}



	public void setProfesores(List<Profesor> profesores) {
		this.profesores = profesores;
	}

	
}
