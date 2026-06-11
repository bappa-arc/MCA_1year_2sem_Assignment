public class SpecialStack extends Stack{
    
    public SpecialStack(int size){
        super(size);
    }
    
    public boolean push(int val){
        if (isFull()){
            System.out.println("Stack Overflow!");
            return false;
        }
        if (isEmpty() || val <= peek()){
            arr[++top] = val;
            return true;
        }else{
            return false;
        }
    }
}
