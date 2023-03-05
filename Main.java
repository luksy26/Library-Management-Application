package com.company;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Main
{
    public static void main(String[] args)
    {
        File fisier;
        String[] elementeLinie;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");

        Book[] carti = new Book[new CountLines().HowMany("books.in") - 1]; // initializare books.in
        fisier = new File("books.in");

        try(BufferedReader br = new BufferedReader(new FileReader(fisier)))
        {
            int i = 0;
            String line;
            br.readLine(); // header-ul
            while ((line = br.readLine()) != null)
            {
                carti[i] = new Book();
                elementeLinie = line.split("###");
                carti[i].setID(Integer.parseInt(elementeLinie[0]));
                carti[i].setName(elementeLinie[1]);
                carti[i].setSubtitle(elementeLinie[2]);
                carti[i].ISBN = elementeLinie[3];
                carti[i].setPageCount(Integer.parseInt(elementeLinie[4]));
                carti[i].setKeywords(elementeLinie[5]);
                carti[i].setLanguageID(Integer.parseInt(elementeLinie[6]));
                carti[i].setCreatedOn(LocalDateTime.parse(elementeLinie[7], formatter)); // parsam la tipul LocalDateTime
                ++i;
            }
        }
        catch(FileNotFoundException ex2) { System.out.println("Nu am gasit fisierul"); }
        catch(IOException ex1) { System.out.println("exceptie de intrare"); }


        Language[] limbi = new Language[new CountLines().HowMany("languages.in") - 1]; // initializare languages.in
        fisier = new File("languages.in");

        try(BufferedReader br = new BufferedReader(new FileReader(fisier)))
        {
            int i = 0;
            String line;
            br.readLine(); // header-ul
            while ((line = br.readLine()) != null)
            {
                limbi[i] = new Language();
                elementeLinie = line.split("###");
                limbi[i].setID(Integer.parseInt(elementeLinie[0]));
                limbi[i].setCode(elementeLinie[1]);
                limbi[i].setName(elementeLinie[2]);
                ++i;
            }
        }
        catch(FileNotFoundException ex2) { System.out.println("Nu am gasit fisierul"); }
        catch(IOException ex1) { System.out.println("exceptie de intrare"); }


        Author[] autori = new Author[new CountLines().HowMany("authors.in") - 1]; // initializare authors.in
        fisier = new File("authors.in");

        try(BufferedReader br = new BufferedReader(new FileReader(fisier)))
        {
            int i = 0;
            String line;
            br.readLine(); // header-ul
            while ((line = br.readLine()) != null)
            {
                autori[i] = new Author();
                elementeLinie = line.split("###");
                autori[i].setID(Integer.parseInt(elementeLinie[0]));
                autori[i].setFirstName(elementeLinie[1]);
                autori[i].setLastName(elementeLinie[2]);
                ++i;
            }
        }
        catch(FileNotFoundException ex2) { System.out.println("Nu am gasit fisierul"); }
        catch(IOException ex1) { System.out.println("exceptie de intrare"); }


        fisier = new File("books-authors.in"); // initializam legaturile dintre carti si autori

        try(BufferedReader br = new BufferedReader(new FileReader(fisier)))
        {   
            String line;
            br.readLine(); // header-ul
            while ((line = br.readLine()) != null)
            {
                elementeLinie = line.split("###");
                int idBook, idAuthor, indiceBook = 0, indiceAuthor = 0;

                idBook = Integer.parseInt(elementeLinie[0]);
                idAuthor = Integer.parseInt(elementeLinie[1]);

                for (int i = 0; i < carti.length ; ++i) // cautam cartea
                    if(carti[i].getID() == idBook)
                    {
                        indiceBook = i;
                        break;
                    }

                for (int i = 0; i< autori.length; ++i) // cautam autorul
                    if(autori[i].getID() == idAuthor)
                    {
                        indiceAuthor = i;
                        break;
                    }

                carti[indiceBook].addAuthor(autori[indiceAuthor]); // adaugam autorul
            }
        }
        catch(FileNotFoundException ex2) { System.out.println("Nu am gasit fisierul"); }
        catch(IOException ex1) { System.out.println("exceptie de intrare"); }


        EditorialGroup[] grupuriEditoriale = new EditorialGroup[new CountLines().HowMany("editorial-groups.in") - 1];
        fisier = new File("editorial-groups.in"); // initializare editorial-groups.in

        try(BufferedReader br = new BufferedReader(new FileReader(fisier)))
        {
            int i = 0;
            String line;
            br.readLine(); // header-ul
            while ((line = br.readLine()) != null)
            {
                grupuriEditoriale[i] = new EditorialGroup();
                elementeLinie = line.split("###");

                grupuriEditoriale[i].setID(Integer.parseInt(elementeLinie[0]));
                grupuriEditoriale[i].setName(elementeLinie[1]);
                ++i;
            }
        }
        catch(FileNotFoundException ex2) { System.out.println("Nu am gasit fisierul"); }
        catch(IOException ex1) { System.out.println("exceptie de intrare"); }


        PublishingBrand[] branduri = new PublishingBrand[new CountLines().HowMany("publishing-brands.in") - 1];
        fisier = new File("publishing-brands.in"); // initializare publishing-brands.in

        try(BufferedReader br = new BufferedReader(new FileReader(fisier)))
        {
            int i = 0;
            String line;
            br.readLine(); // header-ul
            while ((line = br.readLine()) != null)
            {
                branduri[i] = new PublishingBrand();
                elementeLinie = line.split("###");

                branduri[i].setID(Integer.parseInt(elementeLinie[0]));
                branduri[i].setName(elementeLinie[1]);
                ++i;
            }
        }
        catch(FileNotFoundException ex2) { System.out.println("Nu am gasit fisierul"); }
        catch(IOException ex1) { System.out.println("exceptie de intrare"); }


        fisier = new File("editorial-groups-books.in"); // initializare legaturi intre grupuri editoriale si carti

        try(BufferedReader br = new BufferedReader(new FileReader(fisier)))
        {
            String line;
            br.readLine(); // header-ul
            while ((line = br.readLine()) != null)
            {
                elementeLinie = line.split("###");
                int idBook, idGroup, indiceBook = 0, indiceGroup = 0;

                idGroup = Integer.parseInt(elementeLinie[0]);
                idBook = Integer.parseInt(elementeLinie[1]);

                for (int i = 0; i < carti.length ; ++i) // cautam cartea
                    if(carti[i].getID() == idBook)
                    {
                        indiceBook = i;
                        break;
                    }

                for (int i = 0; i< grupuriEditoriale.length; ++i) // cautam grupul
                    if(grupuriEditoriale[i].getID() == idGroup)
                    {
                        indiceGroup = i;
                        break;
                    }

                grupuriEditoriale[indiceGroup].addBook(carti[indiceBook]);
            }
        }
        catch(FileNotFoundException ex2) { System.out.println("Nu am gasit fisierul"); }
        catch(IOException ex1) { System.out.println("exceptie de intrare"); }


        fisier = new File("publishing-brands-books.in"); // initializare legaturi intre brand-uri si carti

        try(BufferedReader br = new BufferedReader(new FileReader(fisier)))
        {
            String line;
            br.readLine(); // header-ul
            while ((line = br.readLine()) != null)
            {
                elementeLinie = line.split("###");
                int idBook, idPublisher, indiceBook = 0, indicePublisher = 0;

                idPublisher = Integer.parseInt(elementeLinie[0]);
                idBook = Integer.parseInt(elementeLinie[1]);

                for (int i = 0; i < carti.length ; ++i) // cautam cartea
                    if(carti[i].getID() == idBook)
                    {
                        indiceBook = i;
                        break;
                    }

                for (int i = 0; i< branduri.length; ++i) // cautam brandul
                    if(branduri[i].getID() == idPublisher)
                    {
                        indicePublisher = i;
                        break;
                    }

                branduri[indicePublisher].addBook(carti[indiceBook]);
            }
        }
        catch(FileNotFoundException ex2) { System.out.println("Nu am gasit fisierul"); }
        catch(IOException ex1) { System.out.println("exceptie de intrare"); }


        PublishingRetailer[] retaileri = new PublishingRetailer[new CountLines().HowMany("publishing-retailers.in") - 1];
        fisier = new File("publishing-retailers.in"); // initializare publishing-retailers.in

        try(BufferedReader br = new BufferedReader(new FileReader(fisier)))
        {
            int i = 0;
            String line;
            br.readLine(); // header-ul
            while ((line = br.readLine()) != null)
            {
                retaileri[i] = new PublishingRetailer();
                elementeLinie = line.split("###");

                retaileri[i].setID(Integer.parseInt(elementeLinie[0]));
                retaileri[i].setName(elementeLinie[1]);
                ++i;
            }
        }
        catch(FileNotFoundException ex2) { System.out.println("Nu am gasit fisierul"); }
        catch(IOException ex1) { System.out.println("exceptie de intrare"); }


        Country[] tari = new Country[new CountLines().HowMany("countries.in") - 1];
        fisier = new File("countries.in"); // initializare countries.in

        try(BufferedReader br = new BufferedReader(new FileReader(fisier)))
        {
            int i = 0;
            String line;
            br.readLine(); // header-ul
            while ((line = br.readLine()) != null)
            {
                tari[i] = new Country();
                elementeLinie = line.split("###");

                tari[i].setID(Integer.parseInt(elementeLinie[0]));
                tari[i].setCountryCode(elementeLinie[1]);
                ++i;
            }
        }
        catch(FileNotFoundException ex2) { System.out.println("Nu am gasit fisierul"); }
        catch(IOException ex1) { System.out.println("exceptie de intrare"); }


        fisier = new File("publishing-retailers-countries.in"); // initializare legaturi intre retaileri si tari

        try(BufferedReader br = new BufferedReader(new FileReader(fisier)))
        {
            String line;
            br.readLine(); // header-ul
            while ((line = br.readLine()) != null)
            {
                elementeLinie = line.split("###");
                int idRetailer, idCountry, indiceRetailer = 0, indiceCountry = 0;

                idRetailer = Integer.parseInt(elementeLinie[0]);
                idCountry = Integer.parseInt(elementeLinie[1]);

                for (int i = 0; i < retaileri.length ; ++i) // cautam retailer-ul
                    if(retaileri[i].getID() == idRetailer)
                    {
                        indiceRetailer = i;
                        break;
                    }

                for (int i = 0; i< tari.length; ++i) // cautam tara
                    if(tari[i].getID() == idCountry)
                    {
                        indiceCountry = i;
                        break;
                    }

                retaileri[indiceRetailer].addCountry(tari[indiceCountry]);
            }
        }
        catch(FileNotFoundException ex2) { System.out.println("Nu am gasit fisierul"); }
        catch(IOException ex1) { System.out.println("exceptie de intrare"); }


        fisier = new File("publishing-retailers-books.in"); // initializare legaturi intre retaileri si carti

        try(BufferedReader br = new BufferedReader(new FileReader(fisier)))
        {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null)
            {
                elementeLinie = line.split("###");
                int idRetailer, idBook, indiceRetailer = 0, indiceBook = 0;

                idRetailer = Integer.parseInt(elementeLinie[0]);
                idBook = Integer.parseInt(elementeLinie[1]);

                for (int i = 0; i < retaileri.length ; ++i) // cautam retailer-ul
                    if(retaileri[i].getID() == idRetailer)
                    {
                        indiceRetailer = i;
                        break;
                    }

                for (int i = 0; i< carti.length; ++i) // cautam cartea
                    if(carti[i].getID() == idBook)
                    {
                        indiceBook = i;
                        break;
                    }
                retaileri[indiceRetailer].addArtifact(carti[indiceBook]);
            }
        }
        catch(FileNotFoundException ex2) { System.out.println("Nu am gasit fisierul"); }
        catch(IOException ex1) { System.out.println("exceptie de intrare"); }


        fisier = new File("publishing-retailers-editorial-groups.in");
        // initializare legaturi intre retaileri si grupuri

        try(BufferedReader br = new BufferedReader(new FileReader(fisier)))
        {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null)
            {
                elementeLinie = line.split("###");
                int idRetailer, idGrup, indiceRetailer = 0, indiceGrup = 0;

                idRetailer = Integer.parseInt(elementeLinie[0]);
                idGrup = Integer.parseInt(elementeLinie[1]);

                for (int i = 0; i < retaileri.length ; ++i) // cautam retailer-ul
                    if(retaileri[i].getID() == idRetailer)
                    {
                        indiceRetailer = i;
                        break;
                    }

                for (int i = 0; i< grupuriEditoriale.length; ++i) // cautam grupul
                    if(grupuriEditoriale[i].getID() == idGrup)
                    {
                        indiceGrup = i;
                        break;
                    }
                retaileri[indiceRetailer].addArtifact(grupuriEditoriale[indiceGrup]);
            }
        }
        catch(FileNotFoundException ex2) { System.out.println("Nu am gasit fisierul"); }
        catch(IOException ex1) { System.out.println("exceptie de intrare"); }


        fisier = new File("publishing-retailers-publishing-brands.in");
        // initializare legaturi intre retaileri si branduri

        try(BufferedReader br = new BufferedReader(new FileReader(fisier)))
        {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null)
            {
                elementeLinie = line.split("###");
                int idRetailer, idBrand, indiceRetailer = 0, indiceBrand = 0;

                idRetailer = Integer.parseInt(elementeLinie[0]);
                idBrand = Integer.parseInt(elementeLinie[1]);

                for (int i = 0; i < retaileri.length ; ++i) // cautam retailer-ul
                    if(retaileri[i].getID() == idRetailer)
                    {
                        indiceRetailer = i;
                        break;
                    }

                for (int i = 0; i < branduri.length; ++i) // cautam brandul
                    if(branduri[i].getID() == idBrand)
                    {
                        indiceBrand = i;
                        break;
                    }
                retaileri[indiceRetailer].addArtifact(branduri[indiceBrand]);
            }
        }
        catch(FileNotFoundException ex2) { System.out.println("Nu am gasit fisierul"); }
        catch(IOException ex1) { System.out.println("exceptie de intrare"); }


        Administration administrare = new Administration(); // obiect nou pentru apelare metode

        administrare.setLimbi(limbi);
        administrare.setRetaileri(retaileri);

        Book[] cartiRetailer;

        System.out.println();
        System.out.println("TESTE PENTRU METODA getBooksForPublishingRetailerID");
        System.out.println();

        cartiRetailer = administrare.getBooksForPublishingRetailerID(1);
        for(int i = 0; cartiRetailer[i] != null; ++i) System.out.println(cartiRetailer[i].getName());
        System.out.println();
        cartiRetailer = administrare.getBooksForPublishingRetailerID(3);
        for(int i = 0; cartiRetailer[i] != null; ++i) System.out.println(cartiRetailer[i].getName());
        System.out.println();
        cartiRetailer = administrare.getBooksForPublishingRetailerID(2);
        for(int i = 0; cartiRetailer[i] != null; ++i) System.out.println(cartiRetailer[i].getName());
        System.out.println();
        cartiRetailer = administrare.getBooksForPublishingRetailerID(5);
        for(int i = 0; cartiRetailer[i] != null; ++i) System.out.println(cartiRetailer[i].getName());
        System.out.println();
        cartiRetailer = administrare.getBooksForPublishingRetailerID(29);
        for(int i = 0; cartiRetailer[i] != null; ++i) System.out.println(cartiRetailer[i].getName());
        System.out.println();

        Language[] limbiRetailer;

        System.out.println("TESTE PENTRU METODA getLanguagesForPublishingRetailerID");
        System.out.println();

        limbiRetailer = administrare.getLanguagesForPublishingRetailerID(12);
        for(int i = 0; limbiRetailer[i] != null; ++i) System.out.println(limbiRetailer[i].getName());
        System.out.println();
        limbiRetailer = administrare.getLanguagesForPublishingRetailerID(3);
        for(int i = 0; limbiRetailer[i] != null; ++i) System.out.println(limbiRetailer[i].getName());
        System.out.println();
        limbiRetailer = administrare.getLanguagesForPublishingRetailerID(5);
        for(int i = 0; limbiRetailer[i] != null; ++i) System.out.println(limbiRetailer[i].getName());
        System.out.println();
        limbiRetailer = administrare.getLanguagesForPublishingRetailerID(33);
        for(int i = 0; limbiRetailer[i] != null; ++i) System.out.println(limbiRetailer[i].getName());
        System.out.println();
        limbiRetailer = administrare.getLanguagesForPublishingRetailerID(29);
        for(int i = 0; limbiRetailer[i] != null; ++i) System.out.println(limbiRetailer[i].getName());
        System.out.println();

        Country[] tariCarte;

        System.out.println("TESTE PENTRU METODA getCountriesForBookID");
        System.out.println();

        tariCarte = administrare.getCountriesForBookID(204);
        for(int i = 0; tariCarte[i] != null; ++i) System.out.println(tariCarte[i].getCountryCode());
        System.out.println();
        tariCarte = administrare.getCountriesForBookID(3188);
        for(int i = 0; tariCarte[i] != null; ++i) System.out.println(tariCarte[i].getCountryCode());
        System.out.println();
        tariCarte = administrare.getCountriesForBookID(395);
        for(int i = 0; tariCarte[i] != null; ++i) System.out.println(tariCarte[i].getCountryCode());
        System.out.println();
        tariCarte = administrare.getCountriesForBookID(730);
        for(int i = 0; tariCarte[i] != null; ++i) System.out.println(tariCarte[i].getCountryCode());
        System.out.println();
        tariCarte = administrare.getCountriesForBookID(1038);
        for(int i = 0; tariCarte[i] != null; ++i) System.out.println(tariCarte[i].getCountryCode());
        System.out.println();

        Book[] cartiComune, cartiToate;

        System.out.println("TESTE PENTRU METODA getCommonBooksForRetailerIDs");
        System.out.println();

        cartiComune = administrare.getCommonBooksForRetailerIDs(1,3);
        for(int i = 0; cartiComune[i] != null; ++i) System.out.println(cartiComune[i].getName());
        System.out.println();
        cartiComune = administrare.getCommonBooksForRetailerIDs(2,5);
        for(int i = 0; cartiComune[i] != null; ++i) System.out.println(cartiComune[i].getName());
        System.out.println();
        cartiComune = administrare.getCommonBooksForRetailerIDs(2,29);
        for(int i = 0; cartiComune[i] != null; ++i) System.out.println(cartiComune[i].getName());
        System.out.println();
        cartiComune = administrare.getCommonBooksForRetailerIDs(12,16);
        for(int i = 0; cartiComune[i] != null; ++i) System.out.println(cartiComune[i].getName());
        System.out.println();
        cartiComune = administrare.getCommonBooksForRetailerIDs(3,30);
        for(int i = 0; cartiComune[i] != null; ++i) System.out.println(cartiComune[i].getName());
        System.out.println();

        System.out.println("TESTE PENTRU METODA getAllBooksForRetailerIDs");
        System.out.println();

        cartiToate = administrare.getAllBooksForRetailerIDs(1,3);
        for(int i = 0; cartiToate[i] != null; ++i) System.out.println(cartiToate[i].getName());
        System.out.println();
        cartiToate = administrare.getAllBooksForRetailerIDs(2,5);
        for(int i = 0; cartiToate[i] != null; ++i) System.out.println(cartiToate[i].getName());
        System.out.println();
        cartiToate = administrare.getAllBooksForRetailerIDs(2,29);
        for(int i = 0; cartiToate[i] != null; ++i) System.out.println(cartiToate[i].getName());
        System.out.println();
        cartiToate = administrare.getAllBooksForRetailerIDs(12,16);
        for(int i = 0; cartiToate[i] != null; ++i) System.out.println(cartiToate[i].getName());
        System.out.println();
        cartiToate = administrare.getAllBooksForRetailerIDs(3,30);
        for(int i = 0; cartiToate[i] != null; ++i) System.out.println(cartiToate[i].getName());

        System.out.println();
        System.out.println(retaileri[0].getArtifacts()[0].Publish());
    }
}