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


insert into Messages (author, room, text)
values (1, 1, 'Hello') returning id;


SELECT * FROM Messages
    ORDER BY id
    LIMIT 4
    OFFSET 3;


WITH selected_users AS (
    SELECT id as UserId, login, password FROM Users
    ORDER BY id
    OFFSET 0 LIMIT 3
   ),
    rooms_owner AS (
        SELECT su.UserId as userId, su.login, su.password, cr.id as roomId, cr.name as roomName, cr.owner, 0 as socialized
        FROM selected_users su
            LEFT JOIN Chatrooms cr ON su.UserId = cr.owner
   ),
    rooms_socilizedr AS (
        SELECT distinct su.UserId as userId, su.login, su.password, cr.id as roomId, cr.name as roomName, cr.owner, 1 as socialized
        FROM selected_users su
            LEFT JOIN Messages m ON su.UserId = m.author
            LEFT JOIN Chatrooms cr ON su.UserId != cr.owner
        WHERE m.room = cr.id
    )
SELECT * FROM rooms_socilizedr t1
UNION ALL
SELECT * FROM rooms_owner t2
ORDER BY socialized







SELECT u.id as UserId, u.login as Login, cr.id as RoomId, cr.name roomName
FROM Users u
    LEFT JOIN Chatrooms cr ON u.id = cr.owner
    LEFT JOIN Messages m ON m.room = cr.id
WHERE m.room not In (1, 2, 4)
ORDER BY UserId
-- LIMIT 2 OFFSET 3
-- GROUP BY u.id