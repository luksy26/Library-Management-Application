package com.company;

class Administration
{
    private PublishingRetailer[] retaileri;
    private Language[] limbi;

    void setRetaileri(PublishingRetailer[] retaileri) { this.retaileri = retaileri; }
    void setLimbi(Language[] limbi) { this.limbi = limbi; }

    Book[] getBooksForPublishingRetailerID (int publishingRetailerID)
    {
        int indiceRetailer = 0, numberOfBooks = 0;
        int[] IDs = new int[50000];
        Book carte;
        EditorialGroup grupEditorial;
        PublishingBrand brand;

        for(int i = 0; i < this.retaileri.length ; ++i)
            if(publishingRetailerID == this.retaileri[i].getID()) // cautam retailer-ul dupa ID
            {
                indiceRetailer = i;
                break;
            }

        Book[] cartiDeLaRetailer = new Book[500];

        for(int i = 0; i < this.retaileri[indiceRetailer].getNumberOfArtifacts() ; ++i) // parcurgem toate artefactele
        {
            if(this.retaileri[indiceRetailer].getArtifacts()[i] instanceof Book) // e de tip carte
            {
                carte = (Book)this.retaileri[indiceRetailer].getArtifacts()[i];
                if(IDs[carte.getID()] == 0) // verificam daca a fost gasita deja
                {
                    IDs[carte.getID()] = 1;
                    cartiDeLaRetailer[numberOfBooks] = carte;
                    ++numberOfBooks;
                }
            }
            else if(this.retaileri[indiceRetailer].getArtifacts()[i] instanceof EditorialGroup) // grup editorial
            {
                grupEditorial = (EditorialGroup)this.retaileri[indiceRetailer].getArtifacts()[i];

                for(int j = 0; j < grupEditorial.getNumberOfBooks(); ++j) // parcurgem cartile din grupul editorial
                {
                    carte = grupEditorial.getBooks()[j];
                    if(IDs[carte.getID()] == 0) // daca nu a fost deja gasita
                    {
                        IDs[carte.getID()] = 1;
                        cartiDeLaRetailer[numberOfBooks] = carte;
                        ++numberOfBooks;
                    }
                }
            }
            else if(this.retaileri[indiceRetailer].getArtifacts()[i] instanceof PublishingBrand) // e brand
            {
                brand = (PublishingBrand)this.retaileri[indiceRetailer].getArtifacts()[i];

                for(int j = 0; j < brand.getNumberOfBooks(); ++j) // parcurgem cartile din brand
                {
                    carte = brand.getBooks()[j];
                    if(IDs[carte.getID()] == 0) // daca nu a fost gasita deja
                    {
                        IDs[carte.getID()] = 1;
                        cartiDeLaRetailer[numberOfBooks] = carte;
                        ++numberOfBooks;
                    }
                }
            }
        }
        return cartiDeLaRetailer;
    }

    Language[] getLanguagesForPublishingRetailerID (int publishingRetailerID)
    {
        Language[] limbiDeLaRetailer = new Language[500];
        int numberOfLanguages = 0;

        Book[] cartiDeLaRetailer = this.getBooksForPublishingRetailerID(publishingRetailerID); // cartile de la retailer
        int[] IDs = new int[50000];

        for(int i = 0; cartiDeLaRetailer[i] != null; ++i)
        {
            if(IDs[cartiDeLaRetailer[i].getLanguageID()] == 0) // daca nu am gasit deja limba
            {
                IDs[cartiDeLaRetailer[i].getLanguageID()] = 1;

                for (Language language : this.limbi) // cautam obiectul limba dupa ID
                    if (language.getID() == cartiDeLaRetailer[i].getLanguageID()) {
                        limbiDeLaRetailer[numberOfLanguages] = language;
                        break;
                    }
                ++numberOfLanguages;
            }
        }
        return limbiDeLaRetailer;
    }

    Country[] getCountriesForBookID (int bookID)
    {
        int[] IDs = new int[50000];
        int numberOfCountries = 0;

        Country[] tariDeLaCarte = new Country[500];

        for (PublishingRetailer publishingRetailer : this.retaileri) // iteram prin retaileri
        {
            Book[] cartiDeLaRetailer = this.getBooksForPublishingRetailerID(publishingRetailer.getID()); // cartile

            for (int j = 0; cartiDeLaRetailer[j] != null; ++j)
                if (cartiDeLaRetailer[j].getID() == bookID) // cartea apartine retailerului
                {
                    for (int k = 0; k < publishingRetailer.getNumberOfCountries(); ++k) // parcurgem tarile retailerului
                        if (IDs[publishingRetailer.getCountries()[k].getID()] == 0) // nu a fost deja gasita
                        {
                            IDs[publishingRetailer.getCountries()[k].getID()] = 1;
                            tariDeLaCarte[numberOfCountries] = publishingRetailer.getCountries()[k];
                            ++numberOfCountries;
                        }
                    break; // deja am gasit cartea, lista are carti unice
                }
        }
        return tariDeLaCarte;
    }

    Book[] getCommonBooksForRetailerIDs(int retailerID1, int retailerID2)
    {
        Book[] cartiDeLaRetailer1, cartiDeLaRetailer2, cartiComune = new Book[500];
        int[] IDs = new int[50000];
        int numberOfBooks = 0;

        cartiDeLaRetailer1 = this.getBooksForPublishingRetailerID(retailerID1); // cartile primului retailer
        cartiDeLaRetailer2 = this.getBooksForPublishingRetailerID(retailerID2); // cartile celui de-al doilea retailer

        for (int i = 0; cartiDeLaRetailer1[i] != null; ++i)
            IDs[cartiDeLaRetailer1[i].getID()] = 1; // marcam cartile din retailer1 dupa ID

        for(int i = 0; cartiDeLaRetailer2[i] != null; ++i)
            if(IDs[cartiDeLaRetailer2[i].getID()] == 1) // daca ID-ul e deja marcat, inseamna ca avem carte comuna
            {
                cartiComune[numberOfBooks] = cartiDeLaRetailer2[i];
                ++numberOfBooks;
            }

        return cartiComune;
    }

    Book[] getAllBooksForRetailerIDs(int retailerID1, int retailerID2)
    {
        Book[] cartiDeLaRetailer1, cartiDeLaRetailer2, cartiToate = new Book[500];
        int[] IDs = new int[50000];
        int numberOfBooks = 0;

        cartiDeLaRetailer1 = this.getBooksForPublishingRetailerID(retailerID1); // cartile primului retailer
        cartiDeLaRetailer2 = this.getBooksForPublishingRetailerID(retailerID2); // cartile celui de-al doilea retailer

        for (int i = 0; cartiDeLaRetailer1[i] != null; ++i)
        {
            IDs[cartiDeLaRetailer1[i].getID()] = 1; // marcam cartile din retailer1 dupa ID
            cartiToate[numberOfBooks] = cartiDeLaRetailer1[i]; // le adaugam
            ++numberOfBooks;
        }

        for(int i = 0; cartiDeLaRetailer2[i] != null; ++i)
            if(IDs[cartiDeLaRetailer2[i].getID()] == 0) // cartea din retailer2 nu a fost marcata, deci o adaugam
            {
                IDs[cartiDeLaRetailer2[i].getID()] = 1;
                cartiToate[numberOfBooks] = cartiDeLaRetailer2[i];
                ++numberOfBooks;
            }

        return cartiToate;
    }
}
