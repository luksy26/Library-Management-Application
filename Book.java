package com.company;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class Book implements IPublishingArtifact
{
    private int ID, pageCount, languageID, numberOfAuthors = 0;
    private String name, subtitle, keywords;
    public String ISBN;
    private LocalDateTime createdOn;
    private final Author[] authors = new Author[100];

    int getID() { return this.ID; }
    void setID(int ID) { this.ID = ID; }

    String getName() { return this.name; }
    void setName(String name) { this.name = name; }

    String getSubtitle() { return this.subtitle; }
    void setSubtitle(String subtitle) { this.subtitle = subtitle; }

    String getKeywords() { return this.keywords; }
    void setKeywords(String keywords) { this.keywords = keywords; }

    String getCreatedOn()
    {
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"); // formatam dateTime
        return this.createdOn.format(myFormatObj);
    }
    void setCreatedOn(LocalDateTime createdOn) { this.createdOn = createdOn; }

    int getPageCount() { return this.pageCount; }
    void setPageCount(int pageCount) { this.pageCount = pageCount; }

    int getLanguageID() { return this.languageID; }
    void setLanguageID(int languageID) { this.languageID = languageID; }

    Author[] getAuthors() { return this.authors; }
    void addAuthor(Author autor){ this.authors[this.numberOfAuthors++] = autor; }
    int getNumberOfAuthors() { return this.numberOfAuthors; }

    public String Publish()
    {
        StringBuilder s = new StringBuilder();
        s.append("<xml>\n\t").append("<title>").append(this.name).append("</title>\n");
        s.append("\t<subtitle>").append(this.subtitle).append("</subtitle>\n");
        s.append("\t<isbn>").append(this.ISBN).append("</isbn>\n");
        s.append("\t<pageCount>").append(this.pageCount).append("</pageCount>\n");
        s.append("\t<keywords>").append(this.keywords).append("</keywords>\n");
        s.append("\t<languageID>").append(this.languageID).append("</languageID>\n");
        s.append("\t<createdOn>").append(this.getCreatedOn()).append("<createdOn>\n");

        s.append("\t<authors>");
        for(int i = 0; i < this.numberOfAuthors - 1; ++i) // parcurgem autorii
            s.append(this.authors[i].getName()).append(" ");
        s.append(this.authors[this.numberOfAuthors - 1].getName());

        s.append("<authors>\n</xml>");
        return s.toString();
    }
}
