package org.apache.catalina.zookeeper;

import java.io.Serializable;

public class ZkInfo implements Serializable{

    private String address;
    private int    timeOut;
    private int    count;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(int timeOut) {
        this.timeOut = timeOut;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "ZkInfo{" +
                "address='" + address + '\'' +
                ", timeOut=" + timeOut +
                ", count=" + count +
                '}';
    }
}
