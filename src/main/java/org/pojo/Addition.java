package org.pojo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Addition {

    /**
     * Дополнительные сведения
     */
    private String additional_info;

    /**
     * Дополнительный номер
     */
    private int additional_number;

    /**
     * ID
     */
    private String id;
}
