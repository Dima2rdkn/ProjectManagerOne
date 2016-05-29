package ru.ddbox.pm.model;

/**
 * Model class for a Project.
 * <p>
 * Created by dd on 03.11.2015.
 */

import javafx.beans.property.*;

import java.time.LocalDate;

public class Project {

    private final StringProperty projectName;
    private final DoubleProperty planCost;
    private final DoubleProperty factCost;
    private final ObjectProperty<LocalDate> startDate;
    private final ObjectProperty<LocalDate> stopDate;

    /**
     * Default constructor.
     */
    public Project() {
        this(null, null);
    }

    /**
     * Constructor with some initial data.
     *
     * @param projectName
     * @param startDate
     */
    public Project(String projectName, ObjectProperty<LocalDate> startDate) {
        this.projectName = new SimpleStringProperty(projectName);
        this.startDate = new SimpleObjectProperty<LocalDate>(LocalDate.of(1999, 2, 21));
        this.stopDate = new SimpleObjectProperty<LocalDate>(LocalDate.of(1999, 2, 21));
        // Some initial dummy data, just for convenient testing.
        this.planCost = new SimpleDoubleProperty(225415.55);
        this.factCost = new SimpleDoubleProperty(225415.55);
    }

    public String getProjectName() {
        return projectName.get();
    }

    public void setProjectName(String projectName) {
        this.projectName.set(projectName);
    }

    public StringProperty projectNameProperty() {
        return projectName;
    }

    public Double getPlanCost() {
        return planCost.get();
    }

    public void setPlanCost(Double planCost) {
        this.planCost.set(planCost);
    }

    public DoubleProperty planCostProperty() {
        return planCost;
    }

    public Double getFactCost() {
        return factCost.get();
    }

    public void setFactCost(Double factCost) {
        this.factCost.set(factCost);
    }

    public DoubleProperty factCostProperty() {
        return factCost;
    }

    public LocalDate getStartDate() {
        return startDate.get();
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate.set(startDate);
    }

    public ObjectProperty<LocalDate> startDateProperty() {
        return startDate;
    }
}
