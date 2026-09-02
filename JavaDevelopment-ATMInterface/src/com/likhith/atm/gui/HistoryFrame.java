package com.likhith.atm.gui;

import com.likhith.atm.Bank;
import com.likhith.atm.Transaction;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class HistoryFrame extends JFrame {

    public HistoryFrame(Bank bank) {

        setTitle("Transaction History");
        setSize(750,400);
        setLocationRelativeTo(null);
        setResizable(false);

        String[] columns = {
                "Date & Time",
                "Type",
                "Amount",
                "Description"
        };

        DefaultTableModel model = new DefaultTableModel(columns,0);

        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        for(Transaction t : bank.getTransactions()){

            model.addRow(new Object[]{
                    t.getDateTime(),
                    t.getType(),
                    "₹"+t.getAmount(),
                    t.getDescription()
            });

        }

        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }

}