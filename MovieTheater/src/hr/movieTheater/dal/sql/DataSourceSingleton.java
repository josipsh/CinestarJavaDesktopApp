/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.movieTheater.dal.sql;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import javax.sql.DataSource;


/**
 *
 * @author Josip Sadek
 */
public class DataSourceSingleton {
    
    private static String SERVER_NAME = "localhost";
    private static String DATABASE_NAME = "MovieTheater";
    private static String USER = "sa";
    private static String PASSWORD = "SQL";
    
    private static DataSource instance;

    private DataSourceSingleton() {}
    
    public static DataSource getInstance(){
        if (instance == null) {
            instance = createInstance();
        }
        return instance;
    }

    private static DataSource createInstance() {
        
        SQLServerDataSource dataSource = new SQLServerDataSource();
        
        dataSource.setServerName(SERVER_NAME);
        dataSource.setDatabaseName(DATABASE_NAME);
        dataSource.setUser(USER);
        dataSource.setPassword(PASSWORD);
        
        return dataSource;
    }

    
    
}
