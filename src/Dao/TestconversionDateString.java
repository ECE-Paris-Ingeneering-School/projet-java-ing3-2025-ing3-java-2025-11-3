package Dao;

import db.AzureDBConnector;
import org.junit.jupiter.api.Test;

public class TestconversionDateString {
@Test
        void conv () {
        ReservationDaoImpl reservationDao = new ReservationDaoImpl(new AzureDBConnector());
        String dateString = "2023-10-01";
        java.sql.Date date = reservationDao.convertStringToDate(dateString);
        System.out.println("Converted Date: " + date);
        assert date != null;
        // Assuming the date format is "yyyy-MM-dd"
        System.out.println( date.getMonth() + " " + date.getYear());

        String convertedString = reservationDao.convertDateToString(date);
        System.out.println("Converted String: " + convertedString);
    }
}
