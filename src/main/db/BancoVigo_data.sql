-- Insert into Sucursal
INSERT INTO Sucursal (CodSucursal, Ciudad, Activo) VALUES (1, 'Ourense', 100000.00);
INSERT INTO Sucursal (CodSucursal, Ciudad, Activo) VALUES (2, 'Vigo', 200000.00);

-- Insert into Cliente
INSERT INTO Cliente (Dni, Nombre, Apellidos, Telefono) VALUES ('12345678A', 'Juan', 'Pérez', 987654321);
INSERT INTO Cliente (Dni, Nombre, Apellidos, Telefono) VALUES ('87654321B', 'Ana', 'García', 123456789);
INSERT INTO Cliente (Dni, Nombre, Apellidos, Telefono) VALUES ('23456789C', 'Carlos', 'López', 912345678);
INSERT INTO Cliente (Dni, Nombre, Apellidos, Telefono) VALUES ('34567890D', 'María', 'Martínez', 934567890);
INSERT INTO Cliente (Dni, Nombre, Apellidos, Telefono) VALUES ('45678901E', 'Lucía', 'Fernández', 956789012);
INSERT INTO Cliente (Dni, Nombre, Apellidos, Telefono) VALUES ('56789012F', 'David', 'González', 978901234);

-- Insert into Cuenta with codCuenta values
INSERT INTO Cuenta (CodCuenta, CodSucursal, FechaCreacion, Saldo) VALUES (1, 1, '2024-01-10', 5000);
INSERT INTO Cuenta (CodCuenta, CodSucursal, FechaCreacion, Saldo) VALUES (2, 2, '2024-01-15', 7500);

-- Insert into CuentaCliente
INSERT INTO CuentaCliente (DNI, CodCuenta) VALUES ('12345678A', 1);
INSERT INTO CuentaCliente (DNI, CodCuenta) VALUES ('87654321B', 2);

-- Insert into Transaccion
INSERT INTO Transaccion (CodCuenta, FechaTransaccion, Tipo, Cantidad) VALUES (1, '2024-01-20', 'I', 1000);
INSERT INTO Transaccion (CodCuenta, FechaTransaccion, Tipo, Cantidad) VALUES (2, '2024-01-22', 'R', 500);
