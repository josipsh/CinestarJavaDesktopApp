create database MovieTheater
go
use MovieTheater
go

create table Genre(
	IDGenre int primary key identity,
	Name nvarchar(25) not null 
)
go

create table MovieRole(
	IDMovieRole int primary key identity,
	Name nvarchar(25) not null 
)
go
create table PersonData(
	IDPersonData int primary key identity,
	FirstName nvarchar(25) not null,
	MiddleName nvarchar(25) not null,
	LastName nvarchar(25) not null
)
go

create table Movie (
	IDMovie int primary key identity,
	Title nvarchar(100) not null,
	OriginalTitle nvarchar(100) not null,
	Description nvarchar(1000) not null,
	Duration int not null,
	PublishedDate nvarchar(25) not null,
	StartDate nvarchar(25) not null,
	PicturePath nvarchar(200) not null
)
go


create table MovieGenre (
	IDMovieGenre int primary key identity,
	GenreID int foreign key references Genre(IDGenre) not null,
	MovieID int foreign key references Movie(IDMovie)
)
go

create table MoviePerson(
	IDMoviePerson int primary key identity,
	PersonDataID int foreign key references PersonData(IDPersonData) not null,
	MovieRoleID int foreign key references MovieRole(IDMovieRole) not null,
	MovieID int foreign key references Movie(IDMovie)
)
go

create table UserRole(
	IDUserRole int primary key identity,
	Name nvarchar(25) not null
)
go

create table ClientUser(
	IDClientUser int primary key identity,
	PersonDataId int foreign key references PersonData(IDPersonData) not null,
	UserRoleID int foreign key references UserRole(IDUserRole) not null,
	Email nvarchar(50) not null unique,
	Password nvarchar(50) not null,
	AddDate nvarchar(25) not null
)
go



-------------------------------
------Inserting basic data-----
-------------------------------
insert into UserRole ([Name]) values ('admin')
insert into UserRole ([Name])  values ('user')
insert into MovieRole ([Name])  values ('director')
insert into MovieRole ([Name])  values ('actor')
go

insert Movie ([Title], [OriginalTitle], [Description], [Duration], [PublishedDate], [StartDate], [PicturePath]) values
('Title 1', 'Original title 1', 'Description 1', 150, '2021-06-09', '2021-06-09', 'picture path 1' )
---index 1 
insert Movie ([Title], [OriginalTitle], [Description], [Duration], [PublishedDate], [StartDate], [PicturePath]) values
('Title 2', 'Original title 2', 'Description 2', 150, '2021-06-09', '2021-06-09', 'picture path 2' )
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

---------------------
------Procedures-----
---------------------

-------------------------Movie--------------------------------------
create procedure getMovie
( @id int )as
begin
	select * from Movie where IDMovie = @id
end
go

create procedure getMovies
as
begin 
	select * from Movie
end
go

create procedure createMovie
(
	@title nvarchar(100),
	@originalTitle nvarchar(100),
	@description nvarchar(1000),
	@duration int,
	@publishedDate nvarchar(25),
	@startDate nvarchar(25),
	@picturePath nvarchar(200),

	@id int output
)as
begin 
	if (select count(*) from Movie as m where m.title = @title and m.OriginalTitle = @originalTitle) = 0
	begin 
		insert Movie ([Title], [OriginalTitle], [Description], [Duration], [PublishedDate], [StartDate], [PicturePath]) 
		values (@title, @originalTitle, @description, @duration, @publishedDate, @startDate, @picturePath)
		
		set @id = Scope_Identity()
		
	end
	else
	begin 
		select @id = m.IDMovie from Movie as m where m.title = @title and m.OriginalTitle = @originalTitle

	end
end
go

create procedure updateMovie
(
	@idMovie int,
	@title nvarchar(100),
	@originalTitle nvarchar(100),
	@description nvarchar(1000),
	@duration int,
	@publishedDate nvarchar(25),
	@startDate nvarchar(25),
	@picturePath nvarchar(200)
)as
begin 
	update Movie  set
			Title = @title,
			OriginalTitle = @originalTitle,
			Description = @description,
			Duration = @duration,
			PublishedDate = @publishedDate,
			StartDate = @startDate,
			PicturePath = @picturePath
	where IDMovie = @idMovie
end
go

create procedure deleteMovie
( @id int )as
begin 
	delete from Movie where IDMovie = @id
end
go

---------------------Genre--------------------------------------------

create procedure getGenre
( @id int )as
begin 
	select * 
	from Genre
	where IDGenre = @id
end
go

create procedure getGenres
as
begin 
	select * 
	from Genre
end
go

create procedure createGenre
( 
@name nvarchar(25), 
@id int output 
)as
begin 
	if (select count(*) from Genre as g where g.Name = @name) = 0
	begin 
		insert Genre (Name) values (@name)
		
		set @id = Scope_Identity()
	end
	else
	begin 
		select @id = g.IDGenre from Genre as g where g.Name = @name
	end
end
go

create procedure updateGenre
( @id int, @name nvarchar(25))as
begin 
	update Genre set
		Name = @name
	where IDGenre = @id
end
go

create procedure deleteGenre
( @id int )as
begin 
	delete from Genre
	where IDGenre = @id
end
go

-------------------------------MovieGenre--------------------------------
create procedure getMovieGenre
( @id int )as
begin
	select count(*) from MovieGenre where IDMovieGenre = @id
end
go

create procedure getMovieGenres
as
begin
	select * from MovieGenre
end
go

create procedure createMovieGenre
(
	@genreID int,
	@movieID int,

	@id int output
)as
begin
	if(select count(*) from MovieGenre where GenreID = @genreID and MovieID = @movieID) = 0
	begin
		insert MovieGenre ([GenreID], [MovieID]) values (@genreID, @movieID)
		
		declare @lastId int
		set @lastId = Scope_Identity()
		return @lastId
	end
	else
	begin 
		select @id = mg.IDMovieGenre from MovieGenre as mg where GenreID = @genreID and MovieID = @movieID
	end
end
go

create procedure updateMovieGenre
(
	@IDMovieGenre int,
	@genreID int,
	@movieID int
)as
begin
	update MovieGenre set
	GenreID = @genreID
	where IDMovieGenre = @IDMovieGenre and MovieID = @movieID
end
go

create procedure deleteMovieGenre
( @movieId int )as
begin
	delete from MovieGenre where MovieID = @movieId
end
go

---------------------MoviePerson--------------------------

create procedure getMoviePersonFilteredByMovie
( @id int )as
begin
	select * from MoviePerson where IDMoviePerson = @id
end
go

create procedure getMoviePersonFilteredByPersonData
( @personDataId int )as
begin
	select * from MoviePerson where PersonDataID = @personDataId
end
go

create procedure getMoviePersons
as
begin 
	select * from MoviePerson
end
go

create procedure createMoviePerson
(
	@personDataID int,
	@movieRoleID int,
	@movieID int,

	@id int output
)as
begin 
	if (select count(*) from MoviePerson where PersonDataID = @personDataID and MovieID = @movieID and MovieRoleID = @movieRoleID) = 0
	begin
		insert MoviePerson ([PersonDataID], [MovieRoleID], [MovieID]) values (@personDataID, @movieRoleID, @movieID)

		set @id = Scope_Identity()
	end
	else
	begin 
		select @id = mp.IDMoviePerson from MoviePerson as mp where PersonDataID = @personDataID and MovieID = @movieID and MovieRoleID = @movieRoleID
	end
end
go

create procedure updateMoviePerson
(
	@personDataID int,
	@movieRoleID int,
	@movieID int,
	@id int
)as
begin 
	update MoviePerson set
		MovieID = @movieID,
		PersonDataID = @personDataID,
		MovieRoleID = @movieRoleID
	where IDMoviePerson = @id
end
go

create procedure deleteMoviePersonFilteredByMovie
( @movieId int )as
begin 
	delete from MoviePerson where MovieID = @movieId
end
go

create procedure deleteMoviePersonFilteredByPersonData
( @personDataId int )as
begin 
	delete from MoviePerson where PersonDataID = @personDataId
end
go

--------------------------------PersonData------------------

create procedure getPersonData
( @id int )as
begin 
	select * from PersonData where IDPersonData = @id
end
go

create procedure getPersonsData
as
begin
	select * from PersonData where IDPersonData not in (select PersonDataId from ClientUser)
end
go

create procedure createPersonData
(
	@FirstName nvarchar(25),
	@MiddleName nvarchar(25),
	@LastName nvarchar(25),
	@id int output
)as
begin
	if (select count(*) from PersonData where FirstName = @FirstName and LastName = @LastName and MiddleName = @MiddleName) = 0
	begin
		insert PersonData ([FirstName], [MiddleName], [LastName]) values (@FirstName, @MiddleName, @LastName)

		set @id = Scope_Identity()
	end
	else
	begin
		select @id = pd.IDPersonData from PersonData as pd where FirstName = @FirstName and LastName = @LastName and MiddleName = @MiddleName
	end
end
go

create procedure updatePersonData
(
	@id int,
	@FirstName nvarchar(25),
	@MiddleName nvarchar(25),
	@LastName nvarchar(25)
	 
)as
begin 
	update PersonData set
		FirstName = @FirstName,
		LastName = @LastName,
		MiddleName = @MiddleName
	where IDPersonData = @id
end
go

create procedure deletePersonData
( @id int )as
begin
	delete from PersonData where IDPersonData = @id
end
go

------------------------MovieRole-------------------

create procedure getMovieRole
( @id int )as
begin 
	select * from MovieRole where IDMovieRole = @id
end
go

create procedure getMovieRoles
as
begin 
	select * from MovieRole
end
go

create procedure createMovieRole
(
	@name nvarchar(25),
	@id int output
)as
begin 
	if(select count(*) from MovieRole where Name = @name) = 0
	begin 
		insert MovieRole (Name) values (@name)

		declare @lastId int
		set @lastId = Scope_Identity()
		return @lastId
	end
	else
	begin
		select @id = mr.IDMovieRole from MovieRole as mr where Name = @name
	end
end
go

create procedure updateMovieRole
(
	@id int,
	@name nvarchar(25)
) as
begin
	update MovieRole set
		Name = @name
	where IDMovieRole = @id
end
go

create procedure deleteMovieRole
( @id int )as
begin 
	delete from MovieRole where IDMovieRole = @id	
end
go


create procedure whatRoleIsPerson
( 
	@personId int, 
	@result int output
)as
begin 
	declare @value int

	--if person is actor, return 1
	--if person is director, return -1
	--if person is actor and director, return 0
	 
	if	exists (
		select * 
		from MoviePerson as mp
		where mp.PersonDataID = @personId and mp.MovieRoleID = 2) 
	begin
		set @value = 1
	end
	else if exists(
		select * 
		from MoviePerson as mp
		where mp.PersonDataID = @personId and mp.MovieRoleID = 1)
	begin
		set @value = -1
	end
	else if exists(
		select * 
		from MoviePerson as mp
		where mp.PersonDataID = @personId and mp.MovieRoleID = 2 and mp.MovieRoleID = 1)
	begin
		set @value = 0
	end
	else if not exists(
		select * 
		from MoviePerson as mp
		where mp.PersonDataID = @personId and mp.MovieRoleID = 2 and mp.MovieRoleID = 1)
	begin
		set @value = 123
	end

	set @result = @value
end
go

------------------------UserRole-------------------

create procedure getUserRole
( @id int )as
begin 
	select * from UserRole where IDUserRole = @id
end
go

create procedure getUserRoles
as
begin 
	select * from UserRole
end
go

create procedure createUserRole
(
	@name nvarchar(25),
	@id int output
)as
begin 
	if(select count(*) from UserRole where Name = @name) = 0
	begin 
		insert UserRole (Name) values (@name)

		declare @lastId int
		set @lastId = Scope_Identity()
		return @lastId
	end
	else
	begin
		select @id = ur.IDUserRole from UserRole as ur where Name = @name
	end
end
go

create procedure updateUserRole
(
	@id int,
	@name nvarchar(25)
) as
begin
	update UserRole set
		Name = @name
	where IDUserRole = @id
end
go

create procedure deleteUserRole
( @id int )as
begin 
	delete from Role where IDUserRole = @id	
end
go


--------------------------ClientUser--------------

create procedure getClientUser
( @email nvarchar(50) )as
begin
	select * from ClientUser as c where c.Email = @email
end
go

create procedure getClientUsers
as
begin
	select * from ClientUser
end
go

create procedure createClientUser
(
	@personDataId int,
	@userRoleID int,
	@email nvarchar(50),
	@password nvarchar(50),
	@addDate nvarchar(25),

	@id int output
)as
begin 
	if ( select count(*) from ClientUser where PersonDataId = @personDataId and Email = @email) = 0
	begin
		insert ClientUser ([PersonDataId], [UserRoleID], [Email], [Password], [AddDate]) values (@personDataId, @userRoleID, @email, @password, @addDate)

		declare @lastId int
		set @lastId = Scope_Identity()
		return @lastId
	end
	else
	begin
		select @id = cu.IDClientUser from ClientUser as cu where PersonDataId = @personDataId and UserRoleID = @userRoleID
	end
end
go

create procedure updateClientUser
(
	@id int,
	@personDataId int,
	@userRoleID int,
	@email nvarchar(50),
	@password nvarchar(50)
) as
begin 
	update ClientUser set
		PersonDataId = @personDataId,
		UserRoleID = @userRoleID,
		Email = @email,
		Password = @password
	where IDClientUser = @id
end
go

create procedure deleteClientUser
( @id int )as
begin
	delete from ClientUser where IDClientUser = @id
end
go

create Type IdForDelete as table(
	ID int
)
go

create procedure deleteMovieData
as
begin

	declare @deletePersonDataIds IdForDelete

	insert into @deletePersonDataIds
	select PersonDataID
	from MoviePerson

	delete MoviePerson

	delete PersonData 
	where IDPersonData in 
	(
		select distinct * 
		from @deletePersonDataIds
	)

	delete MovieGenre
	delete Genre
	delete Movie
end
go
