package serverapp;


import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Himel
 */
public class ServerResponse {
    
    private JSONObject jsonObject;
    
    public JSONObject getErrorResponse(){
        JSONObject jsonObject = new JSONObject();     
        try {
             jsonObject.put("status",400);
             jsonObject.put("message","Invalid Request");
             System.out.println(jsonObject);
        } finally{
          return jsonObject;  
        }
        
    }
    
    public void setResponse(String result){
        try {
            jsonObject = new JSONObject();
            
            jsonObject.put("status",200);
            jsonObject.put("result",result);
        } catch (JSONException ex) {
            Logger.getLogger(ServerResponse.class.getName()).log(Level.SEVERE, null, ex);
        }
             
       
        
    }
    
    public JSONObject getResponse(){
        return jsonObject;
    }
    
}
