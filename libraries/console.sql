CREATE DATABASE usuarios;
USE usuarios;

CREATE TABLE usuarios(
                         id INT AUTO_INCREMENT PRIMARY KEY ,
                         nombre VARCHAR(6) NOT NULL,
                         contraseña VARCHAR(15) NOT NULL
);

INSERT INTO usuarios(nombre, contraseña) VALUES
                                             ('admin','admin123'),
                                             ('user1', 'password1'),
                                             ('user2', 'password2');