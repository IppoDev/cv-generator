package dev.ippo.cvgenarator.service;

import dev.ippo.cvgenarator.util.PdfGeneratorUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import dev.ippo.cvgenarator.model.CVData;

@Service
public class PdfService {

    @Autowired
    private TemplateEngine templateEngine;

    public String generateHtml(CVData cvData) {
        // Thymeleaf bilan htmlni generate qilw
        Context context = new Context();
        context.setVariable("cvData", cvData);
        return templateEngine.process("cvTemplate", context);  // cvTemplate.html ni render qiliw ucun
    }

    public void createPdf(String htmlContent, HttpServletResponse response) {
        // htmlni PDF formatga aylantirw
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=cv.pdf");

        try {
            PdfGeneratorUtil.createPdfFromHtml(htmlContent, response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
