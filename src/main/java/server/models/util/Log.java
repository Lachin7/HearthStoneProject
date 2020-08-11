package server.models.util;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Log {
    @Id
    @Getter
    @Setter
    private long time;
    @Getter
    @Setter
    private long id;
    @Getter
    @Setter
    private String log;

    public Log(long id, String log) {
        time = System.currentTimeMillis();
        this.id = id;
        this.log = log;
    }

    public Log() {
    }
}
