package org.example.panel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class TicketNumberCreator {
    public TicketNumberCreator(JFrame frame, LotteryTicket ticket, List<JButton> buttonList) {

            for (int i = 1; i < 50; i++) {
                JButton button = new JButton("" + i);
                ActionListener listener = new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        button.setSelected(true);
                        button.setEnabled(false);
                        ticket.count++;
                        System.out.println("-------------------------------");
                        System.out.println("Selected count: " + ticket.count);
                        System.out.println("Selected button: " + button.getText());
                        System.out.println("Is selected?: " + button.isSelected());
                        System.out.println("-------------------------------");
                        if (ticket.count == 6) {
                            listenerEraser(buttonList);
                            System.out.println("Button blocked");
                        }
                    }
                };
                button.addActionListener(listener);
                frame.add(button);
                buttonList.add(button);
            }
        }

    private void listenerEraser (List<JButton> buttonList) {
        for (int i = 0; i < 49; i++) {
            for (ActionListener aL : buttonList.get(i).getActionListeners())
                buttonList.get(i).removeActionListener(aL);
        }
    }
    }
