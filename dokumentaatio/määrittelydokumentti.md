# Määrittelydokumentti

## Ongelma: Kauppamatkustajan ongelma

Tavoitteena on etsiä lyhin kaikkien kaupunkien kautta kulkeva reitti takaisin lähtökaupunkiin siten, että jokaisessa kaupungissa vieraillaan täsmälleen kerran. Ennakolta tiedetään kaupunkien lukumäärä ja niiden väliset etäisyydet, jotka ovat molempiin suuntiin yhtä suuret. Kaupunkeja on _n_ kappaletta ja, joten ongelmalle on olemassa kaikkiaan _(n-1)!/2_ mahdollista ratkaisua.

Kauppamatkustajan ongelma on NP-kova ongelma, eli sille ei tunneta polynomisessa ajassa toteutettavissa olevaa tarkkaa ratkaisua. Yksittäisen reitin löytäminen on nopeaa kaupunkien määrästä riippumatta, mutta reitti voi varmistua lyhimmäksi vasta kun kaikki vaihtoehdot on tarkistettu.

## Algoritmit ja tietorakenteet

Tarkoituksena on selvittää noin 10 kaupungin osalta lyhimmät reitit brute forcella ja sen lisäksi kokeilla likimääräisten ratkaisujen saamista lähimmän naapurin heuristiikalla sekä Christofidesin heuristiikkaa käyttämällä. Kaikille näille syöte annetaan verkkona, käytännössä x- ja y-koordinaatit omissa taulukoissaan indeksien toimiessa solmuja määrittelevänä tekijänä. Koordinaattien perusteella solmuille lasketaan sitten keskinäiset etäisyydet etäisyysmatriisiin. Lyhin reitti palautetaan kaikissa tapauksissa taulukkona, jossa peräkkäisissä indekseissä olevat solmut ovat myös reitillä peräkkäin ja taulukon viimeisen ja ensimmäisen solmun välillä on myös suora yhteys. Reitin pituuden kannalta ei siis ole merkitystä, mistä solmusta reitin kierto aloitetaan ja kumpaan suuntaan se tehdään.

Brute force ratkaisussa kaikki vaihtoehdot käydään aina läpi ja ratkaisuksi saadaan lopulta lyhin mahdollinen reitti. Aikavaativuus tälle on O(n!).

Keskimäärin lähimmän naapurin algoritmin avulla saadaan 25% lyhintä mahdollista reittiä pidempi ratkaisu, mutta on olemassa monia kaupunkien jakaumia, joilla ratkaisu on paljon huonompi, esimerkiksi kaupunkien sijoittuminen nauhamaisesti siten että jokaisen kaupungin lähettyvillä on muitakin kaupunkeja, mutta toisistaan kauimpana olevien kaupunkien välimatka on suuri. Jos lähimmän naapurin algoritmi suoritetaan jokaisesta solmusta käsin erikseen, on lyhin ratkaisu huomattavasti parempi. Aikavaativuus on O(n^2).

Christofidesin algoritmilla saatu ratkaisu on aina korkeintaan 1,5-kertainen lyhimpään mahdolliseen reittiin verrattuna. Siinä yhdistetään useita muita algoritmeja, joiden mukaisesti se voidaan jakaa työvaiheisiin. Ensimmäiseksi syötteenä saadusta verkosta etsitään Primin algoritmilla pienin virittävä puu. Toiseksi paritonasteisille solmuille suoritetaan täydellinen minimisovitus, joka yhdistetään pienimpään virittävään puuhun. Kolmanneksi etsitään Eulerin kierros ja neljänneksi Hamiltonin kierros jättämällä Eulerin polulla useammin kuin kerran esiintyvät solmut uudelleen käymättä. Christofidesin algoritmin aikavaativuus on O(n^4).

## Tietoja

Opinto-ohjelma: Tietojenkäsittelytieteen kandiohjelma

Dokumentaation kieli: Suomi

Ohjelmointikieli: Java

## Lähteet

Tuomas Hyvönen - [Kauppamatkustajan ongelman ratkaisualgoritmien vertailu](https://erepo.uef.fi/bitstream/handle/123456789/19575/urn_nbn_fi_uef-20180562.pdf)

Roy Mathew, Divya Cherukupalli, Kevin Pusich ja Kevin Zhao - [Traveling Salesman Algorithms From Naive to Christofide](https://cse442-17f.github.io/Traveling-Salesman-Algorithms/)

Eemeli Syynimaa - [Optimointikirjaston suunnittelu robotisoidulle tuotantolinjalle](https://www.theseus.fi/bitstream/handle/10024/138506/Syynimaa_Eemeli.pdf?sequence=1&isAllowed=y)

Teemu Perasto - [Kauppamatkustajan ongelman ratkaiseminen unkarilaisella algoritmilla](https://trepo.tuni.fi/bitstream/handle/123456789/26226/perasto.pdf?sequence=4&isAllowed=y)


