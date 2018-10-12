/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverapp;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Himel
 */
public class ServerInvalidRequest extends ServerRequest{
    
    private static HttpServletResponse response;
    private static Class className;
    private static Object classObject;
    private static String methodName;
    private static String param;
    
    public ServerInvalidRequest(Class c, Object o, String mName, String p) {
        super(c, o, mName, p);
        className = c;
        classObject = o;
        methodName = mName;
        param = p;
    }
    
    public void setResponse(HttpServletResponse r){
        response = r;
        
    }
    
    public void dispatch(){
        response.setContentType("application/json; charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        
        try {
            
            JSONObject resultObject = (JSONObject) className.getMethod(methodName).invoke(classObject);
            response.getWriter().println(resultObject);
            
        } catch (IOException ex) {
            Logger.getLogger(ServerRequest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(ServerRequest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(ServerRequest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ServerRequest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(ServerRequest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(ServerRequest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
