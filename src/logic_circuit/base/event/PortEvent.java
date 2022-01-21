package logic_circuit.base.event;

/**
 * �˿��¼�
 */
public class PortEvent {
    public static final String SETV = "set V with transmission";//����
    public static final String SETV_ONLY = "set V without transmission";//������
    private String type = "";
    private boolean stateChage = false;

    public PortEvent(String type, boolean stateChage){
        this.type = type;
        this.stateChage = stateChage;
    }

    public boolean isStateChage() {
        return stateChage;
    }

    public String getType(){
        return type;
    }
}
