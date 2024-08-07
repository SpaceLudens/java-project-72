package hexlet.code.model;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class Url {
    private int id;
    private String name;
    private Timestamp createdAt;
}
