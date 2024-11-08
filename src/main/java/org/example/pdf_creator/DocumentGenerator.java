package org.example.pdf_creator;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.example.panel.LotterySimulationDialog;
import org.example.panel.LotteryTicket;

import javax.swing.*;
import java.io.IOException;
import java.util.Calendar;

import static org.apache.pdfbox.pdmodel.font.Standard14Fonts.FontName.COURIER;

public class DocumentGenerator{

    public DocumentGenerator() {
    }

    public void createPDFFile (LotteryTicket ticket, LotterySimulationDialog lsd) {
        Calendar c = Calendar.getInstance();
        String filename = "C:\\Users\\Admin\\Desktop\\Training\\lotto-generator-with-swing\\src\\PDF_files\\ticket_from"
                + "_" + c.get(Calendar.DAY_OF_MONTH)
                + "_" + c.get(Calendar.MONTH)
                + "_" + c.get(Calendar.MILLISECOND)
                + ".pdf";
        try {
        PDDocument document = new PDDocument();
        PDPage page1 = new PDPage();
        document.addPage(page1);
        PDPageContentStream p = new PDPageContentStream(document, page1);


            writeTitle(p, 90, 750, 25 , "Your chosen lottery numbers");
            writeNewLine(p, 40, 700, "Ticket :  ", returnNumbers(ticket));
            writeNewLine(p, 40, 670, "Number of draws needed (Last attempt) :  ", String.valueOf(lsd.getNeededDraws()));
            p.close();
            document.save(filename);
            document.close();
            JOptionPane.showMessageDialog(lsd.getLotteryDialog(), "PDF file created in: \n" +
                    "lotto-generator-with-swing/src/PDF_files");

        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void writeTitle(PDPageContentStream content,
                                  int tx, int ty, int fontSize,
                                  String text) {
        try{
            content.beginText();
            content.newLineAtOffset(tx, ty); // 595 x 842\
            content.setFont(new PDType1Font(COURIER), fontSize);
            content.showText(text);
            content.endText();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void writeNewLine(PDPageContentStream content,
                                    int tx, int ty,
                                    String text,
                                    String text2) {
        try{
            content.beginText();
            content.newLineAtOffset(tx, ty); // 595 x 842\
            content.setFont(new PDType1Font(COURIER),16);
            content.showText(text);
            content.setFont(new PDType1Font(COURIER),20);
            content.showText(text2);
            content.endText();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Selected lottery numbers to save to pdf file
    private static String returnNumbers ( LotteryTicket ticket) {
        String numbers = "";
        for (int s : ticket.getChooseNumbers()) {
            numbers += String.valueOf(s) + ", ";
        }
        numbers = numbers.substring(0, numbers.length()-2);
        return numbers;
    }


}
