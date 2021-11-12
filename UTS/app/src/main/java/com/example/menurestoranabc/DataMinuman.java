package com.example.menurestoranabc;

import java.util.ArrayList;

public class DataMinuman {
    private static String[] namaMinuman = {
            "Teh Talua",
            "Wedang Uwuh",
            "Bajigur",
            "Sarabba",
            "Bir Pletok",
            "STMJ",
            "Wedang Ronde",
            "Bandrek"
    };

    private static String[] hargaMinuman = {
            "Rp15.000,00",
            "Rp10.000,00",
            "Rp15.000,00",
            "Rp25.000,00",
            "Rp10.000,00",
            "Rp25.000,00",
            "Rp15.000,00",
            "Rp10.000,00"
    };

    private static int[] fotoMinuman = {
            R.drawable.tehtalua,
            R.drawable.wedanguwuh,
            R.drawable.bajigur,
            R.drawable.sarabba,
            R.drawable.birpletok,
            R.drawable.stmj,
            R.drawable.wedangronde,
            R.drawable.bandrek

    };

    private static String[] detailMinunman = {
            "Ini adalah minuman kesehatan yang berasal dari Sumatera Barat. Berbahan dasar minuman teh yang dicampur dengan kuning telur dan rempah-rempah khas daerah Sumatera Barat.",
            "Wedang Uwuh, Wedang dalam Bahasa Jawa berarti minuman ini punya khasiat yang banyak jika dikonsumsi lho. Sebab kandungan rempah-rempah yang kaya dalam minuman ini punya antioksidan tinggi dan dapat mengurangi kadar kolesterol dalam tubuh.",
            "Minuman yang satu ini berasal dari daerah Jawa Barat. Karena rasanya yang manis, legit dan hangat, Bajigur cocok banget dikonsumsi saat malam hari. Konon, minuman ini tercipta dari kebiasaan para petani Sunda yang sering membuat minuman dari gula aren sebelum berangkat ke sawah.",
            "Masyarakat di Bugis, Sulawesi Selatan punya minuman tradisional yang menyegarkan, Sarabba. Minuman ini dibuat dari perpaduan gula aren, jahe, santan dan kuning telur. Pokoknya unik dan membuat badan jadi terasa hangat.",
            "Karena minuman anggur tidak diperbolehkan oleh agama setempat, maka dengan kreatifnya orang-orang Betawi membuat minuman yang kaya dengan bumbu-bumbu dan rempah sebagai alternatifnya. Bir Pletok juga melambangkan kemewahan, kesuksesan dan kemakmuran layaknya minuman anggur.",
            "Udah pada tau belum kalau STMJ itu kepanjangan dari susu, telor, madu, jahe? Nah, minuman yang berasal dari daerah Malang, Jawa Timur ini banyak ditemukan di kawasan Pulau Jawa.",
            "Walaupun Wedang Ronde ini berasal dari Cina yang terkenal dengan nama tang yuan atau dongzhi, namun setelah diperkenalkan oleh para pedagang Cina, minuman tradisional ini banyak ditemui di daerah Jawa Tengah.",
            "Minuman hangat ini sangat cocok dinikmati saat hujan dan cuaca dingin. Minuman khas Jawa Barat ini terbuat dari campuran gula aren, jahe dan serai."

    };

    static ArrayList<Minuman> getListData() {
        ArrayList<Minuman> listMinuman = new ArrayList<>();
        for (int position = 0; position < namaMinuman.length; position++) {
            Minuman minuman = new Minuman();
            minuman.setNama(namaMinuman[position]);
            minuman.setHarga(hargaMinuman[position]);
            minuman.setFoto(fotoMinuman[position]);
            minuman.setDetail(detailMinunman[position]);
            listMinuman.add(minuman);
        }
        return listMinuman;
    }
}
