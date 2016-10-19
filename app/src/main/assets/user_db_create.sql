CREATE TABLE pokemon_specie_catched (
  id INTEGER NOT NULL, 
  PRIMARY KEY (id)
);
CREATE TABLE pokemon_user (
  id INTEGER NOT NULL, 
  pokemon_id INTEGER NOT NULL, 
  "level" INTEGER,
  has_evolved INTEGER,
  ability_id INTEGER, 
  move1_id INTEGER, 
  move2_id INTEGER, 
  move3_id INTEGER, 
  move4_id INTEGER, 
  PRIMARY KEY (id),
  CHECK (has_evolved IN(0,1))
);
