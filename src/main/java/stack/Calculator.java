package stack;

public class Calculator {

    public static void main(String[] args) {

        String expression = "7*2*2-5+1-5+3-4";

        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);

        int index = 0;
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int res = 0;
        char ch = ' '; //cada vez scanear char salva no ch
        String keepNum = ""; // uso para conectar variaz numbers
        //scanear expression
        while(true) {

            ch = expression.substring(index, index+1).charAt(0);

            if(operStack.isOper(ch)) {

                if(!operStack.isEmpty()) {

                    if(operStack.priority(ch) <= operStack.priority(operStack.peek())) {
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = numStack.cal(num1, num2, oper);

                        numStack.push(res);

                        operStack.push(ch);
                    } else {
                        //se o opera tem preferencia , entao push direto
                        operStack.push(ch);
                    }
                }else {
                    // se for empty vai para operaStack
                    operStack.push(ch); // 1 + 3
                }
            } else { // se for number push direto


                keepNum += ch;

                //se index ch for ultimo number push direto.
                if (index == expression.length() - 1) {
                    //numStack.push(ch - 48); //? "1+3" '1' => 1
                    numStack.push(Integer.parseInt(keepNum));
                }else{

                    //verifica se for opera push, se for number continua scaneando
                    //alert! veja ultimo nao index++
                    if (operStack.isOper(expression.substring(index+1,index+2).charAt(0))) {
                        //if the last is the opera, then into keepNum = "1" or "123"
                        numStack.push(Integer.parseInt(keepNum));
                        //have to clean the keepNum
                        keepNum = "";

                    }
                }
            }
            //index + 1 for judge if the expression is last.
            index++;
            if (index >= expression.length()) {
                break;
            }
        }

        //number stack e operastack pop the elemnts to calculo
        while(true) {
            //se stack for empty, final da conta vai ser apenas um number na stack
            if(operStack.isEmpty()) {
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res = numStack.cal(num1, num2, oper);
            numStack.push(res);//��ջ
        }
        //����ջ���������pop�������ǽ��
        int res2 = numStack.pop();
        System.out.printf("expression %s = %d", expression, res2);
    }
}

class ArrayStack2 {
    private int maxSize;
    private int[] stack;
    private int top = -1;

    public ArrayStack2(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    public int peek() {
        return stack[top];
    }

    public boolean isFull() {
        return top == maxSize - 1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public void push(int value) {
        if(isFull()) {
            System.out.println("stack full");
            return;
        }
        top++;
        stack[top] = value;
    }

    public int pop() {

        if(isEmpty()) {
            throw new RuntimeException("It is empty~");
        }
        int value = stack[top];
        top--;
        return value;
    }

    public void list() {
        if(isEmpty()) {
            System.out.println("It is empty~~~");
            return;
        }

        for(int i = top; i >= 0 ; i--) {
            System.out.printf("stack[%d]=%d\n", i, stack[i]);
        }
    }

    public int priority(int oper) {
        if(oper == '*' || oper == '/'){
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        } else {
            return -1;
        }
    }

    public boolean isOper(char val) {
        return val == '+' || val == '-' || val == '*' || val == '/';
    }

    public int cal(int num1, int num2, int oper) {
        int res = 0;
        switch (oper) {
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num1;
                break;
            default:
                break;
        }
        return res;
    }
}
