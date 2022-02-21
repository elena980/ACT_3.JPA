package test;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import modelo.entidad.Autor;
import modelo.entidad.Libreria;
import modelo.entidad.Libro;

public class MainRequerimiento2 {

	public static void main(String[] args) {
		
		// empezamos declarando un EntityManagerFactorý, es la clase que se encarga de abrir la conexión 
		// con la base de datos y para ello necesitamos como parámetro el nombre de la unidad de persistencia
		// declarado en el pesistence.xml
		// este es la clase de la que se va a apoyar el EntityManager, que es el que se encarga de hacer las gestiones
		// con la BBDD
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("act3_JPA.Elena");
		EntityManager em = emf.createEntityManager();
		
		// Requerimiento 2.1 (listar libros dados de alta con editorial y autor)
		System.err.println("Listando libros --------------------------------");
		Query query = em.createQuery("Select l from Libro l");
		List<Libro> listBook = query.getResultList();
		for(Libro l : listBook) {
			System.out.println("Id: " + l.getId() +" Libro: " + l.getTitulo() + " Autor: " + l.getAutor() + " Editorial: " + l.getEditorial());
		}
		System.err.println("");
		
		// Requerimiento 2.1 (listar autores dados de alta con sus libros asociados)
		System.err.println("Listando Autores --------------------------------");
		Query queryA = em.createQuery("Select a from Autor a");
		List<Autor> listAutor = queryA.getResultList();
		for(Autor a : listAutor) {
			System.out.println("Id: " + a.getId() + " Autor: " + a.getNombre() + " " + a.getApellido() + " Libros:" + a.getLibro());
		}
		System.err.println("");
		
		// Requerimiento 2.1 (listar librerias dados de alta con sus libros asociados)
		System.err.println("Listando Librerías --------------------------------");
		query = em.createQuery("Select li, libreria.libro from Libreria li");
		List<Object[]> resultados = query.getResultList();
		for (Object[] p : resultados) {
			System.out.println(p[1] + " - " + p[3]);
		}
		
		// Requerimiento 2.1 (listar libros con libreras en la están)
		System.err.println("Listando Libros con Librerias asociadas --------------------------------");
		query = em.createQuery("Select lib, lib.librerias from Libro lib");
		List<Object[]> resultados2 = query.getResultList();
		for(Object[] lib : resultados2) {
			System.out.println(lib[0] + "-" + lib[1]);
		}
		System.err.println("");
		
		// cerramos el EntityManager y el EntityManagerFactory
		em.close();
		emf.close();
	}
}
