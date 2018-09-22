package saiyi.com.gulin_new_wz.ble;

import java.util.Objects;

/**
 * Created by Administrator on 2017/9/15.
 * 蓝牙实体类
 */

public class BleBean {

    private String address;
    private String Name;
    private int   state;





    public BleBean() {

    }

    public BleBean(String name, String address) {
        this.Name = name;
        this.address = address;
    }

    public  String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public   String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BleBean bleBean = (BleBean) o;
        return Objects.equals(address, bleBean.address);
    }

    @Override
    public int hashCode() {

        return Objects.hash(address);
    }


    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("BleBean{");
        sb.append("address='").append(address).append('\'');
        sb.append(", Name='").append(Name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
