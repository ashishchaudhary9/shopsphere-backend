package com.shopsphere.product.document;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Setter
@Getter
@AllArgsConstructor
@Data
@EqualsAndHashCode
@NoArgsConstructor
public class Category {
    private String id;
    private String name;
    private String description;

}
