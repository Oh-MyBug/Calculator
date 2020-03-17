/**
 * 数据节点，用于操作符栈以及数字栈
 * @Class:       DataNode
 * @author:      Oh_MyBug
 * @Date:        2020/3/17 22:26
 */
class DataNode {
    private String data;
    private DataNode next;

    public DataNode(String data) {
        this.data = data;
        this.next = null;
    }

    public DataNode(DataNode DataNode) {
        this.data = DataNode.data;
        this.next = null;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public DataNode getNext() {
        return next;
    }

    public void setNext(DataNode next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "DataNode{" +
                "data=" + data +
                '}';
    }
}
