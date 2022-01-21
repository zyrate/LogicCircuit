package logic_circuit.tools.wavedisplay;

import logic_circuit.base.port.Port;
import logic_circuit.tools.util.CompFactory;
import logic_circuit.tools.util.JavaUtil;
import logic_circuit.tools.util.ThreadPool;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ����ͼ��ʾ��
 * ������SignalCreater��SignalCapturer��WavePainter���ʹ��
 * ������һ�����ڣ��������û���������˿ڵĲ��Σ������LinkedHashMap(�洢������˿ڡ�ʱ���б�)
 * �����������û�����ʱ�Σ�ʱ���õ�λʱ��ı�����ʾ
 * ��map����creater����ports����capturer�����������ƺͶ˿�������painter
 * creater����init��create��������ʼ�����ź�;
 * capturer����start��stop�����������ʱ���ڵ��ź�;
 * painter����draw�������ղ���ʱ����������������ʾ�����Ĳ���
 */
public class WaveDisplayer extends JFrame{
    Port[] inPorts;
    Port[] filtedInPorts;//���˹���ֻ��painter
    Port[] filtedOutPorts;
    String waveName;//����ͼ����
    public static final String sUnit = "500";
    public static final String sTime = "5500";
    private JPanel pCenter, pUnit, pTime, pTU;
    private JButton bGenerate;//���ɼ�
    private JTextField tUnit, tTime;//ʱ��
    private JLabel lUnit, lTime;
    private JPopupMenu pop;
    private JMenuItem iSaveImg, iExport;
    private WaveDisplayer self = this;

    private ArrayList<PortSetting> portSettings = new ArrayList<>();//����˿�
    private SignalCreater creater;//�ź�������
    private SignalCapturer capturer;//�źŲ�����
    private WavePainter painter;//���λ�����

    public WaveDisplayer(String waveName, Port[] inPorts, Port[] filtedInPorts, Port[] filtedOutPorts) {
        this.waveName = waveName;
        this.inPorts = inPorts;
        this.filtedInPorts = filtedInPorts;
        this.filtedOutPorts = filtedOutPorts;

        init();
        initTools();
        addListener();
        addHandler();

        this.setSize(630, (inPorts.length+1)*65+painter.getActlHeight());
        this.setMinimumSize(new Dimension(620, 300));
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void init(){
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setTitle("���β鿴��");

        pCenter = new JPanel();
        pCenter.setLayout(new GridLayout(2+inPorts.length, 1));

        //TU
        pUnit = new JPanel();
        pTime = new JPanel();
        pTU = new JPanel();
        tUnit = CompFactory.createTextField(8);
        tTime = CompFactory.createTextField(8);
        lUnit = CompFactory.createLable("��λʱ�� (ms)");
        lTime = CompFactory.createLable("������ʱ (ms)");
        pUnit.add(lUnit);
        pUnit.add(tUnit);
        pTime.add(lTime);
        pTime.add(tTime);
        pTU.add(pTime);
        pTU.add(pUnit);
        tTime.setText(sTime);
        tUnit.setText(sUnit);

        pCenter.add(pTU);

        //PORT
        for(Port p:inPorts){
            PortSetting ps = new PortSetting(p);
            portSettings.add(ps);
            pCenter.add(ps);
        }

        //BUTTON
        bGenerate = CompFactory.createButton("���ɲ���ͼ");
        pCenter.add(bGenerate);

        //POPMENU
        pop = new JPopupMenu();
        iSaveImg = CompFactory.createMenuItem("���沨��ͼ");
        iExport = CompFactory.createMenuItem("������������");
        pop.add(iSaveImg);
        pop.add(iExport);

        this.add(pCenter, BorderLayout.CENTER);
    }

    private void initTools(){
        capturer = new SignalCapturer(JavaUtil.arrToList(filtedInPorts), JavaUtil.arrToList(filtedOutPorts));
        painter = new WavePainter(waveName, filtedInPorts.length+ filtedOutPorts.length);
        this.add(painter, BorderLayout.SOUTH);
    }

    /**
     * ����
     */
    private void generate() {
        //����ת������Ļ��Ͳ�����
        int testTime, unit;
        try {
            testTime = Integer.parseInt(tTime.getText());
            unit = Integer.parseInt(tUnit.getText());
        }catch (Exception e){
            return;
        }

        LinkedHashMap<Port, ArrayList<Integer>> timeLists = new LinkedHashMap<>();//ʱ���б�

        //��Ӽ�ֵ
        for(PortSetting ps:portSettings){
            timeLists.put(ps.getPort(), ps.getList(unit));
        }

        bGenerate.setEnabled(false);
        bGenerate.setText("�������ɲ���ͼ�����������ʾ���...");

        creater = new SignalCreater(timeLists);
        creater.init(100);
        creater.create();
        capturer.start(testTime);
        painter.draw(testTime, capturer.stop());
        creater.interrupt();

        bGenerate.setEnabled(true);
        bGenerate.setText("���ɲ���ͼ");

    }

    /**
     * ���沨��ͼ
     */
    private void saveImg(){
        painter.saveImg(this.getX(), this.getY(), this.getHeight());
    }

    /**
     * ��Ӽ�����
     */
    private void addListener(){
        bGenerate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ThreadPool.newThread(new Runnable() {
                    @Override
                    public void run() {
                        generate();
                    }
                });
            }
        });
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getButton() == MouseEvent.BUTTON3){
                    pop.show(self, e.getX(), e.getY());
                }
            }
        });
        iSaveImg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ThreadPool.newThread(new Runnable() {
                    @Override
                    public void run() {
                        saveImg();
                    }
                });
            }
        });
        iExport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ThreadPool.newThread(new Runnable() {
                    @Override
                    public void run() {
                        exportSetting();
                    }
                });
            }
        });
    }

    /**
     * ��������
     */
    private void exportSetting(){
        String buff = "";
        buff += "(test_time:"+tTime.getText()+", unit_time:"+tUnit.getText()+")\n";
        for(PortSetting ps:portSettings){
            buff +=  "{state:"+ps.getState()+", list:"+ps.getSList()+"}\n";
        }

        JFileChooser chooser = new JFileChooser("C:\\Users\\windows\\Desktop");//Ĭ��������
        chooser.setSelectedFile(new File(waveName+"��������.wvs"));
        int option = chooser.showDialog(null, "����");
        if(option == JFileChooser.APPROVE_OPTION){
            File file = chooser.getSelectedFile();
            //д���ļ�
            JavaUtil.write(file, buff);
            JOptionPane.showMessageDialog(this, "�����ɹ���", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
        }

    }

    /**
     * ��������
     * @param file
     */
    private void importSetting(File file){
        //�ļ����Ͳ���
        if(!file.getName().endsWith(".wvs")){
            JOptionPane.showMessageDialog(this, "�����ļ�����", "����", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String testTime="", unitTime="";
        ArrayList<Object[]> datas = new ArrayList<>();
        String buff = JavaUtil.readAll(file);
        Matcher m = Pattern.compile("\\([\\s\\S]*?\\)").matcher(buff);
        if(m.find()){
            String[] sTimes = JavaUtil.getUseful(m.group());
            if(sTimes == null) {
                JOptionPane.showMessageDialog(this, "�����ļ�����", "����", JOptionPane.ERROR_MESSAGE);
                return;
            }

            testTime = sTimes[0];
            unitTime = sTimes[1];
        }
        Matcher m1 = Pattern.compile("\\{[\\s\\S]*?\\}").matcher(buff);
        while(m1.find()){
            String[] sData = JavaUtil.getUseful(m1.group());
            if(sData == null) {
                JOptionPane.showMessageDialog(this, "�����ļ�����", "����", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Object[] data = new Object[2];
            data[0] = Integer.parseInt(sData[0]);
            data[1] = sData[1];
            datas.add(data);
        }
        //����˿���������
        if(datas.size() != inPorts.length){
            JOptionPane.showMessageDialog(this, "������ļ�������˿�����������", "����", JOptionPane.ERROR_MESSAGE);
            return;
        }
        //����
        tTime.setText(testTime);
        tUnit.setText(unitTime);
        for(int i = 0; i < datas.size(); i++){
            portSettings.get(i).reset((int)datas.get(i)[0], (String)datas.get(i)[1]);
        }
        JOptionPane.showMessageDialog(this, "����ɹ���", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * �����ק���
     */
    private void addHandler(){
        painter.setTransferHandler(new TransferHandler(){
            @Override
            public boolean importData(JComponent comp, Transferable t) {
                try {
                    //������ж�����Ϊʵ����ק���ܺ��ٽ���ճ���ȼ��̲���ʱ����쳣�����쳣����UnsupportedDataFlavor
                    if(!t.isDataFlavorSupported(DataFlavor.javaFileListFlavor))
                        return false;
                    Object o = t.getTransferData(DataFlavor.javaFileListFlavor);
                    //ע��list������
                    java.util.List list = (java.util.List) o;//�ļ��б�
                    importSetting(new File(list.get(0).toString()));//ֻȡ��һ���ļ�
                    return true;
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                return false;
            }

            @Override
            public boolean canImport(JComponent comp, DataFlavor[] transferFlavors) {
                for (int i = 0; i < transferFlavors.length; i++) {
                    if (DataFlavor.javaFileListFlavor.equals(transferFlavors[i])) {
                        return true;
                    }
                }
                return false;
            }
        });
    }

}


