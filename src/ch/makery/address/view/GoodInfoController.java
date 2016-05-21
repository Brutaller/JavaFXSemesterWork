package ch.makery.address.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ch.makery.address.model.Good;

import java.time.LocalDate;

public class GoodInfoController {

    @FXML
    private TextField nameField;
    @FXML
    private TextField categoryField;
    @FXML
    private TextField quantityField;
    @FXML
    private DatePicker dateField;


    private Stage dialogStage;
    private Good good;
    private boolean okClicked = false;

    @FXML
    private void initialize() {
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setGood(Good good) {
        this.good = good;

        nameField.setText(good.getName());
        categoryField.setText(good.getCategory());
        quantityField.setText(good.getQuantity()+"");
        dateField.setValue(good.getDate());
        dateField.setPromptText("dd.mm.yyyy");
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    private void handleOk() {
        if (isInputValid()) {
            good.setName(nameField.getText());
            good.setCategory(categoryField.getText());
            good.setQuantity(Integer.parseInt(quantityField.getText()));
            good.setDate(dateField.getValue());

            okClicked = true;
            dialogStage.close();
        }
    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (nameField.getText() == null || nameField.getText().length() == 0) {
            errorMessage += "No valid good name!\n";
        }
        if (categoryField.getText() == null || categoryField.getText().length() == 0) {
            errorMessage += "No valid good category!\n";
        }
        try {
            Integer.parseInt(quantityField.getText());
            if (quantityField.getText() == null || quantityField.getText().length() == 0) {
                errorMessage += "No valid quantity of goods!\n";
            }
        } catch (RuntimeException e){
            errorMessage += "No valid quantity of goods!\n";
        }

        if (dateField.getValue() == null || dateField.getValue().compareTo(LocalDate.now()) < 0) {
            errorMessage += "No valid date!\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }
}