package Tubes;

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MenuATM {
	int N=5;
	Nasabah[] a=new Nasabah[N];
	bill[] b=new bill[N];
	
	// Ini Untuk Membaca File Data Nasabah
	void input() {
        try (BufferedReader reader = new BufferedReader(new FileReader("/Users/naufal/Alpro2/Tubes_Alpro2/src/Tubes/nasabah_data.txt"))) {
            String line;
            int i = 0;

            while ((line = reader.readLine()) != null && i < N) {
                String[] data = line.split(",");

                Nasabah x = new Nasabah(data[0], data[1], data[2], data[3], Double.parseDouble(data[4]));

                a[i] = x;
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    } 
	
	void input2() {
        try (BufferedReader reader = new BufferedReader(new FileReader("/Users/naufal/Alpro2/Tubes_Alpro2/src/Tubes/Bill_data.txt"))) {
            String line;
            int i = 0;

            while ((line = reader.readLine()) != null && i < N) {
                String[] data = line.split(",");

                bill bill = new bill(data[1], data[0], Double.parseDouble(data[2]), data[3], data[4]);

                b[i] = bill;
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	
	// Ini untuk menampilkan data nasabah dan saldo nasabah
	void tampilRec() {
		for (int i = 0; i < N ; i++) {
			System.out.println("array ke "+i+":");
			 a[i].tampil();
		}
	}
	
	//prosedur untuk mengecek saldo bersarkan yang udah login
	void CekSaldo(int i){
		 a[i].cekSaldo();
	}
	
	// ini untuk tarik Tunai lain lain
	void Tarik(int i){
		 Scanner sc= new Scanner(System.in);
		 
		 System.out.print("Jumlah: ");
		 double ambil=sc.nextDouble(); 
		 //pengecekan jumlah tarik
		 if (ambil > a[i].saldo) {
			 System.out.println("Saldo anda tidak tidak cukup");
		 } else {
			 a[i].saldo=a[i].saldo-=ambil; 
			 a[i].cekSaldo();
		 }
	}
	
	// prosedur tarik default saldo
	void tarikDefault(int active, double nominal) {
		if (nominal > a[active].saldo) {
	        System.out.println("Saldo tidak mencukupi.");
	    } else {
	    	a[active].saldo = a[active].saldo - nominal;
	    	System.out.println("Penarikan berhasil");
	    	System.out.println("Sisa Saldo anda: Rp "+a[active].saldo);
	    }
	}
	
	// ini untuk Transfer saldo
	void transfer(int active) {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Masukkan nomor rekening tujuan : ");
		String norekTujuan = sc.next();
		
		int ketemu = -1;
		
		for(int i = 0; i < N; i++) {
			if(a[i].norek.equals(norekTujuan)) {
				ketemu = i;
				break;
			}
		}
		
		if(ketemu == -1) {
			System.out.println("Nomor rekening tujuan tidak ditemukan");
		}else{
			System.out.println("Nomor rekening yang di tuju: "+a[ketemu].norek);
			System.out.println("Nama nasabah yang dituju: "+a[ketemu].nama);
			System.out.println("1. Lanjut");
			System.out.println("0. Batalkan");
			int pilih = sc.nextInt();
			switch(pilih) {
				case 1: System.out.print("Silakan masukkan jumlah nominal: ");
						double nominal = sc.nextDouble();
						if(nominal > a[active].saldo) {
							System.out.println("Saldo anda tidak mencukupi");
						}else {
							a[ketemu].saldo += nominal;
							a[active].saldo -= nominal;
							System.out.println("Tranfer berhasil");
							System.out.println("Sisa Saldo anda: "+ a[active].saldo);
							System.out.println("Saldo tujuan: "+a[ketemu].saldo);
						}
						break;
				case 0: mainMenu(active);break;
			}
			
		}
	}
	
	// ini untuk Top up Pulsa
	void topUp(int active) {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Masukkan Nomor HP yang dituju:");
		String noHpTujuan = sc.next();
		
		int ketemu = -1;
		
		for(int i = 0; i < N; i++) {
			if (a[i].NoHP.equals(noHpTujuan)) {
				ketemu = i;
				break;
			}
		}
		
		if(ketemu == -1) {
			System.out.println("Nomor HP yang anda tuju tidak ditemukan");
		}else{
			System.out.print("Masukkan nominal Pulsa: ");
			double nominal = sc.nextDouble();
			if(nominal > a[active].saldo) {
				System.out.println("Saldo anda tidak mencukupi");
			}else {
				a[active].saldo -= nominal;
				System.out.println("Top-Up Pulsa berhasil");
				System.out.println("Sisa saldo anda Rp "+a[active].saldo);
			}
		}
	}
	
	//ini untuk pembayaran
	void pembayaranPDAM(int active) {
	    Scanner sc = new Scanner(System.in);
	
	    System.out.print("Masukkan kode pembayaran: ");
	    String KodeP = sc.next();
	
	    int ketemu = -1;
	
	    for (int i = 0; i < N; i++) {
	    	
	        // Perbandingan dengan kode pembayaran yang sesuai
	    	
	        if (b[i].KodePembayaran.equals(KodeP) && b[i].codeID.equals("P")) {
	            ketemu = i;
	            break;
	        }
	    }
	
	    if (ketemu == -1) {
	        System.out.println("Data tidak ditemukan");
	    } else {
	        System.out.println("Nama: " + b[ketemu].getNama());
	        System.out.println("Jumlah Tagihan: " + b[ketemu].getTagihan());
	        System.out.println("1. Lanjut");
	        System.out.println("0. Batal");
	        int pilih = sc.nextInt();
	        switch (pilih) {
	            case 1:
	                if (b[ketemu].getTagihan() > a[active].getSaldo()) {
	                    System.out.println("Saldo tidak mencukupi");
	                } else {
	                    a[active].setSaldo(a[active].getSaldo() - b[ketemu].getTagihan());
	                    b[ketemu].setStat("Done");
	                    System.out.println("Pembayaran Berhasil");
	                    System.out.println("Sisa saldo anda Rp " + a[active].getSaldo());
	                }
	                break;
	            case 0:
	                mainMenu(active);
	                break;
	        }
	    }
	}
	
	void pembayaranListrik(int active) {
	    Scanner sc = new Scanner(System.in);
	
	    System.out.print("Masukkan kode pembayaran: ");
	    String KodeP = sc.next();
	
	    int ketemu = -1;
	
	    for (int i = 0; i < N; i++) {
	    	
	        // Perbandingan dengan kode pembayaran yang sesuai
	    	
	        if (b[i].KodePembayaran.equals(KodeP) && b[i].codeID.equals("L")) {
	            ketemu = i;
	            break;
	        }
	    }
	
	    if (ketemu == -1) {
	        System.out.println("Data tidak ditemukan");
	    } else {
	        System.out.println("Nama: " + b[ketemu].getNama());
	        System.out.println("Jumlah Tagihan: " + b[ketemu].getTagihan());
	        System.out.println("1. Lanjut");
	        System.out.println("0. Batal");
	        int pilih = sc.nextInt();
	        switch (pilih) {
	            case 1:
	                if (b[ketemu].getTagihan() > a[active].getSaldo()) {
	                    System.out.println("Saldo tidak mencukupi");
	                } else {
	                    a[active].setSaldo(a[active].getSaldo() - b[ketemu].getTagihan());
	                    b[ketemu].setStat("Done");
	                    System.out.println("Pembayaran Berhasil");
	                    System.out.println("Sisa saldo anda Rp " + a[active].getSaldo());
	                }
	                break;
	            case 0:
	                mainMenu(active);
	                break;
	        }
	    }
	}

	
	//ini adalah menu nya setelah login
	void mainMenu(int active) {
		//Memenggil prosedur menu
	    int pilih = Menu();
	    
	    while (pilih != 0) {
	        switch (pilih) {
	            case 1: CekSaldo(active); break;
	            case 2: SubMTarik(active); break;
	            case 3: transfer(active); break;
	            case 4: topUp(active); break;
	            case 5: SubMPembayaran(active); break; 
	            case 0: System.out.println("Terima Kasih");System.exit(pilih); break;
	            default: System.out.print("Pilihan salah! ");
	        }
	        pilih = Menu();
	    }
	}
	
	// Menu
	int Menu(){ 
		 Scanner sc= new Scanner(System.in);
		 
		 System.out.println("Menu: ");
		 System.out.println("1. Cek Saldo ");
		 System.out.println("2. Tarik "); 
		 System.out.println("3. Transfer");
		 System.out.println("4. Top-up Pulsa ");
		 System.out.println("5. Pembayaran ");
		 System.out.println("0. Keluar ");
		 System.out.print("Pilihan anda ");
		 int X=sc.nextInt();
		 return X;
	}
	
	// ini menu tarik dan nominalnya
	void SubMTarik(int active) {
		Scanner sc= new Scanner(System.in);
		 
		 System.out.println("Pilihan Nominal: ");
		 System.out.println("1. Rp 50.000 ");
		 System.out.println("2. Rp 100.000 ");
		 System.out.println("3. Rp 200.000");
		 System.out.println("4. Jumlah Lain-nya");
		 System.out.println("0. Kembali");
		 System.out.print("Pilihan anda ");
		 int subPilih = sc.nextInt();
		 
		 switch (subPilih) {
	         case 1: tarikDefault(active,50000);break;
	         case 2: tarikDefault(active,100000);break;
	         case 3: tarikDefault(active,200000);break;
	         case 4: Tarik(active);break;
	         case 0: mainMenu(active);break;
	         default: System.out.println("Pilihan salah!");
		 }
	}
	
	// ini menu untuk pembayaran
	void SubMPembayaran(int active) {
		Scanner sc= new Scanner(System.in);
		 
		System.out.println("Menu: ");
		System.out.println("1. Pembayran PDAM ");
		System.out.println("2. Pembayaran Listrik ");
		System.out.println("0. Kembali");
		System.out.print("Pilihan anda ");
		int subPilih = sc.nextInt();
		 
		switch (subPilih) {
        	case 1: pembayaranPDAM(active);break;
        	case 2: pembayaranListrik(active);break;
        	case 0: mainMenu(active);break;
        default:
             	System.out.println("Pilihan salah!");
     }
	}
	
	// ini code login 
	int login(){
		 Scanner sc= new Scanner(System.in);
		 
		 System.out.println("Logiiin... ");
		 System.out.print("Norek: ");String norek=sc.next(); 
		 boolean ketemu;
		 int i;
		 int ulang=1;
		 do {
			 System.out.print("Pin: ");String pinku=sc.next();
			 i=0;
			 ketemu=false;
			 while ((ketemu==false)&&(i<N)){
				 if ((a[i].norek.equals(norek)) && (a[i].pin.equals(pinku)))
					 ketemu=true;
				 else
					 i++;
			 } 
			 ulang++;
		 }	while((ketemu==false)&&(ulang<=3));
		 		if (ketemu==true){
		 			System.out.println("Selamat Datang "+ a[i].nama);
		 			return i;
		 		} else {
		 			return -1;
		 		}
	}

	// ini adalah program utama
	public static void main(String[] args) {
		MenuATM A=new MenuATM();
		A.input();
        A.input2();
   
        
        int active = A.login();
        if(active == -1) System.out.println("Terblokir"); else A.mainMenu(active); 
	}
}
