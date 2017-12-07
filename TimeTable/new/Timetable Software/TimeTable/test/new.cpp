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

	/*typedef struct find_sub{
		int day[3];
		int slot[3];
	}found_sub_btech;*/

	sec_subj waste;


	int sec_room[50][10];
	int avail_room[400][6][4];

	//sec_subj v[10][5][4];
	sec_subj mtech[40][6][4];


	//sec_subj sec_teacher[10][7];
	sec_subj mtech_sub[40][8];

	int avail_teacher[40000][6][4];
	//int hours_left[10][7];
	int mtech_hr_left[40 ][8 ];

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
	    for (int j = 0; j < 38; j++) {
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
		return (ts % 38);
	}
	int find_day_mtech( int ts )
	{
		int x = find_sec_mtech(ts);
	    return (ts - x) / 152;

	}

	int find_slot_mtech( int ts)
	{
		int x = find_sec_mtech(ts);
	    x = (ts - x) % 152;
	    return x / 38;
	}

	int find_ts_mtech(int sec,int day,int slot)
	{
		return 152*day+38*slot+sec;
	}




	string IntToString(int a) {
	    ostringstream temp;
	    temp << a;
	    return temp.str();
	}

	/*int find_sec(int ts) {
	    return (ts % 10);
	}

	int find_day(int ts) {
	    int x = find_sec(ts);
	    return (ts - x) / 40;
	}

	int find_slot(int ts) {
	    int x = find_sec(ts);
	    x = (ts - x) % 40;
	    return x / 10;
	}

	int all_hrs_finished() {
	    int flag = 1;
	    for (int j = 0; j < 10; j++) {
	        for (int i = 0; i < 7; i++) {
	            if (hours_left[j][i] > 0) {
	                flag = 0;
	                break;
	            }
	        }
	    }
	    return flag;
	}

	void all_hrs_finished_print() {
	    int flag = 1;
	    for (int j = 0; j < 10; j++) {
	        for (int i = 0; i < 7; i++) {
	            //cout << hours_left[j][i] << endl;
	        }
	    }
	}

	int free_or_not(int sec, int sub, int slot, int day) {
	    if (sec_teacher[sec][sub].elective > 0) {
	        int k = sec_teacher[sec][sub].elective;
	        int flag = 0;
	        for (int i = 0; i < k; i++) {
	            if (avail_teacher[sec_teacher[sec][sub].teacher[i]][day][slot] != -1)
	                flag = 1;
	        }
	        if (flag == 0)
	            return 1;
	    } else {
	        if (avail_teacher[sec_teacher[sec][sub].teacher[0]][day][slot] == -1)
	            return 1;
	    }
	    return 0;
	}

	int count_hr(int sub, int ts) {
	    int c = 0;
	    int slot = find_slot(ts);
	    int day = find_day(ts);
	    int sec = find_sec(ts);
	    for (int i = 0; i < slot; i++) {
	        if (v[sec][day][i].s_sub[0] == sec_teacher[sec][sub].s_sub[0]) {
	            c++;
	        }
	    }
	    return c;
	}

	int is_Safe(int sub, int ts) {
	    int slot = find_slot(ts);
	    int day = find_day(ts);
	    int sec = find_sec(ts);
	    if (hours_left[sec][sub] == 3) {
	        int a = free_or_not(sec, sub, slot, day);
	        return a;
	    } else if (hours_left[sec][sub] == 2) {
	        if (v[sec][day][find_slot(ts) - 1].s_sub[0] == sec_teacher[sec][sub].s_sub[0] && find_slot(ts) != 2) {
	            return free_or_not(sec, sub, slot, day);
	        } else if (count_hr(sub, ts) == 0) {
	            return free_or_not(sec, sub, slot, day);
	        }
	    } else if (hours_left[sec][sub] == 1) {
	        if (v[sec][day][find_slot(ts) - 1].s_sub[0] == sec_teacher[sec][sub].s_sub[0] && find_slot(ts) != 2) {
	            if (count_hr(sub, ts) == 1) {
	                return free_or_not(sec, sub, slot, day);
	            }
	        }
	        if (count_hr(sub, ts) == 0) {
	            return free_or_not(sec, sub, slot, day);
	        }
	    }
	    return 0;
	}

	int algo(int ts) {
	    if (all_hrs_finished()) {
	        return 1;
	    }
	    //printf("%d\n",ts);
	    int day = find_day(ts);
	    int sec = find_sec(ts);
	    int slot = find_slot(ts);
	    for (int sub = 0; sub < 7; sub = sub + 1) {
	        if (is_Safe(sub, ts)) {
	            sec_subj p = sec_teacher[sec][sub];
	            hours_left[find_sec(ts)][sub]--;
	            v[sec][day][find_slot(ts)] = p;
	            if (sec_teacher[sec][sub].elective > 0) {
	                int k = sec_teacher[sec][sub].elective;
	                int flag = 0;
	                for (int i = 0; i < k; i++) {
	                    avail_teacher[sec_teacher[sec][sub].teacher[i]][day][slot] = ts;
	                }

	            } else {
	                avail_teacher[sec_teacher[sec][sub].teacher[0]][day][slot] = ts;
	            }

	            if (algo(ts + 1)) {

	                return 1;
	            }
	            if (sec_teacher[sec][sub].elective > 0) {
	                int k = sec_teacher[sec][sub].elective;
	                int flag = 0;
	                for (int i = 0; i < k; i++) {
	                    avail_teacher[sec_teacher[sec][sub].teacher[i]][day][slot] = -1;
	                }
	            } else {
	                avail_teacher[sec_teacher[sec][sub].teacher[0]][day][slot] = -1;
	            }
	            v[sec][day][find_slot(ts)] = waste;
	            hours_left[find_sec(ts)][sub]++;
	        }
	    }
	    return 0;
	}

	void print_timetable() {

	    map<int, string>m_day;
	    m_day[0] = "MONDAY";
	    m_day[1] = "TUESDAY";
	    m_day[2] = "WEDNESDAY";
	    m_day[3] = "THURSDAY";
	    m_day[4] = "FRIDAY";

	    for (int sec = 0; sec < 10; sec++) {
	        string tmp = "test" + IntToString(sec) + ".txt";
	        char *q = const_cast<char*> (tmp.c_str());
	        ofstream outfile(q);
	        ofstream out;
	        out.open(q);
	        for (int day = 0; day < 5; day++) {
	            out << m_day[day] << ";";

	map<int, string>m_day;
	    m_day[0] = "MONDAY";
	    m_day[1] = "TUESDAY";
	    m_day[2] = "WEDNESDAY";
	    m_day[3] = "THURSDAY";
	    m_day[4] = "FRIDAY";
	            for (int slot = 0; slot < 4; slot++) {
	                if (v[sec][day][slot].elective == 0)
	                    out << "$";
	                else if (v[sec][day][slot].elective == -1) {
	                    out << v[sec][day][slot].s_sub[0] + " " << v[sec][day][slot].s_teacher[0];
	                } else if (v[sec][day][slot].elective > 0) {
	                    int k = v[sec][day][slot].elective;

	                    for (int q = 0; q < k; q++) {
	                        out << v[sec][day][slot].s_sub[q] + " (" << v[sec][day][slot].s_teacher[q] << " )";
	                        out << "!";
	                    }
	                }
	                out << ";";
	            }
	            out << "\n";
	        }
	    }

	}

	int find_s(int ts, int t){
	    sec_subj a = v[find_sec(ts)][find_day(ts)][find_slot(ts)];
	    for( int i = 0; i < 4; i++){
	        if ( a.teacher[i] == t)
	            return i;
	    }
	    return 0;
	}

	string getName(int i){
	    sec_subj a;
	    int ts = 0;
	    for ( int j = 0; j < 5; j++){
	        for ( int k = 0; k < 4; k++){
	            if (avail_teacher[i][j][k] != -1 ){
	                ts = avail_teacher[i][j][k];
	                break;
	            }
	        }
	        if (ts != 0) break;
	    }
	    a=v[find_sec(ts)][find_day(ts)][find_slot(ts)];
	    int indexx = find_s(ts, i);
	    return a.s_teacher[indexx];
	}

	string find_batch(int num){
	    switch(num){
	    case 0:
	        return "B.Tech(I.T)1st Year SEC 1!";
	    case 1:
	        return "B.Tech(I.T)1st Year SEC 2!";
	    case 2:
	        return "B.Tech (ECE)1st Year!";
	    case 3:
	        return "B.Tech(I.T)2nd Year SEC 1!";
	    case 4:
	        return "B.Tech(I.T)2nd Year SEC 2!";
	    case 5:
	        return "B.Tech(ECE) 2nd Year!";
	    case 6:
	        return "B.Tech(I.T) 3rd Year SEC 1!";
	    case 7:
	        return "B.Tech(I.T) 3rd Year SEC 2!";
	    case 8:
	        return "B.Tech(ECE) 3rd Year!";
	    case 9:
	        return "B.Tech(I.T) 4th Year!";
	    default:
	        return "";
	    }

	}*/


	void print_timetable_mtech() {

	    map<int, string>m_day;
	    m_day[0] = "MONDAY";
	    m_day[1] = "TUESDAY";
	    m_day[2] = "WEDNESDAY";
	    m_day[3] = "THURSDAY";
	    m_day[4] = "FRIDAY";

	    for (int sec = 0; sec < 38; sec++) {
	        string tmp = "test" + IntToString(sec) + ".txt";
	        char *q = const_cast<char*> (tmp.c_str());
	        ofstream outfile(q);
	        ofstream out;
	        out.open(q);
	        out<<"SEC : "<<sec<<endl;
	        for (int day = 0; day < 5; day++) {
	        	//out<<"DAY : "<<day<<" ; ";
	        	out<<m_day[day]<<";";


	            //out << m_day[day] << ";";

	map<int, string>m_day;
	    m_day[0] = "MONDAY";
	    m_day[1] = "TUESDAY";
	    m_day[2] = "WEDNESDAY";
	    m_day[3] = "THURSDAY";
	    m_day[4] = "FRIDAY";
	            for (int slot = 0; slot < 5; slot++) {
	            	if(slot==4&&day!=4)
	            	{
	            		//mtech[sec][5][day]

	            	if (mtech[sec][5][day].elective == 0)
	                out << "$";
	                else if (mtech[sec][5][day].elective == -1) {
	                    out << mtech[sec][5][day].s_sub[0] + " " <<mtech[sec][5][day].s_teacher[0];
	                } else if (mtech[sec][5][day].elective > 0) {
	                    int k = mtech[sec][5][day].elective;

	                    for (int q = 0; q < k; q++) {
	                        out << mtech[sec][5][day].s_sub[q] + " (" << mtech[sec][5][day].s_teacher[q] << " )";
	                        out << "!";
	                    }
	                }
	                out << ";";

	            		continue;
					}
	                if (mtech[sec][day][slot].elective == 0)
	                    out << "$";
	                else if (mtech[sec][day][slot].elective == -1) {
	                    out << mtech[sec][day][slot].s_sub[0] + " " << mtech[sec][day][slot].s_teacher[0];
	                } else if (mtech[sec][day][slot].elective > 0) {
	                    int k = mtech[sec][day][slot].elective;

	                    for (int q = 0; q < k; q++) {
	                        out << mtech[sec][day][slot].s_sub[q] + " (" << mtech[sec][day][slot].s_teacher[q] << " )";
	                        out << "!";
	                    }
	                }
	                out << ";";
	            }
	            out << "\n";
	        }
	    }

	}







	/*void prinf_teacher()
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

	    for(int i=0;i<43;i++)
	    {   	out<<"Teacher: "<<getName(i)<<";"<<endl;

	    	for(int j=0;j<5;j++)
	    	{
	    		out<<m_day[j]<<";";
	    		for(int k=0;k<4;k++)
	    		{
	    			if(avail_teacher[i][j][k]== -1)
	    			{
	    				out<<"F"<<";";
	    				continue;
	    			}
	    			int indexx = find_s(avail_teacher[i][j][k], i);
	    			int ts = avail_teacher[i][j][k];
	    			sec_subj pp = v[find_sec(ts)][find_day(ts)][find_slot(ts)];
	    			out<<find_batch(find_sec(ts))<<"("<<pp.s_sub[indexx]<<")"<<";";
	    		}
	    		out<<"\n";
	    	}
	    }
	}
	*/
	/*found_sub_btech search_btech(string sub ,int sec)
	{
		int j=0;
		found_sub_btech var;
		string str=sub.substr(0,4);
		//cout<<str<<endl;
		for(int day=0 ; day <5 ; day++)
		{
			for (int slot=0;slot<4;slot++)
			{


				for (int i=0 ;i < 4; i++ ){

					if (v[sec][day][slot].s_sub[i].find(str)!=string::npos)
						{
							////cout<<v[sec][day][slot].s_sub[i]<<endl;
							var.day[j]=day;
							var.slot[j++]=slot;
							////cout<<j<<endl;
							break;
						}
				}
			}
		}
		return var;
	}
	 */

	int search_mtech(string sub,int sec)
	{
		string str=sub.substr(0,4);
		////cout<<"str:"<<str<<endl;
		for(int i=0;i<8;i++)
		{
			for(int j=0;j<4;j++)
			{
				if (mtech_sub[sec][i].s_sub[j].find(str)!=string::npos){

						////cout<<"i = "<<i<<endl;
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
	        //printf("returning for sub %d : value :%d\n",sub,a);
	        return a;
	    } else if (mtech_hr_left[sec][sub] == 2) {
	        if (mtech[sec][day][find_slot_mtech(ts) - 1].s_sub[0] == mtech_sub[sec][sub].s_sub[0] && find_slot_mtech(ts) != 2) {
	            int a = free_or_not_mtech(sec, sub, slot, day);
	        //printf("returning for sub %d : value :%d\n",sub,a);
	        return a;
	        } else if (count_hr_mtech(sub, ts) == 0) {

	            int a = free_or_not_mtech(sec, sub, slot, day);
	        //printf("returning for sub %d : value :%d\n",sub,a);
	        return a;
	        }
	    } else if (mtech_hr_left[sec][sub] == 1) {
	        if (mtech[sec][day][find_slot_mtech(ts) - 1].s_sub[0] == mtech_sub[sec][sub].s_sub[0] && find_slot_mtech(ts) != 2) {
	            if (count_hr_mtech(sub, ts) == 1) {
	               int a = free_or_not_mtech(sec, sub, slot, day);
	        //printf("returning for sub %d : value :%d\n",sub,a);
	        return a;
	            }
	        }
	        if (count_hr_mtech(sub, ts) == 0) {
	            int a = free_or_not_mtech(sec, sub, slot, day);
	        //printf("returning for sub %d : value :%d\n",sub,a);
	        return a;
	        }
	    }
	    //printf("returning for sub %d : value : default\n",sub);
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

	    /*if(ts > 900){ //859 897
	    	printf("%d\n",ts);
	    	return 1;
	       // printf("entry ts = %d\n",ts);
	    }*/
	    if(ts < 388 && raka ==1)
			//printf("entry ts = %d\n asdasfdsafdfsdfafgfdggfsdg",ts);
			return 1;
	    //print_timetable_mtech();
	    int day = find_day_mtech(ts);
	    int sec = find_sec_mtech(ts);
	    int slot = find_slot_mtech(ts);
	    //cout<<"day: "<<day<<" sec: "<<sec<<" slot: "<<slot<<"ts: "<<ts<<endl;

	    for (int sub = 0; sub < 8; sub = sub + 1) {
	    	int vipul_flag = 0;
	    	if(mtech_sub[sec][sub].linked_or_not ==-5 ){

				//printf("found -5 means linked somewhere \n");
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
	    	//cout<<"checking sub no : "<<sub<<endl;
	        if (is_Safe_mtech(sub, ts)) {


	        	//cout <<ts<<" "<<sec<<" "<<sub<<" "<< mtech_sub[sec][sub].s_sub[0]<<endl;

	        	//cout<<"day:"<<day<<" sec: "<<sec<<" slot: "<<slot<<"ts "<<ts<<" sub:"<<sub<<endl;

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
	            		/*int s=search_mtech(mtech_sub[sec][sub].s_sub[0],mtech_sub[sec][sub].linked_sec[z]);
	            		mtech_hr_left[mtech_sub[sec][sub].linked_sec[z]][s]--;*/
					}

				}
				//printf("done placing all values\n");
	            if (algo_mtech(find_next_slot(ts))) {
	                return 1;
	            }
	            //printf("backtracking starts for ts = %d  sfjhsuihdfiushdgoiushfuighsfuighiuahfgiuhasiugfhiuh\n",ts);
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
	            		/*int s=search_mtech(mtech_sub[sec][sub].s_sub[0],mtech_sub[sec][sub].linked_sec[z]);
	            		mtech_hr_left[mtech_sub[sec][sub].linked_sec[z]][s]++;*/

					}

				}
				//printf("done undoing values for ts = %d\n",ts);




	        }
	    }
	    return 0;
	}






	int main(int argc, char** argv) {

	    string s;
	    waste.s_sub[0] = "faltu";
	    waste.elective = -2;
	    /*for (int l = 0; l < 10; l++) {
	        for (int m = 0; m < 7; m++) {
	            sec_teacher[l][m].elective = -2;
	            sec_teacher[l][m].linked_or_not=-2;
	            for (int z = 0; z < 4; z++) {

	                stringstream ss;
	                ss << (100 * l + 10 * m + z);
	                s = ss.str();
	                sec_teacher[l][m].s_sub[z] = "null" + s;
	                sec_teacher[l][m].teacher[z] = la++;
	                sec_teacher[l][m].s_teacher[z] = "  ";
	                  sec_teacher[l][m].linked_sec[z]=-2;
	            }
	        }
	    }*/
	    string sss="";
	    for(int i=1;i<argc;i++){
	    	string xxx(argv[i]);
	    	if(i==1)
	    		sss=sss+xxx;
	    		else
	    	sss=sss+" "+xxx;
		}
		////cout<<sss<<endl;
		char * S = new char[sss.length() + 1];
		strcpy(S,sss.c_str());
		////cout<<S;

	    ifstream inp_file;
	    inp_file.open(S);
	    int c;
	    int sec;
	    int no_subj;
	    int j;
	    /*for (int i = 0; i < 10; i++) {
	        inp_file >> sec >> no_subj;

	        j = 0;
	        if (no_subj == 5) {
	            sec_teacher[i][0].elective = 0;
	            sec_teacher[i][6].elective = 0;
	            j++;
	            no_subj++;
	        } else if (no_subj == 6) {
	            sec_teacher[i][6].elective = 0;
	        }
	        inp_file >> sec_room[i][0] ;
			for (int q=1; q<= sec_room[i][0];q++)
			{
				inp_file >> sec_room[i][q];
			}
	        for (; j < no_subj; j++) {
	            inp_file >> c >> sec_teacher[i][j].elective;


	            if (sec_teacher[i][j].elective == -1) {
	                inp_file >> sec_teacher[i][j].s_sub[0];
	                inp_file >> sec_teacher[i][j].teacher[0];
	                inp_file >> sec_teacher[i][j].s_teacher[0];
	            } else {
	                for (int k = 0; k < sec_teacher[i][j].elective; k++) {
	                    inp_file >> sec_teacher[i][j].s_sub[k];
	                    inp_file >> sec_teacher[i][j].teacher[k];
	                    inp_file >> sec_teacher[i][j].s_teacher[k];
	                }
	            }
	 inp_file >> sec_teacher[i][j].linked_or_not;
	            //////cout <<"MERA WALA"<< sec_teacher[i][j].linked_or_not<<endl;
	            if( sec_teacher[i][j].linked_or_not==1 )
	            {
	            	////cout << "LELE"<<endl;
	            	inp_file >> sec_teacher[i][j].linked_sec[0];
				}

	        }

	    }
	    	////cout << sec_teacher[9][0].s_sub[2] << " " << sec_teacher[9][0].teacher[2]  << " " << sec_teacher[9][0].s_teacher[2]<<endl;
	    for(int t=0;t < 7 ; t++)
	    {
	    	for(int tt=0;tt<3;tt++)
	    	{
	    		////cout << sec_teacher[9][t].s_sub[tt] << " " << sec_teacher[9][t].teacher[tt]  << " " << sec_teacher[9][t].s_teacher[tt]<<endl;
			}
		}
		for (int t=0;t<10;t++)
		{
			for (int tt=1;tt<=sec_room[t][0];tt++)
			{
				////cout<<sec_room[t][tt]<<endl;
			}
		}
	    ////cout << sec_teacher[0][0].s_sub[0]<<endl;
	    ////cout << sec_teacher[0][0].elective<<endl;

	    for (int i = 0; i < 10; i++) {
	        for (int j = 0; j < 6; j++) {
	            hours_left[i][j] = 3;
	        }
	    }
	    for (int i = 0; i < 10; i++) {
	        hours_left[i][6] = 2;
	    }*/

		for (int i = 0; i < 40000; i++) {
	        for (int j = 0; j < 6; j++) {
	            for (int k = 0; k < 4; k++)
	                avail_teacher[i][j][k] = -1;
	        }
	    }

	    clock_t start = clock();
	    //int a = algo(0);
	    clock_t stop = clock();
	   // print_timetable();
	 //prinf_teacher();
		int aa;
	//	inp_file >> aa;
		////cout<<" beee";



		for(int l=0;l<40;l++)
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



	ofstream out;
	out.open("new_raka");

	for (int i = 0; i < 38; i++) {
	        for (int j = 0; j < 8; j++) {
	            mtech_hr_left[i][j] = 3;
	        }
	    }
	   /* for (int i = 0; i < 38; i++) {
	        mtech_hr_left[i][6] = 2;
	    }*/

		for(int i=0 ;i<10000;i++)
		{
			slot_busy[i]=-1;
		}

	j=0;
	int rishabh=0;
	for (int i = 0; i < 38; i++) {
		//cout<<i<<endl;

	        inp_file >> sec >> no_subj;

	        //////////cout <<"tgb";

	        j = 0;
	        if (no_subj == 5) {
	            j++;
	            no_subj++;
	        }
	        /*else if(no_subj == 4){
	        	mtech_sub[i][0].elective = 0;
	        	mtech_sub[i][3].elective = 0;
	            mtech_sub[i][6].elective = 0;
	            j++;
	            no_subj++;
	            no_subj++;
	            rishabh = 1;
			}*/
	    	inp_file >> sec_room[i][0] ;
	    	//////////cout<<"rooms:"<<sec_room[i+10][0]<<endl;
	    	//out << sec_room[i+10][0];
			for (int q=1; q<= sec_room[i][0];q++)
			{
				inp_file >> sec_room[i][q];
				////cout << "Room :"<<sec_room[i+10][q]<<endl;
				//out << sec_room[i+10][q];
			}
	        for (; j < no_subj; j++) {
	        	////cout<<mtech_sub[i-2][0].s_sub[0]<<"j : "<<j<<"sub "<<no_subj<<endl;
	        	//cout<<"outer j :"<<j<<endl;
	        	/*if(rishabh == 1 && j == 3){
	        		rishabh = 0;
	        		continue;
				}*/
	            inp_file >> c >> mtech_sub[i][j].elective;
	            //out << c << mtech_sub[i][j].elective;



	            if (mtech_sub[i][j].elective == -1) {
	                inp_file >> mtech_sub[i][j].s_sub[0];
	                inp_file >> mtech_sub[i][j].teacher[0];
	                inp_file >> mtech_sub[i][j].s_teacher[0];

	            } else {
	                for (int k = 0; k < mtech_sub[i][j].elective; k++) {
	                    inp_file >> mtech_sub[i][j].s_sub[k];
	                    inp_file >> mtech_sub[i][j].teacher[k];
	                    inp_file >> mtech_sub[i][j].s_teacher[k];
	                    //cout<<" j "<<j<<endl;
	                }
	            }
	 		inp_file >> mtech_sub[i][j].linked_or_not;
	           if( mtech_sub[i][j].linked_or_not==1 )
	            {
	            	int x;
	            	inp_file >> x;
	            	mtech_hr_left[i][j]=0;
	            	//cout<<mtech_sub[i][j].s_sub[0]<<endl;
	            	//cout<<i<<" x: "<<x<<endl;



						int subject=search_mtech(mtech_sub[i][j].s_sub[0],x);
						//////cout<<"newnnewkkjbfdn"<<endl;
					//cout<<"SEC"<<x<<" sub "<<subject<<endl;
					//cout<<mtech_sub[i][j].s_sub[0]<<endl;
						mtech_sub[i][j].linked_or_not=-5;
						//cout<<i<<" "<<j<<" "<<mtech_sub[x][subject].linked_or_not<<endl;

						mtech_sub[x][subject].linked_sec[mtech_sub[x][subject].linked_or_not]=i;
						mtech_sub[x][subject].linked_or_not++;












	            	//inp_file >>mtech_sub[i][j].linked_sec[0];
				}

	        }

	    }
	    ////cout<<mtech_sub[25][1].s_sub[0];

		/*for(int i = 0 ; i< 38 ; i++){
			for(int j = 0 ; j < 7 ; j++){
				/*if(mtech_sub[i][j].linked_sec>0)
					{
						cout<<"linked i j :"<<i<<" "<<j<<endl;
						for (int k=0;k<mtech_sub[i][j].linked_or_not;k++)
						{
							cout<<"sec number: "<<mtech_sub[i][j].linked_sec[k]
						}
					}
				for(int k = 0 ; k < 4 ; k++){
					cout <<"i: "<<i<<" "<< "j:"<<" "<<j<<" "<<mtech_sub[i][j].s_sub[k]<<" "<<mtech_sub[i][j].s_teacher[k]<<endl;
				}
			}
		}*/
		//slot_busy[858] = slot_busy[897] = 1;
		algo_mtech(0);
		print_timetable_mtech();
		for(int j = 0 ; j < 38 ; j++){
			printf("%d   :  ",j);
			for(int i=0 ; i < 8 ; i++){
				printf(" %d ",mtech_hr_left[j][i]);
			}
			printf("\n");
		}
	    return 0;
	}
