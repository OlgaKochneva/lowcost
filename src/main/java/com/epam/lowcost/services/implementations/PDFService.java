package com.epam.lowcost.services.implementations;


import com.epam.lowcost.model.Ticket;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.canvas.PdfCanvasConstants;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.List;
import com.itextpdf.layout.element.ListItem;
import com.itextpdf.layout.element.Paragraph;
import org.springframework.stereotype.Component;

@Component
public class PDFService {

    public void createPDF_Ticket(Ticket ticket) throws Exception {
        PdfWriter writer = new PdfWriter(String.format("src/main/webapp/resources/tickets_pdf/Ticket_%d.pdf", ticket.getId()));
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

// Create a PdfFont
        PdfFont font = PdfFontFactory.createFont(StandardFonts.TIMES_BOLD);

// Add a Paragraph
        document.add(new Paragraph(String.format("Boarding Pass # %d", ticket.getId())).setFont(font));
// Create a List
        List baseInfo = new List()
                .setSymbolIndent(12)
                .setListSymbol("\u2022")
                .setFont(font);

        baseInfo.add(new ListItem(String.format("Passenger Name: %s  %s", ticket.getUser().getFirstName(), ticket.getUser().getLastName())))
                .add(new ListItem(String.format("Departure point: | %s |", ticket.getFlight().getDepartureAirport().getCityEng())))
                .add(new ListItem(String.format("Arrival point: | %s |", ticket.getFlight().getArrivalAirport().getCityEng())));
        document.add(baseInfo);

        document.add(new Paragraph("Flight information:").setFont(font));

        List flightInfo = new List()
                .setSymbolIndent(12)
                .setListSymbol("\u2022")
                .setFont(font);

        flightInfo.add(new ListItem(String.format("Flight # %s", ticket.getFlight().getId())))
                .add(new ListItem(String.format("Departure Date: %s",ticket.getFlight().getDepartureDate())))
                .add(new ListItem(String.format("Arrival Date: %s",ticket.getFlight().getArrivalDate())))
                .add(new ListItem(String.format("Class: %s", ticket.isBusiness()? "Business":"Economy")))
                .add(new ListItem(String.format("Baggage: %s",ticket.isHasLuggage()? "Yes":"No")))
                .add(new ListItem(String.format("Priority seat: %s",ticket.isPlacePriority()? "Yes":"No")));
        document.add(flightInfo);

        document.add(new Paragraph("Thank you for choosing our company!"));

        document.close();
    }


}
