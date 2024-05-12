<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    
<!DOCTYPE html>
<html>
<head>
    <title>Questions</title>
</head>
<body>
    <h1>List of Questions</h1>
    <table>
        <tr>
            <th>Question ID</th>
            <th>Question Text</th>
        </tr>
        <c:forEach var="question" items="${questions}">
            <tr>
                <td>${question.questionId}</td>
                <td>${question.qtext}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>

    