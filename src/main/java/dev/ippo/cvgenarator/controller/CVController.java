package dev.ippo.cvgenarator.controller;


import dev.ippo.cvgenarator.model.CVData;
import dev.ippo.cvgenarator.service.PdfService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
public class CVController {

    @Autowired
    private PdfService pdfService;

    @GetMapping("/generateCV")
    public ModelAndView showCVForm() {
        return new ModelAndView("cvForm");
    }

    @PostMapping("/generateCV")
    public ModelAndView generateCV(@ModelAttribute CVData cvData, HttpServletResponse response) throws IOException {
        ModelAndView modelAndView = new ModelAndView("cvTemplate");
        modelAndView.addObject("cvData", cvData);

        //htmlni generate qilw va pdfga aylantirw
        String htmlContent = pdfService.generateHtml(cvData);
        pdfService.createPdf(htmlContent, response);

        return modelAndView;
    }
}
