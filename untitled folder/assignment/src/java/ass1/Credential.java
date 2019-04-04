/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ass1;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author kouji
 */
@Entity
@Table(name = "CREDENTIAL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Credential.findAll", query = "SELECT c FROM Credential c")
    , @NamedQuery(name = "Credential.findByUserUsername", query = "SELECT c FROM Credential c WHERE c.credentialPK.userUsername = :userUsername")
    , @NamedQuery(name = "Credential.findByUserTable", query = "SELECT c FROM Credential c WHERE c.userTable.userId = :userId")
    , @NamedQuery(name = "Credential.findByFirstnameANDSurnames", query = "SELECT c FROM Credential c WHERE UPPER(c.userTable.userName) = UPPER(:userName) AND UPPER(c.userTable.userSurname) = UPPER(:userSurname)")
    , @NamedQuery(name = "Credential.findByUserId", query = "SELECT c FROM Credential c WHERE c.credentialPK.userId = :userId")
    , @NamedQuery(name = "Credential.findByPasswordHash", query = "SELECT c FROM Credential c WHERE c.credentialPK.passwordHash = :passwordHash")
    , @NamedQuery(name = "Credential.findBySignupDate", query = "SELECT c FROM Credential c WHERE c.signupDate = :signupDate")})
public class Credential implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CredentialPK credentialPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SIGNUP_DATE")
    @Temporal(TemporalType.DATE)
    private Date signupDate;
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private UserTable userTable;

    public Credential() {
    }

    public Credential(CredentialPK credentialPK) {
        this.credentialPK = credentialPK;
    }

    public Credential(CredentialPK credentialPK, Date signupDate) {
        this.credentialPK = credentialPK;
        this.signupDate = signupDate;
    }

    public Credential(String userUsername, long userId, String passwordHash) {
        this.credentialPK = new CredentialPK(userUsername, userId, passwordHash);
    }

    public CredentialPK getCredentialPK() {
        return credentialPK;
    }

    public void setCredentialPK(CredentialPK credentialPK) {
        this.credentialPK = credentialPK;
    }

    public Date getSignupDate() {
        return signupDate;
    }

    public void setSignupDate(Date signupDate) {
        this.signupDate = signupDate;
    }

    public UserTable getUserTable() {
        return userTable;
    }

    public void setUserTable(UserTable userTable) {
        this.userTable = userTable;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (credentialPK != null ? credentialPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Credential)) {
            return false;
        }
        Credential other = (Credential) object;
        if ((this.credentialPK == null && other.credentialPK != null) || (this.credentialPK != null && !this.credentialPK.equals(other.credentialPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ass1.Credential[ credentialPK=" + credentialPK + " ]";
    }
    
}
