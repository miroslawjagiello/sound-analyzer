package io.jagiello;

import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;

public class ExpTimeButtonsFactory {
    VBox create() {
        RadioButton rb1 = new RadioButton("1000");
        rb1.setSelected(true);
        RadioButton rb2 = new RadioButton("125");

        ToggleGroup group = new ToggleGroup();
        rb1.setToggleGroup(group);
        rb2.setToggleGroup(group);

        return new VBox(rb1, rb2);
    }
}
