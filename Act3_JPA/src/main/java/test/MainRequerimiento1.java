package test;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import modelo.entidad.Autor;
import modelo.entidad.Editorial;
import modelo.entidad.Libreria;
import modelo.entidad.Libro;

public class MainRequerimiento1 {

	public static void main(String[] args) {
		
		// empezamos declarando un EntityManagerFactorý, es la clase que se encarga de abrir la conexión 
		// con la base de datos y para ello necesitamos como parámetro el nombre de la unidad de persistencia
		// declarado en el pesistence.xml
		// este es la clase de la que se va a apoyar el EntityManager, que es el que se encarga de hacer las gestiones
		// con la BBDD
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("act3_JPA.Elena");
		EntityManager em = null;

		// Requerimiento 1.1 (alta tres autores)
		// damos de alta tres autores en la base de datos
		Autor a1= new Autor(null, "Carmen", "Mola", "20.05.1982");
		Autor a2= new Autor(null, "Gabriel", "García", "15.02.1974");
		Autor a3= new Autor(null, "Valle", "Inclán", "28.10.1966");
		
		
		// Creamos una lista de libros de cada uno de los autores
		List<Libro> librosA1 = new ArrayList<Libro>();
		Libro a11= new Libro(null, "Un reino de carne y fuego", 20.42, null, a1);
		Libro a12 = new Libro(null, "Crimenes contra el planeta", 6.60, null, a1);
		librosA1.add(a12);
		librosA1.add(a11);
		
		List<Libro> librosA2 = new ArrayList<Libro>();
		Libro a21 = new Libro(null, "¿Cuidamos el planeta?", 11.40, null, a2);
		Libro a22 = new Libro(null, "Al paraiso", 23.65, null, a2);
		librosA2.add(a21);
		librosA2.add(a22);
		
		List<Libro> librosA3 = new ArrayList<Libro>();
		Libro a31 = new Libro(null, "Cuentos para salvar el planeta", 6.60, null, a3);
		Libro a32 = new Libro(null, "Cinco días en otro planeta", 5.67, null, a3);
		Libro a33 = new Libro(null, "Integridad", 8.07, null, a3);
		Libro a34 = new Libro(null, "El libro negro de las horas", 19.81, null, a3);
		librosA3.add(a31);
		librosA3.add(a32);
		librosA3.add(a33);
		librosA3.add(a34);
		
		// para hacer la una relacion bidireccional metemos al autor la lista de libros 
		// que tiene
		a1.setLibro(librosA1);
		a2.setLibro(librosA2);
		a3.setLibro(librosA3);
		
		
		// declaramos una variable EntityManager con el apoyo de EntityManagerFactory
		// que es quien se encarga de abrir conexión con la BBDD. EntityManager es
		// quien gestiona las "querries" a la BBDD
		em = emf.createEntityManager();
		// Abrimos transacción con .getTransaction ya que se trata de una alteración
		// en las tablas, si fuese para únicamente coger datos no haría falta
		em.getTransaction().begin();
		// Persistimos la información en el contexto de persistencia, 
		// esto quiere decir que hasta que no hay un commit no se carga en la BBDD
		em.persist(a1); 
		em.persist(a2); 
		em.persist(a3); 
		// abrimos trasacción y cargamos el contexto de persistencia en la BBDD
		em.getTransaction().commit(); 
		// cerramos el EntityManager
		em.close();	
		
		// Requerimiento 1.2 (alta dos editoriales)
		Editorial e1 = new Editorial(null, "Planeta", "Avenida Diagonal, 662-664 Barcelona");
		Editorial e2 = new Editorial(null, "Delbolsillo", "Travessera de Gràcia, 47 Barcelona");
		
		// al instanciar un libro ya le metemos un editorial
		List<Libro> librosPlaneta = new ArrayList<Libro>();
		Libro l1 = new Libro(null, "Cuentos para salvar el planeta", 6.60, e2, a3);
		Libro l2 = new Libro(null, "¿Cuidamos el planeta?", 11.40, e2, a2);
		Libro l3 = new Libro(null, "Cinco días en otro planeta", 5.67, e2, a3);
		Libro l4 = new Libro(null, "Crimenes contra el planeta", 6.60, e2, a1);
		librosPlaneta.add(l1);
		librosPlaneta.add(l2);
		librosPlaneta.add(l3);
		librosPlaneta.add(l4);
		
		
		List<Libro> librosDelBolsillo = new ArrayList<Libro>();
		Libro l5 = new Libro(null, "Integridad", 8.07, e1, a3);
		Libro l6 = new Libro(null, "Al paraiso", 23.65, e1, a2);
		Libro l7 = new Libro(null, "El libro negro de las horas", 19.81, e1, a3);
		Libro l8= new Libro(null, "Un reino de carne y fuego", 20.42, e1, a1);
		librosDelBolsillo.add(l5);
		librosDelBolsillo.add(l6);
		librosDelBolsillo.add(l7);
		librosDelBolsillo.add(l8);
		
		// aqui hacemos la relación bidireccional, le asignamos a cada editorial
		// una lista de libros
		e1.setLibro(librosPlaneta);
		e2.setLibro(librosDelBolsillo);
		
		// declaramos una variable EntityManager con el apoyo de EntityManagerFactory
		// que es quien se encarga de abrir conexión con la BBDD. EntityManager es
		// quien gestiona las "querries" a la BBDD
		em = emf.createEntityManager();
		// Abrimos transacción con .getTransaction ya que se trata de una alteración
		// en las tablas, si fuese para únicamente coger datos no haría falta
		em.getTransaction().begin();
		// Persistimos la información en el contexto de persistencia, 
		// esto quiere decir que hasta que no hay un commit no se carga en la BBDD
		em.persist(e1); 
		em.persist(e2); 
		// abrimos trasacción y cargamos el contexto de persistencia en la BBDD
		em.getTransaction().commit(); 
		// cerramos el EntityManager
		em.close();	
		
		
		// Requerimiento 1.3 (alta ocho libros)
		// implementamos los libros de uno a uno
		Libro uno = new Libro(null, "Violeta", 21.75, e1, a3);
		Libro dos = new Libro(null, "No tengas miedo a nada", 17.00, e1, a2);
		Libro tres = new Libro(null, "El valle de los Arcángeles", 18.90, e1, a3);
		Libro cuatro = new Libro(null, "La fortaleza helada", 19.90, e1, a1);
		Libro cinco = new Libro(null, "El olivo de los Claudio", 21.85, e1, a3);
		Libro seis = new Libro(null, "El sueño de Texas", 17.57, e1, a2);
		Libro siete = new Libro(null, "Irene de Atenas", 20.90, e1, a3);
		Libro ocho = new Libro(null, "La victoria perdida", 20.90, e1, a1);
		
		// declaramos una variable EntityManager con el apoyo de EntityManagerFactory
		// que es quien se encarga de abrir conexión con la BBDD. EntityManager es
		// quien gestiona las "querries" a la BBDD
		em = emf.createEntityManager();
		// Abrimos transacción con .getTransaction ya que se trata de una alteración
		// en las tablas, si fuese para únicamente coger datos no haría falta
		em.getTransaction().begin();
		// Persistimos la información en el contexto de persistencia, 
		// esto quiere decir que hasta que no hay un commit no se carga en la BBDD
		em.persist(uno); 
		em.persist(dos);
		em.persist(tres);
		em.persist(cuatro);
		em.persist(cinco);
		em.persist(seis);
		em.persist(siete);
		em.persist(ocho);
		// abrimos trasacción y cargamos el contexto de persistencia en la BBDD
		em.getTransaction().commit(); 
		// cerramos el EntityManager
		em.close();	
		
		// Requerimiento 4 (alta dos librerias)
		// imlementamos las librerias de uno a uno
		List<Libreria> librerias1= new ArrayList<Libreria>();
		Libreria li1= new Libreria(null, "Hojas", "Bibiana Laporte", "C/ Magnolina 123, Madrid");
		Libreria li2= new Libreria(null, "Páginas", "Silvia Montana", "C/de la Esperaza 45, Madrid");
		librerias1.add(li2);
		librerias1.add(li1);
		List<Libreria> librerias2= new ArrayList<Libreria>();
		Libreria li21= new Libreria(null, "Pegamento", "Susana Santos", "C/ Buenavista 23, Valencia");
		Libreria li22= new Libreria(null, "Plumas", "Carmen Sierra", "C/del Mio Cid 4, Málaga");
		librerias2.add(li22);
		librerias2.add(li21);
		// para hacerlo bidireccional relacionamos cada libro con las librerias
		// donde están disponibles
		l5.setLibrerias(librerias1);
		l6.setLibrerias(librerias1);
		l7.setLibrerias(librerias2);
		l8.setLibrerias(librerias2);
		
		// declaramos una variable EntityManager con el apoyo de EntityManagerFactory
		// que es quien se encarga de abrir conexión con la BBDD. EntityManager es
		// quien gestiona las "querries" a la BBDD
		em = emf.createEntityManager();
		// Abrimos transacción con .getTransaction ya que se trata de una alteración
		// en las tablas, si fuese para únicamente coger datos no haría falta
		em.getTransaction().begin();
		// Persistimos la información en el contexto de persistencia, 
		// esto quiere decir que hasta que no hay un commit no se carga en la BBDD
		em.persist(li1); 
		em.persist(li2); 
		// abrimos trasacción y cargamos el contexto de persistencia en la BBDD
		em.getTransaction().commit(); 
		// cerramos el EntityManager
		em.close();
		// cerramos el EntityManagerFactory
		emf.close();
		}

}
