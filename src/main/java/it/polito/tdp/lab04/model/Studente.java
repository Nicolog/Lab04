package it.polito.tdp.lab04.model;

public class Studente {

	private Integer matricola;
	private String cognome;
	private String nome;
	private String cds;
	
	public Studente() {
		
	}
	public Studente(Integer matricola, String cognome, String nome, String cds) {
	
		this.matricola = matricola;
		this.cognome = cognome;
		this.nome = nome;
		this.cds = cds;
	}
	public Integer getMatricola() {
		return matricola;
	}
	public String getCognome() {
		return cognome;
	}
	public String getNome() {
		return nome;
	}
	public String getCds() {
		return cds;
	}
	
	@Override
	public String toString() {
		return  matricola + ", " + cognome + ", " + nome + ", " + cds ;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((matricola == null) ? 0 : matricola.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Studente other = (Studente) obj;
		if (matricola == null) {
			if (other.matricola != null)
				return false;
		} else if (!matricola.equals(other.matricola))
			return false;
		return true;
	}
	
	
	
}
