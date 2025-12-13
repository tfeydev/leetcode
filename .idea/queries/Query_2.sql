-- sicherstellen, dass der User existiert
CREATE USER leetcode WITH PASSWORD 'changeme';

-- Eigentümer der Datenbank setzen
ALTER DATABASE leetcode_db OWNER TO leetcode;

-- alle Rechte auf die Datenbank geben
GRANT ALL PRIVILEGES ON DATABASE leetcode_db TO leetcode;
