/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import ass1.Report;
import ass1.ReportPK;
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
@Path("ass1.report")
public class ReportFacadeREST extends AbstractFacade<Report> {

    @PersistenceContext(unitName = "assignmentPU")
    private EntityManager em;

    private ReportPK getPrimaryKey(PathSegment pathSegment) {
        /*
         * pathSemgent represents a URI path segment and any associated matrix parameters.
         * URI path part is supposed to be in form of 'somePath;userId=userIdValue;reportDate=reportDateValue'.
         * Here 'somePath' is a result of getPath() method invocation and
         * it is ignored in the following code.
         * Matrix parameters are used as field names to build a primary key instance.
         */
        ass1.ReportPK key = new ass1.ReportPK();
        javax.ws.rs.core.MultivaluedMap<String, String> map = pathSegment.getMatrixParameters();
        java.util.List<String> userId = map.get("userId");
        if (userId != null && !userId.isEmpty()) {
            key.setUserId(new java.lang.Long(userId.get(0)));
        }
        java.util.List<String> reportDate = map.get("reportDate");
        if (reportDate != null && !reportDate.isEmpty()) {
            key.setReportDate(new java.util.Date(reportDate.get(0)));
        }
        return key;
    }

    public ReportFacadeREST() {
        super(Report.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Report entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") PathSegment id, Report entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") PathSegment id) {
        ass1.ReportPK key = getPrimaryKey(id);
        super.remove(super.find(key));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Report find(@PathParam("id") PathSegment id) {
        ass1.ReportPK key = getPrimaryKey(id);
        return super.find(key);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Report> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Report> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
     public List<Report> findByUserId(@PathParam("userId") Integer
    userId) {
    Query query = em.createNamedQuery("Report.findByUserId");
    query.setParameter("userId", userId);
    return query.getResultList();
    }
     
    @GET
    @Path("findByReportDate/{reportDate}")
    @Produces({"application/json"})
     public List<Report> findByReportDate(@PathParam("reportDate") Date
    reportDate) {
    Query query = em.createNamedQuery("Report.findByReportDate");
    query.setParameter("reportDate", reportDate);
    return query.getResultList();
    }
     
    @GET
    @Path("findByCaloriesConsumed/{caloriesConsumed}")
    @Produces({"application/json"})
     public List<Report> findByCaloriesConsumed(@PathParam("caloriesConsumed") Integer
    caloriesConsumed) {
    Query query = em.createNamedQuery("Report.findByCaloriesConsumedd");
    query.setParameter("caloriesConsumed", caloriesConsumed);
    return query.getResultList();
    }
     
    @GET
    @Path("findByCaloriesBurned/{caloriesBurned}")
    @Produces({"application/json"})
     public List<Report> findByCaloriesBurned(@PathParam("caloriesBurned") Integer
    caloriesBurned) {
    Query query = em.createNamedQuery("Report.findByCaloriesBurned");
    query.setParameter("caloriesBurned", caloriesBurned);
    return query.getResultList();
    }
     
    @GET
    @Path("findByStepsTaken/{stepsTaken}")
    @Produces({"application/json"})
     public List<Report> findByStepsTaken(@PathParam("stepsTaken") Integer
    stepsTaken) {
    Query query = em.createNamedQuery("Report.findByStepsTaken");
    query.setParameter("stepsTaken", stepsTaken);
    return query.getResultList();
    }
     
    @GET
    @Path("findByCalorieGoal/{calorieGoal}")
    @Produces({"application/json"})
     public List<Report> findByCalorieGoal(@PathParam("calorieGoal") Integer
    calorieGoal) {
    Query query = em.createNamedQuery("Report.findByCalorieGoal");
    query.setParameter("calorieGoal", calorieGoal);
    return query.getResultList();
    }
     
    //5.1
    @GET
    @Path("find5a/{userId}/{date}")
    @Produces({"application/json"})
    public Object find5a(@PathParam("userId") Integer userId,@PathParam("date") Date date) {//AND c.consumptionPK.consumptionDate = :consumptionDate c.consumptionPK.consumptionDate = :
        TypedQuery<Object[]> q = em.createQuery("SELECT r.caloriesConsumed,r.caloriesBurned,r.calorieGoal FROM Report"
                + " r WHERE r.reportPK.userId = :userId AND r.reportPK.reportDate = :date ", Object[].class);
        q.setParameter("userId", userId);
        q.setParameter("date", date);
        List<Object[]> queryList = q.getResultList();
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        Double totalconsumed = 0.0;
        Double totalburned = 0.0;
        Double remaining = 0.0;
        for (Object[] row : queryList) {
           totalconsumed=totalconsumed+(long)row[0];
           totalburned = totalburned+(long)row[1];
            remaining = remaining +((long)row[0])-((long)row[1])-((long)row[2]);
        }
        JsonObject personObject = Json.createObjectBuilder().add("totalcaloriesconsumed",totalconsumed).
                add("totalburned",totalburned).add("remaining",remaining).build();
        arrayBuilder.add(personObject);
        JsonArray jArray = arrayBuilder.build();
        return jArray;
    }
    //5.2
    @GET
    @Path("find5b/{userId}/{sdate}/{edate}")
    @Produces({"application/json"})
    public Object find5b(@PathParam("userId") Integer userId,@PathParam("sdate") Date sdate,@PathParam("edate") Date edate) {//AND c.consumptionPK.consumptionDate = :consumptionDate c.consumptionPK.consumptionDate = :
        TypedQuery<Object[]> q = em.createQuery("SELECT r.caloriesConsumed,r.caloriesBurned,r.stepsTaken FROM Report r WHERE "
                + "r.reportPK.userId = :userId AND r.reportPK.reportDate >= :sdate AND r.reportPK.reportDate <= :edate ", Object[].class);
        q.setParameter("userId", userId);
        q.setParameter("sdate", sdate);
        q.setParameter("edate", edate);
        List<Object[]> queryList = q.getResultList();
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        Double totalconsumed = 0.0;
        Double totalburned = 0.0;
        Double totalsteptaken = 0.0;
        for (Object[] row : queryList) {
           totalconsumed=totalconsumed+(long)row[1];
           totalburned = totalburned+(long)row[0];
            totalsteptaken = totalsteptaken +(long)row[2];
        }
        JsonObject personObject = Json.createObjectBuilder().add("totalcaloriesconsumed",totalconsumed).add("totalburned",totalburned)
                .add("totalsteptaken",totalsteptaken).build();
        arrayBuilder.add(personObject);
        JsonArray jArray = arrayBuilder.build();
        return jArray;
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
