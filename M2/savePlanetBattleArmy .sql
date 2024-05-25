CREATE OR REPLACE PROCEDURE savePlanetBattleArmy (
    p_num_battle IN NUMBER,
    p_light_hunter_built IN NUMBER,
    p_light_hunter_destroyed IN NUMBER,
    p_heavy_hunter_built IN NUMBER,
    p_heavy_hunter_destroyed IN NUMBER,
    p_battleship_built IN NUMBER,
    p_battleship_destroyed IN NUMBER,
    p_armored_ship_built IN NUMBER,
    p_armored_ship_destroyed IN NUMBER
)
AS
    v_planet_id NUMBER;
BEGIN
    SELECT planet_id INTO v_planet_id
    FROM Battle_stats
    WHERE num_battle = p_num_battle;

    INSERT INTO Planet_battle_army (
        planet_id, num_battle, light_hunter_built, light_hunter_destroyed,
        heavy_hunter_built, heavy_hunter_destroyed, battleship_built, battleship_destroyed,
        armored_ship_built, armored_ship_destroyed
    ) VALUES (
        v_planet_id, p_num_battle, p_light_hunter_built, p_light_hunter_destroyed,
        p_heavy_hunter_built, p_heavy_hunter_destroyed, p_battleship_built, p_battleship_destroyed,
        p_armored_ship_built, p_armored_ship_destroyed
    );
    COMMIT;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        DBMS_OUTPUT.PUT_LINE('Error: No se encontró el num_battle correspondiente en Battle_stats');
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
END;
/

