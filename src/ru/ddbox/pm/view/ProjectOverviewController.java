package ru.ddbox.pm.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import ru.ddbox.pm.MainApp;
import ru.ddbox.pm.model.Project;
import ru.ddbox.pm.utils.DateUtils;

/**
 * Created by dd on 03.11.2015.
 */
public class ProjectOverviewController {
    @FXML
    private TableView<Project> projectTable;
    @FXML
    private TableColumn<Project, String> projectNameColumn;
    @FXML
    private TableColumn<Project, Double> planCostColumn;
    @FXML
    private TableColumn<Project, Double> factCostColumn;

    @FXML
    private Label projectNameLabel;
    @FXML
    private Label planCostLabel;
    @FXML
    private Label factCostLabel;
    @FXML
    private Label startDateLabel;

    // Reference to the main application.
    private MainApp mainApp;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public ProjectOverviewController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.
        projectNameColumn.setCellValueFactory(cellData -> cellData.getValue().projectNameProperty());
        planCostColumn.setCellValueFactory(cellData -> cellData.getValue().planCostProperty().asObject());
        factCostColumn.setCellValueFactory(cellData -> cellData.getValue().factCostProperty().asObject());
        // Clear person details.
        showprojectDetails(null);
        // Listen for selection changes and show the person details when changed.
        projectTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showprojectDetails(newValue));
    }

    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
        projectTable.setItems(mainApp.getPersonData());
        // Загружаем информацию по первому проекту
        showprojectDetails(projectTable.getItems().get(0));
    }

    /**
     * Fills all text fields to show details about the person.
     * If the specified person is null, all text fields are cleared.
     *
     * @param project the person or null
     */
    private void showprojectDetails(Project project) {
        if (project != null) {
            // Fill the labels with info from the person object.
            projectNameLabel.setText(project.getProjectName());
            planCostLabel.setText(project.getPlanCost().toString());
            factCostLabel.setText(project.getFactCost().toString());
            startDateLabel.setText(DateUtils.format(project.getStartDate()));

        } else {
            // Person is null, remove all the text.
            projectNameLabel.setText("");
            planCostLabel.setText("");
            factCostLabel.setText("");
            startDateLabel.setText("");
        }
    }

    /**
     * Called when the user clicks on the delete button.
     */
    @FXML
    private void handleDeleteProject() {
        int selectedIndex = projectTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            projectTable.getItems().remove(selectedIndex);
        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Ошибка!");
            alert.setHeaderText("Не выбран проект");
            alert.setContentText("Укажите удаляемый проект в таблице.");

            alert.showAndWait();
        }
    }

}
