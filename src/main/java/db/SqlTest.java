package db;

import db.field.Field;
import db.parser.TinyDBOutput;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;

public class SqlTest {
    private String path, name;

    public SqlTest(String path, String name) {
        this.path = path;
        this.name = name;
    }

    public boolean run() {
        File sqlFile = Paths.get(path, name+".sql").toFile();
        File outFile = Paths.get(path, name+".out").toFile();
        File ansFile = Paths.get(path, name+".ans").toFile();
        try{
            Server server = new Server(path);
            server.process(sqlFile, outFile);
            boolean result = compare(ansFile, outFile);
            server.close();
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Compare whether two files are the same
     * @param file1 expected answer file
     * @param file2 actual output file
     * @return
     */
    public boolean compare(File file1, File file2) throws IOException{
        Iterator<String> iterator1 = (new BufferedReader(new FileReader(file1))).lines().filter(
                (String s) -> s.startsWith("|")
                ).iterator();
        Iterator<String> iterator2 = (new BufferedReader(new FileReader(file2))).lines().filter(
                (String s) -> s.startsWith("|")
        ).iterator();
        String line1, line2;
        int i=0;

        String fmt = "line %d\n expected : \"%s\"\n actual : \"%s\"";
        while (iterator1.hasNext()) {
            line1 = iterator1.next();
            if (iterator2.hasNext()) {
                line2 = iterator2.next();
                if (!line1.replace(" ", "").equals(line2.replace(" ", ""))) {
                    System.out.println(String.format(fmt, i, line1, line2));
                    return false;
                }
            } else{
                System.out.println(String.format(fmt, i, line1, ""));
                return false;
            }
            i++;
        }
        if (iterator2.hasNext()) {
            line2 = iterator2.next();
            System.out.println(String.format(fmt, i, "", line2));
            return false;
        } else {
            return true;
        }
    }

    public static void main(String[] args){
        File system_test_dir = new File("system_test_data");
        try {
            for(File file : system_test_dir.listFiles()) {
                String test_name = file.toPath().toString();
                SqlTest sqlTest = new SqlTest(test_name, "test");
                if (sqlTest.run()) {
                    System.out.println("pass " + test_name);
                } else {
                    System.out.println("fail " + test_name);
                }
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }
}
