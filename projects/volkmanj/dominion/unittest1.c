/*
 * cardtest2.c
 *
 
 */

/*
 
 */


#include "dominion.h"
#include "dominion_helpers.h"
#include <string.h>
#include <stdio.h>

#include "rngs.h"
#include <stdlib.h>

#define UNITTEST "getCost()"

int main() {
	
	int test_success = 0;
	int k = 0;
	int cost = 0;
	
	printf("**********Testing Function: %s ***********\n", UNITTEST);
	
	for (k = 0; k < 25; k++){
		
		switch(k){
			
			cost = getCost(k);
			//for some reason doesn't print cost
			printf("%d --------\n", cost);
			
			//victory cards
			case province:
				test_success = test_success = testassert(cost == 8, test_success);
				break;
				
			case duchy:
				test_success = testassert(cost == 5, test_success);
				break;
				
			case estate:
				test_success = testassert(cost == 2, test_success);
				break;
				
			case curse:
				test_success = testassert(cost == 0, test_success);
				break;
				
			//treasure cards
			case gold:
				test_success = testassert(cost == 6, test_success);
				break;

			case silver:
				test_success = testassert(cost == 3, test_success);
				break;
				
			case copper:
				test_success = testassert(cost == 0, test_success);
				break;
				
			//kingdom cards bugged in assignment 2 (check if they work properly)
				
			case adventurer:
				test_success = testassert(cost == 6, test_success);
				break;
				
			case smithy:
				test_success = testassert(cost == 4, test_success);
				break;
				
			case feast:
				test_success = testassert(cost == 4, test_success);
				break;
				
			case village:
				test_success = testassert(cost == 3, test_success);
				break;
				
			case mine:
				test_success = testassert(cost == 5, test_success);
				break;
				
			//other kingdom cards

			case council_room:
				test_success = testassert(cost == 5, test_success);
				break;
				
			case steward:
				test_success = testassert(cost == 3, test_success);
				break;

			case tribute:
				test_success = testassert(cost == 5, test_success);
				break;

			case ambassador:
				test_success = testassert(cost == 3, test_success);
				break;

			case cutpurse:
				test_success = testassert(cost == 4, test_success);
				break;

			case embargo:
				test_success = testassert(cost == 2, test_success);
				break;

			case outpost:
				test_success = testassert(cost == 5, test_success);
				break;

			case gardens:
				test_success = testassert(cost == 4, test_success);
				break;

			case remodel:
				test_success = testassert(cost == 5, test_success);
				break;

			case baron:
				test_success = testassert(cost == 4, test_success);
				break;
				
			case salvager:
				test_success = testassert(cost == 4, test_success);
				break;

			case sea_hag:
				test_success = testassert(cost == 4, test_success);
				break;

			case treasure_map:
				test_success = testassert(cost == 4, test_success);;
				break;

			case great_hall:
				test_success = testassert(cost == 3, test_success);
				break;

			case minion:
				test_success = testassert(cost == 5, test_success);
				break; 
			
		}
		
		//check if switch case was out of bounds
		if (k < 0 || k > 26){
			test_success = testassert(0, test_success);
			printf("\n Test %d Failed\n\n", k);
		} else {
			printf("\n Test %d Passed\n\n", k);
		}
		
		printf("--------\n");
			
	}
  	
 	printf("\n test_success = %d", test_success);
 	if(test_success == 1){
 		printf("\n *******%s TEST PASSED*********\n", UNITTEST);
 	}
 	else {
 		printf("\n *******%s TEST FAILED*********\n", UNITTEST);
 	}
	return 0;
}


