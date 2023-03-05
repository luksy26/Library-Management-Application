package com.company;

class Author
{
    private int ID;
    private String firstName = "", lastName = "";

    String getName() // returneaza un string cu numele complet
    {
        if(this.firstName.length() > 0)
            return this.firstName + " " + this.lastName;
        return this.lastName;
    }
    void setFirstName(String firstName) { this.firstName = firstName; }
    void setLastName(String lastName) { this.lastName = lastName; }

    int getID() { return this.ID; }
    void setID(int ID) { this.ID = ID; }
}
