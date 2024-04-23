package Tubes;

import java.util.Scanner;

public class billListrik {
	double tagihan;
	String nama,KodePembayaran;
	
	billListrik (String n,String kp, double t) {
		nama = n;
		KodePembayaran = kp;
		tagihan = t;
	}
	
	String getNama() {
		return nama;
	}
	
	void setNama(String n) {
		nama=n;
	}
	
	String getKodeP() {
		return KodePembayaran;
	}
	
	void setKodeP(String kp) {
		KodePembayaran = kp;
	}
	
	double getTagihan() {
		return tagihan;
	}
	
	void setTagihan(double t) {
		tagihan = t;
	}
	
	void tampilTagihan() {
		System.out.println("Nama nasabah "+ nama);
		System.out.println("Jumlah Tagihan Rp." + tagihan);
	}

}
