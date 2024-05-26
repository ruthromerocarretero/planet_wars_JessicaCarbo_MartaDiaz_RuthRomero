CREATE OR REPLACE PROCEDURE SaveBattleLog (
    p_log_entry IN VARCHAR2
)
AS
    v_num_battle NUMBER;
    v_p_planet_id NUMBER;
BEGIN
  
    SELECT num_battle, planet_id INTO v_num_battle, v_p_planet_id
    FROM (
        SELECT num_battle, planet_id
        FROM Battle_Stats
        ORDER BY num_battle DESC
    )
    WHERE ROWNUM = 1;


    INSERT INTO Battle_log (
        planet_id, num_battle, log_entry
    ) VALUES (
        v_p_planet_id, v_num_battle, p_log_entry
    );



EXCEPTION
    WHEN NO_DATA_FOUND THEN
        DBMS_OUTPUT.PUT_LINE('Error: No se encontraron registros en Battle_Stats.');
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
END;
/
