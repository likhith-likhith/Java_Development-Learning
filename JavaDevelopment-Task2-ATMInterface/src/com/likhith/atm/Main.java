package com.likhith.atm;

import javax.swing.SwingUtilities;
import com.likhith.atm.gui.LoginFrame;

public class Main {

    public static void main(String[] args) {

        Bank bank = new Bank();

        SwingUtilities.invokeLater(() -> {

            new LoginFrame(bank);

        });

    }

}