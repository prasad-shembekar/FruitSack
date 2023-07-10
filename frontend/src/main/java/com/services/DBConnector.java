/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.services;


 
 
import java.sql.*;
 
 
 
public class DBConnector {
    
 public Connection con;
    public DBConnector() 
    {
    }
    
    public Connection connect()
    {
    
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
         
           //con=DriverManager.getConnection("jdbc:mysql://localhost/fruitQualityPredDb?user=root&&password=crosspolo");
           con=DriverManager.getConnection("jdbc:mysql://ucgxw7r1kroqfofr:j6vXyV03OPORtZJaxVWj@bhgst1pbhtweryaf9yje-mysql.services.clever-cloud.com:3306/bhgst1pbhtweryaf9yje");
           
            System.out.println("Connected..");
        }
        catch(Exception e)
        {
            System.out.println("Error in connection : "+e.getMessage());
        }
        
    return con;
    }
    
    
    
}

