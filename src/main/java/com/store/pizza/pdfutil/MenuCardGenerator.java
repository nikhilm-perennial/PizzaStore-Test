package com.store.pizza.pdfutil;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.store.pizza.model.Product;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class MenuCardGenerator {

    private List<Product> productList;
    private List<Product> productList1;

    private List<Product> productList2;

    public MenuCardGenerator(List<Product> productList,List<Product> productList1,List<Product> productList2) {
        this.productList = productList;
        this.productList1 = productList1;
        this.productList2 = productList2;
    }

    private void writeTableHeader(PdfPTable table){
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.CYAN);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("Product Name",font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Price",font));
        table.addCell(cell);
    }

    private void writeTableHeader1(PdfPTable table1){
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.CYAN);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("Product Name",font));
        table1.addCell(cell);

        cell.setPhrase(new Phrase("Crust",font));
        table1.addCell(cell);
        cell.setPhrase(new Phrase("Size",font));
        table1.addCell(cell);
    }

    private void writeTableHeader2(PdfPTable table2){
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.CYAN);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("Product Name",font));
        table2.addCell(cell);

        cell.setPhrase(new Phrase("Price",font));
        table2.addCell(cell);
    }

    private void writeTableData(PdfPTable table){
        for (Product product:productList){
            table.addCell(product.getName());
            table.addCell(String.valueOf(product.getPrice()));
        }
    }

    private void writeTableData1(PdfPTable table1){
        for (Product product:productList1){
            table1.addCell(product.getName());
            table1.addCell(String.valueOf(product.getPizza().getAvailableCrust()));
            table1.addCell(String.valueOf(product.getPizza().getAvailableSize()));
        }
    }

    private void writeTableData2(PdfPTable table2){
        for (Product product:productList2){
            table2.addCell(product.getName());
            table2.addCell(String.valueOf(product.getPrice()));

        }
    }

    public void export(HttpServletResponse res) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, res.getOutputStream());

        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.DARK_GRAY);

        Paragraph p = new Paragraph("Pizza Store", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);

        Paragraph p1 = new Paragraph("Beverages/Dessert's", font);
        p1.setAlignment(Paragraph.ALIGN_LEFT);

        Paragraph p2 = new Paragraph("Pizza's", font);
        p2.setAlignment(Paragraph.ALIGN_LEFT);

        Paragraph p3 = new Paragraph("Combo Product's", font);
        p3.setAlignment(Paragraph.ALIGN_LEFT);

        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100f);
        table.setWidths(new float[]{3.0f, 3.5f});
        table.setSpacingBefore(10);

        PdfPTable table1 = new PdfPTable(3);
        table1.setWidthPercentage(100f);
        table1.setWidths(new float[]{3.0f, 2.20f, 1.25f});
        table1.setSpacingBefore(10);


        PdfPTable table2 = new PdfPTable(2);
        table2.setWidthPercentage(100f);
        table2.setWidths(new float[]{3.0f, 3.5f});
        table2.setSpacingBefore(10);

        writeTableHeader(table);
        writeTableData(table);

        writeTableHeader1(table1);
        writeTableData1(table1);

        writeTableHeader2(table2);
        writeTableData2(table2);

        document.add(p);
        document.add(p1);
        document.add(table);
        document.add(p2);
        document.add(table1);
        document.add(p3);
        document.add(table2);

        document.close();
    }
}
