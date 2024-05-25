CREATE OR REPLACE PROCEDURE SaveBattleLog (
    p_planet_id IN NUMBER,
    p_log_entry IN VARCHAR2
)
AS
    v_num_battle NUMBER;
BEGIN
    SELECT MAX(num_battle) INTO v_num_battle FROM Battle_Stats;

    INSERT INTO Battle_log (
        planet_id, num_battle, log_entry
    ) VALUES (
        p_planet_id, v_num_battle, p_log_entry
    );
    COMMIT;
END;
/
