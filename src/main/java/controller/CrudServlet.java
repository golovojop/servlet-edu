/**
 * Создание сервлета
 * https://www.shortn0tes.com/2017/01/intellij-idea-community-edition-tomcat.html
 * https://habr.com/ru/post/333626/
 * <p>
 * Взаимодействие сервлета и JSP
 * https://docs.oracle.com/cd/B10501_01/java.920/a96657/basics.htm
 * <p>
 * Работа с СУБД
 * https://danielniko.wordpress.com/2012/04/17/simple-crud-using-jsp-servlet-and-mysql/
 */

package controller;

import dao.JdbcService;
import model.Person;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class CrudServlet extends HttpServlet {
    public void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws IOException {

        // Для того, чтобы в браузере отображалась кириллица
        httpServletResponse.setCharacterEncoding("UTF-8");

        PrintWriter out = httpServletResponse.getWriter();
        out.print("Hello from servlet");

        JdbcService js = new JdbcService(getServletContext());

        Person[] ps = js.selectAll();
        out.println("<table border=\"1\">");
        for (Person p : ps) {
            out.println("<tr>");
            out.println("<td>" + p.getId() + "</td>");
            out.println("<td>" + p.getFirstname() + "</td>");
            out.println("<td>" + p.getLastname() + "</td>");
            out.println("</tr>");
        }
        out.println("</table>");

    }
}
