import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class CSV {
    public static void convertToCSV(House house){
        String filename="house_"+house.getCadastrnumber()+".csv";
        try(PrintWriter writer=new PrintWriter(new File(filename))){
            writer.print(house);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

}
