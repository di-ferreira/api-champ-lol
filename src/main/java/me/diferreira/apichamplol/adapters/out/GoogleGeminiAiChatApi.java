package me.diferreira.apichamplol.adapters.out;

import feign.FeignException;
import feign.RequestInterceptor;
import me.diferreira.apichamplol.domain.ports.GenerativeAiApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@ConditionalOnProperty(name = "generative-ai.provider", havingValue = "GEMINI")
@FeignClient(name = "geminiAiChatApi", url = "${generative-ai.gemini.base-url}")
public interface GoogleGeminiAiChatApi extends GenerativeAiApi {

    String url = "/v1beta/models/gemini-pro:generateContent?key=${gemini.api-key}";

    @PostMapping(url)
    GoogleGeminiResp textOnlyInput(GoogleGeminiReq req);

    @Override
    default String generateContent(String objective, String context) {

        String prompt = """
                %s
                %s
                """.formatted(objective, context);

        GoogleGeminiReq req = new GoogleGeminiReq(
                List.of(new Content(List.of(new Part(prompt))))
        );

        try {
            GoogleGeminiResp resp = textOnlyInput(req);
            return resp.candidates().getFirst().content().parts().getFirst().text();
        } catch (FeignException httpErrors) {
            return "Deu ruim! Erro de comunicação com a API do Google Gemini. \n error: %s".formatted(httpErrors.getMessage());
        } catch (Exception unexpectedError) {
            return "Deu mais ruim! O retorno da API do Google Gemini não contem os dados esperados.";
        }
    }

    record GoogleGeminiReq(List<Content> contents) {
    }

    record Content(List<Part> parts) {
    }

    record Part(String text) {
    }

    record GoogleGeminiResp(List<Candidate> candidates) {
    }

    record Candidate(Content content) {
    }

    class Config {
        @Bean
        public RequestInterceptor apiKeyRequestInterceptor(@Value("${gemini.api-key}") String apiKey) {
            return requestTemplate -> requestTemplate.query("key", apiKey);
        }
    }

}
