import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

@JsonSerialize(using = PersonSerializer.class)
@JsonDeserialize(using = PersonDeserializer.class)

public class Person implements Serializable {
    private String surname;
    private String name;
    private String secondname;
    private int[] data;

    /*public Person(){

    }*/

    @JsonCreator
    public Person(@JsonProperty("surname") String surname, @JsonProperty("name") String name,
                  @JsonProperty("secondname") String secondname, @JsonProperty("data") int[] data) {
        this.surname = surname;
        this.name = name;
        this.secondname = secondname;
        this.data = new int[data.length];
        for(int i=0;i<data.length;i++){
            this.data[i]=data[i];
        }
    }

    public Person(Person person){
        this.surname=person.getSurname();
        this.name=person.getName();
        this.secondname=person.getSecondname();
        this.data=new int[person.getData().length];
        for(int i=0;i<this.data.length;i++){
            this.data[i]=person.getData()[i];
        }
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public String getSecondname() {
        return secondname;
    }

    public String getFullName(){
        return getSurname()+" "+getName()+" "+getSecondname();
    }

    public int[] getData() {
        return data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(surname, person.surname) && Objects.equals(name, person.name) && Objects.equals(secondname, person.secondname) && Arrays.equals(data, person.data);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(surname, name, secondname);
        result = 31 * result + Arrays.hashCode(data);
        return result;
    }

    @Override
    public String toString() {
        return surname + " " + name + " " + secondname + " " + Arrays.toString(data);
    }
}
