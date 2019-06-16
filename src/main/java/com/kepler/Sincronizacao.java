package com.kepler;

import javax.annotation.Resource;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.naming.InitialContext;
import javax.persistence.*;
import javax.sql.DataSource;
import javax.transaction.UserTransaction;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import static javax.transaction.Transactional.TxType.REQUIRED;


@Path("/sincronizacao")
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })
public class Sincronizacao {

    @PersistenceContext(unitName = "testejtsoracle")
    private EntityManager emTestejtsoracle;

    @PersistenceContext(unitName = "testejtsmssql")
    private EntityManager emTestejtsmssql;

    @Resource
    UserTransaction userTran;

    @GET
    public Response sincronizaBases() {


        return Response.ok("sincronizaBases " + (new Date()).toString()).build();
    }


    @GET
    @Path("/emtestejtsoracle")
    public Response emtestejtsoracle() {
        return Response.ok("emtestejtsoracle " + emTestejtsoracle).build();
    }

    @GET
    @Path("/emtestejtsoracle2")
    public Response emtestejtsoracle2() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("testejtsoracle");
        return Response.ok("emtestejtsoracle " + emf.createEntityManager()).build();
    }

    @GET
    @Path("/emtestejtsmssql")
    public Response emtestejtsmssql() {
        return Response.ok("emtestejtsmssql " + emTestejtsmssql).build();
    }

    @GET
    @Path("/emtestejtsmssql2")
    public Response emtestejtsmssql2() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("testejtsmssql");
        return Response.ok("emtestejtsoracle " + emf.createEntityManager()).build();
    }

    @GET
    @Path("/conexaotestejtsoracle")
    public Response conexaotestejtsoracle() {
        return Response.ok("conexaotestejtsoracle " + criaTestejtsoracle()).build();
    }

    @GET
    @Path("/conexaotestejtsmssql")
    public Response conexaotestejtsmssql() {
        return Response.ok("conexaotestejtsmssql " + criaTestejtsmssql()).build();
    }

    @GET
    @Path("clienteoracle")
    public Response getClienteOracle() {
        TypedQuery<ClienteOracle> query = emTestejtsoracle.createQuery("select c from ClienteOracle c", ClienteOracle.class);
        List<ClienteOracle> listaCliente = query.getResultList();
        return Response.ok(listaCliente).build();
    }

    @GET
    @Path("clientemssql")
    public Response getClienteMSSQL() {
        TypedQuery<ClienteMS> query = emTestejtsmssql.createQuery("select c from ClienteMS c", ClienteMS.class);
        List<ClienteMS> listaCliente = query.getResultList();
        return Response.ok(listaCliente).build();
    }


    @GET
    @Path("mix")
    public Response mix() throws  Exception {
        return processMix();
    }

    @GET
    @Path("luck")
    public Response luck() {
        Algo algo = new Algo();
        return Response.ok(algo.sayHello()).build();
    }

//    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    private Response processMix() throws Exception {
        userTran.begin();
        Random r = new Random();
        Mix mix = new Mix();
        TypedQuery<ClienteOracle> queryOracle = emTestejtsoracle.createQuery("select c from ClienteOracle c", ClienteOracle.class);
        mix.listaClienteOracle = queryOracle.getResultList();
        TypedQuery<ClienteMS> queryMSSQL = emTestejtsmssql.createQuery("select c from ClienteMS c", ClienteMS.class);
        mix.listaClienteMSSQL = queryMSSQL.getResultList();

        for (int i=0; i<1000; i++) {
            if (r.nextFloat() < 0.5) {
                System.out.print("Oracle");
                ClienteOracle c = mix.listaClienteOracle.get(r.nextInt(mix.listaClienteOracle.size()));
                c.setVersao(c.getVersao() + r.nextInt(10));
                emTestejtsoracle.persist(c);
            }
            else {
                System.out.print("MSSQL");
                ClienteMS c = mix.listaClienteMSSQL.get(r.nextInt(mix.listaClienteMSSQL.size()));
                c.setVersao(c.getVersao() + r.nextInt(10));
                emTestejtsmssql.persist(c);
            }
        }
        userTran.commit();

        return Response.ok(mix).build();
    }


    public static class Mix {
        public List<ClienteOracle> listaClienteOracle = new ArrayList<ClienteOracle>();
        public List<ClienteMS> listaClienteMSSQL = new ArrayList<ClienteMS>();
    }

    private Connection criaTestejtsoracle() {
        Connection connection = null;
        try {
            InitialContext context;
            context = new InitialContext();
            DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc/testejtsoracle");
            connection  = dataSource.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    private Connection criaTestejtsmssql() {
        Connection connection = null;
        try {
            InitialContext context;
            context = new InitialContext();
            DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc/testejtsmssql");
            connection  = dataSource.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

}



