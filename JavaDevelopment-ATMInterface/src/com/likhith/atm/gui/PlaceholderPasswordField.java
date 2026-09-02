package com.likhith.atm.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class PlaceholderPasswordField extends JPasswordField {

    private final String placeholder;

    public PlaceholderPasswordField(String placeholder) {

        this.placeholder = placeholder;

        setText(placeholder);
        setForeground(Color.GRAY);
        setEchoChar((char) 0);

        addFocusListener(new FocusAdapter() {

            @Override
            public void focusGained(FocusEvent e) {

                if (String.valueOf(getPassword()).equals(placeholder)) {

                    setText("");
                    setForeground(Color.BLACK);
                    setEchoChar('*');

                }

            }

            @Override
            public void focusLost(FocusEvent e) {

                if (getPassword().length == 0) {

                    setText(placeholder);
                    setForeground(Color.GRAY);
                    setEchoChar((char) 0);

                }

            }

        });

    }

}