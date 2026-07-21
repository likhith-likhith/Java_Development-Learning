package com.likhith.reservation.gui;

import javax.swing.*;
import java.awt.*;

public class PlaceholderPasswordField extends JPasswordField {

    private final String placeholder;

    public PlaceholderPasswordField(String placeholder) {
        this.placeholder = placeholder;
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        if (getPassword().length == 0 && !isFocusOwner()) {

            Graphics2D g2 = (Graphics2D) g.create();

            g2.setColor(Color.GRAY);
            g2.setFont(getFont());

            Insets in = getInsets();

            g2.drawString(
                    placeholder,
                    in.left + 5,
                    getHeight() / 2 + 5);

            g2.dispose();
        }
    }
}