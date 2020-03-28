import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws Exception {

//        br = new BufferedReader(new FileReader("input.txt"));

        String input = br.readLine();
        StringBuilder output = new StringBuilder();

        Stack<Character> op = new Stack<>();

        for (char ch : input.toCharArray()) {
            if (ch >= 'A' && ch <= 'Z') {
                output.append(ch);
            } else {
                switch (ch) {
                    case '(':
                        op.push(ch);
                        break;
                    case ')':
                        while(!op.isEmpty() && op.peek() != '(') {
                            output.append(op.pop());
                        }
                        op.pop();
                        break;
                    default:
                        int p = priority(ch);

                        while(!op.isEmpty() && priority(op.peek()) >= p) {
                            output.append(op.pop());
                        }
                        op.push(ch);

                        break;
                }
            }
        }

        while(!op.isEmpty()) {
            output.append(op.pop());
        }

        System.out.println(output.toString());

//        bw.flush();
//        bw.close();
        br.close();
    }

    static int priority(char ch) {
        switch (ch) {
            case '+':
            case '-': return 1;
            case '*':
            case '/': return 2;
        }
        return 0;
    }
}