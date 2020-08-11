package server.models.board;

public enum Side {
    FRIENDLY{
        @Override
        public Side getOpposite() {
            return ENEMY;
        }
    },
    ENEMY {
        @Override
        public Side getOpposite() {
            return FRIENDLY;
        }
    };

    public abstract Side getOpposite();
}
