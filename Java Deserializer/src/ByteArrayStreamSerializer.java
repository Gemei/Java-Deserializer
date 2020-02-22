import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Base64;

public class ByteArrayStreamSerializer {

    public String serializeObject(Object object){
        try{
            //Creating stream and writing the object
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(object);

            objectOutputStream.close();

            System.out.println(byteArrayOutputStream);
            String serializedObject = Base64.getEncoder().encodeToString(byteArrayOutputStream.toByteArray());
            System.out.println("Object Serialized");

            return serializedObject;
        }catch(Exception e){System.out.println(e);}
        return null;
    }

    private Object deserializeObject(String input){
        byte [] inputBytes = Base64.getDecoder().decode(input);
        //String test = new String(inputBytes);
        //writeUsingFiles(test);
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(inputBytes));
            Object object  = objectInputStream.readObject();
            objectInputStream.close();

            System.out.println("Object Deserialized");
            return object;
        }catch (Exception e){System.out.println(e);}
        return null;
    }
}
