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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author kouji
 */
@Entity
@Table(name = "REPORT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Report.findAll", query = "SELECT r FROM Report r")
    , @NamedQuery(name = "Report.findByUserId", query = "SELECT r FROM Report r WHERE r.reportPK.userId = :userId")
    , @NamedQuery(name = "Report.findByReportDate", query = "SELECT r FROM Report r WHERE r.reportPK.reportDate = :reportDate")
    , @NamedQuery(name = "Report.findByCaloriesConsumed", query = "SELECT r FROM Report r WHERE r.caloriesConsumed = :caloriesConsumed")
    , @NamedQuery(name = "Report.findByCaloriesBurned", query = "SELECT r FROM Report r WHERE r.caloriesBurned = :caloriesBurned")
    , @NamedQuery(name = "Report.findByStepsTaken", query = "SELECT r FROM Report r WHERE r.stepsTaken = :stepsTaken")
    , @NamedQuery(name = "Report.findByCalorieGoal", query = "SELECT r FROM Report r WHERE r.calorieGoal = :calorieGoal")})
public class Report implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ReportPK reportPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CALORIES_CONSUMED")
    private long caloriesConsumed;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CALORIES_BURNED")
    private long caloriesBurned;
    @Basic(optional = false)
    @NotNull
    @Column(name = "STEPS_TAKEN")
    private long stepsTaken;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CALORIE_GOAL")
    private long calorieGoal;
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private UserTable userTable;

    public Report() {
    }

    public Report(ReportPK reportPK) {
        this.reportPK = reportPK;
    }

    public Report(ReportPK reportPK, long caloriesConsumed, long caloriesBurned, long stepsTaken, long calorieGoal) {
        this.reportPK = reportPK;
        this.caloriesConsumed = caloriesConsumed;
        this.caloriesBurned = caloriesBurned;
        this.stepsTaken = stepsTaken;
        this.calorieGoal = calorieGoal;
    }

    public Report(long userId, Date reportDate) {
        this.reportPK = new ReportPK(userId, reportDate);
    }

    public ReportPK getReportPK() {
        return reportPK;
    }

    public void setReportPK(ReportPK reportPK) {
        this.reportPK = reportPK;
    }

    public long getCaloriesConsumed() {
        return caloriesConsumed;
    }

    public void setCaloriesConsumed(long caloriesConsumed) {
        this.caloriesConsumed = caloriesConsumed;
    }

    public long getCaloriesBurned() {
        return caloriesBurned;
    }

    public void setCaloriesBurned(long caloriesBurned) {
        this.caloriesBurned = caloriesBurned;
    }

    public long getStepsTaken() {
        return stepsTaken;
    }

    public void setStepsTaken(long stepsTaken) {
        this.stepsTaken = stepsTaken;
    }

    public long getCalorieGoal() {
        return calorieGoal;
    }

    public void setCalorieGoal(long calorieGoal) {
        this.calorieGoal = calorieGoal;
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
        hash += (reportPK != null ? reportPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Report)) {
            return false;
        }
        Report other = (Report) object;
        if ((this.reportPK == null && other.reportPK != null) || (this.reportPK != null && !this.reportPK.equals(other.reportPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ass1.Report[ reportPK=" + reportPK + " ]";
    }
    
}
