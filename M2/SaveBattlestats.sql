CREATE OR REPLACE PROCEDURE saveBattleStats (
    p_resource_metal_acquired IN NUMBER,
    p_resource_deuterion_acquired IN NUMBER
)
AS
    v_planet_id NUMBER;
BEGIN
   
    SELECT MAX(planet_id) INTO v_planet_id FROM Planet_stats;
    
    INSERT INTO Battle_stats (
        planet_id, resource_metal_acquired, resource_deuterion_acquired
    ) VALUES (
        v_planet_id, p_resource_metal_acquired, p_resource_deuterion_acquired
    );
    COMMIT;
END;
/
