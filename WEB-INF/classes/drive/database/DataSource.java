package drive.database;

import org.apache.commons.dbcp2.BasicDataSource;

public class DataSource {
    private static BasicDataSource dataSource;
    private static final String DRIVERCLASS = "com.mysql.cj.jdbc.Driver";
    private static final String CONNECTIONURL = "jdbc:mysql://localhost:3306/GoogleDrive";
    private static final String DBUSER = "root";
    private static final String DBPASSWORD = "root";

    static {
        dataSource = new BasicDataSource();

        dataSource.setDriverClassName( DRIVERCLASS );
        dataSource.setUrl( CONNECTIONURL );
        dataSource.setUsername( DBUSER );
        dataSource.setPassword( DBPASSWORD );

        // Parameters for connection pooling
        dataSource.setInitialSize(10);
        dataSource.setMaxTotal(10);	  
    }

    public static BasicDataSource getDataSource()
    {
        return dataSource;
    }

}