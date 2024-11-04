package org.example.panel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.jar.JarEntry;

public class LotterySimulatonDialog {
    JButton startLottery = new JButton("Start Lottery");
    public LotterySimulatonDialog(JFrame frame, LotteryTicket ticket) {
        JDialog lotteryDialog = new JDialog(frame);
        lotteryDialog.setSize(200, 200);
        startLottery.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ticket.mainButtonsListenAgain(ticket.buttonList, ticket);
                ticket.chooseNumbers.removeAll(ticket.chooseNumbers);
                ticket.pdfButton.setEnabled(false);
                ticket.editButton.setEnabled(false);
                ticket.simulator.setEnabled(false);
            }
        });

        lotteryDialog.add(ticket.numOfHits);
        lotteryDialog.add(startLottery);




    }
}
