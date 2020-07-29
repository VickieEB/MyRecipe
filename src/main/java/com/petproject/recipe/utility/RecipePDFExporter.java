package com.petproject.recipe.utility;

import com.lowagie.text.Font;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.petproject.recipe.domain.Recipe;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.util.Set;

public class RecipePDFExporter {

    private Set<Recipe> recipeList;

    public RecipePDFExporter(Set<Recipe> recipeList) {
        this.recipeList = recipeList;
    }

    private void writeTableHeader(PdfPTable table){
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.white);
        cell.setPadding(5);
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setColor(Color.black);

        cell.setPhrase(new Phrase("Recipe Id.", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Recipe Name", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("URL", font));
        table.addCell(cell);
    }

    private void writeTableData(PdfPTable table){
        for (Recipe recipe :recipeList) {
            table.addCell(recipe.getId().toString());
            table.addCell(recipe.getDescription());
            table.addCell(recipe.getUrl());
        }

    }

    public void export(HttpServletResponse response) throws Exception{
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();

        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLDOBLIQUE);
        font.setColor(Color.orange);
        font.setSize(18);

        Paragraph title = new Paragraph("List of Recipes");
        title.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(title);

        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100);
        table.setSpacingBefore(15);
        table.setKeepTogether(true); 
        table.setWidths(new float[]{1.0f, 6.0f, 5.0f});

        writeTableHeader(table);
        writeTableData(table);

        document.add(table);

        document.close();

    }
}
