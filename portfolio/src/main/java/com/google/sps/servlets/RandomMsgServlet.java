package com.google.sps.servlets;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/** Handles requests sent to the /random URL. */
@WebServlet("/random")
public class RandomMsgServlet extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    ArrayList<String> messages = new ArrayList<>();
    messages.add("I'm learning to cook!");
    messages.add("I like Java.");
    messages.add("I like cats.");

    String jsonMsgs = convertToJson(messages);
    response.setContentType("application/json;");
    response.getWriter().println(jsonMsgs);
  }

  public String convertToJson(ArrayList<String> list) {
    String json = "{";
    json += "\"learning\": ";
    json += "\"" + list.get(0) + "\"";
    json += ", ";
    json += "\"programmingLanguage\": ";
    json += "\"" + list.get(1) + "\"";
    json += ", ";
    json += "\"animal\": ";
    json += "\"" + list.get(2) + "\"";
    json += "}";
    return json;
  }
}
