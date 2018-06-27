#include <cstdlib>
#include <iostream>
#include <fstream>
#include <sstream>
#include <math.h> 
#include <vector>
#include <time.h>

using namespace std;

bool converges(double cx,double cy)
{
  const int iter_n=3000; // max. iteration number
  int n=0;
  double zx=0, zy=0;
  double new_zx=0;

  while( (n<iter_n) && (zx*zx + zy*zy)<4 )
    {
      new_zx = zx*zx - zy*zy + cx;
      zy = 2*zx*zy + cy;
      zx = new_zx;
      n++;
    }
  return n==iter_n;
}


int main(int argc, char **argv){
  time_t t1, t2;
  double cx, cy;
  const long long iternum=10000000;

  srand48((unsigned)time(0));

  t1=clock();
  long long sum=0;
  for(long long i=0;i<iternum;++i){
    cx=-2.+4.*drand48();
    cy=-2.+4.*drand48();
    if(converges(cx,cy)) ++sum;
  }
  t2=clock();
  cout.precision(12);
  cout<<"Area: \t\t"<<16.*sum/iternum<<endl;
  cout<<"Time: \t\t"<<difftime(t2,t1)/CLOCKS_PER_SEC<<endl;
}
