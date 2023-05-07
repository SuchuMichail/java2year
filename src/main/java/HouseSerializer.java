import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.util.List;

public class HouseSerializer extends StdSerializer<House> {
    public HouseSerializer() {
        super(House.class);
    }

    @Override
    public void serialize(House house, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("cadastrnumber",house.getCadastrnumber());
        jsonGenerator.writeStringField("address",house.getAddress());
        jsonGenerator.writeFieldName("mainperson");

        ObjectMapper mapper=new ObjectMapper();
        String str=mapper.writeValueAsString(house.getMainperson());
        jsonGenerator.writeString(str);

        jsonGenerator.writeFieldName("list_flat");
        jsonGenerator.writeStartArray();
        for(Flat iter: house.getList_flat()){
            String json=mapper.writeValueAsString(iter);
            jsonGenerator.writeString(json);
        }
        jsonGenerator.writeEndArray();
        jsonGenerator.writeEndObject();
    }
}
