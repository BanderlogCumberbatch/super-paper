package org.pojo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Entity {

    /**
     * Дополнительная информация
     */
    @Builder.Default
    private Addition addition = Addition.builder().additional_info("Дополнительные сведения").additional_number(123).build();

    /**
     * "Важные" номера
     */
    @Builder.Default
    int[] important_numbers  = new int[] {42, 87, 15};;

    /**
     * Заголовок сущности
     */
    @Builder.Default
    private String title = "Заголовок сущности";

    /**
     * Верификация
     */
    @Builder.Default
    private Boolean verified = true;
}
