package ca.sait.lab6.dataaccess;

import ca.sait.lab6.models.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RoleDB {

    public List<Role> getAll() throws Exception {
        List<Role> roles = new ArrayList<>();
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String sql = "SELECT * FROM role";
        
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            //rs = con.createStatement().executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                
                Role role = new Role(id, name);
                
                roles.add(role);
            }
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }

        return roles;
    }

    public int getRoleId(String roleName) throws Exception{
        
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        int id = 0;
        
        String sql = "SELECT role_id FROM role WHERE role_name=?;";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, roleName);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                id = rs.getInt(1);
            }
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
        
        return id;
    }
}
