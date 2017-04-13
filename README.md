# wordFrequency
WordFrequency provides many functionalities, such as generating a cloud map of words or printing the most frequent words.

# Example usage
```java
WordFrequencyFactory wordFrequencyFactory = 
  new WordFrequencyFactory();

try {
  WordFrequency wordFrequency = wordFrequencyFactory.openFile( "examples/example.txt" );
  System.out.println( "Generating an example of cloud map." );
  wordFrequency.generateWordCloud("examples/cloud.html", 400);
} catch (IOException e) {
  e.printStackTrace();
} catch (Exception e) {
  e.printStackTrace();
}
```

# Result
### cloud.html
![Cloud of text randomly distributed inside a circle](http://i.imgur.com/hrT0Nfn.png)
