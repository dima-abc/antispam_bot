package my.antispam.service;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SpamAnalysisTest {
    private final SpamAnalysis spamAnalysis = new SpamAnalysis();

    @Test
    void isSpamOneTrue() {
        final String text = "Нужны люди на удалённый заработок ! \n18+ \n"
                + "Заработок возможен с любых устройств\n"
                + "От 175$ в день\n"
                + "Пишите + в ЛС";
        boolean result = spamAnalysis.isSpam(text);
        assertThat(result).isTrue();
    }

    @Test
    void isSpamTwoTrue() {
        final String text = "Зaрaбoтoк oт нескoльких тысяч рублей кaждый день. "
                + "Рaбoтa удaлённo, легaльнo, "
                + "без oпытa, нет влoжений. Нужнo выпoлнять не слoжные зaдaния в бoте в TG\n\n"
                + "Узнaть детaли: нaйдите в пoиске rabota_382";
        boolean result = spamAnalysis.isSpam(text);
        assertThat(result).isTrue();
    }

    @Test
    void isSpamThreeTrue() {
        final String text = "\uD83D\uDE80Готов поднимать 100-200 $ каждый день?\n\n"
                + "\uD83D\uDED1БЕСПЛАТНОЕ обучение.\n\n"
                + "\uD83D\uDD36Занятость до 2-х часов в сутки\n\n"
                + "✋\uD83C\uDFFBОТ 18 ЛЕТ\n\n"
                + "\uD83D\uDC8EХочешь к нам? Пиши мне \uD83D\uDC49 @glass_koln";
        assertThat(spamAnalysis.isSpam(text)).isTrue();
    }

    @Test
    void isSpamFourTrue() {
        final String text = "Приглашаю желающих освоить возможности "
                + "хорошего дополнительного заработка на удаленке. "
                + "Опыт не обязателен, важны лишь желание и возраст от 20 лет. "
                + "Детали в личных сообщениях.";
        assertThat(spamAnalysis.isSpam(text)).isTrue();
    }

    @Test
    void isSpamFiveTrue() {
        final String text = "\uD83D\uDC4BПредлагаю возможность стабильного и удалённого зароботка!\n"
                + "\uD83D\uDCB5Поднимаем каждый день более 200$\n\n"
                + "\uD83D\uDD36  Не имеет значения, есть ли у вас опыт, мы научим БЕСПЛАТНО!\n"
                + "✋\uD83C\uDFFB18+\n\n"
                + "\uD83D\uDCE8\uD83D\uDCACИнтересует? Пиши! \uD83D\uDC49 @helix_oka";
        assertThat(spamAnalysis.isSpam(text)).isTrue();
    }

    @Test
    void isSpamSixTrue() {
        final String text = "Здравствуйте. \n"
                + "Мы набираем партнёров в команду для взаимовыгодного сотрудничества.\n"
                + "Возраст от 20-ти лет, неполная занятость, ежедневный доход.\n"
                + "Заинтересованных жду в л.с.";
        assertThat(spamAnalysis.isSpam(text)).isTrue();
    }

    @Test
    void isSpamSevenTrue() {
        final String text = "НУЖНЫ ЛЮДИ ДЛЯ УДАЛЕННОЙ РАБОТЫ\n"
                + "Возраст от 18+ \n"
                + "Доход от $200 в день \n"
                + "1-2 часа времени в день\n"
                + "Пиши в ЛС";
        assertThat(spamAnalysis.isSpam(text)).isTrue();
    }

    @Test
    void isSpamEightTrue() {
        final String text = "Добрый день. Извиняюсь что не по теме, может кто то трейдингом занимается? "
                + "Хочу поделиться полезным материалом по трейдингу) "
                + "Вдруг кому то будет интересно, пишите в личку)";
        assertThat(spamAnalysis.isSpam(text)).isTrue();
    }

    @Test
    void isSpamNineTrue() {
        final String text = "Ищу людей, возьму 2-3 человека 18+ \n"
                + "Удаленная деятельность \n"
                + "От 250$  в  день\n"
                + "Кому интересно: Пишите + в лс";
        assertThat(spamAnalysis.isSpam(text)).isTrue();
    }

    @Test
    void isSpamTenTrue() {
        final String text = "Готов обучить aрбитражу \n"
                + "Меж-биржeвой и внyтри-биpжевой apбитраж \n"
                + "Опeрации прoизводите на своём личном aккаунте \n"
                + "Не беру дaнные и срeдства под свoё упрaвление \n"
                + "Пpибыль получаете также на свой кoшелёк\n"
                + "Беру только прoцент от вaшей чистой пpибыли, oплата прoцентов любым удобным способом\n"
                + "По всем вопросам жду в личных сообщениях";
        assertThat(spamAnalysis.isSpam(text)).isTrue();
    }

    @Test
    void isSpamElevenTrue() {
        final String text = "\uD83D\uDCF2 Рaбoтa - oт 5 тыс. р ежедневнo. Рaбoтa пo интернет, "
                + "без нaрушения зaкoнa, oпыт не требуется, без влoжений. "
                + "Нужны люди, чтoбы делaть лёгкие зaдaния в бoте в TG\n\n"
                + "Пoлучить инфoрмaцию: нaйдите в пoиске rabota_382";
        assertThat(spamAnalysis.isSpam(text)).isTrue();
    }
}