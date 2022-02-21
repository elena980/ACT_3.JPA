package testeo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import modelo.entidad.Profesor;
import modelo.entidad.ContratoLaboral;


public class PruebaOneToOne {
	
	public static void main(String[] args) {
		
		// probamos las persistencias con las relaciones de uno a uno, en este caso son
		// con las entiedades Profesor-ContratoLaboral.
		
		// empezamos declarando un EntityManagerFactorý es la clase que se encarga de abrir la conexión 
		// con la base de datos y para ello necesitamos como parámetro el nombre de la unidad de persistencia
		// declarado en el pesistence.xml
		// este es la clase de la que se va a apoyar el EntityManager, que es el que se encarga de hacer las gestiones
		// con la BBDD
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Requerimiento3");
		// en este caso el EntityManager lo hemos declarado a NULL 
		EntityManager em = null;

		// instanciamos un objeto Profesor
		Profesor p1 = new Profesor(null, "Daniel Martil", 916325989, null);
		
		// instanciamos un objeto ContratoLaboral
		ContratoLaboral cl1= new ContratoLaboral(null, "452_89", "ES00236589657458", null);
		
		// asignamos un contrato a un profesor
		p1.setContrato(cl1);
		
		// y como la relación es bidireccional se le asigna un profesor a un 
		// contrato laboral
		cl1.setProfesor(p1);
		
		
		// repetimos el proceso con otros nuevos datos
		Profesor p2 = new Profesor(null, "Frutos Blancos", 916569841, null);
		ContratoLaboral cl2= new ContratoLaboral(null, "452_90", "ES002253661427458", null);
		p2.setContrato(cl2);
		cl2.setProfesor(p2);
		
		// llamamos al EntityManagerFactory para poder trabajar con EntityManager
		em = emf.createEntityManager();
		
		// comenzamos la transacción
		em.getTransaction().begin();
		
		// persistimos los datos del profesor que al tener los cascades se quedarán
		// metidos también los datos del contrato laboral, en este caso persistimos
		// los dos profesores a la vez.
		em.persist(p1); 
		em.persist(p2); 
		
		// finalmente con commit subimos los datos a la BBDD desde el contexto de 
		// persistencia
		em.getTransaction().commit(); 
		
		// cerramos tanto el EntityManager como el EntityManagerFactory
		em.close();		
		emf.close();			
	}

}
