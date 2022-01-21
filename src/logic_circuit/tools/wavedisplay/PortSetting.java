package logic_circuit.tools.wavedisplay;

import logic_circuit.base.port.Port;
import logic_circuit.tools.util.CompFactory;

import javax.swing.*;
import java.util.ArrayList;

/**
 * ���ö˿ڵĲ���ʱ��
 */
public class PortSetting extends JPanel{
    private JLabel lName, lState, lTimes;
    private JComboBox<Integer> cState;//��ʼ״̬
    private JTextField tTimes;
    Port port;

    public PortSetting(Port port){
        this.port = port;

        lName = CompFactory.createLable(port.getName());
        lState = CompFactory.createLable("��ʼ״̬:");
        cState = CompFactory.createComboBox(0, 1);
        lTimes = CompFactory.createLable("ʱ���б�:");
        tTimes = CompFactory.createTextField(20);

        lName.setBorder(BorderFactory.createRaisedSoftBevelBorder());

        this.add(lName);
        this.add(lState);
        this.add(cState);
        this.add(lTimes);
        this.add(tTimes);
    }

    public int getState(){
        return cState.getSelectedIndex();//ֻ��0��1�������±����
    }
    public String getSList(){
        return tTimes.getText();
    }

    public ArrayList<Integer> getList(int unitTime){
        ArrayList<Integer> list = new ArrayList<>();
        list.add(getState());//��ʼ״̬
        String[] arr = tTimes.getText().split("[,�� ]");
        for(int i = 0; i < arr.length; i++){
            try {
                list.add((int) (Double.parseDouble(arr[i]) * unitTime));
            }catch (Exception e){
                break;//����Ļ�������ľ�ʧЧ��
            }
        }
        return list;
    }

    public Port getPort() {
        return port;
    }

    /**
     * ����
     */
    public void reset(int state, String list){
        cState.setSelectedIndex(state);
        tTimes.setText(list);
    }
}
