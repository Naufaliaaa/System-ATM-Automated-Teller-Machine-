package Tubes;

import java.util.Scanner;

public class Nasabah {
	double saldo; 
	String norek,nama,NoHP,pin;
	
		// konstruktor sebuah metode untuk meng inisialisasi
	
	Nasabah(String no,String name,String pn,String hp,double sd) {
		norek = no;
		nama = name;
		pin = pn;
		NoHP= hp;
		saldo = sd;
	}
	
	// function untuk mengambil nomor rekening nasabah
	String getNoRek() {
		return norek;
	}
	
	//function untuk mengubah nomor rekening nasabah
	void setNoRek(String no) {
		norek = no;
	}
	
	//function untuk mengambil nama rekening nasabah
	String getNama() {
		return nama;
	}
	
	//function untuk mengubah nomor rekening nasabah
	void setNama(String namaku) {
		nama = namaku;
	}
	
	String getPin() {
		 return nama;
	}
	
	void setPin(String pn) {
		pin = pn;
	}
	
	String getNoHP() {
		 return NoHP;
	}
	
	void setNoHP(String hp) {
		NoHP = hp;
	}
	
	 
	double getSaldo() {
		return saldo;
	}
	
	void setSaldo(double sd) {
		saldo = sd;
	} 
	
	// prosedur untuk mengambil norek, nama, dan saldo
	void tampil() {
		System.out.println("Nomor Rekening: "+norek);
		System.out.println("Nama: "+nama);
		System.out.println("Saldo: "+saldo);
	}
	
	//prosedur untuk mengecek saldo
	void cekSaldo() {
		System.out.println("Saldo anda Rp "+ saldo);
	}

}