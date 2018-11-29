/**
 * 
 */
package br.com.cartorio.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.cartorio.entity.Pessoa;
import br.com.cartorio.http.request.HttpRequestApi;

/**
 * @author vitor
 *
 */
@WebServlet(urlPatterns = {"/produto/imagens"})
public class ImageServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5980852761293692565L;
	
	@Autowired
	private HttpRequestApi httpResquestApi;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String id = request.getParameter("id");
			
			Pessoa pessoa = httpResquestApi.encontraPorIdRequest(Long.valueOf(id));

			byte[] content = pessoa.getImagem();
			response.setContentType("image/jpeg");
	        response.getOutputStream().write(content); 
		}catch (Exception e) {
			e.printStackTrace();
		}
	
	}

}