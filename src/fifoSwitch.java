import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class fifoSwitch {

    private LinkedList<Integer> calls;
    private LinkedList<Frame> frames;
    private int errors;


    public fifoSwitch(LinkedList<Integer> calls) {
        Frame[] frameArray = {
                new Frame('A'),
                new Frame('B'),
                new Frame('C'),
                new Frame('D')};
        frames = new LinkedList<>(Arrays.asList(frameArray));

        this.calls = calls;
        this.errors = 0;
    }

        public void run(){
        StringBuilder out = new StringBuilder();
        int lastFrame = 1;
        if(!calls.isEmpty()) {
            frames.get(0).setCurrentPage(calls.getFirst());
            out.append(frames.get(0).getId());
            errors++;
            if (calls.size() >= 2) {
                for (int i = 1; i < calls.size(); i++) {
                    boolean used = false;
                    for(int j = 0; j < frames.size(); j++){
                        if(frames.get(j).getCurrentPage() == calls.get(i)){
                            out.append('-');
                            used = true;
                        }
                    }
                    if(!used){
                        if(lastFrame < 4) {
                            lastFrame++;
                        }else{
                            lastFrame = 1;
                        }
                        frames.get(lastFrame-1).setCurrentPage(calls.get(i));
                        out.append(frames.get(lastFrame-1).getId());
                        errors++;
                    }
                }
            }
        }
        String output = out.toString();
        System.out.println(output);
        System.out.println(errors);


    }
}
