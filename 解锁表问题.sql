select t2.SID,t2.SERIAL# a from v$locked_object t1,v$session t2 where t1.SESSION_ID = t2.SID
alter system kill session 'sid,SERIAL#';
