package ru.andrey.kvstorage.console;

import ru.andrey.kvstorage.exception.DatabaseException;

public class CreateDatabaseCommand implements DatabaseCommand {
    private ExecutionEnvironment env;
    private String[] arguments;

    public CreateDatabaseCommand(ExecutionEnvironment env, String[] arguments) {
        this.env = env;
        this.arguments = arguments;
    }

    @Override
    public DatabaseCommandResult execute() throws DatabaseException {
        env.addDatabase(null);

        return DatabaseCommandResult.success("Db was created");
    }
}
