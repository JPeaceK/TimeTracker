
import 'package:timetracker_app/page_activities.dart';
import 'package:timetracker_app/requests.dart';
import 'package:flutter/material.dart';
import 'dart:async';

import 'package:timetracker_app/tree.dart';

class PageInfo extends StatefulWidget {
  Activity activity;
  PageInfo(this.activity);
  @override
  _PageInfoState createState() => _PageInfoState();
}

class _PageInfoState extends State<PageInfo> {
  late Activity activity;
  late Future<Tree> futureTree;

  @override
void initState(){
    super.initState();
    activity = widget.activity;
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('${activity.name}' + ' information'),
        actions: <Widget>[
          IconButton(onPressed: (){
            while (Navigator.of(context).canPop()){
              Navigator.of(context).pop();
            }
            PageActivities(0);
          }, icon: const Icon(Icons.home))
        ],
      ),
      body: Container(
        margin: EdgeInsets.fromLTRB(15,15,15,15),
        child: Column(
          mainAxisAlignment: MainAxisAlignment.start,
          children: [
            Card(
              child: Text('Started at ${activity.initialDate}'),
            ),
            Card(
              child: Text('Last modified at ${activity.finalDate}'),
            ),
            Card(
              child: Text('Duration ${activity.duration}'),
            ),
            /*Card(
              child: Text('Tags  ${activity.tags}'),
            )
            */
          ],
        ),
      ),
    );
  }
}