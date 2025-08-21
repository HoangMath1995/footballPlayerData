<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Player Information</title>
    <style>
        body { font-family: sans-serif; }
        table { width: 100%; border-collapse: collapse; }
        th, td { border: 1px solid #dddddd; text-align: left; padding: 8px; }
        th { background-color: #f2f2f2; }
        .form-container { padding: 20px; border: 1px solid #ccc; border-radius: 5px; margin-bottom: 20px; }
        .form-group { margin-bottom: 10px; }
        .form-group label { display: block; margin-bottom: 5px; }
        .form-group input, .form-group select { width: 100%; padding: 8px; box-sizing: border-box; }
        .btn { padding: 10px 15px; background-color: #4CAF50; color: white; border: none; cursor: pointer; }
    </style>
</head>
<body>

    <h2>Player Information</h2>

    <div class="form-container">
        <form action="PlayerServlet" method="post">
            <input type="hidden" name="action" value="add">
            <div class="form-group">
                <label for="playerName">Player name</label>
                <input type="text" id="playerName" name="playerName" required>
            </div>
            <div class="form-group">
                <label for="playerAge">Player age</label>
                <input type="number" id="playerAge" name="playerAge" required>
            </div>
            <div class="form-group">
                <label for="indexName">Index name</label>
                <select id="indexName" name="indexId">
                    <c:forEach var="indexer" items="${indexers}">
                        <option value="${indexer.id}">${indexer.name}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group">
                <label for="value">Value</label>
                <input type="number" step="0.1" id="value" name="value" required>
            </div>
            <button type="submit" class="btn">Add</button>
        </form>
    </div>

    <table>
        <thead>
            <tr>
                <th>Id</th>
                <th>Player name</th>
                <th>Player age</th>
                <th>Index name</th>
                <th>Value</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="playerInfo" items="${players}">
                <tr>
                    <td>${playerInfo.playerIndexId}</td>
                    <td>${playerInfo.playerName}</td>
                    <td>${playerInfo.playerAge}</td>
                    <td>${playerInfo.indexName}</td>
                    <td>${playerInfo.value}</td>
                    <td>
                        <a href="PlayerServlet?action=edit&id=${playerInfo.playerIndexId}">Edit</a>
                        <a href="PlayerServlet?action=delete&id=${playerInfo.playerIndexId}" onclick="return confirm('Are you sure?')">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

</body>
</html>