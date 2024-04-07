package my.antispam;

import my.antispam.service.SpamAnalysis;
import my.antispam.telgram.Receiver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@SpringBootApplication
public class AntispamBotApplication {

    @Bean
    public TelegramBotsApi telegramApi(@Value("${tg.name}") String name,
                                       @Value("${tg.token}") String token,
                                       SpamAnalysis spamAnalysis) throws TelegramApiException {
        Receiver receiver = new Receiver(name, token, spamAnalysis);
        var tg = new TelegramBotsApi(DefaultBotSession.class);
        tg.registerBot(receiver);
        return tg;
    }

    public static void main(String[] args) {
        SpringApplication.run(AntispamBotApplication.class, args);
    }
}
