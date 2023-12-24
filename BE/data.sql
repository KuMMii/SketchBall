Select * from diary;
Select * from user;
Select * from teams;

delete from user WHERE id = 'a';
DELETE FROM user WHERE id = '1';

delete from user;
-- user
INSERT INTO user(id, pw, name, nickname, team, statusMessage) VALUE ('a', 'a', '농담곰', '잠실의 주인', 1, '두산짱');
INSERT INTO user(id, pw, name, nickname) VALUE ('1', '1', '아현정', '야구장에 끌려가는 사람');

delete from diary;


-- diary
INSERT INTO diary(userId, title, content,homeTeam,homeScore,awayTeam,awayScore) VALUE ('a','최주환 데려와라', '주딱아 대타딱 해...',1,4,2,2);
INSERT INTO diary(userId, title, content, isStadium,result,homeTeam,homeScore,awayTeam,awayScore) VALUE ('a','test1', 'test1', 1, 1,1,4,3,3);
INSERT INTO diary(userId, title, content, isStadium,result,homeTeam,homeScore,awayTeam,awayScore) VALUE ('a','test2', 'test2', 1, 1,1,4,3,3);
INSERT INTO diary(userId, title, content, isStadium,result,homeTeam,homeScore,awayTeam,awayScore) VALUE ('a','test3', 'test3', 1, 1,1,4,3,3);
INSERT INTO diary(userId, title, content, isStadium,result,homeTeam,homeScore,awayTeam,awayScore) VALUE ('a','test4', 'test4', 1, 1,1,4,3,3);
INSERT INTO diary(userId, title, content, isStadium,result,homeTeam,homeScore,awayTeam,awayScore) VALUE ('a','test5', 'test5', 1, 1,1,4,3,3);

drop table teams;
-- teams
INSERT INTO teams(teamName, teamImg) VALUE ('두산 베어스', '../assets/pic/teamLogo/doosan.png');
INSERT INTO teams(teamName, teamImg) VALUE ('LG 트윈스', '../assets/pic/teamLogo/lg.png');
INSERT INTO teams(teamName, teamImg) VALUE ('한화 이글스', '../assets/pic/teamLogo/hanhwa.png');
INSERT INTO teams(teamName, teamImg) VALUE ('KIA 타이거즈', '../assets/pic/teamLogo/kia.jpg');
INSERT INTO teams(teamName, teamImg) VALUE ('키움 히어로즈', '../assets/pic/teamLogo/kiwoom.png');
INSERT INTO teams(teamName, teamImg) VALUE ('kt wiz', '../assets/pic/teamLogo/kt.jpg');
INSERT INTO teams(teamName, teamImg) VALUE ('롯데 자이언츠', '../assets/pic/teamLogo/lotte.png');
INSERT INTO teams(teamName, teamImg) VALUE ('NC 다이노스', '../assets/pic/teamLogo/nc.png');
INSERT INTO teams(teamName, teamImg) VALUE ('삼성 라이온즈', '../assets/pic/teamLogo/samsung.jpg');
INSERT INTO teams(teamName, teamImg) VALUE ('SSG 랜더스', '../assets/pic/teamLogo/ssg.jpg');

SELECT *,
(SELECT teamName FROM teams WHERE homeTeam=teamId) homeTeamName,
(SELECT teamName FROM teams WHERE awayTeam=teamId) awayTeamName,
(SELECT teamImg FROM teams WHERE homeTeam=teamId) homeTeamImg,
(SELECT teamImg FROM teams WHERE awayTeam=teamId) awayTeamImg
FROM diary
WHERE userId='a' AND
		STR_TO_DATE(20231119,'%Y%m%d')= gamedate;

SELECT d.*, t1.teamName as homeTeamName, t2.teamName as awayTeamName, t3.teamImg as homeTeamImg, t4.teamImg as awayTeamImg
FROM diary d
LEFT JOIN teams t1 ON d.homeTeam=t1.teamId 
LEFT JOIN teams t2 ON d.awayTeam=t2.teamId
LEFT JOIN teams t3 ON d.homeTeam=t3.teamId
LEFT JOIN teams t4 ON d.awayTeam=t4.teamId
WHERE userId='a' AND
		STR_TO_DATE(20231119,'%Y%m%d')= gamedate;
        
        SELECT 
		userId, 
		(SELECT nickname FROM user WHERE id='ssafy1') nickname,
		date_format(NOW(),'%Y') as year,
		(SELECT count(*) FROM diary
		WHERE userId='ssafy1' AND isStadium=1) as totalCount,
		(SELECT count(*)	FROM diary 
		WHERE userId='ssafy1' AND
		isStadium=1 and result=1) as winningCount,
		(SELECT round((SELECT count(*) FROM diary 
		WHERE	userId='ssafy1' AND isStadium=1 and result=1) /
		(SELECT count(*) FROM diary WHERE userId='ssafy1' AND isStadium=1) * 100, 1)) as percent
		FROM diary
		WHERE
		userId='ssafy1'
		AND date_format(gamedate,'%Y')=date_format(NOW(),'%Y');
        
SELECT 
    d.userId, 
    u.nickname,
    date_format(NOW(),'%Y') as year,
    COUNT(CASE WHEN d.userId='a' AND d.isStadium=1 THEN 1 END) as totalCount,
    COUNT(CASE WHEN d.isStadium=1 AND d.result=1 THEN 1 END) as winningCount,
    ROUND((COUNT(CASE WHEN d.isStadium=1 AND d.result=1 THEN 1 END) / COUNT(CASE WHEN d.userId='a' AND d.isStadium=1 THEN 1 END)) * 100, 1) as percent
FROM
    user u
LEFT JOIN
    diary d ON u.id = d.userId AND d.userId = 'a' AND date_format(d.gamedate,'%Y') = date_format(NOW(),'%Y')
WHERE
    u.id = 'a';

