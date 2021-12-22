DROP TABLE IF EXISTS TODO;

CREATE TABLE TODO (
                      "TODO_ID" VARCHAR2 (50 CHAR) NOT NULL,
                      "TITLE" VARCHAR2 (50 CHAR) NOT NULL,
                      "SELECTED" BOOLEAN NOT NULL,
                      PRIMARY KEY ("TODO_ID")
);