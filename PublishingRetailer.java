package com.company;

class PublishingRetailer
{
    private int ID, numberOfCountries = 0, numberOfArtifacts = 0;
    private String name;
    private final Country[] countries = new Country[100];
    IPublishingArtifact[] publishingArtifacts = new IPublishingArtifact[500];

    int getID() { return this.ID; }
    void setID(int ID) { this.ID = ID; }

    String getName() { return this.name; }
    void setName(String name) { this.name = name; }

    Country[] getCountries() { return this.countries; }
    void addCountry( Country tara) { this.countries[this.numberOfCountries++] = tara; }
    int getNumberOfCountries() { return this.numberOfCountries; }

    IPublishingArtifact[] getArtifacts() { return this.publishingArtifacts; }
    void addArtifact( IPublishingArtifact artefact) { this.publishingArtifacts[this.numberOfArtifacts++] = artefact; }
    int getNumberOfArtifacts() { return this.numberOfArtifacts; }
}
