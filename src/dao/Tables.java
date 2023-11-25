/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.Statement;
/**
 *
 * @author 91727
 */
public class Tables {
    public static void main(String[] args){
        try{
            Connection con = ConnectionProvider.getCon();
            Statement st = con.createStatement();
            st.executeUpdate("create table if not exist appuser(appuser_pk int AUTO_INCREMENT primary key, userRole varchar(256), name varchar(256), dob varchar(50), mobileNumber varchar(50), email varchar(256), username varchar(256), password varchar(50), address varchar(256) )");
            st.executeUpdate("insert into appuser(userRole,name,dob,mobileNumber,email,username,password,address) values('Admin','Admin','15-03-2003','7276464726','kaneria.1@iitj.ac.in','admin','admin','Nanded,India' )");
            st.executeUpdate("CREATE TABLE IF NOT EXISTS medicine (medicine_pk INT AUTO_INCREMENT, pharmacist_username VARCHAR(256), uniqueId VARCHAR(256),name VARCHAR(256), companyName VARCHAR(256),quantity BIGINT,price BIGINT, PRIMARY KEY (medicine_pk, pharmacist_username));");
            JOptionPane.showMessageDialog(null,"Table Created Successfully");
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }
}
