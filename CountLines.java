package com.company;

import java.io.*;

class CountLines // clasa definita sa numere liniile dintr-un fisier
{
    public int HowMany(String fileName) // method we're interested in
    {
        File file = new File(fileName);
        int nr = 0;
        try(BufferedReader br = new BufferedReader(new FileReader(file)))
        {
            while (br.readLine() != null)
                ++nr;
        }
        catch(FileNotFoundException ex2) { System.out.println("Nu am gasit fisierul"); }
        catch(IOException ex1) { System.out.println("exceptie de intrare"); }
        return nr;
    }
}
