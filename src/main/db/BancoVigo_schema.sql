USE BancoVigo;

-- Creación de la tabla Cliente
CREATE TABLE Cliente
(
    dni       VARCHAR(255) PRIMARY KEY,
    nombre    VARCHAR(255),
    apellidos VARCHAR(255),
    telefono  VARCHAR(255)
);

-- Creación de la tabla Sucursal primero, para asegurar que las FK puedan referenciarla
CREATE TABLE Sucursal
(
    codSucursal INT PRIMARY KEY,
    ciudad      VARCHAR(255),
    activo      DOUBLE
);

-- Ahora se puede crear Cuenta, ya que Sucursal ya existe
CREATE TABLE Cuenta
(
    codCuenta     INT PRIMARY KEY,
    codSucursal   INT,
    fechaCreacion DATE,
    saldo         INT,
    FOREIGN KEY (codSucursal) REFERENCES Sucursal (codSucursal)
);

-- Creación de la tabla CuentaCliente, después de crear Cliente y Cuenta
CREATE TABLE CuentaCliente
(
    dni       VARCHAR(255),
    codCuenta INT,
    PRIMARY KEY (dni, codCuenta),
    FOREIGN KEY (dni) REFERENCES Cliente (dni),
    FOREIGN KEY (codCuenta) REFERENCES Cuenta (codCuenta)
);

-- Creación de la tabla Transaccion, después de crear Cuenta
CREATE TABLE Transaccion
(
    codCuenta        INT,
    fechaTransaccion DATE,
    tipo             VARCHAR(255),
    cantidad         INT,
    FOREIGN KEY (codCuenta) REFERENCES Cuenta (codCuenta)
);
