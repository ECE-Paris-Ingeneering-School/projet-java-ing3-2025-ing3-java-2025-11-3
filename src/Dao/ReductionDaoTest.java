package Dao;

import MODELE.Reduction;
import db.AzureDBConnector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ReductionDaoTest {

    @BeforeEach
    void setUp() {
        // Initialize the connection to the database
        AzureDBConnector connector = AzureDBConnector.getInstance();
        // Connection connection = connector.getConnection();
        // ReductionDao reductionDao = new ReductionDaoImpl(connector);
    }

    @Test
    void testajout() {
        ReductionDao reductionDao = new ReductionDaoImpl(new AzureDBConnector());
        Reduction reduction = new Reduction("testCode", 10);
        reductionDao.addReduction(reduction);
        int id = reductionDao.getreductionId(reduction);
        Reduction retrievedReduction = reductionDao.getReductionById(id);
        assert retrievedReduction != null;
        assert retrievedReduction.getCodePromo().equals("testCode");
        assert retrievedReduction.getPourcentage() == 10;
        reductionDao.deleteReduction(id);
    }
    @Test
    void testverificode(){ReductionDao reductionDao = new ReductionDaoImpl(new AzureDBConnector());
        Reduction reduction = new Reduction("testCode", 10);
        reductionDao.addReduction(reduction);
        int id = reductionDao.getreductionId(reduction);
        boolean isValid = reductionDao.checkReductionCode("testCode");
        assert isValid;
        boolean isinValid = reductionDao.checkReductionCode("testCodex");
        assert !isinValid;
        boolean isvalid = reductionDao.checkReductionCode("tesr");
        assert !isvalid;
        reductionDao.deleteReduction(id);

    }
}
