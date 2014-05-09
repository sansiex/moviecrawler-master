<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>Dashboard</title>

    <%@ include file="common/ref.jsp" %>

    <script src="js/dashboard.js"></script>
</head>
<body>
    <h3>Movie Crawler Dashboard</h3>
    <hr>
    <p>Output from slaves</p>
    <textarea id="stdout" style="width: 600px;height: 300px"></textarea>
    <button onclick="send()">Send request</button>
</body>
</html>