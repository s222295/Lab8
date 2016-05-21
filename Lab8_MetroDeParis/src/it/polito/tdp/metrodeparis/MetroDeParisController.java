package it.polito.tdp.metrodeparis;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.Lab8_MetroDeParis.Model.Model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.TextArea;

public class MetroDeParisController {
	private Model model = new Model();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ChoiceBox<String> cbStazionePartenza;

    @FXML
    private ChoiceBox<String> cbStazioneArrivo;
    
    @FXML
    private Button btnCalcolaDistanza;

    @FXML
    private TextArea txtArea;
    
	public void setModel(Model model) {
		
		this.model=model;
		model.generaGrafo();
		cbStazionePartenza.getItems().addAll(model.elencoNomifermate());
        cbStazioneArrivo.getItems().addAll(model.elencoNomifermate());
	}
    @FXML
    void doCalcolaDistanza(ActionEvent event) {

    	
    	String s1 = cbStazionePartenza.getValue();
    	String s2 = cbStazioneArrivo.getValue();
    	List<String> lista = new LinkedList<>();
    	if(s1!=null && s2!=null){
    		lista=model.getCammino(s1, s2);
    	}
    	int tempoTotaleInSecondi = (int) model.tempoPercorrenzaStimato();
		int ore = tempoTotaleInSecondi / 3600;
		int minuti = (tempoTotaleInSecondi % 3600) / 60;
		int secondi = tempoTotaleInSecondi % 60;
		String timeString = String.format("%02d:%02d:%02d", ore, minuti, secondi);
    	txtArea.setText("Percorso: "+lista.toString()+"\n\nTempo di percorrenza stimato: "+timeString);
    	
    }

    @FXML
    void initialize() {
        assert cbStazionePartenza != null : "fx:id=\"cbStazionePartenza\" was not injected: check your FXML file 'MetroDeParis.fxml'.";
        assert cbStazioneArrivo != null : "fx:id=\"cbStazioneArrivo\" was not injected: check your FXML file 'MetroDeParis.fxml'.";
        assert btnCalcolaDistanza != null : "fx:id=\"btnCalcolaDistanza\" was not injected: check your FXML file 'MetroDeParis.fxml'.";
        assert txtArea != null : "fx:id=\"txtArea\" was not injected: check your FXML file 'MetroDeParis.fxml'.";
     
    }


}
