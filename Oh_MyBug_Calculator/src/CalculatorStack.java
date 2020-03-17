/**
 * 实现简单计算器所需要的栈结构，类中实现了 判断栈是否为空（isEmpty）、判断栈是否满（isFull）、
 * 入栈（push）、出栈（pop）、获取top节点（peek）、显示栈信息（list）
 * @Class:       CaculatorStack
 * @author:      Oh_MyBug
 * @Date:        2020/3/17 22:01
 */
public class CalculatorStack {
    private DataNode head = null;

    /**
     * 获取头部节点的信息
     * @Function getHead
     * @author   Oh_MyBug
     * @Date     2020/3/17 22:05
     * @param
     * @return
     */
    public DataNode getHead() {
        return head;
    }

    /**
     * CaculatorStack构造器，初始化头部节点
     * @Function CaculatorStack
     * @author   Oh_MyBug
     * @Date     2020/3/17 22:06
     * @param
     * @return
     */
    public CalculatorStack() {
        head = new DataNode("");
    }

    /**
     * 判断栈是否为空
     * @Function isEmpty
     * @author   Oh_MyBug
     * @Date     2020/3/17 22:07
     * @param
     * @return 布尔值 空为true 非空为false
     */
    public boolean isEmpty() {
        return head.getNext() == null;
    }

    /**
     * 压栈操作
     * @Function push
     * @author   Oh_MyBug
     * @Date     2020/3/17 22:07
     * @param    value
     * @return
     */
    public void push(String value) {
        DataNode DataNode = new DataNode(value);
        DataNode.setNext(head.getNext());
        head.setNext(DataNode);
    }

    /**
     * 弹栈操作
     * @Function pop
     * @author   Oh_MyBug
     * @Date     2020/3/17 22:12
     * @param
     * @return 节点数据
     */
    public String pop() {
        if (isEmpty()) {
            throw new RuntimeException("表达式有误！");
        }
        String value = head.getNext().getData();
        head = head.getNext();
        return value;
    }

    /**
     * 获取栈顶节点信息
     * @Function peek
     * @author   Oh_MyBug
     * @Date     2020/3/17 22:16
     * @param
     * @return 栈顶节点的数据
     */
    public String peek() {
        if (isEmpty())
            throw new RuntimeException("表达式有误！");
        return head.getNext().getData();
    }

    /**
     * 显示栈的情况[遍历栈]，遍历时，需要从栈顶开始显示数据
     * @Function list
     * @author   Oh_MyBug
     * @Date     2020/3/17 22:17
     * @param
     * @return
     */
    public void list() {
        if (isEmpty()) {
            System.out.println("栈空！");
            return;
        }
        DataNode h = head;
        while (h.getNext() != null) {
            h = h.getNext();
            System.out.println(h);
        }
    }
}
