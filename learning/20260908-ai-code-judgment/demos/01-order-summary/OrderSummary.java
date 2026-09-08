import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 订单金额汇总服务 —— AI 生成代码的典型样本。
 * 需求：给定订单列表，返回每个用户（userId）的累计消费金额。
 */
public class OrderSummary {

    static class Order {
        final String userId;
        final Long amountCents;

        Order(String userId, Long amountCents) {
            this.userId = userId;
            this.amountCents = amountCents;
        }

        String userId() { return userId; }
        Long amountCents() { return amountCents; }
    }

    public static Map<String, Long> sumByUser(List<Order> orders) {
        return orders.stream()
                .filter(o -> o.amountCents() > 0)      // 只统计有效订单
                .collect(Collectors.groupingBy(
                        Order::userId,                  // 按用户分组
                        Collectors.summingLong(Order::amountCents)));
    }

    public static void main(String[] args) {
        // 场景 A：正常数据
        List<Order> normal = Arrays.asList(
                new Order("u1001", 19900L),
                new Order("u1002", 8900L),
                new Order("u1001", 4500L));
        System.out.println("场景A: " + sumByUser(normal));

        // 场景 B：线上真实数据 —— 历史遗留订单 amountCents 为 null，取消单 userId 为 null
        List<Order> fromDb = Arrays.asList(
                new Order("u1001", 19900L),
                new Order(null, 5000L),        // 游客订单，userId 为 null
                new Order("u1002", null),      // 旧数据未初始化金额
                new Order("u1001", 4500L));
        System.out.println("场景B: " + sumByUser(fromDb));
    }
}
