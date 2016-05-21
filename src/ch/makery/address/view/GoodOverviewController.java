package ch.makery.address.view;

import ch.makery.address.util.DateUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import ch.makery.address.MainApp;
import ch.makery.address.model.Good;

/**
 * Created by Azat Zaripov on 21.05.16.
 */

public class GoodOverviewController {
    @FXML
    private TableView<Good> table;
    @FXML
    private TableColumn<Good, String> nameColumn;
    @FXML
    private TableColumn<Good, String> categoryColumn;

    @FXML
    private Label nameLabel;
    @FXML
    private Label categoryLabel;
    @FXML
    private Label quantityLabel;
    @FXML
    private Label dateLabel;

    private MainApp mainApp;

    public GoodOverviewController() {
    }

    @FXML
    private void initialize() {
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        categoryColumn.setCellValueFactory(cellData -> cellData.getValue().categoryProperty());

        showGoodDetails(null);

        table.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showGoodDetails(newValue));
    }

    @FXML
    private void handleDeleteGood() {
        int selectedIndex = table.getSelectionModel().getSelectedIndex();
        if (selectedIndex>=0) {
            table.getItems().remove(selectedIndex);
        } else {
            alertMethod();
        }
    }

    @FXML
    private void handleNewGood() {
        Good tempPerson = new Good();
        boolean okClicked = mainApp.showPersonEditDialog(tempPerson);
        if (okClicked) {
            mainApp.getGoods().add(tempPerson);
        }
    }

    @FXML
    private void handleEditGood() {
        Good selectedPerson = table.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            boolean okClicked = mainApp.showPersonEditDialog(selectedPerson);
            if (okClicked) {
                showGoodDetails(selectedPerson);
            }
        } else {
            alertMethod();
        }
    }

    private void alertMethod() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.initOwner(mainApp.getPrimaryStage());
        alert.setTitle("No Selection");
        alert.setHeaderText("No Good Selected");
        alert.setContentText("Please select a good in the table.");
        alert.showAndWait();
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        table.setItems(mainApp.getGoods());
    }

    private void showGoodDetails(Good good) {
        if (good != null) {
            nameLabel.setText(good.getName());
            categoryLabel.setText(good.getCategory());
            quantityLabel.setText(good.getQuantity()+"");
            dateLabel.setText(DateUtil.format(good.getDate()));
        } else {
            nameLabel.setText("");
            categoryLabel.setText("");
            quantityLabel.setText("");
            dateLabel.setText("");
        }
    }
}