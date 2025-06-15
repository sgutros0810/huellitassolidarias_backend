-- --------------------------------------------------
-- Tabla users
-- --------------------------------------------------
INSERT INTO users (
    id, active, address, city, country, creation_date,
    email, identification, lastname, name, name_shelter,
    password, phone_number, profile_image_url, role,
    username, verified, website_url
) VALUES
      (1, 1, 'Calle Mayor, 10',   'MALAGA',    'Spain', '2025-01-10 09:00:00',
       'admin@example.com',    'ADMIN0001',  'Admin', 'Super', NULL,
       '11ebf7fd670b838d3e4ba0010488651fa6867fc8d45428b372f549b47a858bfc',
       '600000001', NULL,      'ADMIN',
       'superadmin', 1,      NULL),
      (2, 1, 'Av. Andalucía, 23', 'MALAGA',   'Spain', '2025-03-05 14:30:00',
       'protectoras@example.org','REF1002','Lopez', 'Miguel', 'Protectoras Unidas',
       '11ebf7fd670b838d3e4ba0010488651fa6867fc8d45428b372f549b47a858bfc',
       '600000002', 'https://refugio.org/logo.png', 'REFUGIO',
       'protectoras', 1,    'https://refugio.org'),
      (3, 0, 'C/ de la Paz, 5',   'MALAGA',  'Spain', '2025-02-20 11:15:00',
       'huellitas@example.org','REF1003','Martinez','Ana',   'Huellitas',
       '11ebf7fd670b838d3e4ba0010488651fa6867fc8d45428b372f549b47a858bfc',
       '600000003', 'https://huellitas.org/logo.jpg', 'REFUGIO',
       'huellitas', 0,     'https://huellitas.org'),
      (4, 1, 'Paseo del Prado, 8','MALAGA',    'Spain', '2025-04-12 16:00:00',
       'pedrog@example.com',  'ID2004',  'Gonzalez','Pedro', NULL,
       '11ebf7fd670b838d3e4ba0010488651fa6867fc8d45428b372f549b47a858bfc',
       '600000004', NULL,      'USUARIO',
       'pedrog',    1,     NULL),
      (5, 0, '10 Rue de Rivoli',  'MALAGA',     'Spain','2025-05-01 10:20:00',
       'laura.fernandez@example.fr','ID2005','Fernandez','Laura', NULL,
       '11ebf7fd670b838d3e4ba0010488651fa6867fc8d45428b372f549b47a858bfc',
       '600000005', NULL,      'USUARIO',
       'lauraf',    0,     NULL);

-- --------------------------------------------------
-- Tabla adoptions
-- --------------------------------------------------
INSERT INTO adoptions (
    id, birth_date, breed, contact_email, contact_phone,
    created_at, description, gender, image_url, city,
    name, size, species, status, sterilized, vaccinated, id_user
) VALUES
      (1, '2023-04-15', 'Labrador',   'adoptadog1@example.com', '600100100',
       '2025-06-01 10:00:00', 'Friendly and energetic dog', 'MALE',
       'https://example.com/dog1.jpg',    'MALAGA', 'Max',   'Large', 'DOG',   'AVAILABLE',    1, 1, 2),
      (2, '2022-12-01', 'Siamese',    'adoptacat@example.com',   '600200200',
       '2025-05-20 12:45:00', 'Calm and affectionate cat', 'FEMALE',
       'https://example.com/cat1.jpg',    'MALAGA','Luna',  'Small', 'CAT',   'RESERVED',     0, 1, 2),
      (3, '2024-02-10', 'Dutch',      'adoptatbunny@example.com','600300300',
       '2025-04-10 09:30:00', 'Soft and cuddly rabbit',    'UNKNOWN',
       'https://example.com/rabbit1.jpg','MALAGA','Bunny','Small','RABBIT','UNDER_REVIEW',0, 0, 2),
      (4, '2021-07-20', 'African Grey','adoptaparrot@example.com','600400400',
       '2025-03-15 16:00:00', 'Talkative parrot',          'FEMALE',
       'https://example.com/parrot1.jpg','MALAGA','Kiwi', 'Medium','PARROT','ADOPTED',      1, 1, 3),
      (5, '2023-09-05', 'Other',      'adoptafish@example.com',  '600500500',
       '2025-02-28 11:20:00', 'Colorful aquarium fish',    'UNKNOWN',
       'https://example.com/fish1.jpg',    'MALAGA', 'Goldie','Small','GOLDFISH','AVAILABLE',   0, 1, 3);

-- --------------------------------------------------
-- Tabla animals_reports
-- --------------------------------------------------
INSERT INTO animals_reports (
    id, description, image_url, location,
    name, report_date, state, contact_name, contact_phone, id_user
) VALUES
      (1, 'Cat spotted near highway',  'https://example.com/report1.jpg',
       'Madrid, Spain',   'Unknown', '2025-06-05 08:15:00', 'FOUND', 'Denis', '65777444',     1),
      (2, 'Injured dog in park',       'https://example.com/report1.jpg',
       'Sevilla, Spain',  'Spot',    '2025-06-04 14:00:00', 'MISSING', 'Denis', '65777444',   5),
      (3, 'Lost turtle crossing road',  'https://example.com/report3.jpg',
       'Valencia, Spain', 'Torty',   '2025-06-03 20:45:00', 'MISSING', 'Denis', '65777444',      4),
      (4, 'Bird nest fallen',           'https://example.com/report1.jpg',
       'Barcelona, Spain','Baby Bird','2025-06-02 06:30:00', 'MISSING', 'Denis', '65777444', 4),
      (5, 'Stray goat in garden',       'https://example.com/report5.jpg',
       'Paris, France',   'Billy',   '2025-06-01 12:00:00', 'MISSING', 'Denis', '65777444',     5);

-- --------------------------------------------------
-- Tabla posts
-- --------------------------------------------------
INSERT INTO posts (
    id, category, content, created_at,
    image_url, title, id_user
) VALUES
      (1, 'ADOPTION', 'Se buscan adoptantes para perros senior...',   '2025-05-30 09:00:00',
       'https://example.com/post1.jpg', 'Adopta un senior', 2),
      (2, 'ADVICE',   'Cómo cuidar a tu gato: alimentación y juegos...', '2025-05-28 15:45:00',
       NULL,                              'Consejos para gatos', 4),
      (3, 'RESCUE',   'Rescate de un caballo herido en la montaña...', '2025-05-25 11:20:00',
       'https://example.com/post3.jpg', 'Rescate equino',    3),
      (4, NULL,       'Próximamente evento de adopción masiva...',    '2025-06-06 08:00:00',
       NULL,                              'Próximos eventos',    1),
      (5, 'RESCUE',   'Campaña de rescate de aves migratorias...',      '2025-05-20 14:10:00',
       'https://example.com/post5.jpg', 'Rescate de aves',    5);

-- --------------------------------------------------
-- Tabla comments
-- --------------------------------------------------
INSERT INTO comments (
    id, content, created_at, id_post, id_user
) VALUES
      (1, '¡Me interesa adoptar!',                         '2025-06-02 10:30:00', 1, 4),
      (2, 'Excelente artículo, gracias por los consejos.', '2025-06-03 16:00:00', 2, 5),
      (3, '¿Dónde puedo ayudar en el rescate?',            '2025-06-04 09:15:00', 3, 1),
      (4, 'Gracias por compartir la información.',          '2025-06-06 12:20:00', 4, 3),
      (5, 'Mucha suerte con la campaña.',                  '2025-06-05 18:45:00', 5, 3);
