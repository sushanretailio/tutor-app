
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hansa.app.data.Tutor;
import java.io.File;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sushant
 */
public class Test {
    
    public static void main(String[] args) throws Exception {
        Tutor t = new ObjectMapper().readValue(new File("data.json"), Tutor.class);
        System.out.println(t.getName());
        
    }
}
