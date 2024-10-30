package org.example.panel;

import com.sun.jdi.connect.Connector;
import org.example.pdf_creator.DocumentGenerator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class LotteryTicket {
    int count;
    List<JButton> buttonList;
    List<ActionListener> listenerList;
//
    public void createNewTicket() {
        JFrame frame = new JFrame();
        buttonList = new ArrayList<>();
        TicketNumberCreator numberCreator =
                new TicketNumberCreator(frame,this, buttonList);
        JButton pdfButton = new JButton("Create PDF");
        pdfButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DocumentGenerator generator = new DocumentGenerator();
                generator.createPDFFile(frame);
            }
        });
        frame.add(pdfButton);
        frame.setSize(400, 400);
        frame.setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }




}

