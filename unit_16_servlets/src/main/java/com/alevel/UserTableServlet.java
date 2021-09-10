package com.alevel;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@WebServlet(name = "table-servlet", urlPatterns = "/table")
public class UserTableServlet extends HttpServlet {
    private final Map<String,String> users = new HashMap<>();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter printWriter = response.getWriter();
        printWriter.write("<table border=\"1\"><tr><th>IP</th><th>User-Agent</th></tr>");

        String ip = request.getRemoteAddr();
        users.put(request.getRemoteAddr(), request.getHeader("User-Agent"));
        for (String userIp : users.keySet()) {
            String usersRow;
            if(Objects.equals(userIp, ip)) {
                usersRow = String.format("<tr><td><b>%s</b></td><td><b>%s</b></td></tr>", userIp, users.get(userIp));
            }else{
                usersRow = String.format("<tr><td>%s</td><td>%s</td></tr>", userIp, users.get(userIp));
            }
            printWriter.write(usersRow);
        }
        printWriter.write("</table>");
    }
}