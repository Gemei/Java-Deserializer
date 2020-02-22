import java.util.Base64;

public class Main {
    public static void main(String[]args){
        String serialized = "rO0ABXNyAAlTb21lQ2xhc3MAAAAAAAAAAQIAA0kAAWlMAAFkdAASTGphdmEvbGFuZy9Eb3VibGU7TAABc3QAEkxqYXZhL2xhbmcvU3RyaW5nO3hwf////3NyABBqYXZhLmxhbmcuRG91YmxlgLPCSilr+wQCAAFEAAV2YWx1ZXhyABBqYXZhLmxhbmcuTnVtYmVyhqyVHQuU4IsCAAB4cL/wAAAAAAAAdAAQQUJDREVGR0hJSktMTU5PUA==";

        Deserializer deserializer = new Deserializer();
        deserializer.deserialize("file.ser");
        System.out.println("///////////////////////////////////////////////////////");
        deserializer.deserialize(Base64.getDecoder().decode(serialized));
    }
}
