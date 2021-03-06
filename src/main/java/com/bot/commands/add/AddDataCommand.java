package com.bot.commands.add;

import com.bot.commands.PlannerBaseCommand;
import com.bot.service.commandService.add.AddDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.bots.AbsSender;

@Slf4j
@Component
public class AddDataCommand extends PlannerBaseCommand {

    @Autowired
    private AddDataService addDataService;

    public AddDataCommand() {
        super("adddata", "(добавить трату)\nАтрибуты:\n &lt;category&gt; &lt;price&gt; &lt;description&gt;");
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] arguments) {
        log.info("BotUser {}, id: {}, chat: {} is trying to execute '{}'.", user.getUserName(), user.getId(), chat.getId(), getCommandIdentifier());
        StringBuilder message = new StringBuilder();

        InlineKeyboardMarkup keyboardMarkup = addDataService.addDataCommand(arguments, user, message);

        sendMsg(absSender, user, chat, message.toString(), keyboardMarkup);
    }


}
