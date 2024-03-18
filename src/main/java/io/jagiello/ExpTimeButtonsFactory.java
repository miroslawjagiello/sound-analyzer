package io.jagiello;

import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;

import java.util.concurrent.atomic.AtomicReference;

public class ExpTimeButtonsFactory {
    VBox create(AtomicReference<Double> tau) {
        RadioButton rb1 = new RadioButton("1000");
        rb1.setOnAction(event -> {
            tau.set(1000d);
        });
        RadioButton rb2 = new RadioButton("125");
        rb1.setSelected(true);
        rb2.setOnAction(event -> {
            tau.set(125d);
        });
        ToggleGroup group = new ToggleGroup();
        rb1.setToggleGroup(group);
        rb2.setToggleGroup(group);

        return new VBox(rb1, rb2);
    }
}
