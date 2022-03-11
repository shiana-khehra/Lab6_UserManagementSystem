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
                String email = request.getParameter("email").replaceAll(" ", "+");
                userservice.delete(email);
                
            } catch (Exception ex) {
                Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.setAttribute("users", users);
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
        String action = request.getParameter("action");
        boolean active =false;
        
        String firstname = request.getParameter("fname");
        String lastname = request.getParameter("lname");
        String activeStatus = request.getParameter("activeStatus");
        if(activeStatus != null) {
            active = true;
        }
        String password = request.getParameter("passwd");
        List<Role> roles = null;
        List<User> users = null;
        String email = null;
        try {
            roles = roleservice.getAll();
        } catch (Exception ex) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        String roleName = request.getParameter("role");
        int roleId = 0;
        for(int i=0; i<roles.size(); i++) {
            if(roles.get(i).getName() == roleName) {
                roleId = roles.get(i).getId();
            }
        }
        Role role = new Role(roleId, roleName);
        if (action != null && action.equals("add")) {
            try {
                email = request.getParameter("email");
                userservice.insert(email, active, firstname, lastname, password, role);
                users = userservice.getAll();
            } catch (Exception ex) {
                Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.setAttribute("users", users);
        } else if (action != null && action.equals("edit")) {
            try {
                users = userservice.getAll();
                for(int i=0; i<users.size(); i++) {
                    if(users.get(i).getFirstName() == firstname && users.get(i).getLastName() == lastname) {
                    email = users.get(i).getEmail();
            }
        }
                userservice.update(email, active, firstname, lastname, password, role);
                users = userservice.getAll();
            } catch (Exception ex) {
                Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.setAttribute("users", users);
        }
        
        this.getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
    }

}
