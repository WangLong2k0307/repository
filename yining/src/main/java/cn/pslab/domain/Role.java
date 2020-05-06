package cn.pslab.domain;

import java.io.Serializable;
import java.util.Date;

public class Role implements Serializable {
    private int rid;
    private String rname;
    private String remark;
    private int rli;
    private int aeq;
    private int ueq;
    private int deq;
    private int asub_account;
    private int usub_account;
    private int dsub_account;
    private Date date;


    /*无参构造方法*/
    public Role(){}
    /*有参构造方法*/
    public Role(int rid, String rname, String remark, int rli, int aeq, int ueq, int deq, int asub_account, int usub_account, int dsub_account, Date date){
        this.rid=rid;
        this.rname=rname;
        this.remark=remark;
        this.rli=rli;
        this.aeq=aeq;
        this.ueq=ueq;
        this.deq=deq;
        this.asub_account=asub_account;
        this.usub_account=usub_account;
        this.dsub_account=dsub_account;
        this.date=date;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getRli() {
        return rli;
    }

    public void setRli(int rli) {
        this.rli = rli;
    }

    public int getAeq() {
        return aeq;
    }

    public void setAeq(int aeq) {
        this.aeq = aeq;
    }

    public int getUeq() {
        return ueq;
    }

    public void setUeq(int ueq) {
        this.ueq = ueq;
    }

    public int getDeq() {
        return deq;
    }

    public void setDeq(int deq) {
        this.deq = deq;
    }

    public int getAsub_account() {
        return asub_account;
    }

    public void setAsub_account(int asub_account) {
        this.asub_account = asub_account;
    }

    public int getUsub_account() {
        return usub_account;
    }

    public void setUsub_account(int usub_account) {
        this.usub_account = usub_account;
    }

    public int getDsub_account() {
        return dsub_account;
    }

    public void setDsub_account(int dsub_account) {
        this.dsub_account = dsub_account;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
