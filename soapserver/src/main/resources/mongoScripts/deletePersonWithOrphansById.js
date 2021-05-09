db.getCollection('persons').aggregate([

    {
        $addFields: {
            "questStatus": { $arrayElemAt: [{ $objectToArray: "$questStatus" }, 1] },
            "stats": { $arrayElemAt: [{ $objectToArray: "$stats" }, 1] },
            "skillSet": { $arrayElemAt: [{ $objectToArray: "$skillSet" }, 1] },
        }
    },
    {
        $addFields: {
            "questStatus": "$questStatus.v",
            "stats": "$stats.v",
            "skillSet": "$skillSet.v"
        }
    },
    {
        $project: { _id: 1, questStatus: 1, stats: 1, skillSet: 1 }
    },
    {
        $match : { _id: "ObjectId(\"536f710fc55b2acc61000bd2\")" }
    }

])
.forEach(function(doc) {
    db.getCollection("quests").remove({ "_id": doc.questStatus });
    db.getCollection("skills").remove({ "_id": doc.skillSet });
    db.getCollection("stats").remove({ "_id": doc.stats });
    db.getCollection("persons").remove({ "_id": doc._id });
});