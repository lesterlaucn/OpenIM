/**
 * Created by lesterlaucn
 */

package com.lesterlaucn.chatboot.client.protoBuilder;

import com.lesterlaucn.chatboot.client.client.ClientSession;
import com.lesterlaucn.chatboot.protoc.ChatMsg;
import com.lesterlaucn.chatboot.protoc.UserDTO;
import com.lesterlaucn.chatboot.protoc.message.ProtoMsg;

/**
 * 聊天消息Builder
 */

public class ChatMsgBuilder extends BaseBuilder
{


    private ChatMsg chatMsg;
    private UserDTO user;


    public ChatMsgBuilder(ChatMsg chatMsg, UserDTO user, ClientSession session)
    {
        super(ProtoMsg.HeadType.MESSAGE_REQUEST, session);
        this.chatMsg = chatMsg;
        this.user = user;

    }


    public ProtoMsg.Message build()
    {
        ProtoMsg.Message message = buildCommon(-1);
        ProtoMsg.MessageRequest.Builder cb
                = ProtoMsg.MessageRequest.newBuilder();

        chatMsg.fillMsg(cb);
        return message
                .toBuilder()
                .setMessageRequest(cb)
                .build();
    }

    public static ProtoMsg.Message buildChatMsg(
            ChatMsg chatMsg,
            UserDTO user,
            ClientSession session)
    {
        ChatMsgBuilder builder =
                new ChatMsgBuilder(chatMsg, user, session);
        return builder.build();

    }
}