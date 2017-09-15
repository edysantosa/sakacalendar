package com.edysantosa.sakacalendar;

import com.edysantosa.sakacalendarold.SakaCalendarCalculator;
import org.junit.Test;


//import java.util.concurrent.ThreadLocalRandom;

import static org.junit.Assert.*;

public class SakaCalendarTest {

    SakaCalendarCalculator calc = new SakaCalendarCalculator();

    @Test
    public void wukuShouldEqualsOldVersion() throws Exception {
        for (int year = 1960; year <= 2020; year++) {
            for (int month = 0; month <= 11; month++) {
                for (int day = 1; day <= 29; day++) {


                    SakaCalendar sakaCalendar = new SakaCalendar(year, month, day);
                    com.edysantosa.sakacalendarold.SakaCalendar sakaCalendarOld = new com.edysantosa.sakacalendarold.SakaCalendar(year, month, day);
                    sakaCalendarOld = calc.hitungWuku(sakaCalendarOld);

                    String dateString = year + "-" + month + "-" + day;

                    assertEquals("Testing for noWuku at " + dateString, sakaCalendarOld.noWuku, sakaCalendar.getWuku(SakaCalendar.NO_WUKU));
                    assertEquals("Testing for angkaWuku at " + dateString, sakaCalendarOld.angkaWuku, sakaCalendar.getWuku(SakaCalendar.ANGKA_WUKU));
                    assertEquals("Testing for uripWuku at " + dateString, sakaCalendarOld.uripWuku, sakaCalendar.getWuku(SakaCalendar.URIP_WUKU));

                }
            }
        }
    }

    @Test
    public void wewaranShouldEqualsOldVersion() throws Exception {
        for (int year = 1960; year <= 2020; year++) {
            for (int month = 0; month <= 11; month++) {
                for (int day = 1; day <= 29; day++) {


                    SakaCalendar sakaCalendar = new SakaCalendar(year, month, day);
                    com.edysantosa.sakacalendarold.SakaCalendar sakaCalendarOld = new com.edysantosa.sakacalendarold.SakaCalendar(year, month, day);
                    sakaCalendarOld = calc.hitungWuku(sakaCalendarOld);
                    sakaCalendarOld = calc.hitungWewaran(sakaCalendarOld);

                    String dateString = year + "-" + month + "-" + day;

                    assertEquals("Testing for noSaptawara on " + dateString, sakaCalendarOld.noSaptawara, sakaCalendar.getSaptawara(SakaCalendar.NO_SAPTAWARA));
                    assertEquals("Testing for uripSaptawara on " + dateString, sakaCalendarOld.uripSaptawara, sakaCalendar.getSaptawara(SakaCalendar.URIP_SAPTAWARA));

                    assertEquals("Testing for noPancawara at " + dateString, sakaCalendarOld.noPancawara, sakaCalendar.getPancawara(SakaCalendar.NO_PANCAWARA));
                    assertEquals("Testing for uripPancawara at " + dateString, sakaCalendarOld.uripPancawara, sakaCalendar.getPancawara(SakaCalendar.URIP_PANCAWARA));

                    assertEquals("Testing for noEkawara at " + dateString, sakaCalendarOld.noEkawara, sakaCalendar.getEkawara());
                    assertEquals("Testing for noDwiwara at " + dateString, sakaCalendarOld.noDwiwara, sakaCalendar.getDwiwara());
                    assertEquals("Testing for noTriwara at " + dateString, sakaCalendarOld.noTriwara, sakaCalendar.getTriwara());
                    assertEquals("Testing for noCaturwara at " + dateString, sakaCalendarOld.noCaturwara, sakaCalendar.getCaturwara());
                    assertEquals("Testing for noSadwara at " + dateString, sakaCalendarOld.noSadwara, sakaCalendar.getSadwara());
                    assertEquals("Testing for noAstawara at " + dateString, sakaCalendarOld.noAstawara, sakaCalendar.getAstawara());
                    assertEquals("Testing for noSangawara at " + dateString, sakaCalendarOld.noSangawara, sakaCalendar.getSangawara());
                    assertEquals("Testing for noDasawara at " + dateString, sakaCalendarOld.noDasawara, sakaCalendar.getDasawara());


                }
            }
        }
    }

    @Test
    public void sakaShouldEqualsOldVersion() throws Exception {
        for (int year = 1960; year <= 2020; year++) {
            for (int month = 0; month <= 11; month++) {
                for (int day = 1; day <= 29; day++) {


                    SakaCalendar sakaCalendar = new SakaCalendar(year, month, day);
                    com.edysantosa.sakacalendarold.SakaCalendar sakaCalendarOld = new com.edysantosa.sakacalendarold.SakaCalendar(year, month, day);
                    sakaCalendarOld = calc.hitungWuku(sakaCalendarOld);
                    sakaCalendarOld = calc.hitungWewaran(sakaCalendarOld);
                    sakaCalendarOld = calc.hitungSaka(sakaCalendarOld);

                    String dateString = year + "-" + month + "-" + day;

                    assertEquals("Testing for tahunSaka on " + dateString, sakaCalendarOld.tahunSaka, sakaCalendar.getSakaCalendar(SakaCalendar.TAHUN_SAKA));
                    assertEquals("Testing for penanggal on " + dateString, sakaCalendarOld.penanggal, sakaCalendar.getSakaCalendar(SakaCalendar.PENANGGAL));
                    assertEquals("Testing for noSasih on " + dateString, sakaCalendarOld.noSasih, sakaCalendar.getSakaCalendar(SakaCalendar.NO_SASIH));
                    assertEquals("Testing for isNgunaratri on " + dateString, sakaCalendarOld.isNgunaratri, sakaCalendar.getSakaCalendarStatus(SakaCalendar.IS_NGUNARATRI));
                    assertEquals("Testing for isNampih on " + dateString, sakaCalendarOld.isNampih, sakaCalendar.getSakaCalendarStatus(SakaCalendar.IS_NAMPIH));
                    assertEquals("Testing for isPangelong on " + dateString, sakaCalendarOld.isPangelong, sakaCalendar.getSakaCalendarStatus(SakaCalendar.IS_PANGELONG));


                }
            }
        }
    }

    @Test
    public void othersShouldEqualsOldVersion() throws Exception {
        for (int year = 1960; year <= 2020; year++) {
            for (int month = 0; month <= 11; month++) {
                for (int day = 1; day <= 29; day++) {

                    SakaCalendar sakaCalendar = new SakaCalendar(year, month, day);
                    com.edysantosa.sakacalendarold.SakaCalendar sakaCalendarOld = new com.edysantosa.sakacalendarold.SakaCalendar(year, month, day);
                    sakaCalendarOld = calc.hitungWuku(sakaCalendarOld);
                    sakaCalendarOld = calc.hitungWewaran(sakaCalendarOld);

                    String dateString = year + "-" + month + "-" + day;

                    assertEquals("Testing for noIngkel on " + dateString, calc.hitungIngkel(sakaCalendarOld), sakaCalendar.getIngkel());
                    assertEquals("Testing for noJejepan on " + dateString, calc.hitungJejepan(sakaCalendarOld), sakaCalendar.getJejepan());
                    assertEquals("Testing for noWatekAlit on " + dateString, calc.hitungWatekAlit(sakaCalendarOld), sakaCalendar.getWatekAlit());
                    assertEquals("Testing for noWatekMadya on " + dateString, calc.hitungWatekMadya(sakaCalendarOld), sakaCalendar.getWatekMadya());
                    assertEquals("Testing for noEkaJalaRsi on " + dateString, calc.hitungEkaJalaRsi(sakaCalendarOld), sakaCalendar.getEkaJalaRsi());
                    assertEquals("Testing for noLintang on " + dateString, calc.hitungLintang(sakaCalendarOld), sakaCalendar.getLintang());
                    assertEquals("Testing for noPancasudha on " + dateString, calc.hitungPancasudha(sakaCalendarOld), sakaCalendar.getPancasudha());
                    assertEquals("Testing for noPararasan on " + dateString, calc.hitungPararasan(sakaCalendarOld), sakaCalendar.getPararasan());
                    assertEquals("Testing for noRakam on " + dateString, calc.hitungRakam(sakaCalendarOld), sakaCalendar.getRakam());
                    assertEquals("Testing for noZodiak on " + dateString, calc.hitungZodiak(sakaCalendarOld), sakaCalendar.getZodiak());



                }
            }
        }
    }

}