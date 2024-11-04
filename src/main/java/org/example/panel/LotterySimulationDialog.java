package org.example.panel;

import org.example.pdf_creator.DocumentGenerator;
import org.example.service.LotterySimulator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LotterySimulationDialog {

    JComboBox numOfHits;
    long neededDraws;
    JDialog lotteryDialog;

    public JDialog getLotteryDialog() {
        return lotteryDialog;
    }

    public long getNeededDraws() {
        return neededDraws;
    }

    public LotterySimulationDialog(JFrame frame, LotteryTicket ticket) {
        
        //DIALOG
        lotteryDialog = new JDialog(frame);
        lotteryDialog.setTitle("Select number of hits in lottery");

        
        //BUTTONS
        JButton pdfButton = new JButton("Create PDF summary");
        pdfButtonConfig(ticket, pdfButton);

        JButton startLottery = new JButton("Start Lottery");
        startLotteryButtonConfig(ticket, startLottery, pdfButton);
        
        JButton closeButton = new JButton("close");
        closeButtonConfig(frame, closeButton);

        //ComboBox
        String[] num = {"1", "2", "3", "4", "5", "6"};
        numOfHits = new JComboBox(num);

        lotteryDialog.setSize(300, 150);
        lotteryDialog.add(numOfHits);
        lotteryDialog.add(startLottery);
        lotteryDialog.add(pdfButton);
        lotteryDialog.add(closeButton);
        lotteryDialog.setLayout(new FlowLayout());
        lotteryDialog.setLocationRelativeTo(frame);
        lotteryDialog.setVisible(true);
    }

    private void closeButtonConfig(JFrame frame, JButton closeButton) {
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lotteryDialog.setVisible(false);
                frame.setEnabled(true);
                frame.setVisible(true);
            }
        });
    }

    private void startLotteryButtonConfig(LotteryTicket ticket, JButton startLottery, JButton pdfButton) {
        startLottery.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lotteryGo(ticket, lotteryDialog, pdfButton);
            }
        });
    }

    private void pdfButtonConfig(LotteryTicket ticket, JButton pdfButton) {
        pdfButton.setEnabled(false);
        pdfButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DocumentGenerator generator = new DocumentGenerator();
                generator.createPDFFile(ticket, LotterySimulationDialog.this);
                pdfButton.setEnabled(false);
            }
        });
    }

    private void lotteryGo(LotteryTicket ticket, JDialog lotteryDialog, JButton pdfButton) {
        LotterySimulator lotterySimulator = new LotterySimulator();
        long result = lotterySimulator.startSimulator(ticket.chooseNumbers, numOfHits.getSelectedIndex() + 1);
        JOptionPane.showMessageDialog(lotteryDialog,
                "Number of draws to match "
                        + (numOfHits.getSelectedIndex() + 1)
                        + " of 6 lottery numbers: "
                        + result);
        neededDraws = result;
        pdfButton.setEnabled(true);
    }
}
