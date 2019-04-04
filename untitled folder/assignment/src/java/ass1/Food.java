/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ass1;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author kouji
 */
@Entity
@Table(name = "FOOD")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Food.findAll", query = "SELECT f FROM Food f")
    , @NamedQuery(name = "Food.findByFoodName", query = "SELECT f FROM Food f WHERE f.foodName = :foodName")
    , @NamedQuery(name = "Food.findByFoodId", query = "SELECT f FROM Food f WHERE f.foodId = :foodId")
    , @NamedQuery(name = "Food.findByCategory", query = "SELECT f FROM Food f WHERE f.category = :category")
    , @NamedQuery(name = "Food.findByCalorieAmount", query = "SELECT f FROM Food f WHERE f.calorieAmount = :calorieAmount")
    , @NamedQuery(name = "Food.findByServingUnit", query = "SELECT f FROM Food f WHERE f.servingUnit = :servingUnit")
    , @NamedQuery(name = "Food.findByServingAmount", query = "SELECT f FROM Food f WHERE f.servingAmount = :servingAmount")
    , @NamedQuery(name = "Food.findByFat", query = "SELECT f FROM Food f WHERE f.fat = :fat")})
public class Food implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "FOOD_NAME")
    private String foodName;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "FOOD_ID")
    private Long foodId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "CATEGORY")
    private String category;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CALORIE_AMOUNT")
    private long calorieAmount;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "SERVING_UNIT")
    private String servingUnit;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SERVING_AMOUNT")
    private long servingAmount;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FAT")
    private long fat;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "food")
    private Collection<Consumption> consumptionCollection;

    public Food() {
    }

    public Food(Long foodId) {
        this.foodId = foodId;
    }

    public Food(Long foodId, String foodName, String category, long calorieAmount, String servingUnit, long servingAmount, long fat) {
        this.foodId = foodId;
        this.foodName = foodName;
        this.category = category;
        this.calorieAmount = calorieAmount;
        this.servingUnit = servingUnit;
        this.servingAmount = servingAmount;
        this.fat = fat;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public Long getFoodId() {
        return foodId;
    }

    public void setFoodId(Long foodId) {
        this.foodId = foodId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public long getCalorieAmount() {
        return calorieAmount;
    }

    public void setCalorieAmount(long calorieAmount) {
        this.calorieAmount = calorieAmount;
    }

    public String getServingUnit() {
        return servingUnit;
    }

    public void setServingUnit(String servingUnit) {
        this.servingUnit = servingUnit;
    }

    public long getServingAmount() {
        return servingAmount;
    }

    public void setServingAmount(long servingAmount) {
        this.servingAmount = servingAmount;
    }

    public long getFat() {
        return fat;
    }

    public void setFat(long fat) {
        this.fat = fat;
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
        hash += (foodId != null ? foodId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Food)) {
            return false;
        }
        Food other = (Food) object;
        if ((this.foodId == null && other.foodId != null) || (this.foodId != null && !this.foodId.equals(other.foodId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ass1.Food[ foodId=" + foodId + " ]";
    }
    
}
