import 'package:timetracker_app/add_activity.dart';
import 'package:timetracker_app/info_page.dart';
import 'package:timetracker_app/page_intervals.dart';
import 'package:timetracker_app/tree.dart' hide getTree;
// the old getTree()
import 'package:timetracker_app/requests.dart';
import 'package:flutter/material.dart';
import 'dart:async';

class PageActivities extends StatefulWidget {
  int id;
  PageActivities(this.id);
  @override
  _PageActivitiesState createState() => _PageActivitiesState();
}

class _PageActivitiesState extends State<PageActivities> {
  //late Tree tree;

  late int id;
  late Future<Tree> futureTree;
  late Timer _timer;
  static const int periodeRefresh = 1;

  @override
  void initState() {
    super.initState();
    id = widget.id; // of PageActivities
    futureTree = getTree(id);
    _activateTimer();
  }

  void _refresh() async {
    futureTree = getTree(id); // to be used in build()
    setState(() {});
  }

  Widget _buildRow(Activity activity, int index) {
    String strDuration = Duration(seconds: activity.duration).toString().split('.').first;
    // split by '.' and taking first element of resulting list removes the microseconds part
    if (activity is Project) {
      return ListTile(
        leading: Icon(Icons.create_new_folder_outlined),
        title: Text('${activity.name}'),
        trailing: Row(
          mainAxisSize: MainAxisSize.min,
          children: [
          IconButton(
            icon: Icon(Icons.info_outline_rounded),
            onPressed: () {
              Navigator.of(context).push(MaterialPageRoute<void>(builder: (context) => PageInfo(activity)));
            },
          ), Text('$strDuration'),
          ],
        ),
        onTap: () => _navigateDownActivities(activity.id),
      );
    } else { //if(activity is Task) {
      Task task = activity as Task;
      // at the moment is the same, maybe changes in the future
      Widget trailing;
      trailing = Text('$strDuration',
        style: TextStyle(color: task.active ? Colors.lightGreen : Colors.black));

      return ListTile(
        //leading: Icon(Icons.tiktok),
        title: Text('${activity.name}',
            style: TextStyle(color: task.active ? Colors.lightGreen : Colors.black)),
        trailing: Row(
          mainAxisSize: MainAxisSize.min,
          children: [
            IconButton(
              icon: Icon(Icons.info_outline_rounded),
              onPressed: () {
                Navigator.of(context).push(MaterialPageRoute<void>(builder: (context) => PageInfo(activity)));
              },
            ), trailing,
          ],
        ),
        //tileColor: task.active ? Colors.yellow : Colors.transparent,
        onTap: () => _navigateDownIntervals(activity.id),
        leading: IconButton(
          icon: Icon((activity).active ? Icons.pause : Icons.play_arrow),
          onPressed: () {
            if ((activity).active) {
              stop(activity.id);
              _refresh(); // to show immediately that task has started
            } else {
              start(activity.id);
              _refresh();
            }
          }
        ),
        onLongPress: () {
          if ((activity as Task).active) {
            stop(activity.id);
            _refresh(); // to show immediately that task has started
          } else {
            start(activity.id);
            _refresh(); // to show immediately that task has started
          }
        },
      );
    }
  }

  // future with listview
  // https://medium.com/nonstopio/flutter-future-builder-with-list-view-builder-d7212314e8c9
  @override
  Widget build(BuildContext context) {
    return FutureBuilder<Tree>(
      future: futureTree,
      // this makes the tree of children, when available, go into snapshot.data
      builder: (context, snapshot) {
        // anonymous function
        if (snapshot.hasData) {
          return Scaffold(
            appBar: AppBar(
              title: Text("TimeTracker"),
              actions: <Widget>[

                IconButton(icon: Icon(Icons.info_outline_rounded),
                  onPressed: (){
                    Navigator.of(context).push(MaterialPageRoute<void>(builder: (context) => PageInfo(snapshot.data!.root.children[id])));
                  },
                ),

                IconButton(icon: Icon(Icons.home),
                    onPressed: () {
                      while(Navigator.of(context).canPop()) {
                        print("pop");
                        Navigator.of(context).pop();
                      }
                      /* this works also:
                        Navigator.popUntil(context, ModalRoute.withName('/'));
                      */
                      PageActivities(0);
                    }),
                //TODO other actions
              ],
            ),
            body: ListView.separated(
              // it's like ListView.builder() but better because it includes a separator between items
              padding: const EdgeInsets.all(16.0),
              itemCount: snapshot.data!.root.children.length,
              itemBuilder: (BuildContext context, int index) =>
                  _buildRow(snapshot.data!.root.children[index], index),
              separatorBuilder: (BuildContext context, int index) =>
              const Divider(),
            ),
            floatingActionButton: FloatingActionButton(
              onPressed: (){
                _navigateCreateNewActivity(snapshot.data!.root.id);
              },
              backgroundColor: Colors.yellow,
              child: const Icon(Icons.add_rounded),
            ),
          );
        } else if (snapshot.hasError) {
          return Text("${snapshot.error}");
        }
        // By default, show a progress indicator
        return Container(
            height: MediaQuery.of(context).size.height,
            color: Colors.white,
            child: Center(
              child: CircularProgressIndicator(),
            ));
      },
    );
  }

void _navigateDownActivities(int childId) {
  // we can not do just _refresh() because then the up arrow doesnt appear in the appbar
  Navigator.of(context)
      .push(MaterialPageRoute<void>(
    builder: (context) => PageActivities(childId),
  )).then( (var value) {
    _activateTimer();
    _refresh();
  });
}

void _navigateDownIntervals(int childId) {
  Navigator.of(context)
      .push(MaterialPageRoute<void>(
    builder: (context) => PageIntervals(childId),
  )).then( (var value) {
    _activateTimer();
    _refresh();
  });
  //https://stackoverflow.com/questions/49830553/how-to-go-back-and-refresh-the-previous-page-in-flutter?noredirect=1&lq=1
}

  void _navigateCreateNewActivity(int id){
  _timer.cancel();
  Navigator.of(context).push(MaterialPageRoute<void>(
      builder:(context)=>AddActivity(id))).then((var value){
        _activateTimer();
        _refresh();
  });
}


  void _activateTimer() {
    _timer = Timer.periodic(Duration(seconds: periodeRefresh), (Timer t) {
      futureTree = getTree(id);
      setState(() {});
    });
  }

  @override
  void dispose() {
    // "The framework calls this method when this State object will never build again"
    // therefore when going up
    _timer.cancel();
    super.dispose();
  }
}