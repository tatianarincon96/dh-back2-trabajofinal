db = db.getSiblingDB("serie_database");

db.createUser({
  user: "superuser",
  pwd: "superuser",
  roles: [
    {
      role: "dbOwner",
      db: "serie_database",
    },
  ],
});

db.createUser({
  user: "series",
  pwd: "series",
  roles: [
    {
      role: "readWrite",
      db: "serie_database",
    },
  ],
});

const objectsToBeInserted = [
  {
    _id: 1,
    name: "Breaking Bad",
    genre: "Action",
    seasons: [
      {
        _id: 1,
        seasonNumber: 1,
        chapters: [
          {
            _id: 1,
            name: "Pilot",
            number: 1,
            urlStream: "https://www.netflix.com/breakingbad/81038598",
          },
        ],
      },
    ],
  },
  {
    _id: 2,
    name: "Friends",
    genre: "Comedy",
    seasons: [
      {
        _id: 1,
        seasonNumber: 1,
        chapters: [
          {
            _id: 1,
            name: "The Pilot",
            number: 1,
            urlStream: "https://www.netflix.com/friends/81038598",
          },
        ],
      },
    ],
  },
  {
    _id: 3,
    name: "The Terror",
    genre: "Terror",
    seasons: [
      {
        _id: 1,
        seasonNumber: 1,
        chapters: [
          {
            _id: 1,
            name: "Go For Broke",
            number: 1,
            urlStream: "https://www.netflix.com/terror/81038598",
          },
        ],
      },
    ]
  },
];

db.Series.insertMany(objectsToBeInserted, function (err, res) {
  if (err) throw err;
  console.log("Number of documents inserted: " + res.insertedCount);
});
