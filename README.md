[![Build Status](https://travis-ci.org/edysantosa/sakacalendar.svg?branch=master)](https://travis-ci.org/edysantosa/sakacalendar)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/cc6611397b434857ae326044d0a12d20)](https://www.codacy.com/app/edysantosa/sakacalendar?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=edysantosa/sakacalendar&amp;utm_campaign=Badge_Grade)

# SakaCalendar 2.0
SakaCalendar adalah library kalendar Bali (Wariga) open source yang dapat memberikan informasi mengenai berbagai macam perhitungan-perhitungan yang ada dalam sistem penanggalan kalender Bali, mulai dari sasih, penanggal/pangelong, pawukon, wewaran dan lain-lainnya.

Library ini dapat melakukan perhitungan penanggalan kalendar Bali antara lain :
- Pawukon
- Wewaran
- Tanggalan-Pangelong
- Sasih
- Ingkel
- Ingkel Jejepan
- Watek Catur/Watek Panca
- Pararasan
- Panca Sudha
- Eka Jala Rsi
- Palalintangan

## Penggunaan Dasar

Untuk meng-instantiate `SakaCalendar` dengan tanggal yang telah ditentukan, gunakan parameter (tahu, bulan, tanggal). Parameter bulan adalah berbasis 0, jadi Januari = 0, Februari = 2... dan seterusnya. Jika di-instantiate tanpa parameter maka tanggal yang didapat adalah tanggal pada hari ini.

```java
SakaCalendar tanggal = new SakaCalendar(2014,5,1);
```

Berikut adalah demo sederhana penggunaan library `SakaCalendar` :
```java
SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

System.out.println("Tanggal " + sdf.format(tanggal.getTime()));
System.out.println("No Wuku " + tanggal.getWuku(SakaCalendar.NO_WUKU));
System.out.println("No Pancawara " + tanggal.getPancawara(SakaCalendar.NO_PANCAWARA));
System.out.println("Urip Pancawara " + tanggal.getSaptawara(SakaCalendar.URIP_PANCAWARA));
System.out.println("Tahun Saka " + tanggal.getSakaCalendar(SakaCalendar.TAHUN_SAKA));
System.out.println("No Sasih " + tanggal.getSakaCalendar(SakaCalendar.NO_SASIH));
System.out.println("Penanggal " + tanggal.getSakaCalendar(SakaCalendar.PENANGGAL));
System.out.println((tanggal.getSakaCalendarStatus(SakaCalendar.IS_PANGELONG)) ? "Pangelong" : "Penanggal");
System.out.println((tanggal.getSakaCalendarStatus(SakaCalendar.IS_NGUNARATRI)) ? "Ngunaratri" : "Bukan ngunaratri");
System.out.println((tanggal.getSakaCalendarStatus(SakaCalendar.IS_NAMPIH)) ? "Nampih sasih" : "Bukan nampih sasih");
```

Outputnya :
```java
Tanggal 01-06-2014
No Wuku 13
No Pancawara 1
No Saptawara 0
tahun Saka 1936
No Sasih 12
Penanggal 4
Penanggal
Bukan ngunaratri
Bukan nampih sasih
```

## Detail Fungsi
### Wuku

```java
getWuku(int field)
```

Parameter yang diberikan berupa constant dengan return valuenya antara lain
* `NO_WUKU` = `noWuku` Nomor Wuku merupakan representasi numerik dari Wuku. e.g., 1 -> Sinta, 2-> Landep dan seterusnya.
* `ANGKA_WUKU` = `angkaWuku` Adalah jumlah hari yang telah berlalu dalam wuku saat ini(1 sampai dengan 210).
* `URIP_WUKU` = `uripWuku` Nilai urip/neptu dari Wuku.

Berikut adalah daftar tabel Wuku beserta nomor Wuku dan uripnya.
|No Wuku|Nama Raja|Nama Wuku|Neptu/Urip|
|--- |--- |--- |--- |
|1|Dewi Sintakasih|Sinta|7|
|2|Dewi Sanjiwartia|Landep|1|
|3|Giriswara|Ukir|4|
|4|Kuladewa|Kulantir|6|
|5|Talu|Tolu|5|
|6|Mrabuana|Gumbreg|8|
|7|Waksaya|Wariga|9|
|8|Wariwiyasa|Warigadean|3|
|9|Mrikjulung|Julungwangi|7|
|10|Sungsangtaya|Sungsang|1|
|11|Dungulan|Dungulan|4|
|12|Puspita|Kuningan|6|
|13|Langkir|Langkir|5|
|14|Medangsu|Medangsya|8|
|15|Pujitpwa|Pujut|9|
|16|Paha|Pahang|3|
|17|Kruru|Kerulut|7|
|18|Merangsinga|Merakih|1|
|19|Tambur|Tambir|4|
|20|Medangkusa|Medangkungan|6|
|21|Matal|Matal|5|
|22|Uye|Uye|8|
|23|Ijala|Menahil|9|
|24|Yuddha|Perangbakat|3|
|25|Baliraja|Bala|7|
|26|Wiugah|Ugu|1|
|27|Ringgita|Wayang|4|
|28|Kulawudra|Kelawu|6|
|29|Sasawi|Dukut|5|
|30|Watugunung|Watugunung|8|

### Wewaran

```java
noEkawara    = getEkawara()
noDwiwara    = getDwiwara()
noTriwara    = getTriwara()
noCaturwara  = getCaturwara()
noPancawara  = getPancawara(SakaCalendar.NO_PANCAWARA)
uripPancawara  = getPancawara(SakaCalendar.URIP_PANCAWARA)
noSadwara    = getSadwara()
noSaptawara  = getSaptawara(SakaCalendar.NO_SAPTAWARA)
uripSaptawara  = getSaptawara(SakaCalendar.URIP_SAPTAWARA)
noAstawara   = getAstawara()
noSangawara  = getSangawara()
noDasarawara = getDasawara()
```

Semua fungsi wewaran diatas akan mereturn sebuah nilai `int` yang merepresentasikan wara-nya sesudai dengan urutan (dapat dilihat pada tabel dibawah).
Fungsi `getPancawara` dan `getSaptawara` memerlukan parameter tambahan berupa constant. COntohh :  `getPancawara` dengan constant `NO_PANCAWARA` akan memberikan nomor waranya sedangkan constant `URIP_PANCAWARA` akan memberikan urip pancawaranya.

#### Ekawara
|No Eka Wara|Eka Wara|Urip/Neptu|
|--- |--- |--- |
|1|Luang|1|


#### Dwiwara
|No Dwi Wara|Dwi Wara|Urip/Neptu|
|--- |--- |--- |
|1|Menga|5|
|2|Pepet|7|


#### Triwara
|No Tri Wara|Tri Wara|Urip/Neptu|
|--- |--- |--- |
|1|Dora/Pasah|9|
|2|Wahya/Beteng|4|
|3|Byantara/Kajeng|7|


#### Caturwara
|No Catur Wara|Catur Wara|Neptu/Urip|
|--- |--- |--- |
|1|Sri|4|
|2|Laba|5|
|3|Jaya|9|
|4|Mandala|7|

#### Pancawara
|No Panca Wara|Panca Wara|Urip/Neptu|
|--- |--- |--- |
|1|Umanis|5|
|2|Pahing|9|
|3|Pon|7|
|4|Wage|4|
|5|Kliwon|8|


#### Sadwara
|No. Sad Wara|Sad Wara|Neptu/Urip|
|--- |--- |--- |
|1|Tungleh|7|
|2|Aryang|6|
|3|Wurukung|5|
|4|Paniron|8|
|5|Was|9|
|6|Maulu|3|


#### Saptawara
|No Sapta Wara|Sapta Wara|Urip/Neptu|
|--- |--- |--- |
|0|Redite|5|
|1|Coma|4|
|2|Anggara|3|
|3|Buddha|7|
|4|Wrhaspati|8|
|5|Sukra|6|
|6|Saniscara|9|


#### Astawara
|No. Asta Wara|Asta Wara|Neptu/Urip|
|--- |--- |--- |
|1|Sri|6|
|2|Indra|5|
|3|Guru|8|
|4|Yama|9|
|5|Ludra|3|
|6|Brahma|7|
|7|Kala|1|
|8|Uma|4|

#### Sangawara
|No. Sanga Wara|Sanga Wara|Neptu/Urip|
|--- |--- |--- |
|1|Dangu|9|
|2|Jagur|8|
|3|Gigis|6|
|4|Nohan|7|
|5|Ogan|4|
|6|Erangan|5|
|7|Urungan|7|
|8|Tulus|3|
|9|Dadi|4|


#### Dasawara
|No. Dasa Wara|Dasa Wara|Neptu/Urip|
|--- |--- |--- |
|1|Pandita|5|
|2|Pati|7|
|3|Suka|10|
|4|Duka|4|
|5|Sri|6|
|6|Manu|2|
|7|Manusa|3|
|8|Raja|8|
|9|Dewa|9|
|10|Raksasa|1|

### Sasih

```java
int tahunSaka    = getSakaCalendar(SakaCalendar.TAHUN_SAKA)
int penanggal    = getSakaCalendar(SakaCalendar.PENANGGAL)
int noSasih      = getSakaCalendar(SakaCalendar.NO_SASIH)
int noNgunaratri = getSakaCalendar(SakaCalendar.NO_NGUNARATRI)

boolean isNgunartri = getSakaCalendarStatus(SakaCalendar.IS_NGUNARATRI)
boolean isNgunartri = getSakaCalendarStatus(SakaCalendar.IS_PANGELONG)
boolean isNampih= getSakaCalendarStatus(SakaCalendar.IS_NAMPIH)
```

Pada fungsi `getSakaCalendar()`  parameter constant yang diberikan dengan return valuenya antara lain :
* `TAHUN_SAKA` = `tahunSaka` Tahun Saka.
* `NO_SASIH` = `noSasih` Merupakan representasi numerik dari Sasih. e.g., 1 -> Kasa, 2-> Karo dan seterusnya.
* `PENANGGAL` = `penanggal` Penanggal/pangelong.
* `NO_NGUNARATRI` = `noNgunaratri` Jumlah hari yang berlalu sejak ngunaratri terakhir.

Pada fungsi `getSakaCalendarStatus()` :
* `IS_NGUNARATRI` = `isNgunaratri` Jika `true` maka penanggal/pangelong pada saat itu adalah ngunaratri. e. g. penanggal/pangelong pada saat itu adalah 6, maka ditampilkan sebagai penanggal 6 dan 7. dan hari selanjutnya menjadi penanggal/pangelong 8.
* `IS_PANGELONG` = `isPangelong` Jika `true` maka saat itu adalah pangelong, jika `false` adalah penanggal.
* `IS_NAMPIH` = `isNampih` Jika `true` maka sasih pada saat itu adalah nampih sasih. e. g. jika `true` dan nilai noSasih pada saat itu adalah Jyestha, maka sasih pada saat itu adalah Nampih Jyestha / Mala Jyestha.

|No Sasih|Nama Sasih|
|--- |--- |
|1|Kasa/Srawana|
|2|Karo/Bhadrapada|
|3|Katiga/Aswina|
|4|Kapat/Kartika|
|5|Kalima/Margasira|
|6|Kanem/Pausya|
|7|Kapitu/Magha|
|8|Kawolu/Phalguna|
|9|Kasanga/Caitra|
|10|Kadasa/Waisakha|
|11|Destha/Jyestha|
|12|Sadha/Asadha|

## Fungsi-fungsi lain

Fungsi fungsi lain ini dipanggil dengan paramater SakaCalendar dan akan mereturn sebuah value integer. Fungsi-fungsi ini antara lain :


### Menghitung Ingkel

```java
int noIngkel = getIngkel()
```
|No. Ingkel|Ingkel|
|--- |--- |
|1|Wong|
|2|Sato|
|3|Mina|
|4|Manuk|
|5|Taru|
|6|Buku|

### Menghitung Jejepan

```java
int noJejepan = getJejepan()
```
|No. Jejepan|Jejepan|
|--- |--- |
|1|Mina|
|2|Taru|
|3|Sato|
|4|Patra|
|5|Wong|
|6|Paksi|


### Menghitung Pewatekan Alit (Catur)

```java
int noWatekAlit = getWatekAlit()
```
|No. Watek Alit|Watek Alit|
|--- |--- |
|1|Uler|
|2|Gajah|
|3|Lembu|
|4|Lintah|


### Menghitung Pewatekan Madya (Panca)

```java
int noWatekMadya = getWatekMadya()
```
|No. Watek Madya|Watek Madya|
|--- |--- |
|1|Gajah|
|2|Watu|
|3|Buta|
|4|Suku|
|5|Wong|


### Menghitung Eka Jala Rsi

```java
int noEkaJalaRsi = getEkaJalaRsi()
```
|No. Eka Jala Rsi|Eka Jala Rsi|
|--- |--- |
|1|Bagna mapasah|
|2|Bahu putra|
|3|Buat astawa|
|4|Buat lara|
|5|Buat merang|
|6|Buat sebet|
|7|Buat kingking|
|8|Buat suka|
|9|Dahat kingking|
|10|Kamaranan|
|11|Kamretaan|
|12|Kasobagian|
|13|Kinasihan amreta|
|14|Kinasihan jana|
|15|Langgeng kayohanaan|
|16|Lewih bagia|
|17|Manggih bagia|
|18|Manggih suka|
|19|Patining amreta|
|20|Rahayu|
|21|Sidha kasobagian|
|22|Subagia|
|23|Suka kapanggih|
|24|Suka pinanggih|
|25|Suka rahayu|
|26|Tininggaling suka|
|27|Wredhi putra|
|28|Wredhi sarwa mule|

### Menghitung Palalintangan

```java
int noLintang = getLintang()
```
|No. Lintang|Lintang|
|--- |--- |
|1|Gajah||
|2|Kiriman||
|3|Jong Sarat||
|4|Atiwa-tiwa||
|5|Sangka Tikel||
|6|Bubu Bolong||
|7|Sugenge||
|8|Uluku/Tenggala||
|9|Pedati||
|10|Kuda||
|11|Gajah Mina||
|12|Bade||
|13|Magelut||
|14|Pagelangan||
|15|Kala Sungsang||
|16|Kukus||
|17|Asu||
|18|Kartika||
|19|Naga||
|20|Banak Angerem||
|21|Hru/Panah||
|22|Patrem||
|23|Lembu||
|24|Depat/Sidamalung||
|25|Tangis||
|26|Salah Ukur||
|27|Perahu Pegat||
|28|Puwuh Atarung||
|29|Lawean/Goang||
|30|Kelapa||
|31|Yuyu||
|32|Lumbung||
|33|Kumbha||
|34|Udang||
|35|Begoong||



### Menghitung Panca Sudha

```java
int noPancasudha = getPancasudha()
```
|No. Panca Sudha|Panca Sudha|
|--- |--- |
|1|Wisesa segara||
|2|Tunggak semi||
|3|Satria wibhawa||
|4|Sumur sinaba||
|5|Bumi kapetak||
|6|Satria wirang||
|7|Lebu katiup angin||

### Menghitung Pararasan

```java
int noPararasan = getPararasan()
```
|No. Pararasan;|Pararasan;|
|--- |--- |
|1|Laku bumi|
|2|Laku api|
|3|Laku angin|
|4|Laku pandita sakti|
|5|Aras tuding|
|6|Aras kembang|
|7|Laku bintang|
|8|Laku bulan|
|9|Laku surya|
|10|Laku air/toya|
|11|Laku pretiwi|
|12|Laku agni agung|


### Menghitung Rakam

```java
int noRakam = getRakam()
```
|No. Rakam;|Rakam;|
|--- |--- |
|1|Kala tinatang|
|2|Demang kandhuruwan|
|3|Sanggar waringin|
|4|Mantri sinaroja|
|5|Macam katawan|
|6|Nuju pati|


## Menghitung Zodiak

```java
int noZodiak = getZodiak()
```
|No. Zodiak|Nama Zodiak|
|--- |--- |
|1|Aries|
|2|Taurus|
|3|Gemini|
|4|Cancer|
|5|Leo|
|6|Virgo|
|7|Libra|
|8|Scorpio|
|9|Sagitarius|
|10|Capricon|
|11|Aquarius|
|12|Pisces|


## Pustaka
Class pada package ini dimbil dari skripsi S1 saya pada STIKOM Bali http://digilib.stikom-bali.ac.id/Skripsi_TA.aspx?appid=003259

Buku-buku yang dijadikan acuan adalah "Dasar Wariga", dan "Tenung Wariga" karangan I.B. Putra Manik Aryana, serta "Pokok-pokok Wariga" karangan I.B. Supartha Ardana.

Selain itu juga banyak diambil informasi dari http://www.babadbali.com/, http://www.kalenderbali.org/, dan http://www.balabali.com/id/balabali-kalender-id



[![Analytics](https://ga-beacon.appspot.com/UA-74411957-2/readme-page)](https://github.com/igrigorik/ga-beacon)
