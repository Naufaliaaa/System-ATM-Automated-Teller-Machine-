package Tubes;

import java.util.Scanner;

public class bill {
	String stat;
	double tagihan;
	String nama,KodePembayaran,codeID;

	bill(String n, String kp, double bill, String id, String st) {
		nama = n;
		KodePembayaran = kp;
		tagihan = bill;
		codeID = id;
		stat = st;
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
	
	String getcodeID() {
		return codeID;
	}
	
	void setCodeID(String id) {
		codeID = id;
	}
	
	String getStat() {
		return stat;
	}
	
	void setStat(String st) {
		stat=st;
	}
	
	void tampilTagihan() {
		System.out.println("Nama nasabah "+ nama);
		System.out.println("Jumlah Tagihan Rp." + tagihan);
		System.out.println(codeID);
		System.out.println(stat);
	}

}
