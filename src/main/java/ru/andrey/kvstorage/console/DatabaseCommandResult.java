package ru.andrey.kvstorage.console;

import java.util.Optional;

public interface DatabaseCommandResult {

    Optional<String> getResult();

    DatabaseCommandStatus getStatus();

    boolean isSuccess();

    String getErrorMessage();

    enum DatabaseCommandStatus {
        SUCCESS, FAILED
    }

    static DatabaseCommandResult success(String result) {
        return new DatabaseCommandResultClass(true, result);
    }

    static DatabaseCommandResult error(String message) {
        return new DatabaseCommandResultClass(false, message);
    }

    public class DatabaseCommandResultClass implements DatabaseCommandResult {
        boolean result;
        String message;

        private DatabaseCommandResultClass(boolean success, String message) {
            this.result = success;
            this.message = message;
        }

        @Override
        public Optional<String> getResult() {
            return result ? Optional.of(message) : Optional.empty();
        }

        @Override
        public DatabaseCommandStatus getStatus() {
            return result ? DatabaseCommandStatus.SUCCESS : DatabaseCommandStatus.FAILED;
        }

        @Override
        public boolean isSuccess() {
            return result;
        }

        @Override
        public String getErrorMessage() {
            return message;
        }
    }
}