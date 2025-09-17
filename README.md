
[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/teLsEufN)


# Oppgave 1

I oppgave 1 lagde en metode fo å legge inn eny verdi i treet. satte opp p som roten og opprettet en
hjelpevariabel q. Brukte while-løke for å søke etter det riktige stedet for 
den nye verdien. Jeg oppdaterte også q til å være foreldrenoden til p. hvis verdien til 
p var større enn den nye verdien, ble p oppdatert til å være sin høyre node.
ellers ble den oppdatert til vesntre node. While løkka kjører helt til den nye verdien er lagt inn i treet.


# oppgave 2
I denne oppgaven skulle man telle hvor mange verdier. lagde en en node p og teller variabel,
sjekket for om verdi er i treet. hvis den ikke var skulle den retunere 0. bruke en while løkke for å gå gjennom treet.
lagde en hjelpemetode som skulle sammenligne p.verdi med den nye verdien 


# oppgave 3
lagde 2 metoder. Den første skulle retunere førstePostorden og den andre skulle retunere nestePostorden.
i postorden besøker man først venstre barn, deretter høyre barn og  til slutt selve noden. det er ikke mulig at den 
første noden i postoren å gå fra venstre til høyre, derfor lagde jeg en while løkke som kjørte helt til p er null 

i nestePostorden  så sjekker jeg gjennom 3 ulike tilfeller. Den første tilfellet er når p er høyre barn så skulle jeg
retunere p.forleldre. neste var hvis den ikke var venstre barn, men venstre barn.Det siste tilfellet gjelder 
når p sin forelder ikke har et høyre barn, da blir neste noden i postorden p.forelder.


# oppgave 4
I denne oppgaven så skulle jeg bruke metoden fra oppgave 3 til å finne første, neste også neste node.
Jeg satt p til å være førstepostorden. også bruke while løkke for å finne de neste postorden. skulle også 
utføre en oppgave som skulle skrive ut p sin nodeverdi. I den rekursive metode så bruke jeg huskereglen for 
postorden som er venstre, høyre også node.

i den andre rekursive metoden det den gjør er å sjelle om p.venstre og høyre ikke er lik null.


# oppgave 5
I denne oppgaven skulle jeg lage en fjern alle metode som skulle fjerne et spesifikk verdi
fra treet. Den bruker en while løkke for å gå gjennom treet og sammenligne den spesifiserte verdien
med verdien i noden. etter at den er sammenlignet går den enten til ve stre eller høyre i treet. 
Hvis den er funnet går ut av løkken.


Lagde også en fjenAlle metoden som skulle retunere antall tall
som blir fjernet. lagde en while løkker som skulle kjøre helt til alle
verdiene er tomme og hver gang gang den kjørte la jeg til en teller.
til slutt viser den antall som ble fjernet.

I nullstill metoden begynner med å lagre antall noder i treet i midlertid
variabelen temp. Dett gjøre det lettere å holde oversikt over hvor mange noder 
som ble fjernet senere. Den kaller også hjelpemetoden, denne metoden 
tar inn parameter og fjener verdien





