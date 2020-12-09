/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package silab.nst.dan9.dataaccess.repository;

import silab.nst.dan9.dataaccess.domain.User;
import silab.nst.dan9.dataaccess.repository.helpers.JDBCHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author laptop-02
 */
public class UserRepository {

    public static final String INSERT_SQL_QUERY = "INSERT INTO USER(ID,FIRST_NAME,LAST_NAME,USERNAME,PASSWORD) VALUES(?,?,?,?,?)";
    public static final String UPDATE_SQL_QUERY = "UPDATE USER SET FIRST_NAME=?, LAST_NAME=?, USERNAME=?, PASSWORD=? WHERE ID=?";
    public static final String DELETE_SQL_QUERY = "DELETE FROM USER WHERE ID=?";
    public static final String SELECT_ALL_SQL_QUERY = "SELECT ID,FIRST_NAME,LAST_NAME,USERNAME,PASSWORD FROM USER";

    public User add(User user) throws Exception {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = JDBCHelper.getConnection();
            if (con == null) {
                throw new SQLException("Error getting the connection. Please check if the DB server is running");
            }
            con.setAutoCommit(false);
            ps = con.prepareStatement(INSERT_SQL_QUERY);
            ps.setLong(1, user.getId());
            ps.setString(2, user.getFirstname());
            ps.setString(3, user.getLastname());
            ps.setString(4, user.getUsername());
            ps.setString(5, user.getPassword());

            ps.execute();
            System.out.println("insertUser => " + ps.toString());
            con.commit();

        } catch (SQLException e) {
            try {
                if (con != null) {
                    con.rollback();
                }
            } catch (SQLException e1) {
                throw e1;
            }
            throw e;
        } finally {
            try {
                JDBCHelper.closePrepaerdStatement(ps);
                JDBCHelper.closeConnection(con);
            } catch (SQLException e) {
                throw e;
            }
        }
        return user;
    }


    public void delete(User user) throws Exception {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = JDBCHelper.getConnection();
            ps = con.prepareStatement(DELETE_SQL_QUERY);
            ps.setLong(1, user.getId());
            ps.execute();
            System.out.println("deleteUser => " + ps.toString());
        } catch (SQLException e) {
            throw e;
        } finally {
            try {
                JDBCHelper.closePrepaerdStatement(ps);
                JDBCHelper.closeConnection(con);
            } catch (SQLException e) {
                throw e;
            }
        }

    }

    public User update(User user) throws Exception {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = JDBCHelper.getConnection();
            if (con == null) {
                throw new SQLException("Error getting the connection. Please check if the DB server is running");
            }
            con.setAutoCommit(false);
            ps = con.prepareStatement(UPDATE_SQL_QUERY);
            ps.setString(1, user.getFirstname());
            ps.setString(2, user.getLastname());
            ps.setString(3, user.getUsername());
            ps.setString(4, user.getPassword());
            ps.setLong(5, user.getId());
            ps.execute();
            System.out.println("updateUser => " + ps.toString());
            con.commit();

        } catch (SQLException e) {
            try {
                if (con != null) {
                    con.rollback();
                    throw e;
                }
            } catch (SQLException e1) {
                throw e1;
            }
        } finally {
            try {
                JDBCHelper.closePrepaerdStatement(ps);
                JDBCHelper.closeConnection(con);
            } catch (SQLException e) {
                throw e;
            }
        }

        return user;
    }

    public List<User> getAll() throws Exception {
        {
            Connection con = null;
            PreparedStatement ps = null;
            ResultSet rs = null;
            List<User> users = new ArrayList<>();
            try {
                con = JDBCHelper.getConnection();
                if (con == null) {
                    throw new SQLException("Error getting the connection. Please check if the DB server is running");
                }
                ps = con.prepareStatement(SELECT_ALL_SQL_QUERY);
                rs = ps.executeQuery();
                System.out.println("retriveUsers => " + ps.toString());
                while (rs.next()) {
                    User u = new User();
                    u.setId(rs.getLong("ID"));
                    u.setFirstname(rs.getString("FIRST_NAME"));
                    u.setLastname(rs.getString("LAST_NAME"));
                    u.setUsername(rs.getString("USERNAME"));
                    u.setPassword(rs.getString("PASSWORD"));
                    users.add(u);

                }

            } catch (SQLException e) {
                throw e;

            } finally {
                try {
                    JDBCHelper.closeResultSet(rs);
                    JDBCHelper.closePrepaerdStatement(ps);
                    JDBCHelper.closeConnection(con);
                } catch (SQLException e) {
                    throw e;
                }
            }
            return users;
        }
    }
}
