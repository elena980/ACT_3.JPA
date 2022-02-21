package testeo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import modelo.entidad.Asignatura;
import modelo.entidad.Profesor;

public class PruebaOneToMany {

	public static void main(String[] args) {
		
		// probamos las persistencias con las relaciones de uno a muchos, en este caso son
		// con las entiedades Profesor-Asignatura.
				
		// empezamos declarando un EntityManagerFactorý es la clase que se encarga de abrir la conexión 
		// con la base de datos y para ello necesitamos como parámetro el nombre de la unidad de persistencia
		// declarado en el pesistence.xml
		// este es la clase de la que se va a apoyar el EntityManager, que es el que se encarga de hacer las gestiones
		// con la BBDD
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Requerimiento3");	
		// en este caso el EntityManager lo hemos declarado a NULL 
		EntityManager em = null;
		
		// instanciamos un objeto Profesor
		Profesor p = new Profesor(null, "Raquel Cerdá", 915694325, null);
		
		// Construimos un Lista de Asignaturas y aprovechamos para introducir el profesor
		// en cada una de las asignaturas
		List<Asignatura> asignaturas = new ArrayList<Asignatura>();
		Asignatura a1 = new Asignatura(null, "Desarrollo de interface", "IT", p);
		Asignatura a2 = new Asignatura(null, "Sistemas informáticos", "IT", p);
		Asignatura a3 = new Asignatura(null, "Programación Android", "IT", p);
		Asignatura a4 = new Asignatura(null, "Proyecto", "IT", p);
		asignaturas.add(a1);
		asignaturas.add(a2);
		asignaturas.add(a3);
		asignaturas.add(a4);
		
		// para hacerlo bidireccional le metemos al profesor su listado de asignaturas
		p.setAsignaturas(asignaturas);
		
		// llamamos al EntityManagerFactory para poder trabajar con EntityManager
		em = emf.createEntityManager();
		
		// comenzamos la transacción
		em.getTransaction().begin();
		
		// persistimos los datos del profesor que al tener los cascades se quedarán
		// metidos también los datos de las asignaturas
		em.persist(p); 
		
		// finalmente con commit subimos los datos a la BBDD desde el contexto de 
		// persistencia
		em.getTransaction().commit(); 
		
		// cerramos tanto el EntityManager como el EntityManagerFactory
		em.close();	
		emf.close();

	}

}
