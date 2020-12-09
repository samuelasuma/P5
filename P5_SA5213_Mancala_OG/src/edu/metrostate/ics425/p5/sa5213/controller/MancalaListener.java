package edu.metrostate.ics425.p5.sa5213.controller;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import edu.metrostate.ics425.p5.sa5213.model.Mancala;

/**
 * Application Lifecycle Listener implementation class MancalaListener
 *
 */
@WebListener
public class MancalaListener implements HttpSessionListener {

    /**
     * Default constructor. 
     */
    public MancalaListener() {
       
    }

	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent se)  { 
         se.getSession().setAttribute("cala", new Mancala());
         se.getSession().getServletContext().log("Session Created");
    }

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent se)  { 
         // TODO Auto-generated method stub
    }
	
}
