/*primero creamos la conexion en oracle*/
/*nombre BD : PlanetWars
user  = planet
contraseña = 12345*/*/
/*codigo para tener permisos para hacer modificaciones*/

alter session set "_ORACLE_SCRIPT"=true;


CREATE USER planet IDENTIFIED BY 12345;
GRANT ALL PRIVILEGES TO PlanetWars;

COMMIT;



/*sequencias*/
CREATE SEQUENCE planet_id_seq START WITH 1;
CREATE SEQUENCE num_battle_seq START WITH 1;

/*-Crear tablas*/
CREATE TABLE Planet_stats (
    planet_id NUMBER DEFAULT planet_id_seq.NEXTVAL PRIMARY KEY,
    name VARCHAR2(100),
    resource_metal_amount NUMBER,
    resource_deuterion_amount NUMBER,
    technology_defense_level NUMBER,
    technology_attack_level NUMBER,
    battles_counter NUMBER,
    missile_launcher_remaining NUMBER,
    ion_cannon_remaining NUMBER,
    plasma_cannon_remaining NUMBER,
    light_hunter_remaining NUMBER,
    heavy_hunter_remaining NUMBER,
    battleship_remaining NUMBER,
    armored_ship_remaining NUMBER
);

CREATE TABLE Battle_stats (
    num_battle NUMBER DEFAULT num_battle_seq.NEXTVAL PRIMARY KEY,
    planet_id NUMBER NOT NULL,
    resource_metal_acquired NUMBER,
    resource_deuterion_acquired NUMBER,
    FOREIGN KEY (planet_id) REFERENCES Planet_stats(planet_id),
    UNIQUE (planet_id, num_battle) -- Añadir clave única compuesta
);

CREATE TABLE Battle_log (
    num_battle NUMBER DEFAULT num_battle_seq.NEXTVAL PRIMARY KEY,
    planet_id NUMBER NOT NULL,
    log_entry VARCHAR2(100),
    FOREIGN KEY (planet_id) REFERENCES Planet_stats(planet_id),
    FOREIGN KEY (num_battle) REFERENCES Battle_stats(num_battle)
);

CREATE TABLE Planet_battle_defense (
    planet_id NUMBER NOT NULL,
    num_battle NUMBER NOT NULL,
    missile_launcher_built NUMBER,
    missile_launcher_destroyed NUMBER,
    ion_cannon_built NUMBER,
    ion_cannon_destroyed NUMBER,
    plasma_cannon_built NUMBER,
    plasma_cannon_destroyed NUMBER,
    PRIMARY KEY (planet_id, num_battle),
    FOREIGN KEY (planet_id, num_battle) REFERENCES Battle_stats(planet_id, num_battle)
);

CREATE TABLE Planet_battle_army (
    planet_id NUMBER NOT NULL,
    num_battle NUMBER NOT NULL,
    light_hunter_built NUMBER,
    light_hunter_destroyed NUMBER,
    heavy_hunter_built NUMBER,
    heavy_hunter_destroyed NUMBER,
    battleship_built NUMBER,
    battleship_destroyed NUMBER,
    armored_ship_built NUMBER,
    armored_ship_destroyed NUMBER,
    PRIMARY KEY (planet_id, num_battle),
    FOREIGN KEY (planet_id, num_battle) REFERENCES Battle_stats(planet_id, num_battle)
);

CREATE TABLE Enemy_army (
    planet_id NUMBER NOT NULL,
    num_battle NUMBER NOT NULL,
    light_hunter_threat NUMBER,
    light_hunter_destroyed NUMBER,
    heavy_hunter_threat NUMBER,
    heavy_hunter_destroyed NUMBER,
    battleship_threat NUMBER,
    battleship_destroyed NUMBER,
    armored_ship_threat NUMBER,
    armored_ship_destroyed NUMBER,
    PRIMARY KEY (planet_id, num_battle),
    FOREIGN KEY (planet_id, num_battle) REFERENCES Battle_stats(planet_id, num_battle)
);

COMMIT;
