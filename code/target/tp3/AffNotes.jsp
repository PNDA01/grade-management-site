<%@ page import="servlet.ScolariteManager" %>

<!DOCTYPE html">
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Afficher les notes des etudiants dans un module</title>
</head>

<body>
    <h1>Afficher les notes des etudiants dans un module</h1>
    <form method="get" action="servlet/ScolariteManager">
        <select name="module">
            <%=ScolariteManager.lister(ScolariteManager.modules)%>
        </select>>
        <input type="submit" value="Submit" />
    </form>
</body>

</html>