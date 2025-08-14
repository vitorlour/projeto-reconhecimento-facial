package br.com.projeto.azure.conexao;

import com.azure.ai.vision.face.FaceAdministrationClient;
import com.azure.ai.vision.face.FaceAdministrationClientBuilder;
import com.azure.ai.vision.face.FaceClient;
import com.azure.ai.vision.face.FaceClientBuilder;
import com.azure.core.credential.KeyCredential;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConexaoAzure {

    @Value("${azure.subscription-key}")
    private String subscriptionKey;

    @Value("${azure.endpoint}")
    private String endpoint;

    public FaceClient conectaAzure() {
        return new FaceClientBuilder()
                .endpoint(endpoint)
                .credential(new KeyCredential(subscriptionKey))
                .buildClient();
    }

    public FaceAdministrationClient conectaAzureAdmin() {
        return new FaceAdministrationClientBuilder()
                .endpoint(endpoint)
                .credential(new KeyCredential(subscriptionKey))
                .buildClient();
    }
}
