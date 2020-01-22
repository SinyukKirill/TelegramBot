package com.bot.context;

import com.bot.service.BotService;
import com.bot.model.Bot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.ApiContext;
import org.telegram.telegrambots.meta.TelegramBotsApi;


@Configuration
public class Context {

    @Bean
    public Logger logger() {
        return LoggerFactory.getLogger("appLogger");
    }

    @Bean
    public DefaultBotOptions getDefaultBotOptions() {
        ApiContextInitializer.init();
        DefaultBotOptions botOptions = ApiContext.getInstance(DefaultBotOptions.class);
        String PROXY_HOST = "198.27.75.152";
        int PROXY_PORT = 1080;
        botOptions.setProxyHost(PROXY_HOST);
        botOptions.setProxyPort(PROXY_PORT);
        botOptions.setProxyType(DefaultBotOptions.ProxyType.SOCKS5);
        return botOptions;
    }

    @Bean
    public BotService getBotService() {
        return new BotService();
    }

    @Bean
    public Bot getInstance(DefaultBotOptions botOptions) {
        return new Bot(botOptions);
    }

    @Bean
    public TelegramBotsApi getBotsApi() {
        return new TelegramBotsApi();
    }
}