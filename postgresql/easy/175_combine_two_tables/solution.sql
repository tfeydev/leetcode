SET search_path TO lc_175, public;

SELECT 
    p.firstname,
    p.lastname,
    a.city,
    a.state
FROM person p
LEFT JOIN address a ON p.personid = a.personid;
