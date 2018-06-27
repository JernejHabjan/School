//Calculation of torus-part by Monte Carlo method

#include <cstdlib>
#include <ctime>
#include <iostream>
#include <math.h>
using namespace std;

double den(double x, double y, double z){
  return 1.;
}

int main(int argc, char **argv){
  double x,y,z;
  double vol=3.*7.*2.;
  double sw=0, swx=0, swy=0, swz=0;
  const long long iternum=10000000;

  srand48((unsigned)time(0));

  //long long sum=0;
  for(long long i=0;i<iternum;++i){
    x=1.+3.*drand48();
    y=-3.+7.*drand48();
    z=-1.+2.*drand48();
    //    if( (3.-sqrt(x*x+y*y))*(3.-sqrt(x*x+y*y))+z*z <=1.){
    if( pow(3.-sqrt(x*x+y*y),2)+z*z <=1.){
      sw += den(x,y,z);
      swx += x*den(x,y,z);
      swy += y*den(x,y,z);
      swz += z*den(x,y,z);
    }
  }

  double weight=vol*sw/iternum;
  cout.precision(12);
  cout<<"Weight: \t\t"<<weight<<endl;
  cout<<"x coordinate: \t\t"<<(vol*swx/iternum)/weight<<endl;
  cout<<"y coordinate: \t\t"<<(vol*swy/iternum)/weight<<endl;
  cout<<"z coordinate: \t\t"<<(vol*swz/iternum)/weight<<endl;
  
  return 0;
}
