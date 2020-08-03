package Model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class KaufHistorie
{
    public ArrayList<Transaktion> kaufHistorie = new ArrayList<>();

    public KaufHistorie(File file) throws IOException
    {
        this.kaufHistorie = LesenCSVKaufHistorie.lesenCSVtoTransaktionenListe(file);
    }
}
