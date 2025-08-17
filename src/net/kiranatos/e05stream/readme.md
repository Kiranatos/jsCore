5.1. Removing Elements With Stream

Removing, or rather, filtering elements using Stream is quite straightforward, we just need to create an instance of Stream using our Collection, invoke filter with our Predicate and then collect the result with the help of Collectors:

Collection<String> filteredCollection = names
  .stream()
  .filter(e -> !e.startsWith("A"))
  .collect(Collectors.toList());

Streaming is less invasive than the previous approaches, it promotes isolation and enables the creation of multiple copies from the same source. However, we should keep in mind that it also increases the memory used by our application.



Collectors.partitioningBy

Combining both Stream.filter and Collectors is quite handy, although we may run into scenarios where we need both matching and non-matching elements. In such cases we can take advantage of Collectors.partitioningBy:

Map<Boolean, List<String>> classifiedElements = names
    .stream()
    .collect(Collectors.partitioningBy((String e) -> 
      !e.startsWith("A")));

String matching = String.join(",",
  classifiedElements.get(true));
String nonMatching = String.join(",",
  classifiedElements.get(false));

This method returns a Map that only contains two keys, true and false, each pointing to a list that contains the matching, and non-matching elements, respectively.