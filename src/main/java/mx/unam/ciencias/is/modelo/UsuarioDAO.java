/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.unam.ciencias.is.modelo;

import java.util.List;
import java.util.ArrayList;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author jonh
 */
public class UsuarioDAO {
     //Atributo privado para conectarse con la base de datos
    private SessionFactory sessionFactory;
    
    /**
     * Inicializa la sesión para conectarse con la base de datos
     * @param sessionFactory 
     */
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    /**
     * Método que guarda un usuario en la base de datos
     * @param usuario
     */
    public void guardar(Usuario usuario) {
    
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
           tx = session.beginTransaction();
         
           session.persist(usuario);
           
           tx.commit();
        }
        catch (Exception e) {
           if (tx!=null){ 
               tx.rollback();
           }
           e.printStackTrace(); 
        }finally {
           session.close();
        }
    
    }
    
    
    /**
     * Método que elimina a un usuario de la base de datos
     * @param usuario 
     */
    public void eliminar(Usuario usuario) {
    
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
           tx = session.beginTransaction();
         
           session.delete(usuario);
           
           tx.commit();
        }
        catch (Exception e) {
           if (tx!=null){ 
               tx.rollback();
           }
           e.printStackTrace(); 
        }finally {
           session.close();
        }
    
    }
    /**
     * Método que regresa a un usuario, cuyo id es el que se pasa como 
     * parámetro
     * @param idUsuario
     * @return 
     */
    public Usuario getUsuario(long idUsuario) {
        Usuario persona = null;
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
           tx = session.beginTransaction();
           persona = (Usuario)session.get(Usuario.class, idUsuario);
           tx.commit();
        }
        catch (Exception e) {
           if (tx!=null){ 
               tx.rollback();
           }
           e.printStackTrace(); 
        }finally {
           session.close();
        }
        return persona;
    }
   
    /**
     * Método que regresa un usuario cuyo correo se pasa como parámetro
     * @param correo
     * @return 
     */
    public Usuario getUsuario(String correo) {
        Usuario result = null;
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String hql = " from Usuario where correo = :correo";
            Query query = session.createQuery(hql);
            query.setParameter("correo", correo);
            result = (Usuario)query.uniqueResult();
            tx.commit();
        }
        catch (Exception e) {
           if (tx!=null){
               tx.rollback();
           }
           e.printStackTrace(); 
        }finally {
           session.close();
        }
        return result;
    }
    
    
    
     public List<Usuario> obtenerListaUsuario() {
        
            
           
             List<Usuario> result = null;
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String hql = " from Usuario";
            Query query = session.createQuery(hql);
            result = (List<Usuario>)query.list();
            tx.commit();
            
            
            
            
            
            
            
            
           
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return result;
    }
     
     
     
     
     
     
     public Usuario eliminarUsuario(String user) {
        Usuario us = new Usuario();
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
           tx = session.beginTransaction();
         
            String hql = "  from Usuario where correo = :user";
            Query query = session.createQuery(hql);
            query.setParameter("user", user);
            us = (Usuario)query.uniqueResult();
       
           
           
       session.delete(us);
          // session.getTransaction().commit();
            tx.commit();
           
        }
        catch (Exception e) {
           if (tx!=null){ 
               tx.rollback();
           }
           e.printStackTrace(); 
        }finally {
           session.close();
        }
        return us;
    }
    
    
    

}
