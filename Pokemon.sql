CREATE DATABASE Pokemon;

CREATE TABLE Pokemon.Partida (
  `IdPartida` int(11) NOT NULL AUTO_INCREMENT,
  `Pokecuartos` int(11) NOT NULL,
  `Pociones` int(11) NOT NULL,
  `Pokeballs` int(11) NOT NULL,
  `NombreJugador` varchar(45) DEFAULT NULL,
  `GeneroJugador` varchar(45) DEFAULT NULL,
  `DiaJugador` int(11) DEFAULT NULL,
  `MesJugador` int(11) DEFAULT NULL,
  `AñoJugador` int(11) DEFAULT NULL,
  PRIMARY KEY (`IdPartida`)
);

CREATE TABLE Pokemon.Pokemon (
  `IdPokemon` int(11) NOT NULL AUTO_INCREMENT,
  `IdPartida` int(11) NOT NULL,
  `Nivel` int(11) NOT NULL,
  `Mote` varchar(45) NOT NULL,
  `Especie` varchar(45) NOT NULL,
  `PsActual` int(11) NOT NULL,
  PRIMARY KEY (`IdPokemon`),
  KEY `pokemon-partida_idx` (`IdPartida`),
  CONSTRAINT `pokemon-partida` FOREIGN KEY (`IdPartida`) REFERENCES `Partida` (`IdPartida`) ON DELETE CASCADE ON UPDATE CASCADE
) ;