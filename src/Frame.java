public class Frame {

    char id;
    int currentPage;
    boolean locked;

    public int waitTime;
    public int lockTime;

    public int lastUsedAt;
    public Frame(char id){
        this.id = id;
        this.currentPage = 0;
        this.locked = false;

        this.waitTime= 0;
        this.lockTime = 0;
        this.lastUsedAt = -1;
    }

    public void setCurrentPage(int a){
        this.currentPage = a;
    }

    public int getCurrentPage(){
        return this.currentPage;
    }

    public char getId(){
        return this.id;
    }

    public boolean isLocked(){
       return this.locked;
    }

    public void setLocked(boolean b){
        this.locked = b;
    }


}
