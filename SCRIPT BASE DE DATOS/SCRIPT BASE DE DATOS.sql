-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2018-08-11 18:56:09.529

-- tables
-- Table: Asistente
CREATE TABLE Asistente (
    IdAsistente int  NOT NULL,
    NombreAsistente varchar(100)  NOT NULL,
    ApellidoAsistente varchar(100)  NOT NULL,
    CelularAsistente char(10)  NOT NULL,
    CONSTRAINT Asistente_pk PRIMARY KEY  (IdAsistente)
);

-- Table: Boleto
CREATE TABLE Boleto (
    IdBoleto int  NOT NULL,
    OrigenBoleto varchar(50)  NOT NULL,
    DestinoBoleto varchar(50)  NOT NULL,
    FechaViajeBoleto char(10)  NOT NULL,
    CostoBoleto char(10)  NOT NULL,
    HoraPartida char(10)  NOT NULL,
    Asistente_IdAsistente int  NOT NULL,
    Pasajero_IdPasajero int  NOT NULL,
    Usuario_IdUsuario int  NOT NULL,
    Bus_IdBus int  NOT NULL,
    Chofer_IdChofer int  NOT NULL,
    CONSTRAINT Boleto_pk PRIMARY KEY  (IdBoleto)
);

-- Table: Bus
CREATE TABLE Bus (
    IdBus int  NOT NULL,
    PlacaBus char(10)  NOT NULL,
    TelefonoBus char(7)  NOT NULL,
    CONSTRAINT Bus_pk PRIMARY KEY  (IdBus)
);

-- Table: Chofer
CREATE TABLE Chofer (
    IdChofer int  NOT NULL,
    NombreChofer varchar(50)  NOT NULL,
    ApellidoChofer varchar(50)  NOT NULL,
    TelefonoChofer char(10)  NOT NULL,
    Asistente_IdAsistente int  NOT NULL,
    CONSTRAINT Chofer_pk PRIMARY KEY  (IdChofer)
);

-- Table: Pasajero
CREATE TABLE Pasajero (
    IdPasajero int  NOT NULL,
    NombrePasajero varchar(100)  NOT NULL,
    ApellidoPasajero varchar(100)  NOT NULL,
    DniPasajero char(8)  NOT NULL,
    CONSTRAINT Pasajero_pk PRIMARY KEY  (IdPasajero)
);

-- Table: Usuario
CREATE TABLE Usuario (
    IdUsuario int  NOT NULL,
    NombreUsuario varchar(100)  NOT NULL,
    ApellidoUsuario varchar(100)  NOT NULL,
    CelularUsuario char(9)  NOT NULL,
    CONSTRAINT Usuario_pk PRIMARY KEY  (IdUsuario)
);

-- foreign keys
-- Reference: Boleto_Asistente (table: Boleto)
ALTER TABLE Boleto ADD CONSTRAINT Boleto_Asistente
    FOREIGN KEY (Asistente_IdAsistente)
    REFERENCES Asistente (IdAsistente);

-- Reference: Boleto_Bus (table: Boleto)
ALTER TABLE Boleto ADD CONSTRAINT Boleto_Bus
    FOREIGN KEY (Bus_IdBus)
    REFERENCES Bus (IdBus);

-- Reference: Boleto_Chofer (table: Boleto)
ALTER TABLE Boleto ADD CONSTRAINT Boleto_Chofer
    FOREIGN KEY (Chofer_IdChofer)
    REFERENCES Chofer (IdChofer);

-- Reference: Boleto_Pasajero (table: Boleto)
ALTER TABLE Boleto ADD CONSTRAINT Boleto_Pasajero
    FOREIGN KEY (Pasajero_IdPasajero)
    REFERENCES Pasajero (IdPasajero);

-- Reference: Boleto_Usuario (table: Boleto)
ALTER TABLE Boleto ADD CONSTRAINT Boleto_Usuario
    FOREIGN KEY (Usuario_IdUsuario)
    REFERENCES Usuario (IdUsuario);

-- Reference: Chofer_Asistente (table: Chofer)
ALTER TABLE Chofer ADD CONSTRAINT Chofer_Asistente
    FOREIGN KEY (Asistente_IdAsistente)
    REFERENCES Asistente (IdAsistente);

-- End of file.

