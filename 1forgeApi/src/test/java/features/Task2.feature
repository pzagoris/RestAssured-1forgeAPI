Feature: Get quotes for specific currency pairs


Scenario Outline: Test case for task 2
	Given The market is open
	Then I GET symbols
	Then I GET <numberOfPairs> pairs of quotes
	And Write json response to <fileName> 

Examples:
|numberOfPairs|fileName|
|all|all-list.csv|
|10|ten-list.csv|

