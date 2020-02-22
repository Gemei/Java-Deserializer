import org.unsynchronized.jdeserialize;

import java.io.*;

public class Deserializer {

    public void deserialize(String fileName){
        String[] arguments = new String[] {fileName};
        jdeserialize.main(arguments);
    }

    public void deserialize(byte [] bytes){
        String fileName = convertStreamBytestoFileStream(bytes);
        String[] arguments = new String[] {fileName};
        jdeserialize.main(arguments);
    }

    private static String convertStreamBytestoFileStream(byte [] bytes){
        String fileName = "obj.deser";
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try(FileOutputStream fileOutputStream = new FileOutputStream(fileName)) {
            fileOutputStream.write(bytes);
        } catch (Exception e) {e.printStackTrace();}
        return fileName;
    }
}
