package com.example.vid1112;

import java.io.Serializable;
public class Mahasiswa implements Serializable{
    private String Nmmhs;
    private String Nimmhs;
    private String key;

    public String getNmmhs() {
        return Nmmhs;
    }

    public void setNmmhs(String nmmhs) {
        Nmmhs = nmmhs;
    }

    public String getNimmhs() {
        return Nimmhs;
    }

    public void setNimmhs(String nimmhs) {
        Nimmhs = nimmhs;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String toString() {
        return" "+Nmmhs+"\n" +
                " "+Nimmhs +"\n";
    }

    public Mahasiswa() {
        Nmmhs = this.Nmmhs;
        Nimmhs = this.Nimmhs;
    }
}
