Scrivete una query SQL che restituisca solo i record dalla tabella "products" con il prezzo superiore a 50.





\-- Seleziono il database su cui lavorare

USE classicmodels;



\-- Seleziono solo i record dalla tabella "products" 

\-- e filtro solo i prodotti con prezzo superiore a 50

SELECT \* 

FROM products

WHERE buyPrice > 50;



\-- Seleziono il prezzo dalla tabella "products", 

\-- filtro i prezzi maggiori di 50 e ordino dal più grande al più piccolo

SELECT buyPrice 

FROM products

WHERE buyPrice > 50

ORDER BY buyPrice DESC;



\----------------------------------------------------------



Scrivete una query SQL che restituisca tutti i record dalla tabella "orders" ordinati per data in ordine decrescente.



\-- Seleziono tutti i campi dalla tabella "orders" e ordino per data dell'ordine dal più recente al più vecchio

SELECT \* 

FROM orders

ORDER BY orderDate DESC



\--------------------------------------



Scrivete una query SQL che aggiorni il prezzo di tutti i prodotti nella tabella"products" aumentandolo del 10%.



UPDATE products

SET buyPrice = buyPrice \* 1.10;



UPDATE products

SET buyPrice + (buyPrice \* 10 / 100)



\-----------------------------



Scrivete una query SQL che inserisca un nuovo utente nella tabella "customers".



\-- Inserisco un nuovo cliente nella tabella "customers" specificando tutti i campi obbligatori

\-- Inserisco un nuovo cliente con solo i campi obbligatori (Null = No)



\-- INSERT IGNORE INTO customers



INSERT INTO customers (

&#x20;   customerNumber,

&#x20;   customerName,

&#x20;   contactLastName,

&#x20;   contactFirstName,

&#x20;   phone,

&#x20;   addressLine1,

&#x20;   city,

&#x20;   country

)

VALUES (

&#x20;   501,             

&#x20;   'Bayloncina S.p.A.',  

&#x20;   'Miriam',         

&#x20;   'Baylon',         

&#x20;   '3201234567',    

&#x20;   'Via Roma 1',    

&#x20;   'Barletta',      

&#x20;   'Italy'          

);



\---------------------------------------------------



Scrivete una query SQL che elimini tutti gli ordini nella tabella "orders" con lo stato di "Cancelled".



\-- Verifico prima quanti ordini verranno eliminati

SELECT \* FROM orders

WHERE status = 'Cancelled';



\-- Elimino prima i dettagli degli ordini cancellati (tabella figlia)

DELETE FROM orderdetails

WHERE orderNumber IN (

&#x20;   SELECT orderNumber FROM orders WHERE status = 'Cancelled'

);



\--Poi elimino gli ordini cancellati (tabella padre)

DELETE FROM orders

WHERE status = 'Cancelled';



\---------------------------------------------------------



Scrivete una query SQL che restituisca tutti gli utenti dalla tabella "customers" il cui nome inizia con la S e vivono in California



\-- Seleziono tutti i clienti il cui nome inizia con "S" e che vivono in California

SELECT \* 

FROM customers

WHERE customerName LIKE 'S%'

AND state = 'CA';



\-------------------------

Si vogliono recuperare dal database "world" la lingua e la nazione di ognicittà.



\-- Seleziona il nome della città, il nome della nazione e la lingua parlata

SELECT 

&#x20;   city.Name AS Citta, 

&#x20;   country.Name AS Nazione, 

&#x20;   countrylanguage.Language AS Lingua

FROM 

&#x20;   city



\-- Unisce la tabella città con quella delle nazioni tramite il codice nazione

inner JOIN 

&#x20;   country ON city.CountryCode = country.Code



\-- Unisce la tabella delle nazioni con quella delle lingue per associare gli idiomi parlati

inner JOIN 

&#x20;   countrylanguage ON country.Code = countrylanguage.CountryCode;



\----------------------------------------



Si vuole recuperare il numero di città per nazione dal database "world" mostrando anche il nome della nazione e ordinarli in base al numero di città.



\-- Seleziona il nome della nazione e conta il numero di occorrenze delle città

SELECT 

&#x20;   country.Name AS Nazione, 

&#x20;   COUNT(city.ID) AS Numero\_Citta

FROM 

&#x20;   country

\-- Unisce le nazioni alle loro città tramite il codice identificativo

JOIN 

&#x20;   city ON country.Code = city.CountryCode

\-- Raggruppa i risultati per nazione così da poter eseguire il conteggio

GROUP BY 

&#x20;   country.Code

\-- Ordina l'elenco in base al conteggio (dal valore più alto al più basso)

ORDER BY 

&#x20;   Numero\_Citta DESC;







\-- Conta le città includendo anche le nazioni che ne hanno zero

SELECT 

&#x20;   country.Name AS Nazione, 

&#x20;   COUNT(city.ID) AS Numero\_Citta

FROM 

&#x20;   country

\-- Usa LEFT JOIN per mantenere tutte le nazioni, anche se non hanno città nella tabella city

LEFT JOIN 

&#x20;   city ON country.Code = city.CountryCode

\-- Raggruppa per nazione

GROUP BY 

&#x20;   country.Code

\-- Ordina in modo crescente per vedere subito chi ha 0 o poche città

ORDER BY 

&#x20;   Numero\_Citta ASC;





\----------------------------------------



Si vuole conoscere la lista di repubbliche con aspettativa di vita maggiore di 70 anni, inoltre si vuole visualizzare anche la lingua parlata.



\-- Seleziona il nome della nazione, l'aspettativa di vita e la lingua

SELECT 

&#x20;   country.Name AS Nazione, 

&#x20;   country.LifeExpectancy AS Aspettativa\_Vita, 

&#x20;   countrylanguage.Language AS Lingua,

country.GovernmentForm AS Tipo\_di\_republic

FROM 

&#x20;   country

\-- Unisce la tabella nazioni con le lingue tramite il codice nazione

JOIN 

&#x20;   countrylanguage ON country.Code = countrylanguage.CountryCode

\-- Filtra per nazioni che sono repubbliche e con vita media > 70 anni

WHERE 

&#x20;   country.GovernmentForm LIKE '%Republic%' 

&#x20;   AND country.LifeExpectancy > 70;



\-----------------------------------------------



Visualizza l'elenco di tutti i pagamenti effettuati dai clienti, mostrando il nome del cliente, la data del pagamento e l'importo. Ordina per data più recente.



\-- Seleziona il nome del cliente, la data e l'importo del pagamento

SELECT 

&#x20;   customers.customerName AS Cliente, 

&#x20;   payments.paymentDate AS Data\_Pagamento, 

&#x20;   payments.amount AS Importo

FROM 

&#x20;   customers

\-- Unisce i clienti ai loro pagamenti tramite il numero identificativo del cliente

JOIN 

&#x20;   payments ON customers.customerNumber = payments.customerNumber

\-- Ordina i risultati partendo dalla data più recente (dalla più grande alla più piccola)

ORDER BY 

&#x20;   payments.paymentDate DESC;





\------------------------------



Selezionate tutti i clienti che non hanno mai fatto un ordine, 

da risolvere con join e con subquery



\-- Seleziona i clienti che non hanno corrispondenze nella tabella ordini

SELECT 

&#x20;   customers.customerNumber AS IdCliente,

&#x20;   customers.customerName AS Cliente,

&#x20;   orders.orderNumber AS OrdineNull

FROM 

&#x20;   customers

LEFT JOIN 

&#x20;   orders ON customers.customerNumber = orders.customerNumber

WHERE 

&#x20;   orders.orderNumber IS NULL;







\-- Seleziona i clienti il cui numero NON compare nella lista degli ordini con subquery

SELECT 

&#x20;   customerName AS ClienteSenzaOrdine

FROM 

&#x20;   customers

WHERE 

&#x20;   customerNumber NOT IN (

SELECT customerNumber 

FROM orders);



\------------------------------------------------------------------------



Per ogni cliente il totale dei suoi ordini



\-- Calcola la somma totale (quantità \* prezzo) per ogni cliente

SELECT 

&#x20;   customers.customerName AS Cliente, 

&#x20;   SUM(orderdetails.quantityOrdered \* orderdetails.priceEach) AS Totale\_Ordini

FROM 

&#x20;   customers

\-- Collega i clienti agli ordini

JOIN 

&#x20;   orders ON customers.customerNumber = orders.customerNumber

\-- Collega gli ordini ai dettagli (dove ci sono i prezzi dei prodotti)

JOIN 

&#x20;   orderdetails ON orders.orderNumber = orderdetails.orderNumber

\-- Raggruppa i calcoli per ogni singolo cliente

GROUP BY 

&#x20;   customers.customerNumber

\-- Ordina dal cliente che ha speso di più

ORDER BY 

&#x20;   Totale\_Ordini DESC;



\-------------------------------------------------



Produci una lista di tutti i dipendenti che mostri il loro nome e cognome insieme al nome e cognome del loro diretto superiore



\-- Seleziona nome/cognome del dipendente e nome/cognome del suo capo

SELECT 

&#x20;   e.firstName AS Nome\_Dipendente, 

&#x20;   e.lastName AS Cognome\_Dipendente, 

&#x20;   m.firstName AS Nome\_Capo, 

&#x20;   m.lastName AS Cognome\_Capo,

&#x20;   m.jobTitle AS Ruolo

FROM 

&#x20;   employees AS e

\-- Collega la tabella a se stessa: il codice del capo (reportsTo) deve corrispondere al numero dipendente (employeeNumber)

LEFT JOIN 

&#x20;   employees AS m ON e.reportsTo = m.employeeNumber;



\-----------



Si vogliono recuperare dal database "world" la lingua e la nazione di ogni città.

\-- Recupero il nome della città, la nazione e la lingua unendo le tre tabelle tramite CountryCode





SELECT 

&#x20;   city.Name AS Città,

&#x20;   country.Name AS Nazione,

&#x20;   countrylanguage.Language AS Lingua

FROM city

JOIN country ON city.CountryCode = country.Code

JOIN countrylanguage ON city.CountryCode = countrylanguage.CountryCode;



\-------------------- 



Si vuole recuperare il numero di città per nazione dal database "world" mostrando anche il nome della nazione e ordinarli in base al numero di città.



SELECT 

&#x20;   country.Name AS Nazione, 

&#x20;   COUNT(city.ID) AS Numero\_Citta

FROM 

&#x20;   country

JOIN 

&#x20;   city ON country.Code = city.CountryCode

GROUP BY 

&#x20;   country.Code

ORDER BY 

&#x20;   Numero\_Citta DESC;



\---------------------------------------------------



Si vuole conoscere la lista di repubbliche con aspettativa di vita maggiore dei 70 anni, inoltre si vuole visualizzare anche la lingua parlata.



SELECT 

&#x20;   country.Name AS Nazione, 

&#x20;   country.LifeExpectancy AS Aspettativa\_Vita, 

&#x20;   countrylanguage.Language AS Lingua

FROM 

&#x20;   country

\-- Colleghiamo le nazioni alle loro lingue

JOIN 

&#x20;   countrylanguage ON country.Code = countrylanguage.CountryCode

\-- Filtriamo per forma di governo e aspettativa di vita

WHERE 

&#x20;   country.GovernmentForm LIKE '%Republic%' 

&#x20;   AND country.LifeExpectancy > 70;



\--------------------------------------

Ottenere una lista unica di

città con popolazione > 5 milioni

capitali dei paesi

utilizza UNION



\-- Primo insieme: Città molto popolose

SELECT 

&#x20;   Name AS Nome\_Citta, 

&#x20;   Population AS Popolazione

FROM 

&#x20;   city

WHERE 

&#x20;   Population > 5000000



UNION



\-- Secondo insieme: Capitali dei paesi

SELECT 

&#x20;   city.Name AS Nome\_Citta, 

&#x20;   city.Population AS Popolazione

FROM 

&#x20;   city

JOIN 

&#x20;   country ON city.ID = country.Capital;

\----------------------------------------------



si vuole recuperare dal database world le lingue parlate per nazione con la rispettiva percentuale di utilizzo



\-- Seleziona il nome della nazione, la lingua e la percentuale

SELECT 

&#x20;   country.Name AS Nazione, 

&#x20;   countrylanguage.Language AS Lingua, 

&#x20;   countrylanguage.Percentage AS Percentuale\_Utilizzo

FROM 

&#x20;   country

\-- Unisce le nazioni alle loro lingue tramite il codice nazione

JOIN 

&#x20;   countrylanguage ON country.Code = countrylanguage.CountryCode

\-- Ordina per nazione e poi per la lingua più parlata all'interno di essa

ORDER BY 

&#x20;   country.Name ASC, 

&#x20;   countrylanguage.Percentage DESC;



\--------------------------------------------------



Si vuole recuperare dal database WORLD le nazioni e la percentuale della lingua più parlata;



SELECT 

&#x20;   country.Name AS Nazione, 

&#x20;   countrylanguage.Language AS Lingua\_Principale, 

&#x20;   countrylanguage.Percentage AS Percentuale

FROM 

&#x20;   country

JOIN 

&#x20;   countrylanguage ON country.Code = countrylanguage.CountryCode

WHERE 

&#x20;   countrylanguage.Percentage = (

&#x20;       SELECT MAX(Percentage) 

&#x20;       FROM countrylanguage 

&#x20;       WHERE CountryCode = country.Code

&#x20;   )

ORDER BY 

&#x20;   country.Name;



\---------------------------------------------------------



Unire gli esercizi 1 e 2 facendole diventare 

subQuery per mostrare la lingua più parlata di una nazione con la percentuale;



SELECT 

&#x20;   c.Name AS Nazione, 

&#x20;   cl.Language AS Lingua\_Principale, 

&#x20;   cl.Percentage AS Percentuale

FROM 

&#x20;   country AS c

JOIN 

&#x20;   countrylanguage AS cl ON c.Code = cl.CountryCode

WHERE 

&#x20;   cl.Percentage = (

&#x20;       -- Questa subquery trova la percentuale massima per la nazione corrente

&#x20;       SELECT MAX(Percentage) 

&#x20;       FROM countrylanguage 

&#x20;       WHERE CountryCode = c.Code

&#x20;   )

ORDER BY 

&#x20;   cl.Percentage DESC;

\------------------------------------------------------------



scrivere una query che vada a prendere le

nazioni mostrando come riposta se hanno o meno una superfice di

100.000 e se hanno registrato un IndepYear, se è presente l'anno

va mostrato altrimenti si indica che non è presente;





SELECT 

&#x20;   Name AS Nazione,

&#x20;   -- Controllo sulla superficie

&#x20;   IF(SurfaceArea >= 100000, 'Superiore o uguale a 100.000', 'Inferiore a 100.000') AS Verifica\_Superficie,

&#x20;   -- Controllo sull'anno di indipendenza

&#x20;   IFNULL(IndepYear, 'Non presente') AS Anno\_Indipendenza

FROM 

&#x20;   country;





SELECT 

&#x20;   Name,

&#x20;   CASE 

&#x20;       WHEN SurfaceArea >= 100000 THEN 'Sopra soglia'

&#x20;       ELSE 'Sotto soglia'

&#x20;   END AS Superficie,

&#x20;   CASE 

&#x20;       WHEN IndepYear IS NULL THEN 'Non presente'

&#x20;       ELSE CAST(IndepYear AS CHAR)

&#x20;   END AS Anno

FROM country;



\-------------------------------------------------------



recuperare e mostrare le città con il codice della nazione che

indica l'utente. Inoltre l'utente può decidere:

l'ordine con cui ordinare le città in maniera decrescente o

meno;

se filtrare le città per un minimo di popolazione;

se mostrare il nome della nazione a cui fa riferimento il code;



\-- Ipotizziamo che l'utente inserisca questi parametri:

SET @codice\_nazione = 'ITA';      -- Il codice scelto

SET @min\_popolazione = 500000;    -- Il filtro sulla popolazione (0 se non vuole filtro)

SET @mostra\_nazione = 1;          -- 1 per SI, 0 per NO



SELECT 

&#x20;   city.Name AS Citta,

&#x20;   city.Population AS Popolazione,

&#x20;   -- Mostra il nome della nazione solo se l'utente ha impostato @mostra\_nazione a 1

&#x20;   IF(@mostra\_nazione = 1, country.Name, 'N.D.') AS Nome\_Nazione

FROM 

&#x20;   city

\-- La JOIN è necessaria per recuperare il nome della nazione 

JOIN 

&#x20;   country ON city.CountryCode = country.Code

WHERE 

&#x20;   city.CountryCode = @codice\_nazione

&#x20;   AND city.Population >= @min\_popolazione

\-- L'utente decide l'ordine (esempio: decrescente per popolazione)

ORDER BY 

&#x20;   city.Population DESC;

\-----------------------------------------------



Creare una view di city del database world su una query che

mostra le città italiane.



CREATE VIEW vista\_citta\_italiane AS

SELECT 

&#x20;   ID, 

&#x20;   Name, 

&#x20;   CountryCode, 

&#x20;   District, 

&#x20;   Population

FROM 

&#x20;   city

WHERE 

&#x20;   CountryCode = 'ITA';



Su questa VIEW eseguire una query che mostra solo le città

con una popolazione inferiore ai 100k.



SELECT 

&#x20;   Name AS Citta, 

&#x20;   Population AS Popolazione

FROM 

&#x20;   vista\_citta\_italiane

WHERE 

&#x20;   Population < 100000

ORDER BY 

&#x20;   Population DESC;



\- **CON WITH** 



WITH citta\_italiane AS (

&#x20;   SELECT 

&#x20;       Name, 

&#x20;       Population, 

&#x20;       District

&#x20;   FROM 

&#x20;       city

&#x20;   WHERE 

&#x20;       CountryCode = 'ITA'

)

SELECT 

&#x20;   Name AS Citta, 

&#x20;   Population AS Popolazione

FROM 

&#x20;   citta\_italiane

WHERE 

&#x20;   Population < 100000

ORDER BY 

&#x20;   Population DESC;







