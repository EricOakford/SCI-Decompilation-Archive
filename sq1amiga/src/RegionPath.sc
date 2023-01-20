;;; Sierra Script 1.0 - (do not remove this comment)
(script# 806)
(include sci.sh)
(use Main)
(use Intrface)
(use Motion)


(class RegionPath of MoveTo
	(properties
		client 0
		caller 0
		x 0
		y 0
		dx 0
		dy 0
		b-moveCnt 0
		b-i1 0
		b-i2 0
		b-di 0
		b-xAxis 0
		b-incr 0
		completed 1
		xLast 0
		yLast 0
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
					(= savedOldStuff 1)
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
		(if theRegion
			(if (not ((ScriptID theRegion) keep?))
				(super dispose:)
			)
		else
			(Print 806 1 name)
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
		(Printf 806 0 name)
		(return 0)
	)
)
