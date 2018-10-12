/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverapp.controllers;

import org.json.JSONObject;
import serverapp.ServerResponse;

/**
 *
 * @author Himel
 */
public class BaseController {
    
    public JSONObject error(JSONObject object){
        return new ServerResponse().getErrorResponse();
    }
}
