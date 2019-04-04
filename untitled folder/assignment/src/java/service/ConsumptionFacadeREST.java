/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import ass1.Consumption;
import ass1.ConsumptionPK;
import java.sql.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.PathSegment;

/**
 *
 * @author kouji
 */
@Stateless
@Path("ass1.consumption")
public class ConsumptionFacadeREST extends AbstractFacade<Consumption> {

    @PersistenceContext(unitName = "assignmentPU")
    private EntityManager em;

    private ConsumptionPK getPrimaryKey(PathSegment pathSegment) {
        /*
         * pathSemgent represents a URI path segment and any associated matrix parameters.
         * URI path part is supposed to be in form of 'somePath;userId=userIdValue;foodId=foodIdValue;consumptionDate=consumptionDateValue'.
         * Here 'somePath' is a result of getPath() method invocation and
         * it is ignored in the following code.
         * Matrix parameters are used as field names to build a primary key instance.
         */
        ass1.ConsumptionPK key = new ass1.ConsumptionPK();
        javax.ws.rs.core.MultivaluedMap<String, String> map = pathSegment.getMatrixParameters();
        java.util.List<String> userId = map.get("userId");
        if (userId != null && !userId.isEmpty()) {
            key.setUserId(new java.lang.Long(userId.get(0)));
        }
        java.util.List<String> foodId = map.get("foodId");
        if (foodId != null && !foodId.isEmpty()) {
            key.setFoodId(new java.lang.Long(foodId.get(0)));
        }
        java.util.List<String> consumptionDate = map.get("consumptionDate");
        if (consumptionDate != null && !consumptionDate.isEmpty()) {
            key.setConsumptionDate(new java.util.Date(consumptionDate.get(0)));
        }
        return key;
    }

    public ConsumptionFacadeREST() {
        super(Consumption.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Consumption entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") PathSegment id, Consumption entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") PathSegment id) {
        ass1.ConsumptionPK key = getPrimaryKey(id);
        super.remove(super.find(key));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Consumption find(@PathParam("id") PathSegment id) {
        ass1.ConsumptionPK key = getPrimaryKey(id);
        return super.find(key);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Consumption> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Consumption> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }
    
    @GET
    @Path("findByUserId/{userId}")
    @Produces({"application/json"})
     public List<Consumption> findByUserId(@PathParam("userId") Integer
    userId) {
    Query query = em.createNamedQuery("Consumption.findByUserId");
    query.setParameter("userId", userId);
    return query.getResultList();
    }
     
    @GET
    @Path("findByFoodId/{foodId}")
    @Produces({"application/json"})
     public List<Consumption> findByFoodId(@PathParam("foodId") Integer
    foodId) {
    Query query = em.createNamedQuery("Consumption.findByFoodId");
    query.setParameter("foodId", foodId);
    return query.getResultList();
    }
     
    @GET
    @Path("findByConsumptionDate/{consumptionDate}")
    @Produces({"application/json"})
     public List<Consumption> findByConsumptionDate(@PathParam("consumptionDate") Date
    consumptionDate) {
    Query query = em.createNamedQuery("Consumption.findByConsumptionDate");
    query.setParameter("consumptionDate", consumptionDate);
    return query.getResultList();
    }
     
    @GET
    @Path("findByQuantity/{quantity}")
    @Produces({"application/json"})
     public List<Consumption> findByQuantity(@PathParam("quantity") Integer
    quantity) {
    Query query = em.createNamedQuery("Consumption.findByQuantity");
    query.setParameter("quantity", quantity);
    return query.getResultList();
    }
     
    //4.4
    @GET
    @Path("findtotalcaloriesconsumed/{userId}/{date}")
    @Produces({"application/json"})
    public Object findtotalcaloriesconsumed(@PathParam("userId") Integer userId,@PathParam("date") Date date) {
        TypedQuery<Object[]> q = em.createQuery("SELECT c.food.calorieAmount,c.quantity FROM Consumption c WHERE "
                + "c.consumptionPK.userId = :userId AND c.consumptionPK.consumptionDate = :date ", Object[].class);
        q.setParameter("userId", userId);
        q.setParameter("date", date);
        List<Object[]> queryList = q.getResultList();
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        Double total = 0.0;
        for (Object[] row : queryList) {
           total = total + (long)row[0]*(long)row[1];
            
        }
        JsonObject personObject = Json.createObjectBuilder().add("totalcaloriesconsumed",total).build();
        arrayBuilder.add(personObject);
        JsonArray jArray = arrayBuilder.build();
        return jArray;
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
