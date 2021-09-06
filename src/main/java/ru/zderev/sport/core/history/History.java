package ru.zderev.sport.core.history;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "app_history")
@NoArgsConstructor
public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String  name;

    private String firstData;

    private String lastData;

    private Long teamId;

    private Long sportTypeId;

}
