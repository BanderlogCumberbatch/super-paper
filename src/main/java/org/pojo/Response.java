package org.pojo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Response {

    /**
     * Id пользователя
     */
    private String id;

    /**
     * Имя пользователя
     */
    private String job;

    /**
     * Работа пользователя
     */
    private String name;

    /**
     * Дата создания
     */
    private String creationAt;
}
