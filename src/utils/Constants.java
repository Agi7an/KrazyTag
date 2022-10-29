package utils;

import main.Game;

public class Constants {

    public static class UI {
        public static class Buttons {
            public static final int B_WIDTH_DEFAULT = 140;
            public static final int B_HEIGHT_DEFAULT = 56;
            public static final int B_WIDTH = (int) (B_WIDTH_DEFAULT * Game.SCALE / 3);
            public static final int B_HEIGHT = (int) (B_HEIGHT_DEFAULT * Game.SCALE / 3);
        }

        public static class PauseButtons {
            public static final int SOUND_SIZE_DEFAULT = 42;
            public static final int SOUND_SIZE = (int) (SOUND_SIZE_DEFAULT * Game.SCALE / 3 - 5);
        }

        public static class URMButtons {
            public static final int URM_DEFAULT_SIZE = 56;
            public static final int URM_SIZE = (int) (URM_DEFAULT_SIZE * Game.SCALE / 3);
        }
    }

    public static class Directions {
        public static final int LEFT = 0;
        public static final int UP = 1;
        public static final int RIGHT = 2;
        public static final int DOWN = 3;
    }

    public static class PlayerConstants {
        public static final int IDLE = 0;
        public static final int RUN = 1;
        public static final int HIT = 2;
        public static final int DOUBLEJUMP = 3;
        public static final int WALLJUMP = 4;
        public static final int JUMP = 5;
        public static final int FALL = 6;

        public static int GetSpriteAmount(int playerAction) {
            switch (playerAction) {
                case IDLE:
                    return 11;
                case RUN:
                    return 12;
                case HIT:
                    return 7;
                case DOUBLEJUMP:
                    return 6;
                case WALLJUMP:
                    return 5;
                case JUMP:
                    return 1;
                case FALL:
                    return 1;
                default:
                    return 1;
            }
        }
    }
}
