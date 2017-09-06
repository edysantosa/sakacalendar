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

/**
 * The type Saka calendar.
 */
public class SakaCalendar extends GregorianCalendar {

	/**
	 * Constant untuk mendapatkan <code>noWuku</code> dari method {@link SakaCalendar#getWuku(int)  getWuku()}.
	 * @see #ANGKA_WUKU
	 * @see #URIP_WUKU
	 */
	public final static int NO_WUKU = 0;
	/**
	 * Constant untuk mendapatkan <code>angkaWuku</code> dari method {@link SakaCalendar#getWuku(int)  getWuku}.
	 * @see #NO_WUKU
	 * @see #URIP_WUKU
	 */
	public final static int ANGKA_WUKU = 1;
	/**
	 * Constant untuk mendapatkan <code>uripWuku</code> dari method {@link SakaCalendar#getWuku(int)  getWuku}.
	 * @see #NO_WUKU
	 * @see #ANGKA_WUKU
	 */
	public final static int URIP_WUKU = 2;
	/**
	 * Constant untuk mendapatkan <code>noSaptawara</code> dari method {@link SakaCalendar#getSaptawara(int)  getSaptawara}.
	 * @see #URIP_SAPTAWARA
	 */
	public final static int NO_SAPTAWARA = 0;
	/**
	 * Constant untuk mendapatkan <code>uripSaptawara</code> dari method {@link SakaCalendar#getSaptawara(int)  getSaptawara}.
	 * @see #NO_SAPTAWARA
	 */
	public final static int URIP_SAPTAWARA = 1;
	/**
	 * Constant untuk mendapatkan <code>noPancawara</code> dari method {@link SakaCalendar#getPancawara(int)  getPancawara}.
	 * @see #URIP_PANCAWARA
	 */
	public final static int NO_PANCAWARA = 0;
	/**
	 * Constant untuk mendapatkan <code>uripPancawara</code> dari method {@link SakaCalendar#getPancawara(int)  getPancawara}.
	 * @see #NO_PANCAWARA
	 */
	public final static int URIP_PANCAWARA = 1;

	/**
	 * Constant untuk mendapatkan <code>tahunSaka</code> dari method {@link SakaCalendar#getSakaCalendar(int)  getSakaCalendar}.
	 * @see #PENANGGAL
	 * @see #NO_SASIH
	 * @see #NO_NGUNARATRI
	 */
	public final static int TAHUN_SAKA = 0;
	/**
	 * Constant untuk mendapatkan <code>penanggal</code> dari method {@link SakaCalendar#getSakaCalendar(int)  getSakaCalendar}.
	 * @see #TAHUN_SAKA
	 * @see #NO_SASIH
	 * @see #NO_NGUNARATRI
	 */
	public final static int PENANGGAL = 1;
	/**
	 * Constant untuk mendapatkan <code>noSasih</code> dari method {@link SakaCalendar#getSakaCalendar(int)  getSakaCalendar}.
	 * @see #TAHUN_SAKA
	 * @see #PENANGGAL
	 * @see #NO_NGUNARATRI
	 */
	public final static int NO_SASIH = 2;
	/**
	 * Constant untuk mendapatkan <code>noNgunaratri</code> dari method {@link SakaCalendar#getSakaCalendar(int)  getSakaCalendar}.
	 * @see #TAHUN_SAKA
	 * @see #PENANGGAL
	 * @see #NO_SASIH
	 */
	public final static int NO_NGUNARATRI = 3;
	/**
	 * Constant untuk mendapatkan status apakah tanggal pada instance adalah ngunaratri atau bukan dari method {@link SakaCalendar#getSakaCalendarStatus(int)  getSakaCalendaStatusr}.
	 * @see #IS_PANGELONG
	 * @see #IS_NAMPIH
	 */
	public final static int IS_NGUNARATRI = 0;
	/**
	 * Constant untuk mendapatkan status apakah tanggal pada instance adalah pangelong atau penanggal dari method {@link SakaCalendar#getSakaCalendarStatus(int)  getSakaCalendaStatusr}.
	 * @see #IS_NGUNARATRI
	 * @see #IS_NAMPIH
	 */
	public final static int IS_PANGELONG = 1;
	/**
	 * Constant untuk mendapatkan status apakah tanggal pada instance adalah nampih sasih atau bukan dari method {@link SakaCalendar#getSakaCalendarStatus(int)  getSakaCalendaStatusr}.
	 * @see #IS_NGUNARATRI
	 * @see #IS_PANGELONG
	 */
	public final static int IS_NAMPIH = 2;


	/**
	 * Class ini digunakan sebagai parameter acuan dalam melakukan perhitungan kalender bali
	 */
	SakaCalendarPivot pivot;

	private int tahunSaka;
	private int penanggal;
	private int noNgunaratri;
	private int noSasih;
	private boolean isNgunaratri;
	private boolean isPangelong;
	private boolean isNampih;
	// TODO : Tambah disini

	/**
	 * Membuat sebuah <code>SakaCalendar</code> pada hari saat ini.
	 */
	public SakaCalendar(){
		super();
		this.initialize();
	}

	/**
	 * Membuat sebuah <code>SakaCalendar</code> dengan parameter yang diberikan.
	 *
	 * @param year nilai untuk mengeset tahun.
	 * @param month nilai untuk mengeset tahun.
	 * Parameter adalah 0-based.  e.g., 0 -> January.
	 * @param dayOfMonth nilai untuk mengeset tanggal.
	 */
	public SakaCalendar(int year, int month, int dayOfMonth){
		super(year, month , dayOfMonth);
		this.initialize();
	}

	private void initialize(){
		this.pivot = getPivots(this.getTimeInMillis());
	}

	/**
	 * Method mendapatkan tanggal pivot
	 */
	private SakaCalendarPivot getPivots(long timestamp) {
		SakaCalendarPivot pivot = new SakaCalendarPivot();
		if (timestamp >= 946684800 * 1000L - pivot.get(Calendar.ZONE_OFFSET ) ){
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
			pivot.setTimeInMillis(0 - pivot.get(Calendar.ZONE_OFFSET )); //1970-01-01
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


	/**
	 * Method untuk menghitung perbedaan hari antara 2 Timestamp
	 */
	private long getDateDiff(long d1, long d2) {

		/* Catatan :
		 * Kode lebih cantik jika menggunakan class baru dari java 8.
		 * java.time "return ChronoUnit.DAYS.between(d1.toInstant(), d2.toInstant());"
		 * Akan tetapi, setelah testing performanya lebih rendah rata-rata 2 s/d 3 milisecond.
		 */

		double x = (d2 - d1) / (1000d * 60 * 60 * 24);
		return (long)x;
	}

	/**
	 * Inner class untuk menampung tanggal pivot yang akan digunakan dalam perhitungan kalendar Saka.
	 */
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

	/**
	 * <p>
	 * Wuku merupakan perhitungan tahun yang cukup unik, karena perhitungannya tidak mengacu pada peredaran benda-benda angkasa, tanpa bergantung pada perhitungan tahun surya (solar calendar) maupun tahun candra (lunar calendar). Satu tahun Wuku panjangnya adalah 420 hari, yang terdiri dari 30 Wuku dengan panjang 210 hari dikalikan 2.
	 * Setiap Wuku panjangnya 7 hari terhitung dari Redite, Coma, Anggara, Buda, Wrhaspati, Sukra, dan Saniscara. Satu bulan dalam perhitungan tahun Wuku didapat dari 5 Wuku. Jadi 1 bulan dalam tahun Wuku adalah 35 hari yang didapat dengan mengalikan 7 hari dengan 5 Wuku.
	 * <p>
	 * Satu peredaran Wuku (30 Wuku) termasuk 6 bulan dalam tahun Wuku, 6 bulan ini didapat dengan mengalikan jumlah hari dalam 1 Wuku dengan jumlah Wuku (7 hari x 30 Wuku = 210 hari). 1 tahun Wuku terdiri dari 2 kali peredaran Wuku, yakni 7 hari x 30 Wuku x 2 = 420 hari.
	 * <p>
	 * Penamaan Wuku diambil dari nama-nama raja dalam mitologi Sang Watu Gunung.
	 *
	 * Informasi yang bisa didapatkan dari fungsi ini antara lain:
	 *
	 * <p><strong><code>angkaWuku</code></strong>. Adalah jumlah hari yang telah berlalu dalam wuku saat ini. e.g., 1 -> hari pertama Wuku dan 210 -> hari terakhir wuku </p>
	 * <p><strong><code>noWuku</code></strong>. Merupakan representasi numerik dari Wuku. e.g., 1 -> Sinta, 2-> Landep dan seterusnya.</p>
	 * <p><strong><code>uripWuku</code></strong>. Nilai urip/neptu dari Wuku.</p>
	 *
	 * <p>
	 * Berikut ini adalah tabel wuku beserta neptunya(urip).
	 *
	 * <table cellpadding="0" cellspacing="3" border="0"
	 *        summary="SakaCalendar"
	 *        style="text-align: left; width: 66%;">
	 *     <tbody>
	 *         <tr>
	 *             <th style="vertical-align: top; background-color: rgb(204, 204, 255);
	 *           text-align: center;">No Wuku</th>
	 *             <th style="vertical-align: top; background-color: rgb(204, 204, 255);
	 *           text-align: center;">Nama Raja</th>
	 *             <th style="vertical-align: top; background-color: rgb(204, 204, 255);
	 *           text-align: center;">Nama Wuku</th>
	 *             <th style="vertical-align: top; background-color: rgb(204, 204, 255);
	 *           text-align: center;">Neptu/Urip</th>
	 *         </tr>
	 *         <tr>
	 *             <td style="text-align: center;">1</td>
	 *             <td>Dewi Sintakasih</td>
	 *             <td>Sinta</td>
	 *             <td style="text-align: center;">7</td>
	 *         </tr>
	 *         <tr>
	 *             <td style="text-align: center;">2</td>
	 *             <td>Dewi Sanjiwartia</td>
	 *             <td>Landep</td>
	 *             <td style="text-align: center;">1</td>
	 *         </tr>
	 *         <tr>
	 *             <td style="text-align: center;">3</td>
	 *             <td>Giriswara</td>
	 *             <td>Ukir</td>
	 *             <td style="text-align: center;">4</td>
	 *         </tr>
	 *         <tr>
	 *             <td style="text-align: center;">4</td>
	 *             <td>Kuladewa</td>
	 *             <td>Kulantir</td>
	 *             <td style="text-align: center;">6</td>
	 *         </tr>
	 *         <tr>
	 *             <td style="text-align: center;">5</td>
	 *             <td>Talu</td>
	 *             <td>Tolu</td>
	 *             <td style="text-align: center;">5</td>
	 *         </tr>
	 *         <tr>
	 *             <td style="text-align: center;">6</td>
	 *             <td>Mrabuana</td>
	 *             <td>Gumbreg</td>
	 *             <td style="text-align: center;">8</td>
	 *         </tr>
	 *         <tr>
	 *             <td style="text-align: center;">7</td>
	 *             <td>Waksaya</td>
	 *             <td>Wariga</td>
	 *             <td style="text-align: center;">9</td>
	 *         </tr>
	 *         <tr>
	 *             <td style="text-align: center;">8</td>
	 *             <td>Wariwiyasa</td>
	 *             <td>Warigadean</td>
	 *             <td style="text-align: center;">3</td>
	 *         </tr>
	 *         <tr>
	 *             <td style="text-align: center;">9</td>
	 *             <td>Mrikjulung</td>
	 *             <td>Julungwangi</td>
	 *             <td style="text-align: center;">7</td>
	 *         </tr>
	 *         <tr>
	 *             <td style="text-align: center;">10</td>
	 *             <td>Sungsangtaya</td>
	 *             <td>Sungsang</td>
	 *             <td style="text-align: center;">1</td>
	 *         </tr>
	 *         <tr>
	 *             <td style="text-align: center;">11</td>
	 *             <td>Dungulan</td>
	 *             <td>Dungulan</td>
	 *             <td style="text-align: center;">4</td>
	 *         </tr>
	 *         <tr>
	 *             <td style="text-align: center;">12</td>
	 *             <td>Puspita</td>
	 *             <td>Kuningan</td>
	 *             <td style="text-align: center;">6</td>
	 *         </tr>
	 *         <tr>
	 *             <td style="text-align: center;">13</td>
	 *             <td>Langkir</td>
	 *             <td>Langkir</td>
	 *             <td style="text-align: center;">5</td>
	 *         </tr>
	 *         <tr>
	 *             <td style="text-align: center;">14</td>
	 *             <td>Medangsu</td>
	 *             <td>Medangsya</td>
	 *             <td style="text-align: center;">8</td>
	 *         </tr>
	 *         <tr>
	 *             <td style="text-align: center;">15</td>
	 *             <td>Pujitpwa</td>
	 *             <td>Pujut</td>
	 *             <td style="text-align: center;">9</td>
	 *         </tr>
	 *         <tr>
	 *             <td style="text-align: center;">16</td>
	 *             <td>Paha</td>
	 *             <td>Pahang</td>
	 *             <td style="text-align: center;">3</td>
	 *         </tr>
	 *         <tr>
	 *             <td style="text-align: center;">17</td>
	 *             <td>Kruru</td>
	 *             <td>Kerulut</td>
	 *             <td style="text-align: center;">7</td>
	 *         </tr>
	 *         <tr>
	 *             <td style="text-align: center;">18</td>
	 *             <td>Merangsinga</td>
	 *             <td>Merakih</td>
	 *             <td style="text-align: center;">1</td>
	 *         </tr>
	 *         <tr>
	 *             <td style="text-align: center;">19</td>
	 *             <td>Tambur</td>
	 *             <td>Tambir</td>
	 *             <td style="text-align: center;">4</td>
	 *         </tr>
	 *         <tr>
	 *             <td style="text-align: center;">20</td>
	 *             <td>Medangkusa</td>
	 *             <td>Medangkungan</td>
	 *             <td style="text-align: center;">6</td>
	 *         </tr>
	 *         <tr>
	 *             <td style="text-align: center;">21</td>
	 *             <td>Matal</td>
	 *             <td>Matal</td>
	 *             <td style="text-align: center;">5</td>
	 *         </tr>
	 *         <tr>
	 *             <td style="text-align: center;">22</td>
	 *             <td>Uye</td>
	 *             <td>Uye</td>
	 *             <td style="text-align: center;">8</td>
	 *         </tr>
	 *         <tr>
	 *             <td style="text-align: center;">23</td>
	 *             <td>Ijala</td>
	 *             <td>Menahil</td>
	 *             <td style="text-align: center;">9</td>
	 *         </tr>
	 *         <tr>
	 *             <td style="text-align: center;">24</td>
	 *             <td>Yuddha</td>
	 *             <td>Perangbakat</td>
	 *             <td style="text-align: center;">3</td>
	 *         </tr>
	 *         <tr>
	 *             <td style="text-align: center;">25</td>
	 *             <td>Baliraja</td>
	 *             <td>Bala</td>
	 *             <td style="text-align: center;">7</td>
	 *         </tr>
	 *         <tr>
	 *             <td style="text-align: center;">26</td>
	 *             <td>Wiugah</td>
	 *             <td>Ugu</td>
	 *             <td style="text-align: center;">1</td>
	 *         </tr>
	 *         <tr>
	 *             <td style="text-align: center;">27</td>
	 *             <td>Ringgita</td>
	 *             <td>Wayang</td>
	 *             <td style="text-align: center;">4</td>
	 *         </tr>
	 *         <tr>
	 *             <td style="text-align: center;">28</td>
	 *             <td>Kulawudra</td>
	 *             <td>Kelawu</td>
	 *             <td style="text-align: center;">6</td>
	 *         </tr>
	 *         <tr>
	 *             <td style="text-align: center;">29</td>
	 *             <td>Sasawi</td>
	 *             <td>Dukut</td>
	 *             <td style="text-align: center;">5</td>
	 *         </tr>
	 *         <tr>
	 *             <td style="text-align: center;">30</td>
	 *             <td>Watugunung</td>
	 *             <td>Watugunung</td>
	 *             <td style="text-align: center;">8</td>
	 *         </tr>
	 *     </tbody>
	 * </table>
	 * @param field Constant yang merepresentasikan informasi yang diinginkan {@link SakaCalendar#NO_WUKU NO_WUKU}, {@link SakaCalendar#ANGKA_WUKU ANGKA_WUKU}, dan {@link SakaCalendar#URIP_WUKU URIP_WUKU}.
	 * @return informasi yang diinginkan.
	 */
	public int getWuku(int field){

		long bedaHari = getDateDiff(pivot.getTimeInMillis(),this.getTimeInMillis());
		int angkaWuku;
		int uripWuku;
		int noWuku;

		angkaWuku = (int)(pivot.angkaWuku + bedaHari)%210 ;
		if (angkaWuku < 0){
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

	/**
	 * Informasi yang bisa didapatkan dari fungsi ini antara lain:
	 * <p><strong><code>noSaptawara</code></strong>. Merupakan representasi numerik dari Saptawara. <code>noSaptawara</code> adalah 0-based. e.g., 0 -> Redite, 1-> Soma dan seterusnya.</p>
	 * <p><strong><code>uripSaptawara</code></strong>. Nilai urip/neptu dari Saptawara.</p>
	 * <p>
	 * Perhitungan <code>noSaptawara</code> hanya dilakukan menggunakan DAY_OF_WEEK dari SakaCalendar.
	 *
	 * <table cellpadding="0" cellspacing="3" border="0"
	 *        summary="Sapta Wara"
	 *        style="text-align: left; width: 66%;">
	 *     <tbody>
	 *         <tr>
	 *             <th style="vertical-align: top; background-color: rgb(204, 204, 255);
	 *           text-align: center;">No Sapta Wara</th>
	 *             <th style="vertical-align: top; background-color: rgb(204, 204, 255);
	 *           text-align: center;">Sapta Wara</th>
	 *             <th style="vertical-align: top; background-color: rgb(204, 204, 255);
	 *           text-align: center;">Urip/Neptu</th>
	 *         </tr>
	 *         <tr>
	 *             <td style="text-align: center;">0</td>
	 *             <td>Redite</td>
	 *             <td style="text-align: center;">5</td>
	 *         </tr>
	 *         <tr>
	 *             <td style="text-align: center;">1</td>
	 *             <td>Coma</td>
	 *             <td style="text-align: center;">4</td>
	 *         </tr>
	 *         <tr>
	 *             <td style="text-align: center;">2</td>
	 *             <td>Anggara</td>
	 *             <td style="text-align: center;">3</td>
	 *         </tr>
	 *         <tr>
	 *             <td style="text-align: center;">3</td>
	 *             <td>Buddha</td>
	 *             <td style="text-align: center;">7</td>
	 *         </tr>
	 *         <tr>
	 *             <td style="text-align: center;">4</td>
	 *             <td>Wrhaspati</td>
	 *             <td style="text-align: center;">8</td>
	 *         </tr>
	 *         <tr>
	 *             <td style="text-align: center;">5</td>
	 *             <td>Sukra</td>
	 *             <td style="text-align: center;">6</td>
	 *         </tr>
	 *         <tr>
	 *             <td style="text-align: center;">6</td>
	 *             <td>Saniscara</td>
	 *             <td style="text-align: center;">9</td>
	 *         </tr>
	 *     </tbody>
	 * </table>
	 *
	 * @param field Constant yang merepresentasikan informasi yang diinginkan {@link SakaCalendar#NO_SAPTAWARA NO_SAPTAWARA}, dan {@link SakaCalendar#NO_SAPTAWARA NO_SAPTAWARA}.
	 * @return informasi yang diinginkan.
	 */
	public int getSaptawara(int field){

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

	/**
	 * Informasi yang bisa didapatkan dari fungsi ini antara lain:
	 * <p><strong><code>noPancawara</code></strong>. Merupakan representasi numerik dari Pancawara. e.g., 1 -> Umanis, 2-> Pahing dan seterusnya.</p>
	 * <p><strong><code>uripPancawara</code></strong>. Nilai urip/neptu dari Pancawara.</p>
	 *
	 * <p>
	 * Perhitungan <code>noPancawara</code> pada fungsi ini menggunakan rumus <code>(modulus 5 dari angka pawukon) + 1</code>.
	 * Namun, terdapat rumus lainnya yaitu <code>(No. wuku * 7 + No. Sapta Wara) mod 5<code>, dimana bila sisa 1 Umanis, sisa 2 Pahing, sisa 3 Pon, sisa 4 Wage, dan apabila habis terbagi adalah Kliwon.
	 * </p>
	 *
	 * <table cellpadding="0" cellspacing="3" border="0"
	 *        summary="Panca Wara"
	 *        style="text-align: left; width: 66%;">
	 *     <tbody>
	 *         <tr>
	 *             <th style="vertical-align: top; background-color: rgb(204, 204, 255);
	 *           text-align: center;">No Panca Wara</th>
	 *             <th style="vertical-align: top; background-color: rgb(204, 204, 255);
	 *           text-align: center;">Panca Wara</th>
	 *             <th style="vertical-align: top; background-color: rgb(204, 204, 255);
	 *           text-align: center;">Urip/Neptu</th>
	 *         </tr>
	 *         <tr>
	 *             <td style="text-align: center;">1</td>
	 *             <td>Umanis</td>
	 *             <td style="text-align: center;">5</td>
	 *         </tr>
	 *         <tr>
	 *             <td style="text-align: center;">2</td>
	 *             <td>Pahing</td>
	 *             <td style="text-align: center;">9</td>
	 *         </tr>
	 *         <tr>
	 *             <td style="text-align: center;">3</td>
	 *             <td>Pon</td>
	 *             <td style="text-align: center;">7</td>
	 *         </tr>
	 *         <tr>
	 *             <td style="text-align: center;">4</td>
	 *             <td>Wage</td>
	 *             <td style="text-align: center;">4</td>
	 *         </tr>
	 *         <tr>
	 *             <td style="text-align: center;">5</td>
	 *             <td>Kliwon</td>
	 *             <td style="text-align: center;">8</td>
	 *         </tr>
	 *     </tbody>
	 * </table>
	 *
	 * @param field Constant yang merepresentasikan informasi yang diinginkan {@link SakaCalendar#NO_PANCAWARA NO_PANCAWARA}, dan {@link SakaCalendar#URIP_PANCAWARA URIP_PANCAWARA}.
	 * @return informasi yang diinginkan.
	 *
	 */
	public int getPancawara(int field){

		int noPancawara = (this.getWuku(ANGKA_WUKU) % 5) + 1 ;
		int uripPancawara;

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
		default:
			uripPancawara = 5;break;
		}

		switch(field){
			case NO_PANCAWARA: return noPancawara;
			case URIP_PANCAWARA: return uripPancawara;
			default: return noPancawara;
		}		
	}

	/**
	 * Informasi yang bisa didapatkan dari fungsi ini adalah <code>noTriwara</code></strong> yang merupakan representasi numerik dari Tri Wara. 1 -> Pasah, 2-> Beteng dan seterusnya.</p>
	 *
	 * <p>
	 * Perhitungan <code>noTriwara</code> pada fungsi ini menggunakan rumus <code>angkaWuku mod 3</code>. Sisa 1 adalah Pasah, 2 adalah Beteng, 0 adalah Kajeng.
	 * Rumus lainnya yaitu <code>(noWuku dikalikan dengan 7  ditambah dengan noSaptawara) mod 3<code>.
	 * </p>
	 *
	 * <table cellpadding="0" cellspacing="3" border="0"
	 *        summary="Tri Wara"
	 *        style="text-align: left; width: 66%;">
	 *     <tbody>
	 *         <tr>
	 *             <th style="vertical-align: top; background-color: rgb(204, 204, 255);
	 *           text-align: center;">No Tri Wara</th>
	 *             <th style="vertical-align: top; background-color: rgb(204, 204, 255);
	 *           text-align: center;">Tri Wara</th>
	 *             <th style="vertical-align: top; background-color: rgb(204, 204, 255);
	 *           text-align: center;">Urip/Neptu</th>
	 *         </tr>
	 *         <tr>
	 *             <td style="text-align: center;">1</td>
	 *             <td>Dora/Pasah</td>
	 *             <td style="text-align: center;">9</td>
	 *         </tr>
	 *         <tr>
	 *             <td style="text-align: center;">2</td>
	 *             <td>Wahya/Beteng</td>
	 *             <td style="text-align: center;">4</td>
	 *         </tr>
	 *         <tr>
	 *             <td style="text-align: center;">3</td>
	 *             <td>Byantara/Kajeng</td>
	 *             <td style="text-align: center;">7</td>
	 *         </tr>
	 *     </tbody>
	 * </table>
	 *
	 * @return noTriwara.
	 *
	 */
	public int getTriwara(){
		int noTriwara = this.getWuku(ANGKA_WUKU) % 3;
		if (noTriwara == 0){noTriwara = 3;}
		return noTriwara;
	}

	/**
	 * Informasi yang bisa didapatkan dari fungsi ini adalah <code>noEkawara</code></strong> yang merupakan representasi numerik dari Eka Wara. 1 -> Luang, 0-> bukan Luang(kosong).</p>
	 *
	 * <p>
	 * Perhitungan <code>noEkawara</code> dilakukan dengan menambahkan urip Sapta Wara dan Panca Wara, Luang didapatkan jika hasil penjumlahan merupakan angka ganjil.
	 * </p>
	 *
	 * <table cellpadding="0" cellspacing="3" border="0"
	 *        summary="Eka Wara"
	 *        style="text-align: left; width: 66%;">
	 *     <tbody>
	 *         <tr>
	 *             <th style="vertical-align: top; background-color: rgb(204, 204, 255);
	 *           text-align: center;">No Eka Wara</th>
	 *             <th style="vertical-align: top; background-color: rgb(204, 204, 255);
	 *           text-align: center;">Eka Wara</th>
	 *             <th style="vertical-align: top; background-color: rgb(204, 204, 255);
	 *           text-align: center;">Urip/Neptu</th>
	 *         </tr>
	 *         <tr>
	 *             <td style="text-align: center;">1</td>
	 *             <td>Luang</td>
	 *             <td style="text-align: center;">1</td>
	 *         </tr>
	 *     </tbody>
	 * </table>
	 *
	 * @return noEkawara.
	 *
	 */
	public int getEkawara(){
		int noEkawara = (this.getPancawara(URIP_PANCAWARA) + this.getSaptawara(URIP_SAPTAWARA)) % 2;
		if (noEkawara!=0){
			return 1; //Jika tidak habis dibagi 2 maka luang
		}else{
			return 0; //Jika habis dibagi 2 maka bukan luang 
		}		
	}

	/**
	 * Informasi yang bisa didapatkan dari fungsi ini adalah <code>noDwiwara</code></strong> yang merupakan representasi numerik dari Dwi Wara. 1 -> Menga, 2-> bukan Pepet.</p>
	 *
	 * <p>
	 * Perhitungan <code>noDwiwara</code> mirip dengan Eka Wara, jika hasil penjumlahan urip Sapta Wara dan Panca Wara adalah ganjil maka didapatkan Dwi Wara Pepet, sedangkan Menga jika genap.
	 * </p>
	 *
	 * <table cellpadding="0" cellspacing="3" border="0"
	 *        summary="Dwi Wara"
	 *        style="text-align: left; width: 66%;">
	 *     <tbody>
	 *         <tr>
	 *             <th style="vertical-align: top; background-color: rgb(204, 204, 255);
	 *           text-align: center;">No Dwi Wara</th>
	 *             <th style="vertical-align: top; background-color: rgb(204, 204, 255);
	 *           text-align: center;">Dwi Wara</th>
	 *             <th style="vertical-align: top; background-color: rgb(204, 204, 255);
	 *           text-align: center;">Urip/Neptu</th>
	 *         </tr>
	 *         <tr>
	 *             <td style="text-align: center;">1</td>
	 *             <td>Menga</td>
	 *             <td style="text-align: center;">5</td>
	 *         </tr>
	 *         <tr>
	 *             <td style="text-align: center;">2</td>
	 *             <td>Pepet</td>
	 *             <td style="text-align: center;">7</td>
	 *         </tr>
	 *     </tbody>
	 * </table>
	 *
	 * @return noDwiwara.
	 *
	 */
	public int getDwiwara(){
		int noDwiwara = (this.getPancawara(URIP_PANCAWARA) + this.getSaptawara(URIP_SAPTAWARA)) % 2;
		if (noDwiwara==0){
			return 1; //Jika habis dibagi 2 maka menga
		}else{
			return 2; //Jika tidak habis dibagi 2 maka pepet
		}		
	}

	/**
	 * Informasi yang bisa didapatkan dari fungsi ini adalah <code>noCaturwara</code></strong> yang merupakan representasi numerik dari Catur Wara. 1 -> Sri, 2-> Laba.</p>
	 *
	 * <p>Dalam mencari Catur Wara, ada dua rumus yang dipakai,
	 * yakni rumus yang berlaku dari wuku Sinta hari (wara) Redite hingga wuku Dungulan dari (wara) Redite;
	 * dan rumus yang berlaku dari wuku Dungulan hari (wara) Budha hingga wuku Watugunung hari (wara) Saniscara.
	 * Ini terjadi karena ada hitungan pengecualian yang dikenal dengan istilah Jaya Tiga
	 * dimulai dari hari Redite wuku Dungulan hingga Angkara wuku Dungulan,
	 * jadi pada hari Coma dan Anggara wuku Dungulan adalah pengecualian.</p>
	 *
	 * <p>Rumus pertama nomor wuku dikali 7 ditambah 2 ditambah nomor Sapta Wara dibagi 4;
	 * dan rumus kedua Wuku dikali 7 ditambah nomor Sapta Wara di bagi 4.
	 * Apabila sisa pembagian berjumlah 1 artinya Sri, sisa 2 Laba,  sisa 3 Jaya, dan jika habis terbagi terhitung Menala.</p>
	 *
	 * <p><em>Contoh. </em> (Berlaku dari Redite wuku Shinta hingga Redite wuku Dungulan / rumus pertama)
	 * Wuku Dungulan = 11, dan Sapta Wara Redite = 0. Maka 11 x 7 + 2 + 0 : 4 = 19 sisa 3.
	 * Jadi pada wuku Dungulan hari (wara) Redite, Catur Waranya adalah Jaya.</p>
	 *
	 * <p><em>Contoh 2. </em> (berlaku dari Budha wuku Dungulan hingga Saniscara wuku Watugunung / rumus kedua)
	 * Wuku Langkir = 13 ; Spta Wara Sukra = 5. Maka 13 x 7 + 5 : 4 = 24 tanpa sisa (0).
	 * Jadi Catur Wara dalam wuku Langkir, hari (wara) Sukra adalah Mandala.</p>
	 *
	 * <p>Pada fungsi ini, dengan menggunakan angkaWuku perhitungan
	 * rumus pertama menjadi <code>(angkaWuku + 2) mod 4</code>,
	 * rumus kedua (jika angka wuku <= 70) menjadi <code>angkaWuku mod 4</code>, dan untuk Jaya Tiga pada angka wuku [71,72,73]</p>
	 *
	 * <table cellpadding="0" cellspacing="3" border="0"
	 *        summary="Catur Wara"
	 *        style="text-align: left; width: 66%;">
	 *     <tbody>
	 *         <tr>
	 *             <th style="vertical-align: top; background-color: rgb(204, 204, 255);
	 *           text-align: center;">No Catur Wara</th>
	 *             <th style="vertical-align: top; background-color: rgb(204, 204, 255);
	 *           text-align: center;">Catur Wara</th>
	 *             <th style="vertical-align: top; background-color: rgb(204, 204, 255);
	 *           text-align: center;">Neptu/Urip</th>
	 *         </tr>
	 *         <tr>
	 *             <td style="text-align: center;">1</td>
	 *             <td>Sri</td>
	 *             <td style="text-align: center;">4</td>
	 *         </tr>
	 *         <tr>
	 *             <td style="text-align: center;">2</td>
	 *             <td>Laba</td>
	 *             <td style="text-align: center;">5</td>
	 *         </tr>
	 *         <tr>
	 *             <td style="text-align: center;">3</td>
	 *             <td>Jaya</td>
	 *             <td style="text-align: center;">9</td>
	 *         </tr>
	 *         <tr>
	 *             <td style="text-align: center;">4</td>
	 *             <td>Mandala</td>
	 *             <td style="text-align: center;">7</td>
	 *         </tr>
	 *     </tbody>
	 * </table>
	 *
	 * @return noCaturwara.
	 */
	public int getCaturwara(){
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

	/**
	 * Informasi yang bisa didapatkan dari fungsi ini adalah <code>noSadwara</code></strong> yang merupakan representasi numerik dari Sad Wara. 1 -> Tungleh, 2-> Aryang, dan seterusnya.</p>
	 *
	 * <p>Perhitungan Sad Wara dilakukan dengan rumus ((noWuku * 7 + noSaptawara) mod 6).
	 * Jika sisa 1 adalah Tungleh, sisa 2 Aryang, sisa 3 Wurukung, sisa 4 Paniron, sisa 5 Was, dan apabila habis terbagi adalah Maulu.</p>
	 *
	 * <p><em>Contoh. </em> Wuku Menail (29), Sapta Wara Wraspati (4)
	 * Maka 29 x 7 + 4 : 6 = 34 sisa 3.
	 * Jadi pada wuku menail, hari (wara) Wrhaspati, Sad Waranya adalah Wurukung.</p>
	 *
	 * <p>Pada fungsi ini, dengan menggunakan angkaWuku perhitungan
	 * disingkat menjadi <code>angkaWuku mod 6</code></p>
	 *
	 * <table cellpadding="0" cellspacing="3" border="0"
	 *        summary="Sad Wara"
	 *        style="text-align: left; width: 66%;">
	 *     <tbody>
	 *         <tr>
	 *             <th style="vertical-align: top; background-color: rgb(204, 204, 255);
	 *           text-align: center;">No. Sad Wara</th>
	 *             <th style="vertical-align: top; background-color: rgb(204, 204, 255);
	 *           text-align: center;">Sad Wara</th>
	 *             <th style="vertical-align: top; background-color: rgb(204, 204, 255);
	 *           text-align: center;">Neptu/Urip</th>
	 *         </tr>
	 *         <tr>
	 *             <td style="text-align: center;">1</td>
	 *             <td>Tungleh</td>
	 *             <td style="text-align: center;">7</td>
	 *         </tr>
	 *         <tr>
	 *             <td style="text-align: center;">2</td>
	 *             <td>Aryang</td>
	 *             <td style="text-align: center;">6</td>
	 *         </tr>
	 *         <tr>
	 *             <td style="text-align: center;">3</td>
	 *             <td>Wurukung</td>
	 *             <td style="text-align: center;">5</td>
	 *         </tr>
	 *         <tr>
	 *             <td style="text-align: center;">4</td>
	 *             <td>Paniron</td>
	 *             <td style="text-align: center;">8</td>
	 *         </tr>
	 *         <tr>
	 *             <td style="text-align: center;">5</td>
	 *             <td>Was</td>
	 *             <td style="text-align: center;">9</td>
	 *         </tr>
	 *         <tr>
	 *             <td style="text-align: center;">6</td>
	 *             <td>Maulu</td>
	 *             <td style="text-align: center;">3</td>
	 *         </tr>
	 *     </tbody>
	 * </table>
	 *
	 * @return noSadwara.
	 */
	public int getSadwara(){
		int noSadwara = this.getWuku(ANGKA_WUKU) % 6;
		if (noSadwara == 0){noSadwara = 6;}
		return noSadwara;		
	}

	/**
	 * Informasi yang bisa didapatkan dari fungsi ini adalah <code>noAstawara</code></strong> yang merupakan representasi numerik dari Asta Wara. 1 -> Sri, 2-> Indra, dan seterusnya.</p>
	 *
	 * <p>Rumus perhitungan Asta Wara mirip seperti rumus perhitungan Catur Wara,
	 * didasari atas adanya pengecualian yang dikenal dengan istilah Kala tiga Dungulan.
	 * Penetapan kala Tiga dalam Asta Wara terdapat pada hari Redite Wuku Dunggulan,
	 * Soma Wuku Dunggulan, dan Anggara Wuku Dunggulan.
	 * Jadi Soma dan Anggara Wuku Dunggulan adalah hitungan pengecualian.</p>
	 *
	 * <p>Rumus pertama berlaku dari Redite Wuku Sinta hingga Redite Wuku Dunggulan <code>(noWuku * 7 + 2 + noSaptawara) mod 6</code>.</p>
	 *
	 * <p>Rumus kedua berlaku dari Budha Wuku Dunggulan hingga Saniscara Wuku Watugunung <code>(noWuku * 7 + noSaptawara) mod 8</code>.</p>
	 *
	 * <p>Apabila sisa 1 adalah Sri, sisa 2 Indra, sisa 3 Guru, sisa 4 yama, sisa 5 Rudra, sisa 6 Brahma, sisa 7 Kala, dan jika habis terbagi terhitung Uma.</code>
	 *
	 * <p>Pada fungsi ini, dengan menggunakan angkaWuku perhitungan
	 * rumus pertama menjadi <code>(angkaWuku + 6) mod 8</code>,
	 * rumus kedua (jika angka wuku <= 70) menjadi <code>angkaWuku mod 8</code>, dan untuk Kala Tiga pada angka wuku [71,72,73]</p>
	 *
	 * <table cellpadding="0" cellspacing="3" border="0"
	 *        summary="Asta Wara"
	 *        style="text-align: left; width: 66%;">
	 *     <tbody>
	 *         <tr>
	 *             <th style="vertical-align: top; background-color: rgb(204, 204, 255);
	 *           text-align: center;">No. Asta Wara</th>
	 *             <th style="vertical-align: top; background-color: rgb(204, 204, 255);
	 *           text-align: center;">Asta Wara</th>
	 *             <th style="vertical-align: top; background-color: rgb(204, 204, 255);
	 *           text-align: center;">Neptu/Urip</th>
	 *         </tr>
	 *         <tr>
	 *             <td style="text-align: center;">1</td>
	 *             <td>Sri</td>
	 *             <td style="text-align: center;">6</td>
	 *         </tr>
	 *         <tr>
	 *             <td style="text-align: center;">2</td>
	 *             <td>Indra</td>
	 *             <td style="text-align: center;">5</td>
	 *         </tr>
	 *         <tr>
	 *             <td style="text-align: center;">3</td>
	 *             <td>Guru</td>
	 *             <td style="text-align: center;">8</td>
	 *         </tr>
	 *         <tr>
	 *             <td style="text-align: center;">4</td>
	 *             <td>Yama</td>
	 *             <td style="text-align: center;">9</td>
	 *         </tr>
	 *         <tr>
	 *             <td style="text-align: center;">5</td>
	 *             <td>Ludra</td>
	 *             <td style="text-align: center;">3</td>
	 *         </tr>
	 *         <tr>
	 *             <td style="text-align: center;">6</td>
	 *             <td>Brahma</td>
	 *             <td style="text-align: center;">7</td>
	 *         </tr>
	 *         <tr>
	 *             <td style="text-align: center;">7</td>
	 *             <td>Kala</td>
	 *             <td style="text-align: center;">1</td>
	 *         </tr>
	 *         <tr>
	 *             <td style="text-align: center;">8</td>
	 *             <td>Uma</td>
	 *             <td style="text-align: center;">4</td>
	 *         </tr>
	 *     </tbody>
	 * </table>
	 *
	 * @return noAstawara.
	 */
	public int getAstawara(){
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

	/**
	 * Informasi yang bisa didapatkan dari fungsi ini adalah <code>noSangawara</code></strong> yang merupakan representasi numerik dari Sanga Wara. 1 -> Sri, 2-> Indra, dan seterusnya.</p>
	 *
	 * <p>Rumus mencari Sanga Wara adalah <code>(noWuku * 7 + noSaptawara) mod 9<code>,
	 *  jika sisa 1 Dangu, sisa 2 Jangur, sisa 3 Gigis, sisa 4 Nohan, sisa 5 Ogan, sisa 6 Erangan,
	 *  sisa 7 Urungan, sisa 8 Tulus, dan habis dibagi adalah Dadi. Namun terdapat catatan pada rumus ini yaitu
	 *  rumus ini tidak dapat digunakan pada hari Redite, Soma, Anggara pada Wuku Sinta
	 *  karena ada ketentuan Dangu Pat yakni hitungan Dangu yang terjadi 4 hari berturut-turut</p>
	 *
	 * <p>Pada fungsi ini, dengan menggunakan angkaWuku perhitungan
	 * disingkat menjadi <code>(angkaWuku + 6) % 9</code>,
	 * dan Dangu Pat (jika angka wuku <= 4).</p>
	 *
	 * <table cellpadding="0" cellspacing="3" border="0"
	 *        summary="Sanga Wara"
	 *        style="text-align: left; width: 66%;">
	 *     <tbody>
	 *         <tr>
	 *             <th style="vertical-align: top; background-color: rgb(204, 204, 255);
	 *           text-align: center;">No. Sanga Wara</th>
	 *             <th style="vertical-align: top; background-color: rgb(204, 204, 255);
	 *           text-align: center;">Sanga Wara</th>
	 *             <th style="vertical-align: top; background-color: rgb(204, 204, 255);
	 *           text-align: center;">Neptu/Urip</th>
	 *         </tr>
	 *         <tr>
	 *             <td style="text-align: center;">1</td>
	 *             <td>Dangu</td>
	 *             <td style="text-align: center;">9</td>
	 *         </tr>
	 *         <tr>
	 *             <td style="text-align: center;">2</td>
	 *             <td>Jagur</td>
	 *             <td style="text-align: center;">8</td>
	 *         </tr>
	 *         <tr>
	 *             <td style="text-align: center;">3</td>
	 *             <td>Gigis</td>
	 *             <td style="text-align: center;">6</td>
	 *         </tr>
	 *         <tr>
	 *             <td style="text-align: center;">4</td>
	 *             <td>Nohan</td>
	 *             <td style="text-align: center;">7</td>
	 *         </tr>
	 *         <tr>
	 *             <td style="text-align: center;">5</td>
	 *             <td>Ogan</td>
	 *             <td style="text-align: center;">4</td>
	 *         </tr>
	 *         <tr>
	 *             <td style="text-align: center;">6</td>
	 *             <td>Erangan</td>
	 *             <td style="text-align: center;">5</td>
	 *         </tr>
	 *         <tr>
	 *             <td style="text-align: center;">7</td>
	 *             <td>Urungan</td>
	 *             <td style="text-align: center;">7</td>
	 *         </tr>
	 *         <tr>
	 *             <td style="text-align: center;">8</td>
	 *             <td>Tulus</td>
	 *             <td style="text-align: center;">3</td>
	 *         </tr>
	 *         <tr>
	 *             <td style="text-align: center;">9</td>
	 *             <td>Dadi</td>
	 *             <td style="text-align: center;">4</td>
	 *         </tr>
	 *     </tbody>
	 * </table>
	 *
	 * @return noSangawara.
	 */
	public int getSangawara(){
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

	/**
	 * Informasi yang bisa didapatkan dari fungsi ini adalah <strong><code>noDasawara</code></strong> yang merupakan representasi numerik dari Dasa Wara. 1 -> Pandita, 2-> Pati, dan seterusnya.</p>
	 *
	 * <p>Dasa Wara dihitung dengan rumus <code>(uripSaptawara + uripPancawara + 1) mod 10</code>,
	 * apabila sisa 1 artinya pandita, sisa 2 pati, sisa 3 Suka, sisa 4 Duka, sisa 5 Sri,
	 * sisa 6 Manuh, sisa 7 Manusa, sisa 8 Raja, sisa 9 Dewa,
	 * dan apabila habis adalah Raksasa.
	 *
	 * <p>Pada fungsi ini, terdapat sedikit perbedaan dengan rumus <code>((uripSaptawara + uripPancawara) mod 10) + 1</code>
	 * untuk mendapatkan representasi <strong><code>noDasawara</code></strong> yang diinginkan</p>
	 *
	 * <table cellpadding="0" cellspacing="3" border="0"
	 *        summary="Dasa Wara"
	 *        style="text-align: left; width: 66%;">
	 *     <tbody>
	 *         <tr>
	 *             <th style="vertical-align: top; background-color: rgb(204, 204, 255);
	 *           text-align: center;">No. Dasa Wara</th>
	 *             <th style="vertical-align: top; background-color: rgb(204, 204, 255);
	 *           text-align: center;">Dasa Wara</th>
	 *             <th style="vertical-align: top; background-color: rgb(204, 204, 255);
	 *           text-align: center;">Neptu/Urip</th>
	 *         </tr>
	 *         <tr>
	 *             <td style="text-align: center;">1</td>
	 *             <td>Pandita</td>
	 *             <td style="text-align: center;">5</td>
	 *         </tr>
	 *         <tr>
	 *             <td style="text-align: center;">2</td>
	 *             <td>Pati</td>
	 *             <td style="text-align: center;">7</td>
	 *         </tr>
	 *         <tr>
	 *             <td style="text-align: center;">3</td>
	 *             <td>Suka</td>
	 *             <td style="text-align: center;">10</td>
	 *         </tr>
	 *         <tr>
	 *             <td style="text-align: center;">4</td>
	 *             <td>Duka</td>
	 *             <td style="text-align: center;">4</td>
	 *         </tr>
	 *         <tr>
	 *             <td style="text-align: center;">5</td>
	 *             <td>Sri</td>
	 *             <td style="text-align: center;">6</td>
	 *         </tr>
	 *         <tr>
	 *             <td style="text-align: center;">6</td>
	 *             <td>Manu</td>
	 *             <td style="text-align: center;">2</td>
	 *         </tr>
	 *         <tr>
	 *             <td style="text-align: center;">7</td>
	 *             <td>Manusa</td>
	 *             <td style="text-align: center;">3</td>
	 *         </tr>
	 *         <tr>
	 *             <td style="text-align: center;">8</td>
	 *             <td>Raja</td>
	 *             <td style="text-align: center;">8</td>
	 *         </tr>
	 *         <tr>
	 *             <td style="text-align: center;">9</td>
	 *             <td>Dewa</td>
	 *             <td style="text-align: center;">9</td>
	 *         </tr>
	 *         <tr>
	 *             <td style="text-align: center;">10</td>
	 *             <td>Raksasa</td>
	 *             <td style="text-align: center;">1</td>
	 *         </tr>
	 *     </tbody>
	 * </table>
	 *
	 * @return noDasawara.
	 */
	public int getDasawara(){
		return (((this.getPancawara(URIP_PANCAWARA) + this.getSaptawara(URIP_SAPTAWARA)) % 10)+1);
	}


	/**
	 * <p>Informasi yang bisa didapatkan dari fungsi ini adalah <code>noIngkel</code></strong>
	 * yang merupakan representasi numerik dari Ingkel. 1 -> Wong, 2-> Sato, dan seterusnya.</p>
	 *
	 * <p>Ingkel merupakan ketentuan dalam padewasan dalam sistem Pawukon
	 * yang merupakan pantangan-pantangan dalam melakukan suatu pekerjaan
	 * yang berkaitan dengan ketetapannya</p>
	 *
	 * <p>Ingkel berlaku selama seminggu, berjalan bersama Wuku dan berlaku sepanjang Wuku tersebut.
	 * Periodenya masing-masing 7 hari dalam siklus 6 minggu.
	 * Untuk menghitungnya dilakukan perhitungan <code>noWuku mod 6</code>.
	 * Hasilnya adalah jika 0=Buku (ruas), 1=Wong, 2=Sato, 3=Mina, 4=Manuk, dan 5=Taru.
	 * </p>
	 *
	 * <table cellpadding="0" cellspacing="3" border="0"
	 *        summary="Ingkel"
	 *        style="text-align: left; width: 66%;">
	 *     <tbody>
	 *         <tr>
	 *             <th style="vertical-align: top; background-color: rgb(204, 204, 255);
	 *           text-align: center;">No. Ingkel</th>
	 *             <th style="vertical-align: top; background-color: rgb(204, 204, 255);
	 *           text-align: center;">Ingkel</th>
	 *         </tr>
	 *         <tr>
	 *             <td style="text-align: center;">1</td>
	 *             <td>Wong</td>
	 *         </tr>
	 *         <tr>
	 *             <td style="text-align: center;">2</td>
	 *             <td>Sato</td>
	 *         </tr>
	 *         <tr>
	 *             <td style="text-align: center;">3</td>
	 *             <td>Mina</td>
	 *         </tr>
	 *         <tr>
	 *             <td style="text-align: center;">4</td>
	 *             <td>Manuk</td>
	 *         </tr>
	 *         <tr>
	 *             <td style="text-align: center;">5</td>
	 *             <td>Taru</td>
	 *         </tr>
	 *         <tr>
	 *             <td style="text-align: center;">6</td>
	 *             <td>Buku</td>
	 *         </tr>
	 *     </tbody>
	 * </table>
	 *
	 * <p>Pada fungsi ini, ingkel diketahui melalui beberapa kondisi berdasarkan angkaWuku.</p>
	 *
	 * @return noIngkel.
	 */
	public int getIngkel(){
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

	/**
	 * <p>Informasi yang bisa didapatkan dari fungsi ini adalah <code>noJejepan</code></strong>
	 * yang merupakan representasi numerik dari Jejepan. 1 -> Mina, 2-> Taru, dan seterusnya.</p>
	 *
	 * <p>Jejepan merupakan pantangan-pantangan seperti halnya Ingkel (disebut juga Ingkel Pandakan),
	 * namun menggunakan ketentuan Wewaran sehingga umurnya hanya satu hari.
	 * Untuk menghitungnya dilakukan perhitungan <code>(noWuku  7 + noSaptawara) mod 6</code>.
	 * Hasilnya adalah jika 0=Paksi (unggas), 1=Mina (ikan), 2=Taru (kayu), 3=Sato (binatang), 4=Patra (menjalar), 5=Wong (manusia).
	 * </p>
	 *
	 * <table cellpadding="0" cellspacing="3" border="0"
	 *        summary="Jejepan"
	 *        style="text-align: left; width: 66%;">
	 *     <tbody>
	 *         <tr>
	 *             <th style="vertical-align: top; background-color: rgb(204, 204, 255);
	 *           text-align: center;">No. Jejepan</th>
	 *             <th style="vertical-align: top; background-color: rgb(204, 204, 255);
	 *           text-align: center;">Jejepan</th>
	 *         </tr>
	 *         <tr>
	 *             <td style="text-align: center;">1</td>
	 *             <td>Mina</td>
	 *         </tr>
	 *         <tr>
	 *             <td style="text-align: center;">2</td>
	 *             <td>Taru</td>
	 *         </tr>
	 *         <tr>
	 *             <td style="text-align: center;">3</td>
	 *             <td>Sato</td>
	 *         </tr>
	 *         <tr>
	 *             <td style="text-align: center;">4</td>
	 *             <td>Patra</td>
	 *         </tr>
	 *         <tr>
	 *             <td style="text-align: center;">5</td>
	 *             <td>Wong</td>
	 *         </tr>
	 *         <tr>
	 *             <td style="text-align: center;">6</td>
	 *             <td>Paksi</td>
	 *         </tr>
	 *     </tbody>
	 * </table>
	 *
	 * <p>Pada fungsi ini, dengan menggunakan angkaWuku perhitungan
	 * disingkat menjadi <code>angkaWuku mod 6</code></p>
	 *
	 * @return noJejepan.
	 */
	public int getJejepan(){
        int noJejepan;
        int angkaWuku = this.getWuku(ANGKA_WUKU);
        noJejepan = angkaWuku % 6;
        if (noJejepan == 0){noJejepan = 6;}
        return noJejepan;
    }

	/**
	 * <p>Informasi yang bisa didapatkan dari fungsi ini adalah <code>noWatekAlit</code></strong>
	 * yang merupakan representasi numerik dari Pewatekan Alit / Watek Catur. 1 -> Watek Uler, 2-> Watek Gajah, dan seterusnya.</p>
	 *
	 * <p>Watek Catur atau disebut juga Pewatekan Alit dan Watek Panca atau Pewatekan Madya adalah petunjuk ala-ayuning dewasa
	 * untuk melaksanakan suatu pekerjaan/karya/yadnya, agar berhasil dengan baik
	 * berdasarkan perhitungan Sapta Wara dan Panca Wara.</p>
	 *
	 * <p>Untuk menghitungnya dilakukan perhitungan <code>(uripPancawara + uripSaptawara) mod 4</code>.
	 * Hasilnya adalah jika 0=Lintah, 1=Uler, 2=Gajah, 3=Lembu.
	 * </p>
	 *
	 * <table cellpadding="0" cellspacing="3" border="0"
	 *        summary="Watek Alit;"
	 *        style="text-align: left; width: 66%;">
	 *     <tbody>
	 *         <tr>
	 *             <th style="vertical-align: top; background-color: rgb(204, 204, 255);
	 *           text-align: center;">No. Watek Alit;</th>
	 *             <th style="vertical-align: top; background-color: rgb(204, 204, 255);
	 *           text-align: center;">Watek Alit;</th>
	 *         </tr>
	 *         <tr>
	 *             <td style="text-align: center;">1</td>
	 *             <td>Uler</td>
	 *         </tr>
	 *         <tr>
	 *             <td style="text-align: center;">2</td>
	 *             <td>Gajah</td>
	 *         </tr>
	 *         <tr>
	 *             <td style="text-align: center;">3</td>
	 *             <td>Lembu</td>
	 *         </tr>
	 *         <tr>
	 *             <td style="text-align: center;">4</td>
	 *             <td>Lintah</td>
	 *         </tr>
	 *     </tbody>
	 * </table>
	 *
	 * @return noWatekAlit.
	 */
	public int getWatekAlit(){
        int noWatekAlit;
        noWatekAlit = (this.getPancawara(URIP_PANCAWARA) + this.getSaptawara(URIP_SAPTAWARA)) % 4;
        if (noWatekAlit == 0){noWatekAlit = 4;}

        return noWatekAlit;
    }

	/**
	 * <p>Informasi yang bisa didapatkan dari fungsi ini adalah <code>noWatekMadya</code></strong>
	 * yang merupakan representasi numerik dari Pewatekan Madya / Watek Panca. 1 -> Watek Uler, 2-> Watek Gajah, dan seterusnya.</p>
	 *
	 * <p>Watek Catur atau disebut juga Pewatekan Alit dan Watek Panca atau Pewatekan Madya adalah petunjuk ala-ayuning dewasa
	 * untuk melaksanakan suatu pekerjaan/karya/yadnya, agar berhasil dengan baik
	 * berdasarkan perhitungan Sapta Wara dan Panca Wara.</p>
	 *
	 * <p>Untuk menghitungnya dilakukan perhitungan <code>(uripPancawara + uripSaptawara) mod 5</code>.
	 * Hasilnya adalah jika 0=Wong, 1=Gajah, 2=Watu, 3=Buta, 4=Suku.
	 * </p>
	 *
	 * <table cellpadding="0" cellspacing="3" border="0"
	 *        summary="Watek Madya;"
	 *        style="text-align: left; width: 66%;">
	 *     <tbody>
	 *         <tr>
	 *             <th style="vertical-align: top; background-color: rgb(204, 204, 255);
	 *           text-align: center;">No. Watek Madya;</th>
	 *             <th style="vertical-align: top; background-color: rgb(204, 204, 255);
	 *           text-align: center;">Watek Madya;</th>
	 *         </tr>
	 *         <tr>
	 *             <td style="text-align: center;">1</td>
	 *             <td>Gajah</td>
	 *         </tr>
	 *         <tr>
	 *             <td style="text-align: center;">2</td>
	 *             <td>Watu</td>
	 *         </tr>
	 *         <tr>
	 *             <td style="text-align: center;">3</td>
	 *             <td>Buta</td>
	 *         </tr>
	 *         <tr>
	 *             <td style="text-align: center;">4</td>
	 *             <td>Suku</td>
	 *         </tr>
	 *         <tr>
	 *             <td style="text-align: center;">5</td>
	 *             <td>Wong</td>
	 *         </tr>
	 *     </tbody>
	 * </table>
	 *
	 * @return noWatekMadya.
	 */
	public int getWatekMadya(){
        int noWatekMadya;
        noWatekMadya = (this.getPancawara(URIP_PANCAWARA) + this.getSaptawara(URIP_SAPTAWARA)) % 5;
        if (noWatekMadya == 0){noWatekMadya = 5;}

        return noWatekMadya;
    }

	/*** Fungsi menghitung eka jala rsi  @return the int*/
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

	/*** Fungsi menghitung palalintangan  @return the int*/
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

	/*** Fungsi menghitung panca sudha  @return the int*/
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

	/*** Fungsi menghitung pararasan  @return the int*/
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

	/*** Fungsi menghitung rakam  @return the int*/
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


	/*** Fungsi menghitung zodiak  @return the int*/
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

	/**
	 * Gets saka calendar.
	 *
	 * @param field the field
	 * @return the saka calendar
	 */
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

	/**
	 * Gets saka calendar status.
	 *
	 * @param field the field
	 * @return the saka calendar status
	 */
	public boolean getSakaCalendarStatus(int field) {
		if(this.tahunSaka == 0){
			hitungSaka();
		}
		switch (field){
			case IS_NGUNARATRI: return this.isNgunaratri;
			case IS_PANGELONG: return this.isPangelong;
			case IS_NAMPIH: return isNampih;
			default: return this.isNgunaratri;
		}
	}

}