package com.bot.commands.commonCommands;

import com.bot.commands.PlannerBaseCommand;
import com.bot.model.Action;
import com.bot.service.ProductCommonCommandService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;

@Slf4j
@Component
public class DeleteCommand extends PlannerBaseCommand {

    @Autowired
    ProductCommonCommandService productCommonCommandService;

    public DeleteCommand() {
        super("delete", "(удалить трату)\nАтибуты:\n &lt;category&gt; &lt;price&gt; ");
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] arguments) {
        log.info("BotUser {}, id: {}, chat: {} is trying to execute '{}'.", user.getUserName(), user.getId(), chat.getId(), getCommandIdentifier());

        StringBuilder message = new StringBuilder();

        if(botService.hasAccessToCommands(user.getId())){
            productCommonCommandService.commonAction(arguments, user, message, Action.DELETE);
            sendMsg(absSender, user, chat, message.toString(), null);
        }
    }
}
