/**
 * 订单详情查询接口 —— AI 生成代码的典型样本（简化为单文件，模拟 Controller 层逻辑）。
 * 需求：用户在前端"我的订单"页点击某个订单，查看其详情。
 */
public class OrderDetailApi {

    static class CurrentUser {
        Long userId;          // 从登录态（token）解析出的当前登录用户
        CurrentUser(Long userId) { this.userId = userId; }
    }

    static class Order {
        Long id;
        Long ownerUserId;     // 订单归属人
        String shippingAddress;
        String phone;         // 联系方式，演示中一律用 XXXXXXXX 代替
        Long amountCents;

        Order(Long id, Long ownerUserId, String shippingAddress, String phone, Long amountCents) {
            this.id = id;
            this.ownerUserId = ownerUserId;
            this.shippingAddress = shippingAddress;
            this.phone = phone;
            this.amountCents = amountCents;
        }
    }

    // 模拟数据库里的全平台订单
    static Order[] allOrders = {
        new Order(9001L, 100L, "北京市朝阳区xx路1号", "XXXXXXXX", 19900L),
        new Order(9002L, 200L, "上海市浦东新区xx路2号", "XXXXXXXX", 8900L),
        new Order(9003L, 300L, "广州市天河区xx路3号", "XXXXXXXX", 4500L),
    };

    /** 前端 GET /api/order/detail?orderId=9001 时调用 */
    static String getOrderDetail(CurrentUser currentUser, Long orderId) {
        for (Order o : allOrders) {
            if (o.id.equals(orderId)) {
                // 查到订单，返回详情（真实场景是 JSON 返回给前端）
                return "订单" + o.id + " | 收货人:" + o.shippingAddress
                        + " | 联系方式:" + o.phone + " | 金额:" + o.amountCents;
            }
        }
        return "订单不存在";
    }

    public static void main(String[] args) {
        // 场景 A：用户 200 查自己的订单 9002 —— 正常
        System.out.println("场景A: " + getOrderDetail(new CurrentUser(200L), 9002L));

        // 场景 B：用户 200 在浏览器地址栏把 orderId 改成 9001、9003 挨个试试 —— 事故现场
        System.out.println("场景B: " + getOrderDetail(new CurrentUser(200L), 9001L));
        System.out.println("场景B: " + getOrderDetail(new CurrentUser(200L), 9003L));
    }
}
