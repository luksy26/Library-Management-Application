# Library-Management-Application

Fiecare clasa se afla in propriul ei fisier .java, cu acelasi nume.

Majoritatea campurilor au modificatorul de acces private, cu implementare de metode get si set, pentru protejarea datelor. Exceptie face doar campul ISBN din clasa Book.

Am definit clasa Countlines cu ideea de a scrie o metoda care numara numarul de linii din fisier, pentru initializarea vectorilor carti, autori, limbi, tari, grupuri editoriale, branduri si retaileri.

Clasa Book are o metoda addAuthor, ce adauga un autor in lista de autori.

Clasele EditorialGroup si PublishingBrand au o metoda AddBook, ce adauga o carte in lista de carti.

Clasa PublishingRetailer are o metoda addCountry, ce adauga o tara in lista de tari, dar si o metoda addArtifact, ce adauga un obiect de tip IPublishingArtifact in lista de artefacte. (obs: metodei i se va trimite referinta la un obiect Book, EditorialGroup sau PublishingBrand, care implementeaza interfata IPublishingArtifact)

Clasa Administration contine 5 metode:
  - getBooksForPublishingRetailerID: care cauta retailer-ul dupa id, apoi parcurge prin artefactele sale si extrage carti unice, folosind un vector de frecventa dupa ID-ul cartilor
 
 - getLanguagesForPublishingRetailerID: care foloseste metoda descrisa mai sus pentru a extrage cartile retailerului, apoi le parcurge si adauga limbi unice, folosind un vector de frecventa dupa ID-ul limbilor
  
  - getCountriesForBookID: care itereaza prin retaileri si foloseste metoda getBooksForPublishingRetailerID pentru a extrage cartile pentru fiecare retailer in parte. Apoi se parcurg cartile si se cauta cartea al carui ID a fost dat ca si argument. Daca este gasita, extragem tara respectiva dupa ID, cu conditia sa fie deja gasita (tot folosind un vector de frecventa dupa ID-ul tarii)
 
 - getCommonBooksForRetailerIDs: care foloseste intai metoda getBooksForPublishingRetailerID pentru a extrag cartile celor 2 retaileri. Apoi marcam toate cartile din retailer1 dupa ID, si parcurgem cartile din retailer2. Cartile care ne intereseaza sunt cele din retailer2 al caror ID a fost deja marcat, deci sunt comune.
 
 - getAllBooksForRetailerIDs: cu o implementare similara ca mai sus, cu exceptia faptului ca ne intereseaza toate cartile din retailer1 indiferent ce se afla in retailer2, marcandu-le in vectorul de frecventa. Apoi parcurgem retailer2, adaugand cartile al caror ID NU a fost deja marcat, deci obtinem reuniunea cartilor din cei doi retaileri. Obs: complexitatea este liniara n + m (n carti din retailer1 si m carti din retailer2), daca nu luam in calcul complexitatea metodei getBooksForPublishingRetailerID (care depinde de numarul de Artefacte, distributia lor dupa cele 3 tipuri si numarul de carti din tipurile EditorialGroup si PublishingBrand)


In main: Citim pe rand datele din toate fisierele. Prelucrarea datelor de pe o linie se face cu metoda split dupa pattern-ul "###". La final am facut cate 5 exemple pentru fiecare dintre cele 5 metode din clasa Administration.
