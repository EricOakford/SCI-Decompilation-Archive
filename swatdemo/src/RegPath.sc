;;; Sierra Script 1.0 - (do not remove this comment)
;;;;
;;;;	REGPATH.SC
;;;;
;;;;	(c) Sierra On-Line, Inc, 1992
;;;;
;;;;	Author: 	J. Mark Hood
;;;;	Updated: Randy MacNeill
;;;;
;;;;	Motion classes for a regional path -- i.e. moving to a series of
;;;;	pre-defined points in a sequence of rooms.
;;;;
;;;;	Note: Points along path must be entirely contained in a kept region
;;;;	and the Actor instance as well as the mover instance must be in the
;;;;	Region module.	The Region must be set to the script# of the script
;;;;	containing the	instances. See GAMES\ICE\SOURCE\water.sc for example.
;;;;
;;;;	Classes:
;;;;		RegionPath


(script#	REGPATH)
(include game.sh) (include regpath.sh)
(use Main)
(use Print)
(use Motion)


(class RegionPath kindof MoveTo
	(properties
		currentRoom		0
		value				-1
		endType			RP_STARTOVER
		intermediate 	0
		initialized		FALSE
		completed		TRUE
		savedOldStuff	FALSE
		theRegion		0
		theOldBits		0
		theOldSignal	0
	)
	
;;;	(methods
;;;		init
;;;		next
;;;		moveDone
;;;		atEnd
;;;		nextRoom
;;;		at
;;;		nextValue
;;;		findPathend
;;;		findPrevroom
;;;		curRoomCheck
;;;	)
	
	(method (init actor toCall inter reset)
		(if (>= argc 4) 
			(= value -1)
			(= initialized FALSE)
			(= completed TRUE)
		)
		(if completed
			(if argc
				(= client actor)
				(if (>= argc 2)
					(= caller toCall)
					(if (>= argc 3)
						(= intermediate inter)
					)
				)
			)
			(if (not initialized)
				(if (not savedOldStuff)
					(= theOldBits 		(client illegalBits?))
					(= theOldSignal 	(client signal?))
					(= savedOldStuff TRUE)
				)
				(if (& endType RP_REVERSE)
					(self 
						findPathend:,
						next:
					)
					(client posn: x y)
				else
					(self 
						value: 		0, 
						nextRoom:
					)
				)
				(= initialized TRUE)
				(client 
					illegalBits: 	0,
					ignoreActors:	,
					ignoreHorizon:
				)
			)
			(= completed FALSE)
			(self next:)
		)
		(super init:)
		(self curRoomCheck:)
	); init

	(method (curRoomCheck &tmp theZ)
		(= theZ (client z?))
		(if (== currentRoom curRoomNum) ; show
			(client 
				z:					(if (>= theZ 1000) (- theZ 1000) else theZ),
				illegalBits:	theOldBits,
				signal:			theOldSignal
			)
		else									  ; hide
			(client 
				z:					(if (< theZ 1000) (+ theZ 1000) else theZ),
				illegalBits:	0,
				ignoreActors:	TRUE,
				ignoreHorizon:
			)
		)
	)
	
	(method (next)
		(= x (self at: (self nextValue: TRUE)))
		(= y (self at: (+ value 1)))
	); next
	
	(method (nextRoom)
		(if (& endType RP_REVERSE)
			(self findPrevroom:)
		else
			(= currentRoom (self at: (+ value 1)))
		)
		(self 
			next:,
			curRoomCheck:
		)
		(client posn: x y)
	); nextRoom
	
	(method (moveDone)
		(= completed TRUE)
		(if (self atEnd:)
			(self
				value:			-1,
				initialized:	FALSE
			)
			(if (& endType RP_STARTOVER)
				(self init:)
			else
				(super moveDone:)
			)
		else
			(if intermediate
				(intermediate cue: (/ value 2))
			)
			(if (== (self at: (self nextValue:)) NEXTROOM)
				(self 
					next:,
					nextRoom:
				)
			)
			(self init:)
		)
	); moveDone
	
	(method (atEnd)
		(return
			(or
				(and 
					(& endType RP_REVERSE)
					(<= (- value 2) 0)
				)
				(== (self at: (+ value 2)) PATHEND)
			)
		)
	); atEnd
	
	(method (at n)
;RM 	method 'at' contains (return [nameOfLocalArray n])
		(Printf {%s needs an 'at:' method.} name)
		(return 0)
	) ; at
	
	(method (dispose)
		(if theRegion
			(if (not ((ScriptID theRegion) keep?))
				(super dispose:)
			)
		else
			(Printf {%s theRegion: not defined.} name)
		)
	); dispose

	(method (nextValue args &tmp adjust)
		; increments value by 2 or decrements by 2, depending on direction
		; sets value to correct position in array
		(= adjust (- (* (not (& endType RP_REVERSE)) 4) 2))
		(if argc
			(return (+= value adjust))
		else
			(return (+ value adjust))
		)
	); nextValue

	(method (findPathend) ; only used for RP_REVERSE
		; go find the end of array, keeping track of the prevRoom and currentRoom
		(for ((= value 0)) (!= (self at: value) PATHEND) ((++ value))
			(if (== (self at: value) NEXTROOM)
				(= currentRoom (self at: (+ value 1)))
			)
		)
	); findPathend

	(method (findPrevroom &tmp i) ; only used for RP_REVERSE
		; just look for previous room in up to the current spot in the array
		(for ((= i 0)) (< i value) ((++ i))
			(if (== (self at: i) NEXTROOM)
				(= currentRoom (self at: (+ i 1)))
			)
		)
	);findPrevroom
);RegionPath
