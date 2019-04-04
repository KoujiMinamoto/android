/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ass1;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author kouji
 */
@Entity
@Table(name = "USER_TABLE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserTable.findAll", query = "SELECT u FROM UserTable u")
    , @NamedQuery(name = "UserTable.findByUserId", query = "SELECT u FROM UserTable u WHERE u.userId = :userId")
    , @NamedQuery(name = "UserTable.findByUserName", query = "SELECT u FROM UserTable u WHERE u.userName = :userName")
    , @NamedQuery(name = "UserTable.findByUserSurname", query = "SELECT u FROM UserTable u WHERE u.userSurname = :userSurname")
    , @NamedQuery(name = "UserTable.findByEmail", query = "SELECT u FROM UserTable u WHERE u.email = :email")
    , @NamedQuery(name = "UserTable.findByDob", query = "SELECT u FROM UserTable u WHERE u.dob = :dob")
    , @NamedQuery(name = "UserTable.findByHeight", query = "SELECT u FROM UserTable u WHERE u.height = :height")
    , @NamedQuery(name = "UserTable.findByWeight", query = "SELECT u FROM UserTable u WHERE u.weight = :weight")
    , @NamedQuery(name = "UserTable.findByGender", query = "SELECT u FROM UserTable u WHERE u.gender = :gender")
    , @NamedQuery(name = "UserTable.findByAddress", query = "SELECT u FROM UserTable u WHERE u.address = :address")
    , @NamedQuery(name = "UserTable.findByPostcode", query = "SELECT u FROM UserTable u WHERE u.postcode = :postcode")
    , @NamedQuery(name = "UserTable.findByLevelOfActivity", query = "SELECT u FROM UserTable u WHERE u.levelOfActivity = :levelOfActivity")
    , @NamedQuery(name = "UserTable.findByStepsPerMile", query = "SELECT u FROM UserTable u WHERE u.stepsPerMile = :stepsPerMile")})
public class UserTable implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "USER_ID")
    private Long userId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "USER_NAME")
    private String userName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "USER_SURNAME")
    private String userSurname;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "EMAIL")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DOB")
    @Temporal(TemporalType.DATE)
    private Date dob;
    @Basic(optional = false)
    @NotNull
    @Column(name = "HEIGHT")
    private long height;
    @Basic(optional = false)
    @NotNull
    @Column(name = "WEIGHT")
    private long weight;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "GENDER")
    private String gender;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "ADDRESS")
    private String address;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "POSTCODE")
    private String postcode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "LEVEL_OF_ACTIVITY")
    private String levelOfActivity;
    @Basic(optional = false)
    @NotNull
    @Column(name = "STEPS_PER_MILE")
    private long stepsPerMile;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userTable")
    private Collection<Credential> credentialCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userTable")
    private Collection<Report> reportCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userTable")
    private Collection<Consumption> consumptionCollection;

    public UserTable() {
    }

    public UserTable(Long userId) {
        this.userId = userId;
    }

    public UserTable(Long userId, String userName, String userSurname, String email, Date dob, long height, long weight, String gender, String address, String postcode, String levelOfActivity, long stepsPerMile) {
        this.userId = userId;
        this.userName = userName;
        this.userSurname = userSurname;
        this.email = email;
        this.dob = dob;
        this.height = height;
        this.weight = weight;
        this.gender = gender;
        this.address = address;
        this.postcode = postcode;
        this.levelOfActivity = levelOfActivity;
        this.stepsPerMile = stepsPerMile;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSurname() {
        return userSurname;
    }

    public void setUserSurname(String userSurname) {
        this.userSurname = userSurname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public long getHeight() {
        return height;
    }

    public void setHeight(long height) {
        this.height = height;
    }

    public long getWeight() {
        return weight;
    }

    public void setWeight(long weight) {
        this.weight = weight;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getLevelOfActivity() {
        return levelOfActivity;
    }

    public void setLevelOfActivity(String levelOfActivity) {
        this.levelOfActivity = levelOfActivity;
    }

    public long getStepsPerMile() {
        return stepsPerMile;
    }

    public void setStepsPerMile(long stepsPerMile) {
        this.stepsPerMile = stepsPerMile;
    }

    @XmlTransient
    public Collection<Credential> getCredentialCollection() {
        return credentialCollection;
    }

    public void setCredentialCollection(Collection<Credential> credentialCollection) {
        this.credentialCollection = credentialCollection;
    }

    @XmlTransient
    public Collection<Report> getReportCollection() {
        return reportCollection;
    }

    public void setReportCollection(Collection<Report> reportCollection) {
        this.reportCollection = reportCollection;
    }

    @XmlTransient
    public Collection<Consumption> getConsumptionCollection() {
        return consumptionCollection;
    }

    public void setConsumptionCollection(Collection<Consumption> consumptionCollection) {
        this.consumptionCollection = consumptionCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserTable)) {
            return false;
        }
        UserTable other = (UserTable) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ass1.UserTable[ userId=" + userId + " ]";
    }
    
}
