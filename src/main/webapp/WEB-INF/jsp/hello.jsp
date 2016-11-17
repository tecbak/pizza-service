<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Dmytro_Rud
  Date: 17-Nov-16
  Time: 11:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
Hello!
<p>
<table border="1">

    <c:forEach var="pizza" items="${pizzas}">
        <tr>
            <td>
                    ${pizza}
            </td>
            <td>
                <form action="edit/pizza/${pizza.pizzaId}" method="post">
                    <input type="submit">
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
</p>
${message}
<br>
<a href="create">create</a>

</body>
</html>
