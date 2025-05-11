public class IterationCounter {
    private int iterations;
    public IterationCounter(){
        iterations = 0;
    }
    public void add(){
        iterations++;
    }

    public void add(int i){
        iterations+=i;
    }
}
