package com.google.sps.servlets;

import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.FullEntity;
import com.google.cloud.datastore.IncompleteKey;
import com.google.cloud.datastore.KeyFactory;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

/** Servlet responsible for storing visitors' feedbacks. */
@WebServlet("/store-feedback")
public class StoreFeedbackServlet extends HttpServlet {

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    // Sanitize user input to remove HTML tags and JavaScript.
    String feedback = Jsoup.clean(request.getParameter("feedback-input"), Whitelist.none());
    long timestamp = System.currentTimeMillis();

    // Create an instance of Datastore class
    Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
    // Create a KeyFactory of kind Feedback
    KeyFactory keyFactory = datastore.newKeyFactory().setKind("Feedback");
    // Create an entity to be stored into the database
    FullEntity<IncompleteKey> feedbackEntity =
        Entity.newBuilder(keyFactory.newKey())
            .set("feedback", feedback)
            .set("timestamp", timestamp)
            .build();
    datastore.put(feedbackEntity);

    // Print the raw feedback to be seen in the server log
    System.out.println("Feedback entered: " + request.getParameter("feedback-input"));

    response.sendRedirect("/feedback-redirect.html");
  }
}
