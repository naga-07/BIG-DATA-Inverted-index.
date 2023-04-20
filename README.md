# BIG-DATA-Inverted-index.

MapReduce is a programming model for processing and analyzing large datasets in a distributed computing environment. Inverted index is a common data structure used in search engines to quickly locate documents containing a given word. A MapReduce program to build an inverted index from a collection of documents can be implemented as follows:

Map Function:

Input: a document D
For each word w in D, emit (w, D) as the key-value pair
Reduce Function:

Input: a word w and a list of documents [D1, D2, ..., Dn] that contain it
Build an inverted index entry for w with the list of documents [D1, D2, ..., Dn]
The Map function reads each document and emits a key-value pair for each word in the document. The key is the word and the value is the document that contains the word. The Reduce function receives a key-value pair with a word and a list of documents that contain it. It builds an inverted index entry for the word with the list of documents that contain it.

The MapReduce framework automatically groups together all the key-value pairs with the same key and passes them to the Reduce function. This allows the Reduce function to build an inverted index entry for each word efficiently.

Overall, this MapReduce program builds an inverted index from a given collection of documents, where the keys are words and the values are the documents that contain them.
