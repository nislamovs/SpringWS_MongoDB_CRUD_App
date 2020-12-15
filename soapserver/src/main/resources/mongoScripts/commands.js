db.getCollection('persons').aggregate([
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
