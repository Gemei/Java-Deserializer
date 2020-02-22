import org.unsynchronized.jdeserialize;

import java.io.*;
import java.util.Arrays;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Deserializer {

    public void deserialize(String fileName, boolean burpRequest){
        if(burpRequest){
            String tmpFileName = convertStreamBytestoFileStream(burpCleanup(fileName));
            invokJDeserialize(tmpFileName);
        } else {
            invokJDeserialize(fileName);
        }
    }

    private void invokJDeserialize(String fileName){
        String[] arguments = new String[] {fileName};
        jdeserialize.main(arguments);
    }

    public void deserialize(byte [] bytes){
        String fileName = convertStreamBytestoFileStream(bytes);
        invokJDeserialize(fileName);
    }

    private String convertStreamBytestoFileStream(byte [] bytes){
        String fileName = "obj.deser";
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try(FileOutputStream fileOutputStream = new FileOutputStream(fileName)) {
            fileOutputStream.write(bytes);
        } catch (Exception e) {e.printStackTrace();}
        return fileName;
    }

    private byte[] burpCleanup(String fileName){
        //Beginning of Serialized Object Bytes
        byte[] patternBytes = {(byte)0x00, (byte)0x05, (byte)0x73, (byte)0x72};
        //Magic Bytes to concatenate to serialized object
        byte[] magicBytes = {(byte)0xac, (byte)0xed};

        String fileContent = "";
        String serializedObject;

        byte [] bytes;

        try {
            FileInputStream fileInputStream = new FileInputStream(fileName);
            int character;
            while ((character = fileInputStream.read()) != -1) {
                fileContent += (char) character;
            }
            fileInputStream.close();
        }catch (Exception e){e.printStackTrace();}


        Pattern pattern = Pattern.compile("(?<=\\<request base64=\\\"true\\\">\\<\\!\\[CDATA\\[)(.*)(?=]]>)");
        Matcher matcher = pattern.matcher(fileContent);
        if (matcher.find()) {
            serializedObject = matcher.group(1);
        } else {
            System.out.println("Request is malformed");
            return null;
        }

        bytes = Base64.getDecoder().decode(serializedObject);
        int startOfObject = KPM.indexOf(bytes,patternBytes);
        bytes = Arrays.copyOfRange(bytes,startOfObject,bytes.length);

        // create a destination array that is the size of the two arrays
        byte[] destination = new byte[bytes.length + magicBytes.length];
        // copy ciphertext into start of destination (from pos 0, copy ciphertext.length bytes)
        System.arraycopy(magicBytes, 0, destination, 0, magicBytes.length);
        // copy mac into end of destination (from pos ciphertext.length, copy mac.length bytes)
        System.arraycopy(bytes, 0, destination, magicBytes.length, bytes.length);

        bytes = destination;
        return bytes;
    }

    private String bytesToHex(byte[] hashInBytes) {
        int count = 0;
        StringBuilder sb = new StringBuilder();
        for (byte b : hashInBytes) {
            sb.append(String.format("0x"+"%02x", b)+" ");
            count++;
            if(count == 16){
                sb.append("\n");
                count=0;
            }
        }
        return sb.toString();
    }
}
