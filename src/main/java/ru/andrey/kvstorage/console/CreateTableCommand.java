package ru.andrey.kvstorage.console;

import ru.andrey.kvstorage.exception.DatabaseException;
import ru.andrey.kvstorage.logic.Database;

import java.lang.management.OperatingSystemMXBean;
import java.util.Optional;

public class CreateTableCommand implements DatabaseCommand {
    ExecutionEnvironment env;
    String[] arguments;

    public CreateTableCommand(ExecutionEnvironment env, String[] arguments) {
        this.env = env;
        this.arguments = arguments;
    }

    @Override
    public DatabaseCommandResult execute() throws DatabaseException {
        if(arguments.length != 3) {
            return DatabaseCommandResult.error("Bad arguments");
        }

        Optional<Database> db = env.getDatabase(arguments[1]);
        String tableName = arguments[2];

        if(db.isPresent()) {
            db.get().createTableIfNotExists(tableName);
            return DatabaseCommandResult.success("Table was created");
        }

        return DatabaseCommandResult.error("No database");
    }
}
