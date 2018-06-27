/*
 * Binary Search Tree
 */

#include <stdio.h>

// najvecja dolzina imena osebe
#define IME_LENGTH 50

//
// podatkovni element
//

typedef struct _Element {
    int starost;
    char *ime;
} Element;

//
// BST node <Node>
//


// ...


//
// funkcije binarnega iskalenega drevesa 
//

// Element createElement(starost, ime): na podlagi podatkov kreiraj nov podatkovni <Element>  

// Node createNode: kreiraj in vrni novo BST vozlisce

// Node insert(Node, Element): v BST drevo vstavi nov element 
// kljuc za primerjavo: starost osebe; ce vozlisce s kljucem ze obstaja, ga zavrzemo

// int depth(Node): vrne globino BST drevesa

// void in_order(Node): urejen izpis podatkov

// void level_order(Node): izpis po nivojih BST drevesa
// lahko si pomagate z vrsto v datoteki <queue.h>


int main() {
    // prazno BST drevo
    TreeNode *bst = NULL;


    // ...


    return 0;
}