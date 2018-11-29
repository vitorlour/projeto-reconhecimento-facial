/**
 * 
 */
package br.com.cartorio.http.request;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import br.com.cartorio.constants.Constantes;
import br.com.cartorio.entity.Pessoa;
import br.com.cartorio.entity.PessoasIdentificadas;
import br.com.cartorio.entity.RetornoIdentificarPessoa;
import br.com.cartorio.util.GsonHelper;

/**
 * @author vitor
 *
 */
@Component
public class HttpRequestApi {

	public HttpEntity salvarPessoaRequest(Pessoa pessoa) {

		HttpEntity entity = null;

		try {
			HttpClient httpclient = new DefaultHttpClient();
			URIBuilder builder = new URIBuilder(Constantes.URI_SALVAR_PESSOA);

			URI uri = builder.build();
			HttpPost request = new HttpPost(uri);

			request.setHeader("Content-Type", "application/json;charset=UTF-8");
			Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

			String jsonInString = gson.toJson(pessoa);

			StringEntity reqEntity = new StringEntity(jsonInString, "UTF-8");

			request.setEntity(reqEntity);

			HttpResponse response = httpclient.execute(request);
			entity = response.getEntity();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return entity;
	}

	public Pessoa[] encontratodosRequest() {

		Pessoa[] pessoa = null;

		try {

			HttpClient httpclient = new DefaultHttpClient();
			URIBuilder builder = new URIBuilder(Constantes.URI_PESSOA_ENCONTRAR_TODOS);
			URI uri = builder.build();
			HttpGet request = new HttpGet(uri);

			HttpResponse response = httpclient.execute(request);

			String jsonString = EntityUtils.toString(response.getEntity()).trim();

			pessoa = GsonHelper.customGson.fromJson(jsonString, Pessoa[].class);

		} catch (JsonSyntaxException j) {
			j.printStackTrace();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return pessoa;
	}

	public Pessoa encontraPorIdRequest(Long id) {

		Pessoa pessoa = null;

		try {

			HttpClient httpclient = new DefaultHttpClient();
			URIBuilder builder = new URIBuilder(Constantes.URI_PESSOA_ENCONTRAR_POR_ID + id);
			URI uri = builder.build();
			HttpGet request = new HttpGet(uri);

			request.setHeader("Content-Type", "application/json");

			HttpResponse response = httpclient.execute(request);
			String jsonString = EntityUtils.toString(response.getEntity()).trim();

			pessoa = GsonHelper.customGson.fromJson(jsonString, Pessoa.class);

		} catch (JsonSyntaxException j) {
			j.printStackTrace();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return pessoa;
	}

	public void deleteRequest(Long id) {

		try {

			HttpClient httpclient = new DefaultHttpClient();
			URIBuilder builder = new URIBuilder(Constantes.URI_PESSOA_DELETE + id);
			URI uri = builder.build();
			HttpDelete request = new HttpDelete(uri);

			request.setHeader("Content-Type", "application/json");

			httpclient.execute(request);

		} catch (JsonSyntaxException j) {
			j.printStackTrace();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public List<PessoasIdentificadas> reconhecimentoFacial(Pessoa pessoa) {

		List<PessoasIdentificadas> identificadas = new ArrayList<PessoasIdentificadas>();

		RetornoIdentificarPessoa retorno = null;

		try {
			HttpClient httpclient = new DefaultHttpClient();
			URIBuilder builder = new URIBuilder(Constantes.URI_AZURE_RECONHECIMENTO);

			URI uri = builder.build();
			HttpPost request = new HttpPost(uri);

			request.setHeader("Content-Type", "application/json");

			Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

			String jsonInString = gson.toJson(pessoa);

			StringEntity reqEntity = new StringEntity(jsonInString);

			request.setEntity(reqEntity);

			HttpResponse response = httpclient.execute(request);
			String jsonString = EntityUtils.toString(response.getEntity()).trim();

			retorno = GsonHelper.customGson.fromJson(jsonString, RetornoIdentificarPessoa.class);

			if (retorno != null) {
				for (PessoasIdentificadas pessoasIdentificadas : retorno.getIdentificarPessoa()) {
					identificadas.add(pessoasIdentificadas);
				}
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return identificadas;
	}

}
