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

    { $lookup:
        {
           from: "quests",
           localField: "questStatus",
           foreignField: "_id",
           as: "questStatus"
        }
    },
    {
        $unwind: {
            path: "$questStatus",
            preserveNullAndEmptyArrays: true
        }
    },
    { $lookup:
        {
           from: "skills",
           localField: "skillSet",
           foreignField: "_id",
           as: "skillSet"
        }
    },
    {
        $unwind: {
            path: "$skillSet",
            preserveNullAndEmptyArrays: true
        }
    },
    { $lookup:
        {
           from: "stats",
           localField: "stats",
           foreignField: "_id",
           as: "stats"
        }
    },
    {
        $unwind: {
            path: "$stats",
            preserveNullAndEmptyArrays: true
        }
    },
    { $match :
            {
                _id: "ObjectId(\"536f710fc55b2acc61000bd1\")",
            }
    }

]).pretty();


//Additional info:
// https://stackoverflow.com/questions/5681851/mongodb-combine-data-from-multiple-collections-into-one-how
