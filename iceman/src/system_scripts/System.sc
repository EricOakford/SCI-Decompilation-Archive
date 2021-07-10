;;; Sierra Script 1.0 - (do not remove this comment)
;;;
;;;	SYSTEM.SC
;;;	(c) Sierra On-Line, Inc, 1988
;;;
;;;	Author: Jeff Stephenson
;;;
;;;	General purpose 'system' classes, presumably not adventure-game
;;;	specific.  Defines the base class, Object, and all Collection
;;;	classes.  Probably should be broken up into several modules.
;;;
;;;	Classes:
;;;		Object
;;;		Code
;;;		Collection
;;;		List
;;;		Set
;;;		Script
;;;		Event

(script# SYSTEM)
(include game.sh)
(use Main)
(use Intrface)

;;;(procedure 
;;;	sign
;;;	umod
;;;	Min
;;;	Max
;;;	InRect
;;;	OneOf
;;;)

(public
	sign			0
	umod			1
	Min			2
	Max			3
	InRect		4
	OneOf			5
)

(procedure (sign x)
	;return +1 if arg is positive, -1 if negative, 0 if 0
	(return 
		(if (< x 0) -1 else (> x 0))
	)
)

(procedure (umod n modN)
	(-= n (* modN (/ n modN)))
	(if (< n 0) (+= n modN))
	(return n)
)


(procedure (Min nums &tmp otherMin)
	(if (or (== argc 1) (< nums (= otherMin (Min &rest))))
		nums
	else 
		otherMin
	)
)
(procedure (Max nums &tmp otherMax)
	(if (or (== argc 1) (> nums (= otherMax (Max &rest))))
		nums
	else
		otherMax
	)
)

(procedure (InRect lEdge tEdge rEdge bEdge ptxOrObj pty)
	(return 
		(and 
			(<= lEdge (if (< argc 6) (ptxOrObj x?) else ptxOrObj)	rEdge)	
			(<= tEdge (if (< argc 6) (ptxOrObj y?) else pty	)		bEdge)	
		)
	)
)

(procedure (OneOf what things &tmp i)
	(for
		(	(= i 0)	)
		(< i (- argc 1))
		(	(++ i)	)
		
		(if (== what [things i]) (return (or what TRUE)))
	)
	(return FALSE)
)

;(class RootObj
;	;;; RootObj is defined in the kernel and contains only that which
;	;;; is necessary for an object to be recognized as such, having no
;	;;; behavior of its own.
;
;	(properties
;		species
;			;This is a pointer to the property dictionary (contained
;			;in the defining class) of an object.  Thus if two
;			;objects have equal species properties, they are both
;			;instances of the same class.  Do not change this property,
;			;or the object will stop working.
;
;		superClass
;			;This is a pointer to the method dictionary of the object's
;			;superclass, and is used to look up a method which is not
;			;defined locally.  Don't change it, or the object will cease
;			;functioning.
;
;		-info-
;			;Bit-mapped information about the object.  Bits are:
;			;	CLONED		0 = object is static
;			;					1 = object was created with new:
;			;	NODISPOSE	0 = dispose: object if CLONED

;			;					1 = don't dispose: object
;			;	NODISPLAY	0 = display object in debugger's object display
;			;					1 = don't display object in debugger's object
;			;						 display
;			;	CLASS			0 = object is an instance of a class
;			;					1 = object is a class
;	)
;)




(class Object ;kindof RootObj
;;; Class Object is the superclass of all other Script objects.  It
;;; defines the behavior which is expected of all objects, ensuring
;;; that all objects will respond to a certain set of selectors.
	
	(properties
		name "Obj"			;print name of the object
	)
	
;;;	(methods
;;;		new					;create a new instance of the class/object
;;;		init					;initialize the object
;;;		doit					;do your thing
;;;		dispose				;dispose of this instance
;;;		showStr				;produce a string which shows me
;;;		showSelf				;show who I am
;;;		perform				;execute code in your environment
;;;		isKindOf				;is object/class a subclass of something?
;;;		isMemberOf			;is object an instance of something?
;;;		respondsTo			;does object responds to a message?
;;;		yourself				;answer your object ID
;;;	)
	
	
	(method (new)
		
		(return (Clone self))
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
		
		(return (StrCpy where name))
	)
	
	
	(method (showSelf &tmp [str 200])
		
		(Print (self showStr:@str))
	)
	
	
	(method (perform theCode)
		
		(return (theCode doit: self &rest))
	)
	
	
	(method (respondsTo selector)
		
		(return (RespondsTo self selector))
	)
	
	
	(method (isMemberOf what)
		
		(return
			(and
				(& (what -info-?) CLASS)
				(not (& -info- CLASS))
				(== species (what species?))
			)
		)
	)
	
	
	(method (isKindOf what &tmp theSuper)
		
		(= theSuper (self superClass?))
		(return
			(or
				(== species (what species?))
				(and
					(IsObject theSuper)
					(theSuper isKindOf: what)
				)
			)
		)
	)
	
	
	(method (yourself)
		
		(return self)
	)
)



(class Code kindof Object
;;; Class Code is used to attach code to various objects (as in
;;; the 'viewer' property of an Actor) or for use in the perform:
;;; method of Object.  In either case, it is only the doit: method
;;; of the class which is important.
	
	(method (doit)
	)
)




(class Collection kindof Object
;;; The Collection class provides the ability to manipulate
;;; collections of objects.  Objects which belong to a Collection
;;; are said to be elements or members of it.  The Collection class
;;; has no particular order defined for its elements, so it should
;;; not be used for situations in which the objects should be
;;; ordered -- use a List instead.
	
	
	(properties
		name "Collect"
		elements 0			;pointer to a Kernal List (KList) containing
		;the elements
		size 0				;the number of elements in the list
	)
	
;;;	(methods
;;;		add					;add elements
;;;		delete				;delete elements
;;;		eachElementDo		;invoke a method in each element
;;;		firstTrue			;return first element satisfying a condition
;;;		allTrue				;return TRUE if all elements satisfy a
;;;		;condition
;;;		contains				;return TRUE if an object is in the Collection
;;;		isEmpty				;return TRUE if the list is empty (size == 0)
;;;		first					;return first KNode of the Collection
;;;		next					;return next KNode of the Collection
;;;		release				;delete elements from list to deallocate all nodes
;;;	)
	
	
	
	(method (showStr where)
		(return (Format where SYSTEM 0 name size))
	)
	
	
	(method (showSelf &tmp [str 40])
		(Print (self showStr:@str))
		(self eachElementDo: #showSelf:)
	)
	
	
	(method (add item &tmp obj n node)
		
		;If the Collection does not have a KList, create one for it.
		(if (not elements)
			(= elements (NewList))
		)
		
		;Loop over the arguments, adding each to the Collection.
		(for	((= n 0))
			(< n argc)
			((++ n))
			(AddToEnd elements (NewNode [item n] [item n]))
			(++ size)
		)
		
		;Return the ID of the Collection.
		(return self)
	)
	
	
	(method (delete item &tmp n)
		
		;Loop over arguments, deleting each from the Collection.
		(for	((= n 0))
			(< n argc)
			((++ n))
			
			(if (DeleteKey elements [item n])
				(-- size)
			)
		)
		
		;Return the ID of the Collection.
		(return self)
	)
	
	
	(method (dispose)
		
		(if elements
			(self eachElementDo: #dispose:)
			(DisposeList elements)
		)
		(= elements NULL)
		(= size 0)
		(super dispose:)
	)
	
	
	(method (first)
		
		(return (FirstNode elements))
	)
	
	
	(method (next node)
		
		(return (NextNode node))
	)
	
	
	(method (isEmpty)
		
		(return (or (== elements NULL) (EmptyList elements)))
	)
	
	
	(method (contains anObject)
		
		(return (FindKey elements anObject))
	)
	
	
	(method (eachElementDo action &tmp node nextNode obj)
		
		(for	((= node (FirstNode elements)))	;(for	((= node (self first:)))
			node
			((= node nextNode))
			
			(= nextNode (NextNode node))			;(= nextNode (self next:node))
			(if (not (IsObject (= obj (NodeValue node)))) (return))
			(obj action: &rest)
		)
	)
	
	
	(method (firstTrue action &tmp node nextNode obj)
		
		(for	((= node (FirstNode elements)))	;(for	((= node (self first:)))
			node
			((= node nextNode))
			
			(= nextNode (NextNode node))			;(= nextNode (self next:node))
			(= obj (NodeValue node))
			(if (obj action: &rest)
				(return obj)
			)
		)
		(return NULL)
	)
	
	
	(method (allTrue action &tmp node nextNode obj)
		
		(for	((= node (FirstNode elements)))	;(for	((= node (self first:)))
			node
			((= node nextNode))
			
			(= nextNode (NextNode node))			;(= nextNode (self next:node))
			(= obj (NodeValue node))
			(if (not (obj action: &rest))
				(return FALSE)
			)
		)
		(return TRUE)
	)
	
	
	(method (release &tmp node nextNode)
		
		;delete all elements from collection to deallocate list nodes	
		
		(for	((= node (FirstNode elements)))
			node
			((= node nextNode))
			
			(= nextNode (NextNode node))
			
			(self delete:(NodeValue node))
		)
	)
)




(class List kindof Collection
;;; A List is a Collection which has a specified order to its
;;; elements.  You will probably use the Set class (below) more
;;; than List, as Set automatically weeds out duplicates.
	
;;;	(methods
;;;		at					;return the object at a given position in the List
;;;		last				;return the last KNode of the List
;;;		prev				;return previous KNode
;;;		addToFront		;add object to front of List
;;;		addToEnd			;add object to end of List
;;;		addAfter			;add object after an element of List
;;;		indexOf			;return index of an object in a List
;;;	)
	
	
	(method (showStr where)
		(return (Format where SYSTEM 1 name size))
	)
	
	
	(method (at n &tmp node)
		
		(for	((= node (FirstNode elements)))		;(for	((= node (self first:)))
			(and n node)
			((-- n) (= node (NextNode node)))	;((-- n) (= node (self next: node)))
		)
		(return (NodeValue node))
	)
	
	
	(method (last)
		
		(return (LastNode elements))
	)
	
	
	(method (prev node)
		
		(return (PrevNode node))
	)
	
	
	(method (addToFront obj &tmp n)
		
		(if (not elements)
			(= elements (NewList))
		)
		
		(for	((= n (- argc 1)))
			(<= 0 n)
			((-- n))
			(AddToFront elements (NewNode [obj n] [obj n]))
			(++ size)
		)
		(return self)
	)
	
	
	(method (addToEnd obj &tmp n)
		
		(if (not elements)
			(= elements (NewList))
		)
		
		(for	((= n 0))
			(< n argc)
			((++ n))
			(AddToEnd elements (NewNode [obj n] [obj n]))
			(++ size)
		)
		(return self)
	)
	
	
	(method (addAfter el obj &tmp n num insertNode)
		
		(if (= insertNode (FindKey elements el))
			(-- argc)
			(for	((= n 0))
				(< n argc)
				((++ n))
				(= insertNode
					(AddAfter elements insertNode (NewNode [obj n] [obj n]))
				)
				(++ size)
			)
		)
		(return self)
	)
	
	
	(method (indexOf obj &tmp n node)
		
		(for	((= n 0) (= node (FirstNode elements)))	;(= node (self first:))
			node
			((++ n) (= node (NextNode node)))			;(= node (self next:node))
			
			(if (== obj (NodeValue node))
				(return n)
			)
		)
		(return -1)
	)
)





(class Set kindof List
;;; A Set is a kind of List which does not contain duplicate
;;; elements: adding an object to a Set which already contains the
;;; object does not change the Set.  This eliminates the
;;; possibility of a whole class of errors based on multiple
;;; occurances of an object in a collection.  Most system
;;; collections (the cast, etc.) are Sets.
	
	(method (showStr where)
		(return (Format where SYSTEM 2 name size))
	)
	
	
	(method (add item &tmp obj n anItem)
		
		(if (not elements)
			(= elements (NewList))
		)
		
		(for	((= n 0))
			(< n argc)
			((++ n))
			
			(= anItem [item n])
			(if (not (self contains: anItem))
				(AddToEnd elements (NewNode anItem anItem))
				(++ size)
			)
		)
	)
)

(class EventHandler kindof Set
;;;	(methods
;;;		handleEvent
;;;	)
	
	(method (handleEvent event &tmp node nextNode obj)
		
		(for	((= node (FirstNode elements)))
			(and node (not (event claimed?)))
			((= node nextNode))
			
			(= nextNode (NextNode node))
			(breakif (not (IsObject (= obj (NodeValue node)))))
			(obj handleEvent: event)
		)
		(return (event claimed?))
	)
)




(class Script kindof Object
	;; A Script is a kind of Object which has a state, methods to
	;; change that state, and code to execute when the state changes.
	;; It is used to model a sequence of actions which should be
	;; executed by an object, such as an Actor walking to the base of
	;; some stairs, walking up the stairs, and opening a door.
	
	(properties
		client 0			;the Object whose actions are controlled by this
							;Script
		state -1			;the current state of the script
		start 0			;the starting state of the script
		timer 0			;the ID of a timer whose client is this Script
		cycles 0			;the number of cycles to wait before changing state
		seconds 0		;the number of seconds to wait before changing state
		lastSeconds 0	;private variable
		register	0		;open architecture property, can be anything the
							;programmer wants it to be.
							;to store multiple values, make this a list.
							;	--Pablo Ghenis
		script	0		;sub-scripts provide subroutine-style functionality
		caller	0		;who should we cue when we're disposed
		next		0		;who should we CHAIN to when we're disposed
							;can be pointer to an instance of Script or
							;number of file that has a Script as public zero
	)
	
;;;	(methods
;;;		changeState		;change to new state
;;;		cue				;change to next state
;;;		handleEvent		;handle events passed to this Script
;;;		setScript		;set new sub-script
;;;	)
	
	
	(method (doit &tmp thisSeconds)
		
		(if script
			(script doit:)
		)
		(cond
			(cycles
				(if (not (-- cycles))
					;(client cue:)
					(self cue:)
				)
			)
			(seconds
				(= thisSeconds (GetTime 1))
				(if (!= lastSeconds thisSeconds)
					(= lastSeconds thisSeconds)
					(if (not (-- seconds))
						;(client cue:)
						(self cue:)
					)
				)
			)
		)
	)
	
	
	(method (init who whoCares reg)
		
		(if (>= argc 1)			((= client who) script: self)	;double link!
			(if (>= argc 2) 		(= caller whoCares)
				(if (>= argc 3) 	(= register reg)
				)
			)
		)
		(self changeState:start)
	)
	
	(method (dispose &tmp theNextScript)
		
		(if (IsObject script)
			(script dispose:)
		)
		(if (IsObject timer)
			(timer dispose:)
		)
		(if (IsObject client)
			;;
			;;the following is same as (client script 0) for pre-next code
			;;see QSCRIPT.SC for queueing up scripts using their "next" 
			;;property
			;;
			(client script:
				(= theNextScript
					(cond
						;;((== next self) (Clone self))	;handled in QueScript
						((IsObject next) next)	;script in memory
						(next (ScriptID next))	;next is number, time to load script
						;;(else 0)
					)
				)
			)
			(cond
				((not theNextScript)				;no next, nothing to do
					FALSE
				)
				((== newRoomNum curRoomNum)	;have next, no room change
					(theNextScript init: client)
				)
				(else									;room change
					(theNextScript dispose:)		;clean up clones!
				)
			)
		)
		
		;;NOTE: client's script property MUST be changed BEFORE cue'ing caller
		;;      We DON'T cue caller on room changes.
		;;
		(if (and (IsObject caller) (== newRoomNum curRoomNum))
			(caller cue:register)	;return register value
		)
		
		;;the following cleanup statement allow a "disposed" static script 
		;;to be reused, even though you shouldn't do it! More proper is to 
		;;Clone a script if it will be used many times.
		;;
		(= script (= timer (= client (= next (= caller 0)))))
		
		(super dispose:)
	)
	
	
	(method (changeState newState)
		(= state newState)
	)
		
	(method (cue)
		;;(if (!= self (client script)) (Print "error: orphan script!"))	;debugging aid
		(self changeState: (+ state 1) &rest)
	)
	
	(method (setScript newScript)
		
		(if (IsObject script)		(script dispose:))
		
		;;(if (= script newScript)	(newScript init: self &rest))
		(if newScript
			(newScript init: self &rest)	;init sets our script property!
		)
	)
	
	
	
	(method (handleEvent event)
		(if script
			(script handleEvent:event)
		)
		(return (event claimed?))
	)
)




(class Event kindof Object
;;; The Event class is the class of user input events (key presses,
;;; mouse clicks, etc.)
	
	(properties
		type 0			;the type of event (Event types in kernel.sh)
		message 0		;the value of the event (e.g. ASCII value of
							;keypress)
		modifiers 0		;modifiers of event (shiftDown, ctrlDown, altDown)
		y 0				;y coord of mouse when event occurred
		x 0				;x coord of mouse when event occurred
		claimed 0		;TRUE if the event has been responded to
	)
	
	
	(method (new mask &tmp event)
		
		(= event (super new:))
		(GetEvent
			(if argc	mask else allEvents)
			event
		)
		(return event)
	)
)


