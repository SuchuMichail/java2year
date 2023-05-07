import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HouseDeserializer extends StdDeserializer<House> {
    public HouseDeserializer() {
        super(House.class);
    }

    @Override
    public House deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        String cadastrnumber = "";
        String address="";
        Person mainperson = null;
        List<Flat> list_flat=new ArrayList<>();

        jsonParser.nextToken();
        while(jsonParser.nextToken()!= JsonToken.END_OBJECT){
            String fieldname=jsonParser.currentName();
            if(fieldname.equals("cadastrnumber")){
                cadastrnumber=jsonParser.getText();
                jsonParser.nextToken();
            }else if(fieldname.equals("address")) {
                address=jsonParser.getText();
                jsonParser.nextToken();
            }else if(fieldname.equals("mainperson")){
                ObjectMapper mapper=new ObjectMapper();
                mainperson=mapper.readValue(jsonParser.getText(),Person.class);
                jsonParser.nextToken();
            }else if(fieldname.equals("list_flat")){
                ObjectMapper mapper=new ObjectMapper();
                while(jsonParser.nextToken()!=JsonToken.END_ARRAY){
                    list_flat.add(mapper.readValue(jsonParser.getText(),Flat.class));
                }
            }
        }

        House house = new House(cadastrnumber,address,mainperson,list_flat);
        return house;
    }
}
