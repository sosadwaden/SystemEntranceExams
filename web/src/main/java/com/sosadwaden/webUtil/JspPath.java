package com.sosadwaden.webUtil;

import lombok.experimental.UtilityClass;

@UtilityClass
public class JspPath {

    private static final String JSP_PATH = "/WEB-INF/jsp/%s.jsp";

    public static String getJspPath(String jspName) {
        return String.format(JSP_PATH, jspName);
    }

}
