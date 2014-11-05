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
import java.util.GregorianCalendar;

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
		
		public SakaCalendar(){
			super();
		} 
		
		public SakaCalendar(int year, int month, int dayOfMonth){
			super(year, month , dayOfMonth);
		} 
}