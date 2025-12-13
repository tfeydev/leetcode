-- Create user for new databse
CREATE USER leetcode WITH PASSWORD 'changeme';

-- Create new database
CREATE DATABASE leetcode_db OWNER leetcode;

-- Grant all rights to the new user on the new database
GRANT ALL PRIVILEGES ON DATABASE leetcode_db TO leetcode;