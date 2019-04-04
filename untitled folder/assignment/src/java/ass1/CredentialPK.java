/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ass1;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author kouji
 */
@Embeddable
public class CredentialPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "USER_USERNAME")
    private String userUsername;
    @Basic(optional = false)
    @NotNull
    @Column(name = "USER_ID")
    private long userId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "PASSWORD_HASH")
    private String passwordHash;

    public CredentialPK() {
    }

    public CredentialPK(String userUsername, long userId, String passwordHash) {
        this.userUsername = userUsername;
        this.userId = userId;
        this.passwordHash = passwordHash;
    }

    public String getUserUsername() {
        return userUsername;
    }

    public void setUserUsername(String userUsername) {
        this.userUsername = userUsername;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userUsername != null ? userUsername.hashCode() : 0);
        hash += (int) userId;
        hash += (passwordHash != null ? passwordHash.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CredentialPK)) {
            return false;
        }
        CredentialPK other = (CredentialPK) object;
        if ((this.userUsername == null && other.userUsername != null) || (this.userUsername != null && !this.userUsername.equals(other.userUsername))) {
            return false;
        }
        if (this.userId != other.userId) {
            return false;
        }
        if ((this.passwordHash == null && other.passwordHash != null) || (this.passwordHash != null && !this.passwordHash.equals(other.passwordHash))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ass1.CredentialPK[ userUsername=" + userUsername + ", userId=" + userId + ", passwordHash=" + passwordHash + " ]";
    }
    
}
