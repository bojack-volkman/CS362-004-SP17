#include "randtests.h"

void runTest(struct gameState *G, struct gameState *testStatus) {
  int test_condition;
  int whoseTurn = G->whoseTurn;
  int drawntreasure = 0;
  int cardDrawn;
  int temphand[MAX_HAND];
  int z = 0; // counter for the temp hand

  test_condition = playAdventurer(whoseTurn, G, drawntreasure, cardDrawn, temphand, z);
  test_assert(!test_condition, "Expected Return Value");

  //adventurer code from dominion.c
  while(drawntreasure < 2) {
    if (testStatus->deckCount[whoseTurn] <1) { //if the deck is empty we need to shuffle discard and add to deck
      test_condition = shuffle(whoseTurn, testStatus);
      test_assert(!test_condition, "Expected Deck Count");
    }
    test_condition = drawCard(whoseTurn, testStatus);
    test_assert(!test_condition, "Expected Hand Count");
    cardDrawn = testStatus->hand[whoseTurn][testStatus->handCount[whoseTurn]-1]; //top card of hand is most recently drawn card.
    if (cardDrawn == copper || cardDrawn == silver || cardDrawn == gold)
      drawntreasure++;
    else{
      temphand[z]=cardDrawn;
      testStatus->handCount[whoseTurn]--; //this should just remove the top card (the most recently drawn one).
      z++;
    }
  }

  //pretty sure this is where the bug is. Z goes under 0 if 2 treasures are drawn
  while(z-1 > 0) {
	  testStatus->discard[whoseTurn][testStatus->discardCount[whoseTurn]++]=temphand[z-1]; // discard all cards in play that have been drawn
    z = z-1;
  }
  //compares all memory of gamestates
  test_assert(!memcmp(testStatus->hand[whoseTurn], G->hand[whoseTurn], MAX_HAND), "Drew Treasure");
  test_assert(!memcmp(testStatus, G, sizeof(struct gameState)), "Affected Gamestate Size");

}

int main(){
	struct gameState G, testStatus;
	int i;
	srand(time(NULL));
	test_header("##Adventurer Random Test Beginning##");
	for (i = 0; i < TESTS; i++) {
		setupGame(&G, &testStatus);
		runTest(&G, &testStatus);
	}
	test_header("##Adventurer Random Test Completed##");


	return 0;

}
