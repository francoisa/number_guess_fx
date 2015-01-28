/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package number_guess_fx;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Stack;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author francoisa
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label guessLbl;
  
    @FXML
    private Button restartBtn;
    
    @FXML
    private VBox cbContainer;
    
    private Stack<CheckBox> cbStack;
    
    private int answer;
    private int num;
    private final int tries = 5;
    
    @FXML
    private void handleQuitAction(ActionEvent event) {
        System.exit(0);
    }
    
    @FXML
    private void handleRestartAction(ActionEvent event) {
        number();
        guessLbl.setText("");
        restartBtn.setDisable(true);
        enableCheckboxes();
        while (!cbStack.isEmpty()) {
            CheckBox cb = cbStack.pop();
            cb.setSelected(false);
            cb.setDisable(false);
        }
    }
    
    @FXML
    private void handleCheckboxAction(ActionEvent event) {
        System.out.println("You clicked me!");
        CheckBox cb = (CheckBox) event.getSource();
        int guess = Integer.parseInt(cb.getText());
        cb.setDisable(true);
        cbStack.push(cb);
        if (guess != answer) {
            guessLbl.setText(guess + " is not the number. " + ++num + " of " + tries);
        }
        else {
            disableCheckboxes();
            restartBtn.setDisable(false);
            guessLbl.setText(guess + " is the number!");
        }
        if (num == tries) {
            disableCheckboxes();
            restartBtn.setDisable(false);
            guessLbl.setText("No luck. The number was: " + answer);
        }
    }
    
    private void disableCheckboxes() {
        cbContainer.setDisable(true);
    }

    private void enableCheckboxes() {
        cbContainer.setDisable(false);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cbStack = new Stack<>();
        restartBtn.setDisable(true);
        number();
    }    
    
    public void number() {
        Random random = new Random(System.currentTimeMillis());
	answer = Math.round(10*random.nextFloat()) + 1;
        num = 0;
    }
}
