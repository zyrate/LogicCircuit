package logic_circuit.tools.wavedisplay;

import logic_circuit.base.port.Port;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * �źŲ�����
 * ����Ϊ����Ķ˿ڲ����ź�
 * ���ݵ��Ǵ����ʱ���б�(��һ��Ϊ��ֵ��������Ϊʱ��ζ�����ʱ���)
 */
public class SignalCreater {
    LinkedHashMap<Port, ArrayList<Integer>> timeLists = new LinkedHashMap<>();
    private boolean started = false;

    public SignalCreater(LinkedHashMap timeLists){
        this.timeLists = timeLists;
    }

    /**
     * ��ʼ�� - Ϊ�˿����ó�ֵ
     * �����һ�鲨�β��ԣ�������delayֵ̫С��
     * @param delay �ӳ�ֵ��ȷ��init��ȫ
     */
    public void init(int delay){
        for(Map.Entry<Port, ArrayList<Integer>> e : timeLists.entrySet()){
            Port p = e.getKey();
            ArrayList<Integer> list = e.getValue();
            p.setV(list.get(0));
        }
        try {
            Thread.sleep(delay);//ȷ��init����
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * ��ʼ�����ź�
     */
    public void create(){
        started = true;
        for(Map.Entry<Port, ArrayList<Integer>> e : timeLists.entrySet()){
            Port p = e.getKey();
            ArrayList<Integer> list = e.getValue();
            new Thread(){
                @Override
                public void run() {
                    for(int i = 1; i < list.size(); i++){
                        try {
                            if(!started)
                                break;
                            Thread.sleep(list.get(i));
                        } catch (InterruptedException e1) { e1.printStackTrace(); }
                        p.setV(p.getV()==1?0:1);//��ת
                    }
                }
            }.start();
        }
    }
    /**
     * �жϲ����ź�
     */
    public void interrupt(){
        started = false;
    }
}











