INSERT INTO books (title, summary, isbn, author_id, available, created_at) VALUES
-- Jane Austen (id: 1)
('Pride and Prejudice', 'A romantic novel following the emotional development of Elizabeth Bennet, who learns about the repercussions of hasty judgments and comes to appreciate the difference between superficial goodness and actual goodness.', '978-0-14-143951-8', 1, TRUE, CURRENT_TIMESTAMP),
('Sense and Sensibility', 'The story of the Dashwood sisters, Elinor and Marianne, as they cope with love, heartbreak, and social expectations in 19th-century England.', '978-0-14-143966-2', 1, TRUE, CURRENT_TIMESTAMP),
-- Mark Twain (id: 2)
('The Adventures of Tom Sawyer', 'A novel about a young boy growing up along the Mississippi River, filled with adventure, mischief, and the joys of childhood.', '978-0-14-243723-0', 2, TRUE, CURRENT_TIMESTAMP),
('Adventures of Huckleberry Finn', 'The story of Huck Finn and his friend Jim, a runaway slave, as they travel down the Mississippi River on a raft seeking freedom.', '978-0-14-243721-6', 2, TRUE, CURRENT_TIMESTAMP),
-- Leo Tolstoy (id: 3)
('War and Peace', 'An epic novel that chronicles the French invasion of Russia and its impact on Tsarist society, following five aristocratic families through war, love, and transformation.', '978-0-14-303943-3', 3, TRUE, CURRENT_TIMESTAMP),
('Anna Karenina', 'A tragic love story set in Russian high society, exploring themes of infidelity, jealousy, and the search for meaning in life.', '978-0-14-303500-8', 3, TRUE, CURRENT_TIMESTAMP),
-- Virginia Woolf (id: 4)
('Mrs Dalloway', 'A novel that details a day in the life of Clarissa Dalloway in post-World War I England, exploring themes of mental illness, time, and the nature of identity.', '978-0-15-662870-9', 4, TRUE, CURRENT_TIMESTAMP),
('To the Lighthouse', 'A modernist novel centered on the Ramsay family and their visits to the Isle of Skye, exploring themes of loss, the passage of time, and the nature of art.', '978-0-15-690739-3', 4, TRUE, CURRENT_TIMESTAMP),
-- Gabriel García Márquez (id: 5)
('One Hundred Years of Solitude', 'A landmark of magical realism that tells the multi-generational story of the Buendía family in the fictional town of Macondo.', '978-0-06-088328-7', 5, TRUE, CURRENT_TIMESTAMP),
('Love in the Time of Cholera', 'A story of unrequited love that spans over fifty years, set in a Caribbean city in the late 19th and early 20th centuries.', '978-0-14-303990-7', 5, TRUE, CURRENT_TIMESTAMP),
-- Haruki Murakami (id: 6)
('Norwegian Wood', 'A nostalgic story of loss and sexuality set in Tokyo during the late 1960s, following student Toru Watanabe as he looks back on his days as a college student.', '978-0-37-570020-1', 6, TRUE, CURRENT_TIMESTAMP),
('Kafka on the Shore', 'A surrealistic novel that follows two parallel storylines: a teenage boy who runs away from home and an old man who has the ability to talk to cats.', '978-1-40-003466-0', 6, TRUE, CURRENT_TIMESTAMP),
-- Chinua Achebe (id: 7)
('Things Fall Apart', 'A novel that portrays the life of Okonkwo, a leader in the Igbo community of Nigeria, and the disruption of traditional Igbo culture by the arrival of European missionaries.', '978-0-38-547454-2', 7, TRUE, CURRENT_TIMESTAMP),
-- Isabel Allende (id: 8)
('The House of the Spirits', 'A multigenerational saga of the Trueba family in Chile, blending magical realism with a political history of the country throughout the 20th century.', '978-1-50-119397-4', 8, TRUE, CURRENT_TIMESTAMP),
('Eva Luna', 'The story of Eva Luna, an orphan girl who uses her gift for storytelling to navigate her way through life in an unnamed Latin American country.', '978-0-55-356836-0', 8, TRUE, CURRENT_TIMESTAMP),
-- Fyodor Dostoevsky (id: 9)
('Crime and Punishment', 'A psychological novel following the mental anguish and moral dilemmas of Raskolnikov, an impoverished ex-student who murders a pawnbroker to test his theory that some people are above the law.', '978-0-14-305814-4', 9, TRUE, CURRENT_TIMESTAMP),
('The Brothers Karamazov', 'A passionate philosophical novel set in 19th-century Russia, exploring themes of faith, doubt, morality, and free will through the story of the Karamazov family.', '978-0-37-475813-8', 9, TRUE, CURRENT_TIMESTAMP),
-- Toni Morrison (id: 10)
('Beloved', 'A novel about a Black family in the period after the American Civil War, haunted by the traumatic legacy of slavery through the ghost of a dead child.', '978-1-40-003341-0', 10, TRUE, CURRENT_TIMESTAMP),
('The Bluest Eye', 'A novel about a young Black girl in post-Depression America who prays for blue eyes in the belief that this will make her beautiful and accepted.', '978-0-39-332544-9', 10, TRUE, CURRENT_TIMESTAMP),
-- J.K. Rowling (id: 11)
('Harry Potter and the Philosopher\'s Stone', 'The first novel in the Harry Potter series, following a young boy who discovers on his eleventh birthday that he is a wizard and is accepted into Hogwarts School of Witchcraft and Wizardry.', '978-0-74-750820-6', 11, TRUE, CURRENT_TIMESTAMP),
('Harry Potter and the Chamber of Secrets', 'The second installment in the Harry Potter series, in which Harry returns to Hogwarts and investigates a mysterious attack on students.', '978-0-43-935806-9', 11, TRUE, CURRENT_TIMESTAMP),
-- George Orwell (id: 12)
('1984', 'A dystopian novel set in a totalitarian society ruled by the Party and its leader Big Brother, following Winston Smith as he rebels against the oppressive regime.', '978-0-45-152493-5', 12, TRUE, CURRENT_TIMESTAMP),
('Animal Farm', 'An allegorical novella in which farm animals overthrow their human farmer, only to find that their leaders become as tyrannical as the humans they replaced.', '978-0-45-228424-1', 12, TRUE, CURRENT_TIMESTAMP),
-- Franz Kafka (id: 13)
('The Metamorphosis', 'A novella in which traveling salesman Gregor Samsa wakes one morning to find himself transformed into a giant insect, exploring themes of alienation and identity.', '978-0-14-181453-5', 13, TRUE, CURRENT_TIMESTAMP),
('The Trial', 'A novel about Josef K., a man who is arrested and prosecuted by an inaccessible authority for an unspecified crime, exploring themes of bureaucracy and existential anxiety.', '978-0-80-521221-9', 13, TRUE, CURRENT_TIMESTAMP),
-- Emily Dickinson (id: 14)
('The Complete Poems of Emily Dickinson', 'A comprehensive collection of over 1,700 poems by one of America\'s most beloved poets, exploring themes of death, immortality, nature, and love.', '978-0-31-618414-4', 14, TRUE, CURRENT_TIMESTAMP),
-- Homer (id: 15)
('The Iliad', 'An ancient Greek epic poem set during the Trojan War, focusing on the conflict between King Agamemnon and the warrior Achilles.', '978-0-14-027536-0', 15, TRUE, CURRENT_TIMESTAMP),
('The Odyssey', 'An ancient Greek epic poem following the hero Odysseus on his ten-year journey home after the fall of Troy.', '978-0-14-026886-7', 15, TRUE, CURRENT_TIMESTAMP);