import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * 通过栈实现的简单计算器，可实现带括号的加减乘除的运算
 * @Class:       Calculator
 * @author:      Oh_MyBug
 * @Date:        2020/3/17 21:59
 */
public class Calculator {
    private JButton backspace;
    private JButton time;
    private JButton four;
    private JButton left;
    private JButton right;
    private JButton eight;
    private JButton nine;
    private JButton five;
    private JButton six;
    private JButton minus;
    private JButton one;
    private JButton two;
    private JButton three;
    private JButton plus;
    private JButton clear;
    private JButton seven;
    private JTextField textField;
    private JButton dot;
    private JButton zero;
    private JButton devide;
    private JButton equal;
    private JPanel screen;
    private JPanel key;
    private JPanel panel;
    private CalculatorStack numericStack;    // 数字栈
    private CalculatorStack operationStack;  // 操作符栈

    /**
     * Calculator的构造器，初始化数字栈numericStack和操作符栈operationStack以及增加Button鼠标监听事件
     * @Function Calculator
     * @author   Oh_MyBug
     * @Date     2020/3/17 22:00
     * @param
     * @return
     */
    public Calculator() {
        numericStack = new CalculatorStack();
        operationStack = new CalculatorStack();

        backspace.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                backspaceAction();
            }
        });
        clear.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                clearAction();
            }
        });
        equal.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String text = textField.getText();
                String result = result(text);
                textField.setText(text + " = " + result);
            }
        });
        plus.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                addOperation("+");
            }
        });
        minus.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                addOperation("-");
            }
        });
        time.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                addOperation("*");
            }
        });
        devide.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                addOperation("/");
            }
        });
        left.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                addOperation("(");
            }
        });
        right.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                addOperation(")");
            }
        });
        dot.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                addNumeric(".");
            }
        });
        zero.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                addNumeric("0");
            }
        });
        one.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                addNumeric("1");
            }
        });
        two.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                addNumeric("2");
            }
        });
        three.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                addNumeric("3");
            }
        });
        four.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                addNumeric("4");
            }
        });
        five.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                addNumeric("5");
            }
        });
        six.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                addNumeric("6");
            }
        });
        seven.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                addNumeric("7");
            }
        });
        eight.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                addNumeric("8");
            }
        });
        nine.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                addNumeric("9");
            }
        });
    }

    /**
     * 添加数字（0,...,9以及"."）时的规范<p>
     * 将显示屏的文本进行分割（regex = " "）后得到symbol字符串数组，下面将分类讨论:<p>
     * > 若symbol的最后一个字符串是数字（非"0"）：<p>
     * > > 添加的数字为"."的情况:<p>
     * > > > 若该数字不包含"."，则向显示屏添加"."<p>
     * > > > 若该数字已包含"."，则不再向显示屏添加"."<p>
     * > > 添加的数字为0,..,9的其中之一，直接向显示屏添加"."<p>
     * > 若symbol的最后一个字符串是数字"0"：<p>
     * > > 添加的数字为"."，则直接向显示屏添加"."<p>
     * > > 添加的数字为0,..,9的其中之一，先去除最后一个字符串"0"，再添加<p>
     * > 若symbol的最后一个字符串是操作符<p>
     * > > 添加的数字为"."的情况，则不向显示屏添加"."<p>
     * > > 添加的数字为0,..,9的其中之一的情况：先加空格，在添加数字<p>
     *
     * @Function addNumeric
     * @author   Oh_MyBug
     * @Date     2020/3/18 1:21
     * @param   str
     * @return
     */
    public void addNumeric(String str){
        String text = textField.getText();
        String[] symbol = text.split(" ");
        int end = symbol.length - 1;
        if (MathUtil.isNumeric(symbol[end])) {
            if (!"0".equals(symbol[end])) {
                if (".".equals(str)) {
                    if (!symbol[end].contains(".")) {
                        textField.setText(text + ".");
                        return;
                    } else
                        return;
                }else {
                    textField.setText(text + str);
                    return;
                }
            }else{
                if (".".equals(str)) {
                    textField.setText(text + ".");
                    return;
                }else {
                    textField.setText(text.substring(0, text.length() - 1) + str);
                    return;
                }
            }
        }
        if (MathUtil.isOper(symbol[end]) && !".".equals(str))
            textField.setText(text + " " + str);
    }
    /**
     * 添加操作符（"+"、"-"、"*"、"/"、"("、")"）时的规范<p>
     * 将显示屏的文本进行分割（regex = " "）后得到symbol字符串数组，下面将分类讨论:<p>
     * > 若symbol的最后一个字符串为数字：<p>
     * > > 若该操作符为"("：<p>
     * > > > 若数字非"0"，则先添加空格，再添加"*"，再添加空格，最后添加该操作符<p>
     * > > > 若数字为"0"，则先删除最后一个字符串，再添加该操作符<p>
     * > > 若该操作符非"("：<p>
     * > > > 若数字非"0"，则先添加空格，再添加该操作符<p>
     * > > > 若数字为"0"，则先添加空格，再添加该操作符<p>
     * > 若symbol的最后一个字符串为操作符：<p>
     * > > 若最后一个字符串为"("：<p>
     * > > > 若该操作符为"("或")"，则先添加空格，再添加该操作符<p>
     * > > > 若该操作符为其他，则先删除最后两个字符串(因为"("前面会有一个操作符)，再添加该操作符<p>
     * > > 若最后一个字符串为")"，则先添加空格，再添加该操作符<p>
     * > > 若最后一个字符串为其他：<p>
     * > > > 若该操作符为"("，则先添加空格，再添加该操作符<p>
     * > > > 若该操作符为其他，则先删除最后一个字符串，再添加该操作符<p>
     * @Function addOperation
     * @author   Oh_MyBug
     * @Date     2020/3/18 1:37
     * @param    str
     * @return
     */
    public void addOperation(String str){
        String text = textField.getText();
        String[] symbol = text.split(" ");
        int end = symbol.length - 1;
        if (MathUtil.isNumeric(symbol[end])){
            if (!"0".equals(symbol[end])){
                textField.setText("(".equals(str) ? (text + " * " + str) : (text + " " + str));
                return;
            }else{
                textField.setText("(".equals(str) ? (text.substring(0,text.length() - 1) + str) : (text + " " + str));
                return;
            }
        }
        if (MathUtil.isOper(symbol[end])) {
            if ("(".equals(symbol[end])) {
                if ("(".equals(str) || ")".equals(str)) {
                    textField.setText(text + " " + str);
                } else {
                    textField.setText(text.substring(0, text.length() - 3) + str);
                }
                return;
            } else if (")".equals(symbol[end])) {
                textField.setText(text + " " + str);
                return;
            } else {
                if ("(".equals(str)){
                    textField.setText(text + " " + str);
                    return;
                }else{
                    textField.setText(text.substring(0, text.length() - 1) + str);
                    return;
                }
            }
        }
    }

    /**
     * 后退操作
     * @Function TODO
     * @author   Oh_MyBug
     * @Date     2020/3/18 3:04
     * @param
     * @return
     */
    public void backspaceAction(){
        String text = textField.getText();
        if (text.length() == 1){
            textField.setText("0");
            return;
        }
        String[] symbol = text.split(" ");
        int end = symbol.length - 1;
        if (MathUtil.isNumeric(symbol[end]) && symbol[end].length() > 1) {
            text = text.substring(0, text.length() - 1);
        }else
            text = text.substring(0, text.length() - 2);
        textField.setText(text);
    }

    /**
     * 归零操作
     * @Function clearAction
     * @author   Oh_MyBug
     * @Date     2020/3/18 3:04
     * @param
     * @return
     */
    public void clearAction(){
        textField.setText("0");
    }

    /**
     *计算formula表达式的结果
     * @Function result
     * @author   Oh_MyBug
     * @Date     2020/3/17 21:57
     * @param    formula
     * @return  formula表达式的最终结果
     */
    public String result(String formula){
        // 表达式格式要求：操作符与数字符之间用空格隔开，方便处理
        String[] symbol = formula.split(" ");
        int len = symbol.length;
        for (int i = 0; i < len; i++) {
            while (true) {
                // 如果当前字符串既不是数字符也不是操作符，那么就是表达式格式错误
                if (!(MathUtil.isNumeric(symbol[i]) || MathUtil.isOper(symbol[i]) )){
                    throw new RuntimeException("表达式格式错误！");
                }
                // 如果当前字符串是数字符，直接压入数字栈
                if (MathUtil.isNumeric(symbol[i])) {
                    numericStack.push(symbol[i]);
                    break;
                }
                // 如果当前字符串是操作符，分类讨论
                if (MathUtil.isOper(symbol[i])) {
                    // 如果操作符栈为空 或者 该操作符是左括号，直接压入操作符栈
                    if (operationStack.isEmpty() || MathUtil.isLeftParenthesis(symbol[i])) {
                        operationStack.push(symbol[i]);
                        break;
                    }
                    // 如果该操作符为右括号，将括号内的表达式计算得出结果，并将左括号弹出
                    if (MathUtil.isRightParenthesis(symbol[i])){
                        while (!MathUtil.isLeftParenthesis(operationStack.peek())){
                            calculate();
                        }
                        operationStack.pop();
                        break;
                    }
                    // 如果该操作符的优先级高于操作符栈的top操作符，直接压入操作符栈
                    if (MathUtil.priority(symbol[i]) > MathUtil.priority(operationStack.peek())){
                        operationStack.push(symbol[i]);
                        break;
                    }
                    // 如果该操作符的优先级低于或等于操作符栈的top操作符，弹出数字栈的两个数字以及
                    // 操作符栈的top操作符，并计算
                    if (MathUtil.priority(symbol[i]) <= MathUtil.priority(operationStack.peek())) {
                        calculate();
                    }
                }
            }
        }
        while (!operationStack.isEmpty()){
            calculate();
        }
        return numericStack.pop();
    }
    /**
     * 从数字栈中弹出两个数字、操作符栈中弹出一个操作符，计算出结果并压入数字栈
     * @Function calculate
     * @author   Oh_MyBug
     * @Date     2020/3/17 21:55
     * @param
     * @return 计算结果
     *
     */
    public void calculate(){
        String b = numericStack.pop();
        String a = numericStack.pop();
        String oper = operationStack.pop();
        double result = MathUtil.calculate(a, b, oper);
        numericStack.push(String.valueOf(result));
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("OhMyBug's Calculator");
        frame.setContentPane(new Calculator().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.pack();
        int x = (screenSize.width - frame.getWidth())/2;
        int y = (screenSize.height - frame.getHeight())/2;
        frame.setLocation(x, y);
        frame.setVisible(true);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
