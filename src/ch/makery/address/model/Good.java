package ch.makery.address.model;

import javafx.beans.property.*;

import java.time.LocalDate;

/**
 * Created by Azat Zaripov on 21.05.16.
 */
public class Good {

    private final StringProperty name;
    private final StringProperty category;
    private final IntegerProperty quantity;
    private final ObjectProperty<LocalDate> date;


    public Good() {
        this(null, null);
    }

    public Good (String name, String category){
        this.name = new SimpleStringProperty(name);
        this.category = new SimpleStringProperty(category);
        this.quantity = new SimpleIntegerProperty(100);
        this.date= new SimpleObjectProperty<LocalDate>(LocalDate.of(1999, 2, 21));
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getCategory() {
        return category.get();
    }

    public StringProperty categoryProperty() {
        return category;
    }

    public void setCategory(String category) {
        this.category.set(category);
    }

    public Integer getQuantity() {
        return quantity.get();
    }

    public IntegerProperty quantityProperty() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity.set(quantity);
    }

    public LocalDate getDate() {
        return date.get();
    }

    public ObjectProperty<LocalDate> dateProperty() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date.set(date);
    }
}
