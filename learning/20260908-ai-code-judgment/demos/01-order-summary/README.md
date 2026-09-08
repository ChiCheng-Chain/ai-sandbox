# Demo 1 · 订单金额汇总（KP1 NPE 与空值处理）

一段典型的 AI 生成订单服务代码：功能"正确"、单测能过，但藏着一个生产事故级别的缺陷。

## 运行

在 `demos/` 根目录（本主题统一环境）：

```bash
javac -encoding UTF-8 OrderSummary.java && java OrderSummary
```

## 预期观察点

先预测：这段代码会在什么输入下出事、出什么事。再运行观察，对照你的预测。

## 对应知识点

KP1-null-safety
