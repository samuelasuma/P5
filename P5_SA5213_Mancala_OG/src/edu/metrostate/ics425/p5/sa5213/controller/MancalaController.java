package edu.metrostate.ics425.p5.sa5213.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.metrostate.ics425.p5.sa5213.model.Mancala;

/**
 * Servlet implementation class MancalaController
 */
@WebServlet("/MancalaController")
public class MancalaController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MancalaController() {
		super();
	
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		var cala = (Mancala) request.getSession(true).getAttribute("cala");

		String position = request.getParameter("pit_index");
		String reset = request.getParameter("reset");
		System.out.println(reset +"");
		if (reset != null) {
			request.getSession().invalidate();
			
		}

		int pos = 0;
		if (position != null) {
			pos = Integer.parseInt(position);

			cala.moveStones(pos);

			request.setAttribute("pits", cala.getPits());

			request.setAttribute("currentplayer", cala.getCurrentPlayer(pos));
			log(cala.winner(cala.gameOver(cala.getPits())) + " ");
			
		}
		request.getRequestDispatcher("index.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);

	}

}
