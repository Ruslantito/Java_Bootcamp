DROP TABLE IF EXISTS    Users ,
                        Chatrooms,
                        Messages;

create table if not exists Users (
    id bigserial primary key ,
    login varchar not null ,
    password varchar not null
  );

create table if not exists Chatrooms (
    id bigserial primary key ,
    name varchar not null ,
    owner bigint not null ,

    constraint fk_Chatrooms_owner foreign key (owner) references Users(id)
);

create table if not exists Messages (
    id bigserial primary key ,
    author bigint not null ,
    room bigint not null ,
    text varchar ,
    dateTime timestamp default current_timestamp,

    constraint fk_Messages_author foreign key (author) references Users(id),
    constraint fk_Messages_room foreign key (room) references Chatrooms(id)
);
