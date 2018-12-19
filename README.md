# Rex-java

Rex:

Grammars resolve symbols; a non-terminal grammar needs other grammars to resolve the current input; a terminal grammar
is 'self-sufficient';

## Types

* Lst%T is the basic read-only list implementation
  - get(index as int) as (T|Nothing): returns the index'th element or nothing if item is invalid
  - count as (int|Nothing): gets the number of itens; returns nothing if the count of items is unknown.
  - each(fn as Predicate%(T, int)): enumerates all the itens; interrupts the enumeration if fn returns true
  - find(fn as Predicate%(T,int)) as (T|Nothing): specialization of each that returns the current item
      when fn returns true 
  - slice(start, end as int) as Span%T: returns a sublist of the specified range
  - slice(start as int, size as Size): returns a sublist of the specified range (Size is an alias to int)
  
* Span%T as Lst%T is an specialization of a Lst that represents a section of the list
  - start as int: indicates the first index of the span
  - end as (int|Nothing): index of next item after the last; if Nothing, the span has no defined end
  - source as (Lst%T|Nothing): indicates the data source of the span; may be nothing, in which case it can be assigned.
  
* Sequence%T as Lst%T tracks the poistion of an input sequence:
  - position as integer : gets /sets the current position
  - current as (T|Nothing) : gets the current item
  - finished as boolean: returns true if the current poisition is beyond the end of input
  - move-next as (T|Nothing): increments the position and returns the last item
  - next() as (T|Nothing): alias to <move-next>
  - has-next as boolean: inverse of <finished> 

* Link%T as Lst%T is a singly-linked list of values; the current item is the last value assigned;
  notice that the each (and find) operation enumerate the items in reverse order (from the last one
  added to the first)
  
  - value as (T|Nothing): gets/sets value at the top of the stack
  - get() as (T|Nothing): alias to current
  - get(index as int) as (T|Nothing): returns the nth item from the linked list or nothing if the index is
      beyond the list range; a negative index counts backward from the top;
  - push(value as T): adds a new item to the end of the stack
  - pop as (T|Nothing): removes and returns the last item of the stack
  - pop(index as integer) as (T|Nothing): removes and returns the nth item from the stack; subsequent items
      are lost; a negative index can be specified;
  - peek as (T|Nothing): alias to get
  - peek(index as int) as (T|Nothing): alias to get(int)
  

* Capture%T as Span%T represents a 'capture' of the input
  - id as String: the id of the capture
 
* Parse%T as Capture%T represents a parse result
  - rule as (Rex.Rule|Nothing): identifies the current rule
  - vars as Link%(Capture%T): list of captures performed while parsing the rule
  - children as Link%(Parse%T): list of sub-rules that matched the input
  
Context:
  - sequence as Sequence%T: the current input being parsed
  - result as Link%(Parse%T): the result of the parsing; at any given moment, represents the
    current rule being applied
  - root as Link%(Parse%T): the root of the parsing result
  - failure as Link%(Parse%T): the longest failed parse
