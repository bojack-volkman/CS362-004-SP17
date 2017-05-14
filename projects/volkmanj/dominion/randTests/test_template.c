
#include "randtests.h"

void test_assert(int test, char* msg) {
	printf("\n %s : \n", msg);
	if(!test){
		printf("****TEST FAILED**** \n");
	} else {
		printf("****TEST PASSED**** \n");
	}
}

void test_header(char *name){
	printf("\n************ Testing %s **********\n\n", name);
}

void test_print(int *arr, int n, char *msg){
	int i;
	printf("\n %s \n", msg);
	for(i = 0; i < n; i++){
	printf("%d : %d\n", i, arr[i]);
	}
	printf("*****************\n");
}

void makeCards(struct gameState *G, int *cardCount, int (*cards)[MAX_DECK]){
	int i;
	int rnd = rand() % (MAX_DECK + 1);

	for (i = 0; i < rnd; i++){
		cards[G->whoseTurn][i] = rand() % (treasure_map + 1);
	}
	cardCount[G->whoseTurn] = rnd;
}

void setupGame(struct gameState *G, struct gameState *testStatus){
	int seed = 1000;
	int playerCount = (rand() % MAX_PLAYERS) + 1;
	int whoseTurn = rand() % playerCount;
	int k[10] = {adventurer, council_room, feast, gardens, mine,
						remodel, smithy, village, baron, great_hall};

	memset(G, 23, sizeof(struct gameState));
	initializeGame(playerCount, k, seed, G);
	G->whoseTurn = whoseTurn;

	makeCards(G, G->deckCount, G->deck);
	makeCards(G, G->handCount, G->hand);
	makeCards(G, G->discardCount, G->discard);

	memcpy(testStatus, G, sizeof(struct gameState));

	//test_print(G->deck[whoseTurn], G->deckCount[whoseTurn], "deckCount");
	//test_print(G->hand[whoseTurn], G->handCount[whoseTurn], "handCount");
	//test_print(G->discard[whoseTurn], G->discardCount[whoseTurn], "discardCount");
}
