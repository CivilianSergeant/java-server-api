/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverapp;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.InvocationTargetException;
import java.net.InetSocketAddress;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jdk.nashorn.internal.ir.debug.JSONWriter;
import jdk.nashorn.internal.parser.JSONParser;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.json.*;
/**
 *
 * @author Himel
 */
public class ServerApp extends AbstractHandler{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Server server = new Server(new InetSocketAddress("127.0.0.1", 80));
        
        server.setHandler(new ServerApp());

        try {
            server.start();
            server.join();
        } catch (Exception ex) {
            Logger.getLogger(ServerApp.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("application/json; charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        
        if(!request.getMethod().equalsIgnoreCase("POST")){
            
            response.getWriter().println("Sorry! No Get Request allowed");
            baseRequest.setHandled(true);
            return;
        }
        
        try {
            Route.build(request.getRequestURI());
            ServerRequest serverReq = Route.getServerRequest();
            serverReq.setInputStream(request.getInputStream());
            serverReq.setResponse(response);
            serverReq.dispatch();
            baseRequest.setHandled(true);
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServerApp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(ServerApp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ServerApp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JSONException ex) {
            try {
                JSONObject json = new JSONObject();
                json.put("status", 400);
                json.put("message","Invalid JSON");
                System.out.println(json);
                response.getWriter().println(json);
                baseRequest.setHandled(true);
                
            } catch (JSONException ex1) {
                Logger.getLogger(ServerApp.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        
    }
    
    
    
    
    
}
