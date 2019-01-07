# Rex-java

## Matchers

Rex works by composing complex matchers out of simpler ones. A Matcher implements the method `match(ctx as Context) as boolean`,  which indicates if it matches the provided subject (encompassed into a Context instance). The provided matchers are:

* `lit(what as Object)`: matches a single 'literal' (calls Context.match). If the literal matches the element at the current Context position, it advances the Context position;

* `any`: returns true if the Context is not at the end of input;

* `eof`: returns true if the Context is at the end of input;

* `bof`: returns true if the current position is 0;

* `eps`: always returns true (a zero-length matcher, i.e. doesn't modify the input position);

* `is(what as Matcher)`: zero-length Matcher that returns true if the supplied Matcher matches the current input;

* `isnot(what as Matcher)`: zero-length Matcher that returns true if the supplied Matcher does't match the input;

*`and(itens as List%Matcher)`: returns true if all the matchers in the list match the subject in sequence. If any of the matchers fail, returns false with the input at failing position;

*`or(itens as List%Matcher)`: returns true if any of the matchers in the list match the subject; each matcher is tried in sequence and the input position is reset at each alternative tried;

* `rep(min, max as (int|nothing), what as matcher)`: tries repeatedly to match the subject with the provided matcher until the matcher fails or a max number of matches (if provided) is reached; if min is provided, requires at least a min number of matches; there are the specializations `opt(what as matcher)` which matches 0 or 1 times, `star(what as matcher)` which matches 0 or any number of times and `plus(what as matcher)` which matches at least one time up to any number of times;

* `cap(id as string, what as matcher)`: if the supplied matcher matches the subject, saves the range matched in the context's result's vars with the specified id; the range can be retrieved by querying ctx.var(id); 

* `recap(id as string)`: rematches verbatim a the sequence of literals previously captured; 

* `oneof(itens as iterable)`: matches one of the itens supplied by iterable (calls Context.match);

* `seq(itens as iterable)`: matches all the itens supplied by iterable (calls Context.match);

## Grammars

Rex works with individual Matchers as well as Grammars. A Grammar is a collection of named matchers. A non-terminal grammar needs other grammars to resolve the current input; a terminal grammar is self-sufficient.
  
## Parsing-related classes

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
  - range(start as int, end as (int|nothing)) as iterable: returns an iterable
    span over the specified range

* ParseResult is a Capture implementation with additional properties; it represents a parse result
  - rule as (Rex.Rule|Nothing): identifies the current rule
  - vars as Stk%Capture: list of captures performed while parsing the rule
  - children as Stk%ParseResult: list of sub-rules that matched the input
  
* Capture as Range represents a 'capture' of the input
  - id as String: the id of the capture
 
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
  - get(index as int) as (T|Nothing): returns the nth item from the linked list or nothing if the index is beyond the list range; a negative index counts backward from the top;
  - push(value as T): adds a new item to the end of the stack
  - pop as (T|Nothing): removes and returns the last item of the stack
  - pop(index as integer) as (T|Nothing): removes and returns the nth item from the stack; subsequent items are lost; a negative index can be specified;
  - peek as (T|Nothing): alias to get
  - peek(index as int) as (T|Nothing): alias to get(int)
 