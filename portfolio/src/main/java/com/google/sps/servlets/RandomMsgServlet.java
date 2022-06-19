package com.google.sps.servlets;

import com.google.gson.Gson;
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

    // Convert the ArrayList to json string and print the string
    String jsonMsgs = convertToJson(messages);
    response.setContentType("application/json;");
    response.getWriter().println(jsonMsgs);
  }

  /**
   * Convert an ArrayList of String to its json encoding.
   * 
   * @param list an ArrayList of String
   * @return the json encoding String of the ArrayList of String
   */
  private String convertToJson(ArrayList<String> list) {
    Gson gson = new Gson();
    String json = gson.toJson(list);
    return json;
  }
}
