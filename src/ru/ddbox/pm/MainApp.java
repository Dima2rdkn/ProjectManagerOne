package ru.ddbox.pm;

import javafx.application.Application;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import ru.ddbox.pm.model.Project;
import ru.ddbox.pm.view.ProjectOverviewController;

import java.io.IOException;
import java.time.LocalDate;

public class MainApp extends Application {
    private Stage primaryStage;
    private BorderPane rootLayout;
    private ObservableList<Project> projectData = FXCollections.observableArrayList();

    public MainApp() {
        // Add some sample data
        projectData.add(new Project("Баня. Электрика", new SimpleObjectProperty<LocalDate>(LocalDate.of(1999, 2, 21))));
        projectData.add(new Project("Баня. Печь", new SimpleObjectProperty<LocalDate>(LocalDate.of(1999, 2, 21))));
        projectData.add(new Project("Баня. Перегродка", new SimpleObjectProperty<LocalDate>(LocalDate.of(1999, 2, 21))));
        projectData.add(new Project("Баня. Утепление", new SimpleObjectProperty<LocalDate>(LocalDate.of(1999, 2, 21))));
        projectData.add(new Project("Баня. Полы", new SimpleObjectProperty<LocalDate>(LocalDate.of(1999, 2, 21))));
        projectData.add(new Project("Баня. Двери", new SimpleObjectProperty<LocalDate>(LocalDate.of(1999, 2, 21))));
        projectData.add(new Project("Баня. Окна", new SimpleObjectProperty<LocalDate>(LocalDate.of(1999, 2, 21))));
        projectData.add(new Project("Кибернетекс. Авто", new SimpleObjectProperty<LocalDate>(LocalDate.of(1999, 2, 21))));
        projectData.add(new Project("Рождественский", new SimpleObjectProperty<LocalDate>(LocalDate.of(1999, 2, 21))));
    }

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Returns the data as an observable list of Persons.
     *
     * @return
     */
    public ObservableList<Project> getPersonData() {
        return projectData;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Project manager One");
        initRootLayout();
        showProjectOverview();
    }

    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/RL.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the person overview inside the root layout.
     */
    public void showProjectOverview() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/ProjectOverview.fxml"));
            AnchorPane projectOverview = loader.load();
            rootLayout.setCenter(projectOverview);
            ProjectOverviewController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the main stage.
     *
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }
}
