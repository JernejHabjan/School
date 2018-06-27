//Calculation of the constant Pi by Monte Carlo method

#include <cstdlib>
#include <ctime>
#include <iostream>
#include <math.h>
using namespace std;

int main(int argc, char **argv){
  double x,y, Pi, error;
  const long long iternum=1000000000;

  srand48((unsigned)time(0));

  long long sum=0;
  for(long long i=0;i<iternum;++i){
    //x=(double)rand()/RAND_MAX;
    //y=(double)rand()/RAND_MAX;
    x=drand48();
    y=drand48();
    if(x*x+y*y<1) ++sum;
  }

  Pi=(4.0*sum)/(iternum);
  error = fabs( Pi-M_PI );

  cout.precision(12);
  cout<<"Pi: \t\t"<<M_PI<<endl;
  cout<<"Pi by MC: \t"<<Pi<<endl;
  cout<<"Error: \t\t"<<fixed<<error<<endl;
  
  return 0;
}
