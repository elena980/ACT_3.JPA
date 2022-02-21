package modelo.entidad;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

	@Entity //metemos la clase alumno en un tabla
	@Table( name="contratos_Laborales") //damos nombre a la tabla
	public class ContratoLaboral {
		
		@Id // proporcionamos el id y lo ponemos autoicrement con @GeneratedValue
		@GeneratedValue( strategy = GenerationType.IDENTITY)
		private Integer id;
		private String numeroContrato;
		
		// con @Column le estamos asignado un nombre a la columna en la BBDD que 
		// llevará este atributo
		@Column(name = "numero_cuenta")
		private String cuenta;

		// ContratoLaboral tiene una realción de uno a uno con Profesor,
		// en este caso es indiferente dónde le coloquemos la FK, hemos optado
		// por colocarlo aquí
		@OneToOne
		@JoinColumn(name = "fk_id_profesor", referencedColumnName = "id")
		private Profesor profesor;

		public ContratoLaboral(Integer id, String numeroContrato, String cuenta, Profesor profesor) {
			super();
			this.id = id;
			this.numeroContrato = numeroContrato;
			this.cuenta = cuenta;
			this.profesor = profesor;
		}

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getNumeroContrato() {
			return numeroContrato;
		}

		public void setNumeroContrato(String numeroContrato) {
			this.numeroContrato = numeroContrato;
		}

		public String getCuenta() {
			return cuenta;
		}

		public void setCuenta(String cuenta) {
			this.cuenta = cuenta;
		}

		public Profesor getProfesor() {
			return profesor;
		}

		public void setProfesor(Profesor profesor) {
			this.profesor = profesor;
		}
		
}
