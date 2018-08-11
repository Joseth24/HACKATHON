/* SP PARA INGRESAR DATOS A LA TABLA ASISTENTE */
CREATE PROCEDURE SP_ADD_ASISTENTE
    @NOMBRE VARCHAR(50),
    @APELLIDO VARCHAR(50),
    @CELULAR CHAR(10)
AS
BEGIN
    INSERT INTO Asistente
        (NombreAsistente,ApellidoAsistente,CelularAsistente)
    VALUES
        (@NOMBRE, @APELLIDO, @CELULAR)
END
GO

/* SP PARA MODIFICAR LOS DATOS DE LA TABLA ASISTENTE*/
CREATE PROCEDURE SP_ACTUALIZAR_ASISTENTE
    @CODIGO INT,
    @NOMBRE VARCHAR(50),
    @APELLIDO VARCHAR(50),
    @CELULAR CHAR(10)
AS
BEGIN
    UPDATE Asistente
    SET NombreAsistente = @NOMBRE,
        ApellidoAsistente = @APELLIDO,
        CelularAsistente = @CELULAR
        WHERE IdAsistente = @CODIGO
END
GO

/* SP PARA INGRESAR DATOS EN LA TABLA BOLETO*/
CREATE PROCEDURE SP_ADD_BOLETO
    @ORIGEN VARCHAR(50),
    @DESTINO  VARCHAR(50),
    @FECHAVIAJE CHAR(10),
    @COSTO CHAR(10),
    @PARTIDA CHAR(10),
    @ASISTENTE INT,
    @PASAJERO INT,
    @USUARIO INT,
    @BUS INT,
    @CHOFER INT
AS
BEGIN
    INSERT INTO Boleto
        (OrigenBoleto,DestinoBoleto,FechaViajeBoleto,CostoBoleto,HoraPartida,Asistente_IdAsistente,Pasajero_IdPasajero,
        Usuario_IdUsuario,Bus_IdBus,Chofer_IdChofer)
    VALUES
        (@ORIGEN, @DESTINO, FORMAT(CONVERT(date,@FECHAVIAJE,103),'dd/MM/yyyy','en-gb'), @COSTO, @PARTIDA, @ASISTENTE, @PASAJERO, @USUARIO, @BUS, @CHOFER)
END
GO

EXEC SP_ADD_BOLETO @ORIGEN='ICA',@DESTINO='PISCO',@FECHAVIAJE='12/12/2017',@COSTO='S/ 14.00',@PARTIDA='08:74AM',@ASISTENTE='1',@PASAJERO='1',
@USUARIO='1',@BUS='1',@CHOFER='5'
GO

/* SP PARA MODIFICAR LOS DATOS DE LA TABLA BOLETO*/
CREATE PROCEDURE SP_UPDATE_BOLETO
    @CODIGO INT,
    @ORIGEN VARCHAR(50),
    @DESTINO VARCHAR(50),
    @FECHAVIAJE CHAR(10),
    @COSTO CHAR(10),
    @PARTIDA CHAR(10),
    @ASISTENTE INT,
    @PASAJERO INT,
    @USUARIO INT,
    @BUS INT,
    @CHOFER INT
AS
BEGIN
    UPDATE Boleto
    SET OrigenBoleto = @ORIGEN,
        DestinoBoleto = @DESTINO,
        FechaViajeBoleto = FORMAT(CONVERT(date,@FECHAVIAJE,103),'dd/MM/yyyy','en-gb'),
        CostoBoleto = @COSTO,
        HoraPartida = @PARTIDA,
        Asistente_IdAsistente = @ASISTENTE,
        Pasajero_IdPasajero = @PASAJERO,
        Usuario_IdUsuario = @USUARIO,
        Bus_IdBus = @BUS,
        Chofer_IdChofer = @CHOFER
        WHERE IdBoleto = @CODIGO
END
GO

/* SP PARA AGREGAR DATOS A LA TABLA BUS */
CREATE PROCEDURE SP_ADD_BUS
    @PLACA CHAR(10),
    @TELEFONO CHAR(7)
AS
BEGIN
    INSERT INTO Bus
    (PlacaBus,TelefonoBus)
    VALUES
    (@PLACA,@TELEFONO)
END
GO

/* SP PARA MODIFICAR LOS DATOS DE LA TABLA BUS */
CREATE PROCEDURE SP_UPDATE_BUS
    @CODIGO INT,
    @PLACA CHAR(10),
    @TELEFONO CHAR(10)
AS
BEGIN
    UPDATE Bus
    SET PlacaBus = @PLACA,
        TelefonoBus = @TELEFONO
        WHERE IdBus = @CODIGO
END
GO

/* SP PARA AGREGAR DATOS A LA TABLA CHOFER */
CREATE PROCEDURE SP_ADD_CHOFER
    @NOMBRE VARCHAR(50),
    @APELLIDO VARCHAR(50),
    @TELEFONO CHAR(7),
    @ASISTENTE INT
AS
BEGIN
    INSERT INTO Chofer
    (NombreChofer,ApellidoChofer,TelefonoChofer,Asistente_IdAsistente)
    VALUES
    (@NOMBRE,@APELLIDO,@TELEFONO,@ASISTENTE)
END
GO

/* SP PARA MODIFICAR DATOS DE LA TABLA CHOFER */
CREATE PROCEDURE SP_UPDATE_CHOFER
    @CODIGO INT,
    @NOMBRE VARCHAR(50),
    @APELLIDO VARCHAR(50),
    @TELEFONO CHAR(7),
    @ASISTENTE INT
AS
BEGIN
    UPDATE Chofer
    SET NombreChofer = @NOMBRE,
        ApellidoChofer = @APELLIDO,
        TelefonoChofer = @TELEFONO,
        Asistente_IdAsistente = @ASISTENTE
        WHERE IdChofer = @CODIGO
END
GO

/* SP PARA INGRESAR DATOS A LA TABLA PASAJERO */
CREATE PROCEDURE SP_ADD_PASAJERO
    @NOMBRE VARCHAR(100),
    @APELLIDO VARCHAR(100),
    @DNI CHAR(8)
AS
BEGIN
    INSERT INTO Pasajero
    (NombrePasajero,ApellidoPasajero,DniPasajero)
    VALUES
    (@NOMBRE,@APELLIDO,@DNI)
END
GO

/* SP PARA MODIFICAR DATOS DE LA TABLA PASAJERO */
CREATE PROCEDURE SP_UPDATE_PASAJERO
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

/* SP PARA INGRESAR DATOS DE LA TABLA USUARIO */
CREATE PROCEDURE SP_ADD_USUARIO
    @NOMBRE VARCHAR(100),
    @APELLIDO VARCHAR(100),
    @CELULAR CHAR(9)
AS
BEGIN
    INSERT INTO Usuario
    (NombreUsuario,ApellidoUsuario,CelularUsuario)
    VALUES
    (@NOMBRE,@APELLIDO,@CELULAR)
END
GO

/* SP PARA MODIFICAR LOS DATOS DE LA TABLA USUARIO */
CREATE PROCEDURE SP_UPDATE_USUARIO
    @CODIGO INT,
    @NOMBRE VARCHAR(100),
    @APELLIDO VARCHAR(100),
    @CELULAR VARCHAR(100)
AS 
BEGIN
    UPDATE Usuario
    SET NombreUsuario = @NOMBRE,
        ApellidoUsuario = @APELLIDO,
        CelularUsuario = @CELULAR
        WHERE IdUsuario = @CODIGO
END
GO


------------ VISTAS ----------------- 

/* VISTA PARA MOSTRAR DATOS DE LA TABLA BOLETO CON LAS DEMAS TABLAS */
CREATE VIEW VW_BOLETO
AS
SELECT IdBoleto,OrigenBoleto,DestinoBoleto,FechaViajeBoleto,CostoBoleto,HoraPartida,
CONCAT(Asistente.NombreAsistente,' ',Asistente.ApellidoAsistente) AS 'Asistente',
CONCAT(Pasajero.NombrePasajero,' ',Pasajero.ApellidoPasajero) AS 'Pasajero',
CONCAT(Usuario.NombreUsuario,' ',Usuario.ApellidoUsuario) AS 'Usuario',
CONCAT(Bus.PlacaBus,' / ',Bus.TelefonoBus) AS 'Bus',
CONCAT(Chofer.NombreChofer,' ',Chofer.ApellidoChofer) AS 'Chofer'
FROM Boleto
LEFT OUTER JOIN Asistente ON Boleto.Asistente_IdAsistente = Asistente.IdAsistente
LEFT OUTER JOIN Pasajero ON Boleto.Pasajero_IdPasajero = Pasajero.IdPasajero
LEFT OUTER JOIN Usuario ON Boleto.Usuario_IdUsuario = Usuario.IdUsuario
LEFT OUTER JOIN Bus ON Boleto.Bus_IdBus = Bus.IdBus
LEFT OUTER JOIN Chofer ON Boleto.Chofer_IdChofer = Chofer.IdChofer
GO

/* VISTA DONDE SE MUESTRAN LOS DATOS DE LA TABLA CHOFER CON LA TABLA ASISTENTE*/
CREATE VIEW VW_CHOFER
AS
SELECT IdChofer, NombreChofer, ApellidoChofer, TelefonoChofer,CONCAT(Asistente.NombreAsistente,' ',Asistente.ApellidoAsistente) AS 'Asistente'
FROM Chofer
LEFT OUTER JOIN Asistente ON Chofer.Asistente_IdAsistente = Asistente.IdAsistente
GO

