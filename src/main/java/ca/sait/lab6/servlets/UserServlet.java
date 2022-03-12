package ca.sait.lab6.servlets;

import ca.sait.lab6.models.Role;
import ca.sait.lab6.models.User;
import ca.sait.lab6.services.RoleService;
import ca.sait.lab6.services.UserService;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Shiana Khehra
 */
public class UserServlet extends HttpServlet {
    
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserService userservice = new UserService();
        RoleService roleservice = new RoleService();
        List<User> users = null;
        List<Role> roles = null;
        
        try {
            users = userservice.getAll();
            request.setAttribute("users", users);
            roles = roleservice.getAll();
            request.setAttribute("roles", roles);
            
        } catch (Exception ex) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String action = request.getParameter("action");
        
         if (action != null && action.equals("delete")) {
            
            try {
                String email = request.getParameter("email").replace(" ","+");
                userservice.delete(email);
                users = userservice.getAll();
                request.setAttribute("users", users);
                
            } catch (Exception ex) {
                Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
        this.getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserService userservice = new UserService();
        RoleService roleservice = new RoleService();
        List<Role> roles = null;
        List<User> users = null;
                
        try {
            users = userservice.getAll();
            roles = roleservice.getAll();
            request.setAttribute("roles", roles);
        } catch (Exception ex) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String action = request.getParameter("action");
        
        if (action != null && action.equals("add")) {
            try {
                String email = request.getParameter("Email");
            
                String firstname = request.getParameter("fname");
            
                String lastname = request.getParameter("lname");
            
                String roleName = request.getParameter("role");
                int roleId = roleservice.getRoleID(roleName);
                Role role = new Role(roleId, roleName);
                
                String password = request.getParameter("passwd");
            
                userservice.insert(email, true, firstname, lastname, password, role);
                users = userservice.getAll();
                request.setAttribute("users", users);
            } catch (Exception ex) {
                Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if (action != null && action.equals("edit")) {
            try {
                String email = request.getParameter("Emailedit");
            
                String firstname = request.getParameter("fnameedit");
            
                String lastname = request.getParameter("lnameedit");
            
                String roleName = request.getParameter("roleedit");
                int roleId = roleservice.getRoleID(roleName);
                Role role = new Role(roleId, roleName);
                
                boolean status = false;
                String active = request.getParameter("activeStatusedit");
                if(active.equals("1")) {
                    status = true;
                }
                
                String password = request.getParameter("passwdedit");
            
                userservice.update(email, status, firstname, lastname, password, role);
                users = userservice.getAll();
                request.setAttribute("users", users);
            } catch (Exception ex) {
                request.setAttribute("error", ex.getMessage());
            }
        }
        
        this.getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
    }

}
