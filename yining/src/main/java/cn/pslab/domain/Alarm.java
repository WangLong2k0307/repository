package cn.pslab.domain;

public class Alarm {
    private int a_id;
    private String eq_id;
    private String eq_name;
    private String use_department;
    private String address;
    private String type;
    private String alarmtime;
    private String contact;
    private String contact_tel;

    public Alarm() {
    }

    public Alarm(int a_id, String eq_id, String eq_name, String use_department, String address, String type, String alarmtime, String contact, String contact_tel) {
        this.a_id = a_id;
        this.eq_id = eq_id;
        this.eq_name = eq_name;
        this.use_department = use_department;
        this.address = address;
        this.type = type;
        this.alarmtime = alarmtime;
        this.contact = contact;
        this.contact_tel = contact_tel;
    }

    public int getA_id() {
        return a_id;
    }

    public void setA_id(int a_id) {
        this.a_id = a_id;
    }

    public String getEq_id() {
        return eq_id;
    }

    public void setEq_id(String eq_id) {
        this.eq_id = eq_id;
    }

    public String getEq_name() {
        return eq_name;
    }

    public void setEq_name(String eq_name) {
        this.eq_name = eq_name;
    }

    public String getUse_department() {
        return use_department;
    }

    public void setUse_department(String use_department) {
        this.use_department = use_department;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAlarmtime() {
        return alarmtime;
    }

    public void setAlarmtime(String alarmtime) {
        this.alarmtime = alarmtime;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getContact_tel() {
        return contact_tel;
    }

    public void setContact_tel(String contact_tel) {
        this.contact_tel = contact_tel;
    }

    @Override
    public String toString() {
        return "Alarm{" +
                "a_id=" + a_id +
                ", eq_id='" + eq_id + '\'' +
                ", eq_name='" + eq_name + '\'' +
                ", use_department='" + use_department + '\'' +
                ", address='" + address + '\'' +
                ", type='" + type + '\'' +
                ", alarmtime='" + alarmtime + '\'' +
                ", contact='" + contact + '\'' +
                ", contact_tel='" + contact_tel + '\'' +
                '}';
    }
}
