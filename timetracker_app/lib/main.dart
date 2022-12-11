import 'package:flutter/material.dart';
import 'package:timetracker_app/page_activities.dart';
//import 'package:timetracker_app/page_intervals.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'TimeTracker',
      theme: ThemeData(
        primarySwatch: Colors.blue,
        textTheme: const TextTheme(
            subtitle1: TextStyle(fontSize:20.0),
            bodyText2:TextStyle(fontSize:20.0)),
      ),
        home: PageActivities(0) //PageIntervals()
    );
  }
}