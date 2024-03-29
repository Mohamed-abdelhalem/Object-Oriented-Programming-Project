/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package league;

import attributes.stadium;
import attributes.team;
import static league.team_database.connect;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JTable;
import net.proteanit.sql.DbUtils;


public class stadium_database {
     public static Connection connect() throws SQLException {
    
       return DriverManager.getConnection("jdbc:sqlite:EGYPTION_LEAGUE_MANGMENT_SYSTEM.db");
    }
    public static void insert_stadium(String name,String location,int seat_capicty,String name_of_team) throws SQLException
{
try (
            Connection con = connect();
            PreparedStatement p = con.prepareStatement("insert into STADIUM values(?,?,?,?)");){
            
          
            p.setString(1, name);
             p.setString(2, location);
            p.setInt(3,seat_capicty);
            p.setString(4, name_of_team);
            p.execute();
        } catch (SQLException ee) {
            System.out.println(ee.getMessage());
        }
     
    }
     public static void update_stadium(String name,String location,int seat_capicty,String name_of_team) throws SQLException {
        try (
            Connection con = connect();
            PreparedStatement p = con.prepareStatement("update STADIUM set LOCATION=?,SEAT_CAPICTY=?,NAME_OF_TEAM=? where NAME =?");){
           
             p.setString(1, location);
            p.setInt(2,seat_capicty);
            p.setString(3, name_of_team);
             p.setString(4, name);
          
            p.executeUpdate();
        } catch (SQLException ee) {
            System.out.println(ee.getMessage());
        }
    }
     public static void delete_stadium(String name) throws SQLException {
        try (
            Connection con = connect();
            PreparedStatement p = con.prepareStatement("DELETE FROM STADIUM where NAME =?");){
              p.setString(1,name);
            p.executeUpdate();
        } catch (SQLException ee) {
            System.out.println(ee.getMessage());
        }
    }
     public static void refresh_table(JTable table) throws SQLException
     {
      try (
             Connection con = connect();
            PreparedStatement p = con.prepareStatement("select*from STADIUM");){
            ResultSet r = p.executeQuery();
            table.setModel(DbUtils.resultSetToTableModel(r));
      }catch (SQLException EE) {
            System.out.println(EE.getMessage());
        }
}
      public static ArrayList<stadium> get_stadium() {
        ArrayList<stadium> list = new ArrayList<>();
        try (
            Connection con = connect();
            PreparedStatement p = con.prepareStatement("select*from STADIUM");){
            ResultSet r = p.executeQuery();
            while (r.next()) {
                list.add(new stadium(r.getString("NAME"),r.getString("LOCATION"),r.getInt("SEAT_CAPICTY"),r.getString("NAME_OF_TEAM")));
            }

        } catch (SQLException EE) {
            System.out.println(EE.getMessage());
        }
        return list;
    }
       public static void search_table(JTable table,String NAME) throws SQLException
     {
      try (
             Connection con = connect();
            PreparedStatement p = con.prepareStatement("select*from STADIUM where NAME_OF_TEAM=?");){
           p.setString(1,NAME );
            ResultSet r = p.executeQuery();
            table.setModel(DbUtils.resultSetToTableModel(r));
      }catch (SQLException EE) {
            System.out.println(EE.getMessage());
        }
     
     
     
}
}
