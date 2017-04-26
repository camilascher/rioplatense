CREATE TABLE `empleado` (
  `idempleado` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `dni` int(11) DEFAULT NULL,
  `tarjeta` varchar(45) DEFAULT NULL,
  `bonificado` decimal(5,0) DEFAULT NULL,
  `bonif_tope` int(11) DEFAULT NULL,
  PRIMARY KEY (`idempleado`),
  UNIQUE KEY `idempleado_UNIQUE` (`idempleado`),
  UNIQUE KEY `tarjeta_UNIQUE` (`tarjeta`),
  UNIQUE KEY `dni_UNIQUE` (`dni`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE `parametros` (
  `idparametros` int(11) NOT NULL AUTO_INCREMENT,
  `codigo` varchar(45) NOT NULL,
  `valor` varchar(45) NOT NULL,
  PRIMARY KEY (`idparametros`),
  UNIQUE KEY `codigo_UNIQUE` (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

CREATE TABLE `producto` (
  `idproducto` int(11) NOT NULL,
  `descripcion` varchar(45) NOT NULL,
  `precio` int(11) NOT NULL,
  PRIMARY KEY (`idproducto`),
  UNIQUE KEY `idproducto_UNIQUE` (`idproducto`),
  UNIQUE KEY `descripcion_UNIQUE` (`descripcion`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `usuario` (
  `idusuario` int(11) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `clave` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idusuario`),
  UNIQUE KEY `nombre_UNIQUE` (`nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `pedido` (
  `idpedido` int(11) NOT NULL,
  `idempleado` int(11) NOT NULL,
  `fecha` datetime NOT NULL,
  `bonificacion` decimal(10,0) DEFAULT NULL,
  `total` decimal(11,2) NOT NULL,
  `usuarioid_creacion` int(11) NOT NULL,
  `eliminado` int(11) NOT NULL,
  PRIMARY KEY (`idpedido`),
  KEY `fk_emp_id` (`idempleado`),
  CONSTRAINT `fk_emp_id` FOREIGN KEY (`idempleado`) REFERENCES `empleado` (`idempleado`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE `detalle_pedido` (
  `idpedido` int(11) NOT NULL,
  `idproducto` int(11) NOT NULL,
  `precio` decimal(11,2) NOT NULL,
  `cantidad` int(11) NOT NULL,
  PRIMARY KEY (`idpedido`,`idproducto`),
  KEY `fk_prod_id` (`idproducto`),
  CONSTRAINT `fk_prod_id` FOREIGN KEY (`idproducto`) REFERENCES `producto` (`idproducto`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



