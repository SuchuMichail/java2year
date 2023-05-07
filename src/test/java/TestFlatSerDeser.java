import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestFlatSerDeser {
    @Test
    public void test() throws JsonProcessingException {
        List<Person> l1=new ArrayList<>();
        Person p1=new Person("s1","n1","d1",new int[] {2,3,97});
        Person p2=new Person("s2","n2","d2",new int[] {7,11,98});
        l1.add(p1);
        l1.add(p2);

        Flat flat1=new Flat(1,50,l1);

        ObjectMapper mapper=new ObjectMapper();
        String json=mapper.writeValueAsString(flat1);
        //System.out.println(json);

        Flat test=mapper.readValue(json, Flat.class);

        Assert.assertEquals(flat1, test);
    }
}
