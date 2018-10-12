/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverapp;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Himel
 */
class Route {
    
    private static String className = null;
    private static String methodName = null;
    private static String param = null;
    
    public static void build(String requestUri){
        String[] segments = requestUri.split("/");
         
        switch(segments.length){
            case 2:
                className = segments[1];
                methodName = "error";
                break;
            case 3:
                className = segments[1];
                methodName = segments[2];
                break;
            case 4:
                className = segments[1];
                methodName = segments[2];
                param = segments[3];
                break;
            default:
        }   
        
        
    }
    
    public static ServerRequest getServerRequest() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        
        ServerRequest request = null;
            
        try {
            if(className != null){
                className = className.substring(0,1).toUpperCase()+className.substring(1);
            }
            className = (className == null)? "serverapp.controllers.DefaultController" : "serverapp.controllers."+className+"Controller";
            
            Class c = Class.forName(className);
            Object newObject = c.newInstance();

            request = new ServerRequest(c, newObject,methodName,param);
            
        } catch (ClassNotFoundException ex) {
            
            Class e_c = Class.forName("serverapp.ServerResponse");
            Object e_newObject = e_c.newInstance();
            request = new ServerInvalidRequest(e_c, e_newObject, "getErrorResponse", param);
            
        }  catch (IllegalArgumentException ex) {
            Logger.getLogger(Route.class.getName()).log(Level.SEVERE, null, ex);
        } 
            
        return request;     
      
    }
}
