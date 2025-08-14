package br.com.cartorio.http.request;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import br.com.cartorio.entity.Imagem;
import br.com.cartorio.entity.Pessoa;
import br.com.cartorio.entity.PessoasIdentificadas;
import br.com.cartorio.entity.RetornoIdentificarPessoa;
import br.com.cartorio.util.GsonHelper;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Component
public class HttpRequestApi {

    private static final Logger logger = LoggerFactory.getLogger(HttpRequestApi.class);

    @Value("${api.url.pessoa.salvar}")
    private String salvarPessoaUrl;

    @Value("${api.url.pessoa.encontrartodos}")
    private String encontrarTodosUrl;

    @Value("${api.url.pessoa.encontrarporid}")
    private String encontrarPorIdUrl;

    @Value("${api.url.pessoa.delete}")
    private String deleteUrl;

    @Value("${api.url.azure.reconhecimento}")
    private String reconhecimentoUrl;

    private final CloseableHttpClient httpClient;

    public HttpRequestApi() {
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(20);
        cm.setDefaultMaxPerRoute(20);

        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(5000)
                .setSocketTimeout(5000)
                .setConnectionRequestTimeout(5000)
                .build();

        this.httpClient = HttpClients.custom()
                .setConnectionManager(cm)
                .setDefaultRequestConfig(requestConfig)
                .build();
    }


    public HttpEntity salvarPessoaRequest(Pessoa pessoa) {
        try {
            URIBuilder builder = new URIBuilder(salvarPessoaUrl);
            URI uri = builder.build();
            HttpPost request = new HttpPost(uri);
            request.setHeader("Content-Type", "application/json;charset=UTF-8");

            Gson gson = (pessoa.getId() > 0) ? new Gson() : new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
            String jsonInString = gson.toJson(pessoa);
            StringEntity reqEntity = new StringEntity(jsonInString, "UTF-8");
            request.setEntity(reqEntity);

            HttpResponse response = httpClient.execute(request);
            return response.getEntity();
        } catch (Exception e) {
            logger.error("Error saving person", e);
        }
        return null;
    }

    public Pessoa[] encontratodosRequest() {
        try {
            URIBuilder builder = new URIBuilder(encontrarTodosUrl);
            URI uri = builder.build();
            HttpGet request = new HttpGet(uri);

            HttpResponse response = httpClient.execute(request);
            String jsonString = EntityUtils.toString(response.getEntity()).trim();
            return GsonHelper.customGson.fromJson(jsonString, Pessoa[].class);
        } catch (Exception e) {
            logger.error("Error finding all people", e);
        }
        return null;
    }

    public Pessoa encontraPorIdRequest(Long id) {
        try {
            URIBuilder builder = new URIBuilder(encontrarPorIdUrl + id);
            URI uri = builder.build();
            HttpGet request = new HttpGet(uri);
            request.setHeader("Content-Type", "application/json");

            HttpResponse response = httpClient.execute(request);
            String jsonString = EntityUtils.toString(response.getEntity()).trim();
            return GsonHelper.customGson.fromJson(jsonString, Pessoa.class);
        } catch (Exception e) {
            logger.error("Error finding person by id", e);
        }
        return null;
    }

    public void deleteRequest(Long id) {
        try {
            URIBuilder builder = new URIBuilder(deleteUrl + id);
            URI uri = builder.build();
            HttpDelete request = new HttpDelete(uri);
            request.setHeader("Content-Type", "application/json");
            httpClient.execute(request);
        } catch (Exception e) {
            logger.error("Error deleting person", e);
        }
    }

    public List<PessoasIdentificadas> reconhecimentoFacial(Imagem imagem) {
        List<PessoasIdentificadas> identificadas = new ArrayList<>();
        try {
            URIBuilder builder = new URIBuilder(reconhecimentoUrl);
            URI uri = builder.build();
            HttpPost request = new HttpPost(uri);
            request.setHeader("Content-Type", "application/json");

            Gson gson = new Gson();
            String jsonInString = gson.toJson(imagem);
            StringEntity reqEntity = new StringEntity(jsonInString);
            request.setEntity(reqEntity);

            HttpResponse response = httpClient.execute(request);
            String jsonString = EntityUtils.toString(response.getEntity()).trim();
            RetornoIdentificarPessoa retorno = GsonHelper.customGson.fromJson(jsonString, RetornoIdentificarPessoa.class);

            if (retorno != null && retorno.getIdentificarPessoa() != null) {
                identificadas.addAll(retorno.getIdentificarPessoa());
            }
        } catch (Exception e) {
            logger.error("Error in facial recognition", e);
        }
        return identificadas;
    }
}
