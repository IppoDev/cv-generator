package dev.ippo.cvgenarator.util;

import com.itextpdf.text.DocumentException;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;

import java.io.OutputStream;

public class PdfGeneratorUtil {

    public static void createPdfFromHtml(String html, OutputStream outputStream) throws DocumentException {
        PdfRendererBuilder builder = new PdfRendererBuilder();
        builder.useFastMode();
        builder.withHtmlContent(html, null);
        builder.toStream(outputStream);
        try {
            builder.run();
        } catch (Exception e) {
            throw new DocumentException(e);
        }
    }
}
