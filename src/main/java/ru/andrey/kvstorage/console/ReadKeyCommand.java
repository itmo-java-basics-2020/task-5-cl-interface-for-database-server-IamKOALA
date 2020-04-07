package ru.andrey.kvstorage.console;

import ru.andrey.kvstorage.exception.DatabaseException;
import ru.andrey.kvstorage.logic.Database;

import java.util.Optional;

public class ReadKeyCommand implements DatabaseCommand{
    ExecutionEnvironment env;
    String[] arguments;

    public ReadKeyCommand(ExecutionEnvironment env, String[] arguments) {
        this.env = env;
        this.arguments = arguments;
    }

    @Override
    public DatabaseCommandResult execute() throws DatabaseException {
        if(arguments.length != 4) {
            return DatabaseCommandResult.error("Bad arguments");
        }

        Optional<Database> db = env.getDatabase(arguments[1]);
        String  tableName = arguments[2],
                key = arguments[3];

        if(db.isPresent()) {
            db.get().read(tableName, key);
            return DatabaseCommandResult.success("Key reading succeed");
        }

        return DatabaseCommandResult.error("No database");
    }
}
