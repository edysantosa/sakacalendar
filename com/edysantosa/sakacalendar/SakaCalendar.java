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
import java.time.temporal.ChronoUnit;
import java.util.*;

public class SakaCalendar extends GregorianCalendar {

	public int angkaWuku;
	public int noWuku;
	public int uripWuku;
	public int uripPancawara;
	public int uripSaptawara;
	public int noEkawara;
	public int noDwiwara;
	public int noTriwara;
	public int noCaturwara;
	public int noPancawara;
	public int noSadwara;
	public int noSaptawara;
	public int noAstawara;
	public int noSangawara;
	public int noDasawara;

	// Kalender Saka
	public int tahunSaka;
	public int penanggal;
	public boolean isPangelong;
	public boolean isNgunaratri;
	public int noNgunaratri; //Jumlah hari sejak nemugelang pengalantaka
	public int noSasih;
	public boolean isNampih;
	public String namaSasih;


	//Untuk perhitungan
	private SakaCalendar pivot;
	private ArrayList<SakaCalendar> pivots;

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
		//setPivots();
		//SakaCalendar x = this.getPivot(this);
		//SakaCalendar x = new SakaCalendar(1991,0,1);
		//System.out.println(this.get(Calendar.DATE));
	}
	/*** Fungsi menambahkan tanggal-tanggal pivot ***/
	public void setPivots() {
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
	public SakaCalendar getPivot(SakaCalendar closestOf){

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
	public int getDateDiff( SakaCalendar d1, SakaCalendar d2) {

		/*
		Catatan :
			Kode lebih cantik jika menggunakan class baru dari java 8, java.time

			"return ChronoUnit.DAYS.between(d1.toInstant(), d2.toInstant());"

			Akan tetapi, setelah testing performanya lebih rendah rata-rata 1 s/d 2 milisecond.


			TO DO:
			 	- Bisakah perfforma lebih ditingkatkan?

		 */

		double x = (d2.getTimeInMillis() - d1.getTimeInMillis()) / (1000d * 60 * 60 * 24);
		return (int)x;
	}




}