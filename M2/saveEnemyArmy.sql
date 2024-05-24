CREATE OR REPLACE PROCEDURE saveEnemyArmy (
    p_planet_id IN NUMBER,
    p_num_battle IN NUMBER,
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
BEGIN
    INSERT INTO Enemy_army (
        planet_id, num_battle, light_hunter_threat, light_hunter_destroyed,
        heavy_hunter_threat, heavy_hunter_destroyed, battleship_threat, battleship_destroyed,
        armored_ship_threat, armored_ship_destroyed
    ) VALUES (
        p_planet_id, p_num_battle, p_light_hunter_threat, p_light_hunter_destroyed,
        p_heavy_hunter_threat, p_heavy_hunter_destroyed, p_battleship_threat, p_battleship_destroyed,
        p_armored_ship_threat, p_armored_ship_destroyed
    );
    COMMIT;
END;
/
