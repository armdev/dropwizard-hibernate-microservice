package com.project.dropwizard.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class ClientGet {

    public static void main(String[] args) {
        try {
            //for test
            HttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet("http://localhost:8080/contact/" + 1);
            HttpResponse response = client.execute(request);

            HttpEntity entity = response.getEntity();
         //   System.out.println("Returned@@@@@ " + EntityUtils.toString(entity));

           // System.out.println("Output from Server @@@@.... \n");
            // Contact cont = (Contact) entity;

            ObjectMapper mapper = new ObjectMapper();
           // Contact cont = mapper.readValue((EntityUtils.toString(entity)), Contact.class);
            //System.out.println("GET FIRSTNAME " + cont.getFirstName());
          //  System.out.println("GET LASTNAME " + cont.getLastName());
        } catch (IOException e) {

            e.printStackTrace();

        }

    }

}
