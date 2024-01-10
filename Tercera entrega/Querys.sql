use t_zappa;

-- ********************CONSULTAS************************* --

-- 1. Obtener todos los productos, mostrando nombre del producto, categoría, proveedor (razón social y codigo proveedor), precio.

SELECT Articles.name, Categories.category, Providers.business_name, Providers.cod_provider, Articles.price
FROM Articles
    LEFT JOIN Providers ON (Articles.id_provider = Providers.id)
    LEFT JOIN Categories ON (Articles.id_category = Categories.id)

-- 2. En el listado anterior, además de los datos mostrados, traer el campo imagen aunque el producto NO tenga una. Sino tiene imagen, mostrar "-".

SELECT Articles.name, Categories.category, Providers.business_name, Providers.cod_provider, Articles.price,
        CASE
            WHEN Images.url IS NULL THEN '-'
            ELSE Images.url
        END AS Imagen
FROM Articles
    LEFT JOIN Providers ON (Articles.id_provider = Providers.id)
    LEFT JOIN Categories ON (Articles.id_category = Categories.id)
    LEFT JOIN Images ON (Articles.id = Images.id_article)

-- 3. Mostrar los datos que se pueden modificar (en el front) del producto con ID = 2.

SELECT Articles.name, Articles.description, Articles.price, Articles.id_provider, Providers.business_name, Articles.id_category, Categories.category -- Aca muestro las id xq seria lo que realmente se cambiaria.
FROM Articles
    LEFT JOIN Providers ON (Articles.id_provider = Providers.id)
    LEFT JOIN Categories ON (Articles.id_category = Categories.id)
WHERE(Articles.id = 2)

-- 4. Listar todo los proveedores cuyo teléfono tenga la característica de Córdoba o que la provincia sea igual a alguna de las 3 con más proveedores.

UPDATE Providers
SET phone = 351233123
WHERE Providers.id = 1

SELECT Providers.business_name, Providers.cod_provider, Providers.created_at, Providers.email, Providers.phone, Providers.website
FROM Providers
    LEFT JOIN Locations ON Providers.id = Locations.id_city
WHERE (Providers.phone LIKE '351%' OR Locations.id_city IN ((
    SELECT TOP 3 Cities.id
    FROM Providers 
        RIGHT JOIN Locations ON Providers.id = Locations.id_provider
        RIGHT JOIN Cities ON Locations.id_city = Cities.id
    GROUP BY Cities.id
    ORDER BY COUNT(Providers.id) DESC
)))

-- 5. Traer un listado de todos los proveedores que no hayan sido eliminados , 
--      y ordenados por razon social, codigo proveedor y fecha en que se dió de alta ASC.
--      De este listado mostrar los datos que correspondan con su tabla del front.

SELECT  Providers.cod_provider AS 'Cod. Proveedor', Providers.business_name AS 'Raz. Social', (Contacts_Data.name + ' ' + Contacts_Data.last_name) AS 'Nombre completo',
        Providers.website, Contacts_Data.email, Contacts_Data.phone, Providers.created_at 
FROM Providers 
    LEFT JOIN Contacts_Data ON (Providers.id = Contacts_Data.id_provider)
ORDER BY Providers.business_name, Providers.cod_provider, Providers.created_at

-- 6. Obtener razon social, codigo proveedor, imagen, web, email, teléfono y los datos del contacto del proveedor con más ordenes de compra cargadas.

ALTER TABLE Providers
ADD url_img VARCHAR(50) NULL;

SELECT TOP 1 Providers.business_name, Providers.cod_provider,
        ISNULL(Providers.url_img, '-') AS 'Imagen', 
        Providers.website, Providers.email, Providers.phone,
        Contacts_Data.name, Contacts_Data.last_name, Contacts_Data.email, Contacts_Data.phone, Contacts_Data.role,
        COUNT(Purchase_Orders.id) AS 'Cant'
FROM Providers
    LEFT JOIN Purchase_Orders ON (Providers.id = Purchase_Orders.id_provider)
    LEFT JOIN Contacts_Data ON (Providers.id = Contacts_Data.id_provider)
GROUP BY Providers.business_name, Providers.cod_provider, Providers.url_img, Providers.website, Providers.email, Providers.phone,
        Contacts_Data.name, Contacts_Data.last_name, Contacts_Data.email, Contacts_Data.phone, Contacts_Data.role
ORDER BY 'Cant' DESC;

-- 7. Mostrar la fecha emisión, nº de orden, razon social y codigo de proveedor, y la cantidad de productos de cada orden.

SELECT Purchase_Orders.send_date, Purchase_Orders.nro_purchase_order, Providers.business_name, Providers.cod_provider, SUM(Details.amount) AS 'Cantidad'
FROM Details
    LEFT JOIN Purchase_Orders ON Details.id_purchase_order = Purchase_orders.id 
    LEFT JOIN Providers ON Purchase_orders.id_provider = Providers.id
GROUP BY Purchase_Orders.send_date, Purchase_Orders.nro_purchase_order, Providers.business_name, Providers.cod_provider

-- 8. En el listado anterior, diferenciar cuando una orden está Cancelada o no, y el total de la misma.

UPDATE Purchase_Orders
SET estado = 'C'
WHERE (nro_purchase_order IN (12345,12347,12349));

SELECT Purchase_Orders.send_date, Purchase_Orders.nro_purchase_order, Providers.business_name, Providers.cod_provider, SUM(Details.amount) AS 'Cantidad',
    CASE 
        WHEN Purchase_Orders.estado = 'C' THEN 'Cancelada'
        ELSE 'Aprobada'
    END AS Estado,
    Purchase_Orders.total
FROM Details
    LEFT JOIN Purchase_Orders ON Details.id_purchase_order = Purchase_orders.id 
    LEFT JOIN Providers ON Purchase_orders.id_provider = Providers.id
GROUP BY Purchase_Orders.send_date, Purchase_Orders.nro_purchase_order, Providers.business_name, Providers.cod_provider, Purchase_Orders.estado, Purchase_Orders.total

-- 9. Mostrar el detalle de una orden de compra del proveedor 3, trayendo: SKU del producto, nombre producto, cantidad y subtotal.

SELECT Articles.cod_article, Articles.name, Details.amount, Details.subtotal
FROM Details 
    LEFT JOIN Articles ON Details.id_article = Articles.id
WHERE (Articles.id_provider = 3)

-- 10. Cambiar el estado a Cancelada y la fecha de modificación a la orden de compra con ID = 4.

UPDATE Purchase_Orders
SET estado = 'C', updated_at = GETDATE()
WHERE (id = 4);

select Purchase_Orders.*
from Purchase_Orders
where id = 4

-- 11. Escribir la sentencia para eliminar el producto con id = 1 (NO EJECUTAR, SÓLO MOSTRAR SENTENCIA)

DELETE FROM Images 
WHERE (Images.id_article = 1)

DELETE FROM Articles
WHERE (Articles.id = 1)
