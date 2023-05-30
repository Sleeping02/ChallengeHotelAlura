create table tb_huespedes(
id_huesped int auto_increment not null,
nombre varchar(100) not null,
apellido varchar(100) not null,
fecha_nacimiento date not null,
nacionalidad varchar(100) not null,
telefono varchar(20) not null,
id_reserva int not null,
primary key(id_huesped),
foreign key(id_reserva) references reservas(id_reserva))