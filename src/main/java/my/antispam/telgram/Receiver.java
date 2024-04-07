package my.antispam.telgram;

import my.antispam.service.SpamAnalysis;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Receiver extends TelegramLongPollingBot {
    private final String botUserName;
    private final SpamAnalysis spamAnalysis;

    public Receiver(String botUserName, String botToken, SpamAnalysis spamAnalysis) {
        super(botToken);
        this.botUserName = botUserName;
        this.spamAnalysis = spamAnalysis;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            Message message = update.getMessage();
            if (spamAnalysis.isSpam(message.getText())) {
                SendMessage send = new SendMessage(
                        message.getChatId().toString(),
                        "Обнаружен спам. Сообщение будет удалено через 10 секунд.");
                send.setReplyToMessageId(message.getMessageId());
                try {
                    execute(send);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
                Executors.newSingleThreadExecutor().submit(() -> {
                    try {
                        TimeUnit.SECONDS.sleep(10);
                        execute(new DeleteMessage(message.getChatId().toString(),
                                message.getMessageId()));
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
                });
            }
        }
    }

    @Override
    public String getBotUsername() {
        return this.botUserName;
    }

}
