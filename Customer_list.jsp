<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>User Management Application</title>
</head>
<body>
	<center>
		<h1>Customer Management</h1>
        <h2>
        	<a href="new">Add New Customer</a>
        	&nbsp;&nbsp;&nbsp;
        	<a href="list">List All Customers</a>

        </h2>
	</center>
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>List of Customers</h2></caption>
            <tr>
                <th>ID</th>
                <th>FirstName</th>
                 <th>LastName</th>
                 <th>Address</th>
                 <th>City</th>
                <th>Email</th>
                <th>Phone</th>
                <th>Actions</th>
            </tr>
            <c:forEach var="user" items="${listCustomer}">
                <tr>
                    <td><c:out value="${customer.id}" /></td>
                    <td><c:out value="${customer.firstname}" /></td>
                    <td><c:out value="${customer.lastname}" /></td>
                    <td><c:out value="${customer.address}" /></td>
                    <td><c:out value="${customer.city}" /></td>
                    <td><c:out value="${customer.email}" /></td>
                    <td><c:out value="${customer.phone}" /></td>
                    
                    <td>
                    	<a href="edit?id=<c:out value='${customer.id}' />">Edit</a>
                    	&nbsp;&nbsp;&nbsp;&nbsp;
                    	<a href="delete?id=<c:out value='${customer.id}' />">Delete</a>                    	
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>	
</body>
</html>