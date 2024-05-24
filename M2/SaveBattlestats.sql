CREATE OR REPLACE PROCEDURE saveBattleStats (
    p_planet_id IN NUMBER,
    p_resource_metal_acquired IN NUMBER,
    p_resource_deuterion_acquired IN NUMBER
)
AS
BEGIN
    INSERT INTO Battle_stats (
        planet_id, resource_metal_acquired, resource_deuterion_acquired
    ) VALUES (
        p_planet_id, p_resource_metal_acquired, p_resource_deuterion_acquired
    );
    COMMIT;
END;
/
