// ToDoList.cpp : This file contains the 'main' function. Program execution begins and ends there.
//

#include <iostream>
#include <list>
#include <string>

using namespace std;

int main()
{
    string element;
    list<string> tasks;
    if (tasks.size() == 0) {
        cout << "Your to-do-list is empty. Type a task to fill your list. ";
        getline(cin, element);
        tasks.push_back(element);
    }
    while (tasks.size() > 0)
    {
        cout << "Add more tasks. If you want to see your full list of tasks type print. ";
        if (element != "print") {
            getline(cin, element);
            tasks.push_back(element);
        }
        if (element == "print"){
            tasks.remove(element);
            int c = 1;
            cout << "Here are your current tasks:" << "\n";
        for (string s : tasks) {
            cout << c << "." << s << "\n";
            c++;
        }
        break;
    }
    }
      
