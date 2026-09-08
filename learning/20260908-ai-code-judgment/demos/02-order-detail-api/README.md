# Demo 2 · 订单详情查询（KP2 越权访问）

一段"功能完全正确、测试全绿、上线后能被任意用户拉走全平台订单数据"的 AI 生成接口代码。

## 运行

在 `demos/` 根目录（本主题统一环境，JDK 8+，无额外依赖）：

```bash
javac -encoding UTF-8 OrderDetailApi.java && java OrderDetailApi
```

## 预期观察点

先预测：这个接口的调用方是谁？谁能查到什么数据？再运行观察，对照你的预测。

## 对应知识点

KP2-authz-injection
