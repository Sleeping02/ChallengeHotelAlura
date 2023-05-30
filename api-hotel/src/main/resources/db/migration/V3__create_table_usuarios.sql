create table tb_usuarios(
id_usuario int not null auto_increment,
nombre varchar(100),
email varchar(300) unique,
contrasena varchar(300),
primary key(id_usuario)
)   