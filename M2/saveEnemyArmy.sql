CREATE OR REPLACE PROCEDURE saveEnemyArmy (
    p_light_hunter_threat IN NUMBER,
    p_light_hunter_destroyed IN NUMBER,
    p_heavy_hunter_threat IN NUMBER,
    p_heavy_hunter_destroyed IN NUMBER,
    p_battleship_threat IN NUMBER,
    p_battleship_destroyed IN NUMBER,
    p_armored_ship_threat IN NUMBER,
    p_armored_ship_destroyed IN NUMBER
)
AS
    v_battle_planet_id NUMBER;
    v_num_battle NUMBER;
BEGIN
    SELECT MAX(planet_id), MAX(num_battle)  INTO v_battle_planet_id, v_num_battle
    FROM Battle_stats
    WHERE rownum = 1;

    INSERT INTO Enemy_army (
        planet_id, num_battle, light_hunter_threat, light_hunter_destroyed,
        heavy_hunter_threat, heavy_hunter_destroyed, battleship_threat, battleship_destroyed,
        armored_ship_threat, armored_ship_destroyed
    ) VALUES (
        v_battle_planet_id, v_num_battle, p_light_hunter_threat, p_light_hunter_destroyed,
        p_heavy_hunter_threat, p_heavy_hunter_destroyed, p_battleship_threat, p_battleship_destroyed,
        p_armored_ship_threat, p_armored_ship_destroyed
    );
    COMMIT;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        DBMS_OUTPUT.PUT_LINE('Error: No se encontró el num_battle correspondiente en Battle_stats');
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
END;
/
