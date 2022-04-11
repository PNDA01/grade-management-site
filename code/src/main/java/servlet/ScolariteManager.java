package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

/**
 * Servlet implementation class ScolariteManager
 */
public class ScolariteManager extends HttpServlet {
	private static final long serialVersionUID = 1L;

	static Hashtable<String, String> logins = new Hashtable<String, String>();

	static ArrayList<Etudiant> etudiants = new ArrayList<>();

	public static ArrayList<String> specialites = new ArrayList<>();

	public static ArrayList<String> modules = new ArrayList<>();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ScolariteManager() {
		super();
		logins.put("panda", "1234"); // identifiant et mot de passe

		specialites.add("ICY");
		specialites.add("ME");
		specialites.add("MT");
		specialites.add("IIA");
		specialites.add("GY");
		specialites.add("GCB");

		modules.add("Mathematiques");
		modules.add("Mathematiques appliquees");
		modules.add("Physique");
		modules.add("Physique appliquee");
		modules.add("Informatique");
		modules.add("Sport");
		modules.add("Langue");
	}

	/**
	 * Afficher liste dynamique
	 * 
	 * @param liste
	 * @return
	 */
	public static String lister(ArrayList<String> liste) {
		StringBuilder sb = new StringBuilder();
		for (String s : liste) {
			sb.append("<option value=" + s + ">" + s + "</option>");
		}
		return sb.toString();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		switch (request.getHeader("referer")) {
			case "http://localhost:8080/tp3/Inscrire.jsp":

				String nom = request.getParameter("nom");
				String prenom = request.getParameter("prenom");
				String specialite = request.getParameter("specialite");

				if ((!StringUtils.isBlank(nom)) && (!StringUtils.isBlank(prenom))) {
					Etudiant etudiant = new Etudiant();
					etudiant.setNom(nom);
					etudiant.setPrenom(prenom);
					etudiant.setSpecialite(specialite);
					etudiants.add(etudiant);
					response.sendRedirect("/tp3/Accueil.html");
				} else
					response.getWriter().println("login ou mot de passe invalide");

				break;

			case "http://localhost:8080/tp3/Saisir.jsp":
				nom = request.getParameter("nom");
				prenom = request.getParameter("prenom");
				String module = request.getParameter("module");
				Double note = Double.parseDouble(request.getParameter("note"));

				// On cherche si l'étudiant existe
				Optional<Etudiant> etudiant = etudiants.stream()
						.filter(e -> ((e.getNom().compareTo(nom) == 0) && (e.getPrenom().compareTo(prenom) == 0))).findFirst();
				if (etudiant.isPresent()) {
					// On récupère l"indice de l'étudiant si il existe
					int indice = etudiants.indexOf(etudiant.get());
					etudiants.get(indice).addNotes(module, note);
					response.sendRedirect("/tp3/Accueil.html");
				} else
					response.getWriter().println("Nom ou prenom invalide");

				break;

			case "http://localhost:8080/tp3/AffNotes.jsp":
				module = request.getParameter("module");
				StringBuilder sb = new StringBuilder();
				for (Etudiant s : etudiants) {
					for (Double x : s.getNotes(module)) {
						sb.append(x.toString() + ";  ");
					}
				}
				response.getWriter()
						.println("<html><body><h1>Les notes du module " + module + " sont:</h1><br><p>" + sb.toString()
								+ "</p><ul><a href='/tp3/Accueil.html'>Retour a l'Accueil</a></ul></body></html>");
				break;

			case "http://localhost:8080/tp3/AffSpe.jsp":
				specialite = request.getParameter("specialite");
				sb = new StringBuilder();
				for (Etudiant s : etudiants) {
					if (s.getSpecialite().equals(specialite)) {
						sb.append(s.toString() + ", \n");
					}
				}
				response.getWriter()
						.println("<html><body><h1>Les eleves de la specialité " + specialite + " sont:</h1><br><p>"
								+ sb.toString()
								+ "</p><ul><a href='/tp3/Accueil.html'>Retour a l'Accueil</a></ul></body></html>");
				break;

			case "http://localhost:8080/tp3/":

				String pwd = logins.get(request.getParameter("username"));

				if ((pwd != null) && (pwd.compareTo(request.getParameter("password")) == 0))
					response.sendRedirect("/tp3/Accueil.html");
				else
					response.getWriter().println("login ou mot de passe invalide");
				break;
		}
	}
}
