package logic_circuit.base.gate;

import logic_circuit.base.port.Port;

public interface MultiInputGate {
    /**
     * 输入多个数据
     * @param ins 任意个数据，但不要超过端口数
     */
    void input(int ...ins);

    /**
     * 输入一个数据
     * @param index
     * @param value
     */
    void set(int index, int value);

    /**
     * 得到输入端口
     * @param index 端口下标
     * @return 对应端口
     */
    Port inPort(int index);

    /**
     * 为了简化代码，这个方法将对nextIndex进行自加一。之前的类可以不用换方法
     * @return 没有连接过的端口
     */
    Port inPort();

    /**
     * 重置所有端口
     */
    void reset();
}
