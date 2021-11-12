package com.example.menurestoranabc;

import java.util.ArrayList;

public class DataMakanan {
    private static String [] namaMakanan = {
            "Mie Aceh",
            "Karee Kameng",
            "Gulaie Keumamah",
            "Bika Ambon",
            "Ayam Pinadar",
            "Anyang Pakis",
            "Bihun Bebek",
            "Rendang",
            "Kacimuih"
    };

    private static String [] hargaMakanan = {
            "Rp25.000,00",
            "Rp30.000,00",
            "Rp35.000,00",
            "Rp25.000,00",
            "Rp20.000,00",
            "Rp15.000,00",
            "Rp60.000,00",
            "Rp20.000,00",
            "Rp25.000,00"
    };

    private static int[] fotoMakanan = {
            R.drawable.mieaceh,
            R.drawable.kareekameng,
            R.drawable.gulaiekeumamah,
            R.drawable.bikaambon,
            R.drawable.ayampinadar,
            R.drawable.anyangpakis,
            R.drawable.bihunbebek,
            R.drawable.rendang,
            R.drawable.kacimuih
    };

    private static String [] detailMakanan = {
            "Provinsi Aceh terkenal dengan Mie Acehnya. Mie kuning tebal dengan irisan daging disajikan dalam sup sejenis kari yang gurih dan pedas. Makanan ini benar-benar kaya bumbu.",
            "Karee Kameng adalah makanan tradisional khas dari Aceh.  Makanan ini merupakan sajian yang paling favorit oleh warga Aceh. Karee Kameng dalam bahasa Indonesia disebut kari kambing adalah lauk yang hampir selalu ada di setiap kedai nasi maupun di berbagai perhelatan.  Di banyak keude bu (kedai nasi) kari ini terpampang di depan kedai dalam kuali atau wajan besar yang selalu dipanaskan.",
            "Masakan ini disebut Gulaie Keumamah atau nama populernya Eungkot Kayee.  Pada dasarnya Keumamah atau ikan kayu khas Aceh adalah daing ikan tongkol yang direbus, kemudian dikeringkan dengan cara diasap. Karena ikan tersebut menjadi kering maka akan keras seperti kayu (eungkot kayee).",
            "Makanan khas di Sumatera Utara khususnya Medan adalah Bika Ambon. Bika Ambon ini enak banget kadang juga dijual dengan rasa lain seperti durian dan keju. Rasanya manis dan lembut.",
            "Ayam Pinadar juga lazim disebut manuk na pinadar. Masakan ini adalah ayam bakar khas Batak Toba yang sungguh memukau rasa pedasnya sangat menggigit karena memakai andaliman. Masakan ini memakai bahan yang uni yaitu hati ayam dan gota (darah ayam).  Sekarang karena berbagai pertimbangan masakan ini tidak menggunakan gota atau darah ayam lagi.",
            "Makanan khas Sumatera Utara ini dibuat dari tanaman pakis atau tanaman paku kemudian diberi bumbu tambahan berupa kelapa hasil penyangraian. Sekilas tampilannya mirip dengan urapan namun penggunaan kelapa sangrai yang mirip serundeng menjadi pembeda antar keduanya.",
            "Di Medan terdapat sebuah kedai sederhana namun punya olahan bernama bihun bebek Asie yang disebut-sebut sebagai olahan bihun bebek paling mahal seantero Medan. Harga satu porsinya saja bisa mencapai 60 ribu rupiah.",
            "Sumatera Barat terkenal dengan makanan Padang yang berasal dari kota Padang. Makanan yang banyak rempahnya ini mempunyai rasa yang kuat. Rendang adalah salah satu masakan Padang yang menjadi favorit banyak orang Indonesiabahkan sampai luar negeri.",
            " Walaupun dibuat dengan bahan dasar singkong yang cenderung “ndeso”, namun setelah menjadi kacimuih maka rasanya akan berbeda. Pembuatannya dilakukan dengan bahan singkong, gula (bisa gula putih bisa gula merah), dan parutan kelapa. Bentuknya terbilang sederhana ketela pohon tadi akan ditumbuk dengan kelapa parut maupun gula."
    };

    static ArrayList<Makanan> getListData(){
        ArrayList<Makanan> list = new ArrayList<>();
        for (int position = 0; position < namaMakanan.length; position++){
            Makanan makanan = new Makanan();
            makanan.setNama(namaMakanan[position]);
            makanan.setHarga(hargaMakanan[position]);
            makanan.setFoto(fotoMakanan[position]);
            makanan.setDetail(detailMakanan[position]);
            list.add(makanan);
        }
        return list;
    }
}