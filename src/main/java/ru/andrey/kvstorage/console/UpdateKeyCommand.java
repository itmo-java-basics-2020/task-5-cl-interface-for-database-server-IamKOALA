package ru.andrey.kvstorage.console;

import ru.andrey.kvstorage.exception.DatabaseException;
import ru.andrey.kvstorage.logic.Database;

import java.util.Optional;

public class UpdateKeyCommand implements DatabaseCommand {
    ExecutionEnvironment env;
    String[] arguments;

    public UpdateKeyCommand(ExecutionEnvironment env, String[] arguments) {
        this.env = env;
        this.arguments = arguments;
    }

    @Override
    public DatabaseCommandResult execute() throws DatabaseException {
        if(arguments.length != 5) {
            return DatabaseCommandResult.error("Bad arguments");
        }

        Optional<Database> db = env.getDatabase(arguments[1]);
        String  tableName = arguments[2],
                key = arguments[3],
                value = arguments[4];

        if(db.isPresent()) {
            db.get().write(tableName, key, value);
            return DatabaseCommandResult.success("UpdCommand succeed");
        }

        return DatabaseCommandResult.error("No database");
    }
}
