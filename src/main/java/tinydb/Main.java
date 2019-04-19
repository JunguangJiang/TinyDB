package tinydb;

public class Main {
    /***
     * An example code for class
     * @param args
     */
    public static void main(String[] args){
        Parser parser = new Parser();
        try {
            parser.start(args);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
