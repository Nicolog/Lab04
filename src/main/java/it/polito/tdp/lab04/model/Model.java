package it.polito.tdp.lab04.model;

import java.util.List;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {

	private CorsoDAO corsoDao;
	private StudenteDAO studenteDao;
	public Model() {
		this.corsoDao = new CorsoDAO();
		this.studenteDao = new StudenteDAO();
	}
	
	public String[] getNomeCognome(int matricola) {
		return this.studenteDao.getNomeCognome(matricola);
	}
	
	public List<Corso> getTuttiICorsi(){
		return this.corsoDao.getTuttiICorsi();
	}
	
	public List<Studente> getStudentiIscrittiAlCorso(Corso corso){
		return this.corsoDao.getStudentiIscrittiAlCorso(corso);
	}
	
	public List<Corso> getCorsiIscritto(int matricola){
		return this.studenteDao.getCorsiIscritto(matricola);
	}
	
	public Corso getCorso(String codins) {
		return this.corsoDao.getCorso(codins);
	}
	
	public boolean isCorrect(int matricola) {
		return this.studenteDao.isCorrect(matricola);
	}
}
