/**
 * Создание сервлета
 * https://www.shortn0tes.com/2017/01/intellij-idea-community-edition-tomcat.html
 * https://habr.com/ru/post/333626/
 *
 * Взаимодействие сервлета и JSP
 * https://docs.oracle.com/cd/B10501_01/java.920/a96657/basics.htm
 *
 * Работа с СУБД
 * https://danielniko.wordpress.com/2012/04/17/simple-crud-using-jsp-servlet-and-mysql/
 */

package controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CrudServlet extends HttpServlet {
    public void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws IOException {
        httpServletResponse.getWriter().print("Hello from servlet");
    }
}
