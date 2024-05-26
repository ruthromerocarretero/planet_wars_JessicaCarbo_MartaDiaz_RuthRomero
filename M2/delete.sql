CREATE OR REPLACE PROCEDURE deleteAllBattleData AS
BEGIN

    DELETE FROM Battle_log;
    DELETE FROM Planet_battle_defense;
    DELETE FROM Planet_battle_army;
    DELETE FROM Enemy_army;
    DELETE FROM Battle_stats;
    DELETE FROM Planet_stats;
    COMMIT;
END deleteAllBattleData;
/
