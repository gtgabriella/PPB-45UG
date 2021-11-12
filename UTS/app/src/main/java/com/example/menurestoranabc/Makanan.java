package com.example.menurestoranabc;

import android.os.Parcel;
import android.os.Parcelable;

public class Makanan implements Parcelable {
    private String nama;
    private String harga;
    private int foto;
    private String detail;

    protected Makanan(Parcel in) {
        nama = in.readString();
        harga = in.readString();
        foto = in.readInt();
        detail = in.readString();
    }

    public static final Creator<Makanan> CREATOR = new Creator<Makanan>() {
        @Override
        public Makanan createFromParcel(Parcel in) {
            return new Makanan(in);
        }

        @Override
        public Makanan[] newArray(int size) {
            return new Makanan[size];
        }
    };

    public Makanan() {

    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(nama);
        parcel.writeString(harga);
        parcel.writeInt(foto);
        parcel.writeString(detail);
    }
}
