package org.example;

public enum MoveCommands {

    LEFT("1"), UP("2"), RIGHT("3"), DOWN("4"), GIVE_UP("9");

    private String number;

    MoveCommands(String number) {
        this.number = number;
    }

    public static MoveCommands getCommand(String number) {
        System.out.println(number);
        for (MoveCommands cmd : MoveCommands.values()) {
            if (cmd.number.equals(number)) {
                return cmd;
            }
        }
        return null;
    }
}
