import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

public class PersonDeserializer extends StdDeserializer<Person> {

    public PersonDeserializer() {
        super(Person.class);
    }

    @Override
    public Person deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        String fullname = null;
        String surname;
        String name;
        String secondname;
        int[] data = new int[3];

        jsonParser.nextToken();
        while(jsonParser.nextToken()!=JsonToken.END_OBJECT){
            String fieldname= jsonParser.currentName();
            if(fieldname.equals("fullname")){
                fullname=jsonParser.getText();
            }else if(fieldname.equals("data")){
                int i=0;
                jsonParser.nextToken();
                while(jsonParser.nextToken()!=JsonToken.END_ARRAY) {
                    data[i] = jsonParser.getIntValue();
                    i++;
                }
            }else{
                throw new IllegalArgumentException("Wrong format of json");
            }
        }

        String[] full=fullname.split(" ");
        surname=full[0];
        name=full[1];
        secondname=full[2];

        return new Person(surname,name,secondname,data);
    }
}
