# cse-143-final-project
How to Compile and Start the Program:--------------------------------------------------------------------------------------------

    Provided the starting county of a road trip along with the destination county, 
the application will determine the optimal county-by-county route to take. 
Not only will this route be as short as possible, but it is ensures minimal 
exposure to COVID-19 by identifying those counties with the least COVID 
case percentages.

    It is often difficult to plan out the quickest path to take for road trips, let alone at a time 
when it is equally important to stay safe from COVID-19. While it would be overwhelming to 
pore through all the data and make such a complex decision, The COVID Road Trip Planner, a 
simple console-based application designed in Java, is ready to assist! 

    To compile the program, type "javac" followed by "class name.java" in the terminal for each of 
the program's three classes: AStarAlgorithm, County, and OptimizeTravel. To run the program, 
reenter the terminal and type "java" followed by "OptimizeTravel.java" as this is the class that 
contains the program's main method. Immediately after, a text prompt for your starting county 
should appear in the console, and you should be ready to determine your next travel plans!

    Currently, the application only recognizes counties in Washington state (there are
plans to expand to other states in the future). The application does not take into account natural
obstacles within Washington State (i.e Cascade Mountain Range or Yakima River). It assumes, for 
simplicity sake, that every bit of land in Washington state is traversable by car. 
The caveat is that San Juan County is not as valid county because it is surrounded on all sides
by water. 

How to Use the Program:-----------------------------------------------------------------------------------------------------------
    
    After compiling the program, you will see the prompt "Enter the Washington State county that you are 
starting at:", to which you should type the name of the county in WA state that your trip is starting 
in. After doing that, click your 'Enter' key. Then, you will see another prompt, this time saying 
"Enter the Washington State county that is your destination:", to which you should type the name of 
the county that your trip will end in. Make sure that you correctly spell the name of the county, 
and that the county is in Washington state. Otherwise, the program will print "That is not a valid 
Washington State county. Please try again:" into the console. After seeing this prompt, you may type 
the name of the county again and click your 'Enter' key to continue using the program. 

    To end the program, click and hold your 'Ctrl' key. While holding the 'Ctrl' key, press the 'C' key. 
The program has now stopped running and you will need to recompile it in order to get it running again.

What the Program Returns:----------------------------------------------------------------------------------------------------------

    After entering valid start and end counties, the program returns a list of counties as your 
ideal route. Your route should be displayed as a list of counties from left to right, with each 
bordering the next. For example, the route from Benton to Pacific county will be outputted as 
"Benton -> Yakima -> Lewis -> Pacific". The creation of this route takes into account both the shortest 
distance possible and safest route for COVID. Using a custom equation, we weighted each value 
differently to create a healthy balance between efficiency and safety.


 
 


 
