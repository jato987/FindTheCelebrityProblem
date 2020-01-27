#Find the celebrity
=========================================================================

## Author:
@Jtriana :snake:
Johann Andrés Triana Olaya
## Description:
This is a regular java application for resolve the next problem:

Problem: Find the Celebrity
Description:
- In a team of n people, a celebrity is known by everyone but he/she doesn't know anybody.

This program resolve the problems listed on csv files in resource folder com/problems/files

## CSV validation

A valid csv file use the regular expression ([^,;],[^,;];)([^,;].[^,;]) that describes at less two relation between name1 and name2 separates by the character ,
the relations are separated by ; character.


## Execution
This use regular java se for execute by main method, automatically takes all files in folder com/problems/files on the project's resource folder.

## Response

there is an example of response with 2 files (listed as examples)

> Results for file: problem2 -> 

> Celebrity Found with name: Celebrity

> ##################

> Results for file: problem1 -> 

> There is no person that have the requisites of the problem

> ##################

1) First Result find a Celebrity named Celebrity that have the requisites of the problem, there is no relation with form Celebrity,[^,;] and exist relation with form [^,;],Celebrity for every case found in csv

note that a unique person exist if there is at less one relation with his name (definde as [^,;]) 

2) for the second result the file give no relations that have a person with the requisites in the problem. 

## Complexity

before the application read the files, the estimate complex is O(n²) 
