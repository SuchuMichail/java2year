import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FlatDeserializer extends StdDeserializer<Flat> {
    public FlatDeserializer() {
        super(Flat.class);
    }

    @Override
    public Flat deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        int flatnumber = 0;
        double square = 0;
        List<Person> list_person = new ArrayList<>();

        jsonParser.nextToken();
        while(jsonParser.nextToken()!= JsonToken.END_OBJECT){
            String fieldname=jsonParser.currentName();
            if(fieldname.equals("flatnumber")){
                flatnumber=jsonParser.getIntValue();
            }else if(fieldname.equals("square")){
                jsonParser.nextToken();
                square=jsonParser.getDoubleValue();
            }else if(fieldname.equals("list_person")){
                jsonParser.nextToken();
                while(jsonParser.nextToken()!=JsonToken.END_ARRAY){
                    ObjectMapper mapper=new ObjectMapper();
                    list_person.add(mapper.readValue(jsonParser.getText(),Person.class));
                }
            }else{
                throw new IllegalArgumentException("Wrong format");
            }
        }
        Flat flat=new Flat(flatnumber,square,list_person);
        return flat;
    }
}
