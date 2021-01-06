package com.example.a_middlewares.thymeleaf;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.StringWriter;

public class app {

    public static void main(String[] args) {
        // step1: prepare context
        Context context = new Context();
        context.setVariable("a", "false");
        context.setVariable("aa", "aVariable");

        // step2: the file name of template or contents of template
        String templateStr = "<p th:if='${a}' th:text='${a}'></p>" +
                "<p th:if='${aa}' th:text='${aa}'></p>";

        // step3: render
        String retStr = renderTemplate(context, templateStr);

        System.out.println(retStr);
    }

    private static String renderTemplate(Context context, String templateStr) {
        TemplateEngine templateEngine = new TemplateEngine();

        StringWriter stringWriter = new StringWriter();
        templateEngine.process(templateStr, context, stringWriter);

        return stringWriter.toString();
    }

}

// bk:
//     StringTemplateResolver templateResolver = new StringTemplateResolver();
//     templateResolver.setTemplateMode(TemplateMode.HTML); // TEXT, html tag is not working in this mode
//
//     Context context = new Context();
//     context.setVariable("a", "false");
//     context.setVariable("aa", "aVariable");
//
//     // the file name of template or contents of template
//     String templateStr = "<p th:if='${a}' th:text='${a}'></p>" +
//     "<p th:if='${aa}' th:text='${aa}'></p>";
//
//     StringWriter stringWriter = new StringWriter();
//
//     TemplateEngine templateEngine = new TemplateEngine();
//     templateEngine.setTemplateResolver(templateResolver); // StringTemplateResolver is the default one
//     templateEngine.process(templateStr, context, stringWriter);
//
//     System.out.println(stringWriter);