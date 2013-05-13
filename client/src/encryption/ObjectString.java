/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package encryption;

import java.io.*;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 *
 * @author kamilersz
 */
public class ObjectString {

    static private BASE64Encoder encode = new BASE64Encoder();
    static private BASE64Decoder decode = new BASE64Decoder();

    static public String OToS(Object obj) {
        String out = null;
        if (obj != null) {
            try {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                oos.writeObject(obj);
                out = encode.encode(baos.toByteArray());
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
        return out;
    }

    static public Object SToO(String str) {
        Object out = null;
        if (str != null) {
            try {

                ByteArrayInputStream bios = new ByteArrayInputStream(decode.decodeBuffer(str));
                ObjectInputStream ois = new ObjectInputStream(bios);
                out = ois.readObject();
            } catch ( IOException | ClassNotFoundException e) {
                e.printStackTrace();
                return null;
            }
        }
        return out;
    }
}