package db;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class SqlTest {
    private String path, name;

    private SqlTest(String path, String name) {
        this.path = path;
        this.name = name;
    }

    private void run_() {
        File sqlFile = Paths.get(path, name+".sql").toFile();
        File outFile = Paths.get(path, name+".out").toFile();
        Server server = new Server(path);
        server.process(sqlFile, outFile);
        server.close();
    }

    private boolean run() {
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
     * get a String ArrayList which represents the head and the body of a Table from an String iterator
     * @param iterator
     * @return
     */
    private ArrayList<String> getTable(Iterator<String> iterator) {
        ArrayList<String> tables = new ArrayList<>();
        while (iterator.hasNext() && !iterator.next().startsWith("+")) {
        }
        if (!iterator.hasNext()) {
            return tables;
        } else {
            while (iterator.hasNext()) {
                String line = iterator.next();
                if (line.startsWith("|")) {
                    tables.add(line.replace(" ", ""));
                } else if (!line.startsWith("+")) {
                    break;
                }
            }
            return tables;
        }
    }
    private String formatTable(ArrayList<String> table){
        Iterator<String> it = table.iterator();
        if (! it.hasNext())
            return "[]";

        StringBuilder sb = new StringBuilder();
        sb.append('[');
        sb.append(System.lineSeparator());
        for (;;) {
            String  e = it.next();
            sb.append(e);
            if (! it.hasNext())
                return sb.append(System.lineSeparator()).append(']').toString();
            sb.append(System.lineSeparator());
        }
    }

    /**
     * Compare whether two files are the same
     * @param file1 expected answer file
     * @param file2 actual output file
     * @return
     */
    public boolean compare(File file1, File file2) throws IOException{
        Iterator<String> iterator1 = (new BufferedReader(new FileReader(file1))).lines().iterator();
        Iterator<String> iterator2 = (new BufferedReader(new FileReader(file2))).lines().iterator();
        int i=0;
        String fmt = "table %d\n expected : \"%s\"\n actual : \"%s\"";
        while(true){
            i++;
            ArrayList<String> table1 = getTable(iterator1);
            ArrayList<String> table2 = getTable(iterator2);
            if (table1.size() == 0 && table2.size() == 0) {
                break;
            }
            Collections.sort(table1);
            Collections.sort(table2);
            if(!table1.equals(table2)){
                System.out.println(String.format(fmt, i, formatTable(table1), formatTable(table2)));
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args){
        if (args.length == 0) {
            File root_dir = new File("system_test");
            for(File dir: root_dir.listFiles()) {
                try {
                    for(File file : dir.listFiles()) {
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
        else {
            (new SqlTest((new File(args[0])).toPath().toString(), "test")).run_();
        }
    }
}
