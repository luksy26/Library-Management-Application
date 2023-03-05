package com.company;

class EditorialGroup implements IPublishingArtifact
{
    private int ID, numberOfBooks = 0;
    private String name;
    private final Book[] books = new Book[100];

    int getID() { return ID; }
    void setID(int ID) { this.ID = ID; }

    String getName() { return name; }
    void setName(String name) { this.name = name; }

    Book[] getBooks() { return this.books; }
    void addBook(Book carte) { this.books[this.numberOfBooks++] = carte; }
    int getNumberOfBooks () { return this.numberOfBooks; }

    public String Publish()
    {
        StringBuilder s = new StringBuilder();

        s.append("<xml>\n\n");
        s.append("\t<editorialGroup>\n");
        s.append("\t\t<ID>").append(this.ID).append("</ID>\n");
        s.append("\t\t<Name>").append(this.name).append("</Name>\n");
        s.append("\t</editorialGroup>\n");
        s.append("\t<books>\n");

        for(int i = 0; i < this.numberOfBooks; ++i) // parcurgem cartile
        {
            s.append("\t\t<book>\n");

            s.append("\t\t\t<title>").append(this.books[i].getName()).append("</title>\n");
            s.append("\t\t\t<subtitle>").append(this.books[i].getSubtitle()).append("</subtitle>\n");
            s.append("\t\t\t<isbn>").append(this.books[i].ISBN).append("</isbn>\n");
            s.append("\t\t\t<pageCount>").append(this.books[i].getPageCount()).append("</pageCount>\n");
            s.append("\t\t\t<keywords>").append(this.books[i].getKeywords()).append("</keywords>\n");
            s.append("\t\t\t<languageID>").append(this.books[i].getLanguageID()).append("</languageID>\n");
            s.append("\t\t\t<createdOn>").append(this.books[i].getCreatedOn()).append("<createdOn>\n");

            s.append("\t\t\t<authors>");
            for(int j = 0; j < this.books[i].getNumberOfAuthors() - 1 ; ++j) // parcurgem autorii
                s.append(this.books[i].getAuthors()[j].getName()).append(" ");
            s.append(this.books[i].getAuthors()[this.books[i].getNumberOfAuthors() - 1].getName());
            s.append("<authors>\n");

            s.append("\t\t</book>\n");
        }
        s.append("\t</books>\n</xml>");
        return s.toString();
    }
}
