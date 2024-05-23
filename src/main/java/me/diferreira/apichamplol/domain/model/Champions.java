package me.diferreira.apichamplol.domain.model;

public record Champions(
        Long id,
        String name,
        String role,
        String lore,
        String imageUrl
) {
    public  String generateContextQuestion(String question){
        return """
                Pergunta: %s
                Nome do Campeão: %s
                Função: %s
                Lore(História): %s
                """.formatted(question,this.name, this.role,this.lore);
    }
}
