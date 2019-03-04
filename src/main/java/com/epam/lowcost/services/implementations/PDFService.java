package com.epam.lowcost.services.implementations;


import com.epam.lowcost.model.Ticket;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;

@Component
public class PDFService {

    public void createPDF_Ticket(Ticket ticket) throws Exception {
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(String.format("src/main/webapp/resources/tickets_pdf/Ticket_№%d.pdf",ticket.getId())));
        document.open();
        Image img = Image.getInstance("src/main/webapp/resources/static/img/ticket_template.jpg");
        img.scaleToFit(500,900);
        document.add(img);
        PdfContentByte over = writer.getDirectContent();
        Font font = new Font(Font.FontFamily.COURIER);
        BaseFont bf = font.getCalculatedBaseFont(false);

        over.setTextRenderingMode(PdfContentByte.TEXT_RENDER_MODE_FILL_STROKE);
        over.setFontAndSize(bf,8);
        over.setColorStroke(new BaseColor(51, 154, 185));


        setText(over,String.format("number %d",ticket.getFlight().getId()),87,721);
        setText(over,String.format("%s %s",ticket.getUser().getLastName(),ticket.getUser().getFirstName()),87,708);
        setText(over,String.format("%s",ticket.getFlight().getDepartureDate().toLocalDate().toString()),87,693);
        setText(over,String.format("%s",ticket.getFlight().getDepartureDate().toLocalTime().toString()),87,680);
        setText(over,String.format("%s",ticket.getFlight().getDepartureAirport().getNameEng()),87,665);
        setText(over,String.format("%s",ticket.getFlight().getArrivalAirport().getNameEng()),87,652);
        setText(over,String.format("%s",ticket.isBusiness()? "business":"economy"),87,637);
        setText(over,String.format("%s",ticket.isPlacePriority()? "priority":"regular"),87,624);

        setText(over,String.format("number %d",ticket.getFlight().getId()),440,765);
        setText(over,String.format("%s %s",ticket.getUser().getLastName(),ticket.getUser().getFirstName()),440,752);
        setText(over,String.format("%s",ticket.getFlight().getDepartureDate().toLocalDate().toString()),440,737);
        setText(over,String.format("%s",ticket.getFlight().getDepartureDate().toLocalTime().toString()),440,724);
        setText(over,String.format("%s",ticket.getFlight().getDepartureAirport().getNameEng()),440,709);
        setText(over,String.format("%s",ticket.getFlight().getArrivalAirport().getNameEng()),440,696);
        setText(over,String.format("%s",ticket.isBusiness()? "business":"economy"),440,681);
        setText(over,String.format("%s",ticket.isPlacePriority()? "priority":"regular"),440,668);

        document.close();

    }

    private void setText(PdfContentByte over,String msg,int x, int y){
        over.beginText();
        over.moveText(x,y);
        over.showText(msg);
        over.endText();

    }
}
