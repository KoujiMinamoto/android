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
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author kouji
 */
@Embeddable
public class ReportPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "USER_ID")
    private long userId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "REPORT_DATE")
    @Temporal(TemporalType.DATE)
    private Date reportDate;

    public ReportPK() {
    }

    public ReportPK(long userId, Date reportDate) {
        this.userId = userId;
        this.reportDate = reportDate;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) userId;
        hash += (reportDate != null ? reportDate.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReportPK)) {
            return false;
        }
        ReportPK other = (ReportPK) object;
        if (this.userId != other.userId) {
            return false;
        }
        if ((this.reportDate == null && other.reportDate != null) || (this.reportDate != null && !this.reportDate.equals(other.reportDate))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ass1.ReportPK[ userId=" + userId + ", reportDate=" + reportDate + " ]";
    }
    
}
