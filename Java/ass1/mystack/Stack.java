package mystack;
public class Stack {
    protected int [] arr;
    protected int top;
    protected int size;

    public Stack(int size){
        this.size = size;
        arr = new int[size];
        top = -1;
    }

    public boolean push(int value){
        if(top==size-1){
            return false;
        }else{
            top++;
            arr[top] = value;
            return true;
        }
    }

    public int size(){
        return top+1;
    }

    public int pop(){
        if(top==-1){
            return -1;
        }else{
            int val = arr[top];
            top--;
            return val;
        }
    }

    public int peek(){
        if(top==-1){
            return -1;
        }else{
            return arr[top];
        }
    }

    public void display(){
        if(top==-1){
            System.out.println("Stack is empty!");
        }else{
            System.out.println("Stack elements: ");
            for (int i=top; i>=0;i--){
                System.out.println(arr[i]);
            }
        }
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == size - 1;
    }

    public int display(int depth){
        if (depth < 0 || depth > top) {
            return -1;
        } else {
            return arr[top - depth];
        }
    }
}
