/*  computeModular.c
	Example Code for Lab 3 
    generates a prime p and computes c=a^(p-1) mod p
    c should be equal to 1
	requires libssl libcrpypto
	Ubuntu: Package libssl-dev required
	Compilation: gcc computeModular.c -o computeModular -lssl -lcrypto 
    >>>>>>>>>
    H.K. 2015
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <openssl/bn.h>
#include <openssl/err.h>
#include <openssl/rsa.h>
#include <sys/time.h>

#define	MAXDIGITS	1000		/* maximum number of input digits */


int main(int argc, char **argv)
{
	BIGNUM *a;
    BIGNUM *one;
	BIGNUM *pminusone;
    BIGNUM *c;
    BIGNUM *p;
	BN_CTX *t;
	char input_a[MAXDIGITS];
	
    char seed[MAXDIGITS];
	struct timeval start, end, diff;
	
	t = BN_CTX_new();
	BN_CTX_init(t);
	
	a = BN_new();
    c = BN_new();
    p = BN_new();
    pminusone = BN_new();
    one = BN_new();
    BN_one(one);  // BIGNUM constant 1
    
    int size=2048; // 2048-bit prime
	
    printf("Generate a random prime p  \n ******  \n");
    
    // Seed
    printf("Input seed for prime generation:  ");
    fgets(seed, MAXDIGITS,stdin);
    
    RAND_seed(seed, strnlen(seed,MAXDIGITS));
    
    // Generate random prime p
    
    BN_generate_prime_ex(p, size, 0, NULL, NULL,NULL);
    
    printf("p = %s\n ****** \n",BN_bn2dec(p));
    
    printf("Modular exponentiation a^(p-1) mod p  \n ******  \n");

    // get p - 1
    
    BN_sub(pminusone,p,one);
    
	printf("a=  ");
	fgets(input_a, MAXDIGITS,stdin); // includes \n

    
	// char arrays to BIGNUM
	
	BN_dec2bn(&a,input_a);
	
	gettimeofday(&start, NULL); // start time
    
    // c=a^(p-1) mod p
    
	BN_mod_exp(c,a,pminusone,p,t);
    
    if (ERR_get_error() != 0) {
        printf("Error ! \n");
        exit(1);
    }
    
    
	gettimeofday(&end, NULL);   // end time
    
    timersub(&end, &start, &diff); // time difference

    
	printf("%s^%s \n mod %s \n= %s\n",BN_bn2dec(a),BN_bn2dec(pminusone),BN_bn2dec(p), BN_bn2dec(c));
    
    printf("Time elapsed during exponentiation: %d.%06d s \n", diff.tv_sec, diff.tv_usec);
    
	
    
	return(0);
}
