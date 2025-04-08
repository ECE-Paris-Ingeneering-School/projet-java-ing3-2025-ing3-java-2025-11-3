package Dao;

import MODELE.Admin;

import java.util.List;


public interface AdminDao {
    void createAdmin(Admin admin);
    List<Integer> getAllAdmins();

}
