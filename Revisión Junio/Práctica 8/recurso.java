public class recurso {
    private static long r = 0;
    public void inc(){
        r++;
    }
    public long observer(){
        return r;
    }
}
