package com.example.sqlitedemo1.model;

public class Student {
    private int mID;
    private String mName;
    private String mAddress;
    private String mPhoneNumber;
    private String nEmail;

    public Student(int mID, String mName, String mAddress, String mPhoneNumber, String nEmail) {
        this.mID = mID;
        this.mName = mName;
        this.mAddress = mAddress;
        this.mPhoneNumber = mPhoneNumber;
        this.nEmail = nEmail;
    }
    public Student(String mName, String mAddress, String mPhoneNumber, String nEmail) {

        this.mName = mName;
        this.mAddress = mAddress;
        this.mPhoneNumber = mPhoneNumber;
        this.nEmail = nEmail;
    }
    public Student() {
    }

    public int getmID() {
        return mID;
    }

    public void setmID(int mID) {
        this.mID = mID;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmAddress() {
        return mAddress;
    }

    public void setmAddress(String mAddress) {
        this.mAddress = mAddress;
    }

    public String getmPhoneNumber() {
        return mPhoneNumber;
    }

    public void setmPhoneNumber(String mPhoneNumber) {
        this.mPhoneNumber = mPhoneNumber;
    }

    public String getnEmail() {
        return nEmail;
    }

    public void setnEmail(String nEmail) {
        this.nEmail = nEmail;
    }

    @Override
    public String toString() {
        return "Student{" +
                "mID=" + mID +
                ", mName='" + mName + '\'' +
                ", mAddress='" + mAddress + '\'' +
                ", mPhoneNumber='" + mPhoneNumber + '\'' +
                ", nEmail='" + nEmail + '\'' +
                '}';
    }
}
