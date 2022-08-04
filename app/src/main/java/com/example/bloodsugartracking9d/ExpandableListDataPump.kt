package com.example.bloodsugartracking9d

object ExpandableListDataPump {

    var expandableListDetail :  HashMap<String, ArrayList<String>>  = HashMap<String, ArrayList<String>>();


       fun getdata() : HashMap<String, ArrayList<String>>{

           val cricket: ArrayList<String> = ArrayList()
           cricket.add("India")
           cricket.add("Pakistan")
           cricket.add("Australia")
           cricket.add("England")
           cricket.add("South Africa")
           val football: ArrayList<String> = ArrayList()
           football.add("Brazil")
           football.add("Spain")
           football.add("Germany")
           football.add("Netherlands")
           football.add("Italy")
           val basketball: ArrayList<String> = ArrayList()
           basketball.add("United States")
           basketball.add("Spain")
           basketball.add("Argentina")
           basketball.add("France")
           basketball.add("Russia")
           expandableListDetail["CRICKET TEAMS"] = cricket
           expandableListDetail["FOOTBALL TEAMS"] = football
           expandableListDetail["BASKETBALL TEAMS"] = basketball

           return expandableListDetail
    }

}