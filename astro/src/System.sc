;;; Sierra Script 1.0 - (do not remove this comment)
(script# 999)
(include game.sh)
(use Main)
(use Intrface)

(public
	sign 0
	umod 1
)

(procedure (sign param1)
	(return (if (< param1 0) -1 else (> param1 0)))
)

(procedure (umod param1 param2)
	(if
		(<
			(= param1 (- param1 (* param2 (/ param1 param2))))
			0
		)
		(= param1 (+ param1 param2))
	)
	(return param1)
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
	
	(method (showStr param1)
		(StrCpy param1 name)
	)
	
	(method (showSelf &tmp [temp0 200])
		(Print (self showStr: @temp0))
	)
	
	(method (perform param1)
		(param1 doit: self &rest)
	)
	
	(method (isKindOf param1 &tmp objSuperClass)
		(= objSuperClass (self superClass?))
		(cond 
			((== species (param1 species?)))
			((IsObject objSuperClass) (objSuperClass isKindOf: param1))
		)
	)
	
	(method (isMemberOf param1)
		(return
			(if
				(and
					(& (param1 -info-?) $8000)
					(not (& -info- $8000))
				)
				(== species (param1 species?))
			else
				0
			)
		)
	)
	
	(method (respondsTo param1)
		(RespondsTo self param1)
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
	
	(method (dispose)
		(if elements
			(self eachElementDo: #dispose)
			(DisposeList elements)
		)
		(= size (= elements 0))
		(super dispose:)
	)
	
	(method (showStr param1)
		(Format param1 999 0 name size)
	)
	
	(method (showSelf &tmp [temp0 40])
		(Print (self showStr: @temp0))
		(self eachElementDo: #showSelf)
	)
	
	(method (add param1 &tmp temp0 temp1 temp2)
		(if (not elements) (= elements (NewList)))
		(= temp1 0)
		(while (< temp1 argc)
			(AddToEnd
				elements
				(NewNode [param1 temp1] [param1 temp1])
			)
			(++ size)
			(++ temp1)
		)
		(return self)
	)
	
	(method (delete param1 &tmp temp0)
		(= temp0 0)
		(while (< temp0 argc)
			(if (DeleteKey elements [param1 temp0]) (-- size))
			(++ temp0)
		)
		(return self)
	)
	
	(method (eachElementDo param1 &tmp temp0 temp1 temp2)
		(= temp0 (FirstNode elements))
		(while temp0
			(= temp1 (NextNode temp0))
			(if (not (IsObject (= temp2 (NodeValue temp0))))
				(return)
			)
			(temp2 param1: &rest)
			(= temp0 temp1)
		)
	)
	
	(method (firstTrue param1 &tmp temp0 temp1 temp2)
		(= temp0 (FirstNode elements))
		(while temp0
			(= temp1 (NextNode temp0))
			(= temp2 (NodeValue temp0))
			(if (temp2 param1: &rest) (return temp2))
			(= temp0 temp1)
		)
		(return 0)
	)
	
	(method (allTrue param1 &tmp temp0 temp1 temp2)
		(= temp0 (FirstNode elements))
		(while temp0
			(= temp1 (NextNode temp0))
			(= temp2 (NodeValue temp0))
			(if (not (temp2 param1: &rest)) (return 0))
			(= temp0 temp1)
		)
		(return 1)
	)
	
	(method (contains param1)
		(FindKey elements param1)
	)
	
	(method (isEmpty)
		(if (== elements 0) else (EmptyList elements))
	)
	
	(method (first)
		(FirstNode elements)
	)
	
	(method (next param1)
		(NextNode param1)
	)
	
	(method (release &tmp temp0 temp1)
		(= temp0 (FirstNode elements))
		(while temp0
			(= temp1 (NextNode temp0))
			(self delete: (NodeValue temp0))
			(= temp0 temp1)
		)
	)
)

(class List of Collection
	(properties
		elements 0
		size 0
	)
	
	(method (showStr param1)
		(Format param1 999 1 name size)
	)
	
	(method (at param1 &tmp temp0)
		(= temp0 (FirstNode elements))
		(while (and param1 temp0)
			(-- param1)
			(= temp0 (NextNode temp0))
		)
		(NodeValue temp0)
	)
	
	(method (last)
		(LastNode elements)
	)
	
	(method (prev param1)
		(PrevNode param1)
	)
	
	(method (addToFront param1 &tmp temp0)
		(if (not elements) (= elements (NewList)))
		(= temp0 (- argc 1))
		(while (<= 0 temp0)
			(AddToFront
				elements
				(NewNode [param1 temp0] [param1 temp0])
			)
			(++ size)
			(-- temp0)
		)
		(return self)
	)
	
	(method (addToEnd param1 &tmp temp0)
		(if (not elements) (= elements (NewList)))
		(= temp0 0)
		(while (< temp0 argc)
			(AddToEnd
				elements
				(NewNode [param1 temp0] [param1 temp0])
			)
			(++ size)
			(++ temp0)
		)
		(return self)
	)
	
	(method (addAfter param1 param2 &tmp temp0 temp1 temp2)
		(if (= temp2 (FindKey elements param1))
			(-- argc)
			(= temp0 0)
			(while (< temp0 argc)
				(= temp2
					(AddAfter
						elements
						temp2
						(NewNode [param2 temp0] [param2 temp0])
					)
				)
				(++ size)
				(++ temp0)
			)
		)
		(return self)
	)
	
	(method (indexOf param1 &tmp temp0 temp1)
		(= temp0 0)
		(= temp1 (FirstNode elements))
		(while temp1
			(if (== param1 (NodeValue temp1)) (return temp0))
			(++ temp0)
			(= temp1 (NextNode temp1))
		)
		(return -1)
	)
)

(class Set of List
	(properties
		elements 0
		size 0
	)
	
	(method (showStr param1)
		(Format param1 999 2 name size)
	)
	
	(method (add param1 &tmp temp0 temp1 temp2)
		(if (not elements) (= elements (NewList)))
		(= temp1 0)
		(while (< temp1 argc)
			(= temp2 [param1 temp1])
			(if (not (self contains: temp2))
				(AddToEnd elements (NewNode temp2 temp2))
				(++ size)
			)
			(++ temp1)
		)
	)
)

(class EventHandler of Set
	(properties
		elements 0
		size 0
	)
	
	(method (handleEvent pEvent &tmp temp0 temp1 temp2)
		(= temp0 (FirstNode elements))
		(while (and temp0 (not (pEvent claimed?)))
			(= temp1 (NextNode temp0))
			(breakif (not (IsObject (= temp2 (NodeValue temp0)))))
			(temp2 handleEvent: pEvent)
			(= temp0 temp1)
		)
		(pEvent claimed?)
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
		register 0
		script 0
		caller 0
	)
	
	(method (init theClient theCaller theRegister)
		(if (>= argc 1)
			(= client theClient)
			(if (>= argc 2)
				(= caller theCaller)
				(if (>= argc 3) (= register theRegister))
			)
		)
		(self changeState: start)
	)
	
	(method (doit &tmp theLastSeconds)
		(if script (script doit:))
		(cond 
			(cycles (if (not (-- cycles)) (self cue:)))
			(
				(and
					seconds
					(!= lastSeconds (= theLastSeconds (GetTime 1)))
				)
				(= lastSeconds theLastSeconds)
				(if (not (-- seconds)) (self cue:))
			)
		)
	)
	
	(method (dispose)
		(if (IsObject script) (script dispose:))
		(if (IsObject timer) (timer dispose:))
		(if (IsObject client) (client script: 0))
		(if (IsObject caller) (caller cue: register))
		(super dispose:)
	)
	
	(method (changeState newState)
		(= state newState)
	)
	
	(method (cue)
		(self changeState: (+ state 1) &rest)
	)
	
	(method (handleEvent pEvent)
		(if script (script handleEvent: pEvent))
		(pEvent claimed?)
	)
	
	(method (setScript theScript)
		(if (IsObject script) (script dispose:))
		(if (= script theScript)
			((= script theScript) init: self &rest)
		)
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
	)
	
	(method (new param1 &tmp newSuper)
		(= newSuper (super new:))
		(GetEvent (if argc param1 else 32767) newSuper)
		(return newSuper)
	)
)

(class Timer of Object
	(properties
		cycleCnt -1
		seconds -1
		lastTime -1
		client 0
	)
	
	(procedure (localproc_0768 &tmp theClient)
		(= theClient client)
		(= client 0)
		(if (IsObject theClient)
			(if (theClient respondsTo: #timer) (theClient timer: 0))
			(if (theClient respondsTo: #cue) (theClient cue:))
		)
	)
	
	
	(method (new)
		(return (if (== self Timer) (super new:) else self))
	)
	
	(method (init theClient)
		(= client theClient)
		(timers add: self)
		(if (theClient respondsTo: #timer)
			(if (IsObject (theClient timer?))
				((theClient timer?) dispose:)
			)
			(theClient timer: self)
		)
	)
	
	(method (doit &tmp theLastTime)
		(cond 
			((!= cycleCnt -1) (if (not (-- cycleCnt)) (localproc_0768)))
			((!= lastTime (= theLastTime (GetTime 1)))
				(= lastTime theLastTime)
				(if (not (-- seconds)) (localproc_0768))
			)
		)
	)
	
	(method (dispose)
		(if
		(and (IsObject client) (client respondsTo: #timer))
			(client timer: 0)
		)
		(= client 0)
	)
	
	(method (set param1 param2 param3 param4 &tmp temp0 temp1 theSpeed [temp3 50])
		(if (== (= theSpeed speed) 0) (= theSpeed 1))
		(= temp1 (/ (* param2 60) theSpeed))
		(if (> argc 2)
			(= temp1 (+ temp1 (/ (* param3 3600) theSpeed)))
		)
		(if (> argc 3)
			(= temp1
				(+ temp1 (* (/ (* param4 3600) theSpeed) 60))
			)
		)
		((= temp0 (if (& -info- $8000) (self new:) else self))
			init: param1
			cycleCnt: temp1
		)
		(return temp0)
	)
	
	(method (setCycle theCycler sendParams &tmp temp0)
		((= temp0 (if (& -info- $8000) (self new:) else self))
			init: theCycler
			cycleCnt: sendParams
		)
		(return temp0)
	)
	
	(method (setReal param1 param2 param3 param4 &tmp temp0 temp1)
		(= temp1 param2)
		(if (> argc 2) (= temp1 (+ temp1 (* param3 60))))
		(if (> argc 3) (= temp1 (+ temp1 (* param4 3600))))
		((= temp0 (if (& -info- $8000) (self new:) else self))
			init: param1
			seconds: temp1
		)
		(return temp0)
	)
	
	(method (delete)
		(if (== client 0)
			(timers delete: self)
			(super dispose:)
		)
	)
)

(class TO of Object
	(properties
		timeLeft 0
	)
	
	(method (doit)
		(if timeLeft (-- timeLeft))
	)
	
	(method (set theTimeLeft)
		(= timeLeft theTimeLeft)
	)
)
