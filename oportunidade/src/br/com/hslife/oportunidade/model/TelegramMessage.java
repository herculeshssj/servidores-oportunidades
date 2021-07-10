package br.com.hslife.oportunidade.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class TelegramMessage {
    
    Long chat_id;
    String text;
}
