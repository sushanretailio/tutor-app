
import com.hansa.app.data.DocumentType;
import com.hansa.app.service.S3Service;

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
        S3Service serv = new S3Service();
        String url = serv.save("Test".getBytes(), "text", "testKey",DocumentType.CV);
        System.out.println(url);
    }
}
