INSERT
INTO shops(id,
           address,
           city,
           description,
           latitude,
           longitude,
           name,
           phone_number,
           rating,
           profile_pic)
VALUES ('fcfc15e0-00e0-4076-83c2-1e03aed888c8',
        'st.Test',
        'Bodrum',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent nec velit rutrum, sollicitudin ligula sit amet, auctor ante. Nulla consequat est nulla. Duis vel leo.',
        37.034355,
        27.432339,
        '1st autoshop',
        '0881234586',
        3,
        'https://images.unsplash.com/photo-1486006920555-c77dcf18193c?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1159&q=80')
on conflict do nothing;

INSERT
INTO shops(id,
           address,
           city,
           description,
           latitude,
           longitude,
           name,
           phone_number,
           rating,
           profile_pic)
VALUES ('3ef5c55f-880f-45f5-8609-57a888b78e1e',
        'st.Test',
        'Bodrum',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent nec velit rutrum, sollicitudin ligula sit amet, auctor ante. Nulla consequat est nulla. Duis vel leo.',
        37.035976,
        27.435175,
        '2nd autoshop',
        '0881234586',
        1,
        'https://images.unsplash.com/photo-1567602901358-5ba00815eb15?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1050&q=80')
on conflict do nothing;

INSERT
INTO shops(id,
           address,
           city,
           description,
           latitude,
           longitude,
           name,
           phone_number,
           rating,
           profile_pic)
VALUES ('e3672584-baf4-46c0-bf12-d807705d9735',
        'st.Test',
        'Bodrum',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent nec velit rutrum, sollicitudin ligula sit amet, auctor ante. Nulla consequat est nulla. Duis vel leo.',
        37.033239,
        27.440974,
        '3rd autoshop',
        '0881234586',
        0,
        'https://images.unsplash.com/photo-1597986346643-d54491ef85bb?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1050&q=80')
on conflict do nothing;

INSERT
INTO shops(id,
           address,
           city,
           description,
           latitude,
           longitude,
           name,
           phone_number,
           rating,
           profile_pic)
VALUES ('2d0accd4-5410-4ab3-a49d-e0997e400ee6',
        'st.Test',
        'Bodrum',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent nec velit rutrum, sollicitudin ligula sit amet, auctor ante. Nulla consequat est nulla. Duis vel leo.',
        37.035808,
        27.442691,
        '4th autoshop',
        '0881234586',
        5,
        'https://images.unsplash.com/photo-1570071677470-c04398af73ca?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=912&q=80')
on conflict do nothing;

INSERT
INTO shops(id,
           address,
           city,
           description,
           latitude,
           longitude,
           name,
           phone_number,
           rating,
           profile_pic)
VALUES ('36165f81-a402-40e0-a9e4-f08f1f095739',
        'st.Test',
        'Bodrum',
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent nec velit rutrum, sollicitudin ligula sit amet, auctor ante. Nulla consequat est nulla. Duis vel leo.',
        37.039634,
        27.434633,
        '5th autoshop',
        '0881234586',
        4,
        'https://images.unsplash.com/photo-1594818020972-e96e722493f7?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1050&q=80')
on conflict do nothing;
