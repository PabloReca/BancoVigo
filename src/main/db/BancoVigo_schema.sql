USE BancoVigo;

-- Creación de la tabla Cliente
CREATE TABLE Cliente
(
    clDni VARCHAR(255) PRIMARY KEY,
    clNombre VARCHAR(255),
    clApellido VARCHAR(255),
    clTelefono VARCHAR(255)
);

-- Creación de la tabla Sucursal primero, para asegurar que las FK puedan referenciarla
CREATE TABLE Sucursal
(
    suCodSucursal INT PRIMARY KEY,
    suCiudad VARCHAR(255),
    suActivo DOUBLE
);

-- Ahora se puede crear Cuenta, ya que Sucursal ya existe, ajustando a las correcciones
CREATE TABLE Cuenta
(
    cuCodCuenta INT PRIMARY KEY,
    cuCodSucursal INT,
    cuFechaCreacion DATE,
    cuSaldo DOUBLE,
    FOREIGN KEY (cuCodSucursal) REFERENCES Sucursal(suCodSucursal)
);

-- Creación de la tabla CuentasCliente, después de crear Cliente y Cuenta
CREATE TABLE CuentasCliente
(
    ccDni VARCHAR(255),
    ccCodCuenta INT,
    PRIMARY KEY (ccDni, ccCodCuenta),
    FOREIGN KEY (ccDni) REFERENCES Cliente(clDni),
    FOREIGN KEY (ccCodCuenta) REFERENCES Cuenta(cuCodCuenta)
);

-- Creación de la tabla Transacciones, después de crear Cuenta
CREATE TABLE Transacciones
(
    ID INT AUTO_INCREMENT PRIMARY KEY,
    trCodCuenta INT,
    trFechaTransaccion DATE,
    trTipo VARCHAR(255),
    trCantidad DOUBLE,
    FOREIGN KEY (trCodCuenta) REFERENCES Cuenta(cuCodCuenta)
);
