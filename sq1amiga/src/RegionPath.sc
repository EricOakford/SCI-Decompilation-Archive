;;; Sierra Script 1.0 - (do not remove this comment)
(script# REGPATH) ;806
(include game.sh) (include regpath.sh)
(use Main)
(use Intrface)
(use Motion)


(class RegionPath of MoveTo
	(properties
		completed TRUE
		currentRoom 0
		value -1
		endType 1
		intermediate 0
		initialized 0
		savedOldStuff 0
		theRegion 0
		theOldBits 0
		theOldSignal 0
	)
	
	(method (init actor toCall inter reset &tmp theZ)
		(if completed
			(if argc
				(= client actor)
				(if (>= argc 2)
					(= caller toCall)
					(if (>= argc 3)
						(= intermediate inter)
						(if (>= argc 4)
							(= endType reset)
						)
					)
				)
			)
			(if (not initialized)
				(self nextRoom: initialized: TRUE)
				(if (not savedOldStuff)
					(= theOldBits (client illegalBits?))
					(= theOldSignal (client signal?))
					(= savedOldStuff TRUE)
				)
				(client illegalBits: 0 ignoreActors:)
			)
			(= completed FALSE)
			(self next:)
		)
		(super init:)
		(= theZ (client z?))
		(cond 
			((== currentRoom curRoomNum)
				(if (>= theZ 1000)
					(client
						z: (- theZ 1000)
						illegalBits: theOldBits
						signal: theOldSignal
					)
				)
			)
			((< theZ 1000)
				(client
					z: (+ theZ 1000)
					illegalBits: 0
					ignoreActors: TRUE
				)
			)
		)
	)
	
	(method (dispose)
		(if theRegion
			(if (not ((ScriptID theRegion) keep?))
				(super dispose:)
			)
		else
			(Print 806 1 name)
		)
	)
	
	(method (moveDone)
		(= completed TRUE)
		(if (self atEnd:)
			(self value: -1 initialized: FALSE)
			(if endType (self init:) else (super moveDone:))
		else
			(if intermediate (intermediate cue: (/ value 2)))
			(if (== (self at: (+ value 1)) NEXTROOM)
				(self nextRoom:)
			)
			(self init:)
		)
	)
	
	(method (next)
		(= x (self at: (++ value)))
		(= y (self at: (++ value)))
	)
	
	(method (atEnd)
		(return
			(if (== (self at: (+ value 1)) PATHEND)
			else
				(== (self at: (+ value 2)) PATHEND)
			)
		)
	)
	
	(method (nextRoom)
		(self
			currentRoom: (self at: (= value (+ value 2)))
			next:
		)
		(if (and (!= currentRoom curRoomNum) (< (client z?) 1000))
			(client
				z: (+ (client z?) 1000)
				illegalBits: 0
				ignoreActors: TRUE
			)
		)
		(client posn: x y)
	)
	
	(method (at)
		(Printf 806 0 name)
		(return 0)
	)
)
