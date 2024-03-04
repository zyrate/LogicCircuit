# ģ�������߼���· LogicCiruit
## ����
- �ṩ��·��������Ͳ��ֹ��ܲ���
- ����ƴ�ӷ������������һ������ο��̿���
- �ṩ�˿�״̬���ӻ���幦��
- �ṩ����ͼ���ɹ���

## ����˵��
```text
- logic_circuit
    - base �������
        - event �¼�����
            - PortEvent.java �˿��¼�
            - PortListener.java �˿ڼ�����
        - port �˿�
            - Port.java �˿ڣ��������ӵĶ���
        - gate �߼���
            - MultiInputGate.java �������Žӿ�
            - AbsMultiInputGate.java �������ų����ࣨģ�淽��ģʽ��
            - AndGate.java ����
            - OrGate.java ����
            - NotGate.java ����
            - HubGate.java ������
        - wire ����
            - Wire.java ��ͨ���ߣ������¼�����
            - FWire.java ��ת���ߣ�0/1��ת�������ԣ�
    - flipflop ������
        - basicflipflop ����������
            - RS.java ����RS������
        - edgeflipflop ���ش�����
            - EdgeD.java ����D��������74LS74
            - EdgeJK.java ����JK������
        - syncflipflop ͬ��������
            - SyncD.java ͬ��D������
            - SyncJK.java ͬ��JK������
            - SyncRS.java ͬ��RS������
        - register �Ĵ���
            - $74LS175.java �ı���D������
            - $74LS194.java 4λ˫����λ�Ĵ���
        - counter ������
            - $74LS191.java 4λ������ͬ����/������
    - msi �й�ģ���
        - decoder ������
            - $74LS138.java 3��-8��������
            - $74LS139.java 2��-4��������
        - multiplexer ��·ѡ����
            - Mux41.java 4ѡ1ѡ����
            - $74LS153.java ˫4ѡ1ѡ����
    - tools ���߰�
        - util Java�������
        - decoder ��Ҫ��ģ���߶���ʾ������
        - showpanel ��ʾ���
        - wavedisplay ������ʾ
    - product ��Ʒ��
- test ���Դ���
```

## �����÷�
1. �ҵ���Ҫģ���Ӳ�����߼���·ͼ
2. ���ݵ�·ͼ��base�����ҵ���Ҫ���߼��ź����
3. �õ��߰�����Ķ˿�Port����������`new Wire(port1, port2)`��
4. ��ɺ��������������/����˿�ע�ᵽ��ʾ���ShowPanel�У�ִ����ʾ����
5. ������ʾ���ĵ�Դ���˿ڡ�Ƶ��Դ������ͼ�ȹ��߽���ʵ��