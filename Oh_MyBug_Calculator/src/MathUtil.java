import java.util.HashMap;

/**
 * 数学工具包类
 * @Class:       MathUtil
 * @author:      Oh_MyBug
 * @Date:        2020/3/17 22:17
 */
class MathUtil {
    private static final HashMap<String, Integer> OPERATTION_PRIORITY = new HashMap() {
        {
            put("(", -1);
            put("+", 0);
            put("-", 0);
            put("*", 1);
            put("/", 1);
            put(")", 2);
        }
    };

    /**
     * 判断字符串str是否为数字
     * @Function isNumeric
     * @author   Oh_MyBug
     * @Date     2020/3/17 22:18
     * @param
     * @return   布尔值 是->true 否->false
     */
    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    /**
     * 判断字符串str是否为操作符
     * @Function isOper
     * @author   Oh_MyBug
     * @Date     2020/3/17 22:19
     * @param
     * @return   布尔值 是->true 否->false
     */
    public static boolean isOper(String str) {
        return OPERATTION_PRIORITY.keySet().contains(str) ? true : false;
    }

    /**
     * 判断操作符是否为左括弧
     * @Function isLeftParenthesis
     * @author   Oh_MyBug
     * @Date     2020/3/17 22:22
     * @param
     * @return 布尔值 是->true 否->false
     */
    public static boolean isLeftParenthesis(String str){
        return OPERATTION_PRIORITY.get(str) == OPERATTION_PRIORITY.get("(");
    }
    /**
     * 判断操作符是否为右括弧
     * @Function isRightParenthesis
     * @author   Oh_MyBug
     * @Date     2020/3/17 22:22
     * @param   str
     * @return 布尔值 是->true 否->false
     */
    public static boolean isRightParenthesis(String str){
        return OPERATTION_PRIORITY.get(str) == OPERATTION_PRIORITY.get(")");
    }
    /**
     * 判断操作符优先级
     * @Function priority
     * @author   Oh_MyBug
     * @Date     2020/3/17 22:23
     * @param    str
     * @return   操作符优先级
     */
    public static int priority(String str) {
        return OPERATTION_PRIORITY.get(str);
    }

    /**
     * 计算两个数在操作符oper下的值
     * @Function calculate
     * @author   Oh_MyBug
     * @Date     2020/3/17 22:23
     * @param   num1
     * @param   num2
     * @param   oper
     * @return 计算结果
     */
    public static double calculate(String num1, String num2, String oper) {
        double a = Double.parseDouble(num1);
        double b = Double.parseDouble(num2);
        switch (oper) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "*":
                return a * b;
            case "/":
                if (b == 0) {
                    throw new RuntimeException("不能除以0！");
                }
                return a / b;
            default:
                break;
        }
        return 0;
    }
}
