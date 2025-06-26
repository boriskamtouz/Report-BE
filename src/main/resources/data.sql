-- INSERT INTO cbelements users
INSERT INTO users ( name )
VALUES
( 'Boris KAMTOU' ),
( 'Emmanuel Nkwendjeu' ),
( 'Georges Fodoup' ),
( 'Thierry Mbega' ),
( 'Ndongo Parole' );


-- CREATION of many reports
INSERT INTO report (id, created_at, updated_at, user_id)
VALUES (1, '2025-06-17T10:00:00', '2025-06-17T10:00:00', 1);

-- INSERT INTO cbelements table
INSERT INTO cbelements ( name, val )
VALUES
( 'Prière seule', '0' ),
( 'Méditation', '0' ),
( 'Lecture biblique', '0' ),
( 'Lecture de la littérature chrétienne', '0' ),
( 'Jeûne Partiel', '0' ),
( 'Jeûne complet', '0' ),
( 'Prière en Groupe', '0' ),
( 'Dîme', '0' ),
( 'Offrande', '0' ),
( 'Heure étude', '0' ),
( 'Heure de travail', '0' ),
( 'évangélisation', '0' ),
( 'Âme Gagnées', '0' );


-- INSERT INTO cbelements table
INSERT INTO categories ( name )
VALUES
( 'Prière seule' ),
( 'Méditation' ),
( 'Lecture biblique' ),
( 'Lecture de la littérature chrétienne' ),
( 'Jeûne Partiel' ),
( 'Jeûne complet' ),
( 'Prière en Groupe' ),
( 'Dîme' ),
( 'Offrande' ),
( 'Heure étude' ),
( 'Heure de travail' ),
( 'évangélisation' ),
( 'Âme Gagnées' );

