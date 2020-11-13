/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Base64;

/**
 *
 * @author ocris
 */
public class Token {
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
    
    public String validarToken(Token oToken){
        byte[] decodedBytes = Base64.getDecoder().decode(oToken.getToken());
        String decodedString = new String(decodedBytes);
        String[] split;
        split = decodedString.split(";");
                
        if (split[0].equals("token")){
            if (split[1].equals("0")){
                return "professor";
            }else if(split[1].equals("1"))
                return "aluno";
        }
        return null;
    }
    
    
}
