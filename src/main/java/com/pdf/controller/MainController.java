package com.pdf.controller;


import com.spire.pdf.PdfDocument;
import com.spire.pdf.PdfPageBase;
import com.spire.pdf.PdfPageSize;
import com.spire.pdf.graphics.PdfLayoutType;
import com.spire.pdf.graphics.PdfMargins;
import com.spire.pdf.graphics.PdfTemplate;
import com.spire.pdf.graphics.PdfTextLayout;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.geom.Point2D;


@RestController
public class MainController {


    @GetMapping("/welcome")
    public String welcome(){
        PdfDocument originPdf = new PdfDocument();
        originPdf.loadFromFile("C:\\Users\\Gopal Contractor\\Downloads\\Invoicedetail_INAE202402020045588.pdf");
        PdfDocument newPdf = new PdfDocument();

        for(int i = 0; i< originPdf.getPages().getCount(); i++)

        {

            //Add pages of size A1 to the new PDF

            PdfPageBase newPage = newPdf.getPages().add(PdfPageSize.A3, new PdfMargins((0)));

            //Create a PdfTextLayout instance

            PdfTextLayout layout = new PdfTextLayout();

            //Set text layout as one page (if not set the content will not scale to fit page size)

            layout.setLayout(PdfLayoutType.One_Page);

            //Create templates based on the pages in the original PDF

            PdfTemplate template = originPdf.getPages().get(i).createTemplate();

            //Draw templates onto the pages in the new PDF

            template.draw(newPage, new Point2D.Float(0,0), layout);

        }



        //Save the result document

        newPdf.saveToFile("C:\\Users\\Gopal Contractor\\Downloads\\convertedFileName2.pdf");


        return "Welcome to spring security app";
    }



}
