# CPSC 210 Personal Project Proposal

## Weight Loosing Helper

 *Motivations behind this Application*:

Cutting down weight is a difficult and uneasy experience, especially when
you have to keep track of the calorie intake and calorie output on a daily basis.
I have personally experienced a long period of time of when I was not able to
control my own weight (partly due to medical conditions),  I was extremely
unhappy during that period of time due to the lack of confidence and my body being very
inflexible, so last summer, I decided to put an end to this. Through carefully
keeping track of the foods and its corresponding calories that I eat as well as 
massive exercising, I finally attained a normal BMI at the 
end of the summer by losing more than 50 pounds. 

Through my experience of losing weight, I found it crucial
to set realistic goals for yourself and fulfill daily calorie
deficits accordingly. I am excited to make an application
that allows users to set a goal weight for themselves, and the app
would output the estimated total colorie deficits that the user needs
to reach that goal based on their current weight. At the end of the day,
the calorie deficit that the user has would be used to deduct the calorie deficit goal that the user
has set for himself. Keeping track of daily calorie deficit is not easy
without keeping track of the food/exercise that the user has input/output
on a day. Therefore the application would also allow users to record
the calories that they input/output on a daily basis so users
stay consistent with their desired calorie deficit. The application also
includes a function that allows users to estimate the amount of days that
they need to reach their desired weight under a consistent daily calorie
deficit. Although cutting down weight may be a painful period, I hope that my application
could make it easier and encourage those in need of losing weight
to eventually reach their goals.


 *User Stories:*

- As a user, I want to be able to add the foods I ate in a day and their
corresponding calories to the FoodAndCalorieList.
- As a user, I want to be able to add the exercises I've done
in a day and their corresponding calorie burned to ExerciseAndCalorieList.
- As a user, I want to be able to calculate the calorie deficit for a
day by taking the sum of my output (2000 base calorie + sum of calories
output obtained from ExerciseAndCalorieList) minus sum of input
(sum of calories input obtained from FoodAndCalorieList) on a single day.
- As a user, I want to select a day and see the list of food and exercises
I've done on that day as well as my calorie deficit on that day.

- As a user, I want to set a goal weight for myself and know the 
estimated total calorie deficit I would need to reach
my desired weight based on my current weight.
- As a user, I want to be able to create multiple days with
their corresponding food and exercise performed.





- As a user, I want to be able to save my new days' activities to file.
- As a user, I want to retain my past days' activities the state exactly
as what it was when I left off earlier.


 

*Phase 4: Task 2:*

- Sat Nov 26 16:52:56 PST 2022
- Added New Food : chicken ,1000 Calories
- Sat Nov 26 16:53:01 PST 2022
- Added New Food : chocolate ,350 Calories
- Sat Nov 26 16:53:05 PST 2022
- Added New Exercise : swimming ,666 Calories
- Sat Nov 26 16:53:07 PST 2022
- Added a New Day: Day0
- Sat Nov 26 16:53:15 PST 2022
- Added New Food : fish ,200 Calories
- Sat Nov 26 16:53:21 PST 2022
- Added New Exercise : Running ,200 Calories
- Sat Nov 26 16:53:24 PST 2022
- Added a New Day: Day1
- Sat Nov 26 16:53:26 PST 2022
- Removed a Day: Day 2

*Phase 4: Task 3:*

The design has unwanted and unfortunate high coupling at the 
end of the day that can be improved in many ways. First,
the classes Exercise and Food have many commonalities that 
can be refactored by making them extend to an abstract class
that have the common method implemented. Moreover, the methods in
WeightLosingApp and WeightLosingGUI can also be refactored
into multiple different classes with a main caller for better
cohesiveness. 


