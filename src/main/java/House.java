import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@JsonSerialize(using = HouseSerializer.class)
@JsonDeserialize(using = HouseDeserializer.class)
public class House implements Serializable {

    private static final long serialVersionUID = 1L;

    private String cadastrnumber;
    private String address;
    private Person mainperson;
    private List<Flat> list_flat;

    public House(){
        //this.mainperson=new Person();
        this.list_flat=new ArrayList<>();
    }

    @JsonCreator
    public House(@JsonProperty("cadastrnumber") String cadastrnumber, @JsonProperty("address") String address,
                 @JsonProperty("mainperson") Person mainperson, @JsonProperty("list_flat") List<Flat> list_flat) {
        this.cadastrnumber = cadastrnumber;
        this.address = address;
        this.mainperson = new Person(mainperson.getSurname(), mainperson.getName(), mainperson.getSecondname(), mainperson.getData());
        this.list_flat = new ArrayList<>(list_flat.size());
        this.list_flat.addAll(list_flat);
    }

    public House(House house){
        this.cadastrnumber= house.getCadastrnumber();
        this.address=house.getAddress();
        this.mainperson=new Person(house.getMainperson());

        this.list_flat=new ArrayList<>(house.getList_flat().size());
        this.list_flat.addAll(house.getList_flat());
    }

    public void setCadastrnumber(String cadastrnumber) {
        this.cadastrnumber = cadastrnumber;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setMainperson(Person mainperson) {
        this.mainperson = new Person(mainperson.getSurname(), mainperson.getName(), mainperson.getSecondname(), mainperson.getData());
    }

    public void setList_flat(List<Flat> list_flat) {
        this.list_flat = new ArrayList<>(list_flat.size());
        this.list_flat.addAll(list_flat);
    }

    public String getCadastrnumber() {
        return cadastrnumber;
    }

    public String getAddress() {
        return address;
    }

    public Person getMainperson() {
        return mainperson;
    }

    public List<Flat> getList_flat() {
        return list_flat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        House house = (House) o;
        return Objects.equals(cadastrnumber, house.cadastrnumber) && Objects.equals(address, house.address) && Objects.equals(mainperson, house.mainperson) && Objects.equals(list_flat, house.list_flat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cadastrnumber, address, mainperson, list_flat);
    }

    @Override
    public String toString() {
        StringBuilder builder=new StringBuilder();
        for(int i=0;i<list_flat.size()-1;i++){
            builder.append(list_flat.get(i).toString()).append("\n");
        }
        builder.append(list_flat.get(list_flat.size()-1).toString());
        return "House data\n" +
                "Cadastr number:;" + cadastrnumber + '\n' +
                "Address:;" + address + '\n' +
                "House chief:;" + mainperson + '\n' +
                "Flats data\nID;Square;People\n" + builder;
    }
}
