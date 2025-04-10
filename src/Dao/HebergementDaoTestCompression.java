package Dao;


import db.AzureDBConnector;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class HebergementDaoTestCompression {

    @Test
    public void testcompressionListe() {
        HebergementDaoImpl hebergementDao = new HebergementDaoImpl(new AzureDBConnector());
        ArrayList<String> list = new ArrayList<>();
        list.add("image1.jpg");
        list.add("image2.jpg");
        String compressed = hebergementDao.compreserListe(list);
        System.out.println(compressed);
        assertEquals(compressed, "image1.jpg,image2.jpg");

    }
    @Test
    public void testdecompression() {
        HebergementDaoImpl hebergementDao = new HebergementDaoImpl(new AzureDBConnector());
        String compressed = "image1.jpg,image2.jpg";
        ArrayList<String> decompressed = hebergementDao.decompreserListe(compressed);
        System.out.println(decompressed);
        assertEquals(decompressed.get(0), "image1.jpg");
        assertEquals(decompressed.get(1), "image2.jpg");
    }
}
