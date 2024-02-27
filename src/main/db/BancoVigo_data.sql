-- Insert into Sucursal
INSERT INTO Sucursal (suCodSucursal, suCiudad, suActivo) VALUES (1, 'Ourense', 1);
INSERT INTO Sucursal (suCodSucursal, suCiudad, suActivo) VALUES (2, 'Vigo', 1);

-- Insert into Cliente
INSERT INTO Cliente (clDni, clNombre, clApellido, clTelefono) VALUES ('12345678A', 'Juan', 'Pérez', '987654321');
INSERT INTO Cliente (clDni, clNombre, clApellido, clTelefono) VALUES ('87654321B', 'Ana', 'García', '123456789');
INSERT INTO Cliente (clDni, clNombre, clApellido, clTelefono) VALUES ('23456789C', 'Carlos', 'López', '912345678');
INSERT INTO Cliente (clDni, clNombre, clApellido, clTelefono) VALUES ('34567890D', 'María', 'Martínez', '934567890');
INSERT INTO Cliente (clDni, clNombre, clApellido, clTelefono) VALUES ('45678901E', 'Lucía', 'Fernández', '956789012');
INSERT INTO Cliente (clDni, clNombre, clApellido, clTelefono) VALUES ('56789012F', 'David', 'González', '978901234');

-- Insert into Cuenta
INSERT INTO Cuenta (cuCodCuenta, cuCodSucursal, cuFechaCreacion, cuSaldo) VALUES (1, 1, '2024-01-10', 5000);
INSERT INTO Cuenta (cuCodCuenta, cuCodSucursal, cuFechaCreacion, cuSaldo) VALUES (2, 2, '2024-01-15', 7500);

-- Insert into CuentasCliente
INSERT INTO CuentasCliente (ccDni, ccCodCuenta) VALUES ('12345678A', 1);
INSERT INTO CuentasCliente (ccDni, ccCodCuenta) VALUES ('87654321B', 2);

-- Insert into Transacciones
INSERT INTO Transacciones (trCodCuenta, trFechaTransaccion, trTipo, trCantidad) VALUES (1, '2024-01-20', 'I', 1000);
INSERT INTO Transacciones (trCodCuenta, trFechaTransaccion, trTipo, trCantidad) VALUES (2, '2024-01-22', 'R', 500);
