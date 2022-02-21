package testeo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import modelo.entidad.Alumno;
import modelo.entidad.Profesor;

public class PruebaManyToMany {

	public static void main(String[] args) {
		// probamos las persistencias con las relaciones de muchos a muchos, en este caso son
		// con las entiedades Profesor-Alumno
		
		// empezamos declarando un EntityManagerFactorý es la clase que se encarga de abrir la conexión 
		// con la base de datos y para ello necesitamos como parámetro el nombre de la unidad de persistencia
		// declarado en el pesistence.xml
		// este es la clase de la que se va a apoyar el EntityManager, que es el que se encarga de hacer las gestiones
		// con la BBDD
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Requerimiento3");
		EntityManager em = emf.createEntityManager();
		
		// Declaramos primero unos objetos de Profesor
		Profesor p1 = new Profesor(null, "Raquel Cerdá", 917589431, null);
		Profesor p2 = new Profesor(null, "Félix de Pablo", 915849632, null);
		Profesor p3 = new Profesor(null, "Tomás Escudero", 926359847, null);
		Profesor p4 = new Profesor(null, "Maria de Gracia", 968597412, null);
		
		// Ahora declaramos unos objetos de Alumno
		Alumno a1 = new Alumno(null, "Ismael", "Gonzalez", "24.04.1986", null);
		Alumno a2= new Alumno(null, "Elena", "Santos", "30.05.1980", null);
		Alumno a3= new Alumno(null, "Mateo", "Serrano", "08.08.1982", null);
		
		// Listamos los profesores y se lo metemos a un alumno en concreto
		List<Profesor> profesoresElena = new ArrayList<Profesor>();
		profesoresElena.add(p3);
		profesoresElena.add(p2);
		profesoresElena.add(p4);
		a2.setProfesores(profesoresElena);
		
		List<Profesor> profesoresMateo = new ArrayList<Profesor>();
		profesoresMateo.add(p1);
		profesoresMateo.add(p2);
		profesoresMateo.add(p3);
		a3.setProfesores(profesoresMateo);
		
		List<Profesor> profesoresIsmael = new ArrayList<Profesor>();
		profesoresIsmael.add(p3);
		profesoresIsmael.add(p4);
		a1.setProfesores(profesoresIsmael);
		
		// Para una conexión bidirecional necesitamos listar los alumnos
		// y proporcionarselo a uno de los profesores
		List<Alumno> alumnosRaquel = new ArrayList<Alumno>();
		alumnosRaquel.add(a3);
		p1.setAlumnos(alumnosRaquel);
		
		List<Alumno> alumnosFelix = new ArrayList<Alumno>();
		alumnosFelix.add(a3);
		alumnosFelix.add(a2);
		p2.setAlumnos(alumnosFelix);
		
		List<Alumno> alumnosTomas = new ArrayList<Alumno>();
		alumnosTomas.add(a3);
		alumnosTomas.add(a2);
		alumnosTomas.add(a1);
		p3.setAlumnos(alumnosTomas);
		
		List<Alumno> alumnosMaria = new ArrayList<Alumno>();
		alumnosMaria.add(a2);
		alumnosMaria.add(a1);
		p3.setAlumnos(alumnosMaria);
		
		// decimos al EntityManager que inicie la transación con .getTransaction()
		// sólo para las operaciones de modificación de la BBDD, para lectura de algun
		// dato no es necesario.
		em.getTransaction().begin();
		
		// persistimos la información pero la dejamos en el contexto de persistencia,
		// esto quiere decir que aún no están en la BBDD
		em.persist(p3);
		
		// ahora con el .commit() si lo volcamos a la BBDD
		em.getTransaction().commit();
		
		// cerramos tanto el EntityManager como el EntityManagerFactory
		em.close();
		emf.close();		
	}

}
