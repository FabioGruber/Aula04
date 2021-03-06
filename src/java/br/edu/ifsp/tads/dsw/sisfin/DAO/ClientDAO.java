/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsp.tads.dsw.sisfin.DAO;

import br.edu.ifsp.tads.dsw.sisfin.model.Client;
import br.edu.ifsp.tads.dsw.sisfin.servlet.AddClient;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Aluno
 */
public class ClientDAO {
    private String sql;
    private Connection conn;
    
    public ClientDAO(){
        this.conn = new ConnectionFactory().getConnection();
    }
    public boolean add(Client cli) {
        this.sql = "insert into CLIENT (name,email) values (?,?)";
        try(PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1,cli.getName());
            stmt.setString(2,cli.getEmail());
            stmt.execute();
            conn.commit();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(AddClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public List<Client> getList() {
        List<Client> clients = new ArrayList();
        this.sql = "SELECT * FROM CLIENT";
        try(PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Client cl = new Client(rs.getString("name"),rs.getString("email"));
                clients.add(cl);
            }
        } catch (SQLException ex) { 
            Logger.getLogger(ClientDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return clients;
    }
}
