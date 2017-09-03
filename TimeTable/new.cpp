#include<bits/stdc++.h>
#include<unistd.h>
using namespace std;

map<int,string>m;
map<int,string>m_day;

map<int,string>map_subject[6];
vector < pair <int, pair<int, int> > >v[6][5];
int sec_teacher[6][7] = {
    {0,1,2,3,4,5,25},
    {7,23,2,8,9,6,26},
    {10,24,2,11,12,13,27},
    {29,14,15,16,17,28,10},
    {31,9,15,16,12,30,0},
    {33,19,20,21,22,32,18}
};

int avail_teacher[35][5][4];
int hours_left[6][7];
string IntToString (int a)
{
    ostringstream temp;
    temp<<a;
    return temp.str();
}
int find_sec(int ts){
	return (ts%6);
}
int find_day(int ts){
	int x=find_sec(ts);
	return (ts-x)/24;
}
int find_slot(int ts){
	int x=find_sec(ts);
	x=(ts-x)%24;
	return x/6;
}
int all_hrs_finished(){
	int flag=1;
	 for (int j = 0; j < 6; j++) {

        for (int i = 0; i < 7; i++)
        	{
        		if(hours_left[j][i]>0){

        			flag=0;
        			break;
					}

			}


    }
     return flag;


}


int count_hr(int sub, int ts) {
	int c=0;
	int slot = find_slot(ts);
	int day = find_day(ts);
	int sec = find_sec(ts);
	int teacher=sec_teacher[sec][sub];
	pair<int,int>p=make_pair(sub,teacher);
	vector< pair <int, pair<int,int> > >::iterator it;
	for(it=v[sec][day].begin();it!=v[sec][day].end();it++)
	{

		pair<int ,int>p1=it->second;
	//	cout<<"sfdgh"<<it->first<<p1.first<<p1.second<<endl;
		//cout<<p.first<<" "<<sub<<endl;
		int q=p1.first;
		if(q==sub){
		//	cout<<"q hai "<<q<<sec<<day<<endl;
			c++;
		}

	//	cout<<c<<endl;
	}
	return c;


}

int is_Safe(int sub, int ts){
	int slot = find_slot(ts);
	int day = find_day(ts);
	int sec = find_sec(ts);

	if(hours_left[sec][sub] == 3) {
		if ( avail_teacher[/*find techer*/sec_teacher[sec][sub]][day][slot] == -1){
			return 1;
		}
	} else if ( hours_left[sec][sub] == 2 ) {
		pair<int, int> p = make_pair(sub, sec_teacher[sec][sub]);
        pair<int, pair <int, int> > p1 = make_pair(slot - 1, p);
        //if just befroe
        if (find(v[sec][day].begin(), v[sec][day].end(), p1) != v[sec][day].end()&&find_slot(ts)!=2) {

            if (avail_teacher[/*find techer*/sec_teacher[sec][sub]][day][slot] == -1) {
                return 1;
        	}
    	} else if ( count_hr(sub,ts) == 0 ) {
            if (avail_teacher[/*find techer*/sec_teacher[sec][sub]][day][slot] == -1) {
                return 1;
        	}
		}
	} else if (hours_left[sec][sub] == 1) {

		pair<int, int> p = make_pair(sub, sec_teacher[sec][sub]);
        pair<int, pair <int, int> > p1 = make_pair(slot - 1, p);
		//if just befroe
        if (find(v[sec][day].begin(), v[sec][day].end(), p1) != v[sec][day].end()&&find_slot(ts)!=2) {
        		//cout<<"yi pe hai"<<endl;
        	if ( count_hr(sub,ts) == 1 ){
        		if (avail_teacher[/*find techer*/sec_teacher[sec][sub]][day][slot] == -1) {

                return 1;
        		}
			}

    	}
    //	cout<<"count hai ye "<< count_hr(sub,ts)<<" "<<sub<<sec<<endl;
		if ( count_hr(sub,ts) == 0 ) {




            if (avail_teacher[/*find techer*/sec_teacher[sec][sub]][day][slot] == -1) {
			//	cout <<"yahan se";
                return 1;
        	}
		}
	}

	return 0;
}

int algo(int ts){
	//base case

	/**/
    /*if(ts == 115){
        return 1;
    }*/
	if(all_hrs_finished()){
		//cout<<" bhakjsbd"<<endl;
		return 1;
	}


	for(int sub=0;sub<7;sub=sub+1){

	if (is_Safe(sub,ts)){


		int b = sec_teacher[find_sec(ts)][sub];
        pair<int, int> p = make_pair(sub, b);
        hours_left[find_sec(ts)][sub]--;
		v[find_sec(ts)][find_day(ts)].push_back(make_pair(find_slot(ts), p));
		avail_teacher[b][find_day(ts)][find_slot(ts)]=ts;

		if(algo(ts+1)){

			return 1;
		}
			avail_teacher[b][find_day(ts)][find_slot(ts)]=-1;
			pair< int,pair<int,int> > p1=make_pair(find_slot(ts),p);
			v[find_sec(ts)][find_day(ts)].erase(remove( v[find_sec(ts)][find_day(ts)].begin(),v[find_sec(ts)][find_day(ts)].end(), p1 ), v[find_sec(ts)][find_day(ts)].end());  ///sth missing here
			hours_left[find_sec(ts)][sub]++;

	}
	}

	return 0;
}



int main() {
    for (int i = 0; i < 6; i++) {
        for (int j = 0; j < 6; j++) {
            hours_left[i][j] = 3;
        }
    }
    for(int i = 0 ; i < 6 ; i++){
        hours_left[i][6]=2;
    }
    hours_left[3][6] = 3;
    hours_left[4][6] = 3;
    hours_left[5][6] = 3;
    hours_left[3][0] = 2;
    hours_left[4][0] = 2;
    hours_left[5][0] = 2;
    for (int i = 0; i < 35; i++) {
        for (int j = 0; j < 5; j++) {
           for(int k = 0 ; k < 4 ; k++)
           			avail_teacher[i][j][k]=-1;
        }
    }
    m[0]="(sku)";
    m[1]="(ps)";
    m[2]="(aks)";
    m[3]="(at)";
    m[4]="(vkc)";
    m[5]="(sanjay)";
    m[6]="(pk)";
    m[7]="(abab)";
    m[8]="(sb)";
    m[9]="(tp)";
    m[10]="(akt)";
    m[11]="(sanjai)";
    m[12]="(js)";
    m[13]="(sbh)";
    m[14]="(vrv)";
    m[15]="(vmk)";
    m[16]="(mg)";
    m[17]="(bhibhas)";
    m[18]="(rekha)";
    m[19]="(rajat)";
    m[20]="(s.maity)";
    m[21]="(np)";
    m[22]="(ashutosh)";
    m[23]="(sm)";
    m[24]="(sr)";
    map_subject[0][0]="SMAT130C";
    map_subject[0][1]="MLCS102C";
    map_subject[0][2]="ECAS130C";
    map_subject[0][3]="SEGP132C";
    map_subject[0][4]="IITP132C";
    map_subject[0][5]="EEDC132C";
    map_subject[1][0]="SMAT130C";
    map_subject[1][1]="MLCS102C";
    map_subject[1][2]="ECAS130C";
    map_subject[1][3]="SEGP132C";
    map_subject[1][4]="IITP132C";
    map_subject[1][5]="EEDC132C";
    map_subject[2][6]="SMAT130C";
    map_subject[2][0]="MLCS102C";
    map_subject[2][2]="ECAS130C";
    map_subject[2][3]="SEGP132C";
    map_subject[2][4]="IITP132C";
    map_subject[2][5]="EEDC132C";
    map_subject[3][6]="SMAT330C";
    map_subject[3][1]="IOOM332C";
    map_subject[3][2]="ITOC330C";
    map_subject[3][3]="EMIP33C";
    map_subject[3][4]="IOPS332C";
    map_subject[4][6]="SMAT330C";
    map_subject[4][1]="IOOM332C";
    map_subject[4][2]="ITOC330C";
    map_subject[4][3]="EMIP33C";
    map_subject[4][4]="IOPS332C";
    map_subject[5][6]="EAES3332C";
    map_subject[5][1]="EEFW330C";
    map_subject[5][2]="IOPS332C";
    map_subject[5][3]="EACN332C";
    map_subject[5][4]="EBEE332C";


    m_day[0]="MONDAY";
    m_day[1]="TUESDAY";
    m_day[2]="WEDNESDAY";
    m_day[3]="THURSDAY";
    m_day[4]="FRIDAY";
    


  clock_t start=clock();
int a=algo(0);//ts not present here see for correction
clock_t stop=clock();
//cout<<"final : "<<a<<endl;




    for (int sec = 0; sec < 6; sec++) {

    	    	string tmp="test"+IntToString(sec)+".txt";

    	char *q = const_cast<char*>(tmp.c_str());
    	 ofstream outfile(q);
	  ofstream out;
    out.open(q);
           // out << "Year: " << sec/3 << "\n";
      //  out << "SECTION: " << sec-(3*(sec/3)) <<"\t";
    //   out<<"    9-10"<<"\t"<<"\t"<<"\t"<<"\t     "<<"\t"<<"\t"<<"10-11"<<"\t"<<"\t"<<"\t"<<"\t        \t  "<<"11:15-12:15"<<"\t  \t \t"<<"\t"<<"       "<<"12:15-1:15"<<endl;
        for (int day = 0; day < 5; day++) {
        out<<m_day[day]+";";

            int flag=0;
            sort(v[sec][day].begin(), v[sec][day].end());
            for (vector < pair <int, pair<int, int> > >::iterator it = v[sec][day].begin(); it != v[sec][day].end(); it++) {
                
                pair<int, int>p = it->second;
                if(sec>2) //0,5 is space in batch 2
                {
                    if(p.first==0){
					out<<"$;";//printf("     \t");


					continue;

				}
				if(p.first==5){
					out<<"$;";//printf("     \t");
                   

					continue;

				}
                }
                if(sec<3){ //else
				
				if(p.first==6){ // 6 is space
					out<<"$;";//printf("     \t");
                  

					continue;

				}}
				out<<map_subject[sec][p.first]<<m[p.second]<<";";
				                



            }
            out<<"\n";
           


        }
       
    }




   /* for (int j = 0; j < 6; j++) {
        out << "SECTION :" << j << endl;
        for (int i = 0; i < 7; i++)
            out << i << " " << hours_left[j][i] << endl;
        out << endl;
    }

out<<"Time: "<<(stop-start)/double(CLOCKS_PER_SEC)<<endl;*/

    return 0;
}