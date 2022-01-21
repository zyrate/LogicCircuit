package logic_circuit.base.gate;

import logic_circuit.base.port.Port;

public interface MultiInputGate {
    /**
     * ����������
     * @param ins ��������ݣ�����Ҫ�����˿���
     */
    void input(int ...ins);

    /**
     * ����һ������
     * @param index
     * @param value
     */
    void set(int index, int value);

    /**
     * �õ�����˿�
     * @param index �˿��±�
     * @return ��Ӧ�˿�
     */
    Port inPort(int index);

    /**
     * Ϊ�˼򻯴��룬�����������nextIndex�����Լ�һ��֮ǰ������Բ��û�����
     * @return û�����ӹ��Ķ˿�
     */
    Port inPort();

    /**
     * �������ж˿�
     */
    void reset();
}
