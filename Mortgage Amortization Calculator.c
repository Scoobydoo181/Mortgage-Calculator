#include <stdio.h>
#include <math.h>

int main() {

    float loanTerm;
    float APR;
    float monthlyPayment;
    float numCompounds;
    float loanAmount;
    float rate;
    float amortizedPayment;
    float numMonths;
    float totalInterest;

    printf("Welcome to the Wilson Inc. Mortgage Amortization Calculator!\n");

    printf("\nPlease enter the amount of the loan (Enter as a dollar amount $XX.XX)\n");
    scanf(" $%f", &loanAmount);

    printf("\nPlease enter the intended term of the loan (Enter as a number XX.XX, in years)\n");
    scanf(" %f",&loanTerm);

    printf("\nPlease enter the APR of the mortgage (Enter as a percentage XX.XX%%)\n");
    scanf(" %f%%", &APR);

    printf("\nPlease enter the number of times, per year, that the interest compounds (Enter as a number XX.XX)\n");
    scanf(" %f", &numCompounds);

    numMonths = loanTerm *12;

    rate = (pow((1 + (((1/numCompounds)*APR)/100)),(1/(12* (1/numCompounds)) )));

    amortizedPayment =  ( (pow(rate,numMonths)*(rate -1)) / (pow(rate, numMonths) -1) )*loanAmount;

    printf("Your amortized monthly payment is %.2f", amortizedPayment);

    totalInterest = ((amortizedPayment * 12 * loanTerm) - loanAmount);

    printf(" and at the end of %.1f years", loanTerm);
    printf(" $%.2f will have been paid in interest", totalInterest);

return 0;
}
