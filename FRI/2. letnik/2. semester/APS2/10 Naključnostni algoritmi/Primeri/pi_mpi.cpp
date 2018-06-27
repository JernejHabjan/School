//Calculation of the constant Pi by Monte Carlo method

#include <cstdlib>
#include <ctime>
#include <iostream>
#include <math.h>
#include <mpi.h>
using namespace std;

int main(int argc, char **argv){
  int id, nproc;
  MPI_Status status;
  double x,y, Pi, error;
  long long allsum;
  const long long iternum=1000000000;

  // Initialize MPI:
  MPI_Init(&argc, &argv);
  // Get my rank:
  MPI_Comm_rank(MPI_COMM_WORLD, &id);
  // Get the total number of processors:
  MPI_Comm_size(MPI_COMM_WORLD, &nproc);

  srand48((unsigned)time(0));

  long long sum=0;
  for(long long i=0;i<iternum/nproc;++i){
    x=drand48();
    y=drand48();
    if(x*x+y*y<1) ++sum;
  }

  //sum the local sum-s into the Master's allsum 
  MPI_Reduce(&sum, &allsum, 1, MPI_LONG_LONG, MPI_SUM, 0, MPI_COMM_WORLD);
 
  //Master writes out the calculated Pi
  if(id==0){
    //calculate Pi, compare to the Pi in math.h
    Pi=(4.0*allsum)/(iternum);
    error = fabs( Pi-M_PI );
    cout.precision(12);
    cout<<"Pi: \t\t"<<M_PI<<endl;
    cout<<"Pi by MC: \t"<<Pi<<endl;
    cout<<"Error: \t\t"<<fixed<<error<<endl;
  }

  // Terminate MPI:
  MPI_Finalize();
  return 0;
}
