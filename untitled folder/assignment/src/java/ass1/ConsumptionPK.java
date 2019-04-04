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
public class ConsumptionPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "USER_ID")
    private long userId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FOOD_ID")
    private long foodId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CONSUMPTION_DATE")
    @Temporal(TemporalType.DATE)
    private Date consumptionDate;

    public ConsumptionPK() {
    }

    public ConsumptionPK(long userId, long foodId, Date consumptionDate) {
        this.userId = userId;
        this.foodId = foodId;
        this.consumptionDate = consumptionDate;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getFoodId() {
        return foodId;
    }

    public void setFoodId(long foodId) {
        this.foodId = foodId;
    }

    public Date getConsumptionDate() {
        return consumptionDate;
    }

    public void setConsumptionDate(Date consumptionDate) {
        this.consumptionDate = consumptionDate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) userId;
        hash += (int) foodId;
        hash += (consumptionDate != null ? consumptionDate.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ConsumptionPK)) {
            return false;
        }
        ConsumptionPK other = (ConsumptionPK) object;
        if (this.userId != other.userId) {
            return false;
        }
        if (this.foodId != other.foodId) {
            return false;
        }
        if ((this.consumptionDate == null && other.consumptionDate != null) || (this.consumptionDate != null && !this.consumptionDate.equals(other.consumptionDate))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ass1.ConsumptionPK[ userId=" + userId + ", foodId=" + foodId + ", consumptionDate=" + consumptionDate + " ]";
    }
    
}
