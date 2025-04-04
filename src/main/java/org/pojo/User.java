package org.pojo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class User {

    /**
     * Имя пользователя
     */
    @Builder.Default
    private String name = "Коллега";

    /**
     * Работа пользователя
     */
    @Builder.Default
    private String job = "Доцент";
}
