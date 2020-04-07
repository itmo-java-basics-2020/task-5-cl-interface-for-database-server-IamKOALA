package ru.andrey.kvstorage;

import ru.andrey.kvstorage.console.*;

public enum DatabaseCommands {
    CREATE_DATABASE {
        @Override
        DatabaseCommand getCommand(ExecutionEnvironment env, String[] arguments) {
            return new CreateDatabaseCommand(env, arguments);
        }
    },
    CREATE_TABLE {
        @Override
        DatabaseCommand getCommand(ExecutionEnvironment env, String[] arguments) {
            return new CreateTableCommand(env, arguments);
        }
    },
    UPDATE_KEY {
        @Override
        DatabaseCommand getCommand(ExecutionEnvironment env, String[] arguments) {
            return new UpdateKeyCommand(env, arguments);
        }
    },
    READ_KEY {
        @Override
        DatabaseCommand getCommand(ExecutionEnvironment env, String[] arguments) {
            return new ReadKeyCommand(env, arguments);
        }
    };

    abstract DatabaseCommand getCommand(ExecutionEnvironment env, String[] arguments);
}