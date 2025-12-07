SELECT 
    p.firstname,
    p.lastname,
    a.city,
    a.state
FROM lc_175.person p
LEFT JOIN lc_175.address a ON p.personid = a.personid;
