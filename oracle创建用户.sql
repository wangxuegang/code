-- Create the user 
create user WANDA_WXG
  default tablespace SYSTEM
  temporary tablespace TEMP
  profile DEFAULT
  password expire;
-- Grant/Revoke role privileges 
grant connect to WANDA_WXG;
grant dba to WANDA_WXG;
grant resource to WANDA_WXG;
-- Grant/Revoke system privileges 
grant unlimited tablespace to WANDA_WXG;