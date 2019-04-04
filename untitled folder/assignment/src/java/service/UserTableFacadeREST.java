/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.sql.Date;
import ass1.UserTable;
import java.util.List;
import javax.ejb.Stateless;
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

/**
 *
 * @author kouji
 */
@Stateless
@Path("ass1.usertable")
public class UserTableFacadeREST extends AbstractFacade<UserTable> {

    @PersistenceContext(unitName = "assignmentPU")
    private EntityManager em;

    public UserTableFacadeREST() {
        super(UserTable.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(UserTable entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Long id, UserTable entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public UserTable find(@PathParam("id") Long id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<UserTable> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<UserTable> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }
    
    @GET
    @Path("findByUserId/{userId}")
    @Produces({"application/json"})
     public List<UserTable> findByUserId(@PathParam("userId") Integer
    userId) {
    Query query = em.createNamedQuery("UserTable.findByUserId");
    query.setParameter("userId", userId);
    return query.getResultList();
    }
     
    @GET
    @Path("findByUserName/{userName}")
    @Produces({"application/json"})
     public List<UserTable> findByUserName(@PathParam("userName") String
    userName) {
    Query query = em.createNamedQuery("UserTable.findByUserName");
    query.setParameter("userName", userName);
    return query.getResultList();
    }

    @GET
    @Path("findByUserSurname/{userSurname}")
    @Produces({"application/json"})
     public List<UserTable> findByUserSurname(@PathParam("userSurname") String
    userSurname) {
    Query query = em.createNamedQuery("UserTable.findByUserSurname");
    query.setParameter("userSurname", userSurname);
    return query.getResultList();
    }
     
    @GET
    @Path("findByEmail/{email}")
    @Produces({"application/json"})
     public List<UserTable> findByEmail(@PathParam("email") String
    email) {
    Query query = em.createNamedQuery("UserTable.findByEmail");
    query.setParameter("email", email);
    return query.getResultList();
    }
     
    @GET
    @Path("findByDob/{dob}")
    @Produces({"application/json"})
     public List<UserTable> findByDob(@PathParam("dob") Date
    dob) {
    Query query = em.createNamedQuery("UserTable.findByDob");
    query.setParameter("dob", dob);
    return query.getResultList();
    }
     
    @GET
    @Path("findByHeight/{height}")
    @Produces({"application/json"})
     public List<UserTable> findByHeight(@PathParam("height") Integer
    height) {
    Query query = em.createNamedQuery("UserTable.findByHeight");
    query.setParameter("height", height);
    return query.getResultList();
    }
    
    @GET
    @Path("findByWeight/{weight}")
    @Produces({"application/json"})
     public List<UserTable> findByWeight(@PathParam("weight") Integer
    weight) {
    Query query = em.createNamedQuery("UserTable.findByWeight");
    query.setParameter("weight", weight);
    return query.getResultList();
    }
     
    @GET
    @Path("findByGender/{gender}")
    @Produces({"application/json"})
     public List<UserTable> findByGender(@PathParam("gender") String
    gender) {
    Query query = em.createNamedQuery("UserTable.findByGender");
    query.setParameter("gender", gender);
    return query.getResultList();
    }
     
    @GET
    @Path("findByAddress/{address}")
    @Produces({"application/json"})
     public List<UserTable> findByAddress(@PathParam("address") String
    address) {
    Query query = em.createNamedQuery("UserTable.findByAddress");
    query.setParameter("address", address);
    return query.getResultList();
    }
    
    @GET
    @Path("findByPostcode/{postcode}")
    @Produces({"application/json"})
     public List<UserTable> findByPostcode(@PathParam("postcode") String
    postcode) {
    Query query = em.createNamedQuery("UserTable.findByPostcode");
    query.setParameter("postcode", postcode);
    return query.getResultList();
    }
    
    @GET
    @Path("findByLevelOfActivity/{levelOfActivity}")
    @Produces({"application/json"})
     public List<UserTable> findByLevelOfActivity(@PathParam("levelOfActivity") String
    levelOfActivity) {
    Query query = em.createNamedQuery("UserTable.findByLevelOfActivity");
    query.setParameter("levelOfActivity", levelOfActivity);
    return query.getResultList();
    }
    
     
    @GET
    @Path("findByStepsPerMile/{stepsPerMile}")
    @Produces({"application/json"})
     public List<UserTable> findByStepsPerMile(@PathParam("stepsPerMile") Integer
    stepsPerMile) {
    Query query = em.createNamedQuery("UserTable.findByStepsPerMile");
    query.setParameter("stepsPerMile", stepsPerMile);
    return query.getResultList();
    }
    
    //dy
 
    @GET
    @Path("findByUserNameanduserid/{userId}/{userName}")
    @Produces({"application/json"})
    public List<UserTable> findByUserNameanduserid(@PathParam("userId") Integer userId, @PathParam("userName") String userName) {
        TypedQuery<UserTable> q = em.createQuery("SELECT e FROM UserTable e WHERE e.userId = :userId AND"
                + " e.userName = :userName", UserTable.class);
        q.setParameter("userId", userId);
        q.setParameter("userName", userName); 
        return q.getResultList();
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
