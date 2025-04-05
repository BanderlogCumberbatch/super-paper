package org.pojo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Response {

    /**
     * Дополнительная информация
     */
    private Addition addition;

    /**
     * ID сущности
     */
    private int id;

    /**
     * "Важные" номера
     */
    int[] important_numbers;

    /**
     * Заголовок сущности
     */
    private String title;

    /**
     * Верифицирован
     */
    private Boolean verified;
}
