import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@JsonSerialize(using = FlatSerializer.class)
@JsonDeserialize(using = FlatDeserializer.class)
public class Flat implements Serializable {
    private int flatnumber;
    private double square;
    private List<Person> list_person;

    /*public Flat(){
        this.list_person=new ArrayList<>();
    }*/

    @JsonCreator
    public Flat(@JsonProperty("flatnumber") int flatnumber,
                @JsonProperty("square") double square,
                @JsonProperty("list_person") List<Person> list_person) {
        this.flatnumber = flatnumber;
        this.square = square;
        this.list_person = new ArrayList<>(list_person.size());
        this.list_person.addAll(list_person);
    }

    public int getFlatnumber() {
        return flatnumber;
    }

    public double getSquare() {
        return square;
    }

    public List<Person> getList_person() {
        return list_person;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flat flat = (Flat) o;
        return flatnumber == flat.flatnumber && Double.compare(flat.square, square) == 0 && Objects.equals(list_person, flat.list_person);
    }

    @Override
    public int hashCode() {
        return Objects.hash(flatnumber, square, list_person);
    }

    @Override
    public String toString() {
        StringBuilder builder=new StringBuilder();
        for(int i=0;i<list_person.size()-1;i++){
            builder.append(list_person.get(i).toString()).append(",");
        }
        builder.append(list_person.get(list_person.size()-1).toString());
        return flatnumber + "; " + square + "; " + builder;
    }
}
