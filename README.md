# Rex-java

Rex works with individual Matchers as well as Grammars. A Grammar is a collection of named matchers. A non-terminal grammar needs other grammars to resolve the current input; a terminal grammar is self-sufficient.

## Auxiliary Interfaces

* Lst%T is the basic read-only list implementation
  - get(index as int) as (T|Nothing): returns the index'th element or nothing if item is invalid
  - count as (int|Nothing): gets the number of itens; returns nothing if the count of items is unknown.
  - each(fn as Predicate%(T, int)): enumerates all the itens; interrupts the enumeration if fn returns true
  - find(fn as Predicate%(T,int)) as (T|Nothing): specialization of each that returns the current item
      when fn returns true 
  - span(start, end as int) as Spn%T: returns a span for the specified range
  - slice(start as int, size as int) as Spn%T: returns a span for the specified range;
  - span(range as Range) as Spn%T : returns a span for the specified range
  

* Range : auxiliary interface to specify a range with a star and a (posibly absent) end
	- start as int;
	- end as (int|Nothing)
	
* Spn%T as [Lst%T, Range] is an specialization of a Lst that represents a section of the list
  - start as int: indicates the first index of the span
  - end as (int|Nothing): index of next item after the last; if Nothing, the span has no defined end
  - source as (Lst%T|Nothing): indicates the data source of the span; may be nothing, in which case it can be assigned.
  
* Seq%T as Lst%T tracks the poistion of an input sequence:
  - position as int : gets /sets the current position
  - current as (T|Nothing) : gets the current item
  - finished as boolean: returns true if the current poisition is beyond the end of input
  - move-next as boolean: increments the position and returns the last item
  - next() as (T|Nothing): alias to <move-next>
  - has-next as boolean: inverse of <finished> 

* Stk%T as Lst%T is a singly-linked list of values; the current item is the last value assigned;
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
  
## Parsing related classes

* Capture as Range represents a 'capture' of the input
  - id as String: the id of the capture
 
* ParseResult as Capture represents a parse result
  - rule as (Rex.Rule|Nothing): identifies the current rule
  - vars as Stk%Capture: list of captures performed while parsing the rule
  - children as Stk%ParseResult: list of sub-rules that matched the input
  
* Context is what is passed down to Matchers
	- position as int: gets/sets the current position being parsed
	- finished: returns true if at the end of input
	- move-next as boolean: moves to the next position, if possible
  - result as ParseResult: the result of the parsing; at any given moment, represents the
    current rule being applied
  - root as ParseResult: the root of the parsing result
  - trace as List%ParseResult: the trace of the current parse;
  - matches(position as int, value as object) as boolean: returns true/false
      if the element at the specified position matches the supplied value;
  - matches(value as object) as boolean: returns true/false if the current item
  		matches the supplied value
  		
