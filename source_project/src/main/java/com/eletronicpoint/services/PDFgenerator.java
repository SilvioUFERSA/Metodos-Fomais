package com.eletronicpoint.services;


import com.eletronicpoint.entities.Register;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class PDFgenerator {

    public void generatePDF(List<Register> registers, String filePath) throws FileNotFoundException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(filePath));

        document.open();

        // Add title and table headers
        document.add(new Paragraph("Register Report"));
        document.add(new Paragraph("document generated in: " + formatLocalDateTime(LocalDateTime.now())));
        document.add(new Paragraph("\n"));

        PdfPTable table = new PdfPTable(5); // Adjust column count as needed
        table.setWidthPercentage(100);

        table.addCell("ID");
        table.addCell("Employee Name");
        table.addCell("Date/Time");
        table.addCell("Type");
        table.addCell("Justification");

        // Add data from registers list
        for (Register register : registers) {
            table.addCell(String.valueOf(register.getId()));
            table.addCell(register.getEmployee().getName());
            table.addCell(register.getDateEndTime().toString());
            table.addCell(register.getType().toString());
            table.addCell(register.getJustification());
        }


        document.add(table);

        document.close();
    }

    private String formatLocalDateTime(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return dateTime.format(formatter);
    }

}