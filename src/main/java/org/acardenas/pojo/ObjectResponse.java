package org.acardenas.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ObjectResponse<T> {
    private String path;
    private Integer status;
    private T data;
}
