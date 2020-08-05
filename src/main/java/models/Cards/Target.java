package models.Cards;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
public enum Target {
    FRIENDLY_MINION,
    FRIENDLY_HERO,
    ENEMY_MINION,
    ENEMY_HERO,
}
