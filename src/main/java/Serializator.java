import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Serializator implements Serializable {

    public static void Serialize(House house, OutputStream outputStream) throws IOException {
        try(ObjectOutputStream objectOutputStream=new ObjectOutputStream(outputStream)) {
            objectOutputStream.writeObject(house);
        }catch (IOException e){
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    public static void Deserialize(House house, InputStream inputStream) throws ClassNotFoundException {
        try(ObjectInputStream objectInputStream=new ObjectInputStream(inputStream)) {
            House support = (House) objectInputStream.readObject();

            house.setCadastrnumber(support.getCadastrnumber());
            house.setAddress(support.getAddress());
            house.setMainperson(support.getMainperson());
            house.setList_flat(support.getList_flat());

        }catch (IOException e){
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    public static String HouseSerializator(House house) throws IOException {
        /*Writer writer=new FileWriter("house_"+house.getCadastrnumber(), StandardCharsets.UTF_8);
        ObjectMapper mapper=new ObjectMapper();
        mapper.writeValue(writer,house);
        writer.close();*/

        ObjectMapper mapper=new ObjectMapper();
        String jsonHouse=mapper.writeValueAsString(house);
        return jsonHouse;
    }

    public static House HouseDeserializator(String jsonHouse) throws IOException {
        /*Reader reader=new FileReader("house_"+house.getCadastrnumber(), StandardCharsets.UTF_8);
        ObjectMapper mapper=new ObjectMapper();
        house=mapper.readValue(reader,House.class);
        reader.close();*/
        ObjectMapper mapper=new ObjectMapper();
        House house=mapper.readValue(jsonHouse,House.class);
        return house;
    }

    public static boolean CompareJSonStrings(String js1, String js2) throws JsonProcessingException {
        ObjectMapper mapper=new ObjectMapper();
        JsonNode tree1=mapper.readTree(js1);
        JsonNode tree2=mapper.readTree(js2);
        return tree1.equals(tree2);
    }
}
