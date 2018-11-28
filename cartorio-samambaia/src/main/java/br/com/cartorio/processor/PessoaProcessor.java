/**
 * 
 */
package br.com.cartorio.processor;

import org.apache.http.HttpEntity;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;

import br.com.cartorio.entity.Pessoa;
import br.com.cartorio.entity.RetornoIdentificarPessoa;
import br.com.cartorio.http.request.HttpRequestApi;

/**
 * @author vitor
 *
 */
@Component
public class PessoaProcessor {
	
	@Autowired
	private HttpRequestApi httpRequestApi;
	
	public RetornoIdentificarPessoa retornoSalvarPessoa(Pessoa pessoa, MultipartFile fileUpload) {

		RetornoIdentificarPessoa retornoApi = null;

		try {
			pessoa.setImagem(fileUpload.getBytes());
			HttpEntity entity = httpRequestApi.salvarPessoaRequest(pessoa);
			
			if (entity != null) {
				
				Gson gson = new Gson();
				String jsonString = EntityUtils.toString(entity).trim();
				retornoApi = gson.fromJson(jsonString, RetornoIdentificarPessoa.class);

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return retornoApi;
	}
	

}
