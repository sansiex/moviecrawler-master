<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>Dashboard</title>

    <%@ include file="common/ref.jsp" %>

    <script src="js/dashboard.js"></script>
    <script src="js/utils/datetime.js"></script>
</head>
<body>
    <h3>Movie Crawler Dashboard</h3>
    <hr>
    <p>Output from slaves</p>
    <button onclick="loadRecent()">Recent log</button>
    <br>

    <input id="start" name="start" onfocus="setday(this)" style="cursor:hand">
    <input id="end" name="end" onfocus="setday(this)" style="cursor:hand">

    <button onclick="loadLog()">Load log</button>
    <br>
    <textarea id="stdout" style="width: 600px;height: 300px"></textarea>
    <button onclick="send()">Send request</button>
</body>
</html>