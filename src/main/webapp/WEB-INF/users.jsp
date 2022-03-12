<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Users</title>
        
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
        
    </head>
    <body>
        <div class="bg-light d-flex justify-content-between mt-3">
        <form action="user" method="post">
            <input type="hidden" name="action" value="add">
            
            <h1 style="text-align: center">Add User</h1>
            <table>
                <tr>
                    <td>
                        <label for="Email">Email</label>
                    </td>
                    <td>
                        <input type="text" name="Email" id="Email">
                    </td>
                </tr>
            
                <tr>
                    <td>
                        <label for="fname">First Name</label>
                    </td>
                    <td>
                        <input type="text" name="fname" id="fname">
                    </td>
                </tr>
                
                <tr>
                    <td>
                        <label for="lname">Last Name</label>
                    </td>
                    <td>
                        <input type="text" name="lname" id="lname">
                    </td>
                </tr>
            
                <tr>
                    <td>
                        <label for="role">Role</label>
                    </td>
                    <td>
                        <select name="role" id="role">
                            <c:forEach var="role" items="${roles}">
                                <option value="${role.name}">${role.name}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
            
                <tr>
                    <td>
                        <label for="passwd">Password</label>
                    </td>
                    <td>
                        <input type="password" name="passwd" id="passwd">
                    </td>
                </tr>
            
                <tr>
                    <td>
                        <button type="submit">Add User</button>
                    </td>
                </tr>
            </table>
        </form>
        
            <div class="justify-content-between">
        <div class="container">
            <div class="row">
                <div class="col">
                    <h1 style="text-align: center">Manage Users</h1>
        
                    <table class="table">
                        <thead>
                            <tr>
                                <th>E-mail</th>
                                <th>First Name</th>
                                <th>Last Name</th>
                                <th>Active</th>
                                <th>Delete</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="user" items="${users}">
                                <tr>
                                    <td>${user.email}</td>
                                    <td>${user.firstName}</td>
                                    <td>${user.lastName}</td>
                                    <td>${user.active ? "Y" : "N"}</td>
                                    <td>
                                        <a href="user?action=delete&email=${user.email}">Delete</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        </div>
            
            <div class="pull-right">
        <form action="user" method="post">
            <input type="hidden" name="action" value="edit">
            
            <h1 style="text-align: center">Edit User</h1>
            <table>
                <tr>
                    <td>
                        <label for="Emailedit">Email</label>
                    </td>
                    <td>
                        <input type="text" name="Emailedit" id="Emailedit">
                    </td>
                </tr>
                
                <tr>
                    <td>
                        <label for="fnameedit">First Name</label>
                    </td>
                    <td>
                        <input type="text" name="fnameedit" id="fnameedit">
                    </td>
                </tr>
                
                <tr>
                    <td>
                        <label for="lnameedit">Last Name</label>
                    </td>
                    <td>
                        <input type="text" name="lnameedit" id="lnameedit">
                    </td>
                </tr>
            
                <tr>
                    <td>
                        <label for="roleedit">Role</label>
                    </td>
                    <td>
                        <select name="roleedit">
                            <c:forEach var="role" items="${roles}">
                                <option value="${role.name}">${role.name}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
           
                <tr>
                    <td>
                        <label for="activeStatusedit">Active</label>
                    </td>
                    <td>
                        <input type="number" id="activeStatusedit" name="activeStatusedit" min="0" max="1">
                    </td>
                </tr>
            
                <tr>
                    <td>
                        <label for="passwdedit">Password</label>
                    </td>
                    <td>
                        <input type="password" name="passwdedit" id="passwdedit">
                    </td>
                </tr>
            
                <tr>
                    <td>
                        <button type="submit">Edit User</button>
                    </td>
                </tr>
            </table>
        </form>
        </div>
        </div>
       <p>${error}</p>
    </body>
</html>
