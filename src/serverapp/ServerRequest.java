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
class ServerRequest {
    
    private static Class className;
    private static Object classObject;
    private static InputStream inputStream;
    private static HttpServletResponse response;
    private static String methodName;
    private static String param;
    
    public ServerRequest(Class c, Object o){
        className   = c;
        classObject = o;
    }
    
    public ServerRequest(Class c, Object o,String mName, String p){
        className   = c;
        classObject = o;
        methodName  = mName;
        param = p;
        
    }
    
    public void setInputStream(InputStream is){
        inputStream = is;
    }
    
    public void setResponse(HttpServletResponse r){
        response = r;
        
    }
    
    public void dispatch() throws IOException, JSONException{
        
        
        JSONObject jsonObject;
        
        try {
            String inputString = _getJSONString(inputStream);
            if(!inputString.startsWith("{") || !inputString.endsWith("}")){
                JSONObject json = new JSONObject();
                json.put("status", 400);
                json.put("message","Invalid JSON");
                System.out.println(json);
                response.getWriter().println(json);
                return;
            }
            jsonObject = new JSONObject(inputString);
            JSONObject resultObject = (JSONObject) className.getMethod(methodName, JSONObject.class).invoke(classObject,jsonObject);
            response.getWriter().println(resultObject);
            
        } catch (NoSuchMethodException ex) {
            JSONObject json = new JSONObject();
            json.put("status", 400);
            json.put("message","Invalid Request");
            System.out.println(json);
            response.getWriter().println(json);
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
    
    private String _getJSONString(InputStream is)
    {
        StringBuffer sb = new StringBuffer();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line = null;
            while((line = br.readLine()) != null){
                sb.append(line);
            }
            
        } catch (IOException ex) {
            Logger.getLogger(ServerApp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sb.toString();
    }
    
    
    
    
}
