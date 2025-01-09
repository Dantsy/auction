INSERT INTO auction_questions(id, question)
VALUES (1, 'What would you like to win at the auction?'),
       (2, 'What is your budget for purchasing?'),
       (3, 'What type of items are you interested in?');

INSERT INTO auction_answers_choices(question_id, choice)
VALUES (1, 'Antiques'),
       (1, 'Electronics'),
       (1, 'Jewelry'),
       (1, 'Clothing'),
       (2, 'Up to 1000 rubles'),
       (2, 'From 1000 to 5000 rubles'),
       (2, 'From 5000 to 10000 rubles'),
       (2, 'I have the money for it'),
       (3, 'Modern gadgets'),
       (3, 'Jewelry'),
       (3, 'Fashion'),
       (3, 'I love old things');