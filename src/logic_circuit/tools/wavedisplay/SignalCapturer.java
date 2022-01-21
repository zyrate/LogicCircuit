package logic_circuit.tools.wavedisplay;
import logic_circuit.base.event.PortEvent;
import logic_circuit.base.event.PortListener;
import logic_circuit.base.port.Port;
import java.util.ArrayList;
import java.util.LinkedHashMap;
/**
 * �źŲ�����
 * �����������Ķ˿ڣ���¼��start��stopʱ���ڸ����˿ڵ��źų�ʼֵ�ͷ�תʱ���
 * ����LinkedHashMap - waveDatas�����ڵ���stopʱ����
 */
public class SignalCapturer {
    private ArrayList<Port> ports = new ArrayList<>();
    private LinkedHashMap<String, ArrayList<Integer>> waveDatas;
    private long startTime;
    private boolean started = false;//�Ƿ�ʼ����
    public SignalCapturer(ArrayList ...portsLists){
        for(ArrayList list:portsLists){
            this.ports.addAll(list);
        }
        addListener();
    }
    private void addListener(){
        for(Port p:ports){
            p.addPortListener(new PortListener() {
                @Override
                public void portAffected(PortEvent e) {
                    if(started){
                        if(e.isStateChage()) {//״̬�ı���
                            long time = System.currentTimeMillis();//��ȡ��ǰʱ��
                            waveDatas.get(p.getName()).add((int) (time - startTime));
                        }
                    }
                }
            });
        }
    }
    /**
     * �������ж˿ڵ�ʱ���б�����һ����Ϊ��ʼ״̬
     */
    private void prepare(){
        waveDatas = new LinkedHashMap<>();
        for(Port p : ports){
            ArrayList<Integer> list = new ArrayList<>();
            list.add(p.getV());//��һ���ǳ�ʼֵ
            waveDatas.put(p.getName(), list);
        }
    }
    /**
     * ��ʼ����
     * Ҫֹͣ���������stop����
     * @param testTime ͣ��ʱ��
     */
    public void start(int testTime){
        prepare();//start֮ǰҪȷ������˿ڶ������˳�ֵ
        startTime = System.currentTimeMillis();
        started = true;
        try {
            Thread.sleep(testTime);
        } catch (InterruptedException e) { e.printStackTrace(); }
    }
    /**
     * ֹͣ����
     * ����ȷ��add�����Ѿ�ִ�����ˣ�Ŀǰ�������ӳٵİ취����������
     * @return ���ز�����
     */
    public LinkedHashMap stop(){
        started = false;
        return waveDatas;
    }
}
