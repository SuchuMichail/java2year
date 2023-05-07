import org.junit.Assert;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestSerializator {
    @Test
    public void test() throws FileNotFoundException {
        List<Person> l1 = new ArrayList<>();
        l1.add(new Person("s1", "n1", "d1", new int[]{2, 3, 97}));

        Flat flat1 = new Flat(1, 50, l1);

        List<Flat> list_flat = new ArrayList<>();
        list_flat.add(flat1);

        House house = new House("cadastr", "adress", new Person("Surname", "Name", "Dadname", new int[]{2, 4, 20}), list_flat);

        House real = new House(house);

        FileOutputStream outputStream = new FileOutputStream("haos.txt");
        FileInputStream inputStream = new FileInputStream("haos.txt");

        try {
            Serializator.Serialize(house, outputStream);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        try {
            Serializator.Deserialize(house, inputStream);
            Assert.assertEquals(real, house);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testHouse() throws IOException {
        List<Person> l1 = new ArrayList<>();
        l1.add(new Person("s1", "n1", "d1", new int[]{2, 3, 97}));
        l1.add(new Person("s2", "n2", "d2", new int[]{7, 11, 98}));

        List<Person> l2 = new ArrayList<>();
        l2.add(new Person("s3", "n3", "d3", new int[]{1, 5, 97}));

        Flat flat1 = new Flat(1, 50, l1);
        Flat flat2 = new Flat(2, 60, l2);

        List<Flat> list_flat = new ArrayList<>();
        list_flat.add(flat1);
        list_flat.add(flat2);

        House house = new House("12", "REDLine", new Person("Sir", "Van", "Vanovich", new int[]{2, 4, 20}), list_flat);

        String str = Serializator.HouseSerializator(house);
        Assert.assertEquals(house, Serializator.HouseDeserializator(str));
    }

    @Test
    public void testCompare() throws IOException {
        List<Person> l1 = new ArrayList<>();
        l1.add(new Person("s1", "n1", "d1", new int[]{2, 3, 97}));
        l1.add(new Person("s2", "n2", "d2", new int[]{7, 11, 98}));

        List<Person> l2 = new ArrayList<>();
        l2.add(new Person("s3", "n3", "d3", new int[]{1, 5, 97}));

        Flat flat1 = new Flat(1, 50, l1);
        Flat flat2 = new Flat(2, 60, l2);

        List<Flat> list_flat = new ArrayList<>();
        list_flat.add(flat1);
        list_flat.add(flat2);


        House house1 = new House("12", "REDLine", new Person("Sir", "Van", "Vanovich", new int[]{2, 4, 20}), list_flat);

        String str1 = Serializator.HouseSerializator(house1);

        Assert.assertEquals(true, Serializator.CompareJSonStrings(str1, str1));


        Assert.assertEquals(true, Serializator.CompareJSonStrings("""
                
                {
                    "a": 1,
                    "b": "aaa"
                }
                """, """
                {
                    "b": "aaa",
                    "a": 1
                }
                """));


        List<Person> l0 = new ArrayList<>();
        l0.add(new Person("s1", "n1", "d1", new int[]{2, 3, 97}));

        Flat flat0 = new Flat(1, 50, l1);

        List<Flat> list_flat0 = new ArrayList<>();
        list_flat0.add(flat0);

        House house0 = new House("cadastr", "adress", new Person("Surname", "Name", "Dadname", new int[]{2, 4, 20}), list_flat0);
        String str3 = Serializator.HouseSerializator(house0);

        Assert.assertEquals(false, Serializator.CompareJSonStrings(str1, str3));
    }
}
