/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package number_guess_fx;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.CheckBox;

/**
 *
 * @author francoisa
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label guessLbl;
    
    private int answer;
    private int num;
    private final int tries = 5;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.exit(0);
    }
    
    @FXML
    private void handleCheckboxAction(ActionEvent event) {
        System.out.println("You clicked me!");
        CheckBox cb = (CheckBox) event.getSource();
        int guess = Integer.parseInt(cb.getText());
        cb.setDisable(true);
        if (guess != answer) {
            guessLbl.setText(guess + " is not the number. " + ++num + " of " + tries);
        }
        else {
            guessLbl.setText(guess + " is the number!");
        }
        if (num == tries) {
            guessLbl.setText("No luck. The number was: " + answer);
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        number();
    }    
    
    public void number() {
        Random random = new Random(System.currentTimeMillis());
	answer = Math.round(10*random.nextFloat()) + 1;
        num = 0;
    }
}
