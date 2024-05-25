CREATE OR REPLACE PROCEDURE savePlanetBattleDefense (
    p_num_battle IN NUMBER,
    p_missile_launcher_built IN NUMBER,
    p_missile_launcher_destroyed IN NUMBER,
    p_ion_cannon_built IN NUMBER,
    p_ion_cannon_destroyed IN NUMBER,
    p_plasma_cannon_built IN NUMBER,
    p_plasma_cannon_destroyed IN NUMBER
)
AS
    v_battle_planet_id NUMBER;
BEGIN
    SELECT planet_id INTO v_battle_planet_id
    FROM Battle_stats
    WHERE num_battle = p_num_battle;

    INSERT INTO Planet_battle_defense (
        planet_id, num_battle, missile_launcher_built, missile_launcher_destroyed,
        ion_cannon_built, ion_cannon_destroyed, plasma_cannon_built, plasma_cannon_destroyed
    ) VALUES (
        v_battle_planet_id, p_num_battle, p_missile_launcher_built, p_missile_launcher_destroyed,
        p_ion_cannon_built, p_ion_cannon_destroyed, p_plasma_cannon_built, p_plasma_cannon_destroyed
    );

    COMMIT;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        DBMS_OUTPUT.PUT_LINE('No se encontró un planet_id correspondiente al número de batalla.');
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
END;
/
