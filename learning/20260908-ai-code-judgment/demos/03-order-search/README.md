# Demo 3 · 订单搜索（KP2b SQL 注入）

一段"按订单号搜索订单"的 AI 生成代码。功能正确、测试全绿，但输入框接受任何字符串——包括 SQL 关键字。

## 运行

在 `demos/` 根目录（本主题统一环境，JDK 8+，无额外依赖）：

```bash
javac -encoding UTF-8 OrderSearch.java && java OrderSearch
```

## 预期观察点

搜索框按需求应该"只接受订单号"。先预测：传入非订单号字符串会发生什么？再运行观察。

## 对应知识点

KP2-authz-injection（SQL 注入部分）
