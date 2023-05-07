import org.junit.Assert;
import org.junit.Test;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class TestProblem {
    @Test
    public void testByteAndSimbol() throws IOException {
        int[] arrInt={1,3,70000,-8};
        //byte[] arr={1,2,3};

        FileOutputStream out=new FileOutputStream("integer.bin");
        FileInputStream in=new FileInputStream("integer.bin");


        try {
            Problem.streamByteOutput(arrInt, out);
        } catch (IllegalArgumentException e){
            Assert.fail("IOException");
        }
        int[] testbyte = Problem.streamByteInput(arrInt,in);
        Assert.assertArrayEquals(arrInt,testbyte);

        FileOutputStream outsim=new FileOutputStream("integer.txt");
        FileInputStream insim=new FileInputStream("integer.txt");

        try {
            Problem.streamSimbolOutput(arrInt, outsim);
        } catch (IllegalArgumentException e){
            Assert.fail("IOException");
        }
        int[] testsimbol = Problem.streamSimbolInput(arrInt,insim);

        Assert.assertArrayEquals(arrInt,testsimbol);
    }

    @Test
    public void testRandomAccessFile() throws IOException {
        RandomAccessFile randomAccessFile=new RandomAccessFile("random.txt","rw");
        randomAccessFile.writeInt(2);
        randomAccessFile.writeInt(15);
        randomAccessFile.writeInt(0);
        randomAccessFile.writeInt(7);
        randomAccessFile.writeInt(-8);
        randomAccessFile.close();

        int[] real={15,0,7,-8};
        int[] arr=new int[4];
        Assert.assertArrayEquals(real,Problem.readArrayFromIndex(arr,"random.txt",1));
    }

    @Test
    public void testFile(){
        List<String> list = Problem.listFilesWithExtensionInCatalog(".bin", Path.of("").toAbsolutePath().toString());
        List<String> real=new ArrayList<>();
        real.add("integer.bin"); real.add("integerfile.bin");

        Assert.assertEquals(real,list);
    }

    @Test
    public void testRegex(){
        List<String> list = Problem.listFilesAndCatalogsWithRegex(Path.of("").toAbsolutePath().toString(),"[0-9]*.doc");
        List<String> real=new ArrayList<>();
        real.add("3.doc"); real.add("2.doc"); real.add("1.doc");

        Assert.assertEquals(real,list);
    }
}
