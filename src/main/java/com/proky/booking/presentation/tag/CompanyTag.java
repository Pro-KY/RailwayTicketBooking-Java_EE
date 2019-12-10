package com.proky.booking.presentation.tag;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.Calendar;

/**
 * Custom tag implementation for displaying company name with current year.
 */
public class CompanyTag extends TagSupport {
    private static final Logger log = LogManager.getLogger(CompanyTag.class);
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int doStartTag() {
        JspWriter jspWriter = pageContext.getOut();

        try {
            int year = Calendar.getInstance().get(Calendar.YEAR);
            jspWriter.println("&copy; " + name + ", " + year);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return SKIP_BODY;
    }
}
