;;; Sierra Script 1.0 - (do not remove this comment)
(script# REGPATH) ;806
(include game.sh)
(use Main)
(use Motion)


(class RegionPath of MoveTo
	(properties
		completed 1
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
	
	(method (init theClient theCaller theIntermediate theEndType &tmp clientZ)
		(if completed
			(if argc
				(= client theClient)
				(if (>= argc 2)
					(= caller theCaller)
					(if (>= argc 3)
						(= intermediate theIntermediate)
						(if (>= argc 4) (= endType theEndType))
					)
				)
			)
			(if (not initialized)
				(self nextRoom: initialized: 1)
				(if (not savedOldStuff)
					(= theOldBits (client illegalBits?))
					(= theOldSignal (client signal?))
					(= savedOldStuff TRUE)
				)
				(client illegalBits: 0 ignoreActors:)
			)
			(= completed 0)
			(self next:)
		)
		(super init:)
		(= clientZ (client z?))
		(cond 
			((== currentRoom curRoomNum)
				(if (>= clientZ 1000)
					(client
						z: (- clientZ 1000)
						illegalBits: theOldBits
						signal: theOldSignal
					)
				)
			)
			((< clientZ 1000)
				(client
					z: (+ clientZ 1000)
					illegalBits: 0
					ignoreActors: 1
				)
			)
		)
	)
	
	(method (dispose)
		(if
		(and theRegion (not ((ScriptID theRegion) keep?)))
			(super dispose:)
		)
	)
	
	(method (moveDone)
		(= completed 1)
		(if (self atEnd:)
			(self value: -1 initialized: 0)
			(if endType (self init:) else (super moveDone:))
		else
			(if intermediate (intermediate cue: (/ value 2)))
			(if (== (self at: (+ value 1)) 32767)
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
		(if
		(and (!= currentRoom curRoomNum) (< (client z?) 1000))
			(client
				z: (+ (client z?) 1000)
				illegalBits: 0
				ignoreActors: 1
			)
		)
		(client posn: x y)
	)
	
	(method (at)
	)
)
