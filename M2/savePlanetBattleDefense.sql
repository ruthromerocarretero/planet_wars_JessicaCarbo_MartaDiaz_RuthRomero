CREATE OR REPLACE PROCEDURE savePlanetBattleDefense (
    p_planet_id IN NUMBER,
    p_num_battle IN NUMBER,
    p_missile_launcher_built IN NUMBER,
    p_missile_launcher_destroyed IN NUMBER,
    p_ion_cannon_built IN NUMBER,
    p_ion_cannon_destroyed IN NUMBER,
    p_plasma_cannon_built IN NUMBER,
    p_plasma_cannon_destroyed IN NUMBER
)
AS
BEGIN
    INSERT INTO Planet_battle_defense (
        planet_id, num_battle, missile_launcher_built, missile_launcher_destroyed,
        ion_cannon_built, ion_cannon_destroyed, plasma_cannon_built, plasma_cannon_destroyed
    ) VALUES (
        p_planet_id, p_num_battle, p_missile_launcher_built, p_missile_launcher_destroyed,
        p_ion_cannon_built, p_ion_cannon_destroyed, p_plasma_cannon_built, p_plasma_cannon_destroyed
    );
    COMMIT;
END;
/
