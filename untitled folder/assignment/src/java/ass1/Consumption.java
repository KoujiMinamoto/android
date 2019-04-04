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
@Table(name = "CONSUMPTION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Consumption.findAll", query = "SELECT c FROM Consumption c")
    , @NamedQuery(name = "Consumption.findByUserId", query = "SELECT c FROM Consumption c WHERE c.consumptionPK.userId = :userId")
    , @NamedQuery(name = "Consumption.findByUserTable", query = "SELECT c FROM Consumption c WHERE c.userTable.userId = :userId")
    , @NamedQuery(name = "Consumption.findByFood", query = "SELECT c FROM Consumption c WHERE c.food.foodId = :foodId")    
    , @NamedQuery(name = "Consumption.findByFoodId", query = "SELECT c FROM Consumption c WHERE c.consumptionPK.foodId = :foodId")
    , @NamedQuery(name = "Consumption.findByConsumptionDate", query = "SELECT c FROM Consumption c WHERE c.consumptionPK.consumptionDate = :consumptionDate")
    , @NamedQuery(name = "Consumption.findByQuantity", query = "SELECT c FROM Consumption c WHERE c.quantity = :quantity")})
public class Consumption implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ConsumptionPK consumptionPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "QUANTITY")
    private long quantity;
    @JoinColumn(name = "FOOD_ID", referencedColumnName = "FOOD_ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Food food;
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private UserTable userTable;

    public Consumption() {
    }

    public Consumption(ConsumptionPK consumptionPK) {
        this.consumptionPK = consumptionPK;
    }

    public Consumption(ConsumptionPK consumptionPK, long quantity) {
        this.consumptionPK = consumptionPK;
        this.quantity = quantity;
    }

    public Consumption(long userId, long foodId, Date consumptionDate) {
        this.consumptionPK = new ConsumptionPK(userId, foodId, consumptionDate);
    }

    public ConsumptionPK getConsumptionPK() {
        return consumptionPK;
    }

    public void setConsumptionPK(ConsumptionPK consumptionPK) {
        this.consumptionPK = consumptionPK;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
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
        hash += (consumptionPK != null ? consumptionPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Consumption)) {
            return false;
        }
        Consumption other = (Consumption) object;
        if ((this.consumptionPK == null && other.consumptionPK != null) || (this.consumptionPK != null && !this.consumptionPK.equals(other.consumptionPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ass1.Consumption[ consumptionPK=" + consumptionPK + " ]";
    }
    
}
