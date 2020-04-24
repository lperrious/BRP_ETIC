
package fr.etic.brp.brp_back_end.metier.modele;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author louisrob
 */

@Entity
public class Operateur implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idOperateur;
    @Column(unique = true)
    private String mail;
    private String mdp;
    private Integer salt;
    private String token;
    private String nom;

    public Operateur() {
    }

    public Operateur(String mail, String mdp, Integer salt, String nom) {
        this.mail = mail;
        this.mdp = mdp;
        this.salt = salt;
        this.nom = nom;
        this.token = null;
    }

    public Long getIdOperateur() {
        return idOperateur;
    }

    public void setIdOperateur(Long idOperateur) {
        this.idOperateur = idOperateur;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public Integer getSalt() {
        return salt;
    }

    public void setSalt(Integer salt) {
        this.salt = salt;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }  

    @Override
    public String toString() {
        return "Operateur{" + "idOperateur=" + idOperateur + ", mail=" + mail + ", mdp=" + mdp + ", salt=" + salt + ", token=" + token + ", nom=" + nom + '}';
    }
}
