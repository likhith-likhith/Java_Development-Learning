package com.likhith.atm.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class PlaceholderTextField extends JTextField {

    private final String placeholder;

    public PlaceholderTextField(String placeholder) {

        this.placeholder = placeholder;

        setText(placeholder);
        setForeground(Color.GRAY);

        addFocusListener(new FocusAdapter() {

            @Override
            public void focusGained(FocusEvent e) {

                if (getText().equals(placeholder)) {
                    setText("");
                    setForeground(Color.BLACK);
                }

            }

            @Override
            public void focusLost(FocusEvent e) {

                if (getText().isEmpty()) {
                    setText(placeholder);
                    setForeground(Color.GRAY);
                }

            }

        });

    }

}