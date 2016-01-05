/************************
* computeRSA:
* Author: dongmo sereni(FH Koeln)
* RSA ENcryption and decryption
 *****************************/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <openssl/bn.h>
#include <openssl/err.h>
#include <openssl/rsa.h>
#include <sys/time.h>

#define MIN_SIZE  16
#define MAX_SIZE  4096

int main(int argc, char **argv){
    
    BIGNUM *p;
    BIGNUM *q;
    BIGNUM *e;
    BIGNUM *d;
    BIGNUM *cp;
    BIGNUM *cq;
    BIGNUM *dp;
    BIGNUM *dq;
    BIGNUM *cx;
    BIGNUM *cy;
    BIGNUM *mp;
    BIGNUM *mq;
    BIGNUM *mqcxp;
    BIGNUM *mpcyq;

    BIGNUM *dmp1;
    BIGNUM *dmq1;
    BIGNUM *y;
    BIGNUM *N;
    BIGNUM *pminusone;
    BIGNUM *qminusone;
    BIGNUM *one;
    BIGNUM *mul;
    BN_CTX *t;
    RSA * myrsakey;
    BIGNUM *c;
    BIGNUM *cipher_text;
    BIGNUM *plain_text;
    FILE *keypub;
    FILE *keypriv;
    
    int size_of_p,size_of_q=0;
    //int e;
    char input_e[MAX_SIZE];
    char input_plain_text[MAX_SIZE];

	struct timeval start, end, start_crt,end_crt,diff,diff_crt;

    p = BN_new();
    q = BN_new();
    cq = BN_new();
    cp = BN_new();
    dq = BN_new();
    dp = BN_new();
    mp = BN_new();
    mq = BN_new();
    cx = BN_new();
    cy = BN_new();

    mqcxp = BN_new();
    mpcyq = BN_new();

    dmp1 = BN_new();
    dmq1 = BN_new();
    e = BN_new();
    y = BN_new();
    N =  BN_new();
    c = BN_new();
    plain_text = BN_new();
    cipher_text = BN_new();
    mul =  BN_new();
    d = BN_new();
    pminusone = BN_new();
    qminusone = BN_new();
    one = BN_new();
    BN_one(one);

    t = BN_CTX_new();
    BN_CTX_init(t);

    do {
        printf("Please Enter Size of P and Q \n");
        scanf("%d %d",&size_of_p,&size_of_q);

    } while ( (size_of_p <= MIN_SIZE || size_of_p>= MAX_SIZE) || (size_of_q <= MIN_SIZE || size_of_q>= MAX_SIZE));
    
    printf("SIZE OF P:%d\n",size_of_p);
    printf("SIZE OF Q:%d\n",size_of_q);
    
    
    // Generate random prime p
    BN_generate_prime_ex(p, size_of_p, 0, NULL, NULL,NULL);
    BN_generate_prime_ex(q, size_of_q, 0, NULL, NULL,NULL);
    
    printf("P = %s\n ****** \n",BN_bn2dec(p));
    printf("Q = %s\n ****** \n",BN_bn2dec(q));


    BN_sub(pminusone,p,one);
    BN_sub(qminusone,q,one);
    BN_mul(mul,qminusone,pminusone,t);
    //printf("mul = %s\n ****** \n",BN_bn2dec(mul));


    do{
    	printf("e=  ");
    	fgets(input_e, MAX_SIZE,stdin); // includes \n
    	BN_dec2bn(&e,input_e);
    	BN_mod_inverse(NULL,e,mul,t);

    } while (ERR_get_error());
    
    BN_mod_inverse(d,e,mul,t);
    printf("d = %s\n ****** \n",BN_bn2dec(d));

    BN_mul(N,p,q,t);
    printf("N = %s\n ****** \n",BN_bn2dec(N));

    printf("plain_text=  ");
    fgets(input_plain_text, MAX_SIZE,stdin); // includes \n
    BN_dec2bn(&plain_text,input_plain_text);


    // cipher text encryption c ^e mod N
    BN_mod_exp(c,plain_text,e,N,t);
    printf("c = %s\n ****** \n",BN_bn2dec(c));

    //decryption c ^d mod n
    gettimeofday(&start, NULL); // start time
    BN_mod_exp(plain_text,c,d,N,t);
	gettimeofday(&end, NULL);   // end time
	timersub(&end, &start, &diff); // time difference
	printf("Time elapsed during decrytion: %d.%06d s \n", diff.tv_sec, diff.tv_usec);
    printf("P = %s\n ****** \n",BN_bn2dec(plain_text));


    //decrypt using CRT
    gettimeofday(&start_crt, NULL); // start time


    BN_mod(cp,c,p,t);
    printf("cp = %s\n ****** \n",BN_bn2dec(cp));
    BN_mod(cq,c,q,t);
    printf("cq = %s\n ****** \n",BN_bn2dec(cq));
    BN_mod(dp,d,pminusone,t);
    printf("dp = %s\n ****** \n",BN_bn2dec(dp));
    BN_mod(dq,d,qminusone,t);
    printf("dq = %s\n ****** \n",BN_bn2dec(dq));

    //compute mp and mq
    BN_mod_exp(mp,cp,dp,p,t);
    printf("mp = %s\n ****** \n",BN_bn2dec(mp));
    BN_mod_exp(mq,cq,dq,q,t);
    printf("mq = %s\n ****** \n",BN_bn2dec(mq));

    //compute cx and cy
    BN_mod_inverse(cx,p,q,t);
    printf("cx = %s\n ****** \n",BN_bn2dec(cx));
    BN_mod_inverse(cy,q,p,t);
    printf("cy = %s\n ****** \n",BN_bn2dec(cy));


    BN_mul(mqcxp,mq,cx,t);
    BN_mul(mqcxp,mqcxp,p,t);
    printf("mqcxp = %s\n ****** \n",BN_bn2dec(mqcxp));

    BN_mul(mpcyq,mp,cy,t);
    BN_mul(mpcyq,mpcyq,q,t);
    printf("mpcyq = %s\n ****** \n",BN_bn2dec(mpcyq));


    BN_mod_add(plain_text,mqcxp,mpcyq,N,t);

	gettimeofday(&end_crt, NULL);   // end time

    printf("Time elapsed during decrytion using crt: %d.%06d s \n", diff_crt.tv_sec, diff_crt.tv_usec);
    printf("Plaintext_CRT = %s\n ****** \n",BN_bn2dec(plain_text));


    /* extended compute RSA */
    myrsakey = RSA_new();
    BN_mod_inverse(y,q,p,t);
    printf("Y = %s\n ****** \n",BN_bn2dec(y));

    //compute dmp1,dmq1

    BN_mod(dmq1,d,qminusone,t);
    BN_mod(dmp1,d,pminusone,t);
    //printf("dmq1 = %s\n ****** \n",BN_bn2dec(y));
    //printf("dmp1 = %s\n ****** \n",BN_bn2dec(y));


    myrsakey->n = N;
    myrsakey->e = e;
    myrsakey->d = d;
    myrsakey->p = p;
    myrsakey->q = q;
    myrsakey->dmp1 = dmp1;
    myrsakey->dmq1 = dmq1;
    myrsakey->iqmp = y;


    keypub = fopen("keypub.pem","w");
    keypriv = fopen("keypriv.pem","w");

    //printf("keypub = %d\n ****** \n",keypub);
    //printf("keypriv = %d\n ****** \n",keypriv);

    PEM_write_RSA_PUBKEY(keypub, myrsakey);
    PEM_write_RSAPrivateKey(keypriv,myrsakey,NULL,NULL,0,NULL,NULL);

    // close file
    fclose(keypub);
    fclose(keypriv);

    return 0;

}
