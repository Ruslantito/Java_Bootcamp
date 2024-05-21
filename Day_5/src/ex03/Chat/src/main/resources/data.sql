insert into Users (login, password)
    values  ('Anna', 'Anna123'),
            ('Kat',  'Kat123'),
            ('John', 'John123'),
            ('Mike', 'Mike123'),
            ('Tito', 'Tito123');


insert into Chatrooms (name, owner)
    values  ('Aviation', 1),
            ('Cars', 2),
            ('Food', 1),
            ('Sport', 3),
            ('Health', 2),
            ('programming', 4);


insert into Messages (author, room, text)
    values  (1, 1, 'first flight'),
            (2, 3, 'first food'),
            (3, 2, 'first car'),
            (4, 3, 'second food'),
            (5, 2, 'second car');
