package com.danh.DAL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.danh.model.BaseModel;

public abstract class BaseDAO<T extends BaseModel> {
	protected Connection connection;
    private String HOST ="localhost";
    private String GATE ="1433";
    private String DATABASE_NAME ="HOTEL_MANAGEMENT";
    public BaseDAO()
    {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String user = "sa";
            String pass = "123456";
            String url = "jdbc:sqlserver://"+HOST+":"+GATE+";databaseName="+DATABASE_NAME+";useUnicode=true&characterEncoding=utf-8&useSSL=false";
            connection = DriverManager.getConnection(url,user,pass);
        } catch (Exception ex) {
            Logger.getLogger(BaseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public abstract ArrayList<T> all();
    public abstract T get(int id);
    public abstract void insert(T model);
}
