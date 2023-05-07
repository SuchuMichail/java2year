import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

public class PersonSerializer extends StdSerializer<Person> {
    public PersonSerializer() {
        super(Person.class);
    }


    @Override
    public void serialize(Person person, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {

        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("fullname",person.getFullName());
        jsonGenerator.writeFieldName("data");
        jsonGenerator.writeArray(person.getData(),0,person.getData().length);
        jsonGenerator.writeEndObject();

    }
}
