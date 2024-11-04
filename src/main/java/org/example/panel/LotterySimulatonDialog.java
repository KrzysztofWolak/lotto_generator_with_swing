package org.example.panel;

import org.example.service.LotterySimulator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.jar.JarEntry;

public class LotterySimulatonDialog {
    JButton startLottery = new JButton("Start Lottery");
    JComboBox numOfHits;

    public LotterySimulatonDialog(JFrame frame, LotteryTicket ticket) {
        JDialog lotteryDialog = new JDialog(frame);
        lotteryDialog.setSize(300, 150);

        String[] num = {"1", "2", "3", "4", "5", "6"};
        numOfHits = new JComboBox(num);
        startLottery.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LotterySimulator lotterySimulator = new LotterySimulator();
                long result = lotterySimulator.startSimulator(ticket.chooseNumbers, numOfHits.getSelectedIndex()+1);
                JOptionPane.showMessageDialog(lotteryDialog,
                        "Number of draws to match 2 of 6 lottery numbers: " + result);
            }
        });

        JButton closeButton = new JButton("close");
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lotteryDialog.setVisible(false);
                frame.setEnabled(true);
                frame.setVisible(true);
            }
        });
        lotteryDialog.add(numOfHits);
        lotteryDialog.add(startLottery);
        lotteryDialog.add(closeButton);
        lotteryDialog.setLayout(new FlowLayout());
        lotteryDialog.setLocationRelativeTo(frame);
        lotteryDialog.setVisible(true);
    }
}
