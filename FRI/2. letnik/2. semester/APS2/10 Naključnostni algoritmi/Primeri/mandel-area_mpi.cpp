#include <cstdlib>
#include <iostream>
#include <fstream>
#include <sstream>
#include <math.h> 
#include <vector>
#include <time.h>
#include <mpi.h>

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
 int id, nproc;
  MPI_Status status;
  double cx, cy;
  const long long iternum=10000000;
  long long allsum;
  double t1, t2;

  // Initialize MPI:
  MPI_Init(&argc, &argv);
  // Get my rank:
  MPI_Comm_rank(MPI_COMM_WORLD, &id);
  // Get the total number of processors:
  MPI_Comm_size(MPI_COMM_WORLD, &nproc);

  srand48((unsigned)time(0));

  if(id==0) t1=MPI_Wtime();
  long long sum=0;
  for(long long i=0;i<iternum/nproc;++i){
    cx=-2.+4.*drand48();
    cy=-2.+4.*drand48();
    if(converges(cx,cy)) ++sum;
  }

  //sum the local sum-s into the Master's allsum 
  MPI_Reduce(&sum, &allsum, 1, MPI_LONG_LONG, MPI_SUM, 0, MPI_COMM_WORLD);
 
  //Master writes out the calculated Pi
  if(id==0){
    t2=MPI_Wtime();
    cout.precision(12);
    cout<<"Area: \t\t"<<16.*allsum/iternum<<endl;
    cout<<"Time: \t\t"<<t2-t1<<endl;
  }

  // Terminate MPI:
  MPI_Finalize();
  return 0;

}
