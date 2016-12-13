package ru.phone.ishop.message;


import java.util.List;

public interface MessageManager {

    long addMessage(Message message);

    List<Message> listMessages (long productId);



}
