/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */
package it.polito.tdp.lab04;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

    private Model model;
	
	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Corso> cmbCorsi;

    @FXML
    private TextField txtCognome;

    @FXML
    private TextField txtMatricola;

    @FXML
    private TextField txtNome;

    @FXML
    private TextArea txtRisultato;

    @FXML
    void handleCercaCorsi(ActionEvent event) {
    	txtRisultato.clear();
    	String matricola = this.txtMatricola.getText();
    	int matr;
    	try {
    		matr = Integer.parseInt(matricola);
    	} catch(NumberFormatException e) {
    		txtRisultato.setText("Inserisici un numero di matricola");
    		return;
    	}
    	if(model.isCorrect(matr)==false) {
	    	this.txtRisultato.setText("ERRORE! matricola non esistente");
	   		return;
	   	}
    
    	if(this.cmbCorsi.getValue()==null || this.cmbCorsi.getValue().getNome().equals("")) {
	    	for(Corso c : model.getCorsiIscritto(matr)) {
	    		this.txtRisultato.appendText(c.toStringCompleto()+"\n");
	    	}
    	}
    	else {
    		List<Corso> corsi = model.getCorsiIscritto(matr);
    		Corso corso = this.cmbCorsi.getValue();
    		if(corsi.contains(corso))
    			this.txtRisultato.setText("Studente iscritto al corso");
    		else
    			this.txtRisultato.setText("Studente non iscritto al corso");
    	}
    	
    }

    @FXML
    void handleCercaIscrittiCorso(ActionEvent event) {
    	this.txtRisultato.clear();
    	Corso corso = this.cmbCorsi.getValue();
    	
    	if(corso==null || corso.getNome().equals("")) {
    		this.txtRisultato.setText("ERRORE inserire un corso");
    		return;
    	}
    	
    	for(Studente s : model.getStudentiIscrittiAlCorso(corso)) {
    		this.txtRisultato.appendText(s.toString()+"\n");
    	}
    		
    }

    @FXML
    void handleIscrivi(ActionEvent event) {

    }

    @FXML
    void handleNomeCognome(ActionEvent event) {

    	String matricola = txtMatricola.getText();
    	int matr;
    	try {
    		matr = Integer.parseInt(matricola);
    	} catch(NumberFormatException e) {
    		txtRisultato.setText("Inserisici un numero di matricola");
    		return;
    	}
    	if(model.isCorrect(matr)==false) {
	    	this.txtRisultato.setText("ERRORE! matricola non esistente");
	   		return;
	   	}
    	
    	String array[]=model.getNomeCognome(matr);
    	this.txtNome.setText(array[0]);
    	this.txtCognome.setText(array[1]);
    }

    @FXML
    void initialize() {
        assert cmbCorsi != null : "fx:id=\"cmbCorsi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtRisultato != null : "fx:id=\"txtRisultato\" was not injected: check your FXML file 'Scene.fxml'.";

       
        this.cmbCorsi.getItems().add(new Corso(""));
        
      	
    }

    
    public void setModel(Model model) {
    	this.model = model;
    	
    	 for(Corso c: model.getTuttiICorsi())
    	       	this.cmbCorsi.getItems().add(c);
    }
}
