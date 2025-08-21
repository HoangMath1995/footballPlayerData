<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Edit Player Information</title>
    </head>
<body>

    <h2>Edit Player Information</h2>

    <div class="form-container">
        <form action="PlayerServlet" method="post">
            <input type="hidden" name="action" value="update">
            <input type="hidden" name="id" value="${player.playerIndexId}">

            <div class="form-group">
                <label for="playerName">Player name</label>
                <input type="text" id="playerName" name="playerName" value="${player.playerName}" required>
            </div>
            <div class="form-group">
                <label for="playerAge">Player age</label>
                <input type="number" id="playerAge" name="playerAge" value="${player.playerAge}" required>
            </div>
            <div class="form-group">
                <label for="indexName">Index name</label>
                <select id="indexName" name="indexId">
                    <c:forEach var="indexer" items="${indexers}">
                        <option value="${indexer.id}" ${indexer.name == player.indexName ? 'selected' : ''}>
                            ${indexer.name}
                        </option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group">
                <label for="value">Value</label>
                <input type="number" step="0.1" id="value" name="value" value="${player.value}" required>
            </div>
            <button type="submit" class="btn">Update</button>
        </form>
    </div>

</body>
</html>