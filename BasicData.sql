select m.IDMovie, m.Title, m.OriginalTitle, m.Description, m.Duration, 
	m.PublishedDate, m.StartDate, m.PicturePath, g.Name, mr.Name, 
	pd.FirstName, pd.MiddleName, pd.LastName
from Movie as m
inner join MovieGenre as mg
on mg.MovieID = m.IDMovie
inner join Genre as g
on g.IDGenre = mg.GenreID
inner join MoviePerson as mp
on mp.MovieID = m.IDMovie
inner join MovieRole as mr
on mr.IDMovieRole = mp.MovieRoleID
inner join PersonData as pd
on pd.IDPersonData = mp.PersonDataID
where m.IDMovie = 15 or m.IDMovie = 16

go


insert into UserRole ([Name]) values ('admin')
insert into UserRole ([Name])  values ('user')
insert into MovieRole ([Name])  values ('director')
insert into MovieRole ([Name])  values ('actor')
go


insert Movie ([Title], [OriginalTitle], [Description], [Duration], [PublishedDate], [StartDate], [PicturePath]) values
('Title 1', 'Original title 1', 'Description 1', 150, '2011-12-03T10:15:30', '2020-12-03T10:15:30', 'picture path 1' )
---index 1 
insert Movie ([Title], [OriginalTitle], [Description], [Duration], [PublishedDate], [StartDate], [PicturePath]) values
('Title 2', 'Original title 2', 'Description 2', 150, '2021-12-03T10:15:30', '2020-12-03T10:15:30', 'picture path 2' )
---index 2
go
insert Genre ([Name]) values ('Horor')
---index 1
insert Genre ([Name]) values ('Triler')
---index 2
insert Genre ([Name]) values ('Drama')
---index 3
go
insert MovieGenre ([GenreID], [MovieID]) values (1, 1)
insert MovieGenre ([GenreID], [MovieID]) values (3, 1)
insert MovieGenre ([GenreID], [MovieID]) values (2, 2)
insert MovieGenre ([GenreID], [MovieID]) values (1, 2)
go
insert PersonData ([FirstName], [MiddleName], [LastName]) values ('Pero', 'nema', 'Peric')
--index 1
insert PersonData ([FirstName], [MiddleName], [LastName]) values ('Iva', 'nema', 'Ivic')
---index 2
insert PersonData ([FirstName], [MiddleName], [LastName]) values ('Maja', 'nema', 'Majic')
---index 3
go
insert MoviePerson ([PersonDataID], [MovieRoleID], [MovieID]) values (1, 1, 1)
insert MoviePerson ([PersonDataID], [MovieRoleID], [MovieID]) values (1, 2, 1)
insert MoviePerson ([PersonDataID], [MovieRoleID], [MovieID]) values (1, 2, 2)
insert MoviePerson ([PersonDataID], [MovieRoleID], [MovieID]) values (2, 2, 1)
insert MoviePerson ([PersonDataID], [MovieRoleID], [MovieID]) values (2, 1, 2)
insert MoviePerson ([PersonDataID], [MovieRoleID], [MovieID]) values (2, 2, 2)
go

select * from MoviePerson
select * from PersonData
select * from MovieGenre
select * from Movie
select * from MovieGenre
select * from Genre
go
select *
from MoviePerson as mp
inner join MovieRole as mr
on mp.MovieRoleID = mr.IDMovieRole
inner join PersonData as pd
on pd.IDPersonData = mp.PersonDataID
inner join Movie as m
on m.IDMovie = mp.MovieID
go



go
delete MovieGenre
delete MoviePerson
delete Genre
delete MovieRole
delete PersonData
delete Movie