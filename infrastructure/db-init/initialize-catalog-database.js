db = db.getSiblingDB("catalog_database");

db.createUser({
  user: "superuser",
  pwd: "superuser",
  roles: [
    {
      role: "dbOwner",
      db: "catalog_database",
    },
  ],
});

db.createUser({
  user: "catalog",
  pwd: "catalog",
  roles: [
    {
      role: "readWrite",
      db: "catalog_database",
    },
  ],
});

const objectsToBeInserted = [
  {
    _id: 1,
    genre: "Science Fiction",
    movies: [
      {
        _id: 9,
        name: "Doctor Strange",
        genre: "Science Fiction",
        urlStream: "http://www.movies.com/doctorstrange",
      },
    ],
    series: [],
  },
  {
    _id: 2,
    genre: "Action",
    movies: [
      {
        _id: 6,
        name: "Pacific Rim",
        genre: "Action",
        urlStream: "https://www.netflix.com/title/70267241",
      },
      {
        _id: 7,
        name: "The Old Guard",
        genre: "Action",
        urlStream: "https://www.netflix.com/title/81038963",
      },
    ],
    series: [
      {
        _id: 1,
        name: "Rapid",
        genre: "Action",
        seasons: [
          {
            _id: 1,
            seasonNumber: 1,
            chapters: [
              {
                _id: 1,
                name: "The Rapid",
                number: 1,
                urlStream: "https://www.netflix.com/rapid/81038598",
              },
            ],
          },
        ],
      },
      {
        _id: 5,
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
    ],
  },
];

db.catalogWS.insertMany(objectsToBeInserted, function (err, res) {
  if (err) throw err;
  console.log("Number of documents inserted: " + res.insertedCount);
});
