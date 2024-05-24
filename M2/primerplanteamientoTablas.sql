
CREATE TABLE Planet_stats (
	planet_id not null NUMBER PRIMARY KEY,
email VARCHAR2(100)
	resource_metal_amount int,
	resource_deuterion_amount int,
technology_defense_level number,
technology_atack_level number,
battles_counter number,

	
);

create table Battle_stats(
	
	foreign key(planet_id) references planet_id,
	num_battle not null number primary key,
	resource_metal_acquired number,
	resource_deuterion_acquired number,
);

create table Battle_log(
	foreign key(planet_id) references planet_id,
	foreign key(num_battle ) references num_battle ,
	num_line not null number primary key,
	log entry number
);

create table Planet_battle_defense(
	
	foreign key(planet_id) references planet_id,
foreign key(num_battle ) references num_battle,
missile_launcher_built number,
missile_launcher_destroyed int,
ion_cannon_built number,
missile_launcher_destroyed int,
plasma_canon_built number,
missile_launcher_destroyed number
);


create table Planet_battle_army(
	
foreign key(planet_id) references planet_id,
foreign key(num_battle ) references num_battle,
light_hunter_built number,
light_hunter_destroyed number,
heavy_hunter_built number,
heavy_hunter_destroyed,
battleship_built number,
battleship_built_destroyed number,
	armored_ship_built number,
armored_ship_destroyed number,
);


	
create table Enemy_army(

	foreign key(planet_id) references planet_id,
foreign key(num_battle ) references num_battle,
light_hunter_threat number,
light_hunter_destroyed number,
heavy_hunter_threat number,
heavy_hunter_destroyed number;
battleship_threat number;
battleship_destroyed number;
armored_ship_threat number,
armored_ship_destroyed number
);
