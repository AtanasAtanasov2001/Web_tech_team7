
CREATE TABLE game
(
    id           UUID       NOT NULL,
    white_id     BIGINT(20) NOT NULL,
    black_id     BIGINT(20) NOT NULL,
    time_created DATETIME   NOT NULL DEFAULT NOW(),
    time_ended   DATETIME,
    winner_id    BIGINT(20),

    PRIMARY KEY (id),

    CONSTRAINT game_white_id FOREIGN KEY (white_id) REFERENCES customer (id),
    CONSTRAINT game_black_id FOREIGN KEY (black_id) REFERENCES customer (id),
    CONSTRAINT game_winner_id FOREIGN KEY (winner_id) REFERENCES customer (id),

    INDEX game_white_id_idx (white_id),
    INDEX game_black_id_idx (black_id),
    INDEX game_winner_id_idx (winner_id)
);

CREATE TABLE game_state
(
    id           UUID         NOT NULL,
    game_id      UUID         NOT NULL,
    player_id    BIGINT(20)   NOT NULL,
    state        VARCHAR(128) NOT NULL,
    time_updated DATETIME     NOT NULL DEFAULT NOW(),

    PRIMARY KEY (id),

    CONSTRAINT state_game_id_fk FOREIGN KEY (game_id) REFERENCES game (id),
    CONSTRAINT state_player_id FOREIGN KEY (player_id) REFERENCES customer (id),

    INDEX game_state_game_id_idx (game_id),
    INDEX game_state_player_id_idx (player_id)
);