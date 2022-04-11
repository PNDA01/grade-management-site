package servlet;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;

public class Etudiant implements Serializable {
    String nom;
    String prenom;
    String specialite;
    Hashtable<String, ArrayList<Double>> notes;

    Etudiant() {
        this.nom = "";
        this.prenom = "";
        this.specialite = "";
        this.notes = new Hashtable<String, ArrayList<Double>>();
    }

    public void addNotes(String module, Double note) {
        if (!this.notes.containsKey(module)) {
            this.notes.put(module, new ArrayList<Double>());
        }
        this.notes.get(module).add(note);
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public void setNotes(String module, Double note, int indice) {
        this.notes.get(module).set(indice, note);
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getSpecialite() {
        return specialite;
    }

    public ArrayList<Double> getNotes(String module) {
        return notes.get(module);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getNom() + " " + this.getPrenom());
        return sb.toString();
    }
}
