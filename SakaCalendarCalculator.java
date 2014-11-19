/*

    SakaCalendarCalculator.java
    Author : Edy Santosa Putra (edy.santosa.p@gmail.com)

    This file is part of sakacalendar.

    sakacalendar is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, version 2.

    sakacalendar is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with sakacalendar.  If not, see <http://www.gnu.org/licenses/>.

    Catatan : 
    Selain yang disebutkan dibawah semua perhitungan dilakukan berdasarkan buku Pokok-Pokok Wariga karangan I.B. Suparta Ardhana.

*/

package com.edysantosa.sakacalendar;
import java.util.Calendar;
import java.util.ArrayList;

public class SakaCalendarCalculator {

	private SakaCalendar pivot;
	private ArrayList<SakaCalendar> pivots;
	
	public SakaCalendarCalculator(){
		pivot = new SakaCalendar();
		setPivots();
	}
	
	/*** Fungsi menambahkan tanggal-tanggal pivot ***/
	private void setPivots() {
		/*
		* Angka pivot digunakan sebagai titik patokan dalam melakukan perhitungan
		* semakin dekat pivot dengan tanggal yang dicari, semakin cepat perhitungan dilakukan
		*/
		pivots = new ArrayList<SakaCalendar>();
		
		SakaCalendar pivotTemp = new SakaCalendar(1991,0,1);//01-01-1991
		pivotTemp.noWuku = 21; pivotTemp.angkaWuku = 143;
		pivotTemp.tahunSaka = 1912; pivotTemp.noSasih = 7; pivotTemp.penanggal = 15; pivotTemp.isPangelong = false; pivotTemp.noNgunaratri = 916; pivotTemp.isNgunaratri = false;
		pivots.add(pivotTemp);
		
		pivotTemp = new SakaCalendar(1992,0,1);//01-01-1992
		pivotTemp.noWuku = 13; pivotTemp.angkaWuku = 88;
		pivotTemp.tahunSaka = 1913; pivotTemp.noSasih = 7; pivotTemp.penanggal = 11; pivotTemp.isPangelong = true; pivotTemp.noNgunaratri = 1281; pivotTemp.isNgunaratri = false;
		pivots.add(pivotTemp);
		
		pivotTemp = new SakaCalendar(1999,0,1);//01-01-1999
		pivotTemp.noWuku = 18; pivotTemp.angkaWuku = 125;
		pivotTemp.tahunSaka = 1920; pivotTemp.noSasih = 8; pivotTemp.penanggal = 13; pivotTemp.isPangelong = false; pivotTemp.noNgunaratri = 58; pivotTemp.isNgunaratri = false;
		pivots.add(pivotTemp);
		
		pivotTemp = new SakaCalendar(2000,0,1);//01-01-2000
		pivotTemp.noWuku = 10; pivotTemp.angkaWuku = 70;
		pivotTemp.tahunSaka = 1921; pivotTemp.noSasih = 7; pivotTemp.penanggal = 10; pivotTemp.isPangelong = true; pivotTemp.noNgunaratri = 424; pivotTemp.isNgunaratri = false;
		pivots.add(pivotTemp);
		
		pivotTemp = new SakaCalendar(2002,0,1);//01-01-2002
		pivotTemp.noWuku = 25 ;pivotTemp.angkaWuku = 171;
		pivotTemp.tahunSaka = 1923; pivotTemp.noSasih = 7; pivotTemp.penanggal = 3; pivotTemp.isPangelong = true; pivotTemp.noNgunaratri = 1155; pivotTemp.isNgunaratri = false;
		pivots.add(pivotTemp);
		
		pivotTemp = new SakaCalendar(2003,0,1);//01-01-2003
		pivotTemp.noWuku = 17 ;pivotTemp.angkaWuku = 116;
		pivotTemp.tahunSaka = 1924; pivotTemp.noSasih = 7; pivotTemp.penanggal = 14; pivotTemp.isPangelong = true; pivotTemp.noNgunaratri = 1520; pivotTemp.isNgunaratri = false;
		pivots.add(pivotTemp);
		
		pivotTemp = new SakaCalendar(2012,5,17);//17-05-2012
		pivotTemp.noWuku = 1;pivotTemp.angkaWuku = 1;
		pivotTemp.tahunSaka = 1934; pivotTemp.noSasih = 12; pivotTemp.penanggal = 13; pivotTemp.isPangelong = true; pivotTemp.noNgunaratri = 1195; pivotTemp.isNgunaratri = false;
		pivots.add(pivotTemp);
		
		pivotTemp = new SakaCalendar(2101,0,1);//01-01-2101
		pivotTemp.noWuku = 30;pivotTemp.angkaWuku = 210;
		pivotTemp.tahunSaka = 2022; pivotTemp.noSasih = 7; pivotTemp.penanggal = 1; pivotTemp.isPangelong = false; pivotTemp.noNgunaratri = 1404; pivotTemp.isNgunaratri = false;
		pivots.add(pivotTemp);
		
	}
	
	/*** Fungsi mendapatkan pivot terdekat dari parameter SakaCalendar ***/
	private SakaCalendar getPivot(SakaCalendar closestOf){
		
		int min = Integer.MAX_VALUE;	
		SakaCalendar pivotTerdekat = new SakaCalendar();
		
		try{			
			for (SakaCalendar pivot : pivots) {
				final int diff = Math.abs(pivot.get(Calendar.YEAR) - closestOf.get(Calendar.YEAR));

					if (diff < min) {
						min = diff;
						pivotTerdekat = pivot;
					}
			} 
		}catch(Exception ex){System.out.println(ex);}

		return pivotTerdekat;
	}
	
	/*** Fungsi menghitung perbedaan hari antara 2 SakaCalendar ***/
	private int getDateDiff( SakaCalendar d1, SakaCalendar d2) {
		
		boolean mundur = false;
		if( d1.after(d2) ) {    // make sure d1 < d2, else swap them  
			SakaCalendar temp = d1;  
			d1 = d2;  
			d2 = temp;
			mundur = true;
		}  
		  
		int days = d2.get(java.util.Calendar.DAY_OF_YEAR) - d1.get(java.util.Calendar.DAY_OF_YEAR);
		int y2 = d2.get(java.util.Calendar.YEAR);
		if (d1.get(java.util.Calendar.YEAR) != y2) {
			d1 = (SakaCalendar)d1.clone();
			do {
				days += d1.getActualMaximum(java.util.Calendar.DAY_OF_YEAR);
				d1.add(java.util.Calendar.YEAR, 1);
			} while (d1.get(java.util.Calendar.YEAR) != y2);
		}
		
		if (mundur){
			return -days;
		}else{
			return days;
		}
	}
	
	/*** Fungsi menghitung pawukon ***/
	public SakaCalendar hitungWuku(SakaCalendar tgl){
		
		pivot = getPivot(tgl);
		
		int bedaHari = getDateDiff(pivot,tgl); 
		if (bedaHari >= 0){
			tgl.angkaWuku = (pivot.angkaWuku + bedaHari)%210 ;
		}else{
			tgl.angkaWuku = 210 - ((-(pivot.angkaWuku + bedaHari))%210) ;
		}
		if (tgl.angkaWuku == 0){ tgl.angkaWuku = 210; }
		tgl.noWuku = (int) Math.ceil(tgl.angkaWuku /7.0);
		if (tgl.noWuku > 30) { tgl.noWuku %=30; } 
		if (tgl.noWuku == 0) { tgl.noWuku =30; }
		
		switch (tgl.noWuku) {
		case 1: //Sinta
			tgl.uripWuku = 7;
			break;
		case 2: //Landep
			tgl.uripWuku = 1;
			break;
		case 3: //Ukir
			tgl.uripWuku = 4;
			break;
		case 4: //Kulantir
			tgl.uripWuku = 6;
			break;
		case 5: //Tolu
			tgl.uripWuku = 5;
			break;
		case 6: //Gumbreg
			tgl.uripWuku = 8;
			break;
		case 7: //Wariga 
			tgl.uripWuku = 9;
			break;
		case 8: //Warigadean
			tgl.uripWuku = 3;
			break;
		case 9: //Julungwangi
			tgl.uripWuku = 7;
			break;
		case 10: //Sungsang
			tgl.uripWuku = 1;
			break;
		case 11: //Dunggulan
			tgl.uripWuku = 4;
			break;
		case 12: //Kuningan
			tgl.uripWuku = 6;
			break;
		case 13: //Langkir
			tgl.uripWuku = 5;
			break;
		case 14: //Medangsia
			tgl.uripWuku = 8;
			break;
		case 15: //Pujut
			tgl.uripWuku = 9;
			break;
		case 16: //Pahang
			tgl.uripWuku = 3;
			break;
		case 17: //Krulut
			tgl.uripWuku = 7;
			break;
		case 18: //Merakih
			tgl.uripWuku = 1;
			break;
		case 19: //Tambir
			tgl.uripWuku = 4;
			break;
		case 20: //Medangkungan
			tgl.uripWuku = 6;
			break;
		case 21: //Matal
			tgl.uripWuku = 5;
			break;
		case 22: //Uye
			tgl.uripWuku = 8;
			break;
		case 23: //Menail
			tgl.uripWuku = 9;
			break;
		case 24: //Perangbakat
			tgl.uripWuku = 3;
			break;
		case 25: //Bala
			tgl.uripWuku = 7;
			break;
		case 26: //Ugu
			tgl.uripWuku = 1;
			break;
		case 27: //Wayang
			tgl.uripWuku = 4;
			break;
		case 28: //kelawu
			tgl.uripWuku = 6;
			break;
		case 29: //Dukut
			tgl.uripWuku = 5;
			break;
		case 30: //Watugunung
			tgl.uripWuku = 8;
			break;
		default:
			break;
		}
		
		return tgl;
	}
	
	/*** Fungsi menghitung wewaran ***/
	public SakaCalendar hitungWewaran(SakaCalendar tgl){
	
		pivot = getPivot(tgl);
		
		/*
		* Perhitungan saptawara hanya mengecek DAY_OF_WEEK dari SakaCalendar
		*/
		switch (tgl.get(Calendar.DAY_OF_WEEK)) {
		case 1: //Redite
			tgl.noSaptawara = 0;
			tgl.uripSaptawara = 5;
			break;
		case 2: //Soma
			tgl.noSaptawara = 1;
			tgl.uripSaptawara = 4;
			break;
		case 3: //Anggara
			tgl.noSaptawara = 2;
			tgl.uripSaptawara = 3;
			break;
		case 4: //Buda
			tgl.noSaptawara = 3;
			tgl.uripSaptawara = 7;
			break;
		case 5: //Wrespati
			tgl.noSaptawara = 4;
			tgl.uripSaptawara = 8;
			break;
		case 6: //Sukra
			tgl.noSaptawara = 5;
			tgl.uripSaptawara = 6;
			break;
		case 7: //Saniscara
			tgl.noSaptawara = 6;
			tgl.uripSaptawara = 9;
			break;
		default:
			break;
		}
		
		
		/*
		* Perhitungan pancawara 
		* Menggunakan rumus dari babadbali.com : "Murni aritmatika, modulus 5 dari angka pawukon menghasilkan 0=Umanis, 1=Paing, 2=Pon, 3=Wage, 4=Kliwon.
		* Pada SakaCalendar menjadi : 
		* 0 + 1 = 1 Umanis
		* 1 + 1 = 2 Paing
		* 2 + 1 = 3 Pon
		* 3 + 1 = 4 Wage	
		* 4 + 1 = 5 Kliwon
		*/
		int hasil;
		hasil = (tgl.angkaWuku % 5) + 1 ;
		tgl.noPancawara = hasil;
		// mendapatkan urip pancawara
		switch (tgl.noPancawara) {
		case 1:
			tgl.uripPancawara = 5;break;
		case 2:
			tgl.uripPancawara = 9;break;
		case 3:
			tgl.uripPancawara = 7;break;
		case 4:
			tgl.uripPancawara = 4;break;
		case 5:
			tgl.uripPancawara = 8;break;
		default:break;
		}
		
		
		/*
		* Perhitungan triwara
		* Menggunakan rumus dari babadbali.com : "Perhitungan triwara murni aritmatika, berdaur dari ketiganya. Angka Pawukon dibagi 3, jika sisanya (modulus) 1 adalah Pasah, 2 adalah Beteng, 0 adalah Kajeng"
		* Pada SakaCalendar menjadi : 
		* 1 Pasah
		* 2 Beteng
		* 0 -> 3 kajeng
		*/
		hasil = tgl.angkaWuku % 3;
		if (hasil == 0){hasil = 3;}
		tgl.noTriwara=hasil;
		
		/*
		* Perhitungan ekawara
		* Pada SakaCalendar : 
		* 1 Luang
		* 2 Bukan luang (kosong)
		*/
		hasil = (tgl.uripPancawara + tgl.uripSaptawara) % 2;
		if (hasil!=0){
			tgl.noEkawara = 1; //Jika tidak habis dibagi 2 maka luang
		}else{
			tgl.noEkawara = 0; //Jika habis dibagi 2 maka bukan luang 
		}
		
		/*
		* Perhitungan dwiwara
		* Pada SakaCalendar : 
		* 1 Menga
		* 2 Pepet
		*/
		hasil = (tgl.uripPancawara + tgl.uripSaptawara) % 2;
		if (hasil==0){
			tgl.noDwiwara = 1; //Jika habis dibagi 2 maka menga
		}else{
			tgl.noDwiwara = 2; //Jika tidak habis dibagi 2 maka pepet
		}
		
		/*
		* Perhitungan caturwara
		* Pada wuku dengan angka wuku 71,72,73 caturwara tetap jaya yang disebut dengan Jayatiga
		* Pada SakaCalendar : 
		* 1 Sri
		* 2 Laba
		* 3 Jaya
		* 0 -> Menala
		*/
		if (tgl.angkaWuku == 71 || tgl.angkaWuku == 72 || tgl.angkaWuku == 73){	
			hasil = 3;
		}else if (tgl.angkaWuku <= 70){
			hasil = tgl.angkaWuku % 4;
		}else{
			hasil = (tgl.angkaWuku + 2) % 4;
		}
		if (hasil == 0){hasil = 4;}
		tgl.noCaturwara = hasil;
		
		
		/*
		* Perhitungan sadwara
		* Pada SakaCalendar : 
		* 1 Tungleh
		* 2 Aryang
		* 3 Urukung
		* 4 Paniron
		* 5 Was
		* 0 -> 6 Maulu
		*/
		hasil = tgl.angkaWuku % 6;
		if (hasil == 0){hasil = 6;}
		tgl.noSadwara = hasil;
		
		
		/*
		* Perhitungan astawara
		* Pada wuku dengan angka wuku 71,72,73 astawara tetap kala yang disebut dengan Kalatiga
		* Pada SakaCalendar : 
		* 1 Sri
		* 2 Indra
		* 3 Guru
		* 4 Yama
		* 5 Ludra
		* 6 Brahma
		* 7 kala
		* 0 -> 8 Uma
		*/
		if (tgl.angkaWuku == 71 || tgl.angkaWuku == 72 || tgl.angkaWuku == 73){	
			hasil = 7;
		}else if (tgl.angkaWuku <= 70){
			hasil = tgl.angkaWuku % 8;
		}else{
			hasil = (tgl.angkaWuku + 6) % 8;
		}
		if (hasil == 0){hasil = 8;}
		tgl.noAstawara = hasil;
		
		
		/*
		* Perhitungan sangawara
		* Pada wuku dengan angka wuku 1-4 sangawara tetap dangu yang disebut dengan Caturdangu
		* Pada SakaCalendar : 
		* 1 Dangu
		* 2 Jangur
		* 3 Gigis
		* 4 Nohan
		* 5 Ogan
		* 6 Erangan
		* 7 Urungan
		* 8 Tulus
		* 0 -> 9 Dadi
		*/
		if (tgl.angkaWuku <= 4){
			hasil = 1 ;
		}else{
			hasil = (tgl.angkaWuku + 6) % 9;
		}
		if (hasil == 0){hasil = 9;}
		tgl.noSangawara = hasil;
		
		/*
		* Perhitungan dasawara 
		* Pada SakaCalendar menjadi : 
		* 0 + 1 = 1 Pandita
		* 1 + 1 = 2 Pati
		* 2 + 1 = 3 Suka
		* 3 + 1 = 4 Duka	
		* 4 + 1 = 5 Sri
		* 5 + 1 = 6 Manuh
		* 6 + 1 = 7 Manusa
		* 7 + 1 = 8 Raja
		* 8 + 1 = 9 Dewa
		* 9 + 1 = 10 Raksasa
		*/
		hasil = (((tgl.uripPancawara + tgl.uripSaptawara) % 10)+1);
		tgl.noDasawara = hasil;
		
		return tgl;
	}
	
	/*** Fungsi menghitung ingkel ***/
	public int hitungIngkel(SakaCalendar tgl){
		/*
		* Pada SakaCalendar : 
		* 1 Wong
		* 2 Sato
		* 3 Mina
		* 4 Manuk
		* 5 Taru
		* 6 Buku
		*/
		int noIngkel;	
		if (tgl.noWuku==1||tgl.noWuku==7||tgl.noWuku==13||tgl.noWuku==19||tgl.noWuku==25){
			noIngkel = 1;
		} else if (tgl.noWuku==2||tgl.noWuku==8||tgl.noWuku==14||tgl.noWuku==20||tgl.noWuku==26){
			noIngkel = 2;
		} else if (tgl.noWuku==3||tgl.noWuku==9||tgl.noWuku==15||tgl.noWuku==21||tgl.noWuku==27){
			noIngkel = 3;
		} else if (tgl.noWuku==4||tgl.noWuku==10||tgl.noWuku==16||tgl.noWuku==22||tgl.noWuku==28){
			noIngkel = 4;
		} else if (tgl.noWuku==5||tgl.noWuku==11||tgl.noWuku==17||tgl.noWuku==23||tgl.noWuku==29){
			noIngkel = 5;
		} else {
			noIngkel = 6;
		}
		
		return noIngkel;
	}
	
	/*** Fungsi menghitung jejepan ***/
	public int hitungJejepan(SakaCalendar tgl){
		/*
		* Pada SakaCalendar : 
		* 1 Mina
		* 2 taru
		* 3 Sato
		* 4 Patra
		* 5 Wong
		* 0 -> 6 Paksi
		*/
		int noJejepan;
		noJejepan = tgl.angkaWuku % 6;
		if (noJejepan == 0){noJejepan = 6;}
		return noJejepan;
	}
	
	/*** Fungsi menghitung pewatekan alit ***/
	public int hitungWatekAlit(SakaCalendar tgl){
		
		/*
		* Pada SakaCalendar : 
		* 1 Uler
		* 2 Gajah
		* 3 Lembu
		* 0 -> 4 Lintah
		*/
		int noWatekAlit;
		noWatekAlit = (tgl.uripPancawara + tgl.uripSaptawara) % 4;
		if (noWatekAlit == 0){noWatekAlit = 4;}
		
		return noWatekAlit;
	}
	
	/*** Fungsi menghitung pewatekan madya ***/
	public int hitungWatekMadya(SakaCalendar tgl){
		
		/*
		* Pada SakaCalendar : 
		* 1 Gajah
		* 2 Watu
		* 3 Buta
		* 4 Suku
		* 0 ->5 Wong
		*/
		int noWatekMadya;
		noWatekMadya = (tgl.uripPancawara + tgl.uripSaptawara) % 5;
		if (noWatekMadya == 0){noWatekMadya = 5;}
		
		return noWatekMadya;
	}
	
	/*** Fungsi menghitung eka jala rsi ***/
	public int hitungEkaJalaRsi(SakaCalendar tgl){
	
		/*
		* Pada SakaCalendar : 
		* 1	Bagna mapasah
		* 2	Bahu putra
		* 3	Buat astawa
		* 4	Buat lara
		* 5	Buat merang
		* 6	Buat sebet
		* 7	Buat kingking
		* 8	Buat suka
		* 9	Dahat kingking
		* 10 Kamaranan
		* 11 Kamretaan
		* 12 Kasobagian
		* 13 Kinasihan amreta
		* 14 Kinasihan jana
		* 15 Langgeng kayohanaan
		* 16 Lewih bagia
		* 17 Manggih bagia
		* 18 Manggih suka
		* 19 Patining amreta
		* 20 Rahayu
		* 21 Sidha kasobagian
		* 22 Subagia
		* 23 Suka kapanggih
		* 24 Suka pinanggih
		* 25 Suka rahayu
		* 26 Tininggaling suka
		* 27 Wredhi putra
		* 28 Wredhi sarwa mule
		*/
		
		int noEkaJalaRsi=0;
		switch(tgl.noWuku){
		case 1:
			switch(tgl.noSaptawara){
			case 0 : noEkaJalaRsi = 24;break;
			case 1 : noEkaJalaRsi = 8;break;
			case 2 : noEkaJalaRsi = 18;break;
			case 3 : noEkaJalaRsi = 8;break;
			case 4 : noEkaJalaRsi = 24;break;
			case 5 : noEkaJalaRsi = 24;break;
			case 6 : noEkaJalaRsi = 18;break;
			}
			break;
			
		case 2:
			switch(tgl.noSaptawara){
			case 0 : noEkaJalaRsi = 10;break;
			case 1 : noEkaJalaRsi = 8;break;
			case 2 : noEkaJalaRsi = 14;break;
			case 3 : noEkaJalaRsi = 27;break;
			case 4 : noEkaJalaRsi = 25;break; 
			case 5 : noEkaJalaRsi = 24;break;
			case 6 : noEkaJalaRsi = 21;break;
			}
			break;
			
		case 3:
			switch(tgl.noSaptawara){
			case 0 : noEkaJalaRsi = 14;break;
			case 1 : noEkaJalaRsi = 8;break;
			case 2 : noEkaJalaRsi = 14;break;
			case 3 : noEkaJalaRsi = 26;break;
			case 4 : noEkaJalaRsi = 20;break;
			case 5 : noEkaJalaRsi = 6;break;
			case 6 : noEkaJalaRsi = 3;break;
			}
			break;
			
		case 4:
			switch(tgl.noSaptawara){
			case 0 : noEkaJalaRsi = 15;break;
			case 1 : noEkaJalaRsi = 27;break;
			case 2 : noEkaJalaRsi = 18;break;
			case 3 : noEkaJalaRsi = 21;break;
			case 4 : noEkaJalaRsi = 26;break;
			case 5 : noEkaJalaRsi = 23;break;
			case 6 : noEkaJalaRsi = 1;break;
			}
			break;
			
		case 5:
			switch(tgl.noSaptawara){
			case 0 : noEkaJalaRsi = 11;break;
			case 1 : noEkaJalaRsi = 6;break;
			case 2 : noEkaJalaRsi = 16;break;
			case 3 : noEkaJalaRsi = 24;break;
			case 4 : noEkaJalaRsi = 8;break;
			case 5 : noEkaJalaRsi = 18;break;
			case 6 : noEkaJalaRsi = 24;break;
			}
			break;
			
			
		case 6:
			switch(tgl.noSaptawara){
			case 0 : noEkaJalaRsi = 18;break;
			case 1 : noEkaJalaRsi = 26;break;
			case 2 : noEkaJalaRsi = 5;break;
			case 3 : noEkaJalaRsi = 24;break;
			case 4 : noEkaJalaRsi = 3;break;
			case 5 : noEkaJalaRsi = 3;break;
			case 6 : noEkaJalaRsi = 3;break;
			}
			break;
			
			
		case 7:
			switch(tgl.noSaptawara){
			case 0 : noEkaJalaRsi = 13;break;
			case 1 : noEkaJalaRsi = 13;break;
			case 2 : noEkaJalaRsi = 5;break;
			case 3 : noEkaJalaRsi = 15;break;
			case 4 : noEkaJalaRsi = 13;break;
			case 5 : noEkaJalaRsi = 27;break;
			case 6 : noEkaJalaRsi = 27;break;
			}
			break;
			
			
		case 8:
			switch(tgl.noSaptawara){
			case 0 : noEkaJalaRsi = 2;break;
			case 1 : noEkaJalaRsi = 24;break;
			case 2 : noEkaJalaRsi = 24;break;
			case 3 : noEkaJalaRsi = 16;break;
			case 4 : noEkaJalaRsi = 26;break;
			case 5 : noEkaJalaRsi = 16;break;
			case 6 : noEkaJalaRsi = 6;break;
			}
			break;
			
		case 9:
			switch(tgl.noSaptawara){
			case 0 : noEkaJalaRsi = 10;break;
			case 1 : noEkaJalaRsi = 26;break;
			case 2 : noEkaJalaRsi = 19;break;
			case 3 : noEkaJalaRsi = 26;break;
			case 4 : noEkaJalaRsi = 12;break;
			case 5 : noEkaJalaRsi = 16;break;
			case 6 : noEkaJalaRsi = 22;break;
			}
			break;
		
		case 10:
			switch(tgl.noSaptawara){
			case 0 : noEkaJalaRsi = 26;break;
			case 1 : noEkaJalaRsi = 26;break;
			case 2 : noEkaJalaRsi = 13;break;
			case 3 : noEkaJalaRsi = 1;break;
			case 4 : noEkaJalaRsi = 18;break;
			case 5 : noEkaJalaRsi = 14;break;
			case 6 : noEkaJalaRsi = 1;break;
			}
			break;
			
		case 11:
			switch(tgl.noSaptawara){
			case 0 : noEkaJalaRsi = 16;break;
			case 1 : noEkaJalaRsi = 24;break;
			case 2 : noEkaJalaRsi = 13;break;
			case 3 : noEkaJalaRsi = 8;break;
			case 4 : noEkaJalaRsi = 17;break;
			case 5 : noEkaJalaRsi = 26;break;
			case 6 : noEkaJalaRsi = 19;break;
			}
			break;
			
		case 12:
			switch(tgl.noSaptawara){
			case 0 : noEkaJalaRsi = 25;break;
			case 1 : noEkaJalaRsi = 13;break;
			case 2 : noEkaJalaRsi = 13;break;
			case 3 : noEkaJalaRsi = 6;break;
			case 4 : noEkaJalaRsi = 8;break;
			case 5 : noEkaJalaRsi = 6;break;
			case 6 : noEkaJalaRsi = 27;break;
			}
			break;
			
		case 13:
			switch(tgl.noSaptawara){
			case 0 : noEkaJalaRsi = 8;break;
			case 1 : noEkaJalaRsi = 6;break;
			case 2 : noEkaJalaRsi = 13;break;
			case 3 : noEkaJalaRsi = 8;break;
			case 4 : noEkaJalaRsi = 26;break;
			case 5 : noEkaJalaRsi = 3;break;
			case 6 : noEkaJalaRsi = 13;break;
			}
			break;
			
		
		case 14:
			switch(tgl.noSaptawara){
			case 0 : noEkaJalaRsi = 26;break;
			case 1 : noEkaJalaRsi = 26;break;
			case 2 : noEkaJalaRsi = 15;break;
			case 3 : noEkaJalaRsi = 16;break;
			case 4 : noEkaJalaRsi = 27;break;
			case 5 : noEkaJalaRsi = 8;break;
			case 6 : noEkaJalaRsi = 13;break;
			}
			break;
			
		case 15:
			switch(tgl.noSaptawara){
			case 0 : noEkaJalaRsi = 21;break;
			case 1 : noEkaJalaRsi = 8;break;
			case 2 : noEkaJalaRsi = 6;break;
			case 3 : noEkaJalaRsi = 26;break;
			case 4 : noEkaJalaRsi = 26;break;
			case 5 : noEkaJalaRsi = 6;break;
			case 6 : noEkaJalaRsi = 14;break;
			}
			break;
			
		case 16:
			switch(tgl.noSaptawara){
			case 0 : noEkaJalaRsi = 26;break;
			case 1 : noEkaJalaRsi = 18;break;
			case 2 : noEkaJalaRsi = 14;break;
			case 3 : noEkaJalaRsi = 24;break;
			case 4 : noEkaJalaRsi = 6;break;
			case 5 : noEkaJalaRsi = 27;break;
			case 6 : noEkaJalaRsi = 21;break;
			}
			break;
			
		case 17:
			switch(tgl.noSaptawara){
			case 0 : noEkaJalaRsi = 26;break;
			case 1 : noEkaJalaRsi = 26;break;
			case 2 : noEkaJalaRsi = 24;break;
			case 3 : noEkaJalaRsi = 8;break;
			case 4 : noEkaJalaRsi = 19;break;
			case 5 : noEkaJalaRsi = 19;break;
			case 6 : noEkaJalaRsi = 18;break;
			}
			break;
			
		case 18:
			switch(tgl.noSaptawara){
			case 0 : noEkaJalaRsi = 8;break;
			case 1 : noEkaJalaRsi = 18;break;
			case 2 : noEkaJalaRsi = 8;break;
			case 3 : noEkaJalaRsi = 5;break;
			case 4 : noEkaJalaRsi = 27;break;
			case 5 : noEkaJalaRsi = 18;break;
			case 6 : noEkaJalaRsi = 6;break;
			}
			break;
		
		case 19:
			switch(tgl.noSaptawara){
			case 0 : noEkaJalaRsi = 10;break;
			case 1 : noEkaJalaRsi = 13;break;
			case 2 : noEkaJalaRsi = 13;break;
			case 3 : noEkaJalaRsi = 14;break;
			case 4 : noEkaJalaRsi = 26;break;
			case 5 : noEkaJalaRsi = 19;break;
			case 6 : noEkaJalaRsi = 19;break;
			}
			break;
			
		case 20:
			switch(tgl.noSaptawara){
			case 0 : noEkaJalaRsi = 6;break;
			case 1 : noEkaJalaRsi = 3;break;
			case 2 : noEkaJalaRsi = 26;break;
			case 3 : noEkaJalaRsi = 26;break;
			case 4 : noEkaJalaRsi = 3;break;
			case 5 : noEkaJalaRsi = 26;break;
			case 6 : noEkaJalaRsi = 18;break;
			}
			break;
			
		case 21:
			switch(tgl.noSaptawara){
			case 0 : noEkaJalaRsi = 21;break;
			case 1 : noEkaJalaRsi = 15;break;
			case 2 : noEkaJalaRsi = 28;break;
			case 3 : noEkaJalaRsi = 24;break;
			case 4 : noEkaJalaRsi = 18;break;
			case 5 : noEkaJalaRsi = 9;break;
			case 6 : noEkaJalaRsi = 26;break;
			}
			break;
			
		case 22:
			switch(tgl.noSaptawara){
			case 0 : noEkaJalaRsi = 18;break;
			case 1 : noEkaJalaRsi = 6;break;
			case 2 : noEkaJalaRsi = 18;break;
			case 3 : noEkaJalaRsi = 8;break;
			case 4 : noEkaJalaRsi = 7;break;
			case 5 : noEkaJalaRsi = 16;break;
			case 6 : noEkaJalaRsi = 19;break;
			}
			break;
		
		case 23:
			switch(tgl.noSaptawara){
			case 0 : noEkaJalaRsi = 26;break;
			case 1 : noEkaJalaRsi = 3;break;
			case 2 : noEkaJalaRsi = 8;break;
			case 3 : noEkaJalaRsi = 14;break;
			case 4 : noEkaJalaRsi = 26;break;
			case 5 : noEkaJalaRsi = 21;break;
			case 6 : noEkaJalaRsi = 8;break;
			}
			break;
			
		case 24:
			switch(tgl.noSaptawara){
			case 0 : noEkaJalaRsi = 16;break;
			case 1 : noEkaJalaRsi = 16;break;
			case 2 : noEkaJalaRsi = 24;break;
			case 3 : noEkaJalaRsi = 8;break;
			case 4 : noEkaJalaRsi = 9;break;
			case 5 : noEkaJalaRsi = 25;break;
			case 6 : noEkaJalaRsi = 3;break;
			}
			break;
			
		case 25:
			switch(tgl.noSaptawara){
			case 0 : noEkaJalaRsi = 13;break;
			case 1 : noEkaJalaRsi = 10;break;
			case 2 : noEkaJalaRsi = 25;break;
			case 3 : noEkaJalaRsi = 25;break;
			case 4 : noEkaJalaRsi = 18;break;
			case 5 : noEkaJalaRsi = 25;break;
			case 6 : noEkaJalaRsi = 21;break;
			}
			break;
			
		case 26:
			switch(tgl.noSaptawara){
			case 0 : noEkaJalaRsi = 8;break;
			case 1 : noEkaJalaRsi = 13;break;
			case 2 : noEkaJalaRsi = 13;break;
			case 3 : noEkaJalaRsi = 15;break;
			case 4 : noEkaJalaRsi = 19;break;
			case 5 : noEkaJalaRsi = 26;break;
			case 6 : noEkaJalaRsi = 21;break;
			}
			break;
			
		case 27:
			switch(tgl.noSaptawara){
			case 0 : noEkaJalaRsi = 5;break;
			case 1 : noEkaJalaRsi = 19;break;
			case 2 : noEkaJalaRsi = 5;break;
			case 3 : noEkaJalaRsi = 21;break;
			case 4 : noEkaJalaRsi = 27;break;
			case 5 : noEkaJalaRsi = 13;break;
			case 6 : noEkaJalaRsi = 24;break;
			}
			break;
			
		case 28:
			switch(tgl.noSaptawara){
			case 0 : noEkaJalaRsi = 19;break;
			case 1 : noEkaJalaRsi = 18;break;
			case 2 : noEkaJalaRsi = 18;break;
			case 3 : noEkaJalaRsi = 26;break;
			case 4 : noEkaJalaRsi = 16;break;
			case 5 : noEkaJalaRsi = 3;break;
			case 6 : noEkaJalaRsi = 25;break;
			}
			break;
			
		case 29:
			switch(tgl.noSaptawara){
			case 0 : noEkaJalaRsi = 4;break;
			case 1 : noEkaJalaRsi = 3;break;
			case 2 : noEkaJalaRsi = 24;break;
			case 3 : noEkaJalaRsi = 26;break;
			case 4 : noEkaJalaRsi = 19;break;
			case 5 : noEkaJalaRsi = 26;break;
			case 6 : noEkaJalaRsi = 21;break;
			}
			break;
			
		case 30:
			switch(tgl.noSaptawara){
			case 0 : noEkaJalaRsi = 15;break;
			case 1 : noEkaJalaRsi = 4;break;
			case 2 : noEkaJalaRsi = 3;break;
			case 3 : noEkaJalaRsi = 26;break;
			case 4 : noEkaJalaRsi = 8;break;
			case 5 : noEkaJalaRsi = 26;break;
			case 6 : noEkaJalaRsi = 18;break;
			}
			break;
		}
		
		return noEkaJalaRsi;
	}
	
	/*** Fungsi menghitung palalintangan ***/
	public int hitungLintang(SakaCalendar tgl){
		
		/*
		* Pada SakaCalendar : 
		* 1 Gajah
		* 2 Kiriman
		* 3 Jong Sarat
		* 4 Atiwa-tiwa
		* 5 Sangka Tikel
		* 6 Bubu Bolong
		* 7 Sugenge
		* 8 Uluku/Tenggala
		* 9 Pedati
		* 10 Kuda
		* 11 Gajah Mina
		* 12 Bade
		* 13 Magelut
		* 14 Pagelangan
		* 15 Kala Sungsang
		* 16 Kukus
		* 17 Asu
		* 18 Kartika
		* 19 Naga
		* 20 Banak Angerem
		* 21 Hru/Panah
		* 22 Patrem
		* 23 Lembu
		* 24 Depat/Sidamalung
		* 25 Tangis
		* 26 Salah Ukur
		* 27 Perahu Pegat
		* 28 Puwuh Atarung
		* 29 Lawean/Goang
		* 30 Kelapa
		* 31 Yuyu
		* 32 Lumbung
		* 33 Kumbha
		* 34 Udang
		* 35 Begoong
		*/
		
		int noLintang=0;
		switch(tgl.noSaptawara){
		case 0:
			switch(tgl.noPancawara){
			case 1 : noLintang = 15;break;
			case 2 : noLintang = 1;break;
			case 3 : noLintang = 22;break;
			case 4 : noLintang = 8;break;
			case 5 : noLintang = 29;break;
			}
			break;
		case 1:
			switch(tgl.noPancawara){
			case 1 : noLintang = 30;break;
			case 2 : noLintang = 16;break;
			case 3 : noLintang = 2;break;
			case 4 : noLintang = 23;break;
			case 5 : noLintang = 9;break;
			}
			break;
		case 2:
			switch(tgl.noPancawara){
			case 1 : noLintang = 10;break;
			case 2 : noLintang = 31;break;
			case 3 : noLintang = 17;break;
			case 4 : noLintang = 23;break;
			case 5 : noLintang = 24;break;
			}
			break;
		case 3:
			switch(tgl.noPancawara){
			case 1 : noLintang = 25;break;
			case 2 : noLintang = 11;break;
			case 3 : noLintang = 32;break;
			case 4 : noLintang = 18;break;
			case 5 : noLintang = 4;break;
			}
			break;
		case 4:
			switch(tgl.noPancawara){
			case 1 : noLintang = 5;break;
			case 2 : noLintang = 26;break;
			case 3 : noLintang = 12;break;
			case 4 : noLintang = 33;break;
			case 5 : noLintang = 19;break;
			}
			break;
		case 5:
			switch(tgl.noPancawara){
			case 1 : noLintang = 20;break;
			case 2 : noLintang = 6;break;
			case 3 : noLintang = 27;break;
			case 4 : noLintang = 13;break;
			case 5 : noLintang = 34;break;
			}
			break;
		case 6:
			switch(tgl.noPancawara){
			case 1 : noLintang = 35;break;
			case 2 : noLintang = 21;break;
			case 3 : noLintang = 7;break;
			case 4 : noLintang = 28;break;
			case 5 : noLintang = 15;break;
			}
			break;
		}
		return noLintang;
	
	}
	
	/*** Fungsi menghitung panca sudha ***/
	public int hitungPancasudha(SakaCalendar tgl){
	
		/*
		* Pada SakaCalendar : 
		* 1 Wisesa segara
		* 2 Tunggak semi
		* 3 Satria wibhawa
		* 4 Sumur sinaba
		* 5 Bumi kapetak
		* 6 Satria wirang
		* 7 Lebu katiup angin
		*/
		
		int noPancasudha = 0;
		if (tgl.noSaptawara==0 && tgl.noPancawara==2 || tgl.noSaptawara==3 && tgl.noPancawara==2 || tgl.noSaptawara==1 && tgl.noPancawara==4 || tgl.noSaptawara==5 && tgl.noPancawara==5 || tgl.noSaptawara==2 && tgl.noPancawara==1 ||tgl.noSaptawara==6 && tgl.noPancawara==3){
			noPancasudha = 1;
		} else if (tgl.noSaptawara==1 && tgl.noPancawara==1 || tgl.noSaptawara==4 && tgl.noPancawara==4 || tgl.noSaptawara==5 && tgl.noPancawara==2 || tgl.noSaptawara==6 && tgl.noPancawara==5){ 
			noPancasudha = 2;
		} else if (tgl.noSaptawara==0 && tgl.noPancawara==4 || tgl.noSaptawara==2 && tgl.noPancawara==3 || tgl.noSaptawara==3 && tgl.noPancawara==4 || tgl.noSaptawara==4 && tgl.noPancawara==1 || tgl.noSaptawara==6 && tgl.noPancawara==2){
			noPancasudha = 3;
		} else if (tgl.noSaptawara==0 && tgl.noPancawara==1 || tgl.noSaptawara==1 && tgl.noPancawara==3 || tgl.noSaptawara==2 && tgl.noPancawara==5 || tgl.noSaptawara==3 && tgl.noPancawara==1 || tgl.noSaptawara==5 && tgl.noPancawara==4){
			noPancasudha = 4;
		} else if (tgl.noSaptawara==0 && tgl.noPancawara==3 || tgl.noSaptawara==1 && tgl.noPancawara==2 || tgl.noSaptawara==3 && tgl.noPancawara==3 || tgl.noSaptawara==4 && tgl.noPancawara==5 || tgl.noSaptawara==6 && tgl.noPancawara==1){
			noPancasudha = 5;
		} else if (tgl.noSaptawara==1 && tgl.noPancawara==5 || tgl.noSaptawara==2 && tgl.noPancawara==2 || tgl.noSaptawara==4 && tgl.noPancawara==3 || tgl.noSaptawara==5 && tgl.noPancawara==1 || tgl.noSaptawara==6 && tgl.noPancawara==4){
			noPancasudha = 6;
		} else if (tgl.noSaptawara==0 && tgl.noPancawara==5 || tgl.noSaptawara==2 && tgl.noPancawara==4 || tgl.noSaptawara==3 && tgl.noPancawara==5 || tgl.noSaptawara==4 && tgl.noPancawara==2 || tgl.noSaptawara==5 && tgl.noPancawara==3){
			noPancasudha = 7;
		}
		return noPancasudha;
	}
	
	/*** Fungsi menghitung pararasan ***/
	public int hitungPararasan(SakaCalendar tgl){
	
		/*
		* Pada SakaCalendar : 
		* 1 Laku bumi
		* 2 Laku api
		* 3 Laku angin
		* 4 Laku pandita sakti
		* 5 Aras tuding
		* 6 Aras kembang
		* 7 Laku bintang
		* 8 Laku bulan
		* 9 Laku surya
		* 10 Laku air/toya
		* 11 Laku pretiwi
		* 12 Laku agni agung
		*/
		
		int noPararasan = 0;
		if (tgl.noSaptawara==2 && tgl.noPancawara==4){
			noPararasan = 1;
		} else if (tgl.noSaptawara==1 && tgl.noPancawara==4 || tgl.noSaptawara==2 && tgl.noPancawara==1){
			noPararasan = 2;
		} else if (tgl.noSaptawara==0 && tgl.noPancawara==4 || tgl.noSaptawara==1 && tgl.noPancawara==1){
			noPararasan = 3;
		} else if (tgl.noSaptawara==0 && tgl.noPancawara==1 || tgl.noSaptawara==2 && tgl.noPancawara==3 || tgl.noSaptawara==5 && tgl.noPancawara==4){
			noPararasan = 4;
		} else if (tgl.noSaptawara==1 && tgl.noPancawara==3 || tgl.noSaptawara==2 && tgl.noPancawara==5 || tgl.noSaptawara==3 && tgl.noPancawara==4 || tgl.noSaptawara==5 && tgl.noPancawara==1){
			noPararasan = 5;
		} else if (tgl.noSaptawara==0 && tgl.noPancawara==3 || tgl.noSaptawara==2 && tgl.noPancawara==2 || tgl.noSaptawara==1 && tgl.noPancawara==5 || tgl.noSaptawara==3 && tgl.noPancawara==1 || tgl.noSaptawara==4 && tgl.noPancawara==4){
			noPararasan = 6;
		} else if (tgl.noSaptawara==0 && tgl.noPancawara==5 || tgl.noSaptawara==1 && tgl.noPancawara==2 || tgl.noSaptawara==4 && tgl.noPancawara==1 || tgl.noSaptawara==5 && tgl.noPancawara==3 || tgl.noSaptawara==6 && tgl.noPancawara==4){
			noPararasan = 7;
		} else if (tgl.noSaptawara==0 && tgl.noPancawara==2 || tgl.noSaptawara==3 && tgl.noPancawara==3 || tgl.noSaptawara==5 && tgl.noPancawara==5 || tgl.noSaptawara==6 && tgl.noPancawara==1){
			noPararasan = 8;
		} else if (tgl.noSaptawara==3 && tgl.noPancawara==5 || tgl.noSaptawara==4 && tgl.noPancawara==3 || tgl.noSaptawara==5 && tgl.noPancawara==2){
			noPararasan = 9;
		} else if (tgl.noSaptawara==3 && tgl.noPancawara==2 || tgl.noSaptawara==4 && tgl.noPancawara==5 || tgl.noSaptawara==6 && tgl.noPancawara==3){
			noPararasan = 10;
		} else if (tgl.noSaptawara==4 && tgl.noPancawara==2 || tgl.noSaptawara==6 && tgl.noPancawara==5){
			noPararasan = 11;
		} else if (tgl.noSaptawara==6 && tgl.noPancawara==2){
			noPararasan = 12;
		}
		return noPararasan;
	}
	
	/*** Fungsi menghitung rakam ***/
	public int hitungRakam(SakaCalendar tgl){
		
		/*
		* Menggunakan rumus dari babadbali.com : "Dari hari Sukra diberi angka urut 1 sampai Wrespati - kemudian dari Kliwon juga diberi angka urut sampai Wage. Angka urutan itu dibagi dengan 6, sisanya mencerminkan sifat pimpinan yang akan dinobatkan nanti."
		* Pada SakaCalendar menjadi : 
		* 1 Kala tinatang
		* 2 Demang kandhuruwan
		* 3 Sanggar waringin
		* 4 Mantri sinaroja
		* 5 Macam katawan
		* 0 -> 6 Nuju pati
		*/
		
		int noRakam = 0;
		int saptawara = 0, pancawara = 0;
		
		switch (tgl.noSaptawara){
		case 0:saptawara = 3;break;
		case 1:saptawara = 4;break;
		case 2:saptawara = 5;break;
		case 3:saptawara = 6;break;
		case 4:saptawara = 7;break;
		case 5:saptawara = 1;break;
		case 6:saptawara = 2;break;
		}
		
		switch (tgl.noPancawara){
		case 1:pancawara = 2;break;
		case 2:pancawara = 3;break;
		case 3:pancawara = 4;break;
		case 4:pancawara = 5;break;
		case 5:pancawara = 1;break;
		}
		
		noRakam = (pancawara + saptawara) % 6;
		if (noRakam == 0){noRakam = 6;}
		
		return noRakam;
	}
	
	/*** Fungsi menghitung zodiak ***/
	public int hitungZodiak(SakaCalendar tgl){
	
		/*
		* Pada SakaCalendar : 
		* 1 Aries
		* 2 Taurus
		* 3 Gemini
		* 4 Cancer
		* 5 Leo
		* 6 Virgo
		* 7 Libra
		* 8 Scorpio
		* 9 Sagitarius
		* 10 Capricon
		* 11 Aquarius
		* 12 Pisces
		*/
		
		
		int noZodiak = 0;
		int M = tgl.get(Calendar.MONTH)+1;
        int D = tgl.get(Calendar.DATE);
        
		if ((M == 12 && D >= 22 && D <= 31) || (M ==  1 && D >= 1 && D <= 19))
        	noZodiak = 10;
        else if ((M ==  1 && D >= 20 && D <= 31) || (M ==  2 && D >= 1 && D <= 17))
        	noZodiak = 11;
        else if ((M ==  2 && D >= 18 && D <= 29) || (M ==  3 && D >= 1 && D <= 19))
        	noZodiak = 12;
        else if ((M ==  3 && D >= 20 && D <= 31) || (M ==  4 && D >= 1 && D <= 19))
        	noZodiak = 1;
        else if ((M ==  4 && D >= 20 && D <= 30) || (M ==  5 && D >= 1 && D <= 20))
        	noZodiak = 2;
        else if ((M ==  5 && D >= 21 && D <= 31) || (M ==  6 && D >= 1 && D <= 20))
        	noZodiak = 3;
        else if ((M ==  6 && D >= 21 && D <= 30) || (M ==  7 && D >= 1 && D <= 22))
        	noZodiak = 4;
        else if ((M ==  7 && D >= 23 && D <= 31) || (M ==  8 && D >= 1 && D <= 22))
        	noZodiak = 5;
        else if ((M ==  8 && D >= 23 && D <= 31) || (M ==  9 && D >= 1 && D <= 22))
        	noZodiak = 6;
        else if ((M ==  9 && D >= 23 && D <= 30) || (M == 10 && D >= 1 && D <= 22))
        	noZodiak = 7;
        else if ((M == 10 && D >= 23 && D <= 31) || (M == 11 && D >= 1 && D <= 21))
        	noZodiak = 8;
        else if ((M == 11 && D >= 22 && D <= 30) || (M == 12 && D >= 1 && D <= 21))
        	noZodiak = 9;
        
		return noZodiak;
	}
	
	/*** Fungsi menghitung kalender saka ***/
	public SakaCalendar hitungSaka(SakaCalendar tgl){
		
		int bedaHari = getDateDiff(pivot,tgl);			
		
		/*** MENGHITUNG PENANGGAL PANGELONG ***/
		int hasilNgunaratri=0;
		int jumlahNgunaratri = 0;
		int mulai=0;
		if (bedaHari >= 0){
		
			//mengetahui jumlah ngunaratri
			
			if (pivot.noNgunaratri > 63){
				if (pivot.noNgunaratri % 63 == 0){
					mulai = pivot.noNgunaratri-63;
				} else {
					mulai = pivot.noNgunaratri - (pivot.noNgunaratri % 63);
				}
			}
			
			tgl.noNgunaratri = pivot.noNgunaratri + bedaHari; //Masukkan no ngunaratri
			
			if (tgl.noNgunaratri > (mulai + 63)){
				jumlahNgunaratri = ((tgl.noNgunaratri - (mulai + 63))/63) + 1;
				if ((tgl.noNgunaratri - (mulai + 63))%63==0){jumlahNgunaratri--;}
			}
			
			
			if (pivot.isNgunaratri){jumlahNgunaratri++;} //Jika pivot adalah ngunaratri, tambah jumlah ngunaratri
			// Menghitung angka penanggal/pangelong, jika 0 maka diubah ke 15
			hasilNgunaratri = (bedaHari + pivot.penanggal + jumlahNgunaratri) % 15;
			if (hasilNgunaratri == 0) { hasilNgunaratri =15; }
			tgl.penanggal=hasilNgunaratri;
			// Menghitung apakah penanggal atau pangelong
			tgl.isPangelong = ((((bedaHari + pivot.penanggal + jumlahNgunaratri - 1) / 15) % 2) == 0) ? pivot.isPangelong : !pivot.isPangelong ;
			
		}else{ // Jika tanggal yang dihitung sebelum tanggal pivot
		
			//mengetahui jumlah ngunaratri
			if ((pivot.noNgunaratri+63) > 63){
				if ((pivot.noNgunaratri+63) % 63 == 0){
					mulai = (pivot.noNgunaratri+63)-63;
				} else {
					mulai = (pivot.noNgunaratri+63) - ((pivot.noNgunaratri+63) % 63);
				}
			}
			
			tgl.noNgunaratri = pivot.noNgunaratri + bedaHari; //Masukkan no ngunaratri
			
			if (tgl.noNgunaratri < (mulai - 63)){
				jumlahNgunaratri = ((-(tgl.noNgunaratri - (mulai - 63)))/63) + 1;
				if ((-(tgl.noNgunaratri - (mulai - 63)))%63==0){jumlahNgunaratri--;}
			}
			
			// Menghitung angka penanggal/pangelong, jika 0 maka diubah ke 15
			hasilNgunaratri = bedaHari + pivot.penanggal - jumlahNgunaratri;
			
			hasilNgunaratri = 15 - (-hasilNgunaratri%15) ;
			
			tgl.penanggal=hasilNgunaratri;
			// Menghitung apakah penanggal atau pangelong
			tgl.isPangelong = ((((-bedaHari + tgl.penanggal + jumlahNgunaratri - 1) / 15) % 2) == 0) ? pivot.isPangelong : !pivot.isPangelong ;
		}
		
		/*** MENENTUKAN APAKAH NGUNARATRI ATAU TIDAK ***/
		tgl.isNgunaratri = false;
		if (tgl.get(Calendar.YEAR) > 1999 ){
			// Pengalantaka Eka Sungsang ke Pahing
			if (tgl.noSaptawara == 2){
				
				if (bedaHari > 0){
				
					if ((tgl.noWuku==10 && tgl.noPancawara==2 && (tgl.penanggal==14||tgl.penanggal==9||tgl.penanggal==4)) || 
						(tgl.noWuku==19 && tgl.noPancawara==5 && (tgl.penanggal==3||tgl.penanggal==13||tgl.penanggal==8)) || 
						(tgl.noWuku==28 && tgl.noPancawara==3 && (tgl.penanggal==7||tgl.penanggal==2||tgl.penanggal==12)) || 
						(tgl.noWuku==7 && tgl.noPancawara==1  && (tgl.penanggal==11||tgl.penanggal==6||tgl.penanggal==1)) || 
						(tgl.noWuku==16 && tgl.noPancawara==4 && (tgl.penanggal==15||tgl.penanggal==10||tgl.penanggal==5)) || 
						(tgl.noWuku==25 && tgl.noPancawara==2 && (tgl.penanggal==4||tgl.penanggal==14||tgl.penanggal==9))  || 
						(tgl.noWuku==4 && tgl.noPancawara==5  && (tgl.penanggal==8||tgl.penanggal==3||tgl.penanggal==13)) || 
						(tgl.noWuku==13 && tgl.noPancawara==3 && (tgl.penanggal==12||tgl.penanggal==7||tgl.penanggal==2)) || 
						(tgl.noWuku==22 && tgl.noPancawara==1 && (tgl.penanggal==1||tgl.penanggal==11||tgl.penanggal==6)) || 
						(tgl.noWuku==1 && tgl.noPancawara==4  && (tgl.penanggal==5||tgl.penanggal==15||tgl.penanggal==10))){
								
						tgl.isNgunaratri = true;

					}
				}else{
					if ((tgl.noWuku==10 && tgl.noPancawara==2 && (tgl.penanggal==15||tgl.penanggal==10||tgl.penanggal==5)) || 
						(tgl.noWuku==19 && tgl.noPancawara==5 && (tgl.penanggal==4||tgl.penanggal==14||tgl.penanggal==9)) || 
						(tgl.noWuku==28 && tgl.noPancawara==3 && (tgl.penanggal==8||tgl.penanggal==3||tgl.penanggal==13)) || 
						(tgl.noWuku==7 && tgl.noPancawara==1  && (tgl.penanggal==12||tgl.penanggal==7||tgl.penanggal==2)) || 
						(tgl.noWuku==16 && tgl.noPancawara==4 && (tgl.penanggal==1||tgl.penanggal==11||tgl.penanggal==6)) || 
						(tgl.noWuku==25 && tgl.noPancawara==2 && (tgl.penanggal==5||tgl.penanggal==15||tgl.penanggal==10))  || 
						(tgl.noWuku==4 && tgl.noPancawara==5  && (tgl.penanggal==9||tgl.penanggal==4||tgl.penanggal==14)) || 
						(tgl.noWuku==13 && tgl.noPancawara==3 && (tgl.penanggal==13||tgl.penanggal==8||tgl.penanggal==3)) || 
						(tgl.noWuku==22 && tgl.noPancawara==1 && (tgl.penanggal==2||tgl.penanggal==12||tgl.penanggal==7)) || 
						(tgl.noWuku==1 && tgl.noPancawara==4  && (tgl.penanggal==6||tgl.penanggal==1||tgl.penanggal==11))){
								
						tgl.isNgunaratri = true;
						tgl.penanggal = tgl.penanggal -1; //Jika ngunaratri mundur satu hari
						if (tgl.penanggal == 0 && tgl.isPangelong == false) {tgl.isPangelong = true;} // Ubah pangelong menjadi true apabila mundur dari penanggal 1
						if (tgl.penanggal == 0) {tgl.penanggal = 15;} //Ubah penanggal jadi 15 jika pengurangan akibat ngunaratri menjadi 0
					}
				}
			}
		}else{
			// Pengalantaka Eka Sungsang ke Pon
			if (tgl.noSaptawara == 3){
				if (bedaHari > 0){
				
					if ((tgl.noWuku==10 && tgl.noPancawara==3 && (tgl.penanggal==14||tgl.penanggal==9||tgl.penanggal==4)) || 
						(tgl.noWuku==19 && tgl.noPancawara==1 && (tgl.penanggal==3||tgl.penanggal==13||tgl.penanggal==8)) || 
						(tgl.noWuku==28 && tgl.noPancawara==4 && (tgl.penanggal==7||tgl.penanggal==2||tgl.penanggal==12)) || 
						(tgl.noWuku==7 && tgl.noPancawara==2  && (tgl.penanggal==11||tgl.penanggal==6||tgl.penanggal==1)) || 
						(tgl.noWuku==16 && tgl.noPancawara==5 && (tgl.penanggal==15||tgl.penanggal==10||tgl.penanggal==5)) || 
						(tgl.noWuku==25 && tgl.noPancawara==3 && (tgl.penanggal==4||tgl.penanggal==14||tgl.penanggal==9))  || 
						(tgl.noWuku==4 && tgl.noPancawara==1  && (tgl.penanggal==8||tgl.penanggal==3||tgl.penanggal==13)) || 
						(tgl.noWuku==13 && tgl.noPancawara==4 && (tgl.penanggal==12||tgl.penanggal==7||tgl.penanggal==2)) || 
						(tgl.noWuku==22 && tgl.noPancawara==2 && (tgl.penanggal==1||tgl.penanggal==11||tgl.penanggal==6)) || 
						(tgl.noWuku==1 && tgl.noPancawara==5  && (tgl.penanggal==5||tgl.penanggal==15||tgl.penanggal==10))){
								
						tgl.isNgunaratri = true;

					}
				}else{
					if ((tgl.noWuku==10 && tgl.noPancawara==3 && (tgl.penanggal==15||tgl.penanggal==10||tgl.penanggal==5)) || 
						(tgl.noWuku==19 && tgl.noPancawara==1 && (tgl.penanggal==4||tgl.penanggal==14||tgl.penanggal==9)) || 
						(tgl.noWuku==28 && tgl.noPancawara==4 && (tgl.penanggal==8||tgl.penanggal==3||tgl.penanggal==13)) || 
						(tgl.noWuku==7 && tgl.noPancawara==2  && (tgl.penanggal==12||tgl.penanggal==7||tgl.penanggal==2)) || 
						(tgl.noWuku==16 && tgl.noPancawara==5 && (tgl.penanggal==1||tgl.penanggal==11||tgl.penanggal==6)) || 
						(tgl.noWuku==25 && tgl.noPancawara==3 && (tgl.penanggal==5||tgl.penanggal==15||tgl.penanggal==10))  || 
						(tgl.noWuku==4 && tgl.noPancawara==1  && (tgl.penanggal==9||tgl.penanggal==4||tgl.penanggal==14)) || 
						(tgl.noWuku==13 && tgl.noPancawara==4 && (tgl.penanggal==13||tgl.penanggal==8||tgl.penanggal==3)) || 
						(tgl.noWuku==22 && tgl.noPancawara==2 && (tgl.penanggal==2||tgl.penanggal==12||tgl.penanggal==7)) || 
						(tgl.noWuku==1 && tgl.noPancawara==5  && (tgl.penanggal==6||tgl.penanggal==1||tgl.penanggal==11))){
								
						tgl.isNgunaratri = true;
						tgl.penanggal = tgl.penanggal -1; //Jika ngunaratri mundur satu hari
						if (tgl.penanggal == 0 && tgl.isPangelong == false) {tgl.isPangelong = true;} // Ubah pangelong menjadi true apabila mundur dari penanggal 1
						if (tgl.penanggal == 0) {tgl.penanggal = 15;} //Ubah penanggal jadi 15 jika pengurangan akibat ngunaratri menjadi 0
					}
				}
			}
		}
		
		
		/*** MENGHITUNG SASIH ***/
		/*
		* Pada SakaCalendar : 
		* 1 Kasa
		* 2 Karo
		* 3 Katiga
		* 4 Kapat
		* 5 Kalima
		* 6 Kanem
		* 7 Kapitu
		* 8 Kawolu
		* 9 Kasanga
		* 10 Kadasa
		* 11 Destha
		* 12 Sadha
		*/
		
		int hasilSasih =0;
		int jumlahNampih = 0;
		
		int tahunSaka = pivot.tahunSaka;
		int perulangan1 = 0;
		int perulangan2 = pivot.noSasih;
		boolean isNampih=false;
		
		if (tgl.get(Calendar.YEAR) > 2002 || tgl.get(Calendar.YEAR) < 1992 ){
			// Sistem nampih sasih
			if (bedaHari >= 0){
			
				if (pivot.isPangelong){
					bedaHari = bedaHari + 15 + (pivot.penanggal - 1);
					hasilSasih = (bedaHari + jumlahNgunaratri ) / 30 ;	
				}else{
					bedaHari = bedaHari + (pivot.penanggal - 1);
					hasilSasih = (bedaHari + jumlahNgunaratri ) / 30 ;	
				}
				
				// menghitung tahun saka dan jumlah nampih sasih
				while (perulangan1 < hasilSasih){				
					perulangan1++;
					perulangan2++;
					perulangan2 = perulangan2 % 12;
					if (perulangan2 == 0) { perulangan2 = 12;}
					
					
					if (perulangan2 == 10){
						tahunSaka++;
					}
					
					if (isNampih) {
						isNampih = false;
					}else{
						if (((tahunSaka % 19) == 0)||((tahunSaka % 19) == 6)||((tahunSaka % 19) == 11)){
								tgl.isNampih = (perulangan2==12) ? true : false;
								if (perulangan2==1){perulangan2--;jumlahNampih++;isNampih=true;}
						}else if (((tahunSaka % 19) == 3)||((tahunSaka % 19) == 8)||((tahunSaka % 19) == 14)||((tahunSaka % 19) == 16)) {
								tgl.isNampih = (perulangan2==1) ? true : false;
								if (perulangan2==2){perulangan2--;jumlahNampih++;isNampih=true;}
						}
					}				
				}
				
				tgl.noSasih = (hasilSasih - jumlahNampih + pivot.noSasih)%12  ;
				if (tgl.isNampih){tgl.noSasih--;}
				
				if (tgl.noSasih < 0){
					tgl.noSasih = 12 - (-tgl.noSasih%12);
				}
				if (tgl.noSasih == 0 ){tgl.noSasih = 12;}
				
				tgl.tahunSaka = tahunSaka;
			
			}else{ //Mundur
				
				if (pivot.isPangelong){
					bedaHari = bedaHari - (15 - pivot.penanggal);
					hasilSasih = -(bedaHari - jumlahNgunaratri ) / 30 ;	
				}else{
					bedaHari = bedaHari - 15 - (15 - pivot.penanggal);
					hasilSasih = -(bedaHari - jumlahNgunaratri ) / 30 ;	
				}
				
				
				
				while (perulangan1 < hasilSasih){				
					perulangan1++;
					perulangan2--;
					perulangan2 = perulangan2 % 12;
					if (perulangan2 == 0) { perulangan2 = 12;}
					
					
					if (perulangan2 == 9){
						tahunSaka--;
					}
					
					if (isNampih) {
						isNampih = false;
					}else{
						if (((tahunSaka % 19) == 0)||((tahunSaka % 19) == 6)||((tahunSaka % 19) == 11)){
								tgl.isNampih = (perulangan2==11) ? true : false;
								if (perulangan2==10){perulangan2++;jumlahNampih++;isNampih=true;}
						}else if (((tahunSaka % 19) == 3)||((tahunSaka % 19) == 8)||((tahunSaka % 19) == 14)||((tahunSaka % 19) == 16)) {
								tgl.isNampih = (perulangan2==12) ? true : false;
								if (perulangan2==11){perulangan2++;jumlahNampih++;isNampih=true;}
						}
					}				
				}
				
				tgl.noSasih = pivot.noSasih - hasilSasih + jumlahNampih;
				
				if (tgl.noSasih < 0){
					tgl.noSasih = 12 - (-tgl.noSasih%12);
				}
				if (tgl.noSasih == 0 ){tgl.noSasih = 12;}
				
				tgl.tahunSaka = tahunSaka;
				if (tgl.isPangelong && tgl.penanggal == 15 && tgl.isNgunaratri && tgl.isNampih){tgl.isNampih = false;} // Ubah isnampih menjadi false apabila berada di ngunaratri di awal penanggal
			}
		}else{
			// Nampih Sasih berkesinambungan
			if (bedaHari >= 0){
			
				if (pivot.isPangelong){
					bedaHari = bedaHari + 15 + (pivot.penanggal - 1);
					hasilSasih = (bedaHari + jumlahNgunaratri ) / 30 ;	
				}else{
					bedaHari = bedaHari + (pivot.penanggal - 1);
					hasilSasih = (bedaHari + jumlahNgunaratri ) / 30 ;	
				}
				
				// menghitung tahun saka dan jumlah nampih sasih
				while (perulangan1 < hasilSasih){				
					perulangan1++;
					perulangan2++;
					perulangan2 = perulangan2 % 12;
					if (perulangan2 == 0) { perulangan2 = 12;}
					
					
					if (perulangan2 == 10){
						tahunSaka++;
					}
					
					if (isNampih) {
						isNampih = false;
					}else{
						if (((tahunSaka % 19) == 2)||((tahunSaka % 19) == 10)){
								tgl.isNampih = (perulangan2==12) ? true : false;
								if (perulangan2==1){perulangan2--;jumlahNampih++;isNampih=true;}
						}else if (((tahunSaka % 19) == 4)) {
								tgl.isNampih = (perulangan2==4) ? true : false;
								if (perulangan2==5){perulangan2--;jumlahNampih++;isNampih=true;}
						}else if (((tahunSaka % 19) == 7)) {
								tgl.isNampih = (perulangan2==2) ? true : false;
								if (perulangan2==3){perulangan2--;jumlahNampih++;isNampih=true;}
						}else if (((tahunSaka % 19) == 13)) {
								tgl.isNampih = (perulangan2==11) ? true : false;
								if (perulangan2==12){perulangan2--;jumlahNampih++;isNampih=true;}
						}else if (((tahunSaka % 19) == 15)) {
								tgl.isNampih = (perulangan2==3) ? true : false;
								if (perulangan2==4){perulangan2--;jumlahNampih++;isNampih=true;}
						}else if (((tahunSaka % 19) == 18)) {
								tgl.isNampih = (perulangan2==1) ? true : false;
								if (perulangan2==2){perulangan2--;jumlahNampih++;isNampih=true;}
						}
					}				
				}
				
				tgl.noSasih = (hasilSasih - jumlahNampih + pivot.noSasih)%12  ;
				if (tgl.isNampih){tgl.noSasih--;}
				
				if (tgl.noSasih < 0){
					tgl.noSasih = 12 - (-tgl.noSasih%12);
				}
				if (tgl.noSasih == 0 ){tgl.noSasih = 12;}
				
				tgl.tahunSaka = tahunSaka;
			
			}else{ //Mundur
				
				
				if (pivot.isPangelong){
					bedaHari = bedaHari - (15 - pivot.penanggal);
					hasilSasih = -(bedaHari - jumlahNgunaratri ) / 30 ;	
				}else{
					bedaHari = bedaHari - 15 - (15 - pivot.penanggal);
					hasilSasih = -(bedaHari - jumlahNgunaratri ) / 30 ;	
				}
				
				
				
				while (perulangan1 < hasilSasih){				
					perulangan1++;
					perulangan2--;
					perulangan2 = perulangan2 % 12;
					if (perulangan2 == 0) { perulangan2 = 12;}
										
					if (perulangan2 == 9){
						tahunSaka--;
					}
					
					if (isNampih) {
						isNampih = false;
					}else{
						if (((tahunSaka % 19) == 2)||((tahunSaka % 19) == 10)){
								tgl.isNampih = (perulangan2==11) ? true : false;
								if (perulangan2==10){perulangan2++;jumlahNampih++;isNampih=true;}
						}else if (((tahunSaka % 19) == 4)) {
								tgl.isNampih = (perulangan2==3) ? true : false;
								if (perulangan2==2){perulangan2++;jumlahNampih++;isNampih=true;}
						}else if (((tahunSaka % 19) == 7)) {
								tgl.isNampih = (perulangan2==1) ? true : false;
								if (perulangan2==12){perulangan2++;jumlahNampih++;isNampih=true;}
						}else if (((tahunSaka % 19) == 13)) {
								tgl.isNampih = (perulangan2==10) ? true : false;
								if (perulangan2==9){perulangan2++;jumlahNampih++;isNampih=true;}
						}else if (((tahunSaka % 19) == 15)) {
								tgl.isNampih = (perulangan2==2) ? true : false;
								if (perulangan2==1){perulangan2++;jumlahNampih++;isNampih=true;}
						}else if (((tahunSaka % 19) == 18)) {
								tgl.isNampih = (perulangan2==12) ? true : false;
								if (perulangan2==11){perulangan2++;jumlahNampih++;isNampih=true;}
						}
					}				
				}
				
				tgl.noSasih = pivot.noSasih - hasilSasih + jumlahNampih;
				
				if (tgl.noSasih < 0){
					tgl.noSasih = 12 - (-tgl.noSasih%12);
				}
				if (tgl.noSasih == 0 ){tgl.noSasih = 12;}
				
				
				tgl.tahunSaka = tahunSaka;
				if (tgl.isPangelong && tgl.penanggal == 15 && tgl.isNgunaratri && tgl.isNampih){tgl.isNampih = false;} // Ubah is nampih menjadi false apabila berada di ngunaratri di awal penanggal
			}
		}		
		
		return tgl;
		
	}
	
}