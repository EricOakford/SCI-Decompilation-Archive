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
;;;)

(define	ticksPerSec		60)
(define	ticksPerMin		3600)
(define	minPerHr			60)

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




;;;(class Collection kindof Object
;;; The Collection class provides the ability to manipulate
;;; collections of objects.  Objects which belong to a Collection
;;; are said to be elements or members of it.  The Collection class
;;; has no particular order defined for its elements, so it should
;;; not be used for situations in which the objects should be
;;; ordered -- use a List instead.
	
(class List kindof Object ;Collection
;;; A List is a Collection which has a specified order to its
;;; elements.  You will probably use the Set class (below) more
;;; than List, as Set automatically weeds out duplicates.	
	(properties
;;;		name "Collect"
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
;;;)




;;;(class List kindof Collection
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
;;;	
;;;	
;;;	(method (showStr where)
;;;		(return (Format where SYSTEM 1 name size))
;;;	)
	
	
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
		(return (Format where SYSTEM 1 name size))
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
		
		(if (>= argc 1)			(= client who)
			(if (>= argc 2) 		(= caller whoCares)
				(if (>= argc 3) 	(= register reg)
				)
			)
		)
		(self changeState:start)
	)
	
	(method (dispose)
		
		(if (IsObject script)
			(script dispose:)
		)
		(if (IsObject timer)
			(timer dispose:)
		)
		(if (IsObject client)
			(client script: 0)
		)
		(if (IsObject caller)
			(caller cue: register)
		)
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
		
		(if (= script newScript)	(newScript init: self &rest))
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

(class Timer kindof Object
	;;; The Timer class implements the concept of an alarm clock,
	;;; cue:ing another object after a certain interval.  Timers
	;;; dispose: of themselves after cue:ing their client, and always
	;;; check to see if their client is still present before cue:ing it.

	;;; An important concept relating to Timers is that of game time
	;;; versus real time.  Real time is just what it sounds like --
	;;; real time in real seconds.  Game time is time adjusted to the
	;;; performance of the user's computer -- it is the same as real
	;;; time on a computer which is able to keep up with the animation
	;;; demands of the game, but slows down in proportion to the speed
	;;; of the user's computer when it is not able to keep up.

	;;; An example may help clarify this.  Say that you're writing a
	;;; game in which ego has only five seconds to leave a room before
	;;; a bomb blows up.  If you set this time interval as real time,
	;;; it may give you just enough time to get out on your nice fast
	;;; 286 or 386 machine.  But on a Tandy 1000, where it takes 1/5th
	;;; of a second to complete an animation cycle instead of the
	;;; standard 1/10th of a second, the user will only be able to go
	;;; half as far and thus has no chance of leaving the room before
	;;; the bomb blows.  The time interval should really have been set
	;;; in game time, which would have given the user the same number
	;;; of animation cycles to get out.

	;;; As a rule of thumb, time intervals which are meant to be a
	;;; constraint on how long the user has to do something should be
	;;; set in game time, whereas time intervals which are just meant
	;;; to be a delay between two events should be real time.  A user
	;;; with a slow machine has no desire to watch a banner screen
	;;; twice as long as one with a fast machine.

	(properties
		cycleCnt -1			;number of animation cycles left before done
		seconds -1			;number of seconds left before done
		lastTime -1			;private
		client 0				;who to cue: when done
	)

;;;	(methods
;;;		set					;set number of game-time seconds
;;;		setCycle				;set number of animation cycles
;;;		setReal				;set number of real-time seconds
;;;		delete				;do actual disposal of the Time
;;;	)

	
	(procedure (CueClient &tmp who)
		;; A helper procedure.  Timer has expired, so detach from client
		;; and cue: it.

		;Detach ourselves from our client, but remember who it was.
		(= who client)
		(= client 0)

		(if (IsObject who)
			(if (who respondsTo:#timer:)
				(who timer:0)
			)
			(if (who respondsTo:#cue:)
				(who cue:)
			)
		)
	)


	(method (new)
		;; Create a new timer.  If the class itself is being asked to
		;; perform new:, return a new instance.  Otherwise, return the
		;; instance that is being asked.

		(return
			(if (== self Timer)
				(super new:)
			else
				self
			)
		)
	)

	
	(method (init caller)
		;; Set this timer's client to caller, add it to the master game
		;; timer list, and attach to the client.

		;Set our client.
		(= client caller)
		
		;Add ourselves to the timer list.
		(timers add: self)
		
		(if (caller respondsTo: #timer)
			;If the caller has a timer already attached, dispose that timer.
			(if (IsObject (caller timer?))
				((caller timer?) dispose:)
			)
			(caller timer: self)
		)
	)
	
	(method (doit &tmp theTime)
		;; See if this timer has timed out.  If so, cue the client and
		;; dispose: the timer.

		(if (!= cycleCnt -1)
			;This is a cycle counter.
			(if (not (-- cycleCnt)) (CueClient))
		else
			;This is a real-time counter.
			(if (!= lastTime (= theTime (GetTime TRUE)))
				(= lastTime theTime)
				(if (not (-- seconds)) (CueClient))
			)
		)
	)

	
	(method (dispose)
		;; Simply unhook the timer from its client -- the actual
		;; disposal will be done by the main game loop invoking the
		;; delete: method of all timers without clients.

		(if (and (IsObject client) (client respondsTo:#timer:))
			(client timer:0)
		)
		(= client 0)
	)


	(method (delete)
		;; Do the actual deletion of the Timer.

		(if (== client 0)
			(timers delete:self)
			(super dispose:)
		)
	)


	(method (setCycle caller cycles &tmp aTimer)
		;; Set the timer to go off after a certain number of animation
		;; cycles.

		(= aTimer (if (& -info- CLASS) (self new:) else self))
		(aTimer init:caller, cycleCnt:cycles)
		(return aTimer)
	)

	
	(method (set caller sec min hr &tmp aTimer ticks theSpeed [str 50])
		;; Set the timer to go off after a certain number of seconds of
		;; game time (real time on fast machines, slower on slow
		;; machines).

;;;		(define	ticksPerSec		60)
;;;		(define	ticksPerMin		3600)
;;;		(define	minPerHr			60)
		(= theSpeed speed)
		(if (== theSpeed 0) (= theSpeed 1))
		(= ticks (/ (* sec ticksPerSec) theSpeed))
		(if (> argc 2)
			(+= ticks (/ (* min ticksPerMin) theSpeed))
		)
		(if (> argc 3)
			;This is a bit funny in order to avoid overflow.
			(+= ticks (* (/ (* hr ticksPerMin) theSpeed) minPerHr))
		)
		(= aTimer (if (& -info- CLASS) (self new:) else self))
		(aTimer init:caller, cycleCnt:ticks)
		(return aTimer)
	)

	
	(method (setReal caller sec min hr &tmp aTimer secs)
		;; Set the timer to go off after a certain period of real time.

		(= secs sec)
		(if (> argc 2)
			(+= secs (* min 60))
		)
		(if (> argc 3)
			(+= secs (* hr 3600))
		)
		(= aTimer (if (& -info- CLASS) (self new:) else self))
		(aTimer init:caller, seconds:secs)
		(return aTimer)
	)
)


(class TimeOut of Object
	; static timer counts down elapsed cycles only
	; intended to be interrogated by interested parties
	; recommended that rm000 include kindofs for visibility

	(properties
		name {TO}
		timeLeft 0
	)

;;;	(methods
;;;		set
;;;	)

	(method (set cToCount)
		(= timeLeft cToCount)
	)

	(method (doit)
		(if timeLeft
			(-- timeLeft)
		)
	)
)