import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.util.List;

public class FlatSerializer extends StdSerializer<Flat> {
    public FlatSerializer() {
        super(Flat.class);
    }

    @Override
    public void serialize(Flat flat, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {

        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("flatnumber",flat.getFlatnumber());
        jsonGenerator.writeNumberField("square",flat.getSquare());
        jsonGenerator.writeFieldName("list_person");
        jsonGenerator.writeStartArray();
        for(Person iter: flat.getList_person()){
            ObjectMapper mapper=new ObjectMapper();
            String json=mapper.writeValueAsString(iter);
            jsonGenerator.writeString(json);
        }
        jsonGenerator.writeEndArray();
        jsonGenerator.writeEndObject();

    }
}
