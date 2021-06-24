/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.crimes;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.crimes.model.Model;
import it.polito.tdp.crimes.model.Vicino;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	private Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="boxAnno"
    private ComboBox<Integer> boxAnno; // Value injected by FXMLLoader

    @FXML // fx:id="boxMese"
    private ComboBox<Integer> boxMese; // Value injected by FXMLLoader

    @FXML // fx:id="boxGiorno"
    private ComboBox<Integer> boxGiorno; // Value injected by FXMLLoader

    @FXML // fx:id="btnCreaReteCittadina"
    private Button btnCreaReteCittadina; // Value injected by FXMLLoader

    @FXML // fx:id="btnSimula"
    private Button btnSimula; // Value injected by FXMLLoader

    @FXML // fx:id="txtN"
    private TextField txtN; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void doCreaReteCittadina(ActionEvent event) {

    	txtResult.clear();
    	Integer anno= boxAnno.getValue();
    	if(anno==null) {
    		txtResult.appendText("selezioanre un anno!");
    		return;
    	}
    	
    	
    	model.creaGrafo(anno);
    	txtResult.appendText("Grafo creato!\n");
    	txtResult.appendText("#vertici: "+model.numVertici()+ "\n");
    	txtResult.appendText("#archi: "+model.numArchi()+ "\n");
    	
    	for(Integer i: model.getDistretti()) {
    		txtResult.appendText("Elenco distretti adiacenti al distretto: "+i+ "\n");
    		for(Vicino v: model.getVicini(i)) {
    			txtResult.appendText(v.toString()+ "\n");
    		}
    		txtResult.appendText("\n");
    	}
    }

    @FXML
    void doSimula(ActionEvent event) {

    	txtResult.clear();
    	Integer anno= boxAnno.getValue();
    	if(anno==null) {
    		txtResult.appendText("selezioanre un anno!");
    		return;
    	}
        boxMese.getItems().addAll(model.getMesi(anno));
    	
    	Integer mese= boxMese.getValue();
    	if(mese==null) {
    		txtResult.appendText("selezioanre un mese!");
    		return;
    	}
    	boxGiorno.getItems().addAll(model.getGiorni(anno,mese));
    	Integer numero= Integer.parseInt(txtN.getText());
    	if(numero==null || numero>10 || numero<1) {
    		txtResult.appendText("scrivere un numero compreso tra 1 e 10");
    		return;
    	}
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert boxAnno != null : "fx:id=\"boxAnno\" was not injected: check your FXML file 'Scene.fxml'.";
        assert boxMese != null : "fx:id=\"boxMese\" was not injected: check your FXML file 'Scene.fxml'.";
        assert boxGiorno != null : "fx:id=\"boxGiorno\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCreaReteCittadina != null : "fx:id=\"btnCreaReteCittadina\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnSimula != null : "fx:id=\"btnSimula\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtN != null : "fx:id=\"txtN\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model = model;
    	boxAnno.getItems().addAll(model.getYears());
    	
    	
    }
}
