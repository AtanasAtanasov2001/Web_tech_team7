ALTER TABLE game_state
    DROP FOREIGN KEY IF EXISTS state_player_id;
DROP INDEX IF EXISTS game_state_player_id_idx ON game_state;
ALTER TABLE game_state
    DROP COLUMN IF EXISTS player_id;