/*

    SakaCalendar.java
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

*/

package com.edysantosa.sakacalendar;
import java.util.*;

public class SakaCalendar extends GregorianCalendar {

	public final static int NO_WUKU = 0;
	public final static int ANGKA_WUKU = 1;
	public final static int URIP_WUKU = 2;
	public final static int NO_SAPTAWARA = 0;
	public final static int URIP_SAPTAWARA = 1;
	public final static int NO_PANCAWARA = 0;
	public final static int URIP_PANCAWARA = 1;

	public final static int TAHUN_SAKA = 0;
	public final static int PENANGGAL = 1;
	public final static int NO_SASIH = 2;
	public final static int NO_NGUNARATRI = 3;

	public final static int NGUNARATRI = 0;
	public final static int PANGELONG = 1;
	public final static int NAMPIH = 2;


	SakaCalendarPivot pivot;

	private int tahunSaka;
	private int penanggal;
	private int noNgunaratri;
	private int noSasih;
	private boolean isNgunaratri;
	private boolean isPangelong;
	private boolean isNampih;
	// TODO : Tambah disini

	public SakaCalendar(){
		super();
		this.initialize();
	}

	public SakaCalendar(int year, int month, int dayOfMonth){
		super(year, month , dayOfMonth);
		this.initialize();
	}

	public SakaCalendar(int year, int month, int dayOfMonth, int hourOfDay, int minute, int second){
		super(year, month , dayOfMonth, hourOfDay, minute, second);
			this.initialize();
	}

	public SakaCalendar(Locale aLocale){
		super(aLocale);
		this.initialize();
	}

	public SakaCalendar(TimeZone zone){
		super(zone);
		this.initialize();
	}

	public SakaCalendar(TimeZone zone, Locale aLocale){
		super(zone, aLocale);
	}

	private void initialize(){
		this.pivot = getPivots(this.getTimeInMillis());
	}

	/*** Fungsi menambahkan tanggal-tanggal pivot ***/
	private SakaCalendarPivot getPivots(long timestamp) {
		SakaCalendarPivot pivot = new SakaCalendarPivot();
		if (timestamp >= 946684800 - pivot.get(Calendar.ZONE_OFFSET ) ){
			// Setelah 30 Desember 1999
			// Pengalantaka Eka Sungsang ke Pahing
			// TODO : Pastikan kapan mulai berlakunya Pengalantaka Eka Sungsang ke Pahing!
			pivot.setTimeInMillis(946684800 * 1000L - pivot.get(Calendar.ZONE_OFFSET ) ); //2000-01-01
			pivot.noWuku = 10;
			pivot.angkaWuku = 70;
			pivot.tahunSaka = 1921;
			pivot.noSasih = 7;
			pivot.penanggal = 10;
			pivot.isPangelong = true;
			pivot.noNgunaratri = 424;
			pivot.isNgunaratri = false;
		}else{
			// 30 Desember 1999 kebelakang
			// Pengalantaka Eka Sungsang ke Pon
			pivot.setTimeInMillis(0 * 1000L); //1970-01-01
			pivot.noWuku = 5;
			pivot.angkaWuku =33;
			pivot.tahunSaka = 1891;
			pivot.noSasih = 7;
			pivot.penanggal = 8;
			pivot.isPangelong = true;
			pivot.noNgunaratri = 50;
			pivot.isNgunaratri = false;
		}

		return pivot;
	}


	/*** Fungsi menghitung perbedaan hari antara 2 Timestamp ***/
	private long getDateDiff(long d1, long d2) {

		/*
		Catatan :
			Kode lebih cantik jika menggunakan class baru dari java 8, java.time

			"return ChronoUnit.DAYS.between(d1.toInstant(), d2.toInstant());"

			Akan tetapi, setelah testing performanya lebih rendah rata-rata 2 s/d 3 milisecond.

		 */

		double x = (d2 - d1) / (1000d * 60 * 60 * 24);
		return (long)x;
	}

	private class SakaCalendarPivot extends GregorianCalendar {

		int noWuku;
		int angkaWuku;

		int tahunSaka;
		int noSasih;
		int penanggal;
		boolean isPangelong;
		int noNgunaratri; //Jumlah hari sejak nemugelang pengalantaka
		boolean isNgunaratri;

	}

	/*** Fungsi menghitung pawukon ***/
	public int getWuku(int field){

		long bedaHari = getDateDiff(pivot.getTimeInMillis(),this.getTimeInMillis());
		int angkaWuku;
		int uripWuku;
		int noWuku;

		if (bedaHari >= 0){
			angkaWuku = (int)(pivot.angkaWuku + bedaHari)%210 ;
		}else{
			angkaWuku = 210 - (int)((-(pivot.angkaWuku + bedaHari))%210) ;
		}
		if (angkaWuku == 0){ angkaWuku = 210; }
		noWuku = (int) Math.ceil(angkaWuku /7.0);
		if (noWuku > 30) { noWuku %=30; }
		if (noWuku == 0) { noWuku =30; }

		switch (noWuku) {
			case 1: //Sinta
				uripWuku = 7;
				break;
			case 2: //Landep
				uripWuku = 1;
				break;
			case 3: //Ukir
				uripWuku = 4;
				break;
			case 4: //Kulantir
				uripWuku = 6;
				break;
			case 5: //Tolu
				uripWuku = 5;
				break;
			case 6: //Gumbreg
				uripWuku = 8;
				break;
			case 7: //Wariga
				uripWuku = 9;
				break;
			case 8: //Warigadean
				uripWuku = 3;
				break;
			case 9: //Julungwangi
				uripWuku = 7;
				break;
			case 10: //Sungsang
				uripWuku = 1;
				break;
			case 11: //Dunggulan
				uripWuku = 4;
				break;
			case 12: //Kuningan
				uripWuku = 6;
				break;
			case 13: //Langkir
				uripWuku = 5;
				break;
			case 14: //Medangsia
				uripWuku = 8;
				break;
			case 15: //Pujut
				uripWuku = 9;
				break;
			case 16: //Pahang
				uripWuku = 3;
				break;
			case 17: //Krulut
				uripWuku = 7;
				break;
			case 18: //Merakih
				uripWuku = 1;
				break;
			case 19: //Tambir
				uripWuku = 4;
				break;
			case 20: //Medangkungan
				uripWuku = 6;
				break;
			case 21: //Matal
				uripWuku = 5;
				break;
			case 22: //Uye
				uripWuku = 8;
				break;
			case 23: //Menail
				uripWuku = 9;
				break;
			case 24: //Perangbakat
				uripWuku = 3;
				break;
			case 25: //Bala
				uripWuku = 7;
				break;
			case 26: //Ugu
				uripWuku = 1;
				break;
			case 27: //Wayang
				uripWuku = 4;
				break;
			case 28: //kelawu
				uripWuku = 6;
				break;
			case 29: //Dukut
				uripWuku = 5;
				break;
			case 30: //Watugunung
				uripWuku = 8;
				break;
			default:
				uripWuku =0;
				break;
		}

		switch(field){
		case NO_WUKU: return noWuku;
		case ANGKA_WUKU: return angkaWuku;
		case URIP_WUKU: return uripWuku;
		default: return noWuku;
		}
	}

	public int getSaptawara(int field){
		/*
		* Perhitungan saptawara hanya mengecek DAY_OF_WEEK dari SakaCalendar
		*/
	
		int noSaptawara = 0;
		int uripSaptawara = 5;

		switch (this.get(Calendar.DAY_OF_WEEK)) {
			case 1: //Redite
				noSaptawara = 0;
				uripSaptawara = 5;
				break;
			case 2: //Soma
				noSaptawara = 1;
				uripSaptawara = 4;
				break;
			case 3: //Anggara
				noSaptawara = 2;
				uripSaptawara = 3;
				break;
			case 4: //Buda
				noSaptawara = 3;
				uripSaptawara = 7;
				break;
			case 5: //Wrespati
				noSaptawara = 4;
				uripSaptawara = 8;
				break;
			case 6: //Sukra
				noSaptawara = 5;
				uripSaptawara = 6;
				break;
			case 7: //Saniscara
				noSaptawara = 6;
				uripSaptawara = 9;
				break;
			default:
				break;
		}

		switch(field){
		case NO_SAPTAWARA: return noSaptawara;
		case URIP_SAPTAWARA: return uripSaptawara;
		default: return noSaptawara;
		}		
	}

	public int getPancawara(int field){

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
	
		int noPancawara = (this.getWuku(ANGKA_WUKU) % 5) + 1 ;
		int uripPancawara = 5;


		// mendapatkan urip pancawara
		switch (noPancawara) {
		case 1:
			uripPancawara = 5;break;
		case 2:
			uripPancawara = 9;break;
		case 3:
			uripPancawara = 7;break;
		case 4:
			uripPancawara = 4;break;
		case 5:
			uripPancawara = 8;break;
		default:break;
		}

		switch(field){
			case NO_PANCAWARA: return noPancawara;
			case URIP_PANCAWARA: return uripPancawara;
			default: return noPancawara;
		}		
	}

	public int getTriwara(){
		/*
		* Perhitungan triwara
		* Menggunakan rumus dari babadbali.com : "Perhitungan triwara murni aritmatika, berdaur dari ketiganya. Angka Pawukon dibagi 3, jika sisanya (modulus) 1 adalah Pasah, 2 adalah Beteng, 0 adalah Kajeng"
		* Pada SakaCalendar menjadi : 
		* 1 Pasah
		* 2 Beteng
		* 0 -> 3 kajeng
		*/
		int noTriwara = this.getWuku(ANGKA_WUKU) % 3;
		if (noTriwara == 0){noTriwara = 3;}
		return noTriwara;
	}	

	public int getEkawara(){
		/*
		* Perhitungan ekawara
		* Pada SakaCalendar : 
		* 1 Luang
		* 2 Bukan luang (kosong)
		*/
		int noEkawara = (this.getPancawara(URIP_PANCAWARA) + this.getSaptawara(URIP_SAPTAWARA)) % 2;
		if (noEkawara!=0){
			return 1; //Jika tidak habis dibagi 2 maka luang
		}else{
			return 0; //Jika habis dibagi 2 maka bukan luang 
		}		
	}

	public int getDwiwara(){
		/*
		* Perhitungan dwiwara
		* Pada SakaCalendar : 
		* 1 Menga
		* 2 Pepet
		*/
		int noDwiwara = (this.getPancawara(URIP_PANCAWARA) + this.getSaptawara(URIP_SAPTAWARA)) % 2;
		if (noDwiwara==0){
			return 1; //Jika habis dibagi 2 maka menga
		}else{
			return 2; //Jika tidak habis dibagi 2 maka pepet
		}		
	}

	public int getCaturwara(){
		/*
		* Perhitungan caturwara
		* Pada wuku dengan angka wuku 71,72,73 caturwara tetap jaya yang disebut dengan Jayatiga
		* Pada SakaCalendar : 
		* 1 Sri
		* 2 Laba
		* 3 Jaya
		* 0 -> Menala
		*/
	
		int angkaWuku  = this.getWuku(ANGKA_WUKU);
		int noCaturwara;

		if (angkaWuku == 71 || angkaWuku == 72 || angkaWuku == 73){	
			noCaturwara = 3;
		}else if (angkaWuku <= 70){
			noCaturwara = angkaWuku % 4;
		}else{
			noCaturwara = (angkaWuku + 2) % 4;
		}
		if (noCaturwara == 0){noCaturwara = 4;}
		return noCaturwara;
	}

	public int getSadwara(){
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
		int noSadwara = this.getWuku(ANGKA_WUKU) % 6;
		if (noSadwara == 0){noSadwara = 6;}
		return noSadwara;		
	}

	public int getAstawara(){
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
	
		int noAstawara;
		int angkaWuku  = this.getWuku(ANGKA_WUKU);
		
		if (angkaWuku == 71 || angkaWuku == 72 || angkaWuku == 73){	
			noAstawara = 7;
		}else if (angkaWuku <= 70){
			noAstawara = angkaWuku % 8;
		}else{
			noAstawara = (angkaWuku + 6) % 8;
		}
		if (noAstawara == 0){noAstawara = 8;}
		return noAstawara;
	}

	public int getSangawara(){
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

		int noSangawara;
		int angkaWuku  = this.getWuku(ANGKA_WUKU);	

		if (angkaWuku <= 4){
			noSangawara = 1 ;
		}else{
			noSangawara = (angkaWuku + 6) % 9;
		}
		if (noSangawara == 0){noSangawara = 9;}
		return noSangawara;		
	}

	public int getDasawara(){
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
		return (((this.getPancawara(URIP_PANCAWARA) + this.getSaptawara(URIP_SAPTAWARA)) % 10)+1);
	}


    public int getIngkel(){
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
        int noWuku = this.getWuku(NO_WUKU);
        if (noWuku==1||noWuku==7||noWuku==13||noWuku==19||noWuku==25){
            noIngkel = 1;
        } else if (noWuku==2||noWuku==8||noWuku==14||noWuku==20||noWuku==26){
            noIngkel = 2;
        } else if (noWuku==3||noWuku==9||noWuku==15||noWuku==21||noWuku==27){
            noIngkel = 3;
        } else if (noWuku==4||noWuku==10||noWuku==16||noWuku==22||noWuku==28){
            noIngkel = 4;
        } else if (noWuku==5||noWuku==11||noWuku==17||noWuku==23||noWuku==29){
            noIngkel = 5;
        } else {
            noIngkel = 6;
        }
        
        return noIngkel;
    }
    
    /*** Fungsi menghitung jejepan ***/
    public int getJejepan(){
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
        int angkaWuku = this.getWuku(ANGKA_WUKU);
        noJejepan = angkaWuku % 6;
        if (noJejepan == 0){noJejepan = 6;}
        return noJejepan;
    }
    
    /*** Fungsi menghitung pewatekan alit ***/
    public int getWatekAlit(){

        /*
        * Pada SakaCalendar :
        * 1 Uler
        * 2 Gajah
        * 3 Lembu
        * 0 -> 4 Lintah
        */
        int noWatekAlit;
        noWatekAlit = (this.getPancawara(URIP_PANCAWARA) + this.getSaptawara(URIP_SAPTAWARA)) % 4;
        if (noWatekAlit == 0){noWatekAlit = 4;}

        return noWatekAlit;
    }

    /*** Fungsi menghitung pewatekan madya ***/
    public int getWatekMadya(){

        /*
        * Pada SakaCalendar :
        * 1 Gajah
        * 2 Watu
        * 3 Buta
        * 4 Suku
        * 0 ->5 Wong
        */
        int noWatekMadya;
        noWatekMadya = (this.getPancawara(URIP_PANCAWARA) + this.getSaptawara(URIP_SAPTAWARA)) % 5;
        if (noWatekMadya == 0){noWatekMadya = 5;}

        return noWatekMadya;
    }

    /*** Fungsi menghitung eka jala rsi ***/
    public int getEkaJalaRsi(){

        /*
        * Pada SakaCalendar :
        * 1 Bagna mapasah
        * 2 Bahu putra
        * 3 Buat astawa
        * 4 Buat lara
        * 5 Buat merang
        * 6 Buat sebet
        * 7 Buat kingking
        * 8 Buat suka
        * 9 Dahat kingking
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
        int noWuku = this.getWuku(NO_WUKU);
        int noSaptawara = this.getSaptawara(NO_SAPTAWARA);
        switch(noWuku){
        case 1:
            switch(noSaptawara){
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
            switch(noSaptawara){
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
            switch(noSaptawara){
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
            switch(noSaptawara){
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
            switch(noSaptawara){
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
            switch(noSaptawara){
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
            switch(noSaptawara){
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
            switch(noSaptawara){
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
            switch(noSaptawara){
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
            switch(noSaptawara){
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
            switch(noSaptawara){
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
            switch(noSaptawara){
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
            switch(noSaptawara){
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
            switch(noSaptawara){
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
            switch(noSaptawara){
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
            switch(noSaptawara){
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
            switch(noSaptawara){
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
            switch(noSaptawara){
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
            switch(noSaptawara){
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
            switch(noSaptawara){
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
            switch(noSaptawara){
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
            switch(noSaptawara){
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
            switch(noSaptawara){
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
            switch(noSaptawara){
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
            switch(noSaptawara){
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
            switch(noSaptawara){
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
            switch(noSaptawara){
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
            switch(noSaptawara){
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
            switch(noSaptawara){
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
            switch(noSaptawara){
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
    public int getLintang(){

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
        int noSaptawara = this.getSaptawara(NO_SAPTAWARA);
        int noPancawara = this.getPancawara(NO_PANCAWARA);
        switch(noSaptawara){
        case 0:
            switch(noPancawara){
            case 1 : noLintang = 15;break;
            case 2 : noLintang = 1;break;
            case 3 : noLintang = 22;break;
            case 4 : noLintang = 8;break;
            case 5 : noLintang = 29;break;
            }
            break;
        case 1:
            switch(noPancawara){
            case 1 : noLintang = 30;break;
            case 2 : noLintang = 16;break;
            case 3 : noLintang = 2;break;
            case 4 : noLintang = 23;break;
            case 5 : noLintang = 9;break;
            }
            break;
        case 2:
            switch(noPancawara){
            case 1 : noLintang = 10;break;
            case 2 : noLintang = 31;break;
            case 3 : noLintang = 17;break;
            case 4 : noLintang = 23;break;
            case 5 : noLintang = 24;break;
            }
            break;
        case 3:
            switch(noPancawara){
            case 1 : noLintang = 25;break;
            case 2 : noLintang = 11;break;
            case 3 : noLintang = 32;break;
            case 4 : noLintang = 18;break;
            case 5 : noLintang = 4;break;
            }
            break;
        case 4:
            switch(noPancawara){
            case 1 : noLintang = 5;break;
            case 2 : noLintang = 26;break;
            case 3 : noLintang = 12;break;
            case 4 : noLintang = 33;break;
            case 5 : noLintang = 19;break;
            }
            break;
        case 5:
            switch(noPancawara){
            case 1 : noLintang = 20;break;
            case 2 : noLintang = 6;break;
            case 3 : noLintang = 27;break;
            case 4 : noLintang = 13;break;
            case 5 : noLintang = 34;break;
            }
            break;
        case 6:
            switch(noPancawara){
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
    public int getPancasudha(){

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
        int noSaptawara = this.getSaptawara(NO_SAPTAWARA);
        int noPancawara = this.getPancawara(NO_PANCAWARA);
        if (noSaptawara==0 && noPancawara==2 || noSaptawara==3 && noPancawara==2 || noSaptawara==1 && noPancawara==4 || noSaptawara==5 && noPancawara==5 || noSaptawara==2 && noPancawara==1 ||noSaptawara==6 && noPancawara==3){
            noPancasudha = 1;
        } else if (noSaptawara==1 && noPancawara==1 || noSaptawara==4 && noPancawara==4 || noSaptawara==5 && noPancawara==2 || noSaptawara==6 && noPancawara==5){
            noPancasudha = 2;
        } else if (noSaptawara==0 && noPancawara==4 || noSaptawara==2 && noPancawara==3 || noSaptawara==3 && noPancawara==4 || noSaptawara==4 && noPancawara==1 || noSaptawara==6 && noPancawara==2){
            noPancasudha = 3;
        } else if (noSaptawara==0 && noPancawara==1 || noSaptawara==1 && noPancawara==3 || noSaptawara==2 && noPancawara==5 || noSaptawara==3 && noPancawara==1 || noSaptawara==5 && noPancawara==4){
            noPancasudha = 4;
        } else if (noSaptawara==0 && noPancawara==3 || noSaptawara==1 && noPancawara==2 || noSaptawara==3 && noPancawara==3 || noSaptawara==4 && noPancawara==5 || noSaptawara==6 && noPancawara==1){
            noPancasudha = 5;
        } else if (noSaptawara==1 && noPancawara==5 || noSaptawara==2 && noPancawara==2 || noSaptawara==4 && noPancawara==3 || noSaptawara==5 && noPancawara==1 || noSaptawara==6 && noPancawara==4){
            noPancasudha = 6;
        } else if (noSaptawara==0 && noPancawara==5 || noSaptawara==2 && noPancawara==4 || noSaptawara==3 && noPancawara==5 || noSaptawara==4 && noPancawara==2 || noSaptawara==5 && noPancawara==3){
            noPancasudha = 7;
        }
        return noPancasudha;
    }

    /*** Fungsi menghitung pararasan ***/
    public int getPararasan(){

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
        int noSaptawara = this.getSaptawara(NO_SAPTAWARA);
        int noPancawara = this.getPancawara(NO_PANCAWARA);
        if (noSaptawara==2 && noPancawara==4){
            noPararasan = 1;
        } else if (noSaptawara==1 && noPancawara==4 || noSaptawara==2 && noPancawara==1){
            noPararasan = 2;
        } else if (noSaptawara==0 && noPancawara==4 || noSaptawara==1 && noPancawara==1){
            noPararasan = 3;
        } else if (noSaptawara==0 && noPancawara==1 || noSaptawara==2 && noPancawara==3 || noSaptawara==5 && noPancawara==4){
            noPararasan = 4;
        } else if (noSaptawara==1 && noPancawara==3 || noSaptawara==2 && noPancawara==5 || noSaptawara==3 && noPancawara==4 || noSaptawara==5 && noPancawara==1){
            noPararasan = 5;
        } else if (noSaptawara==0 && noPancawara==3 || noSaptawara==2 && noPancawara==2 || noSaptawara==1 && noPancawara==5 || noSaptawara==3 && noPancawara==1 || noSaptawara==4 && noPancawara==4){
            noPararasan = 6;
        } else if (noSaptawara==0 && noPancawara==5 || noSaptawara==1 && noPancawara==2 || noSaptawara==4 && noPancawara==1 || noSaptawara==5 && noPancawara==3 || noSaptawara==6 && noPancawara==4){
            noPararasan = 7;
        } else if (noSaptawara==0 && noPancawara==2 || noSaptawara==3 && noPancawara==3 || noSaptawara==5 && noPancawara==5 || noSaptawara==6 && noPancawara==1){
            noPararasan = 8;
        } else if (noSaptawara==3 && noPancawara==5 || noSaptawara==4 && noPancawara==3 || noSaptawara==5 && noPancawara==2){
            noPararasan = 9;
        } else if (noSaptawara==3 && noPancawara==2 || noSaptawara==4 && noPancawara==5 || noSaptawara==6 && noPancawara==3){
            noPararasan = 10;
        } else if (noSaptawara==4 && noPancawara==2 || noSaptawara==6 && noPancawara==5){
            noPararasan = 11;
        } else if (noSaptawara==6 && noPancawara==2){
            noPararasan = 12;
        }
        return noPararasan;
    }

    /*** Fungsi menghitung rakam ***/
    public int getRakam(){

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

        int noRakam;
        int saptawara = 0, pancawara = 0;
        int noSaptawara = this.getSaptawara(NO_SAPTAWARA);
        int noPancawara = this.getPancawara(NO_PANCAWARA);

        switch (noSaptawara){
        case 0:saptawara = 3;break;
        case 1:saptawara = 4;break;
        case 2:saptawara = 5;break;
        case 3:saptawara = 6;break;
        case 4:saptawara = 7;break;
        case 5:saptawara = 1;break;
        case 6:saptawara = 2;break;
        }

        switch (noPancawara){
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
	public int getZodiak(){

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
		int M = this.get(Calendar.MONTH)+1;
		int D = this.get(Calendar.DATE);

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
	private void hitungSaka(){

		int bedaHari = (int)getDateDiff(pivot.getTimeInMillis(),this.getTimeInMillis());

		/* MENGHITUNG PENANGGAL PANGELONG ***/
		int hasilNgunaratri;
		int jumlahNgunaratri = 0;
		int mulai=0;

		int noWuku =  this.getWuku(NO_WUKU);
		int noPancawara = this.getPancawara(NO_PANCAWARA);
		int noSaptawara = this.getSaptawara(NO_SAPTAWARA);

		if (bedaHari >= 0){

			//mengetahui jumlah ngunaratri

			if (pivot.noNgunaratri > 63){
				if (pivot.noNgunaratri % 63 == 0){
					mulai = pivot.noNgunaratri-63;
				} else {
					mulai = pivot.noNgunaratri - (pivot.noNgunaratri % 63);
				}
			}

			this.noNgunaratri = pivot.noNgunaratri + bedaHari; //Masukkan no ngunaratri

			if (this.noNgunaratri > (mulai + 63)){
				jumlahNgunaratri = ((this.noNgunaratri - (mulai + 63))/63) + 1;
				if ((this.noNgunaratri - (mulai + 63))%63==0){jumlahNgunaratri--;}
			}


			if (pivot.isNgunaratri){jumlahNgunaratri++;} //Jika pivot adalah ngunaratri, tambah jumlah ngunaratri
			// Menghitung angka penanggal/pangelong, jika 0 maka diubah ke 15
			hasilNgunaratri = (bedaHari + pivot.penanggal + jumlahNgunaratri) % 15;
			if (hasilNgunaratri == 0) { hasilNgunaratri =15; }
			this.penanggal=hasilNgunaratri;
			// Menghitung apakah penanggal atau pangelong
			this.isPangelong = ((((bedaHari + pivot.penanggal + jumlahNgunaratri - 1) / 15) % 2) == 0) == pivot.isPangelong;

		}else{ // Jika tanggal yang dihitung sebelum tanggal pivot

			//mengetahui jumlah ngunaratri
			if ((pivot.noNgunaratri+63) > 63){
				if ((pivot.noNgunaratri+63) % 63 == 0){
					mulai = (pivot.noNgunaratri+63)-63;
				} else {
					mulai = (pivot.noNgunaratri+63) - ((pivot.noNgunaratri+63) % 63);
				}
			}

			this.noNgunaratri = pivot.noNgunaratri + bedaHari; //Masukkan no ngunaratri

			if (this.noNgunaratri < (mulai - 63)){
				jumlahNgunaratri = ((-(this.noNgunaratri - (mulai - 63)))/63) + 1;
				if ((-(this.noNgunaratri - (mulai - 63)))%63==0){jumlahNgunaratri--;}
			}

			// Menghitung angka penanggal/pangelong, jika 0 maka diubah ke 15
			hasilNgunaratri = bedaHari + pivot.penanggal - jumlahNgunaratri;

			hasilNgunaratri = 15 - (-hasilNgunaratri%15) ;

			this.penanggal=hasilNgunaratri;
			// Menghitung apakah penanggal atau pangelong
			this.isPangelong = ((((-bedaHari + this.penanggal + jumlahNgunaratri - 1) / 15) % 2) == 0) == pivot.isPangelong;
		}

		/* MENENTUKAN APAKAH NGUNARATRI ATAU TIDAK ***/
		this.isNgunaratri = false;
		if (this.get(Calendar.YEAR) > 1999 ){
			// Pengalantaka Eka Sungsang ke Pahing
			if (noSaptawara == 2){

				if (bedaHari > 0){

					if ((noWuku==10 && noPancawara==2 && (this.penanggal==14||this.penanggal==9||this.penanggal==4)) ||
							(noWuku==19 && noPancawara==5 && (this.penanggal==3||this.penanggal==13||this.penanggal==8)) ||
							(noWuku==28 && noPancawara==3 && (this.penanggal==7||this.penanggal==2||this.penanggal==12)) ||
							(noWuku==7 && noPancawara==1  && (this.penanggal==11||this.penanggal==6||this.penanggal==1)) ||
							(noWuku==16 && noPancawara==4 && (this.penanggal==15||this.penanggal==10||this.penanggal==5)) ||
							(noWuku==25 && noPancawara==2 && (this.penanggal==4||this.penanggal==14||this.penanggal==9))  ||
							(noWuku==4 && noPancawara==5  && (this.penanggal==8||this.penanggal==3||this.penanggal==13)) ||
							(noWuku==13 && noPancawara==3 && (this.penanggal==12||this.penanggal==7||this.penanggal==2)) ||
							(noWuku==22 && noPancawara==1 && (this.penanggal==1||this.penanggal==11||this.penanggal==6)) ||
							(noWuku==1 && noPancawara==4  && (this.penanggal==5||this.penanggal==15||this.penanggal==10))){

						this.isNgunaratri = true;

					}
				}else{
					if ((noWuku==10 && noPancawara==2 && (this.penanggal==15||this.penanggal==10||this.penanggal==5)) ||
							(noWuku==19 && noPancawara==5 && (this.penanggal==4||this.penanggal==14||this.penanggal==9)) ||
							(noWuku==28 && noPancawara==3 && (this.penanggal==8||this.penanggal==3||this.penanggal==13)) ||
							(noWuku==7 && noPancawara==1  && (this.penanggal==12||this.penanggal==7||this.penanggal==2)) ||
							(noWuku==16 && noPancawara==4 && (this.penanggal==1||this.penanggal==11||this.penanggal==6)) ||
							(noWuku==25 && noPancawara==2 && (this.penanggal==5||this.penanggal==15||this.penanggal==10))  ||
							(noWuku==4 && noPancawara==5  && (this.penanggal==9||this.penanggal==4||this.penanggal==14)) ||
							(noWuku==13 && noPancawara==3 && (this.penanggal==13||this.penanggal==8||this.penanggal==3)) ||
							(noWuku==22 && noPancawara==1 && (this.penanggal==2||this.penanggal==12||this.penanggal==7)) ||
							(noWuku==1 && noPancawara==4  && (this.penanggal==6||this.penanggal==1||this.penanggal==11))){

						this.isNgunaratri = true;
						this.penanggal = this.penanggal -1; //Jika ngunaratri mundur satu hari
						if (this.penanggal == 0 && !this.isPangelong) {this.isPangelong = true;} // Ubah pangelong menjadi true apabila mundur dari penanggal 1
						if (this.penanggal == 0) {this.penanggal = 15;} //Ubah penanggal jadi 15 jika pengurangan akibat ngunaratri menjadi 0
					}
				}
			}
		}else{
			// Pengalantaka Eka Sungsang ke Pon
			if (noSaptawara == 3){
				if (bedaHari > 0){

					if ((noWuku==10 && noPancawara==3 && (this.penanggal==14||this.penanggal==9||this.penanggal==4)) ||
							(noWuku==19 && noPancawara==1 && (this.penanggal==3||this.penanggal==13||this.penanggal==8)) ||
							(noWuku==28 && noPancawara==4 && (this.penanggal==7||this.penanggal==2||this.penanggal==12)) ||
							(noWuku==7 && noPancawara==2  && (this.penanggal==11||this.penanggal==6||this.penanggal==1)) ||
							(noWuku==16 && noPancawara==5 && (this.penanggal==15||this.penanggal==10||this.penanggal==5)) ||
							(noWuku==25 && noPancawara==3 && (this.penanggal==4||this.penanggal==14||this.penanggal==9))  ||
							(noWuku==4 && noPancawara==1  && (this.penanggal==8||this.penanggal==3||this.penanggal==13)) ||
							(noWuku==13 && noPancawara==4 && (this.penanggal==12||this.penanggal==7||this.penanggal==2)) ||
							(noWuku==22 && noPancawara==2 && (this.penanggal==1||this.penanggal==11||this.penanggal==6)) ||
							(noWuku==1 && noPancawara==5  && (this.penanggal==5||this.penanggal==15||this.penanggal==10))){

						this.isNgunaratri = true;

					}
				}else{
					if ((noWuku==10 && noPancawara==3 && (this.penanggal==15||this.penanggal==10||this.penanggal==5)) ||
							(noWuku==19 && noPancawara==1 && (this.penanggal==4||this.penanggal==14||this.penanggal==9)) ||
							(noWuku==28 && noPancawara==4 && (this.penanggal==8||this.penanggal==3||this.penanggal==13)) ||
							(noWuku==7 && noPancawara==2  && (this.penanggal==12||this.penanggal==7||this.penanggal==2)) ||
							(noWuku==16 && noPancawara==5 && (this.penanggal==1||this.penanggal==11||this.penanggal==6)) ||
							(noWuku==25 && noPancawara==3 && (this.penanggal==5||this.penanggal==15||this.penanggal==10))  ||
							(noWuku==4 && noPancawara==1  && (this.penanggal==9||this.penanggal==4||this.penanggal==14)) ||
							(noWuku==13 && noPancawara==4 && (this.penanggal==13||this.penanggal==8||this.penanggal==3)) ||
							(noWuku==22 && noPancawara==2 && (this.penanggal==2||this.penanggal==12||this.penanggal==7)) ||
							(noWuku==1 && noPancawara==5  && (this.penanggal==6||this.penanggal==1||this.penanggal==11))){

						this.isNgunaratri = true;
						this.penanggal = this.penanggal -1; //Jika ngunaratri mundur satu hari
						if (this.penanggal == 0 && !this.isPangelong) {this.isPangelong = true;} // Ubah pangelong menjadi true apabila mundur dari penanggal 1
						if (this.penanggal == 0) {this.penanggal = 15;} //Ubah penanggal jadi 15 jika pengurangan akibat ngunaratri menjadi 0
					}
				}
			}
		}


		/* MENGHITUNG SASIH ***/
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

		int hasilSasih;
		int jumlahNampih = 0;

		int tahunSaka = pivot.tahunSaka;
		int perulangan1 = 0;
		int perulangan2 = pivot.noSasih;
		boolean isNampih=false;

		if (this.get(Calendar.YEAR) > 2002 || this.get(Calendar.YEAR) < 1992 ){
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
							this.isNampih = perulangan2 == 12;
							if (perulangan2==1){perulangan2--;jumlahNampih++;isNampih=true;}
						}else if (((tahunSaka % 19) == 3)||((tahunSaka % 19) == 8)||((tahunSaka % 19) == 14)||((tahunSaka % 19) == 16)) {
							this.isNampih = perulangan2 == 1;
							if (perulangan2==2){perulangan2--;jumlahNampih++;isNampih=true;}
						}
					}
				}

				this.noSasih = (hasilSasih - jumlahNampih + pivot.noSasih)%12  ;
				if (this.isNampih){this.noSasih--;}

				if (this.noSasih < 0){
					this.noSasih = 12 - (-this.noSasih%12);
				}
				if (this.noSasih == 0 ){this.noSasih = 12;}

				this.tahunSaka = tahunSaka;

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
							this.isNampih = perulangan2 == 11;
							if (perulangan2==10){perulangan2++;jumlahNampih++;isNampih=true;}
						}else if (((tahunSaka % 19) == 3)||((tahunSaka % 19) == 8)||((tahunSaka % 19) == 14)||((tahunSaka % 19) == 16)) {
							this.isNampih = perulangan2 == 12;
							if (perulangan2==11){perulangan2++;jumlahNampih++;isNampih=true;}
						}
					}
				}

				this.noSasih = pivot.noSasih - hasilSasih + jumlahNampih;

				if (this.noSasih < 0){
					this.noSasih = 12 - (-this.noSasih%12);
				}
				if (this.noSasih == 0 ){this.noSasih = 12;}

				this.tahunSaka = tahunSaka;
				if (this.isPangelong && this.penanggal == 15 && this.isNgunaratri && this.isNampih){this.isNampih = false;} // Ubah isnampih menjadi false apabila berada di ngunaratri di awal penanggal
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
							this.isNampih = perulangan2 == 12;
							if (perulangan2==1){perulangan2--;jumlahNampih++;isNampih=true;}
						}else if (((tahunSaka % 19) == 4)) {
							this.isNampih = perulangan2 == 4;
							if (perulangan2==5){perulangan2--;jumlahNampih++;isNampih=true;}
						}else if (((tahunSaka % 19) == 7)) {
							this.isNampih = perulangan2 == 2;
							if (perulangan2==3){perulangan2--;jumlahNampih++;isNampih=true;}
						}else if (((tahunSaka % 19) == 13)) {
							this.isNampih = perulangan2 == 11;
							if (perulangan2==12){perulangan2--;jumlahNampih++;isNampih=true;}
						}else if (((tahunSaka % 19) == 15)) {
							this.isNampih = perulangan2 == 3;
							if (perulangan2==4){perulangan2--;jumlahNampih++;isNampih=true;}
						}else if (((tahunSaka % 19) == 18)) {
							this.isNampih = perulangan2 == 1;
							if (perulangan2==2){perulangan2--;jumlahNampih++;isNampih=true;}
						}
					}
				}

				this.noSasih = (hasilSasih - jumlahNampih + pivot.noSasih)%12  ;
				if (this.isNampih){this.noSasih--;}

				if (this.noSasih < 0){
					this.noSasih = 12 - (-this.noSasih%12);
				}
				if (this.noSasih == 0 ){this.noSasih = 12;}

				this.tahunSaka = tahunSaka;

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
							this.isNampih = perulangan2 == 11;
							if (perulangan2==10){perulangan2++;jumlahNampih++;isNampih=true;}
						}else if (((tahunSaka % 19) == 4)) {
							this.isNampih = perulangan2 == 3;
							if (perulangan2==2){perulangan2++;jumlahNampih++;isNampih=true;}
						}else if (((tahunSaka % 19) == 7)) {
							this.isNampih = perulangan2 == 1;
							if (perulangan2==12){perulangan2++;jumlahNampih++;isNampih=true;}
						}else if (((tahunSaka % 19) == 13)) {
							this.isNampih = perulangan2 == 10;
							if (perulangan2==9){perulangan2++;jumlahNampih++;isNampih=true;}
						}else if (((tahunSaka % 19) == 15)) {
							this.isNampih = perulangan2 == 2;
							if (perulangan2==1){perulangan2++;jumlahNampih++;isNampih=true;}
						}else if (((tahunSaka % 19) == 18)) {
							this.isNampih = perulangan2 == 12;
							if (perulangan2==11){perulangan2++;jumlahNampih++;isNampih=true;}
						}
					}
				}

				this.noSasih = pivot.noSasih - hasilSasih + jumlahNampih;

				if (this.noSasih < 0){
					this.noSasih = 12 - (-this.noSasih%12);
				}
				if (this.noSasih == 0 ){this.noSasih = 12;}


				this.tahunSaka = tahunSaka;
				if (this.isPangelong && this.penanggal == 15 && this.isNgunaratri && this.isNampih){this.isNampih = false;} // Ubah is nampih menjadi false apabila berada di ngunaratri di awal penanggal
			}
		}

	}

	public int getSakaCalendar(int field) {
		if(this.tahunSaka == 0){
			hitungSaka();
		}
		switch (field){
			case TAHUN_SAKA: return this.tahunSaka;
			case PENANGGAL: return this.penanggal;
			case NO_NGUNARATRI: return noNgunaratri;
			case NO_SASIH: return noSasih;
			default: return this.tahunSaka;
		}
	}

	public boolean getSakaCalendarStatus(int field) {
		if(this.tahunSaka == 0){
			hitungSaka();
		}
		switch (field){
			case NGUNARATRI: return this.isNgunaratri;
			case PANGELONG: return this.isPangelong;
			case NAMPIH: return isNampih;
			default: return this.isNgunaratri;
		}
	}

}