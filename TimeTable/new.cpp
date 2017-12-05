	#include<bits/stdc++.h>
	#include<unistd.h>
	#include<string>
	using namespace std;
	int la = 100;
	int raka = 0;
	typedef struct subj_struct {
	    int elective;
		int linked_or_not;
		int linked_sec[ 10 ];
	    string s_sub[4];
	    int teacher[4];
	    string s_teacher[4];
	} sec_subj;

    int total_sec;
    int total_teacher=0;
	sec_subj waste;

int is_room_fake(int ll);
void print_room();
	int sec_room[60][10];
	int avail_room[400][6][4];
	int rooms_alloted[60][6][4];

	sec_subj mtech[60][6][4];


	sec_subj mtech_sub[60][8];
	sec_subj lab_btech[11][5];

	sec_subj tt_lab[11][5];
	int lab_hr[11][5];

	int avail_teacher[40000][6][6];
	int mtech_hr_left[60 ][8 ];

	int slot_busy[10000];

	int find_next_slot(int ts)
	{
		for(int i=ts+1;i<10000;i++)
		{
			if(slot_busy[i]==-1)
				return i;
		}
	}

	int all_hrs_finished_mtech() {
	    int flag = 1;
	    for (int j = 0; j < total_sec; j++) {
	        for (int i = 0; i < 8; i++) {
	            if (mtech_hr_left[j][i] > 0) {
	                flag = 0;
	                break;
	            }
	        }
	    }
	    return flag;
	}



	int find_sec_mtech(int ts)
	{
		return (ts % total_sec);
	}
	int find_day_mtech( int ts )
	{
		int x = find_sec_mtech(ts);
	    return (ts - x) / (total_sec*4);

	}

	int find_slot_mtech( int ts)
	{
		int x = find_sec_mtech(ts);
	    x = (ts - x) % (total_sec*4);
	    return x / total_sec;
	}

	int find_ts_mtech(int sec,int day,int slot)
	{
		return (total_sec*4)*day+total_sec*slot+sec;
	}




	string IntToString(int a) {
	    ostringstream temp;
	    temp << a;
	    return temp.str();
	}


void print_lab()
{
	 for (int sec = 0; sec < 11; sec++) {

	 		string tmp = "lab" + IntToString(sec) + ".txt";
	        char *q = const_cast<char*> (tmp.c_str());
	        ofstream outfile(q);
	        ofstream out;
	        out.open(q);
	        for (int day = 0; day < 5; day++) {

	        	out<<lab_btech[sec][day].s_sub[0]<<endl;
	        }

	 }


}
	void print_timetable_mtech() {

	    map<int, string>m_day;
	    m_day[0] = "MONDAY";
	    m_day[1] = "TUESDAY";
	    m_day[2] = "WEDNESDAY";
	    m_day[3] = "THURSDAY";
	    m_day[4] = "FRIDAY";

	    for (int sec = 0; sec < total_sec; sec++) {
	        string tmp = "test" + IntToString(sec) + ".txt";
	        char *q = const_cast<char*> (tmp.c_str());
	        ofstream outfile(q);
	        ofstream out;
	        out.open(q);
	        out<<"SEC : "<<sec<<endl;
	        for (int day = 0; day < 5; day++) {
	        	out<<m_day[day]<<";";



	map<int, string>m_day;
	    m_day[0] = "MONDAY";
	    m_day[1] = "TUESDAY";
	    m_day[2] = "WEDNESDAY";
	    m_day[3] = "THURSDAY";
	    m_day[4] = "FRIDAY";
	            for (int slot = 0; slot < 5; slot++) {
	            	if(slot==4&&day!=4)
	            	{

	            	if (mtech[sec][5][day].elective == 0)
	                out << "$";
	                else if (mtech[sec][5][day].elective == -1) {
	                    out << mtech[sec][5][day].s_sub[0] + " " <<mtech[sec][5][day].s_teacher[0]<<"!Room No : "<<rooms_alloted[sec][5][day];
	                } else if (mtech[sec][5][day].elective > 0) {
	                    int k = mtech[sec][5][day].elective;

	                    for (int q = 0; q < k; q++) {
	                        out << mtech[sec][5][day].s_sub[q] + "(" << mtech[sec][5][day].s_teacher[q] << ")";
	                        out << "!";
	                    }
	                    out<<"Room No : "<<rooms_alloted[sec][5][day];
	                }
	                out << ";";

	            		continue;
					}
	                if (mtech[sec][day][slot].elective == 0)
	                    out << "$";
	                else if (mtech[sec][day][slot].elective == -1) {
	                    out << mtech[sec][day][slot].s_sub[0] + " " << mtech[sec][day][slot].s_teacher[0]<<"!Room No : "<<rooms_alloted[sec][day][slot];
	                } else if (mtech[sec][day][slot].elective > 0) {
	                    int k = mtech[sec][day][slot].elective;

	                    for (int q = 0; q < k; q++) {
	                        out << mtech[sec][day][slot].s_sub[q] + "(" << mtech[sec][day][slot].s_teacher[q] << ")";
	                        out << "!";
	                    }
	                    out<<"Room No : "<<rooms_alloted[sec][day][slot];
	                }
	                out << ";";
	            }
	            out << "\n";
	        }
	    }

	}

int algo_lab(int ts)
{
	cout<<ts<<endl;
	int flag = 1;
	    for (int j = 0; j < 11; j++) {
	        for (int i = 0; i < 5; i++) {
	            if (lab_hr[j][i] > 0) {
	                flag = 0;
	                break;
	            }
	        }
	    }
	if(flag==1)
	{
		return 1;
	}
	int sec=ts%11;
	int day=(ts-sec)/11;
	for(int sub=0;sub<5;sub++)
	{
		if( avail_teacher[lab_btech[sec][sub].teacher[0]][day][5]==-1 && lab_hr[sec][sub] >0 )
		{
			tt_lab[sec][day]=lab_btech[sec][sub];
			avail_teacher[lab_btech[sec][sub].teacher[0]][day][5]=ts;
			lab_hr[sec][sub]--;
			if(algo_lab(ts+1))
			{
				return 1;
			}
			lab_hr[sec][sub]++;
			tt_lab[sec][day]=waste;
			avail_teacher[lab_btech[sec][sub].teacher[0]][day][5]=-1;
		}
	}
	return 0;



}

int find_s(int ts, int t){
	    sec_subj a = mtech[find_sec_mtech(ts)][find_day_mtech(ts)][find_slot_mtech(ts)];
	    for( int i = 0; i < 4; i++){
	        if ( a.teacher[i] == t)
	            return i;
	    }
	    return -1;
	}
/*

*/


	void prinf_teacher()
	{

	map<int, string>m_day;
	    m_day[0] = "MONDAY";
	    m_day[1] = "TUESDAY";
	    m_day[2] = "WEDNESDAY";
	    m_day[3] = "THURSDAY";
	    m_day[4] = "FRIDAY";

	    		ofstream outfile("teacher.txt");
		  ofstream out;
	    out.open("teacher.txt");

	    for(int i=0;i<=total_teacher;i++)
	    {   	out<<"Teacher:"<<i<<";"<<endl;

	    	for(int j=0;j<5;j++)
	    	{
	    		out<<m_day[j]<<";";
	    		for(int k=0;k<5;k++)
	    		{

					if(k == 4 && j!= 4){
	    				if(avail_teacher[i][5][j]== -1)
	    				{
	    					out<<"F"<<";";
	    					continue;
	    				}
	    				int indexx = find_s(avail_teacher[i][5][j], i);
						int ts = avail_teacher[i][5][j];
	    				sec_subj pp = mtech[find_sec_mtech(ts)][find_day_mtech(ts)][find_slot_mtech(ts)];
	    				out<<find_sec_mtech(ts)<<"("<<pp.s_sub[indexx]<<")"<<";";
						continue;
	    			}

	    			if(avail_teacher[i][j][k]== -1)
	    			{
	    				out<<"F"<<";";
	    				continue;
	    			}
	    			int indexx = find_s(avail_teacher[i][j][k], i);
	    			int ts = avail_teacher[i][j][k];
	    			sec_subj pp = mtech[find_sec_mtech(ts)][find_day_mtech(ts)][find_slot_mtech(ts)];
	    			out<<find_sec_mtech(ts)<<"("<<pp.s_sub[indexx]<<")"<<";";
	    		}
	    		out<<"\n";
	    	}
	    }
	}


	int search_mtech(string sub,int sec)
	{
		string str=sub.substr(0,4);
		for(int i=0;i<8;i++)
		{
			for(int j=0;j<4;j++)
			{
				if (mtech_sub[sec][i].s_sub[j].find(str)!=string::npos){

						return i;
					}

			}
		}
		return 100;
	}

	int free_or_not_mtech(int sec,int sub,int slot,int day)
	{
		if (mtech_sub[sec][sub].elective > 0) {
	        int k = mtech_sub[sec][sub].elective;
	        int flag = 0;
	        for (int i = 0; i < k; i++) {
	            if (avail_teacher[mtech_sub[sec][sub].teacher[i]][day][slot] != -1)
	                flag = 1;
	        }
	        if (flag == 0)
	            return 1;
	    } else {
	        if (avail_teacher[mtech_sub[sec][sub].teacher[0]][day][slot] == -1)
	            return 1;
	    }
	    return 0;
	}


	int count_hr_mtech(int sub,int ts)
	{
		int c = 0;
	    int slot = find_slot_mtech(ts);
	    int day = find_day_mtech(ts);
	    int sec = find_sec_mtech(ts);
	    for (int i = 0; i < slot; i++) {
	        if (mtech[sec][day][i].s_sub[0] == mtech_sub[sec][sub].s_sub[0]) {
	            c++;
	        }
	    }
	    return c;
	}



	int is_Safe_mtech(int sub, int ts) {

	    int slot = find_slot_mtech(ts);
	    int day = find_day_mtech(ts);
	    int sec = find_sec_mtech(ts);
	    if(mtech_sub[sec][sub].elective==-2&&mtech_hr_left[sec][sub]>0)
	    		return 1;
	    if (mtech_hr_left[sec][sub] == 3) {
	        int a = free_or_not_mtech(sec, sub, slot, day);
	        return a;
	    } else if (mtech_hr_left[sec][sub] == 2) {
	        if (mtech[sec][day][find_slot_mtech(ts) - 1].s_sub[0] == mtech_sub[sec][sub].s_sub[0] && find_slot_mtech(ts) != 2) {
	            int a = free_or_not_mtech(sec, sub, slot, day);
	        return a;
	        } else if (count_hr_mtech(sub, ts) == 0) {

	            int a = free_or_not_mtech(sec, sub, slot, day);
	        return a;
	        }
	    } else if (mtech_hr_left[sec][sub] == 1) {
	        if (mtech[sec][day][find_slot_mtech(ts) - 1].s_sub[0] == mtech_sub[sec][sub].s_sub[0] && find_slot_mtech(ts) != 2) {
	            if (count_hr_mtech(sub, ts) == 1) {
	               int a = free_or_not_mtech(sec, sub, slot, day);
	        return a;
	            }
	        }
	        if (count_hr_mtech(sub, ts) == 0) {
	            int a = free_or_not_mtech(sec, sub, slot, day);
	        return a;
	        }
	    }
	    return 0;
	}


	void clear(int l,int m)
	{
				mtech_sub[l][m].elective = 0;
	            mtech_sub[l][m].linked_or_not=-2;
	            for (int z = 0; z < 4; z++) {

	                stringstream ss;
	                ss << (75 * l + 10 * m + z);
	                string s = ss.str();
	                mtech_sub[l][m].s_sub[z] = "null" + s;
	                mtech_sub[l][m].teacher[z] = la++;
	                mtech_sub[l][m].s_teacher[z] = "  ";
	                  mtech_sub[l][m].linked_sec[z]=-2;

				}
	}


	int algo_mtech(int ts) {
	    if (all_hrs_finished_mtech()) {
	        return 1;
	    }
	       int day = find_day_mtech(ts);
	    int sec = find_sec_mtech(ts);
	    int slot = find_slot_mtech(ts);

	    for (int sub = 0; sub < 8; sub = sub + 1) {
	    	int vipul_flag = 0;
	    	if(mtech_sub[sec][sub].linked_or_not ==-5 ){

	        		continue;
	        	}
	        	if (mtech_sub[sec][sub].linked_or_not>0 )
	            {
	            	for(int z=0;z<mtech_sub[sec][sub].linked_or_not;z++ )
	            	{
	            		if(slot_busy[find_ts_mtech(mtech_sub[sec][sub].linked_sec[z],day,slot)] == 1){
	            			vipul_flag = 1;
	            			break;
						}
					}
					if(vipul_flag == 1){
						continue;
					}

				}
	        if (is_Safe_mtech(sub, ts)) {
	            sec_subj p = mtech_sub[sec][sub];
	            mtech_hr_left[find_sec_mtech(ts)][sub]--;
	            mtech[sec][day][find_slot_mtech(ts)] = p;

	            if (mtech_sub[sec][sub].elective > 0) {
	                int k = mtech_sub[sec][sub].elective;
	                int flag = 0;
	                for (int i = 0; i < k; i++) {
	                    avail_teacher[mtech_sub[sec][sub].teacher[i]][day][slot] = ts;
	                }

	            } else {
	                avail_teacher[mtech_sub[sec][sub].teacher[0]][day][slot] = ts;
	            }

	            if (mtech_sub[sec][sub].linked_or_not>0 )
	            {
	            	for(int z=0;z<mtech_sub[sec][sub].linked_or_not;z++ )
	            	{
	            		mtech[mtech_sub[sec][sub].linked_sec[z]][day][slot]=p;
	            		slot_busy[find_ts_mtech(mtech_sub[sec][sub].linked_sec[z],day,slot)]=1;
					}

				}
	            if (algo_mtech(find_next_slot(ts))) {
	                return 1;
	            }
	            if (mtech_sub[sec][sub].elective > 0) {
	                int k = mtech_sub[sec][sub].elective;
	                int flag = 0;
	                for (int i = 0; i < k; i++) {
	                    avail_teacher[mtech_sub[sec][sub].teacher[i]][day][slot] = -1;
	                }
	            } else {
	                avail_teacher[mtech_sub[sec][sub].teacher[0]][day][slot] = -1;
	            }
	            mtech[sec][day][find_slot_mtech(ts)] = waste;
	            mtech_hr_left[find_sec_mtech(ts)][sub]++;


	            if (mtech_sub[sec][sub].linked_or_not>0 )
	            {
	            	for(int z=0;z<mtech_sub[sec][sub].linked_or_not;z++ )
	            	{
	            		mtech[mtech_sub[sec][sub].linked_sec[z]][day][slot]=waste;
	            		slot_busy[find_ts_mtech(mtech_sub[sec][sub].linked_sec[z],day,slot)]=-1;
	            		

					}

				}




	        }
	    }
	    return 0;
	}






	int main(int argc, char** argv) {
			for (int i = 0; i < 60; i++) {
	        for (int j = 0; j < 8; j++) {
	            mtech_hr_left[i][j] = 3;
	        }
	    }

	    string s;
	    waste.s_sub[0] = "faltu";
	    waste.elective = -2;
	   
	    string sss="";
	    for(int i=1;i<argc;i++){
	    	string xxx(argv[i]);
	    	if(i==1)
	    		sss=sss+xxx;
	    		else
	    	sss=sss+" "+xxx;
		}
		char * S = new char[sss.length() + 1];
		strcpy(S,sss.c_str());

	    ifstream inp_file;
	    inp_file.open(S);
	    int c;
	    int sec;
	    int no_subj;
	    int j;
	
	    inp_file >>total_sec;

		for (int i = 0; i < 40000; i++) {
	        for (int j = 0; j < 6; j++) {
	            for (int k = 0; k < 5; k++)
	                avail_teacher[i][j][k] = -1;

	        }
	    }

	    for (int i = 0; i < total_sec; i++) {
	        for (int j = 0; j < 6; j++) {
	            for (int k = 0; k < 4; k++)
	                rooms_alloted[i][j][k] = -1;

	        }
	    }
	    for (int i = 0; i < 400; i++) {
	        for (int j = 0; j < 6; j++) {
	            for (int k = 0; k < 4; k++)
	                avail_room[i][j][k] = -1;

	        }
	    }
	   
		int aa;

		for(int l=0;l<total_sec+10;l++)
		{
			for(int m=0;m<8;m++)
				{
				mtech_sub[l][m].elective = -2;
	            mtech_sub[l][m].linked_or_not=-2;
	            for (int z = 0; z < 4; z++) {

	                stringstream ss;
	                ss << (99 * l + 10 * m + z);
	                s = ss.str();
	                mtech_sub[l][m].s_sub[z] = "null" + s;
	                mtech_sub[l][m].teacher[z] = la++;
	                mtech_sub[l][m].s_teacher[z] = "  ";
	                  mtech_sub[l][m].linked_sec[z]=-2;

				}
		}
	}
	for (int i = 0; i < total_sec; i++) {
	        for (int j = 0; j < 8; j++) {
	            mtech_hr_left[i][j] = 3;
	        }
	    }
	  

		for(int i=0 ;i<10000;i++)
		{
			slot_busy[i]=-1;
		}

	j=0;
	int rishabh=0;
	for (int i = 0; i < total_sec; i++) {

	        inp_file >> sec >> no_subj;


	        j = 0;
	        if (no_subj == 5) {
	            j++;
	            no_subj++;
	        }
	       
	    	inp_file >> sec_room[i][0] ;
	    	
			for (int q=1; q<= sec_room[i][0];q++)
			{
				inp_file >> sec_room[i][q];
			
			}
	        for (; j < no_subj; j++) {
	        
	            inp_file >> c >> mtech_sub[i][j].elective;
            if (mtech_sub[i][j].elective == -1) {
	                inp_file >> mtech_sub[i][j].s_sub[0];
	                inp_file >> mtech_sub[i][j].teacher[0];
	                if(total_teacher< mtech_sub[i][j].teacher[0] )
	                			total_teacher=mtech_sub[i][j].teacher[0];
	                inp_file >> mtech_sub[i][j].s_teacher[0];

	            } else {
	                for (int k = 0; k < mtech_sub[i][j].elective; k++) {
	                    inp_file >> mtech_sub[i][j].s_sub[k];
	                    inp_file >> mtech_sub[i][j].teacher[k];
	                    if(total_teacher< mtech_sub[i][j].teacher[k] )
	                			total_teacher=mtech_sub[i][j].teacher[k];
	                    inp_file >> mtech_sub[i][j].s_teacher[k];
	                }
	            }
	 		inp_file >> mtech_sub[i][j].linked_or_not;
	           if( mtech_sub[i][j].linked_or_not==1 )
	            {
	            	int x;
	            	inp_file >> x;
	            	mtech_hr_left[i][j]=0;
	            
						int subject=search_mtech(mtech_sub[i][j].s_sub[0],x);
					
						mtech_sub[i][j].linked_or_not=-5;

						mtech_sub[x][subject].linked_sec[mtech_sub[x][subject].linked_or_not]=i;
						mtech_sub[x][subject].linked_or_not++;


				}

	        }

	    }
	 
		algo_mtech(0);

for(int i=0;i<11;i++)
{
	for(int j=0;j<5;j++)
		lab_hr[i][j]=1;
}
for(int l=0;l<11;l++)
		{
			for(int m=0;m<5;m++)
				{
				lab_btech[l][m].elective = -2;
	            lab_btech[l][m].linked_or_not=-2;
	            for (int z = 0; z < 4; z++) {

	                stringstream ss;
	                ss << (99 * l + 10 * m + z);
	                s = ss.str();
	                lab_btech[l][m].s_sub[z] = "null" + s;
	                lab_btech[l][m].teacher[z] = la++;
	                lab_btech[l][m].s_teacher[z] = "  ";
	                  lab_btech[l][m].linked_sec[z]=-2;

				}
		}
	}
int k=0;
for(int i=0;i<11;i++)
 {
	 k=0;
	 for(int j=0;j<8;j++){
	 	int n=mtech_sub[i][j].s_sub[0].size()-2;
	 	if( mtech_sub[i][j].s_sub[0][8]=='-' )
	 			n=6;
	 if ( mtech_sub[i][j].s_sub[0][n]=='2' ){


		 lab_btech[i][k]=mtech_sub[i][j];
		 lab_hr[i][k]=1;
		 k++;


		}

	
	 }
}
	algo_lab(0);
		int falg=0;
		for ( int i=0;i<total_sec;i++ )
		{
			falg=0;
			if (sec_room[i][0]==1)
				falg=1;
			cout<<"SEC "<<i<<" "<<falg<<endl;
			for ( int j=0;j<6;j++ )
			{
				for(int k=0;k<4;k++)
				{
						if ( mtech[i][j][k].elective==-2 )
								continue;
						if(rooms_alloted[i][j][k]==-1)
						{

							if (falg==1)
								{

								rooms_alloted[i][j][k]=sec_room[i][1];
								avail_room[sec_room[i][1]][j][k]=i;
								if( i==9 )
								{
										avail_room[23][j][k]=i;
											avail_room[33][j][k]=i;
								}

								if (mtech[i][j][k].linked_or_not>0)
								{

									for(int q=0;q<mtech[i][j][k].linked_or_not;q++)
									{
										rooms_alloted[mtech[i][j][k].linked_sec[q]][j][k]=sec_room[i][1];

									}
								}
							}
							else
							{
								int f=0;
								for(int q=1;q<=sec_room[i][0];q++)
								{
									if(avail_room[sec_room[i][q]][j][k]==-1)
									{

										f=1;
										rooms_alloted[i][j][k]=sec_room[i][q];
										avail_room[sec_room[i][q]][j][k]=i;
										if (mtech[i][j][k].linked_or_not>0)
											{

												for(int qq=0;qq<mtech[i][j][k].linked_or_not;qq++)
												{
													rooms_alloted[mtech[i][j][k].linked_sec[qq]][j][k]=sec_room[i][q];
												}


											}
										break;
										}
									}

									if(f==0)
									{
										rooms_alloted[i][j][k]=350;
										if (mtech[i][j][k].linked_or_not>0)
										{

											for(int q=0;q<mtech[i][j][k].linked_or_not;q++)
											{
												rooms_alloted[mtech[i][j][k].linked_sec[q]][j][k]=350;
											}


										}



									}


								}
						}

					}

				}
		}

	
		print_timetable_mtech();
		prinf_teacher();
		print_lab();
		print_room();
		printf("%d",total_teacher);
		
	    return 0;
	}
	
	void print_room() 
	{
		map<int, string>m_day;
	    m_day[0] = "MONDAY";
	    m_day[1] = "TUESDAY";
	    m_day[2] = "WEDNESDAY";
	    m_day[3] = "THURSDAY";
	    m_day[4] = "FRIDAY";

	    for (int room = 0; room < 400; room++) {
	    	if (is_room_fake(room) )continue;
	    	int cnt = 0;
	        string tmp = "room" + IntToString(room) + ".txt";
	        char *q = const_cast<char*> (tmp.c_str());
	        ofstream outfile(q);
	        ofstream out;
	        out.open(q);
	        out<<"ROOM : "<<room<<endl;
	        int day;
	        for (day = 0; day < 5; day++) {
	        	out << m_day[day] << ";";
	        	for (int slot = 0 ; slot < 4; slot++){
	        		out << avail_room[room][day][slot] <<";";
				}
				if ( cnt == 4)
					 out << "-1" <<endl;
				else{
					out << avail_room[room][5][cnt]<<endl;
					cnt++;
				}
			}
		}

	}
	
	int is_room_fake(int ll)
	{
		int mm, nn;
		for ( mm = 0; mm < 6; mm ++){
			for ( nn = 0; nn < 4; nn++){
				if(avail_room[ll][mm][nn] != -1){
					return 0;
				}
			}
		}
		return 1;
	}
