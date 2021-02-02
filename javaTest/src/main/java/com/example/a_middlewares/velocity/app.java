package com.example.a_middlewares.velocity;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.runtime.RuntimeServices;
import org.apache.velocity.runtime.RuntimeSingleton;
import org.apache.velocity.runtime.parser.ParseException;

import java.io.StringWriter;

/**
 * Velocity Template Engine
      https://github.com/apache/velocity-engine
      http://velocity.apache.org/
 */
public class app {

    public static void main(String[] args) throws Exception {
        // 1.prepare context
        VelocityContext context = new VelocityContext();
        context.put("name", new String("Velocity"));

        // 2.prepare template
        String tempStr = "#if($name)\n" +
                "    <p>${name}</p>\n" +
                "#end";

        // render the template to result string
        String retStr = renderVelocityTemplate(context, tempStr);

        System.out.println(retStr);
    }

    private static String renderVelocityTemplate(VelocityContext context, String templateStr) throws ParseException {
        Velocity.init();
        RuntimeServices runtimeServices = RuntimeSingleton.getRuntimeServices();

        Template template = new Template();
        // template.setName(templateName); //"myVelocityTemplate.vm";
        // template.setEncoding("UTF-8");
        template.setRuntimeServices(runtimeServices);
        template.setData(runtimeServices.parse(templateStr, null)); // SimpleNode
        template.initDocument();

        StringWriter sw = new StringWriter();
        template.merge(context, sw);

        return sw.toString();
    }

}

// other examples:  https://github.com/apache/velocity-engine/tree/master/velocity-engine-examples
//     // 0. setup
//     Velocity.init();
//     // 1. prepare context
//     VelocityContext context = new VelocityContext();
//     context.put("name", "Velocity");
//     // 2.init tempate
//     Template template = Velocity.getTemplate("xx file name", "encoding: UTF-8")
//     // 3.render it
//     StringWriter sw = new StringWriter();
//     template.merge(context, sw);
//     print(sw);
