import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestHouseSerDeser {
    @Test
    public void test() throws JsonProcessingException {
        List<Person> l1=new ArrayList<>();
        l1.add(new Person("s1","n1","d1",new int[] {2,3,97}));
        l1.add(new Person("s2","n2","d2",new int[] {7,11,98}));

        List<Person> l2=new ArrayList<>();
        l2.add(new Person("s3","n3","d3",new int[] {1,5,97}));

        Flat flat1=new Flat(1,50,l1);
        Flat flat2=new Flat(2,60,l2);

        List<Flat> list_flat=new ArrayList<>();
        list_flat.add(flat1);
        list_flat.add(flat2);


        House house1=new House("12","REDLine",
                new Person("Sir","Van","Vanovich",new int[] {2,4,20}),list_flat);

        ObjectMapper mapper=new ObjectMapper();
        String json=mapper.writeValueAsString(house1);
        System.out.println(json);

        House test=mapper.readValue(json, House.class);

        Assert.assertEquals(house1, test);
    }
}
