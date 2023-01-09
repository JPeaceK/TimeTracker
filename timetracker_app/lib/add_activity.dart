import 'package:timetracker_app/page_activities.dart';
import 'package:timetracker_app/tree.dart';
import 'package:timetracker_app/requests.dart';

import 'package:flutter/material.dart';
import 'dart:async';

enum ActivityType {project, task}

class AddActivity extends StatefulWidget {
  int id;
  AddActivity(this.id);

  @override
  _AddActivityState createState() => _AddActivityState(id);
}

class _AddActivityState extends State<AddActivity> {
  int id;
  late String name = "";
  late String tags = " ";
  late String option = "Project";
  _AddActivityState(this.id);
  ActivityType? _activityType = ActivityType.project;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: const Text("Create a new activity"),
          actions: <Widget>[
            IconButton(onPressed: () {
              while (Navigator.of(context).canPop()) {
                Navigator.of(context).pop();
              }
              PageActivities(0);
            }, icon: const Icon(Icons.home),
            )
          ],
        ),
        body: Container(
          margin: EdgeInsets.fromLTRB(15,15,15,15),
          child: Column(
              mainAxisAlignment: MainAxisAlignment.start,
              children: [
                Card(
                  child:
                    ListTile(
                      title: Text('Project'),
                      leading: Radio<ActivityType>(
                        value: ActivityType.project,
                        groupValue: _activityType,
                        onChanged: (ActivityType? value) {
                          setState(() {
                            _activityType = value;
                            option = 'Project';
                          });
                        },
                      ),
                    ),
                ),
                Card(
                  child:
                    ListTile(
                      title: const Text('Task'),
                      leading: Radio<ActivityType>(
                        value: ActivityType.task,
                        groupValue: _activityType,
                        onChanged: (ActivityType? value) {
                          setState(() {
                            _activityType = value;
                            option = 'Task';
                          });
                        },
                      ),
                    )
                ),
                Row(
                  children: <Widget>[
                    Container(
                      margin: EdgeInsets.fromLTRB(0, 100, 0, 0),
                    ),
                    Expanded(child:
                      TextField(
                          decoration: const InputDecoration(
                              hintText: 'Activity name'
                          ),
                          onChanged: (String value) async {
                            name = value;
                          }
                      ),
                    )],
                ),
                Row(
                  children: <Widget>[
                    //const Padding(padding: EdgeInsets.all(5)),
                    Container(
                      margin: EdgeInsets.fromLTRB(0, 0, 0, 0),
                    ),
                  Expanded(child:
                      TextField(
                        decoration: const InputDecoration(
                            hintText: 'tag1 tag2 tag3...'
                        ),
                        onChanged: (String value) async {
                          tags = value;
                        },
                      )
                    )],
                ),
                Row(
                  children: [
                    Container(
                      margin: EdgeInsets.fromLTRB(90, 150, 0, 0),
                    ),
                    //const Padding(padding: EdgeInsets.symetric(horizontal: 8, vertical: 16)),
                    ClipRRect(
                        borderRadius: BorderRadius.circular(4),
                        child: Stack(
                          children: <Widget>[
                            Positioned.fill(
                              child: Container(
                                decoration: const BoxDecoration(
                                  gradient: LinearGradient(
                                    colors: <Color>[
                                      Color(0xFFFFEB3B),
                                      Color(0xFFFFEB3B),
                                      Color(0xFFFFEB3B),
                                    ],
                                  ),
                                ),
                              ),
                            ),
                            TextButton(
                              onPressed: () {
                                if (name != "") {
                                  //List<String> finalTags = tags.split(",");
                                  addActivity(id, name, tags, option);
                                }
                                Navigator.of(context).pop();
                              },
                              style: TextButton.styleFrom(
                                  foregroundColor: Colors.black,
                                  padding: EdgeInsets.all(16.0),
                                  textStyle: TextStyle(fontSize: 20)
                              ),
                              child: const Text("CREATE"),
                            )
                          ],
                        )
                    )
                  ],
                ),
              ]),
        )

    );
  }
}