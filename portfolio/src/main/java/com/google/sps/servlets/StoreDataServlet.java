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

/** Servlet responsible for storing visitors' contact data entries. */
@WebServlet("/store-contact-info")
public class StoreDataServlet extends HttpServlet {

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    // Sanitize user input to remove HTML tags and JavaScript.
    String contact = Jsoup.clean(request.getParameter("contact-input"), Whitelist.none());
    long timestamp = System.currentTimeMillis();

    // Create an instance of Datastore class
    Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
    // Create a KeyFactory of kind ContactInfo
    KeyFactory keyFactory = datastore.newKeyFactory().setKind("ContactInfo");
    // Create an entity to be stored into the database
    FullEntity<IncompleteKey> contactEntity =
        Entity.newBuilder(keyFactory.newKey())
            .set("contact", contact)
            .set("timestamp", timestamp)
            .build();
    datastore.put(contactEntity);

    // Print the raw contact info to be seen in the server log
    System.out.println("Contact entered: " + request.getParameter("contact-input"));

    response.sendRedirect("/contact-redirect.html");
  }
}