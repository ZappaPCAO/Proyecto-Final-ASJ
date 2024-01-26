CREATE DATABASE t_zappa;

GO
USE t_zappa;

-- Creacion de las tablas--
CREATE TABLE "Categories"(
    "id" INT NOT NULL identity(1,1) PRIMARY KEY,
    "category" VARCHAR(20) NOT NULL,
    "created_at" DATETIME NOT NULL,
    "updated_at" DATETIME NULL,
    "is_deleted" BIT NOT NULL
);

CREATE TABLE "Countries"(
    "id" INT NOT NULL identity(1,1) PRIMARY KEY,
    "name" VARCHAR(30) NOT NULL
);

CREATE TABLE "States"(
    "id" INT NOT NULL identity(1,1) PRIMARY KEY,
    "name" VARCHAR(30) NOT NULL,
    "id_country" INT NOT NULL,
    FOREIGN key (id_country) references Countries(id)
);

CREATE TABLE "Cities"(
    "id" INT NOT NULL identity(1,1) PRIMARY KEY,
    "name" VARCHAR(30) NOT NULL,
    "id_state" INT NOT NULL,
    FOREIGN key (id_state) references States(id)
);

CREATE TABLE "IVA_conditions"(
    "id" INT NOT NULL identity(1,1) PRIMARY KEY,
    "condition" VARCHAR(50) NOT NULL
);

CREATE TABLE "Sectors"(
    "id" INT NOT NULL identity(1,1) PRIMARY KEY,
    "sector" VARCHAR(30),
    "created_at" DATETIME NOT NULL,
    "updated_at" DATETIME NULL,
    "is_deleted" BIT NOT NULL
)

CREATE TABLE "Providers"(
    "id" INT NOT NULL identity(1,1) PRIMARY KEY,
    "cod_provider" VARCHAR(30) NOT NULL UNIQUE,
    "business_name" VARCHAR(50) NOT NULL,
    "website" TEXT NULL,
    "email" VARCHAR(50) NOT NULL,
    "phone" BIGINT NOT NULL,
    "created_at" DATETIME NOT NULL,
    "updated_at" DATETIME NULL,
    "is_deleted" BIT NOT NULL,
    "id_sector" INT NOT NULL,
    FOREIGN key (id_sector) references Sectors(id)
);

CREATE TABLE "Taxs_Data"(
    "id" INT NOT NULL identity(1,1) PRIMARY KEY,
    "cuit" BIGINT NOT NULL UNIQUE,
    "id_iva_condition" INT NOT NULL,
    "id_provider" INT NOT NULL,
    FOREIGN key (id_provider) references Providers(id),
    FOREIGN key (id_iva_condition) references IVA_conditions(id)
);

CREATE TABLE "Locations"(
    "id" INT NOT NULL identity(1,1) PRIMARY KEY,
    "street" VARCHAR(30) NOT NULL,
    "number" INT NULL,
    "postal_code" VARCHAR(10) NOT NULL,
    "id_city" INT NOT NULL,
    "id_provider" INT NOT NULL,
    FOREIGN key (id_provider) references Providers(id),
    FOREIGN key (id_city) references Cities(id)
);

CREATE TABLE "Contacts_Data"(
    "id" INT NOT NULL identity(1,1) PRIMARY KEY,
    "name" VARCHAR(30) NOT NULL,
    "last_name" VARCHAR(30) NOT NULL,
    "phone" BIGINT NOT NULL,
    "email" VARCHAR(50) NOT NULL,
    "role" VARCHAR(50) NOT NULL,
    "id_provider" INT NOT NULL,
    FOREIGN key (id_provider) references Providers(id)
);

CREATE TABLE "Articles"(
    "id" INT NOT NULL identity(1,1) PRIMARY KEY,
    "cod_article" VARCHAR(30) NOT NULL UNIQUE,
    "name" VARCHAR(50) NOT NULL,
    "description" TEXT NULL,
    "price" FLOAT NOT NULL DEFAULT 0.0,
    "created_at" DATETIME NOT NULL,
    "updated_at" DATETIME NULL,
    "is_deleted" BIT NOT NULL,
    "id_category" INT NOT NULL,
    "id_provider" INT NOT NULL,
    FOREIGN key (id_category) references Categories(id),
    FOREIGN key (id_provider) references Providers(id)
);

CREATE TABLE "Images"(
    "id" INT NOT NULL identity(1,1) PRIMARY KEY,
    "url" VARCHAR(50) NOT NULL,
    "id_article" INT NOT NULL,
    FOREIGN key (id_article) references Articles(id),
);

CREATE TABLE "Purchase_Orders"(
    "id" INT NOT NULL identity(1,1) PRIMARY KEY,
    "nro_purchase_order" BIGINT NOT NULL,
    "send_date" DATE NOT NULL,
    "receipt_date" DATE NOT NULL,
    "description" TEXT NULL,
    "estado" VARCHAR(1) NOT NULL DEFAULT 'A', -- A -> Aprobado ; C -> Cancelado
    "total" FLOAT NOT NULL DEFAULT 0.0,
    "created_at" DATETIME NOT NULL,
    "updated_at" DATETIME NULL,
    "is_deleted" BIT NOT NULL,
    "id_provider" INT NOT NULL,
    FOREIGN key (id_provider) references Providers(id)
);


CREATE TABLE "Details"(
    "id" INT NOT NULL identity(1,1) PRIMARY KEY,
    "amount" INT NOT NULL DEFAULT 0.0,
    "subtotal" FLOAT NOT NULL DEFAULT 0.0,
    "id_article" INT NOT NULL,
    "id_purchase_order" INT NOT NULL,
    FOREIGN key (id_article) references Articles(id),
    FOREIGN key (id_purchase_order) references Purchase_Orders(id)
);

-- Insercion de datos --
INSERT INTO Categories (category, created_at, updated_at, is_deleted) VALUES
    ('Electrónicos', '2023-01-15T10:30:00', null, 0),
    ('Electrónicos', '2023-01-15T10:30:00', null, 0),
    ('Ropa', '2023-02-20T14:45:00', null, 0),
    ('Hogar', '2023-03-25T08:15:00', null, 0),
    ('Juguetes', '2023-04-10T12:20:00', null, 0),
    ('Libros', '2023-05-05T16:40:00', null, 0),
    ('Deportes', '2023-06-15T09:50:00', null, 0),
    ('Joyería', '2023-07-22T11:25:00', null, 0),
    ('Herramientas', '2023-08-30T14:10:00', null, 0),
    ('Alimentos', '2023-09-18T17:30:00', null, 0),
    ('Música', '2023-10-05T08:40:00', null, 0);

INSERT INTO Countries (name) VALUES ('Estados Unidos'),
    ('Canadá'),
    ('México'),
    ('Brasil'),
    ('Argentina'),
    ('Colombia'),
    ('Perú'),
    ('Chile'),
    ('Venezuela'),
    ('Ecuador');

INSERT INTO States (name, country_id) VALUES 
    ('California', 1), ('Texas', 1), ('Florida', 1), ('New York', 1), ('Illinois', 1), -- Estados Unidos
    ('Ontario', 2), ('Quebec', 2), ('Alberta', 2), ('British Columbia', 2), ('Manitoba', 2), -- Canadá
    ('Ciudad de México', 3), ('Jalisco', 3), ('Nuevo León', 3), ('Veracruz', 3), ('Puebla', 3), -- México
    ('São Paulo', 4), ('Rio de Janeiro', 4), ('Minas Gerais', 4), ('Bahia', 4), ('Amazonas', 4), -- Brasil
    ('Buenos Aires', 5), ('Córdoba', 5), ('Santa Fe', 5), ('Mendoza', 5), ('Tucumán', 5), -- Argentina
    ('Bogotá', 6), ('Antioquia', 6), ('Valle del Cauca', 6), ('Atlántico', 6), ('Cundinamarca', 6), -- Colombia
    ('Lima', 7), ('Arequipa', 7), ('Lambayeque', 7), ('Cusco', 7), ('Piura', 7), -- Perú
    ('Santiago', 8), ('Valparaíso', 8), ('Biobío', 8), ('Coquimbo', 8), ('La Araucanía', 8), -- Chile
    ('Caracas', 9), ('Miranda', 9), ('Zulia', 9), ('Carabobo', 9), ('Aragua', 9), -- Venezuela
    ('Quito', 10), ('Guayas', 10), ('Pichincha', 10), ('Azuay', 10), ('Manabí', 10); -- Ecuador


INSERT INTO Cities (name, state_id) VALUES --Por ahora solo agrego de Argentina
    ('La Plata', 21),
    ('Mar del Plata', 21),
    ('Quilmes', 21),
    ('Lomas de Zamora', 21),
    ('Avellaneda', 21),

    ('Villa María', 22),
    ('Río Cuarto', 22),
    ('Alta Gracia', 22),
    ('Villa Carlos Paz', 22),
    ('Bell Ville', 22),

    ('Rosario', 23),
    ('Santa Fe', 23),
    ('Venado Tuerto', 23),
    ('Rafaela', 23),
    ('San Lorenzo', 23),

    ('Godoy Cruz', 24),
    ('Maipú', 24),
    ('San Martín', 24),
    ('Luján de Cuyo', 24),
    ('Guaymallén', 24),

    ('San Miguel de Tucumán', 25),
    ('Yerba Buena', 25),
    ('Tafí Viejo', 25),
    ('Banda del Río Salí', 25),
    ('Alderetes', 25);

INSERT INTO IVA_conditions (condition) VALUES
    ('IVA Responsable Inscripto'),
    ('IVA Responsable no Inscripto'),
    ('IVA no Responsable'),
    ('IVA Sujeto Exento'),
    ('Consumidor Final'),
    ('Responsable Monotributo'),
    ('Sujeto no Categorizado'),
    ('Proveedor del Exterior'),
    ('Cliente del Exterior'),
    ('IVA Liberado - Ley Nº 19.640'),
    ('IVA Responsable Inscripto - Agente de Percepcion'),
    ('Pequeño Contribuyente Eventual'),
    ('Monotributista Social'),
    ('Pequeño Contribuyente Eventual Social');

INSERT INTO Sectors (sector_name, created_at, updated_at, is_deleted) VALUES
    ('Tecnología', '2024-01-10T12:30:00', NULL, 0),
    ('Salud', '2023-11-10T13:15:00', NULL, 0),
    ('Finanzas', '2023-06-10T14:00:00', NULL, 0),
    ('Manufactura', '2023-01-23T15:45:00', NULL, 0),
    ('Educación', '2022-12-22T16:30:00', NULL, 0),
    ('Minorista', '2021-07-11T17:15:00', NULL, 0),
    ('Energía', '2023-12-01T18:00:00', NULL, 0),
    ('Hospitalidad', '2023-10-14T18:45:00', NULL, 0),
    ('Telecomunicaciones', '2023-10-11T19:30:00', NULL, 0),
    ('Transporte', '2024-01-06T20:15:00', NULL, 0);

-- Inserciones para la tabla Providers con nombres ficticios de empresas en Argentina
INSERT INTO Providers (cod_provider, business_name, website, email, phone, created_at, updated_at, is_deleted, sector_id) VALUES
    ('ARTECH123', 'Artech S.A.', 'https://www.artech.com.ar', 'info@artech.com.ar', '543345678', '2024-01-09T12:30:00', NULL, 0, 1),
    ('TECNOPLUS', 'TecnoPlus Tech Solutions', 'https://www.tecnoplus.com.ar', 'info@tecnoplus.com.ar', '551356789', '2024-01-09T13:15:00', NULL, 0, 1),
    ('SALUDYA', 'SaludYa Farmacias', 'https://www.saludya.com.ar', 'info@saludya.com.ar', '543517890', '2024-01-09T14:00:00', NULL, 0, 2),
    ('FINANZTECH', 'FinanzTech S.R.L.', 'https://www.finanztech.com.ar', 'info@finanztech.com.ar', '54351378901', '2024-01-09T15:45:00', NULL, 0, 3),
    ('FABRIMAC', 'Fabrimac Manufacturas', 'https://www.fabrimac.com.ar', 'info@fabrimac.com.ar', '543513892', '2024-01-09T16:30:00', NULL, 0, 4),
    ('EDUCARPLUS', 'EducarPlus Educación', 'https://www.educarplus.com.ar', 'info@educarplus.com.ar', '543513123', '2024-01-09T17:15:00', NULL, 0, 5),
    ('MODAFASH', 'ModaFash Ropa', 'https://www.modafash.com.ar', 'info@modafash.com.ar', '543514034', '2024-01-09T18:00:00', NULL, 0, 6),
    ('ENERGYZA', 'EnerGyza Energía', 'https://www.energyza.com.ar', 'info@energyza.com.ar', '543512345', '2024-01-09T18:45:00', NULL, 0, 7),
    ('HOTELEX', 'HotelEx Hotel & Resort', 'https://www.hotelex.com.ar', 'info@hotelex.com.ar', '543513456', '2024-01-09T19:30:00', NULL, 0, 8),
    ('TELECOMNET', 'TelecomNet Comunicaciones', 'https://www.telecomnet.com.ar', 'info@telecomnet.com.ar', '5411514567', '2024-01-09T20:15:00', NULL, 0, 9),
    ('TRANSPORTAR', 'Transportar Logística', 'https://www.transportar.com.ar', 'info@transportar.com.ar', '5411513678', '2024-01-09T21:00:00', NULL, 0, 10),
    ('CONSULTVET', 'ConsultVet Servicios Veterinarios', 'https://www.consultvet.com.ar', 'info@consultvet.com.ar', '5411456789', '2024-01-09T21:45:00', NULL, 0, 2),
    ('SOLUCIONESIT', 'Soluciones IT Consultora', 'https://www.solucionesit.com.ar', 'info@solucionesit.com.ar', '5411515890', '2024-01-09T22:30:00', NULL, 0, 1),
    ('AGROPLUS', 'AgroPlus Agroindustria', 'https://www.agroplus.com.ar', 'info@agroplus.com.ar', '5411516701', '2024-01-09T23:15:00', NULL, 0, 4),
    ('MODATEXTIL', 'ModaTextil Textiles', 'https://www.modatextil.com.ar', 'info@modatextil.com.ar', '5411517892', '2024-01-10T00:00:00', NULL, 0, 6),
    ('SERVHOT', 'ServiHot Servicios Hoteleros', 'https://www.servihot.com.ar', 'info@servihot.com.ar', '5411518123', '2024-01-10T00:45:00', NULL, 0, 8),
    ('ENERGYSOLAR', 'EnergySolar Energía Renovable', 'https://www.energysolar.com.ar', 'info@energysolar.com.ar', '5411901234', '2024-01-10T01:30:00', NULL, 0, 7),
    ('COMUNICATEC', 'ComunicaTec Soluciones de Comunicación', 'https://www.comunicatec.com.ar', 'info@comunicatec.com.ar', '5411512345', '2024-01-10T02:15:00', NULL, 0, 9),
    ('LOGISTICA24', 'Logística24 Logística Integral', 'https://www.logistica24.com.ar', 'info@logistica24.com.ar', '5411513456', '2024-01-10T03:00:00', NULL, 0, 10);

INSERT INTO Taxs_Data (cuit, iva_condition_id) VALUES
    ('20345678901', 1),
    ('30567890123', 2),
    ('40789012345', 3),
    ('50901234567', 4),
    ('60123456789', 5),
    ('70234567890', 6),
    ('80345678901', 7),
    ('90456789012', 8),
    ('10567890123', 9),
    ('20123456789', 10),
    ('30123456789', 11),
    ('40123456789', 12),
    ('50123456789', 13),
    ('60123454789', 14),
    ('80123456789', 1),
    ('90123456789', 2),
    ('10123456789', 3),
    ('11123456789', 4),
    ('11122126789', 5);

INSERT INTO Locations (street, number, postal_code, city_id) VALUES
    ('Calle San Martín', 123, '5000', 20),
    ('Avenida Córdoba', 456, '5016', 21),
    ('Belgrano', 789, '5020', 22),
    ('Sarmiento', 1011, '5024', 23),
    ('Rivadavia', 1213, '5028', 24),
    ('Lima', 1415, '5002', 25),
    ('Mitre', 1617, '5006', 6),
    ('Pueyrredón', 1819, '5009', 7),
    ('Independencia', 2021, '5032', 8),
    ('9 de Julio', 2223, '5036', 20),
    ('Brown', 2425, '5040', 21),
    ('Alvear', 2627, '5044', 22),
    ('Moreno', 2829, '5048', 23),
    ('Urquiza', 3031, '5052', 24),
    ('General Paz', 3233, '5056', 25),
    ('Maipú', 3435, '5060', 2),
    ('Buenos Aires', 3637, '5064', 7),
    ('San Juan', 3839, '5068', 1),
    ('Santa Fe', 4041, '5072', 6);

INSERT INTO Contacts_Data (name, last_name, phone, email, role) VALUES
    ('Juan', 'Gómez', '1122334455', 'juan.gomez@example.com', 'Gerente de Ventas'),
    ('María', 'Rodríguez', '2233445566', 'maria.rodriguez@example.com', 'Ejecutivo de Cuentas'),
    ('Pablo', 'López', '3344556677', 'pablo.lopez@example.com', 'Director de Marketing'),
    ('Laura', 'Martínez', '4455667788', 'laura.martinez@example.com', 'Analista de Finanzas'),
    ('Carlos', 'Fernández', '5566778899', 'carlos.fernandez@example.com', 'Gerente de Recursos Humanos'),
    ('Ana', 'Díaz', '6677889900', 'ana.diaz@example.com', 'Director de Tecnología'),
    ('Luis', 'Sánchez', '7788990011', 'luis.sanchez@example.com', 'Gerente de Operaciones'),
    ('Marta', 'Gutiérrez', '8899001122', 'marta.gutierrez@example.com', 'Especialista en Logística'),
    ('Javier', 'Pérez', '9900112233', 'javier.perez@example.com', 'Coordinador de Proyectos'),
    ('Lucía', 'López', '223566', 'lucia.lopez@example.com', 'Analista de Calidad'),
    ('Roberto', 'Torres', '3344677', 'roberto.torres@example.com', 'Gerente de Ventas'),
    ('Sofía', 'Ramírez', '4455688', 'sofia.ramirez@example.com', 'Ejecutivo de Cuentas'),
    ('Diego', 'Hernández', '55778899', 'diego.hernandez@example.com', 'Director de Marketing'),
    ('Natalia', 'Gómez', '6677900', 'natalia.gomez@example.com', 'Analista de Finanzas'),
    ('Gustavo', 'Martínez', '7780011', 'gustavo.martinez@example.com', 'Gerente de Recursos Humanos'),
    ('Valentina', 'Pérez', '8891122', 'valentina.perez@example.com', 'Director de Tecnología'),
    ('Federico', 'López', '9900233', 'federico.lopez@example.com', 'Gerente de Operaciones'),
    ('Silvana', 'Sánchez', '2245566', 'silvana.sanchez@example.com', 'Especialista en Logística'),
    ('Mariano', 'Gutiérrez', '3356677', 'mariano.gutierrez@example.com', 'Coordinador de Proyectos');

-- Inserts para la tabla Articles
INSERT INTO Articles (cod_article, name, description, price, created_at, updated_at, is_deleted, category_id, provider_id) VALUES
    ('ELEC001', 'Smartphone', 'Teléfono inteligente de última generación', 799.99,'2022-12-22T16:30:00', null, 0, 1, 1),
    ('ELEC002', 'Laptop', 'Portátil potente y ligera', 1299.99,'2023-12-22T16:30:00', null, 0, 1, 1),
    ('ELEC003', 'Auriculares Bluetooth', 'Auriculares inalámbricos con cancelación de ruido', 149.99,'2023-12-22T16:30:00', null, 0, 1, 1),
    ('ELEC004', 'Cámara DSLR', 'Cámara profesional para fotografía', 899.99,'2024-01-02T16:30:00', null, 0, 1, 1),
    ('ELEC005', 'Altavoz inteligente', 'Altavoz con asistente de voz integrado', 79.99,'2024-01-10T16:30:00', null, 0, 1, 1),

    ('CLOTH001', 'Camiseta de algodón', 'Camiseta cómoda y transpirable', 19.99,'2022-12-22T16:30:00', null, 0, 2, 2),
    ('CLOTH002', 'Jeans clásicos', 'Pantalones vaqueros duraderos', 39.99,'2023-12-22T16:30:00', null, 0, 2, 2),
    ('CLOTH003', 'Chaqueta de cuero', 'Chaqueta elegante de piel de alta calidad', 129.99,'2023-07-28T16:30:00', null, 0, 2, 2),
    ('CLOTH004', 'Vestido de noche', 'Vestido elegante para ocasiones especiales', 89.99,'2022-11-22T16:30:00', null, 0, 2, 2),
    ('CLOTH005', 'Zapatillas deportivas', 'Calzado cómodo para actividades físicas', 49.99,'2024-09-22T16:30:00', null, 0, 2, 2),

    ('HOME001', 'Juego de sábanas', 'Sábanas suaves y confortables', 29.99,'2022-10-24T16:30:00', null, 0, 3, 3),
    ('HOME002', 'Set de utensilios de cocina', 'Utensilios de cocina de acero inoxidable', 59.99,'2023-12-22T16:30:00', null, 0, 3, 3),
    ('HOME003', 'Lámpara de mesa', 'Lámpara moderna para iluminación ambiental', 39.99,'2023-06-23T16:30:00', null, 0, 3, 3),
    ('HOME004', 'Alfombra de área', 'Alfombra decorativa para el hogar', 79.99,'2024-01-06T16:30:00', null, 0, 3, 3),
    ('HOME005', 'Juego de toallas', 'Toallas absorbentes y suaves', 24.99,'2024-01-09T16:30:00', null, 0, 3, 3),

    ('TOYS001', 'Muñeca articulada', 'Muñeca para horas de diversión', 14.99,'2022-12-22T16:30:00', null, 0, 4, 4),
    ('TOYS002', 'Juego de construcción', 'Bloques de construcción para estimular la creatividad', 24.99,'2022-12-22T16:30:00', null, 0, 4, 4),
    ('TOYS003', 'Pelota de fútbol', 'Pelota oficial para partidos y entrenamiento', 9.99,'2023-11-22T16:30:00', null, 0, 4, 4),
    ('TOYS004', 'Rompecabezas', 'Rompecabezas desafiante para todas las edades', 19.99,'2023-11-22T16:30:00', null, 0, 4, 4),
    ('TOYS005', 'Juguete educativo', 'Juguete educativo para aprender mientras juegas', 29.99,'2023-11-22T16:30:00', null, 0, 4, 4),

    ('BOOK001', 'Novela de suspense', 'Novela emocionante llena de intriga', 12.99,'2024-01-08T16:30:00', null, 0, 5, 5),
    ('BOOK002', 'Libro de cocina', 'Recetas deliciosas para experimentar en la cocina', 18.99,'2023-12-22T16:30:00', null, 0, 5, 5),
    ('BOOK003', 'Libro de autoayuda', 'Consejos prácticos para mejorar la vida cotidiana', 14.99,'2024-01-08T16:30:00', null, 0, 5, 5),
    ('BOOK004', 'Libro infantil', 'Cuento ilustrado para niños pequeños', 9.99,'2023-12-22T16:30:00', null, 0, 5, 5),
    ('BOOK005', 'Libro de poesía', 'Colección de poemas inspiradores', 16.99,'2024-01-08T16:30:00', null, 0, 5, 5),

    ('SPORTS001', 'Balón de baloncesto', 'Balón oficial para partidos y entrenamiento', 29.99,'2022-12-22T16:30:00', null, 0, 6, 6),
    ('SPORTS002', 'Raqueta de tenis', 'Raqueta de alta calidad para jugadores avanzados', 89.99,'2023-01-21T16:30:00', null, 0, 6, 6),
    ('SPORTS003', 'Pesas ajustables', 'Pesas para entrenamiento de fuerza en casa', 49.99,'2023-01-21T16:30:00', null, 0, 6, 6),
    ('SPORTS004', 'Cinta de correr plegable', 'Equipo de fitness para entrenamiento en casa', 399.99,'2024-01-02T16:30:00', null, 0, 6, 6),
    ('SPORTS005', 'Bicicleta de ejercicio', 'Bicicleta estática para entrenamiento cardiovascular', 199.99,'2024-01-02T16:30:00', null, 0, 6, 6),

    ('JEWELRY001', 'Collar de perlas', 'Collar elegante para ocasiones especiales', 69.99,'2023-11-22T16:30:00', null, 0, 7, 7),
    ('JEWELRY002', 'Anillo de plata', 'Anillo delicado con diseño moderno', 49.99,'2023-11-22T16:30:00', null, 0, 7, 7),
    ('JEWELRY003', 'Reloj de pulsera', 'Reloj elegante con correa de cuero', 129.99,'2024-01-10T16:30:00', null, 0, 7, 7),
    ('JEWELRY004', 'Pulsera de oro', 'Pulsera brillante para un toque de glamour', 79.99,'2024-01-10T16:30:00', null, 0, 7, 7),
    ('JEWELRY005', 'Aretes de diamantes', 'Aretes de diamantes para un estilo deslumbrante', 159.99,'2023-11-22T16:30:00', null, 0, 7, 7),

    ('TOOLS001', 'Destornillador eléctrico', 'Herramienta práctica para tareas de bricolaje', 39.99,'2022-12-22T16:30:00', null, 0, 8, 8),
    ('TOOLS002', 'Sierra circular', 'Sierra potente para cortes precisos', 99.99,'2022-12-22T16:30:00', null, 0, 8, 8),
    ('TOOLS003', 'Set de llaves inglesas', 'Llaves ajustables para diversas aplicaciones', 29.99,'2024-01-03T16:30:00', null, 0, 8, 8),
    ('TOOLS004', 'Taladro inalámbrico', 'Taladro versátil para perforaciones eficientes', 79.99,'2023-12-22T16:30:00', null, 0, 8, 8),
    ('TOOLS005', 'Caja de herramientas completa', 'Colección completa de herramientas para el hogar', 149.99,'2023-09-22T16:30:00', null, 0, 8, 8),

    ('FOOD001', 'Caja de chocolates surtidos', 'Selección de deliciosos chocolates', 19.99,'2023-11-22T16:30:00', null, 0, 9, 9),
    ('FOOD002', 'Aceite de oliva virgen extra', 'Aceite de oliva premium para cocinar', 14.99,'2023-11-22T16:30:00', null, 0, 9, 9),
    ('FOOD003', 'Paquete de café gourmet', 'Café de alta calidad para los amantes del café', 29.99,'2023-11-22T16:30:00', null, 0, 9, 9),
    ('FOOD004', 'Vino tinto reserva', 'Vino tinto envejecido para ocasiones especiales', 49.99,'2024-01-04T16:30:00', null, 0, 9, 9),
    ('FOOD005', 'Caja de té variado', 'Selección de té gourmet en una elegante caja', 24.99,'2024-01-04T16:30:00', null, 0, 9, 9),

    ('MUSIC001', 'Auriculares Bluetooth', 'Auriculares inalámbricos para disfrutar de la música', 69.99,'2022-12-22T16:30:00', null, 0, 10, 10),
    ('MUSIC002', 'Altavoz portátil', 'Altavoz compacto para llevar la música a todas partes', 29.99,'2023-12-22T16:30:00', null, 0, 10, 10),
    ('MUSIC003', 'Vinilo clásico', 'Disco de vinilo con clásicos atemporales', 34.99,'2022-12-22T16:30:00', null, 0, 10, 10),
    ('MUSIC004', 'Guitarra acústica', 'Guitarra de calidad para músicos aficionados', 149.99,'2023-12-22T16:30:00', null, 0, 10, 10),
    ('MUSIC005', 'Teclado electrónico', 'Teclado versátil para entusiastas de la música', 119.99,'2024-01-03T16:30:00', null, 0, 10, 10),

    ('CLOTH006', 'Sudadera con capucha', 'Sudadera cómoda para uso diario', 29.99, '2023-03-15T12:30:00', null, 0, 2, 11),
    ('CLOTH007', 'Pantalones cortos deportivos', 'Pantalones cortos ideales para actividades físicas', 19.99, '2023-04-22T15:45:00', null, 0, 2, 11),
    ('HOME006', 'Juego de sartenes antiadherentes', 'Sartenes duraderas para cocinar con facilidad', 49.99, '2023-05-10T09:20:00', null, 0, 3, 11),
    ('HOME007', 'Cojín decorativo', 'Cojín con diseño moderno para el hogar', 14.99, '2023-06-18T18:10:00', null, 0, 3, 11),
    ('TOYS006', 'Rompecabezas 3D', 'Rompecabezas tridimensional para desafíos divertidos', 22.99, '2023-07-03T14:55:00', null, 0, 4, 11),
    ('TOYS007', 'Muñeco de peluche', 'Peluche suave y adorable para niños', 18.99, '2023-08-12T10:40:00', null, 0, 4, 11),
    ('BOOK006', 'Libro de ficción', 'Novela emocionante para entretenerse', 16.99, '2023-09-27T22:15:00', null, 0, 5, 11),
    ('BOOK007', 'Libro de ciencia', 'Exploración de conceptos científicos en un libro', 21.99, '2023-10-05T08:05:00', null, 0, 5, 11),
    ('SPORTS006', 'Balón de fútbol', 'Balón oficial para partidos y entrenamiento', 24.99, '2023-11-14T17:30:00', null, 0, 6, 11),
    ('SPORTS007', 'Raqueta de squash', 'Raqueta profesional para jugadores avanzados', 79.99, '2023-12-20T11:25:00', null, 0, 6, 11),

    ('JEWELRY006', 'Collar de diamantes', 'Collar deslumbrante con incrustaciones de diamantes', 159.99, '2023-01-08T14:45:00', null, 0, 7, 12),
    ('JEWELRY007', 'Pulsera de plata', 'Pulsera elegante con diseño moderno', 49.99, '2023-02-17T09:10:00', null, 0, 7, 12),
    ('TOOLS006', 'Martillo de carpintero', 'Martillo resistente para trabajos de carpintería', 19.99, '2023-03-25T21:20:00', null, 0, 8, 12),
    ('TOOLS007', 'Sierra de mano', 'Sierra portátil para cortes precisos', 29.99, '2023-04-30T13:55:00', null, 0, 8, 12),
    ('FOOD006', 'Caja de vinos variados', 'Selección de vinos de distintas cepas', 69.99, '2023-05-19T08:30:00', null, 0, 9, 12),
    ('FOOD007', 'Paquete de snacks saludables', 'Snacks nutritivos para picar entre comidas', 14.99, '2023-06-28T16:40:00', null, 0, 9, 12),
    ('MUSIC006', 'Auriculares inalámbricos', 'Auriculares para disfrutar de la música sin cables', 49.99, '2023-07-11T12:15:00', null, 0, 10, 12),
    ('MUSIC007', 'Guitarra eléctrica', 'Guitarra versátil para músicos experimentados', 249.99, '2023-08-23T19:05:00', null, 0, 10, 12),
    ('ELEC006', 'Smartwatch', 'Reloj inteligente con funciones avanzadas', 129.99, '2023-09-02T23:50:00', null, 0, 1, 12),
    ('ELEC007', 'Impresora multifunción', 'Impresora para tareas de impresión y escaneo', 149.99, '2023-10-10T10:25:00', null, 0, 1, 12),

    ('CLOTH008', 'Camisa formal', 'Camisa elegante para ocasiones especiales', 34.99, '2022-11-15T16:30:00', null, 0, 2, 13),
    ('CLOTH009', 'Pantalones de vestir', 'Pantalones clásicos para looks formales', 44.99, '2022-12-20T11:55:00', null, 0, 2, 13),
    ('HOME008', 'Set de vasos de cristal', 'Vasos de cristal para servir bebidas con estilo', 29.99, '2023-01-25T08:45:00', null, 0, 3, 13),
    ('HOME009', 'Manta de lana', 'Manta suave y abrigada para el invierno', 39.99, '2023-02-05T14:20:00', null, 0, 3, 13),
    ('TOYS008', 'Kit de construcción', 'Kit de construcción para niños creativos', 19.99, '2023-03-12T19:35:00', null, 0, 4, 13),
    ('TOYS009', 'Muñeca articulada', 'Muñeca con articulaciones para poses realistas', 29.99, '2023-04-18T09:10:00', null, 0, 4, 13),
    ('BOOK008', 'Libro de poesía', 'Colección de poesías de autores reconocidos', 18.99, '2023-05-27T17:25:00', null, 0, 5, 13),
    ('BOOK009', 'Libro de historia', 'Exploración de eventos históricos en un libro', 24.99, '2023-06-30T13:50:00', null, 0, 5, 13),
    ('SPORTS008', 'Pelota de tenis', 'Pelota oficial para partidos de tenis', 8.99, '2023-07-15T22:40:00', null, 0, 6, 13),
    ('SPORTS009', 'Botella de agua deportiva', 'Botella ergonómica para mantenerse hidratado', 12.99, '2023-08-28T18:15:00', null, 0, 6, 13);

    INSERT INTO Images (url, article_id) VALUES
    ('https://example.com/image1.jpg', 1),
    ('https://example.com/image2.jpg', 2),
    ('https://example.com/image3.jpg', 3),
    ('https://example.com/image4.jpg', 4),
    ('https://example.com/image5.jpg', 5),
    ('https://example.com/image6.jpg', 6),
    ('https://example.com/image7.jpg', 7),
    ('https://example.com/image8.jpg', 8),
    ('https://example.com/image9.jpg', 9),
    ('https://example.com/image10.jpg', 10),
    ('https://example.com/image11.jpg', 11),
    ('https://example.com/image12.jpg', 12),
    ('https://example.com/image13.jpg', 13),
    ('https://example.com/image14.jpg', 14),
    ('https://example.com/image15.jpg', 15),
    ('https://example.com/image16.jpg', 16),
    ('https://example.com/image17.jpg', 17),
    ('https://example.com/image18.jpg', 18),
    ('https://example.com/image19.jpg', 19),
    ('https://example.com/image20.jpg', 20);

 
INSERT INTO Purchase_Orders (nro_purchase_order, send_date, receipt_date, description, estado, total, created_at, is_deleted, provider_id) VALUES
    (12345, '2023-03-10T08:00:00', '2023-03-15T12:30:00', 'Compra de artículos electrónicos', 'A', 250.75, '2023-03-01T08:00:00', 0, 1),
    (12346, '2023-04-05T09:30:00', '2023-04-10T15:45:00', 'Compra de ropa de temporada', 'A', 180.50, '2023-04-01T09:30:00', 0, 2),
    (12347, '2023-05-12T11:45:00', '2023-05-17T21:20:00', 'Compra de electrodomésticos para el hogar', 'A', 450.20, '2023-05-01T11:45:00', 0, 3),
    (12348, '2023-06-20T14:20:00', '2023-06-25T19:35:00', 'Compra de juguetes infantiles', 'A', 120.90, '2023-06-01T14:20:00', 0, 4),
    (12349, '2023-07-08T16:55:00', '2023-07-13T14:55:00', 'Compra de libros educativos', 'A', 90.25, '2023-07-01T16:55:00', 0, 5),
    (12350, '2023-08-15T19:10:00', '2023-08-20T10:40:00', 'Compra de artículos deportivos', 'A', 210.30, '2023-08-01T19:10:00', 0, 6),
    (12351, '2023-09-22T22:35:00', '2023-09-27T17:30:00', 'Compra de joyería fina', 'A', 320.45, '2023-09-01T22:35:00', 0, 7),
    (12352, '2023-10-18T07:45:00', '2023-10-23T13:55:00', 'Compra de herramientas profesionales', 'A', 180.75, '2023-10-01T07:45:00', 0, 8),
    (12353, '2023-11-25T10:00:00', '2023-11-30T22:15:00', 'Compra de alimentos gourmet', 'A', 150.60, '2023-11-01T10:00:00', 0, 9),
    (12354, '2023-12-12T12:25:00', '2023-12-17T08:30:00', 'Compra de instrumentos musicales', 'A', 300.90, '2023-12-01T12:25:00', 0, 10),
    (12355, '2024-01-05T14:20:00', '2024-01-10T16:30:00', 'Compra de artículos de hogar', 'A', 180.20, '2024-01-01T14:20:00', 0, 2),
    (12356, '2024-02-12T09:30:00', '2024-02-17T14:55:00', 'Compra de herramientas de jardín', 'A', 120.90, '2024-02-01T09:30:00', 0, 4),
    (12357, '2024-03-20T12:00:00', '2024-03-25T21:20:00', 'Compra de ropa de moda', 'A', 250.75, '2024-03-01T12:00:00', 0, 6),
    (12358, '2024-04-08T17:45:00', '2024-04-13T14:20:00', 'Compra de juguetes educativos', 'A', 90.50, '2024-04-01T17:45:00', 0, 8),
    (12359, '2024-05-15T11:10:00', '2024-05-20T19:10:00', 'Compra de equipos deportivos', 'A', 210.30, '2024-05-01T11:10:00', 0, 10),
    (12360, '2024-06-22T15:35:00', '2024-06-27T09:20:00', 'Compra de libros de ciencia ficción', 'A', 120.90, '2024-06-01T15:35:00', 0, 3),
    (12361, '2024-07-10T08:50:00', '2024-07-15T12:25:00', 'Compra de herramientas eléctricas', 'A', 180.75, '2024-07-01T08:50:00', 0, 7);

INSERT INTO Details (amount, subtotal, article_id,purchase_order_id) VALUES
    (3, 104.97, 1, 1),
    (1, 69.99, 2, 1),
    (4, 239.96, 3, 1),
    (2, 58.98, 4, 1),
    (3, 89.97, 5, 1),
    
    (2, 58.98, 6, 2),
    (3, 124.97, 7, 2),
    (1, 99.99, 8, 2),
    (4, 199.96, 9, 2),
    (2, 58.98, 10, 2),

    (1, 69.99, 13, 3),
    (4, 239.96, 14, 3),
    (2, 58.98, 15, 3),

    (2, 79.98, 16, 4),
    (3, 124.97, 17, 4),
    (1, 99.99, 18, 4),
    (4, 199.96, 19, 4),

    (1, 69.99, 20, 5),
    (5, 174.95, 23, 5),
    (2, 79.98, 22, 5),
    (3, 124.97, 24, 5),
    (1, 99.99, 25, 5);



drop table articles;
drop table categories;
drop table cities;
drop table contacts_data;
drop table countries;
drop table details;
drop table images;
drop table iva_conditions;
drop table locations;
drop table providers;
drop table purchase_orders;
drop table sectors;
drop table states;
drop table taxs_data;
