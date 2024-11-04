package org.example.panel;

import com.sun.jdi.connect.Connector;
import org.example.pdf_creator.DocumentGenerator;
import org.example.service.LotterySimulator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.LongStream;

public class LotteryTicket {

    protected int count;
    JButton editButton = new JButton("EDIT");

    JButton simulator = new JButton("Start lottery Simulation");
    List<JButton> buttonList;
    Set<Integer> chooseNumbers = new HashSet<>();

    public Set<Integer> getChooseNumbers() {
        return chooseNumbers;
    }

    public LotteryTicket() {
        JFrame frame = new JFrame();
        buttonList = new ArrayList<>();

        editButton.setEnabled(false);
        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainButtonsListenAgain(buttonList, LotteryTicket.this);
                chooseNumbers.removeAll(chooseNumbers);
                editButton.setEnabled(false);
                simulator.setEnabled(false);
            }
        });
        for (int i = 1; i < 50; i++) {
            JButton button = new JButton("" + i);
            ActionListener listener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    button.setEnabled(false);
                    count++;
                    chooseNumbers.add(Integer.parseInt(button.getText()));
                    if (count == 6) {
                        listenerEraser(buttonList);
                        System.out.println("Button blocked");
                        editButton.setEnabled(true);
                        simulator.setEnabled(true);
                    }
                }
            };
            button.addActionListener(listener);
            frame.add(button);
            buttonList.add(button);
        }
        simulator.setEnabled(false);
        simulator.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LotterySimulatonDialog lotterySimulatonDialog
                        =  new LotterySimulatonDialog(frame, LotteryTicket.this);
                frame.setEnabled(false);
            }
        });
        frame.add(editButton);
        frame.add(simulator);
        frame.setSize(350, 400);
        frame.setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void listenerEraser(List<JButton> buttonList) {
        for (int i = 0; i < 49; i++) {
            for (ActionListener aL : buttonList.get(i).getActionListeners())
                buttonList.get(i).removeActionListener(aL);
        }
        ActionListener sim = simulator.getAction();
        simulator.removeActionListener(sim);
    }


    protected void mainButtonsListenAgain(List<JButton> buttonList, LotteryTicket lotteryTicket) {
        count = 0;
        chooseNumbers.removeAll(chooseNumbers);
        for (JButton button : buttonList) {
            button.setEnabled(true);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    button.setSelected(true);
                    button.setEnabled(false);
                    count++;
                    chooseNumbers.add(Integer.parseInt(button.getText()));

                    if (count == 6) {
                        listenerEraser(buttonList);
                        System.out.println("Button blocked");
//                        pdfButton.setEnabled(true);
                        editButton.setEnabled(true);
                        simulator.setEnabled(true);
                    }
                }
            });

        }

    }
}

