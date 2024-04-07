package my.antispam.service;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SpamAnalysis {
    private final Set<String> jobSynonym = Set.of(
            "работа", "рaбoтa", "заработок",
            "зaрaбoтoк", "поднимать", "заработка", "зароботка",
            "заработать", "работу", "доход",
            "трейдинг", "трейдингу", "трейдингом",
            "деятельность", "деятельностью",
            "aрбитражу", "aрбитраж"
    );

    private final Set<String> messageSynonym = Set.of(
            "пиши", "напиши", "напишите", "пишите", "лс",
            "детали", "детaли", "сообщениях", "пoиске"
    );

    public boolean isSpam(String text) {
        final Set<String> lex = Arrays.stream(text
                        .replace(".", "")
                        .replace(",", " ")
                        .replace("+", " ")
                        .replace("!", " ")
                        .replace("?", " ")
                        .replace(":", " ")
                        .replace("\n", " ")
                        .toLowerCase()
                        .split(" "))
                .collect(Collectors.toSet());
        final boolean job = containsAny(lex, jobSynonym);
        final boolean message = containsAny(lex, messageSynonym);
        return job && message;
    }

    private <T> boolean containsAny(Set<T> lex, Set<T> synonym) {
        return lex.stream()
                .anyMatch(synonym::contains);
    }
}
