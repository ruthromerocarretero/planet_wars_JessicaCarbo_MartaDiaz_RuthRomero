CREATE OR REPLACE PROCEDURE savePlanetStat (
    name VARCHAR2,
    p_resource_metal_amount IN NUMBER,
    p_resource_deuterion_amount IN NUMBER,
    p_technology_defense_level IN NUMBER,
    p_technology_attack_level IN NUMBER,
    p_battles_counter IN NUMBER,
    p_missile_launcher_remaining IN NUMBER,
    p_ion_cannon_remaining IN NUMBER,
    p_plasma_cannon_remaining IN NUMBER,
    p_light_hunter_remaining IN NUMBER,
    p_heavy_hunter_remaining IN NUMBER,
    p_battleship_remaining IN NUMBER,
    p_armored_ship_remaining IN NUMBER
)
AS
BEGIN
    INSERT INTO Planet_stats (
        name, resource_metal_amount, resource_deuterion_amount,
        technology_defense_level, technology_attack_level, battles_counter,
        missile_launcher_remaining, ion_cannon_remaining, plasma_cannon_remaining,
        light_hunter_remaining, heavy_hunter_remaining, battleship_remaining,
        armored_ship_remaining
    ) VALUES (
        name, p_resource_metal_amount, p_resource_deuterion_amount,
        p_technology_defense_level, p_technology_attack_level, p_battles_counter,
        p_missile_launcher_remaining, p_ion_cannon_remaining, p_plasma_cannon_remaining,
        p_light_hunter_remaining, p_heavy_hunter_remaining, p_battleship_remaining,
        p_armored_ship_remaining
    );
    COMMIT;
END;
/

