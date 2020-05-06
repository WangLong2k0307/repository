package cn.pslab.domain;

public class Equipment {
    private String eq_id;          //设备id
    private String datetime;
    private String name;            //名称
    private String status;          //状态
    private String use_department;  //使用单位
    private String equipment_class; //设备类型
    private String address;         //位置
    private String contact;
    private String contact_tel;
    private String producer;        //生产商
    private String type;            //型号
    private String sim_id;         //sim_id
    private String install_time;    //安装日期
    private String note;            //备注
    private Double longitude;       //纬度
    private Double latitude;        //经度
    private String ip;              //ip
    private int uid;
    private int count;

    public Equipment() {
    }

    public Equipment(String eq_id, String datetime, String name, String status, String use_department, String equipment_class, String address, String contact, String contact_tel, String producer, String type, String sim_id, String install_time, String note, Double longitude, Double latitude, String ip, int uid, int count) {
        this.eq_id = eq_id;
        this.datetime = datetime;
        this.name = name;
        this.status = status;
        this.use_department = use_department;
        this.equipment_class = equipment_class;
        this.address = address;
        this.contact = contact;
        this.contact_tel = contact_tel;
        this.producer = producer;
        this.type = type;
        this.sim_id = sim_id;
        this.install_time = install_time;
        this.note = note;
        this.longitude = longitude;
        this.latitude = latitude;
        this.ip = ip;
        this.uid = uid;
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getEq_id() {
        return eq_id;
    }

    public void setEq_id(String eq_id) {
        this.eq_id = eq_id;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUse_department() {
        return use_department;
    }

    public void setUse_department(String use_department) {
        this.use_department = use_department;
    }

    public String getEquipment_class() {
        return equipment_class;
    }

    public void setEquipment_class(String equipment_class) {
        this.equipment_class = equipment_class;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSim_id() {
        return sim_id;
    }

    public void setSim_id(String sim_id) {
        this.sim_id = sim_id;
    }

    public String getInstall_time() {
        return install_time;
    }

    public void setInstall_time(String install_time) {
        this.install_time = install_time;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "Equipment{" +
                "eq_id='" + eq_id + '\'' +
                ", datetime='" + datetime + '\'' +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", use_department='" + use_department + '\'' +
                ", equipment_class='" + equipment_class + '\'' +
                ", address='" + address + '\'' +
                ", contact='" + contact + '\'' +
                ", contact_tel='" + contact_tel + '\'' +
                ", producer='" + producer + '\'' +
                ", type='" + type + '\'' +
                ", sim_id='" + sim_id + '\'' +
                ", install_time='" + install_time + '\'' +
                ", note='" + note + '\'' +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", ip='" + ip + '\'' +
                ", rid=" + uid +
                '}';
    }
}