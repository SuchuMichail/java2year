import javax.xml.crypto.Data;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Problem {
    //private static int deep=0;
    public static void main(String[] args) throws IOException {
        // streamInt();
    }

    public static void streamByteOutput(int[] arr, OutputStream out) {
        try (DataOutputStream outputStream = new DataOutputStream(out)) {
            for (int i = 0; i < arr.length; i++) {
                outputStream.writeInt(arr[i]);
            }
        } catch (IOException e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    public static int[] streamByteInput(int[] arr, InputStream in) {
        try (DataInputStream inputStream = new DataInputStream(in)) {
            for (int i = 0; i < arr.length; i++) {
                arr[i] = inputStream.readInt();
            }
            return arr;
        } catch (IOException e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    public static void streamSimbolOutput(int[] arr, OutputStream outputStream) {
        try (Writer writer = new OutputStreamWriter(outputStream)) {
            for (int i = 0; i < arr.length; i++) {
                String str = arr[i] + " ";
                writer.write(str);
            }
        } catch (IOException e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    public static int[] streamSimbolInput(int[] arr, InputStream inputStream) {
        try (Reader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8)) {
            String str = String.valueOf(reader.read());
            String[] s = str.split(" ");
            for (int i = 0; i < arr.length; i++) {
                arr[i] = Integer.parseInt(s[i]);
            }
            return arr;
        } catch (IOException e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    public static int[] readArrayFromIndex(int[] arr, String name, int index) throws IOException {
        try (RandomAccessFile ob = new RandomAccessFile(name, "r")) {
            if (index >= ob.length()) {
                throw new IllegalArgumentException("illegal index");
            }

            //int[] arr=new int[(int) (ob.length() - index)];
            int i = 0;
            ob.seek(index * 4L);
            while (i != arr.length) {
                arr[i] = ob.readInt();
                i++;
            }
            //ob.close();
            return arr;
        } catch (IOException e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    public static List<String> listFilesWithExtensionInCatalog(String extension, String catalog) {
        List<String> list = new ArrayList<>();
        File file = new File(catalog);
        //System.out.println(Path.of("").toAbsolutePath().toString());
        if (!file.exists()) {
            throw new IllegalArgumentException("no exist");
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException("no catalog");
        }
        List<String> names = List.of(file.list());
        for (String iter : names) {
            // System.out.println("Tut");
            if (iter.indexOf(extension, iter.length() - extension.length()) != -1) {
                list.add(iter);
            }
        }
        return list;
    }

    public static List<String> rec(File file, String regex) {
        List<String> list = new ArrayList<>();
        File[] files = file.listFiles();
        if (files != null) {
            for (File iter : files) {
                if (iter.getName().matches(regex)) {
                    list.add(iter.getName());
                }
                if (iter.isDirectory()) {
                    List<String> newlist = rec(iter, regex);
                    list.addAll(newlist);
                }
            }
        }
        return list;
    }

    public static List<String> listFilesAndCatalogsWithRegex(String catalog, String regex) {
        File file = new File(catalog);
        if (!file.exists()) {
            throw new IllegalArgumentException("no exist" + catalog);
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException("no catalog");
        }

        List<String> list = rec(file, regex);

        return list;
    }
}
