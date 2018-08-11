------ VISTAS----------

/* Vista donde muestra los datos de la tabla Bus con relacion a la tabla Asistente */
CREATE VIEW VW_BUS
AS
    SELECT IdBus, PlacaBus, UPPER(NombreChoferBus) AS 'Nombre Chofer', UPPER(ApellidoChoferBus) AS 'Apellido Chofer', TelefonoBus,
        UPPER(CONCAT(Asistente.NombreAsistente,',',Asistente.ApellidoAsistente)) AS 'Asistente',
        Asistente.CelularAsistente
    FROM Bus
        INNER JOIN Asistente ON Bus.Asistente_IdAsistente = Asistente.IdAsistente
GO

/* Vista donde muestra los datos de la tabla Boleto con relacion a la tabla Pasajero, Usuario y Bus */
CREATE VIEW VW_BOLETO
AS
    SELECT IdBoleto, UPPER(OrigenBoleto) AS 'Origen', UPPER(DestinoBoleto) AS 'Destino',
        FORMAT(CONVERT(date,FechaViajeBoleto,103),'dd/MM/yyyy','en-gb') AS 'Fecha de Viaje', CostoBoleto, UPPER(HoraPartida) AS 'Partida',
        UPPER(CONCAT(Pasajero.NombrePasajero,',',Pasajero.ApellidoPasajero)) AS 'Pasajero',
        UPPER(CONCAT(Usuario.NombreUsuario,',',Usuario.ApellidoUsuario)) AS 'Usuario',
        UPPER(CONCAT(Bus.NombreChoferBus,',',bus.ApellidoChoferBus)) AS 'Chofer', Bus.PlacaBus, Bus.TelefonoBus
    FROM Boleto
        INNER JOIN Pasajero ON Boleto.Pasajero_IdPasajero = Pasajero.IdPasajero
        INNER JOIN Usuario ON Boleto.Usuario_IdUsuario = Usuario.IdUsuario
        INNER JOIN Bus ON Boleto.Bus_IdBus = Bus.IdBus
GO

-------- STORE PROCEDURE--------

/* SP que permite ingresar dato a la Tabla Pasajero */
CREATE PROCEDURE SP_PASAJERO_ADD
    @NOMBRE VARCHAR(100),
    @APELLIDO VARCHAR(100),
    @DNI CHAR(8)
AS
BEGIN
    INSERT INTO Pasajero
        (NombrePasajero,ApellidoPasajero,DniPasajero)
    VALUES
        (UPPER(@NOMBRE), UPPER(@APELLIDO), @DNI)
END
GO

/* SP que permite Modificar datos de la tabla Pasajero */
CREATE PROCEDURE SP_PASAJERO_UPDATE
    @CODIGO INT,
    @NOMBRE VARCHAR(100),
    @APELLIDO VARCHAR(100),
    @DNI CHAR(8)
AS
BEGIN
    UPDATE Pasajero
    SET NombrePasajero = @NOMBRE,
        ApellidoPasajero = @APELLIDO,
        DniPasajero = @DNI
    WHERE IdPasajero = @CODIGO
END
GO

/* SP que permite eliminar un registro de la tabla pasajero */
CREATE PROCEDURE SP_PASAJERO_DELETE
AS
BEGIN
    DELETE FROM Pasajero WHERE IdPasajero =''
END
GO

/* SP que permite ingresar registros a la tabla Asistente */
CREATE PROCEDURE SP_ASISTENTE_ADD
    @NOMBRE VARCHAR(100),
    @APELLIDO VARCHAR(100),
    @CELULAR CHAR(9)
AS
BEGIN
    INSERT INTO Asistente
        (NombreAsistente,ApellidoAsistente,CelularAsistente)
    VALUES
        (UPPER(@NOMBRE), UPPER(@APELLIDO), @CELULAR)
END
GO
EXEC SP_ASISTENTE_ADD @NOMBRE='ISAC',@APELLIDO='LUNA',@CELULAR='123456789'
GO
/* SP que permite modificar datos de la tabla Asistente */
CREATE PROCEDURE SP_ASISTENTE_UPDATE
    @CODIGO INT,
    @NOMBRE VARCHAR(100),
    @APELLIDO VARCHAR(100),
    @CELULAR CHAR(9)
AS
BEGIN
    UPDATE Asistente
SET NombreAsistente = @NOMBRE,
    ApellidoAsistente = @APELLIDO,
    CelularAsistente = @CELULAR
    WHERE IdAsistente = @CODIGO
END
GO

/* SP que permite eliminar un registro de la tabla Asistente */
CREATE PROCEDURE SP_ASISTENTE_DELETE
AS
BEGIN
    DELETE FROM Asistente WHERE IdAsistente ='7'
END
GO

/* SP que permite ingresar datos a la tabla Usuario */
CREATE PROCEDURE SP_USUARIO_ADD
    @NOMBRE VARCHAR(100),
    @APELLIDO VARCHAR(100),
    @CELULAR CHAR(9)
AS
BEGIN
    INSERT INTO Usuario
        (NombreUsuario,ApellidoUsuario,CelularUsuario)
    VALUES
        (UPPER(@NOMBRE), UPPER(@APELLIDO), @CELULAR)
END
GO

/* SP que permite Modificar datos de la tabla Usuario */
CREATE PROCEDURE SP_USUARIO_UPDATE
    @CODIGO INT,
    @NOMBRE VARCHAR(100),
    @APELLIDO VARCHAR(100),
    @CELULAR CHAR(9)
AS
BEGIN
    UPDATE Usuario
SET NombreUsuario = @NOMBRE,
    ApellidoUsuario = @APELLIDO,
    CelsularUsuario = @CELULAR
    WHERE IdUsuario = @CODIGO
END
GO

/* SP  que permite eliminar un registro de la tabla usuario */
CREATE PROCEDURE SP_USUARIO_DELETE
AS
BEGIN
    DELETE FROM Usuario WHERE IdUsuario = ''
END
GO

/* SP que permite ingresar datos a la tabla Bus */
CREATE PROCEDURE SP_BUS_ADD
    @PLACA CHAR(10),
    @NOMBRE VARCHAR(100),
    @TELEFONO CHAR(7),
    @APELLIDO VARCHAR(100),
    @ASISTENTE INT
AS
BEGIN
    INSERT INTO Bus
        (PlacaBus,NombreChoferBus,TelefonoBus,ApellidoChoferBus,Asistente_IdAsistente)
    VALUES
        (UPPER(@PLACA), UPPER(@NOMBRE), @TELEFONO, UPPER(@APELLIDO), @ASISTENTE)
END
GO

/* SP que permite modificar datos de la tabla Bus */
CREATE PROCEDURE SP_BUS_UPDATE
    @CODIGO INT,
    @PLACA CHAR(10),
    @NOMBRE VARCHAR(100),
    @TELEFONO CHAR(7),
    @APELLIDO VARCHAR(100),
    @ASISTENTE INT
AS
BEGIN 
UPDATE Bus
SET PlacaBus = @PLACA,
    NombreChoferBus = @NOMBRE,
    TelefonoBus = @TELEFONO,
    ApellidoChoferBus = @APELLIDO,
    Asistente_IdAsistente = @ASISTENTE
    WHERE IdBus = @CODIGO
END
GO 

/* SP que permite eliminar registro de la tabla Bus */
CREATE PROCEDURE SP_BUS_DELETE
AS
BEGIN 
DELETE FROM Bus WHERE IdBus = ''
END
GO

/* SP que permite ingresar datos a la tabla boleto */
CREATE PROCEDURE SP_BOLETO_ADD
    @ORIGEN VARCHAR(50),
    @DESTINO VARCHAR(50),
    @FECHA CHAR(10),
    @COSTO CHAR(10),
    @PARTIDA CHAR(10),
    @PASAJERO INT,
    @USUARIO INT,
    @BUS INT
AS
BEGIN 
INSERT INTO Boleto
(OrigenBoleto,DestinoBoleto,FechaViajeBoleto,CostoBoleto,HoraPartida,Pasajero_IdPasajero,Usuario_IdUsuario,Bus_IdBus)
VALUES
(UPPER(@ORIGEN),UPPER(@DESTINO),@FECHA,@COSTO,UPPER(@PARTIDA),@PASAJERO,@USUARIO,@BUS)
END
GO

/* SP que permite modificar datos de la tabla boleto */
CREATE PROCEDURE SP_BOLETO_UPDATE
    @CODIGO INT,
    @ORIGEN VARCHAR(50),
    @DESTINO VARCHAR(50),
    @FECHA CHAR(10),
    @COSTO CHAR(10),
    @PARTIDA CHAR(10),
    @PASAJERO INT,
    @USUARIO INT,
    @BUS INT
AS
BEGIN
UPDATE Boleto
SET OrigenBoleto = @ORIGEN,
    DestinoBoleto = @DESTINO,
    FechaViajeBoleto = @FECHA,
    CostoBoleto = @COSTO,
    HoraPartida = @PARTIDA,
    Pasajero_IdPasajero = @PASAJERO,
    Usuario_IdUsuario = @USUARIO,
    Bus_IdBus = @BUS
    WHERE IdBoleto = @CODIGO
END
GO

/* SP que permite eliminar datos de la tabla Boleto */
CREATE PROCEDURE SP_BOLETO_DELETE
AS
BEGIN
DELETE FROM Boleto WHERE IdBoleto = ''
END
GO

    


