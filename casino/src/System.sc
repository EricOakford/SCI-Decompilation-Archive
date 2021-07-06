;;; Sierra Script 1.0 - (do not remove this comment)
(script# SYSTEM)
(include game.sh)
(use Main)
(use Intrface)

(public
	sign 0
	umod 1
	Min 2
	Max 3
	InRect 4
	OneOf 5
	WordAt 6
	Eval 7
)

(procedure (sign x)
	(return (if (< x 0) -1 else (> x 0)))
)

(procedure (umod n modN)
	(if
		(<
			(= n (- n (* modN (/ n modN))))
			0
		)
		(= n (+ n modN))
	)
	(return n)
)

(procedure (Min nums &tmp otherMin)
	(return
		(if
		(or (== argc 1) (< nums (= otherMin (Min &rest))))
			nums
		else
			otherMin
		)
	)
)

(procedure (Max nums &tmp otherMax)
	(return
		(if
		(or (== argc 1) (> nums (= otherMax (Max &rest))))
			nums
		else
			otherMax
		)
	)
)

(procedure (InRect lEdge tEdge rEdge bEdge ptxOrObj pty)
	(return
		(if
			(and
				(<= lEdge (if (< argc 6) (ptxOrObj x?) else ptxOrObj))
				(<= (if (< argc 6) (ptxOrObj x?) else ptxOrObj) rEdge)
			)
			(if
			(<= tEdge (if (< argc 6) (ptxOrObj y?) else pty))
				(<= (if (< argc 6) (ptxOrObj y?) else pty) bEdge)
			)
		else
			0
		)
	)
)

(procedure (OneOf what things &tmp i)
	(= i 0)
	(while (< i (- argc 1))
		(if (== what [things i])
			(return (if what else 1))
		)
		(++ i)
	)
	(return 0)
)

(procedure (WordAt ptr n)
	(Memory MReadWord (+ ptr (* 2 n)))
)

(procedure (Eval obj sel)
	(obj sel: &rest)
)

(class Object
	(properties
		name "Obj"
	)
	
	(method (new)
		(Clone self)
	)
	
	(method (init)
	)
	
	(method (doit)
		(return self)
	)
	
	(method (dispose)
		(DisposeClone self)
	)
	
	(method (showStr where)
		(StrCpy where name)
	)
	
	(method (showSelf &tmp [str 200])
		(Print (self showStr: @str))
	)
	
	(method (perform theCode)
		(theCode doit: self &rest)
	)
	
	(method (isKindOf what &tmp theSuper)
		(cond 
			(
				(and
					(== -propDict- (what -propDict-?))
					(== -classScript- (what -classScript-?))
				)
			)
			((= theSuper (self -super-?)) (if (IsObject theSuper) (theSuper isKindOf: what)))
		)
	)
	
	(method (isMemberOf what)
		(return
			(if
				(and
					(& (what -info-?) CLASS)
					(not (& -info- CLASS))
				)
				(== -propDict- (what -propDict-?))
			else
				FALSE
			)
		)
	)
	
	(method (respondsTo selector)
		(RespondsTo self selector)
	)
	
	(method (yourself)
		(return self)
	)
)

(class Code of Object
	(properties)
	
	(method (doit)
	)
)

(class Collection of Object
	(properties
		name "Collect"
		elements 0
		size 0
	)
	
	(method (doit)
		(self eachElementDo: #doit &rest)
	)
	
	(method (dispose)
		(if elements
			(self eachElementDo: #dispose)
			(DisposeList elements)
		)
		(= size (= elements 0))
		(super dispose:)
	)
	
	(method (showStr where)
		(Format where SYSTEM 0 name size)
	)
	
	(method (showSelf &tmp [str 40])
		(Print (self showStr: @str))
		(self eachElementDo: #showSelf)
	)
	
	(method (add item &tmp obj n node)
		(if (not elements) (= elements (NewList)))
		(= n 0)
		(while (< n argc)
			(if (not (self isDuplicate: [item n]))
				(AddToEnd
					elements
					(NewNode [item n] [item n])
				)
				(++ size)
			)
			(++ n)
		)
		(return self)
	)
	
	(method (delete item &tmp n)
		(= n 0)
		(while (< n argc)
			(if (DeleteKey elements [item n]) (-- size))
			(++ n)
		)
		(return self)
	)
	
	(method (eachElementDo action &tmp node nextNode obj)
		(= node (FirstNode elements))
		(while node
			(= nextNode (NextNode node))
			(if (not (IsObject (= obj (NodeValue node))))
				(return)
			)
			(obj action: &rest)
			(= node nextNode)
		)
	)
	
	(method (firstTrue action &tmp node nextNode obj)
		(= node (FirstNode elements))
		(while node
			(= nextNode (NextNode node))
			(= obj (NodeValue node))
			(if (obj action: &rest) (return obj))
			(= node nextNode)
		)
		(return NULL)
	)
	
	(method (allTrue action &tmp node nextNode obj)
		(= node (FirstNode elements))
		(while node
			(= nextNode (NextNode node))
			(= obj (NodeValue node))
			(if (not (obj action: &rest)) (return FALSE))
			(= node nextNode)
		)
		(return TRUE)
	)
	
	(method (contains anObject)
		(if (not (self isEmpty:))
			(FindKey elements anObject)
		)
	)
	
	(method (isEmpty)
		(if (== elements NULL) else (EmptyList elements))
	)
	
	(method (first)
		(FirstNode elements)
	)
	
	(method (next node)
		(NextNode node)
	)
	
	(method (release &tmp node nextNode)
		(= node (FirstNode elements))
		(while node
			(= nextNode (NextNode node))
			(self delete: (NodeValue node))
			(= node nextNode)
		)
	)
	
	(method (isDuplicate)
		(return 0)
	)
)

(class List of Collection
	(properties)
	
	(method (showStr where)
		(Format where SYSTEM 1 name size)
	)
	
	(method (at n &tmp node)
		(= node (FirstNode elements))
		(while (and n node)
			(-- n)
			(= node (NextNode node))
		)
		(return (if node (NodeValue node) else 0))
	)
	
	(method (last)
		(LastNode elements)
	)
	
	(method (prev node)
		(PrevNode node)
	)
	
	(method (addToFront obj &tmp n)
		(if (not elements) (= elements (NewList)))
		(= n (- argc 1))
		(while (<= 0 n)
			(if (not (self isDuplicate: [obj n]))
				(AddToFront
					elements
					(NewNode [obj n] [obj n])
				)
				(++ size)
			)
			(-- n)
		)
		(return self)
	)
	
	(method (addToEnd obj &tmp n)
		(if (not elements) (= elements (NewList)))
		(= n 0)
		(while (< n argc)
			(if (not (self isDuplicate: [obj n]))
				(AddToEnd
					elements
					(NewNode [obj n] [obj n])
				)
				(++ size)
			)
			(++ n)
		)
		(return self)
	)
	
	(method (addAfter el obj &tmp n num insertNode)
		(if (= insertNode (FindKey elements el))
			(-- argc)
			(= n 0)
			(while (< n argc)
				(if (not (self isDuplicate: [obj n]))
					(= insertNode
						(AddAfter
							elements
							insertNode
							(NewNode [obj n] [obj n])
						)
					)
					(++ size)
				)
				(++ n)
			)
		)
		(return self)
	)
	
	(method (indexOf obj &tmp n node)
		(= n 0)
		(= node (FirstNode elements))
		(while node
			(if (== obj (NodeValue node)) (return n))
			(++ n)
			(= node (NextNode node))
		)
		(return -1)
	)
)

(class Set of List
	(properties)
	
	(method (showStr where)
		(Format where SYSTEM 2 name size)
	)
	
	(method (isDuplicate obj)
		(self contains: obj)
	)
)

(class EventHandler of Set
	(properties)
	
	(method (handleEvent event &tmp node nextNode obj)
		(= node (FirstNode elements))
		(while (and node (not (event claimed?)))
			(= nextNode (NextNode node))
			(breakif (not (IsObject (= obj (NodeValue node)))))
			(obj handleEvent: event)
			(= node nextNode)
		)
		(event claimed?)
	)
)

(class Script of Object
	(properties
		client 0
		state $ffff
		start 0
		timer 0
		cycles 0
		seconds 0
		lastSeconds 0
		ticks 0
		lastTicks 0
		register 0
		script 0
		caller 0
		next 0
	)
	
	(method (init who whoCares reg)
		(= lastTicks gameTime)
		(if (>= argc 1)
			((= client who) script: self)
			(if (>= argc 2)
				(= caller whoCares)
				(if (>= argc 3) (= register reg))
			)
		)
		(= state (- start 1))
		(self cue:)
	)
	
	(method (doit &tmp thisSeconds)
		(if script (script doit:))
		(cond 
			(cycles (if (not (-- cycles)) (self cue:)))
			(seconds
				(if
				(!= lastSeconds (= thisSeconds (GetTime SYSTIME1)))
					(= lastSeconds thisSeconds)
					(if (not (-- seconds)) (self cue:))
				)
			)
			(
				(and
					ticks
					(<=
						(= ticks (- ticks (Abs (- gameTime lastTicks))))
						0
					)
				)
				(= ticks 0)
				(self cue:)
			)
		)
		(= lastTicks gameTime)
	)
	
	(method (dispose &tmp theNextScript)
		(if (IsObject script) (script dispose:))
		(if (IsObject timer) (timer dispose:))
		(if (IsObject client)
			(client
				script:
					(= theNextScript
						(cond 
							((IsObject next) next)
							(next (ScriptID next))
						)
					)
			)
			(cond 
				((not theNextScript) 0)
				((== newRoomNum curRoomNum) (theNextScript init: client))
				(else (theNextScript dispose:))
			)
		)
		(if
		(and (IsObject caller) (== newRoomNum curRoomNum))
			(caller cue: register)
		)
		(= script (= timer (= client (= next (= caller 0)))))
		(super dispose:)
	)
	
	(method (changeState newState)
		(= state newState)
	)
	
	(method (cue)
		(if client (self changeState: (+ state 1) &rest))
	)
	
	(method (handleEvent event)
		(if script (script handleEvent: event))
		(event claimed?)
	)
	
	(method (setScript newScript)
		(if (IsObject script) (script dispose:))
		(if newScript (newScript init: self &rest))
	)
)

(class Event of Object
	(properties
		type $0000
		message 0
		modifiers $0000
		y 0
		x 0
		claimed 0
		port 0
	)
	
	(method (new mask &tmp event)
		(= event (super new:))
		(GetEvent (if argc mask else allEvents) event)
		(return event)
	)
	
	(method (localize &tmp curPort)
		(if (not (& type $4000))
			(= curPort (GetPort))
			(cond 
				((not port) (GlobalToLocal self))
				((!= port curPort)
					(SetPort port)
					(LocalToGlobal self)
					(SetPort curPort)
					(GlobalToLocal self)
				)
			)
			(= port curPort)
		)
		(return self)
	)
	
	(method (globalize &tmp curPort)
		(if (not (& type userEvent))
			(cond 
				((== port (= curPort (GetPort))) (LocalToGlobal self))
				(port (SetPort port) (LocalToGlobal self) (SetPort curPort))
			)
			(= port 0)
		)
		(return self)
	)
)

(class Cursor of Object
	(properties
		view 0
		loop 0
		cel 0
		x 0
		y 0
		hotSpotX 0
		hotSpotY 0
		hidden 0
	)
	
	(method (init)
		(if (or hotSpotX hotSpotY)
			(SetCursor view loop cel hotSpotX hotSpotY)
		else
			(SetCursor view loop cel)
		)
	)
	
	(method (posn theX theY)
		(SetCursor theX theY)
	)
	
	(method (posnHotSpot theX theY)
		(= hotSpotX theX)
		(= hotSpotY theY)
		(self init:)
	)
	
	(method (setLoop whichLoop)
		(= loop whichLoop)
		(self init:)
	)
	
	(method (setCel whichCel)
		(= cel whichCel)
		(self init:)
	)
	
	(method (showCursor trueOrFalse)
		(if argc (SetCursor trueOrFalse))
	)
)
