;;; Sierra Script 1.0 - (do not remove this comment)
(script# 809)
(include game.sh)
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
		theRegion 0
		theOldBits 0
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
				(= theOldBits (client illegalBits?))
			)
			(= completed 0)
			(self next:)
			(super init:)
		)
		(= clientZ (client z?))
		(cond 
			((== currentRoom curRoomNum)
				(if (>= clientZ 1000)
					(client
						z: (- clientZ 1000)
						illegalBits: theOldBits
						ignoreActors: 0
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
		(if theRegion
			(if (not ((ScriptID theRegion) keep?))
				(super dispose:)
			)
		else
			(Print 809 1 name)
		)
	)
	
	(method (moveDone)
		(= completed TRUE)
		(if (self atEnd:)
			(self value: -1 initialized: FALSE)
			(if endType
				(self init:)
			else
				(super moveDone:)
			)
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
			(if (== (self at: (+ value 1)) -32768)
			else
				(== (self at: (+ value 2)) -32768)
			)
		)
	)
	
	(method (nextRoom)
		(self
			currentRoom: (self at: (= value (+ value 2)))
			next:
		)
		(client posn: x y)
	)
	
	(method (at)
		(Printf 809 0 name)
		(return 0)
	)
)
