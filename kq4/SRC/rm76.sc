;;; Sierra Script 1.0 - (do not remove this comment)
(script# 76)
(include game.sh)
(use Main)
(use Intrface)
(use Sound)
(use Motion)
(use Game)
(use System)

(public
	Room76 0
)

(local
	boardOverChasm
	egoY
)
(instance fallSound of Sound
	(properties
		number 52
	)
)

(instance Room76 of Room
	(properties
		picture 76
		style (| BLACKOUT IRISOUT)
		horizon 99
		north 73
	)
	
	(method (init)
		(Load VIEW 936)
		(Load SOUND 52)
		(self setRegions: TROLL_CAVE)
		(super init:)
		(= isIndoors TRUE)
		(= west 75)
		(ego view: 904 xStep: 4 yStep: 1)
		(if (== prevRoomNum 73)
			(ego posn: 189 118 init:)
		else
			(ego posn: 20 161 init:)
		)
		(ego priority: (CoordPri (ego y?)))
		(NotifyScript TROLL_CAVE 1)
	)
	
	(method (doit)
		(super doit:)
		(if (& (ego onControl: 0) $0040) (= newRoomNum 73))
		(if
			(and
				(& (ego onControl: 1) $0004)
				(not boardOverChasm)
				(!= (curRoom script?) fallChasm)
			)
			(self setScript: fallChasm)
		)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return TRUE))
		(return
			(if (== (event type?) saidEvent)
				(cond 
					((Said 'look[<around][/room]') (Print 76 0) (Print 76 1))
					((Said 'find,look/abyss')
						(if (& (ego onControl: 0) $1000)
							(Print 76 2)
						else
							(Print 76 3)
						)
					)
					((Said 'look/(,hole') (Print 76 4))
					((Said '(place,lay)[<down]/board')
						(cond 
							((not (ego has: iBoard)) (Print 76 5 #at -1 10 #time 5))
							(
							(or boardOverChasm (not (& (ego onControl: 0) $1000))) (NotClose))
							(else (self setScript: layBoard))
						)
					)
				)
			else
				FALSE
			)
		)
	)
)

(instance fallChasm of Script
	(properties)
	
	(method (init param1)
		(HandsOff)
		(NotifyScript TROLL_CAVE 4)
		(ego view: 936 setPri: 10 setCycle: Forward)
		(super init: param1)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					illegalBits: 0
					yStep: 10
					setMotion: MoveTo (+ (ego x?) 5) 300 self
				)
				(= cycles 2)
			)
			(1 (fallSound play: self))
			(2
				((inventory at: 3) loop: 0)
				(Print 76 6 #draw #mode teJustCenter #dispose)
				(= seconds 3)
			)
			(3
				(cls)
				(Print 76 7 #draw #mode teJustCenter #dispose)
			)
			(4
				(cls)
				(ShakeScreen 10)
				(= seconds 2)
			)
			(5 (= dead TRUE))
		)
	)
)

(instance layBoard of Script
	(properties)
	
	(method (init param1)
		(= egoY (ego y?))
		(if (< (/ global192 10) 2)
			(theGame changeScore: 2)
			(= global192 (+ global192 10))
		)
		(super init: param1)
	)
	
	(method (doit)
		(if
			(and
				(& (ego onControl: 1) $0004)
				(or
					(> (ego y?) (+ egoY 3))
					(< (ego y?) (- egoY 3))
				)
			)
			(= boardOverChasm FALSE)
			(client setScript: fallChasm)
		)
		(if
			(and
				(& (ego onControl: 1) $0004)
				(== boardOverChasm TRUE)
				(== laidDownBoard FALSE)
			)
			(= laidDownBoard 1)
		)
		(if
			(and
				(& (ego onControl: 1) $1000)
				(== laidDownBoard TRUE)
				(== boardOverChasm TRUE)
			)
			(= laidDownBoard FALSE)
			(= boardOverChasm FALSE)
			(self changeState: 1)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Print 76 8)
				(= boardOverChasm TRUE)
				((inventory at: iBoard) moveTo: 76)
			)
			(1
				(curRoom setScript: 0)
				(Print 76 9 #draw)
				(ego get: iBoard)
			)
		)
	)
)
