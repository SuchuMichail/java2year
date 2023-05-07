import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestCSV {
    @Test
    public void testCSV(){
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

        House house=new House("12","REDLine",
                new Person("Sir","Van","Vanovich",new int[] {2,4,20}),list_flat);

        CSV.convertToCSV(house);
    }
}
