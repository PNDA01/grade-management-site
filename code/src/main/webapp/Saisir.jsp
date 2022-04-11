<%@ page import="servlet.ScolariteManager" %>

<!DOCTYPE html">
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Saisir la note d'un etudiant</title>
</head>

<body>
    <h1>Saisir une note</h1>
    <form method="get" action="servlet/ScolariteManager">
        <table style "50%">
            <tr>
                <td>Nom</td>
                <td><input type="text" name="nom" required /></td>
            </tr>
            <tr>
                <td>Prenom</td>
                <td><input type="text" name="prenom" required /></td>
            </tr>
            <select name="module">
                <%=ScolariteManager.lister(ScolariteManager.modules)%>
            </select>>
            <tr>
                <td>Note</td>
                <td><input type="text" name="note" required /></td>
            </tr>
        </table>
        <input type="submit" value="Submit" />
    </form>
</body>

</html>