package ru.andrey.kvstorage;

import ru.andrey.kvstorage.console.DatabaseCommand;
import ru.andrey.kvstorage.console.DatabaseCommandResult;
import ru.andrey.kvstorage.console.ExecutionEnvironment;
import ru.andrey.kvstorage.exception.DatabaseException;

public class DatabaseServer {

    private final ExecutionEnvironment env;

    public DatabaseServer(ExecutionEnvironment env) {
        this.env = env;
    }

    public static void main(String[] args) {

    }

    DatabaseCommandResult executeNextCommand(String commandText) {
        if (commandText == null) {
            return DatabaseCommandResult.error("Command text is null");
        }

        String[] arguments = commandText.split(" ");
        boolean commandCorrect = false;

        for (var c : DatabaseCommands.values()) {
            if (c.name().equals(arguments[0])) {
                commandCorrect = true;
                break;
            }
        }

        if (!commandCorrect) {
            return DatabaseCommandResult.error("Command does not exist");
        }

        try {
            return DatabaseCommands.valueOf(arguments[0]).getCommand(env, arguments).execute();
        } catch (DatabaseException dbEx) {
            return DatabaseCommandResult.error(dbEx.getMessage());
        }
    }
}
