Escape Blog
===============


Alpha
=======


Development Day 1
-----------------
I started working on the Escape game project today.
The first thing I did was read the rules and expectations. 
The first thing I started with is looking at the escape game manager. 
I added an abstract class called EscapeGameManagerImpl, which implements
EscapeGameManager as EscapeGameManager is not modifiable and it helps build
a good programming practice. I wrote TDD cases and also implemented tests to 
ensure that the game was building properly. 

Development Day 2
-----------------
I ensured that all my tests were still running just in case my progress had not been saved.
Firstly I wrote TDD tests to ensure that only 2 of the same coordinates can be used and that
each case gave the distance in an accurate manner.I started to work on Distance to for Coordinates 
and I modified my formula so that I subtract the x values and y values and then return which is higher 
between both of them as the return type is an integer and using the mathematical distance formula gives 
float numbers. 

I also modified my EscapeManagerImplement to have a constructor that does not need rules or gameInitializer,
as did not feel the need for both of those in there. I wrote TDD tests to ensure that make coordinate would 
make the correct type of coordinate each time, based on the input given by the EscapeManagerImplement. 
I designed it so that the CoordinateType given as an input to the manager is checked and depeding on what it
is a coordinate is made. My tests run successfully confirming that the right type of coordinate is made based on
the coordinate type Input. 

Development Day 3
-----------------
I ensured that nothing had been changed and that all my tests were still running. I decided
that I needed a board interface that could be implemented by different types of board classes. 
As this would make it easier in the future as Boards will be the place were actions happen.
I add functions like getPieceAt, LocationTyeRet and remove into the board as these will make 
most of the move functionality. I also modified my design to have my EscapeManagerImplement only
take in CoordinateType, xMax, YMax and a Board, as passing in a board will ensure that almost 
everything can be created smoothly. I wrote some TDD tests for my board and began working on 
GetPieceAt. My main thought was that I need to be able to move and store pieces based on a coordinate
given so I made a Hashmap where the coordinate was the key and the EscapePiece was the value. Now I 
went ahead and started working on getpieceAt which I thought would need to compare parameters from 
the locationInitializer the board contained so I did this. My pieces seemed not be storing so I turned 
to hashcode thinking this would help solve my problems. It seemed to work eventually as all the pieces 
were being attained correctly and my tests were passing. 

Development Day 4
-----------------
I once again made sure of passing tests and nothing changed with the funcationality. I thought of moving to 
the move function in my EscapeManagerImplement. In here I first planned out what conditions would result in true 
and what would result in false. Coming to that I wrote them as my TDD tests, I began will having a null from piece
which would result in false. This test passed. I next wrote down all the conditions when pieces move in my TDD tests 
such as pieces moving towards the exit, one on top of the other, players trying to move the other's pieces and moving
towards block. I returned a false if none of the true conditions were met. I tested these out and realized that they 
weren't passing. I realized I had messed up the block condition and the null condition which made it so that it was 
giving false everytime. I modified these to be exactly as described by the Escape Documentation. My tests passed
and it all seemed to be coming along.


Now I moved to the more detailed part of move, i.e moving the pieces and storing them. My thought process was that
whenever a true was yielded the piece had to be moved, except in the case of the exit, where no moving would take
place. In order to do so I had to implement my PlacePieceMethod in the board, so to do so I would put the piece and 
coordinate value, key pair in the pieces hashMap.I tried testing this but my pieces didn't seem to move. I couldn't 
understand why. I looked over the code but nothing seemed wrong to me. Upon looking at my getPieceAt method I realized 
that I was using the locationInitializer all the time which meant I was always checking the start state. I needed to 
intitialize my locationmap and pieces maps instead. So I created functions that would do so and fed them to the maps. 
I again tried using move and my pieces seemed to be moving now but they were duplicating. I realized I was missing the 
remove function. I decided to implement that in my board, basically so that when a coordinate is given that certain key
value pair will be removed.


I also realized my LocTypeRet function was not created. So I did that so that I was getting a certain value from the 
locmap based on the key coordinate value given. I used this to check locationTypes instead of the locationINitializer.
With all of these combined my tests were passing. 


Development Day 5
-----------------
Having completed everything and tests running successfully I just worked on the project to get my coverage up
to 95%. 


Development Day 6 
-----------------
Tested my code to ensure everything was working, refactored some if it to ensure duplication was removed. 


Development Day 7 
-----------------
Added final touches, and refactored code one final time. Did more testing to see if every requirement was met. 


------------------------------------------------------------------------------------------------------------------
Beta
====

Development Day 1 - 11/22/2020
-----------------

I started working on the beta project today. To ensure I had a solid understanding of the ideas, I read over the beta 
developer guide. I also drew out to a massive triangleBoard to ensure that I was getting the right idea for distance to. 
I manually counted the distance on the board that i created and then tried to figure out a formula that would work. I also 
looked over the documents that professor pollice posted about  triangle coordinates and how the R value works. I thought that
this might not be necessary as distance to was easier to get by doing abs delta x -delta y. I wrote out some tests to see if it
would work. It seemed to be working after running them. However I soon realized that it would not necessarily work for coordinates 
have the same y  but different x. I spend time trying to thing how it would work but I could not figure it, so I called it for the day. 

Development Day 2 11/23/2020
-----------------
I continued working on beta, initally testing to see if my test from yesterday was still wroking. I wrote out how distance was working 
for same y coordinates. It was 1 if they were next to each other and one was down and the other up and was only one above the current one. 
But it was different if that was not the case. I looked over the documents from Professor pollice again to see I could find another clue in 
there. It mentioned stuff about R values and them being 0 or 1. I figured I needed these as coordinates were up r was 1 and when they were 
upwards r was 0 and this what needed to be added. I wrote tests for these in my todo. Then I  made testcases in my tests. I then proceeded 
to implementing this in my code. I ran the tests and it was working. I made sure that
it was not just a stroke of luck and it was working for different cases, and it was. 


Now I thought that If i was to do pathfinding later I should make directionTo functions as I will need a neighors in a certain direction, and 
maybe even use the function to check,linearity. I knew square directions were going to be easier, so I wrote tests for them and decided to 
implement first. All the tests I wrote were passing and I confirmed that everything else was working fine as well. I had to implement triangle 
direction to now, and I realized this was going to be trickier. So I decided to assign directions based on delatx values and y values. The easiest 
to check were east and west directions. So I wrote tests for them first and then implemented, all of my tests passsing as I expected. Now I decided 
to assign directions such as southwest and northwest based on if the coordinates were up or down faced and delta  and ys. I wrote tests for these and 
everything seemed to be working. I made sure to add a None if direction was not found. This worked as well.

Development Day 3 11/28/2020
----------------- 

I opened up my beta project and tested to see if my tests were still working. After confirming working tests I thought of adding a
triangleBoard. After implementing it I realized the code was very similar to my squareboard and I would need to refactor it after 
the project was done. I decided to write tests for my triangleBoard, and ran them seeing them all run successfully. Now i thought 
that I need to add a neighborAt and neighboring coords function for all my coordinate types as I would use them later. I wrote tests
first to follow TDD patter for them and see how they would function. I first wanted to implement both functions for squarecoords
since I felt doing so would be easier. So I implemented them for squarecoord and ran my tests to see them passing. Now I had to 
do the same for triangles and I realized that neighbooring coords would be different based on how the piece was oriented. So I felt
that an upfaced coordinate could not have a southeast or southwest neighbor. And northeast and northwest for downfaced coords. I ran
my tests but saw them failing. I then went back to my function and realized that I forgot to change the x and y's that the neighbor 
would have. I did this and my tests passed. I ran my tests one more time and called it for the day.   
 

Development Day 4 11/30/2020
-----------------

I ran my tests again to see if everything was working and it was. So I felt now was the time to move onto pathfinding, I looked at 
some ideas for pathfinding and how it would function for this project. I am planning to use  Breadth first search so I looked at 
articles and videos pertaining to this. I wrote tests and  tried to implement my thinking. I was basically planning to go backwards 
starting at the to Coordinate and search for neighboring coords and then neighboring coords for all of them and the put them in 
HashMap that contained a coordinate type and LinkedList that would be the list of neighboring coords for a certain coordinate. I 
felt like if this loop ran for the max distance value times, and then at the end I could check if the from coord was in the hasmap
as a key. If it was true would be returned else a false. Realizing this I felt like I needed a function that gave the MaxpathLength
for any given escapePiece. In Order to implement this I had a piece take in a PieceAttribute[]. I wrote my TDD tests first and then 
moved on to implement The MaxPathlength which  searched the attributes to get a value for the distance value fora given piecetype. 
I ran my tests and they were passing. Then I put this into my pathfinding function and it was working for shortdistance but for longer
distances it wasn't really working. I decided to call it for the day not being able to figure out what the problem was.

Development Day 5 12/1/2020
----------------------------

I ran all my tests and saw them all pass. I also realized that professor pollice had released his pathfinding implementation from last year.
I downloaded the code and looked over trying to digest what he was doing exactly and how he was doing it. There were two classes Path and 
PathFindImpl and a PathFindngStartegy interface. I decided to look over path first, everything made sense to me except how the extend function 
was working. I kept looking over it until I undertsood it completely. Soon enough I realized that it was essentially cloning and add a new value
to the existing path a smarttactic indeed. I decided to look over the PathFindImpl now, and the first thing I realized looking over it was that 
Professor Pollice was trying to figure movement patterns. So i figured out it might be a good idea to have my EscapePieceNeeded constructor take
in a Movement pattern that pertains to the certain piece. I also had to modify my InitializePieceMap function in my Boards. I also had to add in
a pathFinderImpl into teh EscapemansgerImpl, taking in a board as an argument. Confirming that this was not breaking any of my previous functionality 
I also I wrote tests to see  if the function returned the right movement patterns. After testing it out and seeing success I decided to have a look at 
the searchforpath function he was doing something similar to what I had done, except he was checking doing orthogonal, diagnoal etc checks, which I needed.
I started writing  tests as to how this would work for my moves, specifically for squareboard.Instead of creating special orthogonal functions and diagonal 
functions I just used my Direction to function. I decided to run a test for path to aneighbor and it was working.I decided to call it for the day.


Development Day 6 12/2/2020
---------------------------- 

I ran all my tests and saw them all pass. It is really satisfying to see that  Junit greenlight. But now was the hard part, making tests work for any direction
and coordinate. I ran one of my tests and saw a null pointer exception. I decided to debug it to find the problem, realizing that the problem lied on line 121 
where a jump would be false and still try to add a null into the path. I decided to add a check that said the part after would would only trigger if jump was false
or a direction coordinate was not equal null. Running my tests again I realized they were passing. I made sure to check jump and fly as well and they seemed to work
just fine. Double Confirming everything I decided to move on a check the same for triangleBoard. However only the neighbor tests were passing. I realized I had to 
modify it for triangles, especially with triangle movement being not so smooth as squares. I felt that fly could essentially move anywhere as long as direction from
the new path.last was not none when compared to the old path.last. I decided to add this and run my tests again only to see a whole bunch of other errors. I decided
to stop there. 

Development Day 7 12/1/2020
----------------------------

I ran my tests once again and saw the whole bunch of errors with the squaretests passing. I thought of debugging them again, stepping into neighboring coords for triangles
I found that I was not giving all the neighbors. I quickly fixed this and ran my tests again. Some of them started to work but not all the errors were gone. The program kept
running into a false when it ws supposed to return true when direction moved was east. I couldnt say what was wrong until I looked at my direction to once again and realized 
there was no East and west in directionTo. I quickly fixed this and everything seemed to work except jump for certain cases. I decided to have a look at this the next day 
having spent quite a few hours debugging code. 


Development Day 8 12/2/2020
---------------------------

I decided to see how I could fix jump, maybe I though that if a piece has jump and is triangle and distancTo target<= maxpathlength it could alwyas move there, cause jump was 
basically like not caring about whats in the way. I wrote a helper function for this in my EscapemanagerImpl and before any move was made I made a check to see if path is not
null  or trianglejumpcheck was true. With these my jump was working fine. I decided to call it there for the day once again confriming none of my other previous functionality 
was broken.  

Development Day 9 12/3/2020
---------------------------

I ran all my tests to ensure that nothing was broken, and everything seemed to work as I had left it. I now decided to write more tests for triangle jump and movement in general 
and all of them passed. Then I started working towards bringing my coverage up refactoring little by little. I still could not figure out how to the boards without breaking them,
as the deadline approaches in hours to go. I decided to do what I could and fix it for gamma implementation. I ensured that I had  jdoc comments and descriptive function names. 
I decided to run all my tests again and when content I decided to stop and submit it. 


GAMMA
====

Development Day 1 12/8/2020
---------------------------

I started working on my Observer stuff today my apporach was to create an abstract observer controller class that 
implements the EscapeManager Interface that is extended by the EscapeManagerImplementation. I wrote tests in my TDD 
as to what the observer was supposed to do, then I used Professor Pollice's MasterTestObserver as a class that implements 
GameObserver. 

Development Day 2 12/9/2020
---------------------------

I worked on trying to sendMessages using obervers, first I wrote tests for everything then I worked on making the messages 
I added the messages in make coordinate to catch any illeagl coordinates being made and in move to send messages for players 
winning and game being over based on pieces remainign and scores. All my tests were working, but then I realized my Id for rule ID
was always returning null. I had a look at it to figure it out but i couldnt.

Development Day 3 12/9/2020
---------------------------

I tried working on fixing the error that I had with ruleID but I couldnt still find it . I tried changing the confgurators and then
testing but no luck. I decided to get my DistanceTo for triangleCoordinate fixed instead. I used the master tests given by professor
Pollice to test and everything seemed to be working now. 


Development Day 4 12/11/2020
---------------------------

I couldnt figure out anything for RuleID for the past 2 days so I emailed the TAs in hope of figuring it out. Then I heard back from
Aaron who helped me fix the issue, I needed to have my ruleId as just id and it worked and I could fix the way I had hardcoded my 
turnlimit checks and score checks. I kept working on achieving 95% coverage and did the MR coverage for my code.










