# 模拟数字逻辑电路 LogicCiruit
## 介绍
- 提供电路基础组件和部分功能测试
- 具体拼接方法和物理组件一样，请参考教科书
- 提供端口状态可视化面板功能
- 提供波形图生成功能

## 包类说明
```text
- logic_circuit
    - base 基础组件
        - event 事件机制
            - PortEvent.java 端口事件
            - PortListener.java 端口监听器
        - port 端口
            - Port.java 端口，导线连接的对象
        - gate 逻辑门
            - MultiInputGate.java 多输入门接口
            - AbsMultiInputGate.java 多输入门抽象类（模版方法模式）
            - AndGate.java 与门
            - OrGate.java 或门
            - NotGate.java 非门
            - HubGate.java 集线门
        - wire 导线
            - Wire.java 普通导线，负责事件传递
            - FWire.java 翻转导线，0/1翻转（仅测试）
    - flipflop 触发器
        - basicflipflop 基本触发器
            - RS.java 基本RS触发器
        - edgeflipflop 边沿触发器
            - EdgeD.java 边沿D触发器，74LS74
            - EdgeJK.java 边沿JK触发器
        - syncflipflop 同步触发器
            - SyncD.java 同步D触发器
            - SyncJK.java 同步JK触发器
            - SyncRS.java 同步RS触发器
        - register 寄存器
            - $74LS175.java 四边沿D触发器
            - $74LS194.java 4位双向移位寄存器
        - counter 计数器
            - $74LS191.java 4位二进制同步加/减法器
    - msi 中规模组件
        - decoder 译码器
            - $74LS138.java 3线-8线译码器
            - $74LS139.java 2线-4线译码器
        - multiplexer 多路选择器
            - Mux41.java 4选1选择器
            - $74LS153.java 双4选1选择器
    - tools 工具包
        - util Java工具类包
        - decoder 主要是模拟七段显示译码器
        - showpanel 显示面板
        - wavedisplay 波形显示
    - product 成品包
- test 测试代码
```

## 基础用法
1. 找到想要模拟的硬件的逻辑电路图
2. 根据电路图从base包中找到需要的逻辑门和组件
3. 用导线把组件的端口Port连接起来（`new Wire(port1, port2)`）
4. 完成后把组件整体的输入/输出端口注册到显示面板ShowPanel中，执行显示方法
5. 操作显示面板的电源、端口、频率源、波形图等工具进行实验