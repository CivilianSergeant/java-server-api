/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverapp.controllers;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import serverapp.ServerApp;
import serverapp.ServerResponse;


/**
 *
 * @author Himel
 */
public class CalcController extends BaseController{
    
    public JSONObject sum(JSONObject requestJSON){
        
        ServerResponse serverResponse = new ServerResponse();
        try {
             
             System.out.println("OP1: "+requestJSON.getString("op1"));
             System.out.println("OP2: "+requestJSON.getString("op2"));
             
             int a = Integer.parseInt(requestJSON.getString("op1"));
             int b = Integer.parseInt(requestJSON.getString("op2"));
             int c = a+b;
             
             serverResponse.setResponse(String.valueOf(c));
             System.out.println(serverResponse.getResponse());
        } catch (JSONException ex) {
            Logger.getLogger(ServerApp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return serverResponse.getResponse();
    }
    
    public JSONObject substract(JSONObject requestJSON){
        
        ServerResponse serverResponse = new ServerResponse();
        try {
             
             System.out.println("OP1: "+requestJSON.getString("op1"));
             System.out.println("OP2: "+requestJSON.getString("op2"));
             
             int a = Integer.parseInt(requestJSON.getString("op1"));
             int b = Integer.parseInt(requestJSON.getString("op2"));
             int c = a-b;
             
             serverResponse.setResponse(String.valueOf(c));
             System.out.println(serverResponse.getResponse());
        } catch (JSONException ex) {
            Logger.getLogger(ServerApp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return serverResponse.getResponse();
    }
    
    public JSONObject multiply(JSONObject requestJSON){
        
        ServerResponse serverResponse = new ServerResponse();
        try {
             
             System.out.println("OP1: "+requestJSON.getString("op1"));
             System.out.println("OP2: "+requestJSON.getString("op2"));
             
             int a = Integer.parseInt(requestJSON.getString("op1"));
             int b = Integer.parseInt(requestJSON.getString("op2"));
             int c = a*b;
             
             serverResponse.setResponse(String.valueOf(c));
             System.out.println(serverResponse.getResponse());
        } catch (JSONException ex) {
            Logger.getLogger(ServerApp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return serverResponse.getResponse();
    }
    
    public JSONObject division(JSONObject requestJSON){
        
        ServerResponse serverResponse = new ServerResponse();
        try {
             
             System.out.println("OP1: "+requestJSON.getString("op1"));
             System.out.println("OP2: "+requestJSON.getString("op2"));
             
             int a = Integer.parseInt(requestJSON.getString("op1"));
             int b = Integer.parseInt(requestJSON.getString("op2"));
             String result = "";
             if(b != 0){
                 int c = a/b;
                 result = String.valueOf(c);
             }else{
                 result = "Cannot divided by zero";
             }
             
             
             serverResponse.setResponse(result);
             System.out.println(serverResponse.getResponse());
        } catch (JSONException ex) {
            Logger.getLogger(ServerApp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return serverResponse.getResponse();
    }
}
