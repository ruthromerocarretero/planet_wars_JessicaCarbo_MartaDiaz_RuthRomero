CREATE OR REPLACE PROCEDURE SaveBattleLog (
    p_planet_id IN NUMBER,
    p_num_battle IN NUMBER,
    p_log_entry IN VARCHAR2
)
AS
BEGIN
    INSERT INTO Battle_log (
        planet_id, num_battle, log_entry
    ) VALUES (
        p_planet_id, p_num_battle, p_log_entry
    );
    COMMIT;
END;
/
