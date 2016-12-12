/*
 * Copyright Gavin Jenkins.
 * All rights reserved.
 */
package utility;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author Gavin
 */
public class DeepCopy {
    
    public static Object copy(Object toCopy){
        Object copied = new Object();
        try{
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(bos);
        out.writeObject(toCopy);
        ByteArrayInputStream bis = new   ByteArrayInputStream(bos.toByteArray());
        ObjectInputStream in = new ObjectInputStream(bis);
        copied = (Object) in.readObject();
        } catch (Exception e){
            
        }
        return copied;
    }
}
