/**
 * 订单搜索 —— AI 生成代码的典型样本（模拟 Service 层拼接 SQL 并执行）。
 * 需求：运营在后台输入订单号，搜索对应订单。
 *
 * 真实场景中 SQL 发给数据库执行；这里用字符串模拟"拼接后送库"的效果，
 * 数据库行为用注释标明。
 */
public class OrderSearch {

    // 模拟订单表：orderId -> 描述
    static String[][] orderTable = {
        {"SO20260901001", "用户A的订单，含收货信息"},
        {"SO20260901002", "用户B的订单，含收货信息"},
        {"SO20260901003", "用户C的订单，含收货信息"},
    };

    /** AI 生成的实现：直接拼接用户输入 */
    static String searchOrder(String userInput) {
        // 第一步：拼 SQL（AI 的写法）
        String sql = "SELECT * FROM orders WHERE order_id = '" + userInput + "'";
        System.out.println("[拼出的SQL] " + sql);

        // 第二步：送库执行 —— 这里模拟数据库看到这句 SQL 后的实际行为
        return executeSql(sql);
    }

    /** 模拟数据库：按 SQL 语义执行（不是按开发意图执行） */
    static String executeSql(String sql) {
        // 数据库只认 SQL 语义。为了演示，从 sql 里提取 WHERE 后面的条件来判断命中范围。
        // ' OR '1'='1  会让条件永真 —— 返回全表（注入成功的标志）
        // ' OR '1'='2  会让条件永假 —— 全表都查不到
        if (sql.contains("' OR '1'='1")) {
            StringBuilder all = new StringBuilder("【注入成功】返回全表:\n");
            for (String[] row : orderTable) all.append("  ").append(row[0]).append(" | ").append(row[1]).append("\n");
            return all.toString();
        }
        // 正常按订单号匹配
        String target = sql.substring(sql.indexOf("= '") + 3, sql.lastIndexOf("'"));
        for (String[] row : orderTable) {
            if (row[0].equals(target)) return "命中: " + row[0] + " | " + row[1];
        }
        return "未找到订单";
    }

    public static void main(String[] args) {
        // 场景 A：正常使用 —— 输入真实订单号
        System.out.println("场景A: " + searchOrder("SO20260901002"));

        // 场景 B：攻击者输入 —— 引号闭合原条件，OR 一个永真式
        System.out.println("场景B: " + searchOrder("' OR '1'='1"));

        // 场景 C：攻击者输入 —— 永假式，用于探测（盲注的第一步）
        System.out.println("场景C: " + searchOrder("' OR '1'='2"));
    }
}
