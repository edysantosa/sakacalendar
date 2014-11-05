#sakacalendar 0.3

Copyright (c) 2012 - 2014 Edy Santosa Putra
sakacalendar is licensed under the GNU General Public License, version 2 (http://www.gnu.org/licenses/old-licenses/gpl-2.0.html)

sakacalendar adalah java package untuk melakukan perhitungan sistem penanggalan Saka.
package ini dapat melakukan perhitungan penanggalan Saka antara lain :
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


Package ini terdiri dari class SakaCalendar yang berfungsi sebagai container atribut-atribut penanggalan saka, dan SakaCalendarCalculator yang berfungsi melakukan perhitungan atribut-atribut tersebut. 

## Penggunaan Dasar:

Setalah mengimport package ini inisialisasi kedua class. Inisialisasi class SakaCalendar sama dengan pada class GregorianCalendar Java. 

```java
SakaCalendar tanggal = new SakaCalendar(2014,5,1);
SakaCalendarCalculator calc = new SakaCalendarCalculator();-
```

Gunakan class SakaCalendarCalculator dengan cara menggunakan SakaCalendar yang telah diinisialisasi dengan value yang valid sebagai parameter untuk menghitung variabel-variabel pada SakaCalendar yakni variabel-variabel untuk Wuku, Wewaran, dan Tahun Saka. 

```java
tanggal = calc.hitungWuku(tanggal);
tanggal = calc.hitungWewaran(tanggal);
tanggal = calc.hitungSaka(tanggal);
```

Hasil perhitungan tersebut kemudian dapat ditampilkan

```java
SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

System.out.println("Tanggal " + sdf.format(tanggal.getTime()));
System.out.println("No Wuku " + tanggal.noWuku);
System.out.println("No Pancawara " + tanggal.noPancawara);
System.out.println("No Saptawara " + tanggal.noSaptawara);
System.out.println("Tahun Saka " + tanggal.tahunSaka);
System.out.println("No Sasih " + tanggal.noSasih);
System.out.println("Penanggal " + tanggal.penanggal);
System.out.println((tanggal.isPangelong) ? "Pangelong" : "Penanggal");
System.out.println((tanggal.isNgunaratri) ? "Ngunaratri" : "Bukan ngunaratri");
System.out.println((tanggal.isNampih) ? "Nampih sasih" : "Bukan nampih sasih");
```

## Detail Fungsi
### Menghitung Wuku

```java
hitungWuku(SakaCalendar tgl)
```

Fungsi ini mengambil parameter SakaCalendar dan me-return SakaCalendar yang telah memiliki variabel noWuku dan uripWuku. Wuku Sinta - Watugunung diuurutkan dengan noWuku dari 1-30, beserta uripnya pada uripWuku 

### Menghitung Wewaran

```java
hitungWewaran(SakaCalendar tgl)
```

Fungsi ini mengambil parameter SakaCalendar dan me-return SakaCalendar yang telah memiliki variabel-variabel wewaran beserta urip Saptawara dan urip Pancawara, antara lain :

- uripPancawara;
- uripSaptawara;
- noEkawara;
- noDwiwara;
- noTriwara;
- noCaturwara;
- noPancawara;
- noSadwara;
- noSaptawara;
- noAstawara;
- noSangawara;
- noDasawara;


### Menghitung Wuku

```java
hitungWuku(SakaCalendar tgl)
```

Fungsi ini mengambil parameter SakaCalendar dan me-return SakaCalendar yang telah memiliki variabel noWuku dan uripWuku. Wuku Sinta - Watugunung diuurutkan dengan noWuku dari 1-30, beserta uripnya pada uripWuku 


### Menghitung Penanggalan Saka

```java
hitungSaka(SakaCalendar tgl)
```

Fungsi ini mengambil parameter SakaCalendar dan me-return SakaCalendar yang telah memiliki variabel antara lain :

- tahunSaka
- noSasih
- penanggal
- isPangelong
- isNgunaratri
- isNampih


tahunSaka memberikan angka Tahun Saka. http://www.kalenderbali.org/ memberikan angka yang merepresentasikan sasih dengan urutan :

- 1 Kasa
- 2 Karo
- 3 Katiga
- 4 Kapat
- 5 Kalima
- 6 Kanem
- 7 Kapitu
- 8 Kawolu
- 9 Kasanga
- 10 Kadasa
- 11 Destha
- 12 Sadha

penanggal memberikan angka dari penanggalan tanpa memperdulikan apakah angka tersebut Pangelong atau Penanggal. Untuk menentukannya digunakan variabel boolean isPangelong dimana jika true variabel penanggal tersebut adalah Pangelong dan sebaliknya.

isNgunaratri memberikan value boolean yang menentukan apakah penanggal tersebut adalah penganggal yang Ngunaratri(Pengalantaka) dimana jika variabel isNgunaratri memberikan nilai true maka ex. jika penanggal pada hari tersebut adalah 9 maka hasil yang akan didapat pada hari selanjutnya adalah 11. Variabel ini berguna untuk menampilkan penanggal pada user interface (ex. Pangelong 10/11).

isNampih memberikan value boolean yang menentukan apakah sasih pada saat tersebut adalah sasih nampih atau tidak. Jika true makah sasih pada saat itu adalah nampih sasih pada sasih sesuai dengan noSasih. Ex. Jika noSasih adalah 11 dan isNampih adalah true maka sasih tersebut adalah nampih Destha(Jyestha).

## Fungsi-fungsi lain

Fungsi fungsi lain ini dipanggil dengan paramater SakaCalendar dan akan mereturn sebuah value integer. Fungsi-fungsi ini antara lain :


## Menghitung Ingkel

```java
int pararasan = calc.hitungIngkel(tanggal)
```
Dimana nilai return-nya :
- 1 Wong
- 2 Sato
- 3 Mina
- 4 Manuk
- 5 Taru
- 6 Buku


## Menghitung Jejepan

```java
int jejepan = calc.hitungJejepan(tanggal)
```
Dimana nilai return-nya :
- 1 Mina
- 2 taru
- 3 Sato
- 4 Patra
- 5 Wong
- 0 -> 6 Paksi


## Menghitung Pewatekan Alit (Catur)

```java
int watekalit = calc.hitungWatekAlit(tanggal)
```
Dimana nilai return-nya :
- 1 Uler
- 2 Gajah
- 3 Lembu
- 0 -> 4 Lintah


## Menghitung Pewatekan Madya (Panca)

```java
int watekmadya = calc.hitungWatekMadya(tanggal)
```
Dimana nilai return-nya :
- 1 Gajah
- 2 Watu
- 3 Buta
- 4 Suku
- 0 ->5 Wong


## Menghitung Eka Jala Rsi

```java
int ekajalarsi = calc.hitungEkaJalaRsi(tanggal)
```
Dimana nilai return-nya :
- 1	Bagna mapasah
- 2	Bahu putra
- 3	Buat astawa
- 4	Buat lara
- 5	Buat merang
- 6	Buat sebet
- 7	Buat kingking
- 8	Buat suka
- 9	Dahat kingking
- 10 Kamaranan
- 11 Kamretaan
- 12 Kasobagian
- 13 Kinasihan amreta
- 14 Kinasihan jana
- 15 Langgeng kayohanaan
- 16 Lewih bagia
- 17 Manggih bagia
- 18 Manggih suka
- 19 Patining amreta
- 20 Rahayu
- 21 Sidha kasobagian
- 22 Subagia
- 23 Suka kapanggih
- 24 Suka pinanggih
- 25 Suka rahayu
- 26 Tininggaling suka
- 27 Wredhi putra
- 28 Wredhi sarwa mule


## Menghitung Palalintangan

```java
int lintang = calc.hitungLintang(tanggal)
```
Dimana nilai return-nya :
- 1 Gajah
- 2 Kiriman
- 3 Jong Sarat
- 4 Atiwa-tiwa
- 5 Sangka Tikel
- 6 Bubu Bolong
- 7 Sugenge
- 8 Uluku/Tenggala
- 9 Pedati
- 10 Kuda
- 11 Gajah Mina
- 12 Bade
- 13 Magelut
- 14 Pagelangan
- 15 Kala Sungsang
- 16 Kukus
- 17 Asu
- 18 Kartika
- 19 Naga
- 20 Banak Angerem
- 21 Hru/Panah
- 22 Patrem
- 23 Lembu
- 24 Depat/Sidamalung
- 25 Tangis
- 26 Salah Ukur
- 27 Perahu Pegat
- 28 Puwuh Atarung
- 29 Lawean/Goang
- 30 Kelapa
- 31 Yuyu
- 32 Lumbung
- 33 Kumbha
- 34 Udang
- 35 Begoong


## Menghitung Panca Sudha

```java
int pancasudha = calc.hitungPancasudha(tanggal)
```
Dimana nilai return-nya :
- 1 Wisesa segara
- 2 Tunggak semi
- 3 Satria wibhawa
- 4 Sumur sinaba
- 5 Bumi kapetak
- 6 Satria wirang
- 7 Lebu katiup angin


## Menghitung Pararasan

```java
int pararasan = calc.hitungPararasan(tanggal)
```
Dimana nilai return-nya :
- 1 Laku bumi
- 2 Laku api
- 3 Laku angin
- 4 Laku pandita sakti
- 5 Aras tuding
- 6 Aras kembang
- 7 Laku bintang
- 8 Laku bulan
- 9 Laku surya
- 10 Laku air/toya
- 11 Laku pretiwi
- 12 Laku agni agung


## Menghitung Rakam

```java
int rakam = calc.hitungRakam(tanggal)
```
Dimana nilai return-nya :
- 1 Kala tinatang
- 2 Demang kandhuruwan
- 3 Sanggar waringin
- 4 Mantri sinaroja
- 5 Macam katawan
- 0 -> 6 Nuju pati

## Menghitung Zodiak

```java
int zodiak = calc.hitungZodiak(tanggal)
```
Dimana nilai return-nya :
- 1 Aries
- 2 Taurus
- 3 Gemini
- 4 Cancer
- 5 Leo
- 6 Virgo
- 7 Libra
- 8 Scorpio
- 9 Sagitarius
- 10 Capricon
- 11 Aquarius
- 12 Pisces


## Pustaka
Class pada package ini dimbil dari skripsi S1 saya pada stikom Bali http://digilib.stikom-bali.ac.id/Skripsi_TA.aspx?appid=003259

Buku-buku yang dijadikan acuan adalah "Dasar Wariga", dan "Tenung Wariga" karangan I.B. Putra Manik Aryana, serta "Pokok-pokok Wariga" karangan I.B. Supartha Ardana.

Selain itu juga banyak diambil informasi dari http://www.babadbali.com/ dan http://www.kalenderbali.org/
