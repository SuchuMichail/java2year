import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;

public class TestPersonSerDeser {
    @Test
    public void test() throws JsonProcessingException {
        Person person=new Person("Iakovlev","Evgen","Nikitovich",new int[]{2,3,97});

        ObjectMapper mapper=new ObjectMapper();
        String json=mapper.writeValueAsString(person);

        Person test=mapper.readValue(json,Person.class);

        Assert.assertEquals(person,test);
    }
}
