import java.util.Arrays;
import java.util.LinkedList;

public class lruPSwitch {

    private LinkedList<Integer> calls;
    private LinkedList<Frame> frames;
    private int errors;

    public lruPSwitch(LinkedList<Integer> calls) {
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
        frames.get(0).lastUsedAt = 0;
        frames.get(0).locked = true;
        out.append(frames.get(0).getId());
        errors++;
        if (calls.size() >= 2) {
            for (int i = 1; i < calls.size(); i++) {
                boolean canAssign = false;
                for(int j = 0; j < frames.size(); j++){
                    if(frames.get(j).getCurrentPage() == 0){
                        canAssign = true;
                        frames.get(j).setCurrentPage(calls.get(i));
                        out.append(frames.get(j).getId());
                        frames.get(j).lastUsedAt = i;
                        frames.get(j).locked = true;
                        errors++;
                        break;
                    }
                    if(frames.get(j).getCurrentPage() == calls.get(i)){
                        out.append('-');
                        frames.get(j).locked = false;
                        frames.get(j).lastUsedAt = i;
                        canAssign = true;
                        break;
                    }
                }
                if(!canAssign){
                    boolean ok = false;
                    int nextInd = 0;
                    if((!frames.get(0).locked) || frames.get(0).lastUsedAt+5 < i) {
                        ok = true;
                    }
                    for(int j = 0; j < frames.size(); j++){
                        if(frames.get(j).lastUsedAt < frames.get(nextInd).lastUsedAt && (frames.get(j).lastUsedAt+5 < i || !frames.get(j).locked)){
                            ok = true;
                            nextInd = j;
                            break;
                        }
                    }
                    if(ok){
                        frames.get(nextInd).setCurrentPage(calls.get(i));
                        frames.get(nextInd).lastUsedAt = i;
                        frames.get(nextInd).locked = true;
                        out.append(frames.get(nextInd).getId());
                        errors++;
                    }else{
                        for(int j = 0; j < frames.size(); j++){
                            if((!frames.get(j).locked) && (frames.get(nextInd).lastUsedAt > frames.get(j).lastUsedAt || frames.get(nextInd).locked) ){
                                ok = true;
                                nextInd = j;
                            }
                        }
                        if(!ok){
                            out.append('*');
                            errors++;
                        }else{
                            frames.get(nextInd).setCurrentPage(calls.get(i));
                            frames.get(nextInd).lastUsedAt = i;
                            out.append(frames.get(nextInd).getId());
                            errors++;
                        }

                    }
                }
            }
        }
    }
    String output = out.toString();
        System.out.println(output);
        System.out.println(errors);
}
}
