package com.gachen95.jsonprotobuf;

import com.gachen95.jsonprotobuf.pojo.Person;
import com.gachen95.jsonprotobuf.protobuf.PersonProtos;
import com.google.gson.Gson;
import com.google.protobuf.util.JsonFormat;

import java.io.FileOutputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class SavePerson extends HttpServlet {

    public SavePerson() {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Gson gson = new Gson();

        try {
            // get json string from post request
            StringBuilder sb = new StringBuilder();
            String s;
            while ((s = request.getReader().readLine()) != null) {
                sb.append(s);
            }

            // convert from string to json
            // make sure every field is correct
            Person person = gson.fromJson(sb.toString(), Person.class);

            // convert from json to protobuf
            PersonProtos.Person.Builder builder = PersonProtos.Person.newBuilder();
            JsonFormat.parser().ignoringUnknownFields().merge(sb.toString(), builder);
            PersonProtos.Person p = builder.build();
            System.out.println(p);

            // Write the new address book back to disk.
            String sessionId = request.getSession().getId();
            FileOutputStream output = new FileOutputStream("protobuf_" + sessionId + ".txt", true);
            p.writeTo(output);
            output.close();



            response.setStatus(HttpServletResponse.SC_OK);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

        } finally {
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().println("");
            response.getWriter().close();
        }
    }

}
