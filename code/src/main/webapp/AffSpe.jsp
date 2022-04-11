<%@ page import="servlet.ScolariteManager" %>

<!DOCTYPE html">
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Afficher les etudiants par specialitees</title>
</head>

<body>
    <h1>Afficher les etudiants par specialitees</h1>
    <form method="get" action="servlet/ScolariteManager">
        <select name="specialite">
            <%=ScolariteManager.lister(ScolariteManager.specialites)%>
        </select>
        <input type="submit" value="Submit" />
    </form>
</body>

</html>