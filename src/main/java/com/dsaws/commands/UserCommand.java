package com.dsaws.commands;

import com.dsaws.DontSpeakAbsWordsMirai;
import com.dsaws.common.entity.Result;
import com.dsaws.common.entity.Translation;
import com.dsaws.service.FuzzyService;
import com.dsaws.service.SelectService;
import net.mamoe.mirai.console.command.CommandContext;
import net.mamoe.mirai.console.command.CommandSender;
import net.mamoe.mirai.console.command.CommandSenderKt;
import net.mamoe.mirai.console.command.java.JSimpleCommand;
import net.mamoe.mirai.contact.Group;

import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@SuppressWarnings("all")
public class UserCommand extends JSimpleCommand {

    public static final UserCommand INSTANCE = new UserCommand();
    private UserCommand(){
        super(DontSpeakAbsWordsMirai.INSTANCE,"query");
        setDescription("查询抽象词");
        setPrefixOptional(true);
    }

    @Handler
    public void help(CommandContext context){
        CommandSender sender = context.getSender();
        Group groupOrNull = CommandSenderKt.getGroupOrNull(sender);
        if (groupOrNull != null){
            groupOrNull.sendMessage("指令集:\n" +
                                    "query <word> 查询词条\n" +
                                    "query fuzzy <word> 模糊查询\n"+
                                    "query ? 查询指令\n");
        }
    }

    @Handler
    public void select(CommandContext context, String arg){
        if ("?".equals(arg)){
            CommandSender sender = context.getSender();
            Group groupOrNull = CommandSenderKt.getGroupOrNull(sender);
            if (groupOrNull != null){
                groupOrNull.sendMessage("指令集:\n" +
                        "query <word> 查询词条\n" +
                        "query fuzzy <word> 模糊查询\n"+
                        "query ? 查询指令\n");
            }
            return;
        }
        HashMap<String, String> param = new HashMap<>();
        param.put("word",arg);
        Result result = null;
        ExecutorService pool = Executors.newFixedThreadPool(1);
        SelectService service = new SelectService(param);

        try {
            Future<Result> submit = pool.submit(service);
            result = submit.get(1000*5, TimeUnit.MILLISECONDS);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            pool.shutdown();
        }

        CommandSender sender = context.getSender();
        Group group = CommandSenderKt.getGroupOrNull(sender);
        if (group == null){
            System.out.println("Group is Empty");
        }else {
            if (result != null){
                if (result.getCode() != 0){
                    group.sendMessage("Code: "+ result.getCode()+"\n"
                            +"Info: " + result.getMessage());
                }else {
                    StringBuilder sb = new StringBuilder();
                    Translation[] data = result.getData();
                    for (int i = 0; i < data.length; i++) {
                        sb.append("Transl: "+data[i].getTranslation() +
                                "| Likes: "+data[i].getLikes()+ "\n");
                    }
                    group.sendMessage("========结果========\n"+
                            "Word: " + data[0].getWord()+"\n"+
                            "===================\n"+
                            sb+
                            "===================");
                }
                System.out.println("查询到: "+ result);
            }else{
                group.sendMessage("查询超时，请稍后重试");
                System.out.println("查询超时");
            }
        }
    }

    @Handler
    public void fuzzyQuery(CommandContext context, String subCommand, String arg){
        if (!"fuzzy".equals(subCommand)){
            CommandSender sender = context.getSender();
            Group groupOrNull = CommandSenderKt.getGroupOrNull(sender);
            if (groupOrNull != null){
                groupOrNull.sendMessage("指令集:\n" +
                        "query <word> 查询词条\n" +
                        "query fuzzy <word> 模糊查询\n"+
                        "query ? 查询指令\n");
            }
            return;
        }
        HashMap<String, String> param = new HashMap<>();
        param.put("word",arg);
        Result result = null;
        ExecutorService pool = Executors.newFixedThreadPool(1);
        FuzzyService service = new FuzzyService(param);

        try {
            Future<Result> submit = pool.submit(service);
            result = submit.get(1000*5, TimeUnit.MILLISECONDS);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            pool.shutdown();
        }

        CommandSender sender = context.getSender();
        Group groupOrNull = CommandSenderKt.getGroupOrNull(sender);
        if (groupOrNull != null){
            if (result != null){
                if (result.getCode() != 0){
                    groupOrNull.sendMessage("Code: "+ result.getCode()+"\n"
                            +"Info: " + result.getMessage());
                }else {
                    Translation[] data = result.getData();
                    HashSet<String> strings = new HashSet<>();
                    for (Translation datum : data) {
                        strings.add(datum.getWord());
                    }
                    StringBuilder sb = new StringBuilder();
                    for (String string : strings) {
                        sb.append(string+"\n");
                    }

                    groupOrNull.sendMessage("========结果========\n"+
                            "FuzzyWord: " + arg+"\n"+
                            "===查询到的相关词条===\n"+
                            sb+"\n"+
                            "(如果想查询词条的具体翻译请使用query <word>)\n"+
                            "===================");
                }
            }
        }
    }
}
