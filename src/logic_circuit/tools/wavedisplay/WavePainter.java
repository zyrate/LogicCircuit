package logic_circuit.tools.wavedisplay;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * ���λ����� - JPanel
 * ���ⲿ����һ��HashMap����Ϊ�˿����ƣ�ֵΪһ��ʱ���б�(��¼�ų�ʼֵ��һϵ�з�תʱ���)
 * ʱ��γ���ʱ���ܳ���(һ��Ϊ5����CP����)���õ���ʱ�����ռ�����ı�
 * panel��ָ����� X ������� = �˿�ά��ĳһ״̬�Ĳ����߶γ�
 */
class WavePainter extends JPanel {
    public static final int INITIAL_X = 20;//���ʳ�ʼλ��
    public static final int INITIAL_Y = 50;
    public static final int INTERVAL_LEFT = 50;//�����벨�μ��
    public static final int WAVE_WIDTH = 50;//���εĿ��
    public static final int INTERVAL = 20;//���εļ��
    public static final int PANEL_WIDTH = 650;//���Ŀ��
    //LinkedHashMap�ᰴ�ռ����˳�����
    private LinkedHashMap<String, ArrayList<Integer>> waveDatas;//ʱ���б���¼��ʼ״̬�ͷ�ת��
    private LinkedHashMap<String, ArrayList<Integer>> waveforms;//�����б���¼���γ�ʼ״̬��ÿ��ʱ�εĳ���
    private int posX = INITIAL_X;//����λ��(��ͣ�仯)
    private int posY = INITIAL_Y;
    private int testTime;//��������ʱ
    private int portCnt;//�˿�����
    private String name;//����ͼ����

    public WavePainter(String name, int portCnt){
        this.name = name;
        this.portCnt = portCnt;
        setPreferredSize(new Dimension(PANEL_WIDTH, getActlHeight()));
        setBorder(BorderFactory.createTitledBorder(name+"����ͼ"));

    }

    //�õ�ʵ�ʸ߶�
    public int getActlHeight(){
        return 2*INITIAL_Y+portCnt*WAVE_WIDTH+(portCnt-1)*INTERVAL;
    }
    /**
     * ��ʼ��
     */
    public void draw(int testTime, LinkedHashMap waveDatas){
        this.testTime = testTime;
        this.waveDatas = waveDatas;

        repaint();
    }

    /**
     * ��ͼǰ��׼������
     * ��ʼ����posX��poxY
     * ��waveDatas����󴴽�waveforms��painterʹ��
     */
    private void prepare(){
        posX = INITIAL_X;
        posY = INITIAL_Y;
        waveforms = new LinkedHashMap<>();

        //û��������������
        if(waveDatas == null)
            return;

        //�ܿ��
        int totalWidth = this.getWidth()- INTERVAL_LEFT *2;

        for(Map.Entry<String, ArrayList<Integer>> e : waveDatas.entrySet()){
            String portName = e.getKey();
            ArrayList<Integer> waveData = e.getValue();
            ArrayList<Integer> waveform = new ArrayList<>();

            waveform.add(waveData.get(0));//��ʼ״̬
            if(waveData.size() > 1)
                waveform.add((int)((1.0 * waveData.get(1))/testTime * totalWidth));//�൱�ڵ�һ�� - 0
            int size = waveData.size();
            for(int i = 1; i < size; i++){
                if(i+1 < size) {
                    //һ��ʱ��ռ��ʱ֮�� * ���
                    waveform.add((int) (1.0 * (waveData.get(i + 1) - waveData.get(i)) / testTime * totalWidth));
                }
            }
            //���ʣ�µ�
            waveform.add((int)(1.0*(testTime-waveData.get(size-1))/testTime * totalWidth));
            waveforms.put(portName, waveform);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //����ͼƬ
        ImageIcon img = new ImageIcon(WavePainter.class.getResource("/logic_circuit/tools/imgs/back.jpg"));
        g.drawImage(img.getImage(), 0, 0, getWidth(), getActlHeight(), img.getImageObserver());
        //׼������
        prepare();
        //��ʼ��
        for(Map.Entry<String, ArrayList<Integer>> e : waveforms.entrySet()){
            String portName = e.getKey();
            ArrayList<Integer> waveform = e.getValue();

            //������
            g.setFont(new Font("Arial", 0, 18));
            g.drawString(portName, posX, posY+30);
            //������
            int state = waveform.get(0);
            posX = INITIAL_X + INTERVAL_LEFT;
            for(int i = 1; i < waveform.size(); i++){
                if(state == 0){
                    state = 1;
                    posY += WAVE_WIDTH;
                    g.drawLine(posX,posY,posX+waveform.get(i),posY);
                    posX += waveform.get(i);
                    if(i != waveform.size()-1){
                        //����ط�(��������)��Ϊ���������ݼ�����������˶λ�����һ�γ�����0���򲻻�����
                        if(!(i+1<waveform.size() && (waveform.get(i+1) == 0 || waveform.get(i)==0))) {
                            g.drawLine(posX, posY, posX, posY - WAVE_WIDTH);
                        }
                    }
                }else if(state == 1){
                    state = 0;
                    if(i != 1)//��ʼ��ʱ��posY����1��λ��
                        posY -= WAVE_WIDTH;
                    g.drawLine(posX,posY,posX+waveform.get(i),posY);
                    posX += waveform.get(i);
                    if(i != waveform.size()-1){
                        if(!(i+1<waveform.size() && (waveform.get(i+1) == 0 || waveform.get(i)==0))){
                            g.drawLine(posX, posY, posX, posY + WAVE_WIDTH);
                        }
                    }
                }
            }
            if(state == 0) {//�û�0�ˣ�˵�����״̬��1
                //��֤���ʽ������λ���� 0 ��
                posY += WAVE_WIDTH;
            }

            posX = INITIAL_X;//X�����λ
            posY += INTERVAL;//Y������
        }
    }


    /**
     * ���沨��ͼ
     * @param x ����x
     * @param y ����y
     * @param h ���ڸ�
     */
    public void saveImg(int x, int y, int h){
        try {
            //����
            BufferedImage screenshot = new Robot().createScreenCapture(
                    new Rectangle(x+8, y+h-this.getHeight()-8, this.getWidth(), this.getHeight())
            );

            //ѡ��·��
            JFileChooser chooser = new JFileChooser("C:\\Users\\windows\\Desktop");//Ĭ��������
            chooser.setSelectedFile(new File(name+"����ͼ.png"));
            int option = chooser.showDialog(null, "���沨��ͼ");
            if(option == JFileChooser.APPROVE_OPTION){

                File file = chooser.getSelectedFile();
                //д���ļ�
                ImageIO.write(screenshot, "png", file);
            }
        } catch (AWTException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}