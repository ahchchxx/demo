package com.example.a_middlewares.pdf;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.html2pdf.resolver.font.DefaultFontProvider;

import java.io.FileOutputStream;

/**
 * a demo of rendering HTML to PDF
 */
public class app {
    public static void main(String[] args) throws Exception {
        String htmlStr = "<div>" +
                "<h1>hi, 人力资源</h1>" +
                "<p><img src='https://www.baidu.com/img/flexible/logo/pc/result.png' /></p>" +
                "<table border='1'>" +
                "   <tr><th>a</th><th>啊</th></tr>" +
                "   <tr><td>吧</td><td>他</td></tr>" +
                "</table></div>";
        String pdfPath = "d:/payslip.pdf";

        ConverterProperties properties = new ConverterProperties();
        // way1: register system font (the third param in construct)
        DefaultFontProvider fontProvider = new DefaultFontProvider(true, true, true);
        // way2: add local font file
        // refer:  https://kb.itextpdf.com/home/it7kb/ebooks/itext-7-converting-html-to-pdf-with-pdfhtml/chapter-6-using-fonts-in-pdfhtml
        // DefaultFontProvider fontProvider = new DefaultFontProvider();
        // FontProgram fontProgram = FontProgramFactory.createFont("d:/font/Microsoft YaHei.ttf");
        // fontProvider.addFont(fontProgram);
        properties.setFontProvider(fontProvider);

        HtmlConverter.convertToPdf(htmlStr, new FileOutputStream(pdfPath), properties);

        System.out.println("success");
    }
}
