/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import ass1.Credential;
import ass1.CredentialPK;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
@Path("ass1.credential")
public class CredentialFacadeREST extends AbstractFacade<Credential> {

    @PersistenceContext(unitName = "assignmentPU")
    private EntityManager em;

    private CredentialPK getPrimaryKey(PathSegment pathSegment) {
        /*
         * pathSemgent represents a URI path segment and any associated matrix parameters.
         * URI path part is supposed to be in form of 'somePath;userUsername=userUsernameValue;userId=userIdValue;passwordHash=passwordHashValue'.
         * Here 'somePath' is a result of getPath() method invocation and
         * it is ignored in the following code.
         * Matrix parameters are used as field names to build a primary key instance.
         */
        ass1.CredentialPK key = new ass1.CredentialPK();
        javax.ws.rs.core.MultivaluedMap<String, String> map = pathSegment.getMatrixParameters();
        java.util.List<String> userUsername = map.get("userUsername");
        if (userUsername != null && !userUsername.isEmpty()) {
            key.setUserUsername(userUsername.get(0));
        }
        java.util.List<String> userId = map.get("userId");
        if (userId != null && !userId.isEmpty()) {
            key.setUserId(new java.lang.Long(userId.get(0)));
        }
        java.util.List<String> passwordHash = map.get("passwordHash");
        if (passwordHash != null && !passwordHash.isEmpty()) {
            key.setPasswordHash(passwordHash.get(0));
        }
        return key;
    }

    public CredentialFacadeREST() {
        super(Credential.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Credential entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") PathSegment id, Credential entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") PathSegment id) {
        ass1.CredentialPK key = getPrimaryKey(id);
        super.remove(super.find(key));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Credential find(@PathParam("id") PathSegment id) {
        ass1.CredentialPK key = getPrimaryKey(id);
        return super.find(key);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Credential> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Credential> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }
    
    @GET
    @Path("findByUserUsername/{userUsername}")
    @Produces({"application/json"})
     public List<Credential> findByUserUsername(@PathParam("userUsername") String
    userUsername) {
    Query query = em.createNamedQuery("Credential.findByUserUsername");
    query.setParameter("userUsername", userUsername);
    return query.getResultList();
    }
     
    @GET
    @Path("findByUserId/{userId}")
    @Produces({"application/json"})
     public List<Credential> findByUserId(@PathParam("userId") Integer
    userId) {
    Query query = em.createNamedQuery("Credential.findByUserId");
    query.setParameter("userId", userId);
    return query.getResultList();
    }
     
    @GET
    @Path("findByPasswordHash/{passwordHash}")
    @Produces({"application/json"})
     public List<Credential> findByPasswordHash(@PathParam("passwordHash") String
    passwordHash) {
    Query query = em.createNamedQuery("Credential.findByPasswordHash");
    query.setParameter("passwordHash", passwordHash);
    return query.getResultList();
    }
     
    @GET
    @Path("findBySignupDate/{passwordHash}")
    @Produces({"application/json"})
     public List<Credential> findBySignupDate(@PathParam("signupDate") Date
    signupDate) {
    Query query = em.createNamedQuery("Credential.findBySignupDate");
    query.setParameter("signupDate", signupDate);
    return query.getResultList();
    }
     
    //3.3
    @GET 
    @Path("findByusernameandSurname/{userName}/{userSurname}") 
    @Produces({"application/json"}) 
    public List<Credential> findByFirstnameANDSurnamed(@PathParam("userName") String userName, @PathParam("userSurname") String userSurname) { 
        TypedQuery<Credential> q = em.createQuery("SELECT c FROM Credential c WHERE UPPER(c.userTable.userName) ="
                + " UPPER(:userName) AND UPPER(c.userTable.userSurname) = UPPER(:userSurname)", Credential.class);
        q.setParameter("userName", userName); 
        q.setParameter("userSurname", userSurname); 
        return q.getResultList(); 
    }
    //3.4
    @GET 
    @Path("findByFirstnameANDSurnames/{userName}/{userSurname}") 
    @Produces({"application/json"}) 
    public List<Credential> findByFirstnameANDSurnames(@PathParam("userName") String userName, @PathParam("userSurname") String userSurname) { 
        Query query = em.createNamedQuery("Credential.findByFirstnameANDSurnames");
        query.setParameter("userName", userName); 
        query.setParameter("userSurname", userSurname); 
        return query.getResultList(); 
    }
    
    //4.1
    @GET
    @Path("findburnedperstep/{userId}")
    @Produces({"application/json"})
    public Object findByuseridca(@PathParam("userId") Integer userId) {
        TypedQuery<Object[]> q = em.createQuery("SELECT c.userTable.stepsPerMile,c.userTable.weight FROM Credential c"
                + "WHERE c.userTable.userId = :userId", Object[].class);
        q.setParameter("userId", userId);
        List<Object[]> queryList = q.getResultList();
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        Double ca = 0.0;
        for (Object[] row : queryList) {
            ca =((long)row[1]*0.49)/(long)row[0];
            
        }
        JsonObject personObject = Json.createObjectBuilder().add("calories burned per step",ca).build();
        arrayBuilder.add(personObject);
        JsonArray jArray = arrayBuilder.build();
        return jArray;
    }
    //4.2
    @GET
    @Path("findBMR/{userId}")
    @Produces({"application/json"})
    public Object findByuseridgender(@PathParam("userId") Integer userId) throws ParseException {
        TypedQuery<Object[]> q = em.createQuery("SELECT c.userTable.weight,c.userTable.height,c.userTable.gender,"
                + "c.userTable.dob FROM Credential c WHERE c.userTable.userId = :userId", Object[].class);
        q.setParameter("userId", userId);
        List<Object[]> queryList = q.getResultList();
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Double bmr = 0.0;
        String gender;
        for (Object[] row : queryList) {
            String date1 = formatter.format(row[3]);
            java.util.Date date= formatter.parse(date1); 
            gender = (String)row[2];
            Calendar cal = Calendar.getInstance();
            int yearNow = cal.get(Calendar.YEAR);
            cal.setTime(date);
            int yearBirth = cal.get(Calendar.YEAR);
            int age = yearNow - yearBirth;
            if(gender.equals("M"))
            {
            
                bmr = (13.75*(long)row[0])+(5.003*(long)row[1])-(6.755*age)+66.5;
            }
            else
                bmr = (9.563*(long)row[0])+(1.85*(long)row[1])-(4.676*age)+655.1;
                
    
        }
        JsonObject personObject = Json.createObjectBuilder().add("BMR",bmr).build();
        arrayBuilder.add(personObject);
        JsonArray jArray = arrayBuilder.build();
        return jArray;
    }
    
    //4。3
    @GET
    @Path("findetotaldailycaloriesburned/{userId}")
    @Produces({"application/json"})
    public Object findetotaldailycaloriesburned(@PathParam("userId") Integer userId) throws ParseException {
        TypedQuery<Object[]> q = em.createQuery("SELECT c.userTable.levelOfActivity,c.userTable.height FROM Credential c"
                + " WHERE c.userTable.userId = :userId", Object[].class);
        q.setParameter("userId", userId);
        List<Object[]> queryList = q.getResultList();
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        Double tc = 0.0;
        Double bmr = 0.0;
       // bmr = (Double) findByuseridgender(userId);
        JsonArray jArray1 =(JsonArray)findByuseridgender(userId);
        String a = jArray1.toString();
        a = a.replace("}]", " ");
        a = a.replace("[{\"BMR\":", " ");
        a = a.trim();

        bmr = Double.valueOf(a);
        
        
        //[{"BMR":1427.368}]
        
        for (Object[] row : queryList) {
            String level =(String)row[0];
            if(level.equals("1")){tc =bmr*1.2;}
            else if(level.equals("2")){tc =bmr*1.375;}
            else if(level.equals("3")){tc =bmr*1.55;}
            else if(level.equals("4")){tc =bmr*1.725;}
            else if(level.equals("5")){tc =bmr*1.9;}
            
            
        }
        JsonObject personObject = Json.createObjectBuilder().add("totaldailycaloriesburned",tc).build();
        arrayBuilder.add(personObject);
        JsonArray jArray = arrayBuilder.build();
        return jArray;
    }
    
    
    
    
    
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
