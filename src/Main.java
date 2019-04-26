import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Main {

    public static void main(String[] args){

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = null;
        String[] calls = null;
        try {

                input = br.readLine();
        } catch (
    IOException e) {
            e.printStackTrace();
        }
        try{
            calls = input.split(",");
        }catch (NullPointerException e){
            e.printStackTrace();
        }
        LinkedList<Integer> pages = new LinkedList<>();
        for(String str: calls){
            pages.add(Integer.parseInt(str));
        }

        fifoSwitch fifo = new fifoSwitch(pages);
        fifo.run();

        lruSwitch lru = new lruSwitch(pages);
        lru.run();

    }
}
